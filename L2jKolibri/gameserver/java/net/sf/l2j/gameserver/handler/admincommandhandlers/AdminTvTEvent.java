package net.sf.l2j.gameserver.handler.admincommandhandlers;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.model.WorldObject;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.mods.events.tvt.TvTEvent;
import net.sf.l2j.mods.events.tvt.TvTEventTeleport;

/**
 * @author FBIagent The class handles administrator commands for the TvT Engine
 *         which was first implemented by FBIagent
 */
public class AdminTvTEvent implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS = { "admin_tvt_add", "admin_tvt_remove" };

	@Override
	public void useAdminCommand(String command, Player adminInstance) {

		// AdminData.auditGMAction(adminInstance.getName(), command,
		// (adminInstance.getTarget() != null ? adminInstance.getTarget().getName() :
		// "no-target"), "");

		if (command.equals("admin_tvt_add")) {
			WorldObject target = adminInstance.getTarget();

			if (target == null || !(target instanceof Player)) {
				adminInstance.sendMessage("You should select a player!");
				return;
			}

			add(adminInstance, (Player) target);
		} else if (command.equals("admin_tvt_remove")) {
			WorldObject target = adminInstance.getTarget();

			if (target == null || !(target instanceof Player)) {
				adminInstance.sendMessage("You should select a player!");
				return;
			}

			remove(adminInstance, (Player) target);
		}

		return;
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	private static void add(Player adminInstance, Player playerInstance) {
		if (TvTEvent.isPlayerParticipant(playerInstance.getName())) {
			adminInstance.sendMessage("Player already participated in the event!");
			return;
		}

		if (!TvTEvent.addParticipant(playerInstance)) {
			adminInstance.sendMessage("Player instance could not be added, it seems to be null!");
			return;
		}

		if (TvTEvent.isStarted())
			// we don't need to check return value of
			// TvTEvent.getParticipantTeamCoordinates() for null, TvTEvent.addParticipant()
			// returned true so target is in event
			new TvTEventTeleport(playerInstance, TvTEvent.getParticipantTeamCoordinates(playerInstance.getName()), true,
					false);
	}

	private static void remove(Player adminInstance, Player playerInstance) {
		if (!TvTEvent.removeParticipant(playerInstance.getName())) {
			adminInstance.sendMessage("Player is not part of the event!");
			return;
		}

		new TvTEventTeleport(playerInstance, Config.TVT_EVENT_BACK_COORDINATES, true, true);
	}
}