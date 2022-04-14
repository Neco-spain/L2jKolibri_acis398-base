package net.sf.l2j.gameserver.handler.itemhandlers;


import net.sf.l2j.gameserver.data.xml.AugmentationData;
import net.sf.l2j.gameserver.enums.Paperdoll;
import net.sf.l2j.gameserver.enums.SayType;
import net.sf.l2j.gameserver.handler.IItemHandler;
import net.sf.l2j.gameserver.model.Augmentation;
import net.sf.l2j.gameserver.model.actor.Playable;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.ConfirmDlg;
import net.sf.l2j.gameserver.network.serverpackets.CreatureSay;
import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
import net.sf.l2j.gameserver.network.serverpackets.InventoryUpdate;

public class FastAugmentTopGrade implements IItemHandler {

	@Override
	public void useItem(Playable playable, ItemInstance item, boolean forceUse) {
		Player player = (Player) playable;
		ItemInstance weap = playable.getInventory().getItemFrom(Paperdoll.RHAND);
		Augmentation aug = AugmentationData.getInstance().generateRandomAugmentation(76, 3);

		if (weap == null) {
			player.sendMessage(player.getName() + " you have to equip a weapon.");

		} else if (weap.getItem().getCrystalType().getId() <= 3) {
			player.sendMessage("You can only fast augment on A and S grade  Weapons!");
//

		} else if (weap.isHeroItem()) {
			player.sendMessage("You can't add Augment on " + weap.getItemName() + " !");

		} else if (weap.isAugmented()) {

			boolean isPassive = weap.getAugmentation().getSkill().isPassive()&& !weap.getAugmentation().getSkill().isChance();
			boolean isActive = weap.getAugmentation().getSkill().isActive();
			boolean isChance = weap.getAugmentation().getSkill().isChance();
			if (weap.getAugmentation().getSkill() == null) {
				weap.getAugmentation().removeBonus(player);
				weap.removeAugmentation(true);
				InventoryUpdate iu = new InventoryUpdate();
				iu.addModifiedItem(weap);
				player.sendPacket(iu);
				player.broadcastUserInfo();
			}
			else if (isActive||isPassive ||isChance){
				sendDlg(player,playable);
			}

		} else {
			player.destroyItem("Consume", item.getObjectId(), 1, null, false);
			weap.setAugmentation(aug);

			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(weap);
			player.sendPacket(iu);
			player.broadcastUserInfo();
			if (weap.getAugmentation().getSkill() == null) {
				player.sendMessage("No luck try again!");
			} else
				checkAugment(playable);
		}
	}

	public void sendDlg(Player player , Playable playable) {
		ItemInstance weap = playable.getInventory().getItemFrom(Paperdoll.RHAND);
		String name = weap.getAugmentation().getSkill().getName();
		ConfirmDlg confirm = new ConfirmDlg(SystemMessageId.S1.getId());
		confirm.addString("Do you wish to remove " + name + "augment ?");
		confirm.addTime(5000);
		weap.removeAugmentation(true);
		player.sendPacket(confirm);
		playable.sendMessage("Removed" + name + " augmentation.");

	}
	private static void checkAugment(Playable playable) {
		Player player = (Player) playable;
		ItemInstance weap = playable.getInventory().getItemFrom(Paperdoll.RHAND);
		String name = weap.getAugmentation().getSkill().getName();
		boolean isPassive = weap.getAugmentation().getSkill().isPassive()&& !weap.getAugmentation().getSkill().isChance();
		boolean isActive = weap.getAugmentation().getSkill().isActive();
		boolean isChance = weap.getAugmentation().getSkill().isChance();
		if (isChance) {
			player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[CHANCE]", "You got " + name));
			sendMsg(player, "CHANCE : " + "You got " + name);
			player.disarmWeapon(true);
			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(weap);
			player.sendPacket(iu);
			player.broadcastUserInfo();
		}
		if (isActive) {
			player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[ACTIVE]", "You got " + name));
			sendMsg(player, "ACTIVE : " + "You got " + name);
			player.disarmWeapon(true);
			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(weap);
			player.sendPacket(iu);
			player.broadcastUserInfo();
		}
		if (isPassive) {
			player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[PASSIVE]", "You got " + name));
			sendMsg(player, "PASSIVE : " + "You got " + name);
			player.disarmWeapon(true);
			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(weap);
			player.sendPacket(iu);
			player.broadcastUserInfo();
		}
	}
	private static void sendMsg(final Player player, final String s) {
		player.sendPacket(new ExShowScreenMessage(s, 3000, ExShowScreenMessage.SMPOS.TOP_CENTER, true));
		player.sendMessage(s);
	}
}