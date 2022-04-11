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
import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
import net.sf.l2j.gameserver.network.serverpackets.InventoryUpdate;

public class FastAugmentNoGrade implements IItemHandler {

	@Override
	public void useItem(Playable playable, ItemInstance item, boolean forceUse) {
		Player player = (Player) playable;
		ItemInstance weap = playable.getInventory().getItemFrom(Paperdoll.RHAND);
		Augmentation aug = AugmentationData.getInstance().generateRandomAugmentation(76, 0);

		if (weap == null) {
			player.sendMessage(player.getName() + " you have to equip a weapon.");

		} else if (weap.getItem().getCrystalType().getId() <= 3) {
			player.sendMessage("You can only fast augment on A and S grade  Weapons!");
			return;
		} else if (weap.isHeroItem()) {
			player.sendMessage("You can't add Augment on " + weap.getItemName() + " !");

		} else if (weap.isAugmented()) {
			weap.getAugmentation().removeBonus(player);
			weap.removeAugmentation();
//			askRemove(player, forceUse);
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

			if (weap.getAugmentation().getSkill() == null) {
				player.sendMessage("No luck try again!");
			} else
				checkaugment(playable, weap);
		}
		
	}
	private static void checkaugment(Playable playable, ItemInstance item) {
		Player player = (Player) playable;
		ItemInstance weap = playable.getInventory().getItemFrom(Paperdoll.RHAND);
		String name = weap.getAugmentation().getSkill().getName();
		boolean isPassive = weap.getAugmentation().getSkill().isPassive()&& !weap.getAugmentation().getSkill().isChance();
		boolean isactive = weap.getAugmentation().getSkill().isActive();
		boolean isChance = weap.getAugmentation().getSkill().isChance();
		if (isChance == true) {
			player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[CHANCE]", "You got " + name));
			sendMsg(player, "CHANCE : " + "You got " + name);
			player.disarmWeapon(true);
			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(weap);
			player.sendPacket(iu);
			player.broadcastUserInfo();
		}
		if (isactive == true) {
			player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[ACTIVE]", "You got " + name));
			sendMsg(player, "ACTIVE : " + "You got " + name);
			player.disarmWeapon(true);
			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(weap);
			player.sendPacket(iu);
			player.broadcastUserInfo();
		}
		if (isPassive == true) {
			player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[PASSIVE]", "You got " + name));
			sendMsg(player, "PASSIVE : " + "You got " + name);
			player.disarmWeapon(true);
			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(weap);
			player.sendPacket(iu);
			player.broadcastUserInfo();
		}
		return;
	}
	private static void sendMsg(final Player player, final String s) {
		player.sendPacket(new ExShowScreenMessage(s, 3000, ExShowScreenMessage.SMPOS.TOP_CENTER, true));
		player.sendMessage(s);
	}
}