package net.sf.l2j.gameserver.network.clientpackets;

import net.sf.l2j.gameserver.data.manager.FestivalOfDarknessManager;
import net.sf.l2j.gameserver.enums.ZoneId;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.entity.Tournament.TournamentManager;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
import net.sf.l2j.gameserver.taskmanager.AttackStanceTaskManager;
import net.sf.l2j.mods.events.tvt.TvTEvent;

public final class Logout extends L2GameClientPacket {
	@Override
	protected void readImpl() {
	}

	@Override
	protected void runImpl() {
		final Player player = getClient().getPlayer();
		if (player == null)
			return;

		if (player.getActiveEnchantItem() != null || player.isLocked()) {
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		if (!TvTEvent.isInactive() && TvTEvent.isPlayerParticipant(player.getName())) {
			player.sendMessage("You can not leave the game while attending an event.");
			return;
		}
		if (player.isInsideZone(ZoneId.NO_RESTART)) {
			player.sendPacket(SystemMessageId.NO_LOGOUT_HERE);
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (AttackStanceTaskManager.getInstance().isInAttackStance(player)) {
			player.sendPacket(SystemMessageId.CANT_LOGOUT_WHILE_FIGHTING);
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (player.isFestivalParticipant() && FestivalOfDarknessManager.getInstance().isFestivalInitialized()) {
			player.sendPacket(SystemMessageId.NO_LOGOUT_HERE);
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		TournamentManager.getInstance().onDisconnect(player);
		player.removeFromBossZone();
		player.logout(true);
	}
}