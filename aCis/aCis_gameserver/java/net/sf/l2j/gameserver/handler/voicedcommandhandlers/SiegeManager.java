//package net.sf.l2j.gameserver.handler.voicedcommandhandlers;
//
//import net.sf.l2j.Config;
//import net.sf.l2j.gameserver.data.manager.CastleManager;
//import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
//import net.sf.l2j.gameserver.model.actor.Player;
//import net.sf.l2j.gameserver.model.entity.Castle;
//import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
//import net.sf.l2j.gameserver.network.serverpackets.SiegeInfo;
//
//public class SiegeManager implements IVoicedCommandHandler {
//	private static final String[] VOICED_COMMANDS = { "castlemanager", "siege_of_gludio", "siege_of_dion",
//			"siege_of_giran", "siege_of_oren", "siege_of_aden", "siege_of_innadril", "siege_of_goddard",
//			"siege_of_rune", "siege_of_schuttgart", "siege_of_" };
//
//	@Override
//	public boolean useVoicedCommand(String command, Player activeChar, String target) {
//		if (Config.ENABLE_GNU_PANEL) {
//			if (command.startsWith("castlemanager")) {
//				sendHtml(activeChar);
//			}
//
//			if (command.startsWith("siege_")) {
//				if (activeChar.getClan() != null && !activeChar.isClanLeader()) {
//					activeChar.sendMessage("Only Clan Leader can be use this command");
//					return false;
//				}
//
//				int castleId = 0;
//
//				if (command.startsWith("siege_of_gludio")) {
//					castleId = 1;
//					activeChar.sendMessage("Grettings from Gludio Castle");
//				}
//				if (command.startsWith("siege_of_dion")) {
//					castleId = 2;
//					activeChar.sendMessage("Grettings from Dion Castle");
//				}
//				if (command.startsWith("siege_of_giran")) {
//					castleId = 3;
//					activeChar.sendMessage("Grettings from Giran Castle");
//				}
//				if (command.startsWith("siege_of_oren")) {
//					castleId = 4;
//					activeChar.sendMessage("Grettings from Oren Castle");
//				}
//				if (command.startsWith("siege_of_aden")) {
//					castleId = 5;
//					activeChar.sendMessage("Grettings from Aden Castle");
//				}
//				if (command.startsWith("siege_of_innadril")) {
//					castleId = 6;
//					activeChar.sendMessage("Grettings from Innadril Castle");
//				}
//				if (command.startsWith("siege_of_goddard")) {
//					castleId = 7;
//					activeChar.sendMessage("Grettings from Goddard Castle");
//				}
//				if (command.startsWith("siege_of_rune")) {
//					castleId = 8;
//					activeChar.sendMessage("Grettings from Rune Castle");
//				}
//				if (command.startsWith("siege_of_schuttgart")) {
//					castleId = 9;
//					activeChar.sendMessage("Grettings from Schuttgart Castle");
//				}
//
//				Castle castle = CastleManager.getInstance().getCastleById(castleId);
//				if (castle != null && castleId != 0) {
//					activeChar.sendPacket(new SiegeInfo(castle));
//				}
//			}
//			return true;
//		} else if (!Config.ENABLE_GNU_PANEL) {
//			if (command.startsWith("castlemanager")) {
//				sendErrorHtml(activeChar);
//			}
//
//			if (command.startsWith("siege_")) {
//				if (activeChar.getClan() != null && !activeChar.isClanLeader()) {
//					activeChar.sendMessage("Only Clan Leader can be use this command");
//					return false;
//				}
//
//				int castleId = 0;
//
//				if (command.startsWith("siege_of_gludio")) {
//					castleId = 1;
//					activeChar.sendMessage("Grettings from Gludio Castle");
//				}
//				if (command.startsWith("siege_of_dion")) {
//					castleId = 2;
//					activeChar.sendMessage("Grettings from Dion Castle");
//				}
//				if (command.startsWith("siege_of_giran")) {
//					castleId = 3;
//					activeChar.sendMessage("Grettings from Giran Castle");
//				}
//				if (command.startsWith("siege_of_oren")) {
//					castleId = 4;
//					activeChar.sendMessage("Grettings from Oren Castle");
//				}
//				if (command.startsWith("siege_of_aden")) {
//					castleId = 5;
//					activeChar.sendMessage("Grettings from Aden Castle");
//				}
//				if (command.startsWith("siege_of_innadril")) {
//					castleId = 6;
//					activeChar.sendMessage("Grettings from Innadril Castle");
//				}
//				if (command.startsWith("siege_of_goddard")) {
//					castleId = 7;
//					activeChar.sendMessage("Grettings from Goddard Castle");
//				}
//				if (command.startsWith("siege_of_rune")) {
//					castleId = 8;
//					activeChar.sendMessage("Grettings from Rune Castle");
//				}
//				if (command.startsWith("siege_of_schuttgart")) {
//					castleId = 9;
//					activeChar.sendMessage("Grettings from Schuttgart Castle");
//				}
//
//				Castle castle = CastleManager.getInstance().getCastleById(castleId);
//				if (castle != null && castleId != 0) {
//					activeChar.sendPacket(new SiegeInfo(castle));
//				}
//			}
//		}
//		return true;
//	}
//
//	private void sendHtml(Player activeChar) {
//		String htmFile = "data/html/mods/Castle_Info.htm";
//
//		NpcHtmlMessage msg = new NpcHtmlMessage(5);
//		msg.setFile(htmFile);
//		activeChar.sendPacket(msg);
//	}
//
//	private void sendErrorHtml(Player activeChar) {
//		String htmFile = "data/html/mods/Castle_Error_Info.htm";
//
//		NpcHtmlMessage msg = new NpcHtmlMessage(5);
//		msg.setFile(htmFile);
//		activeChar.sendPacket(msg);
//	}
//
//	@Override
//	public String[] getVoicedCommandList() {
//		return VOICED_COMMANDS;
//	}
//}