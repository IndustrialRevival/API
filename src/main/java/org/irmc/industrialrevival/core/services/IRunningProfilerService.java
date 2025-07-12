package org.irmc.industrialrevival.core.services;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.ProfiledBlock;
import org.irmc.industrialrevival.api.objects.ChunkPosition;
import org.irmc.industrialrevival.api.objects.TimingViewRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Map;

public interface IRunningProfilerService {
    void requestTimingView(@NotNull TimingViewRequest request);

    @Nullable TimingViewRequest pullTimingViewRequest();

    void respondToTimingView(@Nullable TimingViewRequest request);

    @NotNull Map<ProfiledBlock, Long> getProfilingData();

    @Nullable Map.Entry<ProfiledBlock, Long> getProfilingData(@NotNull Location location);

    @NotNull Map<NamespacedKey, Long> getProfilingDataByID();

    @NotNull Map<ChunkPosition, Long> getProfilingDataByChunk();

    @NotNull Map<String, Long> getProfilingDataByPlugin();

    @NotNull Map<ProfiledBlock, Long> getProfilingDataByID(@NotNull NamespacedKey id);

    @NotNull Map<ProfiledBlock, Long> getProfilingDataByChunk(@NotNull ChunkPosition chunkPosition);

    @NotNull Map<ProfiledBlock, Long> getProfilingDataByPlugin(@NotNull String pluginName);

    @Range(from = 0, to = Integer.MAX_VALUE)
    int getTotalMachine(@NotNull NamespacedKey id);

    void clearProfilingData();

    void startProfiling(@NotNull Location location);

    void stopProfiling(@NotNull Location location);
}
