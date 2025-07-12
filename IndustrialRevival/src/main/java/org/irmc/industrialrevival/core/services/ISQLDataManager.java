package org.irmc.industrialrevival.core.services;

import org.bukkit.Location;
import org.irmc.industrialrevival.core.data.BlockRecord;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Interface for SQL data management operations.
 * Provides methods for managing block records in the database.
 */
public interface ISQLDataManager {
    /**
     * Retrieves all block records from the database.
     *
     * @return a list of all block records
     */
    @NotNull List<BlockRecord> getAllBlockRecords();

    /**
     * Saves a block record to the database.
     *
     * @param record the block record to save
     */
    void saveBlockRecord(@NotNull BlockRecord record);

    /**
     * Retrieves a block record for the specified location.
     *
     * @param loc the location to get the block record for
     * @return the block record for the specified location, or null if not found
     */
    @Nullable BlockRecord getBlockRecord(@NotNull Location loc);

    /**
     * Deletes a block record for the specified location.
     *
     * @param loc the location to delete the block record for
     */
    void deleteBlockRecord(@NotNull Location loc);
}