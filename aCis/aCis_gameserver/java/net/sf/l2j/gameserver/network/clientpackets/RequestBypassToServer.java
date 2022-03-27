package net.sf.l2j.gameserver.network.clientpackets;

import java.util.StringTokenizer;
import java.util.logging.Logger;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.communitybbs.CommunityBoard;
import net.sf.l2j.gameserver.data.manager.HeroManager;
import net.sf.l2j.gameserver.data.xml.AdminData;
import net.sf.l2j.gameserver.enums.FloodProtector;
import net.sf.l2j.gameserver.enums.Paperdoll;
import net.sf.l2j.gameserver.handler.AdminCommandHandler;
import net.sf.l2j.gameserver.handler.BypassHandler;
import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.handler.IBypassHandler;
import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.handler.VoicedCommandHandler;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.WorldObject;
import net.sf.l2j.gameserver.model.actor.Npc;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.actor.instance.MultiShop;
import net.sf.l2j.gameserver.model.actor.instance.OlympiadManagerNpc;
import net.sf.l2j.gameserver.model.olympiad.OlympiadManager;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
import net.sf.l2j.gameserver.scripting.QuestState;
import net.sf.l2j.mods.skins.DressMeData;

public final class RequestBypassToServer extends L2GameClientPacket {
	private static final Logger GMAUDIT_LOG = Logger.getLogger("gmaudit");

	private String _command;

	@Override
	protected void readImpl() {
		_command = readS();
	}

