package net.sf.l2j.gameserver.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.GameServer;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.GrandBossStatus;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Online;

public class VoicedCommandHandler {
	private static Logger LOGGER = Logger.getLogger(GameServer.class.getName());

	private static VoicedCommandHandler _instance;

	private final Map<String, IVoicedCommandHandler> _datatable;

	public static VoicedCommandHandler getInstance() {
		if (_instance == null) {
			_instance = new VoicedCommandHandler();
		}

		return _instance;
	}

	private VoicedCommandHandler() {
		_datatable = new HashMap<>();
		// registrar comando aqui exemplo abaixo
		registerVoicedCommandHandler(new Online());
		if (Config.ENABLE_COMMAND_EPIC) {
			registerVoicedCommandHandler(new GrandBossStatus());
		}

	}

	public void registerVoicedCommandHandler(final IVoicedCommandHandler handler) {
		String[] ids = handler.getVoicedCommandList();

		for (final String id : ids) {
			_datatable.put(id, handler);
		}

		ids = null;
	}

	public IVoicedCommandHandler getVoicedCommandHandler(final String voicedCommand) {
		String command = voicedCommand;

		if (voicedCommand.indexOf(" ") != -1) {
			command = voicedCommand.substring(0, voicedCommand.indexOf(" "));
		}
		return _datatable.get(command);
	}

	/**
	 * @return
	 */
	public int size() {
		return _datatable.size();
	}
}