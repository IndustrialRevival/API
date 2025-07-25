package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.NotHopperable;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.data.runtime.IRBlockData;
import org.irmc.industrialrevival.api.events.vanilla.BlockExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.events.vanilla.EndermanMoveIRBlockEvent;
import org.irmc.industrialrevival.api.events.vanilla.EntityChangeIRBlockEvent;
import org.irmc.industrialrevival.api.events.vanilla.EntityExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.events.vanilla.EntityPickupIRItemEvent;
import org.irmc.industrialrevival.api.events.vanilla.IRBlockBreakEvent;
import org.irmc.industrialrevival.api.events.vanilla.IRBlockFromToEvent;
import org.irmc.industrialrevival.api.events.vanilla.IRBlockPlaceEvent;
import org.irmc.industrialrevival.api.events.vanilla.IRItemBreakBlockEvent;
import org.irmc.industrialrevival.api.events.vanilla.IRItemDamageEntityEvent;
import org.irmc.industrialrevival.api.events.vanilla.IRItemInteractEvent;
import org.irmc.industrialrevival.api.events.vanilla.IRItemKillEntityEvent;
import org.irmc.industrialrevival.api.events.vanilla.InventoryMoveIRItemEvent;
import org.irmc.industrialrevival.api.events.vanilla.MenuOpenEvent;
import org.irmc.industrialrevival.api.events.vanilla.PistonExtendIRBlockEvent;
import org.irmc.industrialrevival.api.events.vanilla.PistonRetractIRBlockEvent;
import org.irmc.industrialrevival.api.events.vanilla.PlayerBucketEmptyToIRBlockEvent;
import org.irmc.industrialrevival.api.events.vanilla.PlayerInteractIRBlockEvent;
import org.irmc.industrialrevival.api.events.vanilla.PrepareGrindstoneIRItemEvent;
import org.irmc.industrialrevival.api.events.vanilla.PrepareIRItemEnchantEvent;
import org.irmc.industrialrevival.api.events.vanilla.PrepareItemCraftIRItemEvent;
import org.irmc.industrialrevival.api.events.vanilla.PrepareSmithingIRItemEvent;
import org.irmc.industrialrevival.api.events.vanilla.PrepareTradeSelectIRItemEvent;
import org.irmc.industrialrevival.utils.DataUtil;

public class EventPrechecker implements Listener {
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onBlockExplodeIRBlockEvent(BlockExplodeIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getBlock().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onEndermanMoveIRBlock(EndermanMoveIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getBlock().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onEntityChangeIRBlock(EntityChangeIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getBlock().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onEntityExplodeIRBlock(EntityExplodeIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getLocation().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onEntityPickupIRItem(EntityPickupIRItemEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getItem().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onInventoryMoveIRItem(InventoryMoveIRItemEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getDestination().getLocation().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }

        if (event.getOriginalEvent().getDestination().getType().equals(InventoryType.HOPPER) && event.getIritem() instanceof NotHopperable) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onIRBlockBreak(IRBlockBreakEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getBlock().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onIRBlockFromTo(IRBlockFromToEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getToBlock().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onIRBlockPlace(IRBlockPlaceEvent event) {
        Location location = event.getOriginalEvent().getBlockPlaced().getLocation();
        IRBlockData blockData = DataUtil.getBlockData(location);
        if (blockData != null) {
            event.getOriginalEvent().setCancelled(true);
        }

        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getBlockPlaced().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }

        if (event.getIritem() instanceof NotPlaceable) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onIRItemBreakBlock(IRItemBreakBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getBlock().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onIRItemDamageEntity(IRItemDamageEntityEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getEntity().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onIRItemInteract(IRItemInteractEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getPlayer().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onIRItemKillEntity(IRItemKillEntityEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getEntity().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onMenuOpen(MenuOpenEvent event) {
        if (!event.getInventoryOpenEvent().getPlayer().isOp()) {
            IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(event.getOpenedMenu().getPreset().getId());
            if (iritem != null && iritem.isDisabledInWorld(event.getInventoryOpenEvent().getPlayer().getWorld())) {
                event.getInventoryOpenEvent().setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPistonExtendIRBlock(PistonExtendIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getBlock().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPistonRetractIRBlock(PistonRetractIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getBlock().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerBucketEmptyToIRBlock(PlayerBucketEmptyToIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getBlock().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerInteractIRBlock(PlayerInteractIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getClickedBlock().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPrepareGrindstoneIRItem(PrepareGrindstoneIRItemEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getInventory().getLocation().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPrepareIRItemEnchant(PrepareIRItemEnchantEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getInventory().getLocation().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPrepareItemCraftIRItem(PrepareItemCraftIRItemEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getInventory().getLocation().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPrepareSmithingIRItem(PrepareSmithingIRItemEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getInventory().getLocation().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPrepareTradeSelectIRItem(PrepareTradeSelectIRItemEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getOriginalEvent().getInventory().getLocation().getWorld())) {
            event.getOriginalEvent().setCancelled(true);
        }
    }
}
