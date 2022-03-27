package net.sf.l2j.mods.Tournament.matches;

import net.sf.l2j.mods.Tournament.model.TournamentTeam;

/**
 * @author Rouxy
 */
public abstract class TournamentMatch
{
	public abstract boolean register(TournamentTeam team);
	
	public abstract boolean unRegister(TournamentTeam team);
	
	public abstract boolean checkConditions(TournamentTeam team);
	
}
