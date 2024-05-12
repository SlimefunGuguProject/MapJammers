package me.vaan.mapjammer.implementation;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.vaan.mapjammer.util.ConfigStorage;
import org.bukkit.Material;

public class Items {
    public static SlimefunItemStack JAMMER = new SlimefunItemStack("MAP_JAMMER", Material.COMPASS, "&c地图干扰器", "", "&7当你物品栏中拥有该物品时", "&7你会消失在地图上", "", LoreBuilder.power((int) ConfigStorage.COST, "/" + ConfigStorage.TIME_FRAME + "s"), LoreBuilder.powerCharged(0, 120));
    public static SlimefunItemStack JAMMER_2 = new SlimefunItemStack("MAP_JAMMER_2", Material.COMPASS, "&c地图干扰器", "", "&7当你物品栏中拥有该物品时", "&7你会消失在地图上", "", LoreBuilder.power((int) ConfigStorage.COST, "/" + ConfigStorage.TIME_FRAME + "s"), LoreBuilder.powerCharged(0, 360));
    public static SlimefunItemStack JAMMER_3 = new SlimefunItemStack("MAP_JAMMER_3", Material.COMPASS, "&c地图干扰器", "", "&7当你物品栏中拥有该物品时", "&7你会消失在地图上", "", LoreBuilder.power((int) ConfigStorage.COST, "/" + ConfigStorage.TIME_FRAME + "s"), LoreBuilder.powerCharged(0, 1440));

}
