package net.sf.l2j.gameserver.model.zone.type;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.data.xml.MapRegionData.TeleportType;
import net.sf.l2j.gameserver.enums.SayType;
import net.sf.l2j.gameserver.enums.ZoneId;
import net.sf.l2j.gameserver.model.actor.Creature;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.zone.type.subtype.ZoneType;
import net.sf.l2j.gameserver.network.serverpackets.CreatureSay;
import net.sf.l2j.gameserver.skills.L2Skill;
import net.sf.l2j.gameserver.taskmanager.PvpFlagTaskManager;
import net.sf.l2j.mods.partyfarm.PartyFarm;

/**
 * A zone extending {@link ZoneType}, where summoning is forbidden. The place is
 * considered a pvp zone (no flag, no karma). It is used for pvp zones.
 */
public class PartyZone extends ZoneType {
	L2Skill noblesse = SkillTable.getInstance().getInfo(1323, 1);

	public PartyZone(int id) {
		super(id);
	}

	@Override
	protected void onEnter(Creature character) {
		if (character instanceof Player && PartyFarm.is_started()) {

			final Player player = (Player) character;


			PvpFlagTaskManager.getInstance().remove(player, true);
			noblesse.getEffects(player, player);
			player.updatePvPFlag(1);
			character.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "System", "Entered Party Zone!"));
			character.setInsideZone(ZoneId.NO_SUMMON_FRIEND, true);
			character.setInsideZone(ZoneId.PARTY, true);
			if (Config.PARTY_ONLY) {
				if (!player.isInParty() || player.getParty().getMembersCount() < Config.MIN_PARTY_MEMBERS) {

					player.teleportTo(TeleportType.TOWN);
					player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "System",
							"You can't access party zone without being in a party of at least  "
									+ Config.MIN_PARTY_MEMBERS + " members !"));
				}
			}
			if (player.getPvpKills() < Config.PVP_REQUIRED) {
				player.teleportTo(TeleportType.TOWN);

				player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "System",
						"You need at least " + Config.PVP_REQUIRED + " PvP's to enter PartyZone"));
			}
		} else {
			character.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "System",
					"Party zone not active yet! Check opening hours!"));

		}

	}

	@Override
	protected void onExit(Creature character) {
		if (character instanceof Player && PartyFarm.is_started()) {
			final Player player = (Player) character;
			PvpFlagTaskManager.getInstance().remove(player, false);
			player.updatePvPFlag(0);
			character.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "System", "Left from Party zone!"));
			character.setInsideZone(ZoneId.NO_SUMMON_FRIEND, false);
			character.setInsideZone(ZoneId.PARTY, false);

		} else {
			character.sendPacket(
					new CreatureSay(0, SayType.HERO_VOICE, "System", "Left from Party zone! See you on next opening!"));
		}
	}
}