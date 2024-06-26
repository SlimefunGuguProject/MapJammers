package me.vaan.mapjammer;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.vaan.mapjammer.implementation.Setup;
import me.vaan.mapjammer.runnables.CheckPlayers;
import me.vaan.mapjammer.util.ConfigStorage;
import me.vaan.mapjammer.util.ShowHideInterface;
import net.guizhanss.guizhanlibplugin.updater.GuizhanUpdater;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapCommonAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.jpenilla.squaremap.api.PlayerManager;
import xyz.jpenilla.squaremap.api.Squaremap;
import xyz.jpenilla.squaremap.api.SquaremapProvider;

import java.io.File;

public final class MapJammer extends JavaPlugin implements SlimefunAddon {


    @Override
    public void onEnable() {
        configure();

        startMapTask();

        ItemGroup group = new ItemGroup(new NamespacedKey(this, "map_jammer"), new CustomItemStack(Material.COMPASS, "&e地图干扰"));
        new Setup(this, group);

        if (ConfigStorage.AUTO_UPDATE && getPluginVersion().startsWith("Build")) {
            GuizhanUpdater.start(this, getFile(), "SlimefunGuguProject", "MapJammers", "main");
        }
    }

    private void startMapTask() {
        PluginManager pm = getServer().getPluginManager();
        // seconds * 20L (20 ticks = 1 sec)
        long period = 20L * ConfigStorage.TIME_FRAME;

        if (pm.isPluginEnabled("squaremap")) {
            Squaremap sqmp = SquaremapProvider.get();
            PlayerManager mng = sqmp.playerManager();

            ShowHideInterface showHideInterface = new ShowHideInterface() {
                @Override
                public void show(Player p) {
                    mng.show(p.getUniqueId());
                }

                @Override
                public void hide(Player p) {
                    mng.hide(p.getUniqueId());
                }
            };

            new CheckPlayers(showHideInterface).runTaskTimer(this, 0L, period);
            return;
        }

        Plugin dynmap = pm.getPlugin("dynmap");
        if (dynmap != null && dynmap.isEnabled()) {
            DynmapCommonAPI dynmapAPI = (DynmapCommonAPI) dynmap;

            ShowHideInterface showHideInterface = new ShowHideInterface() {
                @Override
                public void show(Player p) {
                    dynmapAPI.setPlayerVisiblity(p.getName(), true);
                }

                @Override
                public void hide(Player p) {
                    dynmapAPI.setPlayerVisiblity(p.getName(), false);
                }
            };

            new CheckPlayers(showHideInterface).runTaskTimer(this, 0L, period);
            return;
        }

        this.setEnabled(false);
        getLogger().warning("未找到支持的地图插件，地图干扰器不会启用。");
    }

    private void configure() {
        FileConfiguration config = this.getConfig();

        getDataFolder().mkdirs();
        if(!new File(getDataFolder(), "config.yml").exists()) saveDefaultConfig();

        new ConfigStorage(config);
    }

    @Override
    public void onDisable() {

    }

    @NotNull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return null;
    }
}
