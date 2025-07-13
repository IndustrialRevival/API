package org.irmc.industrialrevival.api.events.ir;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.irmc.industrialrevival.api.data.runtime.IRBlockData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Event triggered at the start of a tick cycle in the industrial revival system.
 * This event contains information about block data, check interval and tick count.
 *
 * @author balugaq
 * @see IRBlockData
 */
@Getter
public class TickStartEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Map<Location, IRBlockData> blockDataMap;
    private final int checkInterval;
    private final long ticked;

    public TickStartEvent(Map<Location, IRBlockData> blockDataMap, int checkInterval, long ticked) {
        super(true);
        this.blockDataMap = new HashMap<>(blockDataMap);
        this.checkInterval = checkInterval;
        this.ticked = ticked;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
