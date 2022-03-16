package net.sf.l2j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import net.sf.l2j.commons.config.ExProperties;
import net.sf.l2j.commons.logging.CLogger;
import net.sf.l2j.commons.math.MathUtil;
import net.sf.l2j.gameserver.enums.GeoType;
import net.sf.l2j.gameserver.model.holder.IntIntHolder;
import net.sf.l2j.loginserver.network.serverpackets.RewardHolder;

/**
 * This class contains global server configuration.<br>
 * It has static final fields initialized from configuration files.
 */
public final class Config {
	private static final CLogger LOGGER = new CLogger(Config.class.getName());

	public static final String CLANS_FILE = "./config/clans.properties";
	public static final String EVENTS_FILE = "./config/events.properties";
	public static final String GEOENGINE_FILE = "./config/geoengine.properties";
	public static final String HEXID_FILE = "./config/hexid.txt";
	public static final String LOGINSERVER_FILE = "./config/loginserver.properties";
	public static final String NPCS_FILE = "./config/npcs.properties";
	public static final String PLAYERS_FILE = "./config/players.properties";
	public static final String SERVER_FILE = "./config/server.properties";
	public static final String SIEGE_FILE = "./config/siege.properties";
	public static final String MODS_FILE = "./config/mods.properties";
	public static final String SKIN_FILE = "./config/Skin.properties";
	public static final String ENCHANT_FILE = "./config/EnchantSystem.properties";
	public static final String PARTYFARMEVENT = "./config/PartyFarmEvent.properties";
	public static final String PCBANGEVENT = "./config/PcBangEvent.properties";
	public static final String TVTEVENT = "./config/TvT.properties";
	public static final String CUSTOMQUESTS = "./config/quests.properties";

//=====================================================================================================================================================
	public static int IMPERIAL1;
	public static int IMPERIAL2;
	public static int IMPERIAL3;
	public static int IMPERIAL4;
	public static int IMPERIAL5;
	public static int IMPERIAL6;
	public static int IMPERIAL7;
	public static int IMPERIAL8;
	public static int IMPERIAL9;
	public static int IMPERIAL10;
	public static int IMPERIALSTABLE1;
	public static int IMPERIALSTABLE2;
	public static int STABLE_AMOUNT1;
	public static int STABLE_AMOUNT2;
	public static int RANDOM_AMMOUNT;
	// TvT Event Settings
	public static boolean TVT_EVENT_ENABLED;
	public static int TVT_EVENT_INTERVAL;
	public static int TVT_EVENT_PARTICIPATION_TIME;
	public static int TVT_EVENT_RUNNING_TIME;
	public static int TVT_EVENT_PARTICIPATION_NPC_ID;
	public static int TVT_EVENT_MIN_PLAYERS_IN_TEAMS;
	public static int TVT_EVENT_MAX_PLAYERS_IN_TEAMS;
	public static int TVT_EVENT_RESPAWN_TELEPORT_DELAY;
	public static int TVT_EVENT_START_LEAVE_TELEPORT_DELAY;
	public static String TVT_EVENT_TEAM_1_NAME;
	public static int[] TVT_EVENT_BACK_COORDINATES = new int[3];
	public static int[] TVT_EVENT_TEAM_1_COORDINATES = new int[3];
	public static String TVT_EVENT_TEAM_2_NAME;
	public static int[] TVT_EVENT_TEAM_2_COORDINATES = new int[3];
	public static List<int[]> TVT_EVENT_REWARDS = new ArrayList<>();
	public static boolean TVT_EVENT_TARGET_TEAM_MEMBERS_ALLOWED;
	public static boolean TVT_EVENT_POTIONS_ALLOWED;
	public static boolean TVT_EVENT_SUMMON_BY_ITEM_ALLOWED;
	public static List<Integer> TVT_EVENT_DOOR_IDS = new ArrayList<>();
	public static byte TVT_EVENT_MIN_LVL;
	public static byte TVT_EVENT_MAX_LVL;
	public static boolean TVT_EVENT_REMOVE_BUFFS;
	public static boolean TVT_EVENT_HEAL_PLAYERS;
	public static boolean TVT_KILLS_REWARD_ENABLED;
	public static List<int[]> TVT_KILLS_REWARD = new ArrayList<>();
	public static boolean ALLOW_DUALBOX_TVT;

	// PC Bang Event
	public static int PCB_MIN_LEVEL;
	public static int PCB_POINT_MIN;
	public static int PCB_POINT_MAX;
	public static int PCB_CHANCE_DUAL_POINT;
	public static int PCB_INTERVAL;
	public static int PCB_COIN_ID;
	public static boolean PCB_ENABLE;

	// Party Farm Event
	public static int EVENT_BEST_FARM_TIME;
	public static String[] EVENT_BEST_FARM_INTERVAL_BY_TIME_OF_DAY;
	public static int PARTY_FARM_MONSTER_DALAY;
	public static String PARTY_FARM_MESSAGE_TEXT;
	public static int PARTY_FARM_MESSAGE_TIME;
	public static int monsterId;
	public static int MONSTER_LOCS_COUNT;
	public static int[][] MONSTER_LOCS;
	public static boolean PARTY_MESSAGE_ENABLED;
	public static boolean ENABLE_DUALBOX_PARTYFARM;
	public static boolean PARTY_FARM_BY_TIME_OF_DAY;
	public static boolean START_PARTY;
	public static boolean PARTY_ONLY;
	public static int MIN_PARTY_MEMBERS;
	public static String PART_ZONE_MONSTERS_EVENT;
	public static List<Integer> PART_ZONE_MONSTERS_EVENT_ID;
	public static List<RewardHolder> PARTY_ZONE_REWARDS = new ArrayList<>();

	// Enchant Setting
	public static int ENCHANT_WEAPON_MAX;
	public static int ENCHANT_ARMOR_MAX;
	public static int ENCHANT_JEWELRY_MAX;
	public static int BLESSED_ENCHANT_WEAPON_MAX;
	public static int BLESSED_ENCHANT_ARMOR_MAX;
	public static int BLESSED_ENCHANT_JEWELRY_MAX;
	public static int BREAK_ENCHANT;
	public static int CRYSTAL_ENCHANT_MIN;
	public static int CRYSTAL_ENCHANT_WEAPON_MAX;
	public static int CRYSTAL_ENCHANT_ARMOR_MAX;
	public static int CRYSTAL_ENCHANT_JEWELRY_MAX;
	public static int DONATOR_ENCHANT_MIN;
	public static int DONATOR_ENCHANT_WEAPON_MAX;
	public static int DONATOR_ENCHANT_ARMOR_MAX;
	public static int DONATOR_ENCHANT_JEWELRY_MAX;
	public static boolean DONATOR_DECREASE_ENCHANT;
	public static int GOLD_WEAPON;
	public static int GOLD_ARMOR;
	public static boolean SCROLL_STACKABLE;
	public static boolean ENCHANT_HERO_WEAPON;
	public static HashMap<Integer, Integer> NORMAL_WEAPON_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> BLESS_WEAPON_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> CRYSTAL_WEAPON_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> DONATOR_WEAPON_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> NORMAL_ARMOR_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> BLESS_ARMOR_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> CRYSTAL_ARMOR_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> DONATOR_ARMOR_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> NORMAL_JEWELRY_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> BLESS_JEWELRY_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> CRYSTAL_JEWELRY_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> DONATOR_JEWELRY_ENCHANT_LEVEL = new HashMap<>();
	public static boolean DEBUG;

	// --------------------------------------------------
	// Mods settings
	// --------------------------------------------------
	public static boolean ALT_DISABLE_BOW_CLASSES;
	public static String DISABLE_BOW_CLASSES_STRING;
	public static ArrayList<Integer> DISABLE_BOW_CLASSES = new ArrayList<>();

	public static boolean ALT_DISABLE_HEAVY_CLASSES;
	public static String DISABLE_HEAVY_CLASSES_STRING;
	public static ArrayList<Integer> DISABLE_HEAVY_CLASSES = new ArrayList<>();

	public static boolean REMOVE_WEAPON;
	public static boolean REMOVE_CHEST;
	public static boolean REMOVE_LEG;
	public static boolean REMOVE_TATTOO;

	public static boolean ALT_OLY_END_ANNOUNCE;
	public static boolean ENABLE_RAIDBOSS_NOBLES;
	public static boolean ENABLE_COMMAND_EPIC;
	public static String RAID_BOSS_DATE_FORMAT;
	public static String RAID_BOSS_IDS;
	public static List<Integer> LIST_RAID_BOSS_IDS;
	public static boolean ANNOUNCE_BOSS_ALIVE;
	public static boolean ANNOUNCE_RAIDBOS_KILL;
	public static boolean ANNOUNCE_GRANDBOS_KILL;
	public static boolean INFINITY_SS;
	public static boolean INFINITY_ARROWS;
	public static boolean ALLOW_DIRECT_TP_TO_BOSS_ROOM;
	public static boolean ALT_GAME_VIEWNPC;
	/** Balancer */
	public static boolean BALANCER_ALLOW;
	/** Buffer */
	public static String FIGHTER_SET;
	public static int[] FIGHTER_SET_LIST;
	public static String MAGE_SET;
	public static int[] MAGE_SET_LIST;

	/** Custom Buff Time */
	public static boolean ENABLE_ALTERNATIVE_SKILL_DURATION;
	public static HashMap<Integer, Integer> SKILL_DURATION_LIST;

	/** Vip System */
	public static boolean ENABLE_VIP_SYSTEM;
	public static boolean ALLOW_VIP_NCOLOR;
	public static int VIP_NCOLOR;
	public static boolean ALLOW_VIP_TCOLOR;
	public static int VIP_TCOLOR;
	public static float VIP_XP_SP_RATE;
	public static float VIP_ADENA_RATE;
	public static float VIP_DROP_RATE;
	public static float VIP_BOSSDROP_RATE;

	public static float VIP_SPOIL_RATE;
	public static String MESSAGE_VIP_ENTER;
	public static int MESSAGE_TIME_VIP;
	public static String MESSAGE_VIP_EXIT;
	public static int MESSAGE_EXIT_VIP_TIME;
	public static int VIP_COIN_ID1;
	public static int VIP_DAYS_ID1;
	public static int VIP_COIN_ID2;
	public static int VIP_DAYS_ID2;
	public static int VIP_COIN_ID3;
	public static int VIP_DAYS_ID3;

	/** Skins */
	/** Variaveis Skin Click */
	public static int SEGUNDS_SKILL_ANIMATION;
	public static int SKILL_ID_SKIN1;
	public static int SKILL_ID_SKIN2;
	public static int SKILL_ID_SKIN3;
	public static int SKILL_ID_SKIN4;
	public static int SKILL_ID_SKIN5;
	public static int SKILL_ID_SKIN6;
	public static int SKILL_ID_SKIN7;
	public static int SKILL_ID_SKIN8;
	public static int SKILL_ID_SKIN9;
	public static int SKILL_ID_SKIN10;
	public static int SKILL_ID_SKIN11;
	public static int SKILL_ID_SKIN12;
	public static int SKILL_ID_SKIN13;
	public static int SKILL_ID_SKIN14;
	public static int SKILL_ID_SKIN15;
	public static boolean ALLOW_DRESS_ME_SYSTEM;
	public static Map<String, Integer> DRESS_ME_HELMET = new HashMap<>();
	public static Map<String, Integer> DRESS_ME_CHESTS = new HashMap<>();
	public static Map<String, Integer> DRESS_ME_LEGS = new HashMap<>();
	public static Map<String, Integer> DRESS_ME_BOOTS = new HashMap<>();
	public static Map<String, Integer> DRESS_ME_GLOVES = new HashMap<>();
	public static String SKIN_NAME1;
	public static String SKIN_NAME2;
	public static String SKIN_NAME3;
	public static String SKIN_NAME4;
	public static String SKIN_NAME5;
	public static String SKIN_NAME6;
	public static String SKIN_NAME7;
	public static String SKIN_NAME8;
	public static String SKIN_NAME9;
	public static String SKIN_NAME10;
	public static String SKIN_NAME11;
	public static String SKIN_NAME12;
	public static String SKIN_NAME13;
	public static String SKIN_NAME14;
	public static String SKIN_NAME15;
	public static String NAME1;
	public static String NAME2;
	public static String NAME3;
	public static String NAME4;
	public static String NAME5;
	public static String NAME6;
	public static String NAME7;
	public static String NAME8;
	public static String NAME9;
	public static String NAME10;
	public static String NAME11;
	public static String NAME12;
	public static String NAME13;
	public static String NAME14;
	public static String NAME15;

	// --------------------------------------------------
	// Clans settings
	// --------------------------------------------------

	/** Clans */
	public static int CLAN_JOIN_DAYS;
	public static int CLAN_CREATE_DAYS;
	public static int CLAN_DISSOLVE_DAYS;
	public static int ALLY_JOIN_DAYS_WHEN_LEAVED;
	public static int ALLY_JOIN_DAYS_WHEN_DISMISSED;
	public static int ACCEPT_CLAN_DAYS_WHEN_DISMISSED;
	public static int CREATE_ALLY_DAYS_WHEN_DISSOLVED;
	public static int MAX_NUM_OF_CLANS_IN_ALLY;
	public static int CLAN_MEMBERS_FOR_WAR;
	public static int CLAN_WAR_PENALTY_WHEN_ENDED;
	public static boolean MEMBERS_CAN_WITHDRAW_FROM_CLANWH;

	/** Manor */
	public static int MANOR_REFRESH_TIME;
	public static int MANOR_REFRESH_MIN;
	public static int MANOR_APPROVE_TIME;
	public static int MANOR_APPROVE_MIN;
	public static int MANOR_MAINTENANCE_MIN;
	public static int MANOR_SAVE_PERIOD_RATE;

	/** Clan Hall function */
	public static long CH_TELE_FEE_RATIO;
	public static int CH_TELE1_FEE;
	public static int CH_TELE2_FEE;
	public static long CH_ITEM_FEE_RATIO;
	public static int CH_ITEM1_FEE;
	public static int CH_ITEM2_FEE;
	public static int CH_ITEM3_FEE;
	public static long CH_MPREG_FEE_RATIO;
	public static int CH_MPREG1_FEE;
	public static int CH_MPREG2_FEE;
	public static int CH_MPREG3_FEE;
	public static int CH_MPREG4_FEE;
	public static int CH_MPREG5_FEE;
	public static long CH_HPREG_FEE_RATIO;
	public static int CH_HPREG1_FEE;
	public static int CH_HPREG2_FEE;
	public static int CH_HPREG3_FEE;
	public static int CH_HPREG4_FEE;
	public static int CH_HPREG5_FEE;
	public static int CH_HPREG6_FEE;
	public static int CH_HPREG7_FEE;
	public static int CH_HPREG8_FEE;
	public static int CH_HPREG9_FEE;
	public static int CH_HPREG10_FEE;
	public static int CH_HPREG11_FEE;
	public static int CH_HPREG12_FEE;
	public static int CH_HPREG13_FEE;
	public static long CH_EXPREG_FEE_RATIO;
	public static int CH_EXPREG1_FEE;
	public static int CH_EXPREG2_FEE;
	public static int CH_EXPREG3_FEE;
	public static int CH_EXPREG4_FEE;
	public static int CH_EXPREG5_FEE;
	public static int CH_EXPREG6_FEE;
	public static int CH_EXPREG7_FEE;
	public static long CH_SUPPORT_FEE_RATIO;
	public static int CH_SUPPORT1_FEE;
	public static int CH_SUPPORT2_FEE;
	public static int CH_SUPPORT3_FEE;
	public static int CH_SUPPORT4_FEE;
	public static int CH_SUPPORT5_FEE;
	public static int CH_SUPPORT6_FEE;
	public static int CH_SUPPORT7_FEE;
	public static int CH_SUPPORT8_FEE;
	public static long CH_CURTAIN_FEE_RATIO;
	public static int CH_CURTAIN1_FEE;
	public static int CH_CURTAIN2_FEE;
	public static long CH_FRONT_FEE_RATIO;
	public static int CH_FRONT1_FEE;
	public static int CH_FRONT2_FEE;

