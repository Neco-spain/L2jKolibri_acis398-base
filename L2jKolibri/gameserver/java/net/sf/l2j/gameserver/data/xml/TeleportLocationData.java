package net.sf.l2j.gameserver.data.xml;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

import net.sf.l2j.commons.data.StatSet;
import net.sf.l2j.commons.data.xml.IXmlReader;
import net.sf.l2j.gameserver.model.location.TeleportLocation;

/**
 * This class loads and stores {@link TeleportLocation}s.
 */
public class TeleportLocationData implements IXmlReader {
	private final Map<Integer, TeleportLocation> _teleports = new HashMap<>();

	protected TeleportLocationData() {
		load();
	}

	@Override
	public void load() {
		parseFile("./data/xml/teleportLocations.xml");
		LOGGER.info("Loaded {} teleport locations.", _teleports.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, "teleport", teleportNode -> {
			final StatSet set = parseAttributes(teleportNode);
			_teleports.put(set.getInteger("id"), new TeleportLocation(set));
		}));
	}

	public void reload() {
		_teleports.clear();

		load();
	}

	public TeleportLocation getTeleportLocation(int id) {
		return _teleports.get(id);
	}
	
	public static TeleportLocationData getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder {
		protected static final TeleportLocationData INSTANCE = new TeleportLocationData();
	}
}