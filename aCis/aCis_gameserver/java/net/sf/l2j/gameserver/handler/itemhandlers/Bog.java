package net.sf.l2j.gameserver.handler.itemhandlers;

import java.util.List;

import net.sf.l2j.gameserver.data.xml.SkillTreeData;
import net.sf.l2j.gameserver.handler.IItemHandler;
import net.sf.l2j.gameserver.model.actor.Playable;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.holder.skillnode.EnchantSkillNode;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.AcquireSkillDone;
import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
import net.sf.l2j.gameserver.network.serverpackets.ExEnchantSkillList;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
import net.sf.l2j.gameserver.network.serverpackets.SystemMessage;

public class Bog implements IItemHandler {
	@Override
	public void useItem(Playable playable, ItemInstance item, boolean forceUse) {
		if (!(playable instanceof Player))
			return;

		final Player player = (Player) playable;

		showEnchantSkillList(player);
	}

	public void showEnchantSkillList(Player player) {

		if (player.getClassId().getLevel() < 3) {
			final NpcHtmlMessage html = new NpcHtmlMessage(1);
			html.setHtml("<html><body> You must have 3rd class change quest completed.</body></html>");
			player.sendPacket(html);
			return;
		}

		final List<EnchantSkillNode> skills = SkillTreeData.getInstance().getEnchantSkillsFor(player);
		if (skills.isEmpty()) {
			player.sendPacket(SystemMessageId.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT);

			if (player.getStatus().getLevel() < 74)
				player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.DO_NOT_HAVE_FURTHER_SKILLS_TO_LEARN_S1)
						.addNumber(74));
			else
				player.sendPacket(SystemMessageId.NO_MORE_SKILLS_TO_LEARN);

			player.sendPacket(AcquireSkillDone.STATIC_PACKET);
		} else
			player.sendPacket(new ExEnchantSkillList(skills));

		player.sendPacket(ActionFailed.STATIC_PACKET);
	}

	public void onBypassFeedback(Player player, String command) {

		if (command.startsWith("EnchantSkillList"))
			showEnchantSkillList(player);
		else
			player.sendMessage("action failed");

	}
}