	// --------------------------------------------------
	// Events settings
	// --------------------------------------------------

	/** Olympiad */
	public static int OLY_START_TIME;
	public static int OLY_MIN;
	public static long OLY_CPERIOD;
	public static long OLY_BATTLE;
	public static long OLY_WPERIOD;
	public static long OLY_VPERIOD;
	public static int OLY_WAIT_TIME;
	public static int OLY_WAIT_BATTLE;
	public static int OLY_WAIT_END;
	public static int OLY_START_POINTS;
	public static int OLY_WEEKLY_POINTS;
	public static int OLY_MIN_MATCHES;
	public static int OLY_CLASSED;
	public static int OLY_NONCLASSED;
	public static IntIntHolder[] OLY_CLASSED_REWARD;
	public static IntIntHolder[] OLY_NONCLASSED_REWARD;
	public static int OLY_GP_PER_POINT;
	public static int OLY_HERO_POINTS;
	public static int OLY_RANK1_POINTS;
	public static int OLY_RANK2_POINTS;
	public static int OLY_RANK3_POINTS;
	public static int OLY_RANK4_POINTS;
	public static int OLY_RANK5_POINTS;
	public static int OLY_MAX_POINTS;
	public static int OLY_DIVIDER_CLASSED;
	public static int OLY_DIVIDER_NON_CLASSED;
	public static boolean OLY_ANNOUNCE_GAMES;
	public static int ALT_OLY_ENCHANT_LIMIT;
	public static boolean ALLOW_DUALBOX;
	public static boolean ALLOW_DUALBOX_OLY;
	public static int ALLOWED_BOXES;

	/** SevenSigns Festival */
	public static boolean SEVEN_SIGNS_BYPASS_PREREQUISITES;
	public static int FESTIVAL_MIN_PLAYER;
	public static int MAXIMUM_PLAYER_CONTRIB;
	public static long FESTIVAL_MANAGER_START;
	public static long FESTIVAL_LENGTH;
	public static long FESTIVAL_CYCLE_LENGTH;
	public static long FESTIVAL_FIRST_SPAWN;
	public static long FESTIVAL_FIRST_SWARM;
	public static long FESTIVAL_SECOND_SPAWN;
	public static long FESTIVAL_SECOND_SWARM;
	public static long FESTIVAL_CHEST_SPAWN;

	/** Four Sepulchers */
	public static int FS_TIME_ENTRY;
	public static int FS_TIME_END;
	public static int FS_PARTY_MEMBER_COUNT;

	/** dimensional rift */
	public static int RIFT_MIN_PARTY_SIZE;
	public static int RIFT_SPAWN_DELAY;
	public static int RIFT_MAX_JUMPS;
	public static int RIFT_AUTO_JUMPS_TIME_MIN;
	public static int RIFT_AUTO_JUMPS_TIME_MAX;
	public static int RIFT_ENTER_COST_RECRUIT;
	public static int RIFT_ENTER_COST_SOLDIER;
	public static int RIFT_ENTER_COST_OFFICER;
	public static int RIFT_ENTER_COST_CAPTAIN;
	public static int RIFT_ENTER_COST_COMMANDER;
	public static int RIFT_ENTER_COST_HERO;
	public static double RIFT_BOSS_ROOM_TIME_MUTIPLY;

	/** Wedding system */
	public static boolean ALLOW_WEDDING;
	public static int WEDDING_PRICE;
	public static boolean WEDDING_SAMESEX;
	public static boolean WEDDING_FORMALWEAR;

	/** Lottery */
	public static int LOTTERY_PRIZE;
	public static int LOTTERY_TICKET_PRICE;
	public static double LOTTERY_5_NUMBER_RATE;
	public static double LOTTERY_4_NUMBER_RATE;
	public static double LOTTERY_3_NUMBER_RATE;
	public static int LOTTERY_2_AND_1_NUMBER_PRIZE;

	/** Fishing tournament */
	public static boolean ALLOW_FISH_CHAMPIONSHIP;
	public static int FISH_CHAMPIONSHIP_REWARD_ITEM;
	public static int FISH_CHAMPIONSHIP_REWARD_1;
	public static int FISH_CHAMPIONSHIP_REWARD_2;
	public static int FISH_CHAMPIONSHIP_REWARD_3;
	public static int FISH_CHAMPIONSHIP_REWARD_4;
	public static int FISH_CHAMPIONSHIP_REWARD_5;

	// --------------------------------------------------
	// GeoEngine
	// --------------------------------------------------

	/** Geodata */
	public static String GEODATA_PATH;
	public static GeoType GEODATA_TYPE;

	/** Path checking */
	public static int PART_OF_CHARACTER_HEIGHT;
	public static int MAX_OBSTACLE_HEIGHT;

	/** Path finding */
	public static String PATHFIND_BUFFERS;
	public static int MOVE_WEIGHT;
	public static int MOVE_WEIGHT_DIAG;
	public static int OBSTACLE_WEIGHT;
	public static int HEURISTIC_WEIGHT;
	public static int HEURISTIC_WEIGHT_DIAG;
	public static int MAX_ITERATIONS;
	public static boolean DEBUG_GEO_NODE;

	// --------------------------------------------------
	// HexID
	// --------------------------------------------------

	public static int SERVER_ID;
	public static byte[] HEX_ID;

	// --------------------------------------------------
	// Loginserver
	// --------------------------------------------------

	public static String LOGINSERVER_HOSTNAME;
	public static int LOGINSERVER_PORT;

	public static int LOGIN_TRY_BEFORE_BAN;
	public static int LOGIN_BLOCK_AFTER_BAN;
	public static boolean ACCEPT_NEW_GAMESERVER;

	public static boolean SHOW_LICENCE;

	public static boolean AUTO_CREATE_ACCOUNTS;

	public static boolean FLOOD_PROTECTION;
	public static int FAST_CONNECTION_LIMIT;
	public static int NORMAL_CONNECTION_TIME;
	public static int FAST_CONNECTION_TIME;
	public static int MAX_CONNECTION_PER_IP;

	// --------------------------------------------------
	// NPCs / Monsters
	// --------------------------------------------------

	/** Buffer */
	public static int BUFFER_MAX_SCHEMES;
	public static int BUFFER_STATIC_BUFF_COST;

	/** Class Master */
	public static boolean ALLOW_CLASS_MASTERS;
	public static boolean ALLOW_ENTIRE_TREE;
	public static ClassMasterSettings CLASS_MASTER_SETTINGS;

	/** Misc */
	public static boolean FREE_TELEPORT;
	public static boolean ANNOUNCE_MAMMON_SPAWN;
	public static boolean MOB_AGGRO_IN_PEACEZONE;
	public static boolean SHOW_NPC_LVL;
	public static boolean SHOW_NPC_CREST;
	public static boolean SHOW_SUMMON_CREST;

	/** Wyvern Manager */
	public static boolean WYVERN_ALLOW_UPGRADER;
	public static int WYVERN_REQUIRED_LEVEL;
	public static int WYVERN_REQUIRED_CRYSTALS;

	/** Raid Boss */
	public static double RAID_HP_REGEN_MULTIPLIER;
	public static double RAID_MP_REGEN_MULTIPLIER;
	public static double RAID_DEFENCE_MULTIPLIER;
	public static int RAID_MINION_RESPAWN_TIMER;

	public static boolean RAID_DISABLE_CURSE;

	/** Grand Boss */
	public static int SPAWN_INTERVAL_AQ;
	public static int RANDOM_SPAWN_TIME_AQ;

	public static int SPAWN_INTERVAL_ANTHARAS;
	public static int RANDOM_SPAWN_TIME_ANTHARAS;
	public static int WAIT_TIME_ANTHARAS;

	public static int SPAWN_INTERVAL_BAIUM;
	public static int RANDOM_SPAWN_TIME_BAIUM;

	public static int SPAWN_INTERVAL_CORE;
	public static int RANDOM_SPAWN_TIME_CORE;

	public static int SPAWN_INTERVAL_FRINTEZZA;
	public static int RANDOM_SPAWN_TIME_FRINTEZZA;
	public static int WAIT_TIME_FRINTEZZA;

	public static int SPAWN_INTERVAL_ORFEN;
	public static int RANDOM_SPAWN_TIME_ORFEN;

	public static int SPAWN_INTERVAL_SAILREN;
	public static int RANDOM_SPAWN_TIME_SAILREN;
	public static int WAIT_TIME_SAILREN;

	public static int SPAWN_INTERVAL_VALAKAS;
	public static int RANDOM_SPAWN_TIME_VALAKAS;
	public static int WAIT_TIME_VALAKAS;

	public static int SPAWN_INTERVAL_ZAKEN;
	public static int RANDOM_SPAWN_TIME_ZAKEN;

	/** AI */
	public static boolean GUARD_ATTACK_AGGRO_MOB;
	public static int RANDOM_WALK_RATE;
	public static int MAX_DRIFT_RANGE;
	public static int MIN_NPC_ANIMATION;
	public static int MAX_NPC_ANIMATION;
	public static int MIN_MONSTER_ANIMATION;
	public static int MAX_MONSTER_ANIMATION;

	// --------------------------------------------------
	// Players
	// --------------------------------------------------

	/** Misc */
	public static boolean EFFECT_CANCELING;
	public static double HP_REGEN_MULTIPLIER;
	public static double MP_REGEN_MULTIPLIER;
	public static double CP_REGEN_MULTIPLIER;
	public static int PLAYER_SPAWN_PROTECTION;
	public static int PLAYER_FAKEDEATH_UP_PROTECTION;
	public static double RESPAWN_RESTORE_HP;
	public static int MAX_PVTSTORE_SLOTS_DWARF;
	public static int MAX_PVTSTORE_SLOTS_OTHER;
	public static boolean DEEPBLUE_DROP_RULES;
	public static boolean ALLOW_DELEVEL;
	public static int DEATH_PENALTY_CHANCE;

	/** Inventory & WH */
	public static int INVENTORY_MAXIMUM_NO_DWARF;
	public static int INVENTORY_MAXIMUM_DWARF;
	public static int INVENTORY_MAXIMUM_PET;
	public static int MAX_ITEM_IN_PACKET;
	public static double WEIGHT_LIMIT;
	public static int WAREHOUSE_SLOTS_NO_DWARF;
	public static int WAREHOUSE_SLOTS_DWARF;
	public static int WAREHOUSE_SLOTS_CLAN;
	public static int FREIGHT_SLOTS;
	public static boolean REGION_BASED_FREIGHT;
	public static int FREIGHT_PRICE;

	/** Enchant */
	public static double ENCHANT_CHANCE_WEAPON_MAGIC;
	public static double ENCHANT_CHANCE_WEAPON_MAGIC_15PLUS;
	public static double ENCHANT_CHANCE_WEAPON_NONMAGIC;
	public static double ENCHANT_CHANCE_WEAPON_NONMAGIC_15PLUS;
	public static double ENCHANT_CHANCE_ARMOR;
	public static int ENCHANT_MAX_WEAPON;
	public static int ENCHANT_MAX_ARMOR;
	public static int ENCHANT_SAFE_MAX;
	public static int ENCHANT_SAFE_MAX_FULL;

	/** Augmentations */
	public static int AUGMENTATION_NG_SKILL_CHANCE;
	public static int AUGMENTATION_NG_GLOW_CHANCE;
	public static int AUGMENTATION_MID_SKILL_CHANCE;
	public static int AUGMENTATION_MID_GLOW_CHANCE;
	public static int AUGMENTATION_HIGH_SKILL_CHANCE;
	public static int AUGMENTATION_HIGH_GLOW_CHANCE;
	public static int AUGMENTATION_TOP_SKILL_CHANCE;
	public static int AUGMENTATION_TOP_GLOW_CHANCE;
	public static int AUGMENTATION_BASESTAT_CHANCE;

	/** Karma & PvP */
	public static boolean KARMA_PLAYER_CAN_BE_KILLED_IN_PZ;
	public static boolean KARMA_PLAYER_CAN_SHOP;
	public static boolean KARMA_PLAYER_CAN_USE_GK;
	public static boolean KARMA_PLAYER_CAN_TELEPORT;
	public static boolean KARMA_PLAYER_CAN_TRADE;
	public static boolean KARMA_PLAYER_CAN_USE_WH;

	public static boolean KARMA_DROP_GM;
	public static boolean KARMA_AWARD_PK_KILL;
	public static int KARMA_PK_LIMIT;

	public static int[] KARMA_NONDROPPABLE_PET_ITEMS;
	public static int[] KARMA_NONDROPPABLE_ITEMS;

	public static int PVP_NORMAL_TIME;
	public static int PVP_PVP_TIME;

	/** Party */
	public static String PARTY_XP_CUTOFF_METHOD;
	public static int PARTY_XP_CUTOFF_LEVEL;
	public static double PARTY_XP_CUTOFF_PERCENT;
	public static int PARTY_RANGE;

	/** GMs & Admin Stuff */
	public static int DEFAULT_ACCESS_LEVEL;
	public static boolean GM_HERO_AURA;
	public static boolean GM_STARTUP_INVULNERABLE;
	public static boolean GM_STARTUP_INVISIBLE;
	public static boolean GM_STARTUP_BLOCK_ALL;
	public static boolean GM_STARTUP_AUTO_LIST;

	/** petitions */
	public static boolean PETITIONING_ALLOWED;
	public static int MAX_PETITIONS_PER_PLAYER;
	public static int MAX_PETITIONS_PENDING;

	/** Crafting **/
	public static boolean IS_CRAFTING_ENABLED;
	public static int DWARF_RECIPE_LIMIT;
	public static int COMMON_RECIPE_LIMIT;
	public static boolean BLACKSMITH_USE_RECIPES;

	/** Skills & Classes **/
	public static boolean AUTO_LEARN_SKILLS;
	public static boolean MAGIC_FAILURES;
	public static int PERFECT_SHIELD_BLOCK_RATE;
	public static boolean LIFE_CRYSTAL_NEEDED;
	public static boolean SP_BOOK_NEEDED;
	public static boolean ES_SP_BOOK_NEEDED;
	public static boolean DIVINE_SP_BOOK_NEEDED;
	public static boolean SUBCLASS_WITHOUT_QUESTS;
	public static int MAX_SUBS;
	public static int SUB_LEVEL;

	/** Buffs */
	public static boolean STORE_SKILL_COOLTIME;
	public static int MAX_BUFFS_AMOUNT;

	// --------------------------------------------------
	// Sieges
	// --------------------------------------------------

