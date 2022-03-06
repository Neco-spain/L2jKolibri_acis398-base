package net.sf.l2j.gameserver.handler.admincommandhandlers;

import java.util.StringTokenizer;

import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.mods.partyfarm.InitialPartyFarm;

public class AdminTest implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS = { "admin_test", };

	@Override
	public void useAdminCommand(String command, Player player) {
		final StringTokenizer st = new StringTokenizer(command);
		st.nextToken();

		if (!st.hasMoreTokens()) {
			InitialPartyFarm.getInstance().StartCalculationOfNextEventTime();
			LOGGER.info("[Party Farm Time]: Enabled");
			player.sendMessage("Usage : //test ...");
			return;
		}

		switch (st.nextToken()) {
		// Add your own cases.

		default:
			InitialPartyFarm.getInstance().StartCalculationOfNextEventTime();
			LOGGER.info("[Party Farm Time]: Enabled");
			player.sendMessage("Usage : //test ...");
			break;
		}

	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}
}