	@Override
	protected void runImpl() {
		if (_command.isEmpty())
			return;

		if (!getClient().performAction(FloodProtector.SERVER_BYPASS))
			return;

		final Player player = getClient().getPlayer();
		if (player == null)
			return;

		if (_command.startsWith("admin_")) {
			String command = _command.split(" ")[0];

			final IAdminCommandHandler ach = AdminCommandHandler.getInstance().getHandler(command);
			if (ach == null) {
				if (player.isGM())
					player.sendMessage("The command " + command.substring(6) + " doesn't exist.");

				LOGGER.warn("No handler registered for admin command '{}'.", command);
				return;
			}

			if (!AdminData.getInstance().hasAccess(command, player.getAccessLevel())) {
				player.sendMessage("You don't have the access rights to use this command.");
				LOGGER.warn("{} tried to use admin command '{}' without proper Access Level.", player.getName(),
						command);
				return;
			}

			if (Config.GMAUDIT)
				GMAUDIT_LOG.info(player.getName() + " [" + player.getObjectId() + "] used '" + _command
						+ "' command on: " + ((player.getTarget() != null) ? player.getTarget().getName() : "none"));

			ach.useAdminCommand(_command, player);
		} else if (_command.startsWith("player_help ")) {
			final String path = _command.substring(12);
			if (path.indexOf("..") != -1)
				return;

			final StringTokenizer st = new StringTokenizer(path);
			final String[] cmd = st.nextToken().split("#");

			final NpcHtmlMessage html = new NpcHtmlMessage(0);
			html.setFile("data/html/help/" + cmd[0]);
			if (cmd.length > 1) {
				final int itemId = Integer.parseInt(cmd[1]);
				html.setItemId(itemId);

				if (itemId == 7064 && cmd[0].equalsIgnoreCase("lidias_diary/7064-16.htm")) {
					final QuestState qs = player.getQuestList().getQuestState("Q023_LidiasHeart");
					if (qs != null && qs.getCond() == 5 && qs.getInteger("diary") == 0)
						qs.set("diary", "1");
				}
			}
			html.disableValidation();
			player.sendPacket(html);
		}

		else if (_command.startsWith("bp_")) {
			String command = _command.split(" ")[0];
			IBypassHandler bh = BypassHandler.getInstance().getBypassHandler(command);
			if (bh == null) {
				GMAUDIT_LOG.warning("No handler registered for bypass '" + command + "'");
				return;
			}
			bh.handleBypass(_command, player);
		} else if (_command.startsWith("base"))
			MultiShop.Classes(_command, player);
		else if (_command.startsWith("voiced_")) {
			String command = _command.split(" ")[0];

			IVoicedCommandHandler ach = VoicedCommandHandler.getInstance()
					.getVoicedCommandHandler(_command.substring(7));

			if (ach == null) {
				player.sendMessage("The command " + command.substring(7) + " does not exist!");
				LOGGER.warn("No handler registered for command '" + _command + "'");
				return;
			}

			ach.useVoicedCommand(_command.substring(7), player, null);
		}

		else if (_command.startsWith("npc_")) {
			if (!player.validateBypass(_command))
				return;

			int endOfId = _command.indexOf('_', 5);
			String id;
			if (endOfId > 0)
				id = _command.substring(4, endOfId);
			else
				id = _command.substring(4);

			try {
				final WorldObject object = World.getInstance().getObject(Integer.parseInt(id));

				if (object instanceof Npc && endOfId > 0 && player.getAI().canDoInteract(object))
					((Npc) object).onBypassFeedback(player, _command.substring(endOfId + 1));

				player.sendPacket(ActionFailed.STATIC_PACKET);
			} catch (NumberFormatException nfe) {
			}
		}
		// Navigate throught Manor windows
		else if (_command.startsWith("manor_menu_select?")) {
			WorldObject object = player.getTarget();
			if (object instanceof Npc)
				((Npc) object).onBypassFeedback(player, _command);
		} else if (_command.startsWith("bbs_") || _command.startsWith("_bbs") || _command.startsWith("_friend")
				|| _command.startsWith("_mail") || _command.startsWith("_block")) {
			CommunityBoard.getInstance().handleCommands(getClient(), _command);
		} else if (_command.startsWith("Quest ")) {
			if (!player.validateBypass(_command))
				return;

			String[] str = _command.substring(6).trim().split(" ", 2);
			if (str.length == 1)
				player.getQuestList().processQuestEvent(str[0], "");
			else
				player.getQuestList().processQuestEvent(str[0], str[1]);
		} else if (_command.startsWith("_match")) {
			String params = _command.substring(_command.indexOf("?") + 1);
			StringTokenizer st = new StringTokenizer(params, "&");
			int heroclass = Integer.parseInt(st.nextToken().split("=")[1]);
			int heropage = Integer.parseInt(st.nextToken().split("=")[1]);
			int heroid = HeroManager.getInstance().getHeroByClass(heroclass);
			if (heroid > 0)
				HeroManager.getInstance().showHeroFights(player, heroclass, heroid, heropage);
		} else if (_command.startsWith("_diary")) {
			String params = _command.substring(_command.indexOf("?") + 1);
			StringTokenizer st = new StringTokenizer(params, "&");
			int heroclass = Integer.parseInt(st.nextToken().split("=")[1]);
			int heropage = Integer.parseInt(st.nextToken().split("=")[1]);
			int heroid = HeroManager.getInstance().getHeroByClass(heroclass);
			if (heroid > 0)
				HeroManager.getInstance().showHeroDiary(player, heroclass, heroid, heropage);
		} else if (_command.startsWith("arenachange")) // change
		{
			final boolean isManager = player.getCurrentFolk() instanceof OlympiadManagerNpc;
			if (!isManager) {
				// Without npc, command can be used only in observer mode on arena
				if (!player.isInObserverMode() || player.isInOlympiadMode() || player.getOlympiadGameId() < 0)
					return;
			}

			if (OlympiadManager.getInstance().isRegisteredInComp(player)) {
				player.sendPacket(
						SystemMessageId.WHILE_YOU_ARE_ON_THE_WAITING_LIST_YOU_ARE_NOT_ALLOWED_TO_WATCH_THE_GAME);
				return;
			}

			final int arenaId = Integer.parseInt(_command.substring(12).trim());
			player.enterOlympiadObserverMode(arenaId);
		}
	}

