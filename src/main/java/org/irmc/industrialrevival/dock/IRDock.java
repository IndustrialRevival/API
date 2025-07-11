package org.irmc.industrialrevival.dock;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;

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
}
