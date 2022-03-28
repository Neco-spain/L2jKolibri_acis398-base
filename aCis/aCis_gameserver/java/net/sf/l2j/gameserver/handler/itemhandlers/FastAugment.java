package net.sf.l2j.gameserver.handler.itemhandlers;

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

public class FastAugment implements IItemHandler {

	@Override
	public void useItem(Playable playable, ItemInstance item, boolean forceUse) {
		InventoryUpdate iu = new InventoryUpdate();
		ItemInstance weap = playable.getInventory().getItemFrom(Paperdoll.RHAND);
		Augmentation aug = AugmentationData.getInstance().generateRandomAugmentation(76, 3);
		Player player = (Player) playable;
		if (weap == null) {
			player.sendMessage(player.getName() + " have to equip a weapon.");

		} else if (weap.getItem().getCrystalType().getId() == 0 || weap.getItem().getCrystalType().getId() == 1
				|| weap.getItem().getCrystalType().getId() == 2) {
			player.sendMessage("You can't fast augment on " + weap.getItem().getCrystalType() + " Grade Weapons!");
			return;
		} else if (weap.isHeroItem()) {
			player.sendMessage("You can't add Augment On " + weap.getItemName() + " !");
			return;

		}
		checkaugment(playable, weap);
		player.sendPacket(iu);
		player.broadcastUserInfo();

	}

	public void checkaugment(Playable playable, ItemInstance weap) {
		Player player = (Player) playable;
		final int level = weap.getAugmentation().getSkill().getLevel();
		final String name = weap.getAugmentation().getSkill().getName();
		Augmentation aug = AugmentationData.getInstance().generateRandomAugmentation(76, 3);
		InventoryUpdate iu = new InventoryUpdate();

		if (weap.getAugmentation().getSkill().isActive())

		{

			player.disarmWeapon(true);
			player.sendPacket(
					new CreatureSay(0, SayType.HERO_VOICE, "[ACTIVE]", "You got " + name + " level " + level));
			player.sendPacket(iu);
			player.broadcastUserInfo();

			return;
		}

		if (weap.getAugmentation().getSkill().isPassive()) {

			player.disarmWeapon(true);
			player.sendPacket(
					new CreatureSay(0, SayType.HERO_VOICE, "[PASSIVE]", "You got " + name + " level " + level));
			player.sendPacket(iu);
			player.broadcastUserInfo();

			return;
		} else if (!weap.isAugmented()) {

			weap.setAugmentation(aug);
			player.sendPacket(iu);

			playable.sendMessage("Your weapon has been augmented!");

			player.broadcastUserInfo();
		} else

		{
			weap.removeAugmentation();
			player.disarmWeapon(true);
			playable.sendMessage("Your augmented has been removed!");
			player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[NOTHING]", "You got shit  "));
			player.sendPacket(iu);
			player.broadcastUserInfo();

		}
	}
}
