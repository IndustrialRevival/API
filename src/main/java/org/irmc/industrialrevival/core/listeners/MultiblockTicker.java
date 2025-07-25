package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.api.items.attributes.ExtraTickable;
import org.irmc.industrialrevival.api.events.ir.TickDoneEvent;
import org.irmc.industrialrevival.api.events.ir.TickStartEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultiblockTicker implements Listener {
    private static final Map<Location, ExtraTickable> tickable_tickstart = new ConcurrentHashMap<>();
    private static final Map<Location, ExtraTickable> tickables_tickdone = new ConcurrentHashMap<>();

    public static void addTickable(Location location, ExtraTickable extraTickable) {
        switch (extraTickable.getTime()) {
            case TICK_START -> {
                synchronized (tickable_tickstart) {
                    tickable_tickstart.put(location, extraTickable);
                }
            }
            case TICK_DONE -> {
                synchronized (tickables_tickdone) {
                    tickables_tickdone.put(location, extraTickable);
                }
            }
        }
    }

    public static void removeTickable(Location location) {
        synchronized (tickable_tickstart) {
            tickable_tickstart.remove(location);
        }
        synchronized (tickables_tickdone) {
            tickables_tickdone.remove(location);
        }
    }

    @EventHandler
    public void onTickStart(TickStartEvent event) {
        for (Location location : tickable_tickstart.keySet()) {
            tickable_tickstart.get(location).tick(location);
        }
    }

    @EventHandler
    public void onTickDone(TickDoneEvent event) {
        for (Location location : tickables_tickdone.keySet()) {
            tickables_tickdone.get(location).tick(location);
        }
    }
}
