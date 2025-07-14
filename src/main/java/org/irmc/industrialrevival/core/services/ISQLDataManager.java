package org.irmc.industrialrevival.core.services;

import org.bukkit.Location;
import org.irmc.industrialrevival.api.data.sql.BlockRecord;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Interface for SQL data management operations.
 * Provides methods for managing block records in the database.
 *
 * @author balugaq
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
     * Retrieves a block record by its location.
     *
     * @param loc the location to search for
     * @return the block record at the given location, or null if not found
     */
    @Nullable BlockRecord getBlockRecord(@NotNull Location loc);

    /**
     * Deletes a block record by its location.
     *
     * @param loc the location of the block record to delete
     */
    void deleteBlockRecord(@NotNull Location loc);

    void close();

    void init();
}