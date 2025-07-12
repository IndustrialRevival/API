package org.irmc.industrialrevival.core.services;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * Interface for IndustrialRevival data management.
 * Provides methods for managing block data and player profiles.
 */
public interface IIRDataManager {
    /**
     * Gets the block data at the specified location.
     *
     * @param location the location to check
     * @return the block data at the specified location, or null if none exists
     */
    @Nullable
    IRBlockData getBlockData(@NotNull Location location);

    /**
     * Places a block with the specified ID at the given location.
     *
     * @param loc the location to place the block
     * @param blockID the ID of the block to place
     */
    void placeBlock(@NotNull Location loc, @NotNull NamespacedKey blockID);

    /**
     * Breaks a block at the specified location.
     *
     * @param loc the location of the block to break
     * @return the block data of the broken block, or null if none existed
     */
    @CanIgnoreReturnValue
    @Nullable
    IRBlockData breakBlock(@NotNull Location loc);

    /**
     * Saves all data to persistent storage.
     */
    void saveAllData();

    /**
     * Gets the map of all block data.
     *
     * @return a map of locations to block data
     */
    @NotNull
    Map<Location, IRBlockData> getBlockDataMap();

    /**
     * Gets all player profiles by their string identifier.
     *
     * @return a map of player profile string identifiers to profiles
     */
    @NotNull
    Map<String, PlayerProfile> getPlayerProfiles();

    /**
     * Gets all player profiles as a collection.
     *
     * @return a collection of all player profiles
     */
    @NotNull Collection<PlayerProfile> getAllPlayerProfiles();

    /**
     * Gets a player profile by player name.
     *
     * @param playerName the name of the player
     * @return the player profile, or null if not found
     */
    @Nullable PlayerProfile getPlayerProfile(@NotNull String playerName);

    /**
     * Gets a player profile for the specified player.
     *
     * @param player the player to get the profile for
     * @return the player profile
     */
    @NotNull PlayerProfile getPlayerProfile(@NotNull Player player);

    /**
     * Gets a player profile by player UUID.
     *
     * @param playerUUID the UUID of the player
     * @return the player profile
     */
    @NotNull PlayerProfile getPlayerProfile(@NotNull UUID playerUUID);

    /**
     * Saves a player profile to persistent storage.
     *
     * @param profile the player profile to save
     */
    void savePlayerProfile(@NotNull PlayerProfile profile);

    /**
     * Requests a player profile for the specified player.
     *
     * @param player the player to request the profile for
     */
    void requestPlayerProfile(@NotNull Player player);
}