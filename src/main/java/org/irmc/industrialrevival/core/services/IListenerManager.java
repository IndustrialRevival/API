package org.irmc.industrialrevival.core.services;

import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IListenerManager {
    @NotNull List<Listener> getListeners();

    void registerListener(@NotNull Listener listener);

    void unregisterListener(@NotNull Listener listener);

    void unregisterAllListeners();
}
