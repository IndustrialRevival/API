package org.irmc.industrialrevival.api.multiblock.piece;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.data.runtime.IRBlockData;
import org.irmc.industrialrevival.utils.DataUtil;
import org.jetbrains.annotations.NotNull;

public class IRBlockStructurePiece extends StructurePiece {
    private final IndustrialRevivalItem iritem;

    public IRBlockStructurePiece(IndustrialRevivalItem iritem) {
        if (!iritem.getItemStack().getType().isBlock()) {
            throw new IllegalArgumentException("The industrial revival item must be a block");
        }
        this.iritem = iritem;
    }

    @Override
    public boolean matches(Block b) {
        return matches(b.getLocation());
    }

    public boolean matches(Location location) {
        IRBlockData blockData = DataUtil.getBlockData(location);
        if (blockData == null) {
            return false;
        }

        IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(blockData.getId());
        if (iritem == null) {
            return false;
        }

        return this.iritem.getId().equals(iritem.getId());
    }

    @Override
    @NotNull
    public ItemStack getDisplay() {
        return iritem.getIcon();
    }
}
