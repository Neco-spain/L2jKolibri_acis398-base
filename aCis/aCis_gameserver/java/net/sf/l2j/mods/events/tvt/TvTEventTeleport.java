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
package net.sf.l2j.mods.events.tvt;

import net.sf.l2j.Config;
import net.sf.l2j.commons.pool.ThreadPool;
import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.actor.Summon;
import net.sf.l2j.gameserver.skills.AbstractEffect;
import net.sf.l2j.gameserver.skills.L2Skill;

public class TvTEventTeleport implements Runnable {
	/** Gives Noblesse to players */
	static L2Skill noblesse = SkillTable.getInstance().getInfo(1323, 1);
	/** The instance of the player to teleport */
	public Player _playerInstance;
	/** Coordinates of the spot to teleport to */
	public int[] _coordinates = new int[3];
	/** Admin removed this player from event */
	private boolean _adminRemove;

	/**
	 * Initialize the teleporter and start the delayed task
	 * 
	 * @param playerInstance
	 * @param coordinates
	 * @param fastSchedule
	 * @param adminRemove
	 */
	public TvTEventTeleport(Player playerInstance, int[] coordinates, boolean fastSchedule, boolean adminRemove) {
		_playerInstance = playerInstance;
		_coordinates = coordinates;
		_adminRemove = adminRemove;

		// in config as seconds
		long delay = (TvTEvent.isStarted() ? Config.TVT_EVENT_RESPAWN_TELEPORT_DELAY
				: Config.TVT_EVENT_START_LEAVE_TELEPORT_DELAY) * 1000;

		if (fastSchedule)
			delay = 0;

		ThreadPool.schedule(this, delay);
	}

	/**
	 * The task method to teleport the player<br>
	 * 1. Unsummon pet if there is one 2. Remove all effects 3. Revive and full heal
	 * the player 4. Teleport the player 5. Broadcast status and user info
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		if (_playerInstance == null)
			return;

		Summon summon = _playerInstance.getSummon();

		if (summon != null)
			summon.unSummon(_playerInstance);

		for (AbstractEffect effect : _playerInstance.getAllEffects()) {
			if (Config.TVT_EVENT_REMOVE_BUFFS && effect != null)
				effect.exit();
		}

		ThreadPool.schedule(new Runnable() {
			@Override
			public void run() {
				_playerInstance.doRevive();
				_playerInstance.getStatus().setMaxCpHpMp();
				noblesse.getEffects(_playerInstance, _playerInstance);
				_playerInstance.teleportTo(_coordinates[0], _coordinates[1], _coordinates[2], 0);
			}
		}, 4000);

		if (TvTEvent.isStarted() && !_adminRemove)
			_playerInstance.setTeam(TvTEvent.getParticipantTeamId(_playerInstance.getName()) + 1);
		else
			_playerInstance.setTeam(0);

		// _playerInstance.broadcastStatusUpdate();
		_playerInstance.broadcastUserInfo();
	}
}