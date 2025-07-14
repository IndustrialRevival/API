package org.irmc.industrialrevival.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.core.services.IListenerManager;
import org.irmc.industrialrevival.dock.IRDock;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager implements IListenerManager {
    private final List<Listener> listeners = new ArrayList<>();

    @Override
    public @NotNull List<Listener> getListeners() {
        return listeners;
    }

    @Override
    public void registerListener(@NotNull Listener listener) {
        listeners.add(listener);
        Bukkit.getPluginManager().registerEvents(listener, IRDock.getDock());
    }

    @Override
    public void unregisterListener(@NotNull Listener listener) {
        HandlerList.unregisterAll(listener);
    }

    @Override
    public void unregisterAllListeners() {
        for (Listener listener : listeners) {
            HandlerList.unregisterAll(listener);
        }
    }
}
