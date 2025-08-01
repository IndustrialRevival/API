package org.irmc.industrialrevival.dock;

import com.google.common.base.Preconditions;
import com.tcoded.folialib.FoliaLib;
import com.tcoded.folialib.wrapper.task.WrappedTask;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.core.listeners.BulkDensityListener;
import org.irmc.industrialrevival.core.listeners.DefaultHandler;
import org.irmc.industrialrevival.core.listeners.DropListener;
import org.irmc.industrialrevival.core.listeners.EventCreator;
import org.irmc.industrialrevival.core.listeners.EventPrechecker;
import org.irmc.industrialrevival.core.listeners.GuideListener;
import org.irmc.industrialrevival.core.listeners.HandlerCaller;
import org.irmc.industrialrevival.core.listeners.LimitedItemListener;
import org.irmc.industrialrevival.core.listeners.MachineMenuListener;
import org.irmc.industrialrevival.core.listeners.MultiBlockListener;
import org.irmc.industrialrevival.core.listeners.MultiblockTicker;
import org.irmc.industrialrevival.core.listeners.NotPlaceableListener;
import org.irmc.industrialrevival.core.listeners.PlayerJoinListener;
import org.irmc.industrialrevival.core.listeners.RespondTimingListener;
import org.irmc.industrialrevival.core.listeners.UnusableItemListener;
import org.irmc.industrialrevival.core.managers.ListenerManager;
import org.irmc.industrialrevival.core.services.IItemSettings;
import org.irmc.industrialrevival.core.services.IGitHubService;
import org.irmc.industrialrevival.core.services.IIRDataManager;
import org.irmc.industrialrevival.core.services.IIRRegistry;
import org.irmc.industrialrevival.core.services.IItemDataService;
import org.irmc.industrialrevival.core.services.ILanguageManager;
import org.irmc.industrialrevival.core.services.IListenerManager;
import org.irmc.industrialrevival.core.services.IMinecraftRecipeService;
import org.irmc.industrialrevival.core.services.IRunningProfilerService;
import org.irmc.industrialrevival.core.services.ISQLDataManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class IRDock extends JavaPlugin {
    private static final String PLUGIN_CLASS = "org.irmc.industrialrevival.implementation.IndustrialRevival";
    private static IRDock instance;
    private static IIndustrialRevivalPlugin plugin;
    private static IListenerManager baseListenerManager;
    
    public static IRDock getDock() {
        return instance;
    }
    
    @Override
    public void onEnable() {
        instance = this;
        
        baseListenerManager = new ListenerManager();
        baseListenerManager.registerListener(new BulkDensityListener());
        baseListenerManager.registerListener(new DefaultHandler());
        baseListenerManager.registerListener(new DropListener());
        baseListenerManager.registerListener(new EventCreator());
        baseListenerManager.registerListener(new EventPrechecker());
        baseListenerManager.registerListener(new GuideListener());
        baseListenerManager.registerListener(new HandlerCaller());
        baseListenerManager.registerListener(new LimitedItemListener());
        baseListenerManager.registerListener(new MachineMenuListener());
        baseListenerManager.registerListener(new MultiBlockListener());
        baseListenerManager.registerListener(new NotPlaceableListener());
        baseListenerManager.registerListener(new PlayerJoinListener());
        baseListenerManager.registerListener(new RespondTimingListener());
        baseListenerManager.registerListener(new UnusableItemListener());
        baseListenerManager.registerListener(new MultiblockTicker());
    }
    
    @Override
    public void onDisable() {
        baseListenerManager.unregisterAllListeners();
    }

    @NotNull
    public static IIndustrialRevivalPlugin getPlugin() {
        Preconditions.checkNotNull(plugin, "Plugin is not loaded");
        return plugin;
    }

    public static void setPlugin(@NotNull IIndustrialRevivalPlugin plugin) {
        Preconditions.checkState(IRDock.plugin == null, "Plugin is already loaded");
        Preconditions.checkArgument(plugin != null, "Plugin cannot be null");
        Preconditions.checkArgument(plugin.getClass().getName().equals(PLUGIN_CLASS), "Not an valid plugin instance");
        IRDock.plugin = plugin;
    }

    public static @NotNull ILanguageManager getLanguageManager() {
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

    public static ConsoleCommandSender getConsoleSender() {
        return getPlugin().getServer().getConsoleSender();
    }

    public static String getIssueTrackerURL() {
        return getPlugin().getIssueTrackerURL();
    }

    public static FoliaLib getFoliaLibImpl() {
        return getPlugin().getFoliaLibImpl();
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

    public IListenerManager getBaseListenerManager() {
        return baseListenerManager;
    }
}
