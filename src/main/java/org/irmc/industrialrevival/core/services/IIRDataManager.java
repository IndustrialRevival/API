package org.irmc.industrialrevival.core.services;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.data.runtime.IRBlockData;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * Service interface for managing IndustrialRevival data in the plugin.
 * Provides methods to handle block data and player profiles.
 *
 * @author balugaq
 */
public interface IIRDataManager {
    /**
     * Gets the block data at the specified location if it exists.
     *
     * @param location the location to check
     * @return the block data at the location, or null if not found
     */
    @Nullable
    IRBlockData getBlockData(@NotNull Location location);

    /**
     * Places a block with the specified ID at the given location.
     *
     * @param loc     the location to place the block
     * @param blockID the NamespacedKey ID of the block to place
     */
    void placeBlock(@NotNull Location loc, @NotNull NamespacedKey blockID);

    /**
     * Breaks (removes) a block at the specified location.
     *
     * @param loc the location to break the block
     * @return the removed block data, or null if no block was at that location
     */
    @CanIgnoreReturnValue
    @Nullable
    IRBlockData breakBlock(@NotNull Location loc);

    /**
     * Saves all current data to storage.
     */
    void saveAllData();

    /**
     * Save block data to storage.
     */
    void saveBlock(@NotNull Location location);

    /**
     * Gets a map of all block data locations to their respective data.
     *
     * @return a map of block locations to block data
     */
    @NotNull
    Map<Location, IRBlockData> getBlockDataMap();

    /**
     * Gets a map of player names to their player profiles.
     *
     * @return a map of player names to player profiles
     */
    @NotNull Map<String, PlayerProfile> getPlayerProfiles();

    /**
     * Gets a collection of all player profiles.
     *
     * @return a collection of all player profiles
     */
    @NotNull Collection<PlayerProfile> getAllPlayerProfiles();

    /**
     * Gets a player profile by player name if it exists.
     *
     * @param playerName the name of the player to get the profile for
     * @return the player profile if found, null otherwise
     */
    @Nullable PlayerProfile getPlayerProfile(@NotNull String playerName);

    /**
     * Gets a player profile by player object.
     *
     * @param player the player to get the profile for
     * @return the player profile associated with this player
     */
    @NotNull PlayerProfile getPlayerProfile(@NotNull Player player);

    /**
     * Gets a player profile by player UUID.
     *
     * @param playerUUID the UUID of the player to get the profile for
     * @return the player profile associated with this UUID
     */
    @NotNull PlayerProfile getPlayerProfile(@NotNull UUID playerUUID);

    /**
     * Saves a player profile to storage.
     *
     * @param profile the player profile to save
     */
    void savePlayerProfile(@NotNull PlayerProfile profile);

    /**
     * Saves a player profile to storage.
     *
     * @param player the player to save
     */
    void savePlayerProfile(@NotNull Player player);

    /**
     * Requests and loads a player profile for the specified player.
     *
     * @param player the player to request a profile for
     */
    void requestPlayerProfile(@NotNull Player player);

    void close();
}