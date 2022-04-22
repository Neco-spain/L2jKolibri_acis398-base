package net.sf.l2j.gameserver.model.zone.type;

import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.enums.SayType;
import net.sf.l2j.gameserver.enums.ZoneId;
import net.sf.l2j.gameserver.model.actor.Creature;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.zone.type.subtype.ZoneType;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.CreatureSay;
import net.sf.l2j.gameserver.skills.L2Skill;
import net.sf.l2j.gameserver.taskmanager.PvpFlagTaskManager;

/**
 * A zone extending {@link ZoneType}, where summoning is forbidden. The place is
 * considered a pvp zone (no flag, no karma). It is used for pvp zones.
 */
public class FlagZone extends ZoneType {
	L2Skill noblesse = SkillTable.getInstance().getInfo(1323, 1);

	public FlagZone(int id) {
		super(id);
	}

	@Override
	protected void onEnter(Creature character) {
		if (character instanceof Player) {
			final Player player = (Player) character;
			PvpFlagTaskManager.getInstance().remove(player, true);
			noblesse.getEffects(player, player);
			player.updatePvPFlag(1);
			((Player) character).sendPacket(SystemMessageId.ENTERED_COMBAT_ZONE);
			player.getStatus().setMaxCpHpMp();
			character.sendPacket(new CreatureSay(0, SayType.PARTYROOM_COMMANDER, "System", "Entered  PvP zone!"));
			character.setInsideZone(ZoneId.NO_SUMMON_FRIEND, true);
			character.setInsideZone(ZoneId.FLAG, true);

		}
	}

	@Override
	protected void onExit(Creature character) {
		if (character instanceof Player) {
			final Player player = (Player) character;
			PvpFlagTaskManager.getInstance().remove(player, false);
			player.updatePvPFlag(0);
			character.sendPacket(new CreatureSay(0, SayType.PARTYROOM_COMMANDER, "System", "Left from PvP zone!"));
			character.setInsideZone(ZoneId.NO_SUMMON_FRIEND, false);
			character.setInsideZone(ZoneId.FLAG, false);

			if (character instanceof Player)
				((Player) character).sendPacket(SystemMessageId.LEFT_COMBAT_ZONE);
		}
	}
}