	public static int SIEGE_LENGTH;
	public static int MINIMUM_CLAN_LEVEL;
	public static int MAX_ATTACKERS_NUMBER;
	public static int MAX_DEFENDERS_NUMBER;
	public static int ATTACKERS_RESPAWN_DELAY;

	public static int CH_MINIMUM_CLAN_LEVEL;
	public static int CH_MAX_ATTACKERS_NUMBER;

	// --------------------------------------------------
	// Server
	// --------------------------------------------------

	public static String HOSTNAME;
	public static String GAMESERVER_HOSTNAME;
	public static int GAMESERVER_PORT;
	public static String GAMESERVER_LOGIN_HOSTNAME;
	public static int GAMESERVER_LOGIN_PORT;
	public static int REQUEST_ID;
	public static boolean ACCEPT_ALTERNATE_ID;
	public static boolean USE_BLOWFISH_CIPHER;

	/** Access to database */
	public static String DATABASE_URL;
	public static String DATABASE_LOGIN;
	public static String DATABASE_PASSWORD;
	public static int DATABASE_MAX_CONNECTIONS;

	/** serverList & Test */
	public static boolean SERVER_LIST_BRACKET;
	public static boolean SERVER_LIST_CLOCK;
	public static int SERVER_LIST_AGE;
	public static boolean SERVER_LIST_TESTSERVER;
	public static boolean SERVER_LIST_PVPSERVER;
	public static boolean SERVER_GMONLY;

	/** clients related */
	public static int DELETE_DAYS;
	public static int MAXIMUM_ONLINE_USERS;

	/** Auto-loot */
	public static boolean AUTO_LOOT;
	public static boolean AUTO_LOOT_HERBS;
	public static boolean AUTO_LOOT_RAID;

	/** Items Management */
	public static boolean ALLOW_DISCARDITEM;
	public static boolean MULTIPLE_ITEM_DROP;
	public static int HERB_AUTO_DESTROY_TIME;
	public static int ITEM_AUTO_DESTROY_TIME;
	public static int EQUIPABLE_ITEM_AUTO_DESTROY_TIME;
	public static Map<Integer, Integer> SPECIAL_ITEM_DESTROY_TIME;
	public static int PLAYER_DROPPED_ITEM_MULTIPLIER;

	/** Rate control */
	public static double RATE_XP;
	public static double RATE_SP;
	public static double RATE_PARTY_XP;
	public static double RATE_PARTY_SP;
	public static double RATE_DROP_ADENA;
	public static double RATE_DROP_ITEMS;
	public static double RATE_DROP_ITEMS_BY_RAID;
	public static double RATE_DROP_SPOIL;
	public static int RATE_DROP_MANOR;

	public static double RATE_QUEST_DROP;
	public static double RATE_QUEST_REWARD;
	public static double RATE_QUEST_REWARD_XP;
	public static double RATE_QUEST_REWARD_SP;
	public static double RATE_QUEST_REWARD_ADENA;

	public static double RATE_KARMA_EXP_LOST;
	public static double RATE_SIEGE_GUARDS_PRICE;

	public static int PLAYER_DROP_LIMIT;
	public static int PLAYER_RATE_DROP;
	public static int PLAYER_RATE_DROP_ITEM;
	public static int PLAYER_RATE_DROP_EQUIP;
	public static int PLAYER_RATE_DROP_EQUIP_WEAPON;

	public static int KARMA_DROP_LIMIT;
	public static int KARMA_RATE_DROP;
	public static int KARMA_RATE_DROP_ITEM;
	public static int KARMA_RATE_DROP_EQUIP;
	public static int KARMA_RATE_DROP_EQUIP_WEAPON;

	public static double PET_XP_RATE;
	public static int PET_FOOD_RATE;
	public static double SINEATER_XP_RATE;

	public static double RATE_DROP_COMMON_HERBS;
	public static double RATE_DROP_HP_HERBS;
	public static double RATE_DROP_MP_HERBS;
	public static double RATE_DROP_SPECIAL_HERBS;

	/** Allow types */
	public static boolean ALLOW_FREIGHT;
	public static boolean ALLOW_WAREHOUSE;
	public static boolean ALLOW_WEAR;
	public static int WEAR_DELAY;
	public static int WEAR_PRICE;
	public static boolean ALLOW_LOTTERY;
	public static boolean ALLOW_WATER;
	public static boolean ALLOW_BOAT;
	public static boolean ALLOW_CURSED_WEAPONS;
	public static boolean ALLOW_MANOR;
	public static boolean ENABLE_FALLING_DAMAGE;

	/** Debug & Dev */
	public static boolean NO_SPAWNS;
	public static boolean DEVELOPER;
	public static boolean PACKET_HANDLER_DEBUG;

	/** Deadlock Detector */
	public static boolean DEADLOCK_DETECTOR;
	public static int DEADLOCK_CHECK_INTERVAL;
	public static boolean RESTART_ON_DEADLOCK;

	/** Logs */
	public static boolean LOG_CHAT;
	public static boolean LOG_ITEMS;
	public static boolean GMAUDIT;

	/** Community Board */
	public static boolean ENABLE_COMMUNITY_BOARD;
	public static String BBS_DEFAULT;

	/** Flood Protectors */
	public static int ROLL_DICE_TIME;
	public static int HERO_VOICE_TIME;
	public static int SUBCLASS_TIME;
	public static int DROP_ITEM_TIME;
	public static int SERVER_BYPASS_TIME;
	public static int MULTISELL_TIME;
	public static int MANUFACTURE_TIME;
	public static int MANOR_TIME;
	public static int SENDMAIL_TIME;
	public static int CHARACTER_SELECT_TIME;
	public static int GLOBAL_CHAT_TIME;
	public static int TRADE_CHAT_TIME;
	public static int SOCIAL_TIME;
	public static int MOVE_TIME;

	/** ThreadPool */
	public static int SCHEDULED_THREAD_POOL_COUNT;
	public static int THREADS_PER_SCHEDULED_THREAD_POOL;
	public static int INSTANT_THREAD_POOL_COUNT;
	public static int THREADS_PER_INSTANT_THREAD_POOL;

	/** Misc */
	public static boolean L2WALKER_PROTECTION;
	public static boolean SERVER_NEWS;
	public static int ZONE_TOWN;

	// --------------------------------------------------
	// Those "hidden" settings haven't configs to avoid admins to fuck their server
	// You still can experiment changing values here. But don't say I didn't warn
	// you.
	// --------------------------------------------------

	/** Reserve Host on LoginServerThread */
	public static boolean RESERVE_HOST_ON_LOGIN = false; // default false

	/** MMO settings */
	public static int MMO_SELECTOR_SLEEP_TIME = 20; // default 20
	public static int MMO_MAX_SEND_PER_PASS = 80; // default 80
	public static int MMO_MAX_READ_PER_PASS = 80; // default 80
	public static int MMO_HELPER_BUFFER_COUNT = 20; // default 20

	/** Client Packets Queue settings */
	public static int CLIENT_PACKET_QUEUE_SIZE = MMO_MAX_READ_PER_PASS + 2; // default MMO_MAX_READ_PER_PASS + 2
	public static int CLIENT_PACKET_QUEUE_MAX_BURST_SIZE = MMO_MAX_READ_PER_PASS + 1; // default MMO_MAX_READ_PER_PASS +
																						// 1
	public static int CLIENT_PACKET_QUEUE_MAX_PACKETS_PER_SECOND = 160; // default 160
	public static int CLIENT_PACKET_QUEUE_MEASURE_INTERVAL = 5; // default 5
	public static int CLIENT_PACKET_QUEUE_MAX_AVERAGE_PACKETS_PER_SECOND = 80; // default 80
	public static int CLIENT_PACKET_QUEUE_MAX_FLOODS_PER_MIN = 2; // default 2
	public static int CLIENT_PACKET_QUEUE_MAX_OVERFLOWS_PER_MIN = 1; // default 1
	public static int CLIENT_PACKET_QUEUE_MAX_UNDERFLOWS_PER_MIN = 1; // default 1
	public static int CLIENT_PACKET_QUEUE_MAX_UNKNOWN_PER_MIN = 5; // default 5

	// --------------------------------------------------

	/**
	 * Initialize {@link ExProperties} from specified configuration file.
	 * 
	 * @param filename : File name to be loaded.
	 * @return ExProperties : Initialized {@link ExProperties}.
	 */
	public static final ExProperties initProperties(String filename) {
		final ExProperties result = new ExProperties();

		try {
			result.load(new File(filename));
		} catch (Exception e) {
			LOGGER.error("An error occured loading '{}' config.", e, filename);
		}

		return result;
	}

	// ================================================================================================================

	private static final void loadTvTConfig() {
		final ExProperties tvt = initProperties(TVTEVENT);
		TVT_EVENT_ENABLED = tvt.getProperty("TvTEventEnabled", false);
		TVT_EVENT_INTERVAL = tvt.getProperty("TvTEventInterval", 18000);
		TVT_EVENT_PARTICIPATION_TIME = tvt.getProperty("TvTEventParticipationTime", 3600);
		TVT_EVENT_RUNNING_TIME = tvt.getProperty("TvTEventRunningTime", 1800);
		TVT_EVENT_PARTICIPATION_NPC_ID = tvt.getProperty("TvTEventParticipationNpcId", 0);
		TVT_EVENT_REMOVE_BUFFS = tvt.getProperty("TvTEventRemoveBuffs", false);
		TVT_KILLS_REWARD_ENABLED = tvt.getProperty("TvTKillsRewardEnable", false);
		TVT_EVENT_HEAL_PLAYERS = tvt.getProperty("TvTHealPlayersEnable", false);
		ALLOW_DUALBOX_TVT = Boolean.parseBoolean(tvt.getProperty("AllowDualBoxInTvT", "False"));

		if (TVT_EVENT_PARTICIPATION_NPC_ID == 0) {
			TVT_EVENT_ENABLED = false;
			System.out.println("TvTEventEngine[Config.load()]: invalid config property -> TvTEventParticipationNpcId");
		} else {
			String[] propertySplit = tvt.getProperty("TvTEventParticipationNpcCoordinates", "0,0,0").split(",");

			if (propertySplit.length < 3) {
				TVT_EVENT_ENABLED = false;
				System.out.println(
						"TvTEventEngine[Config.load()]: invalid config property -> TvTEventParticipationNpcCoordinates");
			} else {
				TVT_EVENT_BACK_COORDINATES[0] = Integer.parseInt(propertySplit[0]);
				TVT_EVENT_BACK_COORDINATES[1] = Integer.parseInt(propertySplit[1]);
				TVT_EVENT_BACK_COORDINATES[2] = Integer.parseInt(propertySplit[2]);

				TVT_EVENT_MIN_PLAYERS_IN_TEAMS = Integer.parseInt(tvt.getProperty("TvTEventMinPlayersInTeams", "1"));
				TVT_EVENT_MAX_PLAYERS_IN_TEAMS = Integer.parseInt(tvt.getProperty("TvTEventMaxPlayersInTeams", "20"));
				TVT_EVENT_MIN_LVL = (byte) Integer.parseInt(tvt.getProperty("TvTEventMinPlayerLevel", "1"));
				TVT_EVENT_MAX_LVL = (byte) Integer.parseInt(tvt.getProperty("TvTEventMaxPlayerLevel", "80"));
				TVT_EVENT_RESPAWN_TELEPORT_DELAY = Integer
						.parseInt(tvt.getProperty("TvTEventRespawnTeleportDelay", "20"));
				TVT_EVENT_START_LEAVE_TELEPORT_DELAY = Integer
						.parseInt(tvt.getProperty("TvTEventStartLeaveTeleportDelay", "20"));

				TVT_EVENT_TEAM_1_NAME = tvt.getProperty("TvTEventTeam1Name", "Team1");
				propertySplit = tvt.getProperty("TvTEventTeam1Coordinates", "0,0,0").split(",");

				if (propertySplit.length < 3) {
					TVT_EVENT_ENABLED = false;
					System.out.println(
							"TvTEventEngine[Config.load()]: invalid config property -> TvTEventTeam1Coordinates");
				} else {
					TVT_EVENT_TEAM_1_COORDINATES[0] = Integer.parseInt(propertySplit[0]);
					TVT_EVENT_TEAM_1_COORDINATES[1] = Integer.parseInt(propertySplit[1]);
					TVT_EVENT_TEAM_1_COORDINATES[2] = Integer.parseInt(propertySplit[2]);

					TVT_EVENT_TEAM_2_NAME = tvt.getProperty("TvTEventTeam2Name", "Team2");
					propertySplit = tvt.getProperty("TvTEventTeam2Coordinates", "0,0,0").split(",");

					if (propertySplit.length < 3) {
						TVT_EVENT_ENABLED = false;
						System.out.println(
								"TvTEventEngine[Config.load()]: invalid config property -> TvTEventTeam2Coordinates");
					} else {
						TVT_EVENT_TEAM_2_COORDINATES[0] = Integer.parseInt(propertySplit[0]);
						TVT_EVENT_TEAM_2_COORDINATES[1] = Integer.parseInt(propertySplit[1]);
						TVT_EVENT_TEAM_2_COORDINATES[2] = Integer.parseInt(propertySplit[2]);
						propertySplit = tvt.getProperty("TvTEventReward", "57,100000").split(";");

						for (String reward : propertySplit) {
							String[] rewardSplit = reward.split(",");

							if (rewardSplit.length != 2)
								System.out.println(
										"TvTEventEngine[Config.load()]: invalid config property -> TvTEventReward \""
												+ reward + "\"");
							else {
								try {
									TVT_EVENT_REWARDS.add(new int[] { Integer.valueOf(rewardSplit[0]),
											Integer.valueOf(rewardSplit[1]) });
								} catch (NumberFormatException nfe) {
									if (!reward.equals(""))
										System.out.println(
												"TvTEventEngine[Config.load()]: invalid config property -> TvTEventReward \""
														+ reward + "\"");
								}
							}
						}

						propertySplit = tvt.getProperty("TvTKillsReward", "57,100000").split(";");

						for (String rewardKills : propertySplit) {
							String[] rewardSplit = rewardKills.split(",");

							if (rewardSplit.length != 2)
								System.out.println(
										"TvTEventEngine[Config.load()]: invalid config property -> TvTEventReward \""
												+ rewardKills + "\"");
							else {
								try {
									TVT_KILLS_REWARD.add(new int[] { Integer.valueOf(rewardSplit[0]),
											Integer.valueOf(rewardSplit[1]) });
								} catch (NumberFormatException nfe) {
									if (!rewardKills.equals(""))
										System.out.println(
												"TvTEventEngine[Config.load()]: invalid config property -> TvTEventReward \""
														+ rewardKills + "\"");
								}
							}
						}

						TVT_EVENT_TARGET_TEAM_MEMBERS_ALLOWED = Boolean
								.parseBoolean(tvt.getProperty("TvTEventTargetTeamMembersAllowed", "true"));
						TVT_EVENT_POTIONS_ALLOWED = Boolean
								.parseBoolean(tvt.getProperty("TvTEventPotionsAllowed", "false"));
						TVT_EVENT_SUMMON_BY_ITEM_ALLOWED = Boolean
								.parseBoolean(tvt.getProperty("TvTEventSummonByItemAllowed", "false"));
						propertySplit = tvt.getProperty("TvTEventDoorsCloseOpenOnStartEnd", "").split(";");

						for (String door : propertySplit) {
							try {
								TVT_EVENT_DOOR_IDS.add(Integer.valueOf(door));
							} catch (NumberFormatException nfe) {
								if (!door.equals(""))
									System.out.println(
											"TvTEventEngine[Config.load()]: invalid config property -> TvTEventDoorsCloseOpenOnStartEnd \""
													+ door + "\"");
							}
						}
					}
				}
			}
		}
	}
	

private static final void loadCustomQuestConfig() {
		final ExProperties quest = initProperties(CUSTOMQUESTS);
		IMPERIAL1 = quest.getProperty("Imperialreward1", 961);
		IMPERIAL2 = quest.getProperty("Imperialreward2", 961);
		IMPERIAL3 = quest.getProperty("Imperialreward3", 961);
		IMPERIAL4 = quest.getProperty("Imperialreward4", 961);
		IMPERIAL5 = quest.getProperty("Imperialreward5", 961);
		IMPERIAL6 = quest.getProperty("Imperialreward6", 961);
		IMPERIAL7 = quest.getProperty("Imperialreward7", 961);
		IMPERIAL8 = quest.getProperty("Imperialreward8", 961);
		IMPERIAL9 = quest.getProperty("Imperialreward9", 961);
		IMPERIAL10 = quest.getProperty("Imperialreward10", 961);
		IMPERIALSTABLE1 = quest.getProperty("ImperialStablereward1", 961);
		IMPERIALSTABLE2 = quest.getProperty("ImperialStablereward2", 961);
		RANDOM_AMMOUNT = quest.getProperty("RandomRewardAmount", 1);
		STABLE_AMOUNT1 = quest.getProperty("StableRewardAmount1", 1);
		STABLE_AMOUNT2 = quest.getProperty("StableRewardAmount2", 1);

		}

