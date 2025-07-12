package org.irmc.industrialrevival.core.services;

import org.bukkit.Location;
import org.irmc.industrialrevival.core.data.BlockRecord;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ISQLDataManager {
    @NotNull List<BlockRecord> getAllBlockRecords();
    void saveBlockRecord(@NotNull BlockRecord record);
    @Nullable BlockRecord getBlockRecord(@NotNull Location loc);
    @Nullable BlockRecord deleteBlockRecord(@NotNull Location loc);
}
