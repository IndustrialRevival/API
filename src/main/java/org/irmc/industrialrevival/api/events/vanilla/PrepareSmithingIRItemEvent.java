package org.irmc.industrialrevival.api.events.vanilla;

import com.destroystokyo.paper.event.inventory.PrepareResultEvent;
import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class PrepareSmithingIRItemEvent extends PrepareResultEvent implements Cancellable, RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final IndustrialRevivalItem iritem;
    private final PrepareSmithingEvent originalEvent;
    private boolean cancelled;

    public PrepareSmithingIRItemEvent(PrepareSmithingEvent event, IndustrialRevivalItem iritem) {
        super(event.getView(), event.getResult());
        this.originalEvent = event;
        this.iritem = iritem;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
        originalEvent.setResult(null);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
