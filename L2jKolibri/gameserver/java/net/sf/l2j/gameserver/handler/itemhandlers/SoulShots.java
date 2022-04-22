package net.sf.l2j.gameserver.handler.itemhandlers;

import net.sf.l2j.Config;
import net.sf.l2j.commons.pool.ThreadPool;
import net.sf.l2j.commons.random.Rnd;
import net.sf.l2j.gameserver.enums.SayType;
import net.sf.l2j.gameserver.enums.items.ShotType;
import net.sf.l2j.gameserver.handler.IItemHandler;
import net.sf.l2j.gameserver.model.actor.Playable;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.holder.IntIntHolder;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.model.item.kind.Weapon;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.CreatureSay;
import net.sf.l2j.gameserver.network.serverpackets.ExAutoSoulShot;
import net.sf.l2j.gameserver.network.serverpackets.MagicSkillUse;

public class SoulShots implements IItemHandler {
	private static final int MANA_POT_CD = 2, HEALING_POT_CD = 2, // DO NOT PUT LESS THAN 10
			CP_POT_CD = 2;
	final int MANA = Config.AUTOMANA;
	final int HP = Config.AUTOHP;
	final int CP = Config.AUTOCP;

	@Override
	public void useItem(Playable playable, ItemInstance item, boolean forceUse) {
		if (!(playable instanceof Player))
			return;

		final Player player = (Player) playable;
		final ItemInstance weaponInst = player.getActiveWeaponInstance();
		final Weapon weaponItem = player.getActiveWeaponItem();
		final int itemId = item.getItemId();

		if (itemId == MANA) {

			if (player.isAutoPot(MANA)) {
				player.sendPacket(new ExAutoSoulShot(MANA, 0));
				player.sendPacket(new CreatureSay(0, SayType.PARTY, "[AUTOPOTS]", "Deactivated mana Potions"));

				player.setAutoPot(MANA, null, false);

			} else {
				if (player.getInventory().getItemByItemId(MANA) != null) {
					if (player.getInventory().getItemByItemId(MANA).getCount() > 1) {
						player.sendPacket(new ExAutoSoulShot(MANA, 1));
						player.sendPacket(new CreatureSay(0, SayType.PARTY, "[AUTOPOTS]", "Activated mana Potions"));

						player.setAutoPot(MANA,
								ThreadPool.scheduleAtFixedRate(new AutoPot(MANA, player), 1000, MANA_POT_CD * 1000),
								true);
					} else {
						MagicSkillUse msu = new MagicSkillUse(player, player, 2279, 2, 0, 100);
						player.broadcastPacket(msu);

						ItemSkills is = new ItemSkills();
						is.useItem(player, player.getInventory().getItemByItemId(MANA), true);
					}
				}
			}
			return;
		}

		if (itemId == HP)

		{
			if (player.isAutoPot(HP)) {
				player.sendPacket(new ExAutoSoulShot(HP, 0));
				player.sendPacket(new CreatureSay(0, SayType.PARTY, "[AUTOPOTS]", "Deactivated healing Potions"));

				player.setAutoPot(HP, null, false);

			} else {
				if (player.getInventory().getItemByItemId(HP) != null) {
					if (player.getInventory().getItemByItemId(HP).getCount() > 1) {
						player.sendPacket(new ExAutoSoulShot(HP, 1));

						player.sendPacket(new CreatureSay(0, SayType.PARTY, "[AUTOPOTS]", "Activated healing Potions"));
						player.setAutoPot(HP,
								ThreadPool.scheduleAtFixedRate(new AutoPot(HP, player), 1000, HEALING_POT_CD * 1000),
								true);
					} else {
						MagicSkillUse msu = new MagicSkillUse(player, player, 2037, 1, 0, 100);
						player.broadcastPacket(msu);

						ItemSkills is = new ItemSkills();
						is.useItem(player, player.getInventory().getItemByItemId(HP), true);
					}
				}
			}
			return;
		}

		if (itemId == CP) {
			if (player.isAutoPot(5592)) {
				player.sendPacket(new ExAutoSoulShot(5592, 0));
				player.sendPacket(new CreatureSay(0, SayType.PARTY, "[AUTOPOTS]", "Deactivated CP Potions"));

				player.setAutoPot(5592, null, false);

			} else {
				if (player.getInventory().getItemByItemId(5592) != null) {
					if (player.getInventory().getItemByItemId(5592).getCount() > 1) {
						player.sendPacket(new ExAutoSoulShot(5592, 1));
						player.sendPacket(new CreatureSay(0, SayType.PARTY, "[AUTOPOTS]", "Activated CP Potions"));

						player.setAutoPot(CP,
								ThreadPool.scheduleAtFixedRate(new AutoPot(5592, player), 1000, CP_POT_CD * 1000),
								true);
					} else {
						MagicSkillUse msu = new MagicSkillUse(player, player, 2166, 2, 0, 100);
						player.broadcastPacket(msu);

						ItemSkills is = new ItemSkills();
						is.useItem(player, player.getInventory().getItemByItemId(5592), true);
					}
				}
			}
			return;
		}
//		if (itemId == 728 || itemId == 1539 || itemId == 5592) {
//			switch (itemId) {
//
//			case 728: // mana potion
//			{
//				if (player.isAutoPot(728)) {
//					player.sendPacket(new ExAutoSoulShot(728, 0));
//					player.sendMessage("Deactivated auto mana potions.");
//					player.setAutoPot(728, null, false);
//
//				} else {
//					if (player.getInventory().getItemByItemId(728) != null) {
//						if (player.getInventory().getItemByItemId(728).getCount() > 1) {
//							player.sendPacket(new ExAutoSoulShot(728, 1));
//							player.sendMessage("Activated auto mana potions.");
//							player.setAutoPot(728,
//									ThreadPool.scheduleAtFixedRate(new AutoPot(728, player), 1000, MANA_POT_CD * 1000),
//									true);
//						} else {
//							MagicSkillUse msu = new MagicSkillUse(player, player, 2279, 2, 0, 100);
//							player.broadcastPacket(msu);
//
//							ItemSkills is = new ItemSkills();
//							is.useItem(player, player.getInventory().getItemByItemId(728), true);
//						}
//					}
//				}
//
//				break;
//
//			}
//			case 1539: // greater healing potion
//			{
//				if (player.isAutoPot(1539)) {
//					player.sendPacket(new ExAutoSoulShot(1539, 0));
//					player.sendMessage("Deactivated auto healing potions.");
//					player.setAutoPot(1539, null, false);
//				} else {
//					if (player.getInventory().getItemByItemId(1539) != null) {
//						if (player.getInventory().getItemByItemId(1539).getCount() > 1) {
//							player.sendPacket(new ExAutoSoulShot(1539, 1));
//							player.sendMessage("Activated auto healing potions.");
//							player.setAutoPot(1539, ThreadPool.scheduleAtFixedRate(new AutoPot(1539, player), 1000,
//									HEALING_POT_CD * 1000), true);
//						} else {
//							MagicSkillUse msu = new MagicSkillUse(player, player, 2037, 1, 0, 100);
//							player.broadcastPacket(msu);
//
//							ItemSkills is = new ItemSkills();
//							is.useItem(player, player.getInventory().getItemByItemId(1539), true);
//						}
//					}
//				}
//				break;
//			}
//
//			case 5592: // greater cp potion
//			{
//				if (player.isAutoPot(5592)) {
//					player.sendPacket(new ExAutoSoulShot(5592, 0));
//					player.sendMessage("Deactivated auto cp potions.");
//					player.setAutoPot(5592, null, false);
//				} else {
//					if (player.getInventory().getItemByItemId(5592) != null) {
//						if (player.getInventory().getItemByItemId(5592).getCount() > 1) {
//							player.sendPacket(new ExAutoSoulShot(5592, 1));
//							player.sendMessage("Activated auto cp potions.");
//							player.setAutoPot(5592,
//									ThreadPool.scheduleAtFixedRate(new AutoPot(5592, player), 1000, CP_POT_CD * 1000),
//									true);
//						} else {
//							MagicSkillUse msu = new MagicSkillUse(player, player, 2166, 2, 0, 100);
//							player.broadcastPacket(msu);
//
//							ItemSkills is = new ItemSkills();
//							is.useItem(player, player.getInventory().getItemByItemId(5592), true);
//						}
//					}
//				}
//
//				break;
//			}
//			}
//			return;
//		}
		// Check if soulshot can be used
		if (weaponInst == null || weaponItem.getSoulShotCount() == 0)

		{
			if (!player.getAutoSoulShot().contains(item.getItemId()))
				player.sendPacket(SystemMessageId.CANNOT_USE_SOULSHOTS);

			return;
		}

		if (weaponItem.getCrystalType() != item.getItem().getCrystalType()) {
			if (!player.getAutoSoulShot().contains(item.getItemId()))
				player.sendPacket(SystemMessageId.SOULSHOTS_GRADE_MISMATCH);

			return;
		}

		// Check if Soulshot are already active.
		if (player.isChargedShot(ShotType.SOULSHOT))
			return;

		// Consume Soulshots if player has enough of them.
		int ssCount = weaponItem.getSoulShotCount();
		if (weaponItem.getReducedSoulShot() > 0 && Rnd.get(100) < weaponItem.getReducedSoulShotChance())
			ssCount = weaponItem.getReducedSoulShot();

		if (!Config.INFINITY_SS && !player.destroyItemWithoutTrace(item.getObjectId(), ssCount)) {
			if (!player.disableAutoShot(itemId))
				player.sendPacket(SystemMessageId.NOT_ENOUGH_SOULSHOTS);

			return;
		}

		final IntIntHolder[] skills = item.getItem().getSkills();

		weaponInst.setChargedShot(ShotType.SOULSHOT, true);
		player.sendPacket(SystemMessageId.ENABLED_SOULSHOT);
		player.broadcastPacketInRadius(new MagicSkillUse(player, player, skills[0].getId(), 1, 0, 0), 600);
	}

