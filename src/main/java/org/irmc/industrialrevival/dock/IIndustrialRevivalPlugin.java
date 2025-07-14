package org.irmc.industrialrevival.dock;

import com.comphenix.protocol.ProtocolManager;
import com.tcoded.folialib.FoliaLib;
import com.tcoded.folialib.wrapper.task.WrappedTask;
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

/**
 * This interface serves as the core plugin interface for the IndustrialRevival plugin.
 * It extends IndustrialRevivalAddon to provide access to the main plugin instance and services.
 * This interface is used to retrieve various managers and services required for plugin operations.
 *
 * @author lijinhong11
 * @author balugaq
 */
public interface IIndustrialRevivalPlugin extends IndustrialRevivalAddon {
    /**
     * Gets the LanguageManager instance used for handling multi-language support.
     *
     * @return the LanguageManager instance
     */
    @NotNull
    LanguageManager getLanguageManager();

    /**
     * Gets the ProtocolManager instance used for handling ProtocolLib packet operations.
     *
     * @return the ProtocolManager instance
     */
    @NotNull
    ProtocolManager getProtocolManager();

    /**
     * Gets the SQL data manager for handling SQL database operations.
     *
     * @return an instance of ISQLDataManager
     */
    @NotNull ISQLDataManager getSQLDataManager();

    /**
     * Gets the main data manager for handling general data operations within the plugin.
     *
     * @return an instance of IIRDataManager
     */
    @NotNull IIRDataManager getDataManager();

    /**
     * Gets the registry used for registering and managing custom items and blocks.
     *
     * @return an instance of IIRRegistry
     */
    @NotNull IIRRegistry getRegistry();

    /**
     * Gets the service responsible for managing item data and persistence.
     *
     * @return an instance of IItemDataService
     */
    @NotNull IItemDataService getItemDataService();

    /**
     * Gets the service responsible for managing Minecraft recipes.
     *
     * @return an instance of IMinecraftRecipeService
     */
    @NotNull IMinecraftRecipeService getMinecraftRecipeService();

    /**
     * Gets the profiler service used for performance monitoring and profiling.
     *
     * @return an instance of IRunningProfilerService
     */
    @NotNull IRunningProfilerService getRunningProfilerService();

    /**
     * Gets the GitHub service used for interacting with GitHub repositories.
     *
     * @return an instance of IGitHubService
     */
    @NotNull IGitHubService getGitHubService();

    /**
     * Gets the global settings for items in the plugin.
     *
     * @return an instance of ItemSettings
     */
    @NotNull IItemSettings getItemSettings();

    /**
     * Runs the specified task asynchronously on the server thread pool.
     *
     * @param runnable the task to execute asynchronously
     */
    void runAsync(@NotNull Runnable runnable);

    /**
     * Runs the specified task synchronously on the main server thread.
     *
     * @param runnable the task to execute synchronously
     */
    void runSync(@NotNull Runnable runnable);

    void runAsync(@NotNull Consumer<WrappedTask> consumer);

    void runSync(@NotNull Consumer<WrappedTask> consumer);

    /**
     * Gets the list of all addons loaded and registered with the plugin.
     *
     * @return a list of IndustrialRevivalAddon instances
     */
    @NotNull
    List<IndustrialRevivalAddon> getAddons();

    @NotNull FoliaLib getFoliaLibImpl();

    @NotNull IListenerManager getListenerManager();
}