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
import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
import net.sf.l2j.gameserver.network.serverpackets.InventoryUpdate;

/**
 * @author Kolibri
 */
public class FastAugmentation implements IItemHandler {

    @Override
    public void useItem(Playable playable, ItemInstance item, boolean forceUse) {
        Player player = (Player) playable;
        ItemInstance Weapon = playable.getInventory().getItemFrom(Paperdoll.RHAND);
        Augmentation TopLs = AugmentationData.getInstance().generateRandomAugmentation(76, 3);
        Augmentation HighLs = AugmentationData.getInstance().generateRandomAugmentation(76, 2);
        Augmentation MidLs = AugmentationData.getInstance().generateRandomAugmentation(76, 1);
        Augmentation NoGradeLs = AugmentationData.getInstance().generateRandomAugmentation(76, 0);

        if (Weapon == null) {
            player.sendMessage("You have to equip a weapon.");

        } else if (Weapon.getItem().getCrystalType().getId() <= 3) {
            player.sendMessage("Fast Augmentation available only for A and S grade  Weapons!");
        } else if (Weapon.isHeroItem()) {
            player.sendMessage("You can't add Augment on " + Weapon.getItemName() + " !");

        } else if (Weapon.isAugmented()) {
            Weapon.getAugmentation().removeBonus(player);
            Weapon.removeAugmentation(true);
            InventoryUpdate iu = new InventoryUpdate();
            iu.addModifiedItem(Weapon);
            player.sendPacket(iu);
            player.broadcastUserInfo();
        } else {
            player.destroyItem("Consume", item.getObjectId(), 1, null, false);

            if (item.getItemId() == 8762) {
                Weapon.setAugmentation(TopLs);
            } else if (item.getItemId() == 8752) {
                Weapon.setAugmentation(HighLs);
            } else if (item.getItemId() == 8742) {
                Weapon.setAugmentation(MidLs);
            } else if (item.getItemId() == 8732) {
                Weapon.setAugmentation(NoGradeLs);
            }
            InventoryUpdate iu = new InventoryUpdate();
            iu.addModifiedItem(Weapon);
            player.sendPacket(iu);
            player.broadcastUserInfo();
            if (Weapon.getAugmentation().getSkill() == null) {
                player.sendMessage("No luck try again!");
            } else
                checkAugmentResult(playable);
        }
    }

    private static void checkAugmentResult(Playable playable) {
        Player player = (Player) playable;
        ItemInstance Weapon = playable.getInventory().getItemFrom(Paperdoll.RHAND);
        InventoryUpdate iu = new InventoryUpdate();
        String name = Weapon.getAugmentation().getSkill().getName();
        boolean isChance = Weapon.getAugmentation().getSkill().isChance();
        boolean isActive = Weapon.getAugmentation().getSkill().isActive();
        boolean isPassive = Weapon.getAugmentation().getSkill().isPassive() && !isChance;
        if (isChance) {
            player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[CHANCE]", "You got " + name));
            sendMsg(player, "CHANCE : " + "You got " + name);
            player.disarmWeapon(true);
            iu.addModifiedItem(Weapon);
            player.sendPacket(iu);
            player.broadcastUserInfo();
        }
        else if (isActive) {
            player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[ACTIVE]", "You got " + name));
            sendMsg(player, "ACTIVE : " + "You got " + name);
            player.disarmWeapon(true);
            iu.addModifiedItem(Weapon);
            player.sendPacket(iu);
            player.broadcastUserInfo();
        }
        else if (isPassive) {
            player.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[PASSIVE]", "You got " + name));
            sendMsg(player, "PASSIVE : " + "You got " + name);
            player.disarmWeapon(true);
            iu.addModifiedItem(Weapon);
            player.sendPacket(iu);
            player.broadcastUserInfo();
        }
    }

    private static void sendMsg(final Player player, final String s) {
        player.sendPacket(new ExShowScreenMessage(s, 3000, ExShowScreenMessage.SMPOS.TOP_CENTER, true));
        player.sendMessage(s);
    }
}