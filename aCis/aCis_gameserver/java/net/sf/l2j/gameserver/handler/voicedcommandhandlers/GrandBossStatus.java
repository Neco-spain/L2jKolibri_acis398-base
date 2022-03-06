package net.sf.l2j.gameserver.handler.voicedcommandhandlers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.data.xml.NpcData;
import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.actor.template.NpcTemplate;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
import net.sf.l2j.mods.epicinfo.RaidBossInfoManager;

public class GrandBossStatus implements IVoicedCommandHandler {
	private static final String[] _voicedCommands = { "epic" };

	@Override
	public boolean useVoicedCommand(String command, Player activeChar, String target) {
		if (command.startsWith("epic")) {
			showMainPage(activeChar);
		}

		return true;
	}

	public void showMainPage(Player player) {
		List<Integer> infos = new ArrayList<>();
		infos.addAll(Config.LIST_RAID_BOSS_IDS);

		final StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<center>");
		sb.append("<body>");
		sb.append("<table><tr>");
		sb.append("<td width=32><img src=Icon.etc_alphabet_b_i00 height=32 width=32></td>");
		sb.append("<td width=32><img src=Icon.etc_alphabet_o_i00 height=32 width=32></td>");
		sb.append("<td width=32><img src=Icon.etc_alphabet_s_i00 height=32 width=32></td>");
		sb.append("<td width=32><img src=Icon.etc_alphabet_s_i00 height=32 width=32></td>");
		sb.append("</tr></table><br>");

		sb.append("<img src=\"L2UI.SquareGray\" width=300 height=1>");
		sb.append("<table bgcolor=\"000000\" width=\"318\">");
		sb.append("<tr><td><center><font color=\"FF8C00\">Grand Boss Info</font></center></td></tr>");
		sb.append("</table>");
		sb.append("<img src=\"L2UI.SquareGray\" width=300 height=1>");

		sb.append("<table bgcolor=\"000000\" width=\"318\">");

		for (int bossId : infos) {
			final NpcTemplate template = NpcData.getInstance().getTemplate(bossId);
			if (template == null)
				continue;

			String bossName = template.getName();
			if (bossName.length() > 23)
				bossName = bossName.substring(0, 23) + "...";

			final long respawnTime = RaidBossInfoManager.getInstance().getRaidBossRespawnTime(bossId);
			if (respawnTime <= System.currentTimeMillis()) {
				sb.append("<tr>");
				sb.append("<td><a action=\"" + bossId + "\">" + bossName + "</a></td>");
				sb.append("<td><font color=\"9CC300\">Alive</font></td>");
				sb.append("</tr>");
			} else {
				sb.append("<tr>");
				sb.append("<td width=\"159\" align=\"left\"><a action=\"" + bossId + "\">" + bossName + "</a></td>");
				sb.append("<td width=\"159\" align=\"left\"><font color=\"FB5858\">Dead</font> "
						+ new SimpleDateFormat(Config.RAID_BOSS_DATE_FORMAT).format(new Date(respawnTime)) + "</td>");
				sb.append("</tr>");
			}
		}
		sb.append("</table>");

		sb.append("<img src=\"L2UI.SquareGray\" width=300 height=1>");

		sb.append("<table width=\"300\" cellspacing=\"2\">");
		sb.append("<tr>");

		sb.append("</tr>");
		sb.append("</table>");

		sb.append("<img src=\"L2UI.SquareGray\" width=300 height=1>");

		sb.append("<table bgcolor=\"000000\" width=\"350\">");
		// sb.append("<tr><td><center><a action=\"bypass
		// returnboss\">Return</a></center></td></tr>");
		sb.append("</table>");
		sb.append("<img src=\"L2UI.SquareGray\" width=300 height=1>");

		sb.append("</center>");
		sb.append("</body>");
		sb.append("</html>");

		NpcHtmlMessage msg = new NpcHtmlMessage(5);
		msg.setHtml(sb.toString());
		player.sendPacket(msg);
	}

	@Override
	public String[] getVoicedCommandList() {
		return _voicedCommands;
	}
}