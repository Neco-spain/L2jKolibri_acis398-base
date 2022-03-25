package net.sf.l2j.mods.partyfarm;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import net.sf.l2j.Config;
import net.sf.l2j.commons.random.Rnd;
import net.sf.l2j.gameserver.model.actor.Creature;
import net.sf.l2j.gameserver.model.actor.Npc;
import net.sf.l2j.gameserver.model.actor.Playable;
import net.sf.l2j.gameserver.model.actor.Player;

/**
 * 
 * @author Sarada
 *
 */

public class PartyZoneReward {
	protected static final Logger _log = Logger.getLogger(PartyZoneReward.class.getName());

	protected PartyZoneReward() {
	}

	private static boolean _canReward = false;
	private static HashMap<String, Integer> _playerHwids = new HashMap<>();

	// Give Reward
	public final static void addPartyZoneReward(Creature killer, Npc monster) {
		if (killer instanceof Playable) {
			Player player = killer.getActingPlayer();

			if (player.isInParty()) {
				List<Player> party = player.getParty().getMembers();

				for (Player member : party) {
					// String pHwid = member.getHWID();
					String pHwid = member.getClient().getConnection().getInetAddress().getHostAddress();
					if (!_playerHwids.containsKey(pHwid) || Config.ENABLE_DUALBOX_PARTYFARM) {
						_playerHwids.put(pHwid, 1);
						_canReward = true;
					} else {
						int count = _playerHwids.get(pHwid);

						if (count < 1) {
							_playerHwids.remove(pHwid);
							_playerHwids.put(pHwid, count + 1);
							_canReward = true;
						} else {
							member.sendMessage("You are Other PC Reward.");
							_canReward = false;
						}
					}
					if (_canReward) {
						if (member.isIn3DRadius(monster.getX(), monster.getY(), monster.getZ(), 1000))
							RandomReward(member);
						else
							member.sendMessage("You are too far from your party to be rewarded.");
					}
				}
				_playerHwids.clear();
			} else
				RandomReward(player);
		}
	}

	public static void RandomReward(Player player) {
		for (net.sf.l2j.util.RewardHolder reward : Config.PARTY_ZONE_REWARDS) {
			if (Rnd.get(100) <= reward.getRewardChance()) {
				// if (player.isVip())
				// player.addItem("Random Reward", reward.getRewardId(),
				// Rnd.get(reward.getRewardMin(), reward.getRewardMax()) * Config.VIP_DROP_RATE,
				// player, true);
				// else
				player.addItem("Random Reward", reward.getRewardId(),
						Rnd.get(reward.getRewardMin(), reward.getRewardMax()), player, true);
			}
		}
	}

	public static final PartyZoneReward getInstance() {
		return SingletonHolder._instance;
	}

	private static class SingletonHolder {
		protected static final PartyZoneReward _instance = new PartyZoneReward();
	}
}