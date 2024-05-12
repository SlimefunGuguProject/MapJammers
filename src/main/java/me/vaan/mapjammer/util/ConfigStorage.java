package me.vaan.mapjammer.util;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigStorage {

    public static boolean AUTO_UPDATE;
    public static float COST;
    public static int TIME_FRAME;
    public static boolean UNDERGROUD_HIDE;
    public static int UNDERGROUND_Y;


    public ConfigStorage(FileConfiguration config) {
        AUTO_UPDATE = config.getBoolean("auto-update", true);
        COST = config.getInt("energy-cost");
        TIME_FRAME = config.getInt("time-frame");
        UNDERGROUD_HIDE = config.getBoolean("underground-hide");
        UNDERGROUND_Y = config.getInt("underground-y");
    }
}