	private class AutoPot implements Runnable {
		private int id;
		private Player player;

		public AutoPot(int id, Player player) {
			this.id = id;
			this.player = player;
		}

		@Override
		public void run() {
			if (player.getInventory().getItemByItemId(id) == null) {
				player.sendPacket(new ExAutoSoulShot(id, 0));
				player.setAutoPot(id, null, false);
				return;
			}

			if (id == MANA) {
				if (player.getStatus().getMp() < 0.70 * player.getStatus().getMaxMp()) {
					MagicSkillUse msu = new MagicSkillUse(player, player, 2279, 2, 0, 100);
					player.broadcastPacket(msu);

					ItemSkills is = new ItemSkills();
					is.useItem(player, player.getInventory().getItemByItemId(MANA), true);
				}

			}
			if (id == HP) {
				if (player.getStatus().getHp() < 0.95 * player.getStatus().getMaxHp()) {
					MagicSkillUse msu = new MagicSkillUse(player, player, 2037, 1, 0, 100);
					player.broadcastPacket(msu);

					ItemSkills is = new ItemSkills();
					is.useItem(player, player.getInventory().getItemByItemId(HP), true);
				}
			}
			if (id == CP) {
				if (player.getStatus().getCp() < 0.95 * player.getStatus().getMaxCp()) {
					MagicSkillUse msu = new MagicSkillUse(player, player, 2166, 2, 0, 100);
					player.broadcastPacket(msu);

					ItemSkills is = new ItemSkills();
					is.useItem(player, player.getInventory().getItemByItemId(CP), true);
				}

			}

//			switch (id) {
//			case 728: {
//				if (player.getStatus().getMp() < 0.70 * player.getStatus().getMaxMp()) {
//					MagicSkillUse msu = new MagicSkillUse(player, player, 2279, 2, 0, 100);
//					player.broadcastPacket(msu);
//
//					ItemSkills is = new ItemSkills();
//					is.useItem(player, player.getInventory().getItemByItemId(728), true);
//				}
//
//				break;
//			}
//			case 1539: {
//				if (player.getStatus().getHp() < 0.95 * player.getStatus().getMaxHp()) {
//					MagicSkillUse msu = new MagicSkillUse(player, player, 2037, 1, 0, 100);
//					player.broadcastPacket(msu);
//
//					ItemSkills is = new ItemSkills();
//					is.useItem(player, player.getInventory().getItemByItemId(1539), true);
//				}
//
//				break;
//			}
//
//			case 5592: {
//				if (player.getStatus().getCp() < 0.95 * player.getStatus().getMaxCp()) {
//					MagicSkillUse msu = new MagicSkillUse(player, player, 2166, 2, 0, 100);
//					player.broadcastPacket(msu);
//
//					ItemSkills is = new ItemSkills();
//					is.useItem(player, player.getInventory().getItemByItemId(5592), true);
//				}
//
//				break;
//			}
//			}

			if (player.getInventory().getItemByItemId(id) == null) {
				player.sendPacket(new ExAutoSoulShot(id, 0));
				player.setAutoPot(id, null, false);
			}
		}
	}
}