	private static final void loadPcBangConfig() {
		final ExProperties PcBanG = initProperties(PCBANGEVENT);
		PCB_ENABLE = Boolean.parseBoolean(PcBanG.getProperty("PcBangPointEnable", "true"));
		PCB_MIN_LEVEL = Integer.parseInt(PcBanG.getProperty("PcBangPointMinLevel", "20"));
		PCB_POINT_MIN = Integer.parseInt(PcBanG.getProperty("PcBangPointMinCount", "20"));
		PCB_POINT_MAX = Integer.parseInt(PcBanG.getProperty("PcBangPointMaxCount", "1000000"));
		PCB_COIN_ID = Integer.parseInt(PcBanG.getProperty("PCBCoinId", "0"));
		if (PCB_POINT_MAX < 1) {
			PCB_POINT_MAX = Integer.MAX_VALUE;

		}
		PCB_CHANCE_DUAL_POINT = Integer.parseInt(PcBanG.getProperty("PcBangPointDualChance", "20"));
		PCB_INTERVAL = Integer.parseInt(PcBanG.getProperty("PcBangPointTimeStamp", "900"));

	}

	private static final void loadPTFarmConfig() {
		final ExProperties BestFarm = initProperties(PARTYFARMEVENT);
		PART_ZONE_MONSTERS_EVENT = BestFarm.getProperty("PartyEventMonster");
		PART_ZONE_MONSTERS_EVENT_ID = new ArrayList<>();
		for (String id : PART_ZONE_MONSTERS_EVENT.split(","))
			PART_ZONE_MONSTERS_EVENT_ID.add(Integer.parseInt(id));
		PARTY_ZONE_REWARDS = parseReward(BestFarm, "PartyZoneReward");

		PARTY_FARM_MONSTER_DALAY = Integer.parseInt(BestFarm.getProperty("MonsterDelay", "10"));
		PARTY_FARM_BY_TIME_OF_DAY = Boolean.parseBoolean(BestFarm.getProperty("PartyFarmEventEnabled", "false"));
		START_PARTY = Boolean.parseBoolean(BestFarm.getProperty("StartSpawnPartyFarm", "false"));
		PARTY_ONLY = Boolean.parseBoolean(BestFarm.getProperty("PartyPlayersOnly", "true"));
		MIN_PARTY_MEMBERS = Integer.parseInt(BestFarm.getProperty("MinPartyMembers", "3"));

		ENABLE_DUALBOX_PARTYFARM = Boolean.parseBoolean(BestFarm.getProperty("RenewalDualBoxPTFarm", "false"));
		EVENT_BEST_FARM_TIME = Integer.parseInt(BestFarm.getProperty("EventBestFarmTime", "1"));
		EVENT_BEST_FARM_INTERVAL_BY_TIME_OF_DAY = BestFarm.getProperty("BestFarmStartTime", "20:00").split(",");
		PARTY_MESSAGE_ENABLED = Boolean.parseBoolean(BestFarm.getProperty("ScreenPartyMessageEnable", "false"));
		PARTY_FARM_MESSAGE_TEXT = BestFarm.getProperty("ScreenPartyFarmMessageText", "Welcome to l2j server!");
		PARTY_FARM_MESSAGE_TIME = Integer.parseInt(BestFarm.getProperty("ScreenPartyFarmMessageTime", "10")) * 1000;

		String[] monsterLocs2 = BestFarm.getProperty("MonsterLoc", "").split(";");
		String[] locSplit3 = null;

		monsterId = Integer.parseInt(BestFarm.getProperty("MonsterId", "1"));

		MONSTER_LOCS_COUNT = monsterLocs2.length;
		MONSTER_LOCS = new int[MONSTER_LOCS_COUNT][3];
		int g;
		for (int e = 0; e < MONSTER_LOCS_COUNT; e++) {
			locSplit3 = monsterLocs2[e].split(",");
			for (g = 0; g < 3; g++) {
				MONSTER_LOCS[e][g] = Integer.parseInt(locSplit3[g].trim());
			}
		}

	}

	public static List<RewardHolder> parseReward(Properties propertie, String configName) {
		List<RewardHolder> auxReturn = new ArrayList<>();

		String aux = propertie.getProperty(configName).trim();
		for (String randomReward : aux.split(";")) {
			final String[] infos = randomReward.split(",");

			if (infos.length > 3)
				auxReturn.add(new RewardHolder(Integer.valueOf(infos[0]), Integer.valueOf(infos[1]),
						Integer.valueOf(infos[2]), Integer.valueOf(infos[3])));
			else
				auxReturn.add(new RewardHolder(Integer.valueOf(infos[0]), Integer.valueOf(infos[1]),
						Integer.valueOf(infos[2])));
		}
		return auxReturn;
	}

