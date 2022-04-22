package net.sf.l2j.gameserver.network.clientpackets;

import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.WorldObject;
import net.sf.l2j.gameserver.model.actor.Creature;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;

public final class Action extends L2GameClientPacket {
	private int _objectId;
	@SuppressWarnings("unused")
	private int _originX, _originY, _originZ;
	private int _actionId;

	@Override
	protected void readImpl() {
		_objectId = readD();
		_originX = readD();
		_originY = readD();
		_originZ = readD();
		_actionId = readC();
	}

	@Override
	protected void runImpl() {
		final Player player = getClient().getPlayer();
		if (player == null)
			return;

		if (player.isInObserverMode()) {
			player.sendPacket(SystemMessageId.OBSERVERS_CANNOT_PARTICIPATE);
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (player.getActiveRequester() != null) {
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final WorldObject target = (player.getTargetId() == _objectId) ? player.getTarget()
				: World.getInstance().getObject(_objectId);
		if (target == null) {
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Check if the target is valid, if the player haven't a shop or isn't the
		// requester of a transaction (ex : FriendInvite, JoinAlly, JoinParty...)
		if (player.getActiveRequester() == null) {
			switch (_actionId) {
			case 0: {
				target.onAction(player, false, false);
				break;
			}
			case 1: {
				if ((target instanceof Creature) && ((Creature) target).isAlikeDead()) {
					target.onAction(player, false, false);
				} else {
					target.onActionShift(player);
				}
				break;
			}
			default: {
				// Invalid action detected (probably client cheating), LOGGER this
				LOGGER.warn("Character: " + player.getName() + " requested invalid action: " + _actionId);
				player.sendPacket(ActionFailed.STATIC_PACKET);
				break;
			}
			}
		} else {
			player.sendPacket(ActionFailed.STATIC_PACKET); // Actions prohibited when in trade
		}
	}
}