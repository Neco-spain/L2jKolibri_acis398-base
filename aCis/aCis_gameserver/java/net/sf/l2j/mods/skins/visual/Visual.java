package net.sf.l2j.mods.skins.visual;

import net.sf.l2j.gameserver.handler.IItemHandler;
import net.sf.l2j.gameserver.model.actor.Playable;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.MagicSkillUse;

public class Visual implements IItemHandler {

	@Override
	public void useItem(Playable playable, ItemInstance item, boolean forceUse) {
		if (!(playable instanceof Player))
			return;

		Player player = (Player) playable;
		int itemId = item.getItemId();
		switch (itemId) {
		case 26101:
		case 26111:
		case 26121:
			player.broadcastPacket(new MagicSkillUse(player, 1410, 1, 10000, 10000));
			player.setVisual(player.getVisual() == 1 ? 0 : 1);
			break;
		case 26102:
		case 26112:
		case 26122:
			player.setVisual(player.getVisual() == 2 ? 0 : 2);
			break;
		case 26103:
		case 26113:
		case 26123:
			player.setVisual(player.getVisual() == 3 ? 0 : 3);
			break;
		case 26104:
		case 26114:
		case 26124:
			player.setVisual(player.getVisual() == 4 ? 0 : 4);
			break;
		case 26105:
		case 26115:
		case 26125:
			player.setVisual(player.getVisual() == 5 ? 0 : 5);
			break;
		case 23881:
			player.setVisual(player.getVisual() == 6 ? 0 : 6);
			break;
		}
	}

}
