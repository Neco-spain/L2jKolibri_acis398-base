/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.l2j.gameserver.handler.voicedcommandhandlers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import net.sf.l2j.Config;
import net.sf.l2j.commons.pool.ThreadPool;
import net.sf.l2j.gameserver.communitybbs.manager.RankingBBSManager;
import net.sf.l2j.gameserver.communitybbs.manager.RepairBBSManager;
import net.sf.l2j.gameserver.data.manager.CastleManager;
import net.sf.l2j.gameserver.enums.SayType;
import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.entity.Castle;
import net.sf.l2j.gameserver.network.serverpackets.CreatureSay;
import net.sf.l2j.gameserver.network.serverpackets.MagicSkillUse;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
import net.sf.l2j.gameserver.network.serverpackets.SiegeInfo;
import net.sf.l2j.mods.Tournament.TournamentManager;
import net.sf.l2j.mods.Tournament.enums.TournamentFightType;

/**
 * @author Kolibri
 */

public class Menu implements IVoicedCommandHandler

{
	private static final String ACTIVED = "<font color=00FF00>ON</font>";

	private static final String DESATIVED = "<font color=FF0000>OFF</font>";
	String pattern = "dd/MM/yyyy HH:mm:ss";

	// Create an instance of SimpleDateFormat used for formatting 
	// the string representation of date according to the chosen pattern
	DateFormat df = new SimpleDateFormat(pattern);

	// Get the today date using Calendar object.
	Date today = Calendar.getInstance().getTime();        
	// Using DateFormat format method we can create a string 
	// representation of a date with the defined format.
	String todayAsString = df.format(today);
	private static final String[] _voicedCommands =

			{

					"menu", "setPartyRefuse", "setTradeRefuse", "setbuffsRefuse", "setMessageRefuse", "raid", "rank",
					"visualTest", "skins", "skin", "castlemanager", "siege_of_gludio", "siege_of_dion",
					"siege_of_giran", "siege_of_oren", "siege_of_aden", "siege_of_innadril", "siege_of_goddard",
					"siege_of_rune", "siege_of_schuttgart", "siege_of_", "repair", "time", "mytour",

			};

	@Override

	public boolean useVoicedCommand(String command, Player activeChar, String target)

