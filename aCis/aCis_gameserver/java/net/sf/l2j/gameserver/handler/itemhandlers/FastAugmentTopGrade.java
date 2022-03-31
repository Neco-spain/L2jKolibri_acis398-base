package net.sf.l2j.gameserver.handler.itemhandlers;

//
import net.sf.l2j.gameserver.data.xml.AugmentationData;
import net.sf.l2j.gameserver.enums.Paperdoll;
import net.sf.l2j.gameserver.enums.SayType;
import net.sf.l2j.gameserver.handler.IItemHandler;
import net.sf.l2j.gameserver.model.Augmentation;
import net.sf.l2j.gameserver.model.actor.Playable;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.CreatureSay;
import net.sf.l2j.gameserver.network.serverpackets.InventoryUpdate;

public class FastAugmentTopGrade implements IItemHandler {

	@Override
	public void useItem(Playable playable, ItemInstance item, boolean forceUse) {
		Player player = (Player) playable;
		ItemInstance weap = playable.getInventory().getItemFrom(Paperdoll.RHAND);
		Augmentation aug = AugmentationData.getInstance().generateRandomAugmentation(76, 3);

		if (weap == null) {
			player.sendMessage(player.getName() + " have to equip a weapon.");

		} else if (weap.getItem().getCrystalType().getId() == 0 || weap.getItem().getCrystalType().getId() == 1
				|| weap.getItem().getCrystalType().getId() == 2) {
			player.sendMessage("You can't fast augment on " + weap.getItem().getCrystalType() + " Grade Weapons!");
			return;
		} else if (weap.isHeroItem()) {
			player.sendMessage("You can't add Augment On " + weap.getItemName() + " !");
			return;

		} else if (weap.isAugmented()) {
			weap.getAugmentation().removeBonus(player);
			weap.removeAugmentation();

			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(weap);
			player.sendPacket(iu);
			player.broadcastUserInfo();
		} else {
			player.destroyItem("Consume", item.getObjectId(), 1, null, false);
			weap.setAugmentation(aug);

			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(weap);
			player.sendPacket(iu);
			player.broadcastUserInfo();
			checkaugment(playable, weap);

		}

	}

	private void checkaugment(Playable playable, ItemInstance item) {
		Player player = (Player) playable;

		ItemInstance weap = playable.getInventory().getItemFrom(Paperdoll.RHAND);
		String name = weap.getAugmentation().getSkill().getName();
//		int level = weap.getAugmentation().getSkill().getLevel();
//		Augmentation aug = AugmentationData.getInstance().generateRandomAugmentation(76, 3);
		boolean isPassive = weap.getAugmentation().getSkill().isPassive()
				&& !weap.getAugmentation().getSkill().isChance();
		boolean isactive = weap.getAugmentation().getSkill().isActive();
		boolean isChance = weap.getAugmentation().getSkill().isChance();
//		L2Skill isnone = weap.getAugmentation().getSkill();
//		while (isnone == true) {
//		break;   + name + level
		// (((L2Skill) player.getAllAvailableSkills().getname()
//	}

		while (isChance == true) {

			player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[CHANCE]", "You got " + name));
			player.disarmWeapon(true);
			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(weap);
			player.sendPacket(iu);
			player.broadcastUserInfo();
			break;
		}

		while (isactive == true) {

			player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[ACTIVE]", "You got " + name));
			player.disarmWeapon(true);
			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(weap);
			player.sendPacket(iu);
			player.broadcastUserInfo();
			break;
		}

		while (isPassive == true) {

			player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[PASSIVE]", "You got " + name));
			player.disarmWeapon(true);
			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(weap);
			player.sendPacket(iu);
			player.broadcastUserInfo();
			break;

		}

		return;

	}

}