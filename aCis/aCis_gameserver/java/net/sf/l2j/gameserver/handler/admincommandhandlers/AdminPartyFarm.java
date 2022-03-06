package net.sf.l2j.gameserver.handler.admincommandhandlers;

import java.util.logging.Logger;

import net.sf.l2j.commons.pool.ThreadPool;
import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.mods.partyfarm.PartyFarm;
//import Dev.Tournament.properties.ArenaTask;

public class AdminPartyFarm implements IAdminCommandHandler {

	private static final String[] ADMIN_COMMANDS = { "admin_tour", "admin_ptfarm"

	};

	protected static final Logger _log = Logger.getLogger(AdminPartyFarm.class.getName());
	public static boolean _arena_manual = false;
	public static boolean _bestfarm_manual = false;

	@Override
	public void useAdminCommand(String command, Player activeChar) {

		/*
		 * if (command.equals("admin_tour")) { if (ArenaTask._started) { _log.info(
		 * "----------------------------------------------------------------------------"
		 * ); _log.info("[Tournament]: Event Finished."); _log.info(
		 * "----------------------------------------------------------------------------"
		 * ); ArenaTask._aborted = true; finishEventArena(); _arena_manual = true;
		 * 
		 * activeChar.
		 * sendMessage("SYS: Voce Finalizou o evento Tournament Manualmente.."); } else
		 * { _log.info(
		 * "----------------------------------------------------------------------------"
		 * ); _log.info("[Tournament]: Event Started."); _log.info(
		 * "----------------------------------------------------------------------------"
		 * ); initEventArena(); _arena_manual = true;
		 * activeChar.sendMessage("SYS: Voce ativou o evento Tournament Manualmente..");
		 * }
		 * 
		 * return true; }
		 */
		if (command.equals("admin_ptfarm")) {
			if (PartyFarm._started) {
				_log.info("----------------------------------------------------------------------------");
				_log.info("[Party Farm]: Event Finished.");
				_log.info("----------------------------------------------------------------------------");
				PartyFarm._aborted = true;
				finishEventPartyFarm();

				activeChar.sendMessage("SYS: Voce Finalizou o Party Farm Manualmente..");
			} else {
				_log.info("----------------------------------------------------------------------------");
				_log.info("[Party Farm]: Event Started.");
				_log.info("----------------------------------------------------------------------------");
				initEventPartyFarm();
				_bestfarm_manual = true;
				activeChar.sendMessage("SYS: Voce ativou o Best Farm Manualmente..");
			}
		}
		return;

	}

	private static void initEventPartyFarm() {
		ThreadPool.schedule(new Runnable() {
			@Override
			public void run() {

				PartyFarm.bossSpawnMonster();
			}
		}, 1L);
	}

	private static void finishEventPartyFarm() {
		ThreadPool.schedule(new Runnable() {
			@Override
			public void run() {

				PartyFarm.Finish_Event();

			}
		}, 1L);
	}

	/*
	 * private static void initEventArena() {
	 * ThreadPoolManager.getInstance().scheduleGeneral(new Runnable() {
	 * 
	 * @Override public void run() {
	 * 
	 * ArenaTask.SpawnEvent(); } }, 10L); }
	 * 
	 * private static void finishEventArena() {
	 * ThreadPoolManager.getInstance().scheduleGeneral(new Runnable() {
	 * 
	 * @Override public void run() {
	 * 
	 * ArenaTask.finishEvent(); } }, 10L); }
	 */

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}
}