	/**
	 * config enchant novo.
	 */
	private static final void loadEnchantSystemConfig() {
		final ExProperties enchant = initProperties(ENCHANT_FILE);
		DEBUG = enchant.getProperty("Debug", false);
		String[] propertySplit = enchant.getProperty("NormalWeaponEnchantLevel", "").split(";");
		for (String readData : propertySplit) {
			String[] writeData = readData.split(",");
			if (writeData.length != 2) {
				LOGGER.info("invalid config property");
			} else {
				try {
					NORMAL_WEAPON_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])),
							Integer.valueOf(Integer.parseInt(writeData[1])));
				} catch (NumberFormatException nfe) {
					if (DEBUG) {
						nfe.printStackTrace();
					}
					if (!readData.equals("")) {
						LOGGER.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("BlessWeaponEnchantLevel", "").split(";");
		for (String readData : propertySplit) {
			String[] writeData = readData.split(",");
			if (writeData.length != 2) {
				LOGGER.info("invalid config property");
			} else {
				try {
					BLESS_WEAPON_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])),
							Integer.valueOf(Integer.parseInt(writeData[1])));
				} catch (NumberFormatException nfe) {
					if (DEBUG) {
						nfe.printStackTrace();
					}
					if (!readData.equals("")) {
						LOGGER.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("CrystalWeaponEnchantLevel", "").split(";");
		for (String readData : propertySplit) {
			String[] writeData = readData.split(",");
			if (writeData.length != 2) {
				LOGGER.info("invalid config property");
			} else {
				try {
					CRYSTAL_WEAPON_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])),
							Integer.valueOf(Integer.parseInt(writeData[1])));
				} catch (NumberFormatException nfe) {
					if (DEBUG) {
						nfe.printStackTrace();
					}
					if (!readData.equals("")) {
						LOGGER.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("DonatorWeaponEnchantLevel", "").split(";");
		for (String readData : propertySplit) {
			String[] writeData = readData.split(",");
			if (writeData.length != 2) {
				System.out.println("invalid config property");
			} else {
				try {
					DONATOR_WEAPON_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])),
							Integer.valueOf(Integer.parseInt(writeData[1])));
				} catch (NumberFormatException nfe) {
					if (DEBUG) {
						nfe.printStackTrace();
					}
					if (!readData.equals("")) {
						System.out.println("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("NormalArmorEnchantLevel", "").split(";");
		for (String readData : propertySplit) {
			String[] writeData = readData.split(",");
			if (writeData.length != 2) {
				LOGGER.info("invalid config property");
			} else {
				try {
					NORMAL_ARMOR_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])),
							Integer.valueOf(Integer.parseInt(writeData[1])));
				} catch (NumberFormatException nfe) {
					if (DEBUG) {
						nfe.printStackTrace();
					}
					if (!readData.equals("")) {
						LOGGER.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("BlessArmorEnchantLevel", "").split(";");
		for (String readData : propertySplit) {
			String[] writeData = readData.split(",");
			if (writeData.length != 2) {
				LOGGER.info("invalid config property");
			} else {
				try {
					BLESS_ARMOR_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])),
							Integer.valueOf(Integer.parseInt(writeData[1])));
				} catch (NumberFormatException nfe) {
					if (DEBUG) {
						nfe.printStackTrace();
					}
					if (!readData.equals("")) {
						LOGGER.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("CrystalArmorEnchantLevel", "").split(";");
		for (String readData : propertySplit) {
			String[] writeData = readData.split(",");
			if (writeData.length != 2) {
				LOGGER.info("invalid config property");
			} else {
				try {
					CRYSTAL_ARMOR_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])),
							Integer.valueOf(Integer.parseInt(writeData[1])));
				} catch (NumberFormatException nfe) {
					if (DEBUG) {
						nfe.printStackTrace();
					}
					if (!readData.equals("")) {
						LOGGER.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("DonatorArmorEnchantLevel", "").split(";");
		for (String readData : propertySplit) {
			String[] writeData = readData.split(",");
			if (writeData.length != 2) {
				System.out.println("invalid config property");
			} else {
				try {
					DONATOR_ARMOR_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])),
							Integer.valueOf(Integer.parseInt(writeData[1])));
				} catch (NumberFormatException nfe) {
					if (DEBUG) {
						nfe.printStackTrace();
					}
					if (!readData.equals("")) {
						System.out.println("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("NormalJewelryEnchantLevel", "").split(";");
		for (String readData : propertySplit) {
			String[] writeData = readData.split(",");
			if (writeData.length != 2) {
				LOGGER.info("invalid config property");
			} else {
				try {
					NORMAL_JEWELRY_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])),
							Integer.valueOf(Integer.parseInt(writeData[1])));
				} catch (NumberFormatException nfe) {
					if (DEBUG) {
						nfe.printStackTrace();
					}
					if (!readData.equals("")) {
						LOGGER.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("BlessJewelryEnchantLevel", "").split(";");
		for (String readData : propertySplit) {
			String[] writeData = readData.split(",");
			if (writeData.length != 2) {
				LOGGER.info("invalid config property");
			} else {
				try {
					BLESS_JEWELRY_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])),
							Integer.valueOf(Integer.parseInt(writeData[1])));
				} catch (NumberFormatException nfe) {
					if (DEBUG) {
						nfe.printStackTrace();
					}
					if (!readData.equals("")) {
						LOGGER.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("CrystalJewelryEnchantLevel", "").split(";");
		for (String readData : propertySplit) {
			String[] writeData = readData.split(",");
			if (writeData.length != 2) {
				LOGGER.info("invalid config property");
			} else {
				try {
					CRYSTAL_JEWELRY_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])),
							Integer.valueOf(Integer.parseInt(writeData[1])));
				} catch (NumberFormatException nfe) {
					if (DEBUG) {
						nfe.printStackTrace();
					}
					if (!readData.equals("")) {
						LOGGER.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("DonatorJewelryEnchantLevel", "").split(";");
		for (String readData : propertySplit) {
			String[] writeData = readData.split(",");
			if (writeData.length != 2) {
				System.out.println("invalid config property");
			} else {
				try {
					DONATOR_JEWELRY_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])),
							Integer.valueOf(Integer.parseInt(writeData[1])));
				} catch (NumberFormatException nfe) {
					if (DEBUG) {
						nfe.printStackTrace();
					}
					if (!readData.equals("")) {
						System.out.println("invalid config property");
					}
				}
			}
		}
		ENCHANT_HERO_WEAPON = Boolean.parseBoolean(enchant.getProperty("EnableEnchantHeroWeapons", "False"));

		GOLD_WEAPON = Integer.parseInt(enchant.getProperty("IdEnchantDonatorWeapon", "10010"));

		GOLD_ARMOR = Integer.parseInt(enchant.getProperty("IdEnchantDonatorArmor", "10011"));

		ENCHANT_SAFE_MAX = Integer.parseInt(enchant.getProperty("EnchantSafeMax", "3"));

		ENCHANT_SAFE_MAX_FULL = Integer.parseInt(enchant.getProperty("EnchantSafeMaxFull", "4"));

		SCROLL_STACKABLE = Boolean.parseBoolean(enchant.getProperty("ScrollStackable", "False"));

		ENCHANT_WEAPON_MAX = Integer.parseInt(enchant.getProperty("EnchantWeaponMax", "25"));
		ENCHANT_ARMOR_MAX = Integer.parseInt(enchant.getProperty("EnchantArmorMax", "25"));
		ENCHANT_JEWELRY_MAX = Integer.parseInt(enchant.getProperty("EnchantJewelryMax", "25"));

		BLESSED_ENCHANT_WEAPON_MAX = Integer.parseInt(enchant.getProperty("BlessedEnchantWeaponMax", "25"));
		BLESSED_ENCHANT_ARMOR_MAX = Integer.parseInt(enchant.getProperty("BlessedEnchantArmorMax", "25"));
		BLESSED_ENCHANT_JEWELRY_MAX = Integer.parseInt(enchant.getProperty("BlessedEnchantJewelryMax", "25"));

		BREAK_ENCHANT = Integer.valueOf(enchant.getProperty("BreakEnchant", "0")).intValue();

		CRYSTAL_ENCHANT_MIN = Integer.parseInt(enchant.getProperty("CrystalEnchantMin", "20"));
		CRYSTAL_ENCHANT_WEAPON_MAX = Integer.parseInt(enchant.getProperty("CrystalEnchantWeaponMax", "25"));
		CRYSTAL_ENCHANT_ARMOR_MAX = Integer.parseInt(enchant.getProperty("CrystalEnchantArmorMax", "25"));
		CRYSTAL_ENCHANT_JEWELRY_MAX = Integer.parseInt(enchant.getProperty("CrystalEnchantJewelryMax", "25"));

		DONATOR_ENCHANT_MIN = Integer.parseInt(enchant.getProperty("DonatorEnchantMin", "20"));
		DONATOR_ENCHANT_WEAPON_MAX = Integer.parseInt(enchant.getProperty("DonatorEnchantWeaponMax", "25"));
		DONATOR_ENCHANT_ARMOR_MAX = Integer.parseInt(enchant.getProperty("DonatorEnchantArmorMax", "25"));
		DONATOR_ENCHANT_JEWELRY_MAX = Integer.parseInt(enchant.getProperty("DonatorEnchantJewelryMax", "25"));
		DONATOR_DECREASE_ENCHANT = Boolean.valueOf(enchant.getProperty("DonatorDecreaseEnchant", "false"))
				.booleanValue();

	}

	/**
	 * Loads mods settings.
	 */
	private static final void loadMods() {
		final ExProperties mods = initProperties(MODS_FILE);

		ALT_DISABLE_BOW_CLASSES = Boolean.parseBoolean(mods.getProperty("AltDisableBow", "False"));
		DISABLE_BOW_CLASSES_STRING = mods.getProperty("DisableBowForClasses", "");
		DISABLE_BOW_CLASSES = new ArrayList<>();
		for (String class_id : DISABLE_BOW_CLASSES_STRING.split(",")) {
			if (!class_id.equals(""))
				DISABLE_BOW_CLASSES.add(Integer.parseInt(class_id));
		}

		ALT_DISABLE_HEAVY_CLASSES = Boolean.parseBoolean(mods.getProperty("AltDisableHeavy", "False"));
		DISABLE_HEAVY_CLASSES_STRING = mods.getProperty("DisableHeavyForClasses", "");
		DISABLE_HEAVY_CLASSES = new ArrayList<>();
		for (String class_id : DISABLE_BOW_CLASSES_STRING.split(",")) {
			if (!class_id.equals(""))
				DISABLE_HEAVY_CLASSES.add(Integer.parseInt(class_id));
		}

		REMOVE_WEAPON = Boolean.parseBoolean(mods.getProperty("RemoveWeapon", "False"));
		REMOVE_CHEST = Boolean.parseBoolean(mods.getProperty("RemoveChest", "False"));
		REMOVE_LEG = Boolean.parseBoolean(mods.getProperty("RemoveLeg", "False"));
		REMOVE_TATTOO = Boolean.parseBoolean(mods.getProperty("RemoveLeg", "False"));

		ALLOW_DIRECT_TP_TO_BOSS_ROOM = mods.getProperty("AllowDirectTeleportToBossRoom", false);
		ALT_GAME_VIEWNPC = Boolean.parseBoolean(mods.getProperty("AltGameViewNpc", "False"));
		INFINITY_SS = mods.getProperty("InfinitySS", true);
		INFINITY_ARROWS = mods.getProperty("InfinityArrows", true);
		ALT_OLY_END_ANNOUNCE = Boolean.parseBoolean(mods.getProperty("AltOlyEndAnnounce", "False"));
		ENABLE_RAIDBOSS_NOBLES = Boolean.parseBoolean(mods.getProperty("RaidBossNobles", "false"));
		ANNOUNCE_RAIDBOS_KILL = Boolean.parseBoolean(mods.getProperty("AnnounceRaidBossKill", "false"));
		ANNOUNCE_GRANDBOS_KILL = Boolean.parseBoolean(mods.getProperty("AnnounceGranBossKill", "false"));
		ANNOUNCE_BOSS_ALIVE = Boolean.parseBoolean(mods.getProperty("AnnounceSpawnAllBoss", "false"));
		ENABLE_COMMAND_EPIC = mods.getProperty("EnableCommandEpic", false);
		RAID_BOSS_DATE_FORMAT = mods.getProperty("RaidBossDateFormat", "MMM dd, HH:mm");
		RAID_BOSS_IDS = mods.getProperty("RaidBossIds", "0,0");
		LIST_RAID_BOSS_IDS = new ArrayList<>();
		for (String val : RAID_BOSS_IDS.split(",")) {
			int npcId = Integer.parseInt(val);
			LIST_RAID_BOSS_IDS.add(npcId);
		}
		/**
		 * Balancer.
		 */

		BALANCER_ALLOW = mods.getProperty("BalancerAllow", true);

		FIGHTER_SET = mods.getProperty("FighterSet", "2375,3500,3501,3502,4422,4423,4424,4425,6648,6649,6650");
		MAGE_SET = mods.getProperty("MageSet", "2375,3500,3501,3502,4422,4423,4424,4425,6648,6649,6650");

		String[] FighterList = FIGHTER_SET.split(",");
		FIGHTER_SET_LIST = new int[FighterList.length];
		for (int i = 0; i < FighterList.length; i++)
			FIGHTER_SET_LIST[i] = Integer.parseInt(FighterList[i]);

		String[] MageList = MAGE_SET.split(",");
		MAGE_SET_LIST = new int[MageList.length];
		for (int i = 0; i < MageList.length; i++)
			MAGE_SET_LIST[i] = Integer.parseInt(MageList[i]);

		ENABLE_ALTERNATIVE_SKILL_DURATION = Boolean
				.parseBoolean(mods.getProperty("EnableAlternativeSkillDuration", "false"));
		if (ENABLE_ALTERNATIVE_SKILL_DURATION)

		{
			SKILL_DURATION_LIST = new HashMap<>();

			String[] propertySplit;

			propertySplit = mods.getProperty("SkillDurationList", "").split(";");

			for (String skill : propertySplit)

			{

				String[] skillSplit = skill.split(",");

				if (skillSplit.length != 2)

				{

					System.out.println(
							"[SkillDurationList]: invalid config property -> SkillDurationList \"" + skill + "\"");

				}

				else

				{

					try

					{

						SKILL_DURATION_LIST.put(Integer.parseInt(skillSplit[0]), Integer.parseInt(skillSplit[1]));

					}

					catch (NumberFormatException nfe)

					{

						nfe.printStackTrace();

						if (!skill.equals(""))

						{

							System.out.println("[SkillDurationList]: invalid config property -> SkillList \""
									+ skillSplit[0] + "\"" + skillSplit[1]);

						}

					}

				}

			}

		}

		ENABLE_VIP_SYSTEM = Boolean.parseBoolean(mods.getProperty("EnableVipSystem", "True"));
		ALLOW_VIP_NCOLOR = Boolean.parseBoolean(mods.getProperty("AllowVipNameColor", "True"));
		VIP_NCOLOR = Integer.decode("0x" + mods.getProperty("VipNameColor", "88AA88"));
		ALLOW_VIP_TCOLOR = Boolean.parseBoolean(mods.getProperty("AllowVipTitleColor", "True"));
		VIP_TCOLOR = Integer.decode("0x" + mods.getProperty("VipTitleColor", "88AA88"));
		VIP_XP_SP_RATE = Float.parseFloat(mods.getProperty("VIPXpSpRate", "1.5"));
		VIP_ADENA_RATE = Float.parseFloat(mods.getProperty("VIPAdenaRate", "1.5"));
		VIP_DROP_RATE = Float.parseFloat(mods.getProperty("VIPDropRate", "1.5"));
		VIP_BOSSDROP_RATE = Float.parseFloat(mods.getProperty("VIPBossDropRate", "1.5"));

		VIP_SPOIL_RATE = Float.parseFloat(mods.getProperty("VIPSpoilRate", "1.5"));
		MESSAGE_VIP_ENTER = mods.getProperty("ScreenVIPMessageText", "Forbidden to Use Enchant near the bank!");
		MESSAGE_TIME_VIP = Integer.parseInt(mods.getProperty("ScreenVIPMessageTime", "6")) * 1000;
		MESSAGE_VIP_EXIT = mods.getProperty("ScreenVIPMessageExitText", "Forbidden to Use Enchant near the bank!");
		MESSAGE_EXIT_VIP_TIME = Integer.parseInt(mods.getProperty("ScreenVIPMessageTimeExit", "6")) * 1000;
		VIP_COIN_ID1 = Integer.parseInt(mods.getProperty("VipCoin", "6392"));
		VIP_DAYS_ID1 = Integer.parseInt(mods.getProperty("VipCoinDays", "1"));
		VIP_COIN_ID2 = Integer.parseInt(mods.getProperty("VipCoin2", "6393"));
		VIP_DAYS_ID2 = Integer.parseInt(mods.getProperty("VipCoinDays2", "2"));
		VIP_COIN_ID3 = Integer.parseInt(mods.getProperty("VipCoin3", "5557"));
		VIP_DAYS_ID3 = Integer.parseInt(mods.getProperty("VipCoinDays3", "3"));

	}

	/**
	 * Loads Skin Click settings.<br>
	 */
	private static final void loadSkin() {
		final ExProperties Skins = initProperties(SKIN_FILE);
		SEGUNDS_SKILL_ANIMATION = Integer.parseInt(Skins.getProperty("SkillEffectsTime", "0"));
		SKILL_ID_SKIN1 = Integer.parseInt(Skins.getProperty("SkillIDSkin1", "0"));
		SKILL_ID_SKIN2 = Integer.parseInt(Skins.getProperty("SkillIDSkin2", "0"));
		SKILL_ID_SKIN3 = Integer.parseInt(Skins.getProperty("SkillIDSkin3", "0"));
		SKILL_ID_SKIN4 = Integer.parseInt(Skins.getProperty("SkillIDSkin4", "0"));
		SKILL_ID_SKIN5 = Integer.parseInt(Skins.getProperty("SkillIDSkin5", "0"));
		SKILL_ID_SKIN6 = Integer.parseInt(Skins.getProperty("SkillIDSkin6", "0"));
		SKILL_ID_SKIN7 = Integer.parseInt(Skins.getProperty("SkillIDSkin7", "0"));
		SKILL_ID_SKIN8 = Integer.parseInt(Skins.getProperty("SkillIDSkin8", "0"));
		SKILL_ID_SKIN9 = Integer.parseInt(Skins.getProperty("SkillIDSkin9", "0"));
		SKILL_ID_SKIN10 = Integer.parseInt(Skins.getProperty("SkillIDSkin10", "0"));
		SKILL_ID_SKIN11 = Integer.parseInt(Skins.getProperty("SkillIDSkin11", "0"));
		SKILL_ID_SKIN12 = Integer.parseInt(Skins.getProperty("SkillIDSkin12", "0"));
		SKILL_ID_SKIN13 = Integer.parseInt(Skins.getProperty("SkillIDSkin13", "0"));
		SKILL_ID_SKIN14 = Integer.parseInt(Skins.getProperty("SkillIDSkin14", "0"));
		SKILL_ID_SKIN15 = Integer.parseInt(Skins.getProperty("SkillIDSkin15", "0"));
		ALLOW_DRESS_ME_SYSTEM = Boolean.parseBoolean(Skins.getProperty("AllowDressMeSystem", "false"));
		SKIN_NAME1 = String.valueOf(Skins.getProperty("SkinName1", "SkinName"));
		SKIN_NAME2 = String.valueOf(Skins.getProperty("SkinName2", "SkinName"));
		SKIN_NAME3 = String.valueOf(Skins.getProperty("SkinName3", "SkinName"));
		SKIN_NAME4 = String.valueOf(Skins.getProperty("SkinName4", "SkinName"));
		SKIN_NAME5 = String.valueOf(Skins.getProperty("SkinName5", "SkinName"));
		SKIN_NAME6 = String.valueOf(Skins.getProperty("SkinName6", "SkinName"));
		SKIN_NAME7 = String.valueOf(Skins.getProperty("SkinName7", "SkinName"));
		SKIN_NAME8 = String.valueOf(Skins.getProperty("SkinName8", "SkinName"));
		SKIN_NAME9 = String.valueOf(Skins.getProperty("SkinName9", "SkinName"));
		SKIN_NAME10 = String.valueOf(Skins.getProperty("SkinName10", "SkinName"));
		SKIN_NAME11 = String.valueOf(Skins.getProperty("SkinName11", "SkinName"));
		SKIN_NAME12 = String.valueOf(Skins.getProperty("SkinName12", "SkinName"));
		SKIN_NAME13 = String.valueOf(Skins.getProperty("SkinName13", "SkinName"));
		SKIN_NAME14 = String.valueOf(Skins.getProperty("SkinName14", "SkinName"));
		SKIN_NAME15 = String.valueOf(Skins.getProperty("SkinName15", "SkinName"));

		String temp = Skins.getProperty("DressMeChests", "");
		String[] temp2 = temp.split(";");
		for (String s : temp2) {
			String[] t = s.split(",");
			DRESS_ME_CHESTS.put(t[0], Integer.parseInt(t[1]));
		}

		temp = Skins.getProperty("DressMeHair", "");
		temp2 = temp.split(";");
		for (String s : temp2) {
			String[] t = s.split(",");
			DRESS_ME_HELMET.put(t[0], Integer.parseInt(t[1]));
		}

		temp = Skins.getProperty("DressMeLegs", "");
		temp2 = temp.split(";");
		for (String s : temp2) {
			String[] t = s.split(",");
			DRESS_ME_LEGS.put(t[0], Integer.parseInt(t[1]));
		}
		temp = Skins.getProperty("DressMeBoots", "");
		temp2 = temp.split(";");
		for (String s : temp2) {
			String[] t = s.split(",");
			DRESS_ME_BOOTS.put(t[0], Integer.parseInt(t[1]));
		}
		temp = Skins.getProperty("DressMeGloves", "");
		temp2 = temp.split(";");
		for (String s : temp2) {
			String[] t = s.split(",");
			DRESS_ME_GLOVES.put(t[0], Integer.parseInt(t[1]));
		}

		NAME1 = Skins.getProperty("NameArmor1", " Skins Dressme");
		NAME2 = Skins.getProperty("NameArmor2", " Skins Dressme");
		NAME3 = Skins.getProperty("NameArmor3", " Skins Dressme");
		NAME4 = Skins.getProperty("NameArmor4", " Skins Dressme");
		NAME5 = Skins.getProperty("NameArmor5", " Skins Dressme");
		NAME6 = Skins.getProperty("NameArmor6", " Skins Dressme");
		NAME7 = Skins.getProperty("NameArmor7", " Skins Dressme");
		NAME8 = Skins.getProperty("NameArmor8", " Skins Dressme");
		NAME9 = Skins.getProperty("NameArmor9", " Skins Dressme");
		NAME10 = Skins.getProperty("NameArmor10", " Skins Dressme");
		NAME11 = Skins.getProperty("NameArmor11", " Skins Dressme");
		NAME12 = Skins.getProperty("NameArmor12", " Skins Dressme");
		NAME13 = Skins.getProperty("NameArmor13", " Skins Dressme");
		NAME14 = Skins.getProperty("NameArmor14", " Skins Dressme");
		NAME15 = Skins.getProperty("NameArmor15", " Skins Dressme");
	}

	/**
	 * Loads clan and clan hall settings.
	 */
	private static final void loadClans() {
		final ExProperties clans = initProperties(CLANS_FILE);

		CLAN_JOIN_DAYS = clans.getProperty("DaysBeforeJoinAClan", 5);
		CLAN_CREATE_DAYS = clans.getProperty("DaysBeforeCreateAClan", 10);
		MAX_NUM_OF_CLANS_IN_ALLY = clans.getProperty("MaxNumOfClansInAlly", 3);
		CLAN_MEMBERS_FOR_WAR = clans.getProperty("ClanMembersForWar", 15);
		CLAN_WAR_PENALTY_WHEN_ENDED = clans.getProperty("ClanWarPenaltyWhenEnded", 5);
		CLAN_DISSOLVE_DAYS = clans.getProperty("DaysToPassToDissolveAClan", 7);
		ALLY_JOIN_DAYS_WHEN_LEAVED = clans.getProperty("DaysBeforeJoinAllyWhenLeaved", 1);
		ALLY_JOIN_DAYS_WHEN_DISMISSED = clans.getProperty("DaysBeforeJoinAllyWhenDismissed", 1);
		ACCEPT_CLAN_DAYS_WHEN_DISMISSED = clans.getProperty("DaysBeforeAcceptNewClanWhenDismissed", 1);
		CREATE_ALLY_DAYS_WHEN_DISSOLVED = clans.getProperty("DaysBeforeCreateNewAllyWhenDissolved", 10);
		MEMBERS_CAN_WITHDRAW_FROM_CLANWH = clans.getProperty("MembersCanWithdrawFromClanWH", false);

		MANOR_REFRESH_TIME = clans.getProperty("ManorRefreshTime", 20);
		MANOR_REFRESH_MIN = clans.getProperty("ManorRefreshMin", 0);
		MANOR_APPROVE_TIME = clans.getProperty("ManorApproveTime", 6);
		MANOR_APPROVE_MIN = clans.getProperty("ManorApproveMin", 0);
		MANOR_MAINTENANCE_MIN = clans.getProperty("ManorMaintenanceMin", 6);
		MANOR_SAVE_PERIOD_RATE = clans.getProperty("ManorSavePeriodRate", 2) * 3600000;

		CH_TELE_FEE_RATIO = clans.getProperty("ClanHallTeleportFunctionFeeRatio", 86400000);
		CH_TELE1_FEE = clans.getProperty("ClanHallTeleportFunctionFeeLvl1", 7000);
		CH_TELE2_FEE = clans.getProperty("ClanHallTeleportFunctionFeeLvl2", 14000);
		CH_SUPPORT_FEE_RATIO = clans.getProperty("ClanHallSupportFunctionFeeRatio", 86400000);
		CH_SUPPORT1_FEE = clans.getProperty("ClanHallSupportFeeLvl1", 17500);
		CH_SUPPORT2_FEE = clans.getProperty("ClanHallSupportFeeLvl2", 35000);
		CH_SUPPORT3_FEE = clans.getProperty("ClanHallSupportFeeLvl3", 49000);
		CH_SUPPORT4_FEE = clans.getProperty("ClanHallSupportFeeLvl4", 77000);
		CH_SUPPORT5_FEE = clans.getProperty("ClanHallSupportFeeLvl5", 147000);
		CH_SUPPORT6_FEE = clans.getProperty("ClanHallSupportFeeLvl6", 252000);
		CH_SUPPORT7_FEE = clans.getProperty("ClanHallSupportFeeLvl7", 259000);
		CH_SUPPORT8_FEE = clans.getProperty("ClanHallSupportFeeLvl8", 364000);
		CH_MPREG_FEE_RATIO = clans.getProperty("ClanHallMpRegenerationFunctionFeeRatio", 86400000);
		CH_MPREG1_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl1", 14000);
		CH_MPREG2_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl2", 26250);
		CH_MPREG3_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl3", 45500);
		CH_MPREG4_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl4", 96250);
		CH_MPREG5_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl5", 140000);
		CH_HPREG_FEE_RATIO = clans.getProperty("ClanHallHpRegenerationFunctionFeeRatio", 86400000);
		CH_HPREG1_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl1", 4900);
		CH_HPREG2_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl2", 5600);
		CH_HPREG3_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl3", 7000);
		CH_HPREG4_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl4", 8166);
		CH_HPREG5_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl5", 10500);
		CH_HPREG6_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl6", 12250);
		CH_HPREG7_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl7", 14000);
		CH_HPREG8_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl8", 15750);
		CH_HPREG9_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl9", 17500);
		CH_HPREG10_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl10", 22750);
		CH_HPREG11_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl11", 26250);
		CH_HPREG12_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl12", 29750);
		CH_HPREG13_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl13", 36166);
		CH_EXPREG_FEE_RATIO = clans.getProperty("ClanHallExpRegenerationFunctionFeeRatio", 86400000);
		CH_EXPREG1_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl1", 21000);
		CH_EXPREG2_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl2", 42000);
		CH_EXPREG3_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl3", 63000);
		CH_EXPREG4_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl4", 105000);
		CH_EXPREG5_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl5", 147000);
		CH_EXPREG6_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl6", 163331);
		CH_EXPREG7_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl7", 210000);
		CH_ITEM_FEE_RATIO = clans.getProperty("ClanHallItemCreationFunctionFeeRatio", 86400000);
		CH_ITEM1_FEE = clans.getProperty("ClanHallItemCreationFunctionFeeLvl1", 210000);
		CH_ITEM2_FEE = clans.getProperty("ClanHallItemCreationFunctionFeeLvl2", 490000);
		CH_ITEM3_FEE = clans.getProperty("ClanHallItemCreationFunctionFeeLvl3", 980000);
		CH_CURTAIN_FEE_RATIO = clans.getProperty("ClanHallCurtainFunctionFeeRatio", 86400000);
		CH_CURTAIN1_FEE = clans.getProperty("ClanHallCurtainFunctionFeeLvl1", 2002);
		CH_CURTAIN2_FEE = clans.getProperty("ClanHallCurtainFunctionFeeLvl2", 2625);
		CH_FRONT_FEE_RATIO = clans.getProperty("ClanHallFrontPlatformFunctionFeeRatio", 86400000);
		CH_FRONT1_FEE = clans.getProperty("ClanHallFrontPlatformFunctionFeeLvl1", 3031);
		CH_FRONT2_FEE = clans.getProperty("ClanHallFrontPlatformFunctionFeeLvl2", 9331);
	}

	/**
	 * Loads event settings.<br>
	 * Such as olympiad, seven signs festival, four sepulchures, dimensional rift,
	 * weddings, lottery, fishing championship.
	 */
	private static final void loadEvents() {
		final ExProperties events = initProperties(EVENTS_FILE);

		OLY_START_TIME = events.getProperty("OlyStartTime", 18);
		OLY_MIN = events.getProperty("OlyMin", 0);
		OLY_CPERIOD = events.getProperty("OlyCPeriod", 21600000);
		OLY_BATTLE = events.getProperty("OlyBattle", 180000);
		OLY_WPERIOD = events.getProperty("OlyWPeriod", 604800000);
		OLY_VPERIOD = events.getProperty("OlyVPeriod", 86400000);
		OLY_WAIT_TIME = events.getProperty("OlyWaitTime", 30);
		OLY_WAIT_BATTLE = events.getProperty("OlyWaitBattle", 60);
		OLY_WAIT_END = events.getProperty("OlyWaitEnd", 40);
		OLY_START_POINTS = events.getProperty("OlyStartPoints", 18);
		OLY_WEEKLY_POINTS = events.getProperty("OlyWeeklyPoints", 3);
		OLY_MIN_MATCHES = events.getProperty("OlyMinMatchesToBeClassed", 5);
		OLY_CLASSED = events.getProperty("OlyClassedParticipants", 5);
		OLY_NONCLASSED = events.getProperty("OlyNonClassedParticipants", 9);
		OLY_CLASSED_REWARD = events.parseIntIntList("OlyClassedReward", "6651-50");
		OLY_NONCLASSED_REWARD = events.parseIntIntList("OlyNonClassedReward", "6651-30");
		OLY_GP_PER_POINT = events.getProperty("OlyGPPerPoint", 1000);
		OLY_HERO_POINTS = events.getProperty("OlyHeroPoints", 300);
		OLY_RANK1_POINTS = events.getProperty("OlyRank1Points", 100);
		OLY_RANK2_POINTS = events.getProperty("OlyRank2Points", 75);
		OLY_RANK3_POINTS = events.getProperty("OlyRank3Points", 55);
		OLY_RANK4_POINTS = events.getProperty("OlyRank4Points", 40);
		OLY_RANK5_POINTS = events.getProperty("OlyRank5Points", 30);
		OLY_MAX_POINTS = events.getProperty("OlyMaxPoints", 10);
		OLY_DIVIDER_CLASSED = events.getProperty("OlyDividerClassed", 3);
		OLY_DIVIDER_NON_CLASSED = events.getProperty("OlyDividerNonClassed", 5);
		OLY_ANNOUNCE_GAMES = events.getProperty("OlyAnnounceGames", true);
		ALT_OLY_ENCHANT_LIMIT = Integer.parseInt(events.getProperty("AltOlyEnchantLimit ", "6"));
		ALLOW_DUALBOX_OLY = Boolean.parseBoolean(events.getProperty("AllowDualBoxInOly", "True"));
		ALLOWED_BOXES = Integer.parseInt(events.getProperty("AllowedBoxes", "99"));
		ALLOW_DUALBOX = Boolean.parseBoolean(events.getProperty("AllowDualBox", "True"));

		SEVEN_SIGNS_BYPASS_PREREQUISITES = events.getProperty("SevenSignsBypassPrerequisites", false);
		FESTIVAL_MIN_PLAYER = MathUtil.limit(events.getProperty("FestivalMinPlayer", 5), 2, 9);
		MAXIMUM_PLAYER_CONTRIB = events.getProperty("MaxPlayerContrib", 1000000);
		FESTIVAL_MANAGER_START = events.getProperty("FestivalManagerStart", 120000);
		FESTIVAL_LENGTH = events.getProperty("FestivalLength", 1080000);
		FESTIVAL_CYCLE_LENGTH = events.getProperty("FestivalCycleLength", 2280000);
		FESTIVAL_FIRST_SPAWN = events.getProperty("FestivalFirstSpawn", 120000);
		FESTIVAL_FIRST_SWARM = events.getProperty("FestivalFirstSwarm", 300000);
		FESTIVAL_SECOND_SPAWN = events.getProperty("FestivalSecondSpawn", 540000);
		FESTIVAL_SECOND_SWARM = events.getProperty("FestivalSecondSwarm", 720000);
		FESTIVAL_CHEST_SPAWN = events.getProperty("FestivalChestSpawn", 900000);

		FS_TIME_ENTRY = events.getProperty("EntryTime", 55);
		FS_TIME_END = events.getProperty("EndTime", 50);
		FS_PARTY_MEMBER_COUNT = MathUtil.limit(events.getProperty("NeededPartyMembers", 4), 2, 9);

		RIFT_MIN_PARTY_SIZE = events.getProperty("RiftMinPartySize", 2);
		RIFT_MAX_JUMPS = events.getProperty("MaxRiftJumps", 4);
		RIFT_SPAWN_DELAY = events.getProperty("RiftSpawnDelay", 10000);
		RIFT_AUTO_JUMPS_TIME_MIN = events.getProperty("AutoJumpsDelayMin", 480);
		RIFT_AUTO_JUMPS_TIME_MAX = events.getProperty("AutoJumpsDelayMax", 600);
		RIFT_ENTER_COST_RECRUIT = events.getProperty("RecruitCost", 18);
		RIFT_ENTER_COST_SOLDIER = events.getProperty("SoldierCost", 21);
		RIFT_ENTER_COST_OFFICER = events.getProperty("OfficerCost", 24);
		RIFT_ENTER_COST_CAPTAIN = events.getProperty("CaptainCost", 27);
		RIFT_ENTER_COST_COMMANDER = events.getProperty("CommanderCost", 30);
		RIFT_ENTER_COST_HERO = events.getProperty("HeroCost", 33);
		RIFT_BOSS_ROOM_TIME_MUTIPLY = events.getProperty("BossRoomTimeMultiply", 1.);

		ALLOW_WEDDING = events.getProperty("AllowWedding", false);
		WEDDING_PRICE = events.getProperty("WeddingPrice", 1000000);
		WEDDING_SAMESEX = events.getProperty("WeddingAllowSameSex", false);
		WEDDING_FORMALWEAR = events.getProperty("WeddingFormalWear", true);

		LOTTERY_PRIZE = events.getProperty("LotteryPrize", 50000);
		LOTTERY_TICKET_PRICE = events.getProperty("LotteryTicketPrice", 2000);
		LOTTERY_5_NUMBER_RATE = events.getProperty("Lottery5NumberRate", 0.6);
		LOTTERY_4_NUMBER_RATE = events.getProperty("Lottery4NumberRate", 0.2);
		LOTTERY_3_NUMBER_RATE = events.getProperty("Lottery3NumberRate", 0.2);
		LOTTERY_2_AND_1_NUMBER_PRIZE = events.getProperty("Lottery2and1NumberPrize", 200);

		ALLOW_FISH_CHAMPIONSHIP = events.getProperty("AllowFishChampionship", true);
		FISH_CHAMPIONSHIP_REWARD_ITEM = events.getProperty("FishChampionshipRewardItemId", 57);
		FISH_CHAMPIONSHIP_REWARD_1 = events.getProperty("FishChampionshipReward1", 800000);
		FISH_CHAMPIONSHIP_REWARD_2 = events.getProperty("FishChampionshipReward2", 500000);
		FISH_CHAMPIONSHIP_REWARD_3 = events.getProperty("FishChampionshipReward3", 300000);
		FISH_CHAMPIONSHIP_REWARD_4 = events.getProperty("FishChampionshipReward4", 200000);
		FISH_CHAMPIONSHIP_REWARD_5 = events.getProperty("FishChampionshipReward5", 100000);
	}

	/**
	 * Loads geoengine settings.
	 */
	private static final void loadGeoengine() {
		final ExProperties geoengine = initProperties(GEOENGINE_FILE);

		GEODATA_PATH = geoengine.getProperty("GeoDataPath", "./data/geodata/");
		GEODATA_TYPE = Enum.valueOf(GeoType.class, geoengine.getProperty("GeoDataType", "L2OFF"));

		PART_OF_CHARACTER_HEIGHT = geoengine.getProperty("PartOfCharacterHeight", 75);
		MAX_OBSTACLE_HEIGHT = geoengine.getProperty("MaxObstacleHeight", 32);

		PATHFIND_BUFFERS = geoengine.getProperty("PathFindBuffers", "500x10;1000x10;3000x5;5000x3;10000x3");
		MOVE_WEIGHT = geoengine.getProperty("MoveWeight", 10);
		MOVE_WEIGHT_DIAG = geoengine.getProperty("MoveWeightDiag", 14);
		OBSTACLE_WEIGHT = geoengine.getProperty("ObstacleWeight", 30);
		HEURISTIC_WEIGHT = geoengine.getProperty("HeuristicWeight", 12);
		HEURISTIC_WEIGHT_DIAG = geoengine.getProperty("HeuristicWeightDiag", 18);
		MAX_ITERATIONS = geoengine.getProperty("MaxIterations", 3500);
		DEBUG_GEO_NODE = geoengine.getProperty("DebugGeoNode", false);
	}

	/**
	 * Loads hex ID settings.
	 */
	private static final void loadHexID() {
		final ExProperties hexid = initProperties(HEXID_FILE);

		SERVER_ID = Integer.parseInt(hexid.getProperty("ServerID"));
		HEX_ID = new BigInteger(hexid.getProperty("HexID"), 16).toByteArray();
	}

	/**
	 * Saves hex ID file.
	 * 
	 * @param serverId : The ID of server.
	 * @param hexId    : The hex ID of server.
	 */
	public static final void saveHexid(int serverId, String hexId) {
		saveHexid(serverId, hexId, HEXID_FILE);
	}

	/**
	 * Saves hexID file.
	 * 
	 * @param serverId : The ID of server.
	 * @param hexId    : The hexID of server.
	 * @param filename : The file name.
	 */
	public static final void saveHexid(int serverId, String hexId, String filename) {
		try {
			final File file = new File(filename);
			file.createNewFile();

			final Properties hexSetting = new Properties();
			hexSetting.setProperty("ServerID", String.valueOf(serverId));
			hexSetting.setProperty("HexID", hexId);

			try (OutputStream out = new FileOutputStream(file)) {
				hexSetting.store(out, "the hexID to auth into login");
			}
		} catch (Exception e) {
			LOGGER.error("Failed to save hex ID to '{}' file.", e, filename);
		}
	}

	/**
	 * Loads NPC settings.<br>
	 * Such as champion monsters, NPC buffer, class master, wyvern, raid bosses and
	 * grand bosses, AI.
	 */
	private static final void loadNpcs() {
		final ExProperties npcs = initProperties(NPCS_FILE);

		BUFFER_MAX_SCHEMES = npcs.getProperty("BufferMaxSchemesPerChar", 4);
		BUFFER_STATIC_BUFF_COST = npcs.getProperty("BufferStaticCostPerBuff", -1);

		ALLOW_CLASS_MASTERS = npcs.getProperty("AllowClassMasters", false);
		ALLOW_ENTIRE_TREE = npcs.getProperty("AllowEntireTree", false);
		if (ALLOW_CLASS_MASTERS)
			CLASS_MASTER_SETTINGS = new ClassMasterSettings(npcs.getProperty("ConfigClassMaster"));

		FREE_TELEPORT = npcs.getProperty("FreeTeleport", false);
		ANNOUNCE_MAMMON_SPAWN = npcs.getProperty("AnnounceMammonSpawn", true);
		MOB_AGGRO_IN_PEACEZONE = npcs.getProperty("MobAggroInPeaceZone", true);
		SHOW_NPC_LVL = npcs.getProperty("ShowNpcLevel", false);
		SHOW_NPC_CREST = npcs.getProperty("ShowNpcCrest", false);
		SHOW_SUMMON_CREST = npcs.getProperty("ShowSummonCrest", false);

		WYVERN_ALLOW_UPGRADER = npcs.getProperty("AllowWyvernUpgrader", true);
		WYVERN_REQUIRED_LEVEL = npcs.getProperty("RequiredStriderLevel", 55);
		WYVERN_REQUIRED_CRYSTALS = npcs.getProperty("RequiredCrystalsNumber", 10);

		RAID_HP_REGEN_MULTIPLIER = npcs.getProperty("RaidHpRegenMultiplier", 1.);
		RAID_MP_REGEN_MULTIPLIER = npcs.getProperty("RaidMpRegenMultiplier", 1.);
		RAID_DEFENCE_MULTIPLIER = npcs.getProperty("RaidDefenceMultiplier", 1.);
		RAID_MINION_RESPAWN_TIMER = npcs.getProperty("RaidMinionRespawnTime", 300000);

		RAID_DISABLE_CURSE = npcs.getProperty("DisableRaidCurse", false);

		SPAWN_INTERVAL_AQ = npcs.getProperty("AntQueenSpawnInterval", 36);
		RANDOM_SPAWN_TIME_AQ = npcs.getProperty("AntQueenRandomSpawn", 17);

		SPAWN_INTERVAL_ANTHARAS = npcs.getProperty("AntharasSpawnInterval", 264);
		RANDOM_SPAWN_TIME_ANTHARAS = npcs.getProperty("AntharasRandomSpawn", 72);
		WAIT_TIME_ANTHARAS = npcs.getProperty("AntharasWaitTime", 30) * 60000;

		SPAWN_INTERVAL_BAIUM = npcs.getProperty("BaiumSpawnInterval", 168);
		RANDOM_SPAWN_TIME_BAIUM = npcs.getProperty("BaiumRandomSpawn", 48);

		SPAWN_INTERVAL_CORE = npcs.getProperty("CoreSpawnInterval", 60);
		RANDOM_SPAWN_TIME_CORE = npcs.getProperty("CoreRandomSpawn", 23);

		SPAWN_INTERVAL_FRINTEZZA = npcs.getProperty("FrintezzaSpawnInterval", 48);
		RANDOM_SPAWN_TIME_FRINTEZZA = npcs.getProperty("FrintezzaRandomSpawn", 8);
		WAIT_TIME_FRINTEZZA = npcs.getProperty("FrintezzaWaitTime", 1) * 60000;

		SPAWN_INTERVAL_ORFEN = npcs.getProperty("OrfenSpawnInterval", 48);
		RANDOM_SPAWN_TIME_ORFEN = npcs.getProperty("OrfenRandomSpawn", 20);

		SPAWN_INTERVAL_SAILREN = npcs.getProperty("SailrenSpawnInterval", 36);
		RANDOM_SPAWN_TIME_SAILREN = npcs.getProperty("SailrenRandomSpawn", 24);
		WAIT_TIME_SAILREN = npcs.getProperty("SailrenWaitTime", 5) * 60000;

		SPAWN_INTERVAL_VALAKAS = npcs.getProperty("ValakasSpawnInterval", 264);
		RANDOM_SPAWN_TIME_VALAKAS = npcs.getProperty("ValakasRandomSpawn", 72);
		WAIT_TIME_VALAKAS = npcs.getProperty("ValakasWaitTime", 30) * 60000;

		SPAWN_INTERVAL_ZAKEN = npcs.getProperty("ZakenSpawnInterval", 60);
		RANDOM_SPAWN_TIME_ZAKEN = npcs.getProperty("ZakenRandomSpawn", 20);

		GUARD_ATTACK_AGGRO_MOB = npcs.getProperty("GuardAttackAggroMob", false);
		RANDOM_WALK_RATE = npcs.getProperty("RandomWalkRate", 30);
		MAX_DRIFT_RANGE = npcs.getProperty("MaxDriftRange", 200);
		MIN_NPC_ANIMATION = npcs.getProperty("MinNPCAnimation", 20);
		MAX_NPC_ANIMATION = npcs.getProperty("MaxNPCAnimation", 40);
		MIN_MONSTER_ANIMATION = npcs.getProperty("MinMonsterAnimation", 10);
		MAX_MONSTER_ANIMATION = npcs.getProperty("MaxMonsterAnimation", 40);
	}

	/**
	 * Loads player settings.<br>
	 * Such as stats, inventory/warehouse, enchant, augmentation, karma, party,
	 * admin, petition, skill learn.
	 */
	private static final void loadPlayers() {
		final ExProperties players = initProperties(PLAYERS_FILE);

		EFFECT_CANCELING = players.getProperty("CancelLesserEffect", true);
		HP_REGEN_MULTIPLIER = players.getProperty("HpRegenMultiplier", 1.);
		MP_REGEN_MULTIPLIER = players.getProperty("MpRegenMultiplier", 1.);
		CP_REGEN_MULTIPLIER = players.getProperty("CpRegenMultiplier", 1.);
		PLAYER_SPAWN_PROTECTION = players.getProperty("PlayerSpawnProtection", 0);
		PLAYER_FAKEDEATH_UP_PROTECTION = players.getProperty("PlayerFakeDeathUpProtection", 0);
		RESPAWN_RESTORE_HP = players.getProperty("RespawnRestoreHP", 0.7);
		MAX_PVTSTORE_SLOTS_DWARF = players.getProperty("MaxPvtStoreSlotsDwarf", 5);
		MAX_PVTSTORE_SLOTS_OTHER = players.getProperty("MaxPvtStoreSlotsOther", 4);
		DEEPBLUE_DROP_RULES = players.getProperty("UseDeepBlueDropRules", true);
		ALLOW_DELEVEL = players.getProperty("AllowDelevel", true);
		DEATH_PENALTY_CHANCE = players.getProperty("DeathPenaltyChance", 20);

		INVENTORY_MAXIMUM_NO_DWARF = players.getProperty("MaximumSlotsForNoDwarf", 80);
		INVENTORY_MAXIMUM_DWARF = players.getProperty("MaximumSlotsForDwarf", 100);
		INVENTORY_MAXIMUM_PET = players.getProperty("MaximumSlotsForPet", 12);
		MAX_ITEM_IN_PACKET = Math.max(INVENTORY_MAXIMUM_NO_DWARF, INVENTORY_MAXIMUM_DWARF);
		WEIGHT_LIMIT = players.getProperty("WeightLimit", 1.);
		WAREHOUSE_SLOTS_NO_DWARF = players.getProperty("MaximumWarehouseSlotsForNoDwarf", 100);
		WAREHOUSE_SLOTS_DWARF = players.getProperty("MaximumWarehouseSlotsForDwarf", 120);
		WAREHOUSE_SLOTS_CLAN = players.getProperty("MaximumWarehouseSlotsForClan", 150);
		FREIGHT_SLOTS = players.getProperty("MaximumFreightSlots", 20);
		REGION_BASED_FREIGHT = players.getProperty("RegionBasedFreight", true);
		FREIGHT_PRICE = players.getProperty("FreightPrice", 1000);

		AUGMENTATION_NG_SKILL_CHANCE = players.getProperty("AugmentationNGSkillChance", 15);
		AUGMENTATION_NG_GLOW_CHANCE = players.getProperty("AugmentationNGGlowChance", 0);
		AUGMENTATION_MID_SKILL_CHANCE = players.getProperty("AugmentationMidSkillChance", 30);
		AUGMENTATION_MID_GLOW_CHANCE = players.getProperty("AugmentationMidGlowChance", 40);
		AUGMENTATION_HIGH_SKILL_CHANCE = players.getProperty("AugmentationHighSkillChance", 45);
		AUGMENTATION_HIGH_GLOW_CHANCE = players.getProperty("AugmentationHighGlowChance", 70);
		AUGMENTATION_TOP_SKILL_CHANCE = players.getProperty("AugmentationTopSkillChance", 60);
		AUGMENTATION_TOP_GLOW_CHANCE = players.getProperty("AugmentationTopGlowChance", 100);
		AUGMENTATION_BASESTAT_CHANCE = players.getProperty("AugmentationBaseStatChance", 1);

		KARMA_PLAYER_CAN_BE_KILLED_IN_PZ = players.getProperty("KarmaPlayerCanBeKilledInPeaceZone", false);
		KARMA_PLAYER_CAN_SHOP = players.getProperty("KarmaPlayerCanShop", false);
		KARMA_PLAYER_CAN_USE_GK = players.getProperty("KarmaPlayerCanUseGK", false);
		KARMA_PLAYER_CAN_TELEPORT = players.getProperty("KarmaPlayerCanTeleport", true);
		KARMA_PLAYER_CAN_TRADE = players.getProperty("KarmaPlayerCanTrade", true);
		KARMA_PLAYER_CAN_USE_WH = players.getProperty("KarmaPlayerCanUseWareHouse", true);
		KARMA_DROP_GM = players.getProperty("CanGMDropEquipment", false);
		KARMA_AWARD_PK_KILL = players.getProperty("AwardPKKillPVPPoint", true);
		KARMA_PK_LIMIT = players.getProperty("MinimumPKRequiredToDrop", 5);
		KARMA_NONDROPPABLE_PET_ITEMS = players.getProperty("ListOfPetItems",
				new int[] { 2375, 3500, 3501, 3502, 4422, 4423, 4424, 4425, 6648, 6649, 6650 });
		KARMA_NONDROPPABLE_ITEMS = players.getProperty("ListOfNonDroppableItemsForPK",
				new int[] { 1147, 425, 1146, 461, 10, 2368, 7, 6, 2370, 2369 });

		PVP_NORMAL_TIME = players.getProperty("PvPVsNormalTime", 40000);
		PVP_PVP_TIME = players.getProperty("PvPVsPvPTime", 20000);

		PARTY_XP_CUTOFF_METHOD = players.getProperty("PartyXpCutoffMethod", "level");
		PARTY_XP_CUTOFF_PERCENT = players.getProperty("PartyXpCutoffPercent", 3.);
		PARTY_XP_CUTOFF_LEVEL = players.getProperty("PartyXpCutoffLevel", 20);
		PARTY_RANGE = players.getProperty("PartyRange", 1500);

		DEFAULT_ACCESS_LEVEL = players.getProperty("DefaultAccessLevel", 0);
		GM_HERO_AURA = players.getProperty("GMHeroAura", false);
		GM_STARTUP_INVULNERABLE = players.getProperty("GMStartupInvulnerable", false);
		GM_STARTUP_INVISIBLE = players.getProperty("GMStartupInvisible", false);
		GM_STARTUP_BLOCK_ALL = players.getProperty("GMStartupBlockAll", false);
		GM_STARTUP_AUTO_LIST = players.getProperty("GMStartupAutoList", true);

		PETITIONING_ALLOWED = players.getProperty("PetitioningAllowed", true);
		MAX_PETITIONS_PER_PLAYER = players.getProperty("MaxPetitionsPerPlayer", 5);
		MAX_PETITIONS_PENDING = players.getProperty("MaxPetitionsPending", 25);

		IS_CRAFTING_ENABLED = players.getProperty("CraftingEnabled", true);
		DWARF_RECIPE_LIMIT = players.getProperty("DwarfRecipeLimit", 50);
		COMMON_RECIPE_LIMIT = players.getProperty("CommonRecipeLimit", 50);
		BLACKSMITH_USE_RECIPES = players.getProperty("BlacksmithUseRecipes", true);

		AUTO_LEARN_SKILLS = players.getProperty("AutoLearnSkills", false);
		MAGIC_FAILURES = players.getProperty("MagicFailures", true);
		PERFECT_SHIELD_BLOCK_RATE = players.getProperty("PerfectShieldBlockRate", 5);
		LIFE_CRYSTAL_NEEDED = players.getProperty("LifeCrystalNeeded", true);
		SP_BOOK_NEEDED = players.getProperty("SpBookNeeded", true);
		ES_SP_BOOK_NEEDED = players.getProperty("EnchantSkillSpBookNeeded", true);
		DIVINE_SP_BOOK_NEEDED = players.getProperty("DivineInspirationSpBookNeeded", true);
		SUBCLASS_WITHOUT_QUESTS = players.getProperty("SubClassWithoutQuests", false);
		MAX_SUBS = players.getProperty("MaxSubs", 5);
		SUB_LEVEL = players.getProperty("SubLevel", 76);

		MAX_BUFFS_AMOUNT = players.getProperty("MaxBuffsAmount", 20);
		STORE_SKILL_COOLTIME = players.getProperty("StoreSkillCooltime", true);
	}

	/**
	 * Loads siege settings.
	 */
	private static final void loadSieges() {
		final ExProperties sieges = initProperties(Config.SIEGE_FILE);

		SIEGE_LENGTH = sieges.getProperty("SiegeLength", 120);
		MINIMUM_CLAN_LEVEL = sieges.getProperty("SiegeClanMinLevel", 4);
		MAX_ATTACKERS_NUMBER = sieges.getProperty("AttackerMaxClans", 10);
		MAX_DEFENDERS_NUMBER = sieges.getProperty("DefenderMaxClans", 10);
		ATTACKERS_RESPAWN_DELAY = sieges.getProperty("AttackerRespawn", 10000);

		CH_MINIMUM_CLAN_LEVEL = sieges.getProperty("ChSiegeClanMinLevel", 4);
		CH_MAX_ATTACKERS_NUMBER = sieges.getProperty("ChAttackerMaxClans", 10);
	}

	/**
	 * Loads gameserver settings.<br>
	 * IP addresses, database, rates, feature enabled/disabled, misc.
	 */
	private static final void loadServer() {
		final ExProperties server = initProperties(SERVER_FILE);

		HOSTNAME = server.getProperty("Hostname", "*");
		GAMESERVER_HOSTNAME = server.getProperty("GameserverHostname");
		GAMESERVER_PORT = server.getProperty("GameserverPort", 7777);
		GAMESERVER_LOGIN_HOSTNAME = server.getProperty("LoginHost", "127.0.0.1");
		GAMESERVER_LOGIN_PORT = server.getProperty("LoginPort", 9014);
		REQUEST_ID = server.getProperty("RequestServerID", 0);
		ACCEPT_ALTERNATE_ID = server.getProperty("AcceptAlternateID", true);
		USE_BLOWFISH_CIPHER = server.getProperty("UseBlowfishCipher", true);

		DATABASE_URL = server.getProperty("URL", "jdbc:mariadb://localhost/acis");
		DATABASE_LOGIN = server.getProperty("Login", "root");
		DATABASE_PASSWORD = server.getProperty("Password", "");
		DATABASE_MAX_CONNECTIONS = server.getProperty("MaximumDbConnections", 10);

		SERVER_LIST_BRACKET = server.getProperty("ServerListBrackets", false);
		SERVER_LIST_CLOCK = server.getProperty("ServerListClock", false);
		SERVER_GMONLY = server.getProperty("ServerGMOnly", false);
		SERVER_LIST_AGE = server.getProperty("ServerListAgeLimit", 0);
		SERVER_LIST_TESTSERVER = server.getProperty("TestServer", false);
		SERVER_LIST_PVPSERVER = server.getProperty("PvpServer", true);

		DELETE_DAYS = server.getProperty("DeleteCharAfterDays", 7);
		MAXIMUM_ONLINE_USERS = server.getProperty("MaximumOnlineUsers", 100);

		AUTO_LOOT = server.getProperty("AutoLoot", false);
		AUTO_LOOT_HERBS = server.getProperty("AutoLootHerbs", false);
		AUTO_LOOT_RAID = server.getProperty("AutoLootRaid", false);

		ALLOW_DISCARDITEM = server.getProperty("AllowDiscardItem", true);
		MULTIPLE_ITEM_DROP = server.getProperty("MultipleItemDrop", true);
		HERB_AUTO_DESTROY_TIME = server.getProperty("AutoDestroyHerbTime", 15) * 1000;
		ITEM_AUTO_DESTROY_TIME = server.getProperty("AutoDestroyItemTime", 600) * 1000;
		EQUIPABLE_ITEM_AUTO_DESTROY_TIME = server.getProperty("AutoDestroyEquipableItemTime", 0) * 1000;
		SPECIAL_ITEM_DESTROY_TIME = new HashMap<>();
		String[] data = server.getProperty("AutoDestroySpecialItemTime", (String[]) null, ",");
		if (data != null) {
			for (String itemData : data) {
				String[] item = itemData.split("-");
				SPECIAL_ITEM_DESTROY_TIME.put(Integer.parseInt(item[0]), Integer.parseInt(item[1]) * 1000);
			}
		}
		PLAYER_DROPPED_ITEM_MULTIPLIER = server.getProperty("PlayerDroppedItemMultiplier", 1);

		RATE_XP = server.getProperty("RateXp", 1.);
		RATE_SP = server.getProperty("RateSp", 1.);
		RATE_PARTY_XP = server.getProperty("RatePartyXp", 1.);
		RATE_PARTY_SP = server.getProperty("RatePartySp", 1.);
		RATE_DROP_ADENA = server.getProperty("RateDropAdena", 1.);
		RATE_DROP_ITEMS = server.getProperty("RateDropItems", 1.);
		RATE_DROP_ITEMS_BY_RAID = server.getProperty("RateRaidDropItems", 1.);
		RATE_DROP_SPOIL = server.getProperty("RateDropSpoil", 1.);
		RATE_DROP_MANOR = server.getProperty("RateDropManor", 1);
		RATE_QUEST_DROP = server.getProperty("RateQuestDrop", 1.);
		RATE_QUEST_REWARD = server.getProperty("RateQuestReward", 1.);
		RATE_QUEST_REWARD_XP = server.getProperty("RateQuestRewardXP", 1.);
		RATE_QUEST_REWARD_SP = server.getProperty("RateQuestRewardSP", 1.);
		RATE_QUEST_REWARD_ADENA = server.getProperty("RateQuestRewardAdena", 1.);
		RATE_KARMA_EXP_LOST = server.getProperty("RateKarmaExpLost", 1.);
		RATE_SIEGE_GUARDS_PRICE = server.getProperty("RateSiegeGuardsPrice", 1.);
		RATE_DROP_COMMON_HERBS = server.getProperty("RateCommonHerbs", 1.);
		RATE_DROP_HP_HERBS = server.getProperty("RateHpHerbs", 1.);
		RATE_DROP_MP_HERBS = server.getProperty("RateMpHerbs", 1.);
		RATE_DROP_SPECIAL_HERBS = server.getProperty("RateSpecialHerbs", 1.);
		PLAYER_DROP_LIMIT = server.getProperty("PlayerDropLimit", 3);
		PLAYER_RATE_DROP = server.getProperty("PlayerRateDrop", 5);
		PLAYER_RATE_DROP_ITEM = server.getProperty("PlayerRateDropItem", 70);
		PLAYER_RATE_DROP_EQUIP = server.getProperty("PlayerRateDropEquip", 25);
		PLAYER_RATE_DROP_EQUIP_WEAPON = server.getProperty("PlayerRateDropEquipWeapon", 5);
		PET_XP_RATE = server.getProperty("PetXpRate", 1.);
		PET_FOOD_RATE = server.getProperty("PetFoodRate", 1);
		SINEATER_XP_RATE = server.getProperty("SinEaterXpRate", 1.);
		KARMA_DROP_LIMIT = server.getProperty("KarmaDropLimit", 10);
		KARMA_RATE_DROP = server.getProperty("KarmaRateDrop", 70);
		KARMA_RATE_DROP_ITEM = server.getProperty("KarmaRateDropItem", 50);
		KARMA_RATE_DROP_EQUIP = server.getProperty("KarmaRateDropEquip", 40);
		KARMA_RATE_DROP_EQUIP_WEAPON = server.getProperty("KarmaRateDropEquipWeapon", 10);

		ALLOW_FREIGHT = server.getProperty("AllowFreight", true);
		ALLOW_WAREHOUSE = server.getProperty("AllowWarehouse", true);
		ALLOW_WEAR = server.getProperty("AllowWear", true);
		WEAR_DELAY = server.getProperty("WearDelay", 5);
		WEAR_PRICE = server.getProperty("WearPrice", 10);
		ALLOW_LOTTERY = server.getProperty("AllowLottery", true);
		ALLOW_WATER = server.getProperty("AllowWater", true);
		ALLOW_MANOR = server.getProperty("AllowManor", true);
		ALLOW_BOAT = server.getProperty("AllowBoat", true);
		ALLOW_CURSED_WEAPONS = server.getProperty("AllowCursedWeapons", true);

		ENABLE_FALLING_DAMAGE = server.getProperty("EnableFallingDamage", true);

		NO_SPAWNS = server.getProperty("NoSpawns", false);
		DEVELOPER = server.getProperty("Developer", false);
		PACKET_HANDLER_DEBUG = server.getProperty("PacketHandlerDebug", false);

		DEADLOCK_DETECTOR = server.getProperty("DeadLockDetector", false);
		DEADLOCK_CHECK_INTERVAL = server.getProperty("DeadLockCheckInterval", 20);
		RESTART_ON_DEADLOCK = server.getProperty("RestartOnDeadlock", false);

		LOG_CHAT = server.getProperty("LogChat", false);
		LOG_ITEMS = server.getProperty("LogItems", false);
		GMAUDIT = server.getProperty("GMAudit", false);

		ENABLE_COMMUNITY_BOARD = server.getProperty("EnableCommunityBoard", false);
		BBS_DEFAULT = server.getProperty("BBSDefault", "_bbshome");

		ROLL_DICE_TIME = server.getProperty("RollDiceTime", 4200);
		HERO_VOICE_TIME = server.getProperty("HeroVoiceTime", 10000);
		SUBCLASS_TIME = server.getProperty("SubclassTime", 2000);
		DROP_ITEM_TIME = server.getProperty("DropItemTime", 1000);
		SERVER_BYPASS_TIME = server.getProperty("ServerBypassTime", 100);
		MULTISELL_TIME = server.getProperty("MultisellTime", 100);
		MANUFACTURE_TIME = server.getProperty("ManufactureTime", 300);
		MANOR_TIME = server.getProperty("ManorTime", 3000);
		SENDMAIL_TIME = server.getProperty("SendMailTime", 10000);
		CHARACTER_SELECT_TIME = server.getProperty("CharacterSelectTime", 3000);
		GLOBAL_CHAT_TIME = server.getProperty("GlobalChatTime", 0);
		TRADE_CHAT_TIME = server.getProperty("TradeChatTime", 0);
		SOCIAL_TIME = server.getProperty("SocialTime", 2000);
		MOVE_TIME = server.getProperty("MoveTime", 100);

		SCHEDULED_THREAD_POOL_COUNT = server.getProperty("ScheduledThreadPoolCount", -1);
		THREADS_PER_SCHEDULED_THREAD_POOL = server.getProperty("ThreadsPerScheduledThreadPool", 4);
		INSTANT_THREAD_POOL_COUNT = server.getProperty("InstantThreadPoolCount", -1);
		THREADS_PER_INSTANT_THREAD_POOL = server.getProperty("ThreadsPerInstantThreadPool", 2);

		L2WALKER_PROTECTION = server.getProperty("L2WalkerProtection", false);
		ZONE_TOWN = server.getProperty("ZoneTown", 0);
		SERVER_NEWS = server.getProperty("ShowServerNews", false);
	}

	/**
	 * Loads loginserver settings.<br>
	 * IP addresses, database, account, misc.
	 */
	private static final void loadLogin() {
		final ExProperties server = initProperties(LOGINSERVER_FILE);

		HOSTNAME = server.getProperty("Hostname", "localhost");
		LOGINSERVER_HOSTNAME = server.getProperty("LoginserverHostname", "*");
		LOGINSERVER_PORT = server.getProperty("LoginserverPort", 2106);
		GAMESERVER_LOGIN_HOSTNAME = server.getProperty("LoginHostname", "*");
		GAMESERVER_LOGIN_PORT = server.getProperty("LoginPort", 9014);
		LOGIN_TRY_BEFORE_BAN = server.getProperty("LoginTryBeforeBan", 3);
		LOGIN_BLOCK_AFTER_BAN = server.getProperty("LoginBlockAfterBan", 600);
		ACCEPT_NEW_GAMESERVER = server.getProperty("AcceptNewGameServer", false);
		SHOW_LICENCE = server.getProperty("ShowLicence", true);

		DATABASE_URL = server.getProperty("URL", "jdbc:mariadb://localhost/acis");
		DATABASE_LOGIN = server.getProperty("Login", "root");
		DATABASE_PASSWORD = server.getProperty("Password", "");
		DATABASE_MAX_CONNECTIONS = server.getProperty("MaximumDbConnections", 5);

		AUTO_CREATE_ACCOUNTS = server.getProperty("AutoCreateAccounts", true);

		FLOOD_PROTECTION = server.getProperty("EnableFloodProtection", true);
		FAST_CONNECTION_LIMIT = server.getProperty("FastConnectionLimit", 15);
		NORMAL_CONNECTION_TIME = server.getProperty("NormalConnectionTime", 700);
		FAST_CONNECTION_TIME = server.getProperty("FastConnectionTime", 350);
		MAX_CONNECTION_PER_IP = server.getProperty("MaxConnectionPerIP", 50);
	}

	public static final void loadGameServer() {
		LOGGER.info("Loading gameserver configuration files.");

		// clans settings
		loadClans();

		// events settings
		loadEvents();

		// geoengine settings
		loadGeoengine();

		// hexID
		loadHexID();

		// NPCs/monsters settings
		loadNpcs();

		// players settings
		loadPlayers();

		// siege settings
		loadSieges();

		// server settings
		loadServer();
		// mods settings
		loadMods();
		// Loads Skin Click
		loadSkin();
		// enchant settings new
		loadEnchantSystemConfig();
		// PartyFarmEvent
		loadPTFarmConfig();
		// PC Bang Event
		loadPcBangConfig();
		// Tvt Settings
		loadTvTConfig();
		//custom quests
		loadCustomQuestConfig();

	}

	public static final void loadLoginServer() {
		LOGGER.info("Loading loginserver configuration files.");

		// login settings
		loadLogin();
	}

	public static final void loadAccountManager() {
		LOGGER.info("Loading account manager configuration files.");

		// login settings
		loadLogin();
	}

	public static final void loadGameServerRegistration() {
		LOGGER.info("Loading gameserver registration configuration files.");

		// login settings
		loadLogin();
	}

	public static final class ClassMasterSettings {
		private final Map<Integer, Boolean> _allowedClassChange;
		private final Map<Integer, List<IntIntHolder>> _claimItems;
		private final Map<Integer, List<IntIntHolder>> _rewardItems;

		public ClassMasterSettings(String configLine) {
			_allowedClassChange = new HashMap<>(3);
			_claimItems = new HashMap<>(3);
			_rewardItems = new HashMap<>(3);

			if (configLine != null)
				parseConfigLine(configLine.trim());
		}

		private void parseConfigLine(String configLine) {
			StringTokenizer st = new StringTokenizer(configLine, ";");
			while (st.hasMoreTokens()) {
				// Get allowed class change.
				int job = Integer.parseInt(st.nextToken());

				_allowedClassChange.put(job, true);

				List<IntIntHolder> items = new ArrayList<>();

				// Parse items needed for class change.
				if (st.hasMoreTokens()) {
					StringTokenizer st2 = new StringTokenizer(st.nextToken(), "[],");
					while (st2.hasMoreTokens()) {
						StringTokenizer st3 = new StringTokenizer(st2.nextToken(), "()");
						items.add(
								new IntIntHolder(Integer.parseInt(st3.nextToken()), Integer.parseInt(st3.nextToken())));
					}
				}

				// Feed the map, and clean the list.
				_claimItems.put(job, items);
				items = new ArrayList<>();

				// Parse gifts after class change.
				if (st.hasMoreTokens()) {
					StringTokenizer st2 = new StringTokenizer(st.nextToken(), "[],");
					while (st2.hasMoreTokens()) {
						StringTokenizer st3 = new StringTokenizer(st2.nextToken(), "()");
						items.add(
								new IntIntHolder(Integer.parseInt(st3.nextToken()), Integer.parseInt(st3.nextToken())));
					}
				}

				_rewardItems.put(job, items);
			}
		}

		public boolean isAllowed(int job) {
			if (_allowedClassChange == null)
				return false;

			if (_allowedClassChange.containsKey(job))
				return _allowedClassChange.get(job);

			return false;
		}

		public List<IntIntHolder> getRewardItems(int job) {
			return _rewardItems.get(job);
		}

		public List<IntIntHolder> getRequiredItems(int job) {
			return _claimItems.get(job);
		}
	}
}