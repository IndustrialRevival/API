package org.irmc.industrialrevival.dock;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.objects.ItemSettings;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;
import org.irmc.industrialrevival.core.services.IGitHubService;
import org.irmc.industrialrevival.core.services.IIRDataManager;
import org.irmc.industrialrevival.core.services.IIRRegistry;
import org.irmc.industrialrevival.core.services.IItemDataService;
import org.irmc.industrialrevival.core.services.IMinecraftRecipeService;
import org.irmc.industrialrevival.core.services.IRunningProfilerService;
import org.irmc.industrialrevival.core.services.ISQLDataManager;
import org.irmc.pigeonlib.language.LanguageManager;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class IRDock {
    private static final String PLUGIN_CLASS = "org.irmc.industrialrevival.implementation.IndustrialRevival";
    private static final Map<UUID, IRGuideImplementation> guides = new ConcurrentHashMap<>();
    private static IIndustrialRevivalPlugin plugin;

    public static IIndustrialRevivalPlugin getPlugin() {
        Preconditions.checkNotNull(plugin, "Plugin is not loaded");
        return plugin;
    }

    public static void setPlugin(IIndustrialRevivalPlugin plugin) {
        Preconditions.checkState(IRDock.plugin == null, "Plugin is already loaded");
        Preconditions.checkArgument(plugin != null, "Plugin cannot be null");
        Preconditions.checkArgument(plugin.getClass().getName().equals(PLUGIN_CLASS), "Not an valid plugin instance");
        IRDock.plugin = plugin;
    }

    public static IRGuideImplementation getGuide(Player player) {
        return guides.get(player.getUniqueId());
    }

    /**
     * Called when a player interact a guide book
     */
    public static void setGuide(Player player, IRGuideImplementation guide) {
        guides.put(player.getUniqueId(), guide);
    }

    public static @NotNull LanguageManager getLanguageManager() {
        return getPlugin().getLanguageManager();
    }

    public static @NotNull ISQLDataManager getSQLDataManager() {
        return getPlugin().getSQLDataManager();
    }

    public static @NotNull IIRDataManager getDataManager() {
        return getPlugin().getDataManager();
    }

    public static @NotNull IIRRegistry getRegistry() {
        return getPlugin().getRegistry();
    }

    public static @NotNull IItemDataService getItemDataService() {
        return getPlugin().getItemDataService();
    }

    public static @NotNull IMinecraftRecipeService getMinecraftRecipeService() {
        return getPlugin().getMinecraftRecipeService();
    }

    public static @NotNull IRunningProfilerService getRunningProfilerService() {
        return getPlugin().getRunningProfilerService();
    }

    public static @NotNull IGitHubService getGitHubService() {
        return getPlugin().getGitHubService();
    }

    public static @NotNull ItemSettings getItemSettings() {
        return getPlugin().getItemSettings();
    }

    public static void runSync(@NotNull Runnable runnable) {
        getPlugin().runSync(runnable);
    }

    public static void runAsync(@NotNull Runnable runnable) {
        getPlugin().runAsync(runnable);
    }
}
