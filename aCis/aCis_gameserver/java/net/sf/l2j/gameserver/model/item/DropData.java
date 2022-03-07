package net.sf.l2j.gameserver.model.item;

import java.util.Arrays;

/**
 * A container used by monster drops.<br>
 * <br>
 * The chance is exprimed as 1.000.000 to handle 4 point accuracy digits
 * (100.0000%).
 */
public class DropData {
	public static final int MAX_CHANCE = 1000000;

	private final int _itemId;
	private final int _minDrop;
	private final int _maxDrop;
	private final int _chance;
	private String _questID = null;
	private String[] _stateID = null;

	public DropData(int itemId, int minDrop, int maxDrop, int chance) {
		_itemId = itemId;
		_minDrop = minDrop;
		_maxDrop = maxDrop;
		_chance = chance;
	}

	@Override
	public String toString() {
		String out = "ItemID: " + _itemId + " Min: " + _minDrop + " Max: " + _maxDrop + " Chance: "
				+ (_chance / 10000.0) + "%";
		if (isQuestDrop()) {
			out = out + " QuestID: " + getQuestID() + " StateID's: " + Arrays.toString(getStateIDs());
		}
		return out;
		// return "DropData =[ItemID: " + _itemId + " Min: " + _minDrop + " Max: " +
		// _maxDrop + " Chance: " + (_chance / 10000.0) + "%]";
	}

	public String[] getStateIDs() {
		return this._stateID;
	}

	public boolean isQuestDrop() {
		return (this._questID != null) && (this._stateID != null);
	}

	public String getQuestID() {
		return this._questID;
	}

	/**
	 * @return the id of the dropped item.
	 */
	public int getItemId() {
		return _itemId;
	}

	/**
	 * @return the minimum quantity of dropped items.
	 */
	public int getMinDrop() {
		return _minDrop;
	}

	/**
	 * @return the maximum quantity of dropped items.
	 */
	public int getMaxDrop() {
		return _maxDrop;
	}

	/**
	 * @return the chance to have a drop, under a 1.000.000 chance.
	 */
	public int getChance() {
		return _chance;
	}
}