	{

		if (command.equals("menu"))

			showHtml(activeChar);

		else if (command.equals("setPartyRefuse"))

		{

			if (activeChar.isPartyInRefuse())

				activeChar.setIsPartyInRefuse(false);

			else

				activeChar.setIsPartyInRefuse(true);

			showHtml(activeChar);

		}

		else if (command.equals("setTradeRefuse"))

		{

			if (activeChar.isInTradeProt())

				activeChar.setIsInTradeProt(false);

			else

				activeChar.setIsInTradeProt(true);

			showHtml(activeChar);

		}

		else if (command.equals("setMessageRefuse"))

		{

			if (activeChar.getBlockList().isBlockingAll())

				activeChar.getBlockList().setInBlockingAll(false);

			else

				activeChar.getBlockList().setInBlockingAll(true);

			showHtml(activeChar);

		}

		else if (command.equals("setbuffsRefuse"))

		{

			if (activeChar.isBuffProtected())

				activeChar.setIsBuffProtected(false);

			else

				activeChar.setIsBuffProtected(true);

			showHtml(activeChar);

		} else if (command.equals("skins")) {
			skinsHtml(activeChar);
		}

		else if (command.startsWith("visualTest")) {
			if (activeChar.getVisualTest() > 0) {
				activeChar.sendPacket(new CreatureSay(0, SayType.TELL, "[SKIN System]",
						"You are already trying on a skin, please wait till it finishes."));

				skinsHtml(activeChar);
				return false;

			}
			activeChar.broadcastPacket(new MagicSkillUse(activeChar, Config.SKILL_ID_SKIN1, 1, 1000, 1000));
			activeChar.setDressMeEnabled(false);
			int uniform = Integer.parseInt(command.substring(11));
			activeChar.setVisualTest(uniform);
			ThreadPool.schedule(() -> activeChar.setVisualTest(0), 1000 * 15);
			activeChar.broadcastUserInfo();
			skinsHtml(activeChar);

		} else if (command.equals("raid")) {

			GrandBossStatus.showMainPage(activeChar);
		} else if (command.equals("rank")) {

			RankingBBSManager.getInstance().parseCmd("_bbsranking", activeChar);
			showHtml(activeChar);
		} else if (command.equals("repair")) {

			RepairBBSManager.getInstance().parseCmd("_bbsShowRepair", activeChar);
			showHtml(activeChar);
		} else if (command.equals("time")) {

			
			activeChar.sendPacket(
					new CreatureSay(0, SayType.PARTY, "[System]", "Current Server's  time is : " + todayAsString + "."));
			showHtml(activeChar);
		} else if (command.startsWith("mytour")) {
			TournamentManager.getInstance().showHtml(activeChar, "myTour", TournamentFightType.NONE);
		}
		if (Config.ENABLE_GNU_PANEL) {
			if (command.startsWith("castlemanager")) {
				sendHtml(activeChar);
			}

			if (command.startsWith("siege_")) {
				if (activeChar.getClan() != null && !activeChar.isClanLeader()) {
					activeChar.sendMessage("Only Clan Leader can use this command");
					return false;
				}

				int castleId = 0;

				if (command.startsWith("siege_of_gludio")) {
					castleId = 1;
					activeChar.sendMessage("Grettings from Gludio Castle");
				}
				if (command.startsWith("siege_of_dion")) {
					castleId = 2;
					activeChar.sendMessage("Grettings from Dion Castle");
				}
				if (command.startsWith("siege_of_giran")) {
					castleId = 3;
					activeChar.sendMessage("Grettings from Giran Castle");
				}
				if (command.startsWith("siege_of_oren")) {
					castleId = 4;
					activeChar.sendMessage("Grettings from Oren Castle");
				}
				if (command.startsWith("siege_of_aden")) {
					castleId = 5;
					activeChar.sendMessage("Grettings from Aden Castle");
				}
				if (command.startsWith("siege_of_innadril")) {
					castleId = 6;
					activeChar.sendMessage("Grettings from Innadril Castle");
				}
				if (command.startsWith("siege_of_goddard")) {
					castleId = 7;
					activeChar.sendMessage("Grettings from Goddard Castle");
				}
				if (command.startsWith("siege_of_rune")) {
					castleId = 8;
					activeChar.sendMessage("Grettings from Rune Castle");
				}
				if (command.startsWith("siege_of_schuttgart")) {
					castleId = 9;
					activeChar.sendMessage("Grettings from Schuttgart Castle");
				}

				Castle castle = CastleManager.getInstance().getCastleById(castleId);
				if (castle != null && castleId != 0) {
					activeChar.sendPacket(new SiegeInfo(castle));
				}
			}
		}
		return true;

	}

	private static void showHtml(Player activeChar)

	{
		

		NpcHtmlMessage html = new NpcHtmlMessage(0);
		String pattern = "dd/MM/yyyy HH:mm:ss";

		// Create an instance of SimpleDateFormat used for formatting 
		// the string representation of date according to the chosen pattern
		DateFormat df = new SimpleDateFormat(pattern);

		// Get the today date using Calendar object.
		Date today = Calendar.getInstance().getTime();        
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String todayAsString = df.format(today); 

		html.setFile("data/html/mods/menu.htm");
		html.replace("%time%" , todayAsString);

		html.replace("%online%", World.getInstance().getPlayers().size());

		html.replace("%partyRefusal%", activeChar.isPartyInRefuse() ? ACTIVED : DESATIVED);

		html.replace("%tradeRefusal%", activeChar.isInTradeProt() ? ACTIVED : DESATIVED);

		html.replace("%buffsRefusal%", activeChar.isBuffProtected() ? ACTIVED : DESATIVED);

		html.replace("%messageRefusal%", activeChar.isInRefusalMode() ? ACTIVED : DESATIVED);

		activeChar.sendPacket(html);

	}

	public static void skinsHtml(Player activeChar)

	{

		NpcHtmlMessage html = new NpcHtmlMessage(0);
		// skins html file
		html.setFile("data/html/mods/menu-1.htm");

		activeChar.sendPacket(html);
	}

//castlemanager file
	public static void sendHtml(Player activeChar) {
		NpcHtmlMessage html = new NpcHtmlMessage(0);

		html.setFile("data/html/mods/Castle_Info.htm");

		activeChar.sendPacket(html);
	}

	@Override

	public String[] getVoicedCommandList()

	{

		return _voicedCommands;

	}

}