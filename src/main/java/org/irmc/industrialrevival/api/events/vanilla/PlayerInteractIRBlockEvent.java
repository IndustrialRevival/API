package org.irmc.industrialrevival.api.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerInteractIRBlockEvent extends PlayerEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final PlayerInteractEvent originalEvent;
    private final IndustrialRevivalItem iritem;

    public PlayerInteractIRBlockEvent(PlayerInteractEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getPlayer());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
