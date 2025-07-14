package org.irmc.industrialrevival.dock;

import com.google.common.base.Preconditions;
import com.tcoded.folialib.FoliaLib;
import com.tcoded.folialib.wrapper.task.WrappedTask;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.command.ConsoleCommandSender;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.core.services.IItemSettings;
import org.irmc.industrialrevival.core.services.IGitHubService;
import org.irmc.industrialrevival.core.services.IIRDataManager;
import org.irmc.industrialrevival.core.services.IIRRegistry;
import org.irmc.industrialrevival.core.services.IItemDataService;
import org.irmc.industrialrevival.core.services.IListenerManager;
import org.irmc.industrialrevival.core.services.IMinecraftRecipeService;
import org.irmc.industrialrevival.core.services.IRunningProfilerService;
import org.irmc.industrialrevival.core.services.ISQLDataManager;
import org.irmc.pigeonlib.language.LanguageManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class IRDock {
    private static final String PLUGIN_CLASS = "org.irmc.industrialrevival.implementation.IndustrialRevival";
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

    public static @NotNull IItemSettings getItemSettings() {
        return getPlugin().getItemSettings();
    }

    public static void runSync(@NotNull Runnable runnable) {
        getPlugin().runSync(runnable);
    }

    public static void runAsync(@NotNull Runnable runnable) {
        getPlugin().runAsync(runnable);
    }

    public static void runSync(@NotNull Consumer<WrappedTask> consumer) {
        getPlugin().runSync(consumer);
    }

    public static void runAsync(@NotNull Consumer<WrappedTask> consumer) {
        getPlugin().runAsync(consumer);
    }

    public static Logger getLogger() {
        return getPlugin().getLogger();
    }

    public static org.slf4j.Logger getSLF4JLogger() {
        return getPlugin().getSLF4JLogger();
    }

    public static ComponentLogger getComponentLogger() {
        return getPlugin().getComponentLogger();
    }

    public static ConsoleCommandSender getConsoleSender() {
        return getPlugin().getServer().getConsoleSender();
    }

    public static String getIssueTrackerURL() {
        return getPlugin().getIssueTrackerURL();
    }

    public static FoliaLib getFoliaLibImpl() {
        return getPlugin().getFoliaLibImpl();
    }

    public static String getName() {
        return getPlugin().getName();
    }

    public static void reloadConfig() {
        getPlugin().reloadConfig();
    }
    public static String getVersion() {
        return getPlugin().getVersion();
    }

    public static List<IndustrialRevivalAddon> getAddons() {
        return getPlugin().getAddons();
    }

    public static @NotNull IListenerManager getListenerManager() {
        return getPlugin().getListenerManager();
    }
}
