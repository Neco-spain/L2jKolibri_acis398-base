/*
 * This file is part of the L2J Mobius project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.l2j.gameserver.handler.voicedcommandhandlers;

import net.sf.l2j.gameserver.enums.SayType;
import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.serverpackets.CreatureSay;

public class Online implements IVoicedCommandHandler {
	private static final String[] _voicedCommands = { "online" };

	@Override

	public boolean useVoicedCommand(String command, Player activeChar, String target) {
		if (command.equals("online")) {
			activeChar.sendPacket(new CreatureSay(0, SayType.HERO_VOICE, "[SERVER]",
					"There are  " + World.getInstance().getPlayers().size() + " players online now!"));

		}
		return true;
	}

	@Override

	public String[] getVoicedCommandList()

	{

		return _voicedCommands;

	}

}
