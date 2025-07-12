package org.irmc.industrialrevival.dock;

import com.comphenix.protocol.ProtocolManager;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.core.services.IGitHubService;
import org.irmc.industrialrevival.core.services.IIRDataManager;
import org.irmc.industrialrevival.core.services.IIRRegistry;
import org.irmc.industrialrevival.core.services.IItemDataService;
import org.irmc.industrialrevival.core.services.IRunningProfilerService;
import org.irmc.industrialrevival.core.services.IMinecraftRecipeService;
import org.irmc.industrialrevival.core.services.ISQLDataManager;
import org.irmc.pigeonlib.language.LanguageManager;
import org.jetbrains.annotations.NotNull;

/**
 * This interface is for the IndustrialRevival plugin.
 */
public interface IIndustrialRevivalPlugin extends IndustrialRevivalAddon {
    @NotNull
    LanguageManager getLanguageManager();

    @NotNull
    ProtocolManager getProtocolManager();

    @NotNull ISQLDataManager getSQLDataManager();

    @NotNull IIRDataManager getDataManager();

    @NotNull IIRRegistry getRegistry();

    @NotNull IItemDataService getItemDataService();

    @NotNull IMinecraftRecipeService getMinecraftRecipeService();

    @NotNull IRunningProfilerService getRunningProfilerService();

    @NotNull IGitHubService getGitHubService();
}
