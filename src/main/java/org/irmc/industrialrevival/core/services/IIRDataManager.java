package org.irmc.industrialrevival.core.services;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.data.BlockRecord;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IIRDataManager {
    @Nullable
    IRBlockData getBlockData(@NotNull Location location);

    void placeBlock(@NotNull Location loc, @NotNull NamespacedKey blockID);

    @CanIgnoreReturnValue
    @Nullable
    IRBlockData breakBlock(@NotNull Location loc);

    void saveAllData();

    @NotNull
    Map<Location, IRBlockData> getBlockDataMap();

    @NotNull Map<String, PlayerProfile> getPlayerProfiles();
    @NotNull Collection<PlayerProfile> getAllPlayerProfiles();
    @Nullable PlayerProfile getPlayerProfile(@NotNull String playerName);
    @NotNull PlayerProfile getPlayerProfile(@NotNull Player player);
    @NotNull PlayerProfile getPlayerProfile(@NotNull UUID playerUUID);
    void savePlayerProfile(@NotNull PlayerProfile profile);
    void requestPlayerProfile(@NotNull Player player);
}
