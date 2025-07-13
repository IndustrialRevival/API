package org.irmc.industrialrevival.api.events.ir;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.data.runtime.IRBlockData;
import org.irmc.industrialrevival.api.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

/**
 * Event representing a tick update for a specific block in the industrial revival system.
 * Contains information about the block, associated machine menu, industrial item and block data.
 *
 * @author balugaq
 * @see Cancellable
 * @see RelatedIRItem
 */
@Getter
public class BlockTickEvent extends Event implements Cancellable, RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final Block block;
    private final MachineMenu menu;
    private final IndustrialRevivalItem iritem;
    private final IRBlockData blockData;
    @Setter
    private boolean cancelled;

    public BlockTickEvent(Block block, MachineMenu menu, IndustrialRevivalItem iritem, IRBlockData blockData) {
        super(true);
        this.block = block;
        this.menu = menu;
        this.iritem = iritem;
        this.blockData = blockData;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
