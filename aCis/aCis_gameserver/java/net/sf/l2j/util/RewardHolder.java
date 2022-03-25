package net.sf.l2j.util;

/**
 * @author Rouxy
 */
public class RewardHolder {
	private int itemId;
	private int count;
	private int chance;

	public RewardHolder(int itemId, int count) {
		this.itemId = itemId;
		this.count = count;
		this.chance = 100;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	private int _id;
	private int _min;
	private int _max;
	private int _chance;

	/**
	 * @param rewardId
	 * @param rewardMin
	 * @param rewardMax
	 */
	public RewardHolder(int rewardId, int rewardMin, int rewardMax) {
		_id = rewardId;
		_min = rewardMin;
		_max = rewardMax;
		_chance = 100;
	}

	/**
	 * @param rewardId
	 * @param rewardMin
	 * @param rewardMax
	 * @param rewardChance
	 */
	public RewardHolder(int rewardId, int rewardMin, int rewardMax, int rewardChance) {
		_id = rewardId;
		_min = rewardMin;
		_max = rewardMax;
		_chance = rewardChance;
	}

	public int getRewardId() {
		return _id;
	}

	public int getRewardMin() {
		return _min;
	}

	public int getRewardMax() {
		return _max;
	}

	public int getRewardChance() {
		return _chance;
	}

	public void setId(int id) {
		_id = id;
	}

	public void setMin(int min) {
		_min = min;
	}

	public void setMax(int max) {
		_max = max;
	}

	public void setChance2(int chance) {
		_chance = chance;
	}
}
