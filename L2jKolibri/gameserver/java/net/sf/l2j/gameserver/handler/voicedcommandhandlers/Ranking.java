package net.sf.l2j.gameserver.handler.voicedcommandhandlers;

import net.sf.l2j.gameserver.communitybbs.manager.RankingBBSManager;
import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;

public class Ranking implements IVoicedCommandHandler {
	private static final String[] VOICED_COMMANDS = { "menu" };

	@Override
	public boolean useVoicedCommand(final String command, final Player activeChar, final String target) {
		if (command.equalsIgnoreCase("menu")) {

			RankingBBSManager.getInstance().parseCmd("_bbsranking", activeChar);
			GrandBossStatus.showMainPage(activeChar);
		}
		return true;
	}

	@Override
	public String[] getVoicedCommandList() {
		return VOICED_COMMANDS;
	}
}
