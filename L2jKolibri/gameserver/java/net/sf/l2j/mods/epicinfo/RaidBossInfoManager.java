package net.sf.l2j.mods.epicinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import net.sf.l2j.Config;
import net.sf.l2j.commons.pool.ConnectionPool;

public class RaidBossInfoManager {
	private static final Logger _log = Logger.getLogger(RaidBossInfoManager.class.getName());
	private final Map<Integer, Long> _raidBosses;

	protected RaidBossInfoManager() {
		_raidBosses = new ConcurrentHashMap<>();
		load();
	}

	public void load() {
		try (Connection con = ConnectionPool.getConnection();
				PreparedStatement statement = con.prepareStatement(
						"SELECT boss_id, respawn_time FROM grandboss_data UNION SELECT boss_id, respawn_time FROM raidboss_spawnlist ORDER BY boss_id")) {
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					int bossId = rs.getInt("boss_id");
					if (Config.LIST_RAID_BOSS_IDS.contains(bossId))
						_raidBosses.put(bossId, rs.getLong("respawn_time"));
				}
			}
		} catch (Exception e) {
			_log.warning("Exception: RaidBossInfoManager load: " + e);
		}
		_log.info("RaidBossInfoManager: Loaded " + _raidBosses.size() + " instances.");
	}

	public void updateRaidBossInfo(int bossId, long respawnTime) {
		_raidBosses.put(bossId, respawnTime);
	}

	public long getRaidBossRespawnTime(int bossId) {
		return _raidBosses.get(bossId);
	}

	public static RaidBossInfoManager getInstance() {
		return SingletonHolder._instance;
	}

	private static class SingletonHolder {
		protected static final RaidBossInfoManager _instance = new RaidBossInfoManager();
	}
}