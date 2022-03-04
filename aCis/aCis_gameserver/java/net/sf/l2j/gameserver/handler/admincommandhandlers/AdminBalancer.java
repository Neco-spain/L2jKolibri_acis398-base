package net.sf.l2j.gameserver.handler.admincommandhandlers;

import net.sf.l2j.gameserver.communitybbs.balancer.ClassBalanceGui;
import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;

/**
 * Admin handler for calling Balancer system in Community Board.
 */
public class AdminBalancer implements IAdminCommandHandler
{
	private static final String[] ADMIN_COMMANDS =
	{
		"admin_balancer"
	};
	
	@Override
	public void useAdminCommand(String command, Player activeChar)
	{
		if (command.equals("admin_balancer"))
		{
			ClassBalanceGui.getInstance().parseCmd("_bbs_balancer", activeChar);
			return ;
		}
		
		return ;
	}
	
	@Override
	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}
}