	public static void setPart(Player p, String part, String type) {
		if (p.getDressMeData() == null) {
			DressMeData dmd = new DressMeData();
			p.setDressMeData(dmd);
		}

		switch (part) {

		case "helmet": {
			if (Config.DRESS_ME_HELMET.keySet().contains(type)) {
				p.getDressMeData().setHelmetId(Config.DRESS_ME_HELMET.get(type));
			}

			break;
		}

		case "chest": {
			if (Config.DRESS_ME_CHESTS.keySet().contains(type)) {
				p.getDressMeData().setChestId(Config.DRESS_ME_CHESTS.get(type));
			}

			break;
		}
		case "legs": {
			if (Config.DRESS_ME_LEGS.keySet().contains(type)) {
				p.getDressMeData().setLegsId(Config.DRESS_ME_LEGS.get(type));
			}

			break;
		}
		case "gloves": {
			if (Config.DRESS_ME_GLOVES.keySet().contains(type)) {
				p.getDressMeData().setGlovesId(Config.DRESS_ME_GLOVES.get(type));
			}

			break;
		}
		case "boots": {
			if (Config.DRESS_ME_BOOTS.keySet().contains(type)) {
				p.getDressMeData().setBootsId(Config.DRESS_ME_BOOTS.get(type));
			}

			break;
		}

		}

		p.broadcastUserInfo();
		// sendEditWindow(p, part);
	}

	public static void stealTarget(Player p, String part) {
		if (p.getTarget() == null || !(p.getTarget() instanceof Player)) {
			p.sendMessage("Invalid target.");
			return;
		}

		Player t = (Player) p.getTarget();

		if (p.getDressMeData() == null) {
			DressMeData dmd = new DressMeData();
			p.setDressMeData(dmd);
		}

		switch (part) {
		case "helmet": {
			if (t.getInventory().getItemFrom(Paperdoll.FACE) == null) {
				p.getDressMeData().setHelmetId(0);
			} else {
				p.getDressMeData().setHelmetId(t.getInventory().getItemFrom(Paperdoll.FACE).getItemId());
			}
			break;
		}

		case "chest": {
			if (t.getInventory().getItemFrom(Paperdoll.CHEST) == null) {
				p.getDressMeData().setChestId(0);
			} else {
				p.getDressMeData().setChestId(t.getInventory().getItemFrom(Paperdoll.CHEST).getItemId());
			}
			break;
		}
		case "legs": {
			if (t.getInventory().getItemFrom(Paperdoll.LEGS) == null) {
				p.getDressMeData().setLegsId(0);
			} else {
				p.getDressMeData().setLegsId(t.getInventory().getItemFrom(Paperdoll.LEGS).getItemId());
			}
			break;
		}
		case "gloves": {
			if (t.getInventory().getItemFrom(Paperdoll.GLOVES) == null) {
				p.getDressMeData().setGlovesId(0);
			} else {
				p.getDressMeData().setGlovesId(t.getInventory().getItemFrom(Paperdoll.GLOVES).getItemId());
			}
			break;
		}
		case "boots": {
			if (t.getInventory().getItemFrom(Paperdoll.FEET) == null) {
				p.getDressMeData().setBootsId(0);
			} else {
				p.getDressMeData().setBootsId(t.getInventory().getItemFrom(Paperdoll.FEET).getItemId());
			}
			break;
		}
		case "all": {

			if (t.getInventory().getItemFrom(Paperdoll.FACE) == null) {
				p.getDressMeData().setHelmetId(0);
			} else {
				p.getDressMeData().setHelmetId(t.getInventory().getItemFrom(Paperdoll.FACE).getItemId());
			}

			if (t.getInventory().getItemFrom(Paperdoll.CHEST) == null) {
				p.getDressMeData().setChestId(0);
			} else {
				p.getDressMeData().setChestId(t.getInventory().getItemFrom(Paperdoll.CHEST).getItemId());
			}
			if (t.getInventory().getItemFrom(Paperdoll.LEGS) == null) {
				p.getDressMeData().setLegsId(0);
			} else {
				p.getDressMeData().setLegsId(t.getInventory().getItemFrom(Paperdoll.LEGS).getItemId());
			}
			if (t.getInventory().getItemFrom(Paperdoll.GLOVES) == null) {
				p.getDressMeData().setGlovesId(0);
			} else {
				p.getDressMeData().setGlovesId(t.getInventory().getItemFrom(Paperdoll.GLOVES).getItemId());
			}
			if (t.getInventory().getItemFrom(Paperdoll.FEET) == null) {
				p.getDressMeData().setBootsId(0);
			} else {
				p.getDressMeData().setBootsId(t.getInventory().getItemFrom(Paperdoll.FEET).getItemId());
			}

			break;
		}
		}

		p.broadcastUserInfo();
	}
}