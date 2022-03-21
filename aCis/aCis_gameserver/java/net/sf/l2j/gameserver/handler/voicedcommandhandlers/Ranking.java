package net.sf.l2j.gameserver.handler.voicedcommandhandlers;

import net.sf.l2j.gameserver.communitybbs.manager.RankingBBSManager;
import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;

public class Ranking implements IVoicedCommandHandler {
	private static final String[] VOICED_COMMANDS = { "ranking" };

	@Override
	public boolean useVoicedCommand(final String command, final Player activeChar, final String target) {
		if (command.equalsIgnoreCase("ranking")) {

			RankingBBSManager.getInstance().parseCmd("_bbsranking", activeChar);
		}
		return true;
	}

	@Override
	public String[] getVoicedCommandList() {
		return VOICED_COMMANDS;
	}
}
