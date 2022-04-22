package net.sf.l2j.gameserver.handler.admincommandhandlers;

import java.util.StringTokenizer;

import net.sf.l2j.Config;
import net.sf.l2j.commons.pool.ThreadPool;
import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.mods.partyfarm.InitialPartyFarm;
import net.sf.l2j.mods.pcbang.PcBang;

public class AdminTest implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS = { "admin_test", };

	@Override
	public void useAdminCommand(String command, Player player) {
		final StringTokenizer st = new StringTokenizer(command);
		st.nextToken();

		if (!st.hasMoreTokens()) {
			InitialPartyFarm.getInstance().StartCalculationOfNextEventTime();
			LOGGER.info("[Party Farm Time]: Enabled");
			if (Config.PCB_ENABLE) {
				System.out.println("############PCB_ENABLE################");
				ThreadPool.scheduleAtFixedRate(PcBang.getInstance(), Config.PCB_INTERVAL * 1000,
						Config.PCB_INTERVAL * 1000);
				player.sendMessage(
						"Reloaded PartyZone next Event at : " + InitialPartyFarm.getInstance().getRestartNextTime());
				player.sendMessage("Reloaded PcBang");

			}
			return;
		}

		switch (st.nextToken()) {
		// Add your own cases.

		default:
			InitialPartyFarm.getInstance().StartCalculationOfNextEventTime();
			LOGGER.info("[Party Farm Time]: Enabled");
			if (Config.PCB_ENABLE) {
				System.out.println("############PCB_ENABLE################");
				ThreadPool.scheduleAtFixedRate(PcBang.getInstance(), Config.PCB_INTERVAL * 1000,
						Config.PCB_INTERVAL * 1000);
				player.sendMessage("Reloaded PartyZone next Event at : " + InitialPartyFarm.getInstance());
				player.sendMessage("Reloaded" + PcBang.getInstance());
			}
			break;
		}

	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}
}