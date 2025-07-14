package org.irmc.industrialrevival.core.services;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.timings.PerformanceSummary;
import org.irmc.industrialrevival.api.timings.ProfiledBlock;
import org.irmc.industrialrevival.api.objects.ChunkPosition;
import org.irmc.industrialrevival.api.timings.TimingViewRequest;
import org.irmc.industrialrevival.core.task.TickerTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Map;

/**
 * Interface for running profiler service.
 * Provides methods for profiling and analyzing performance data of blocks/machines.
 *
 * @author balugaq
 */
public interface IRunningProfilerService {
    /**
     * Requests a timing view for performance analysis.
     *
     * @param request the timing view request containing parameters
     */
    void requestTimingView(@NotNull TimingViewRequest request);

    /**
     * Pulls (retrieves and removes) the latest timing view request.
     *
     * @return the latest timing view request, or null if none exists
     */
    @Nullable TimingViewRequest pullTimingViewRequest();

    /**
     * Responds to a timing view request by providing results.
     *
     * @param request the timing view request to respond to
     */
    void respondToTimingView(@Nullable TimingViewRequest request);

    /**
     * Gets all profiling data collected so far.
     *
     * @return a map of profiled blocks to their execution times
     */
    @NotNull Map<ProfiledBlock, Long> getProfilingData();

    /**
     * Gets profiling data for a specific location.
     *
     * @param location the location to get data for
     * @return a map entry containing the profiled block and its execution time, or null if not found
     */
    @Nullable Map.Entry<ProfiledBlock, Long> getProfilingData(@NotNull Location location);

    /**
     * Gets profiling data grouped by NamespacedKey identifier.
     *
     * @return a map of NamespacedKey identifiers to total execution times
     */
    @NotNull Map<NamespacedKey, Long> getProfilingDataByID();

    /**
     * Gets profiling data grouped by chunk position.
     *
     * @return a map of chunk positions to total execution times
     */
    @NotNull Map<ChunkPosition, Long> getProfilingDataByChunk();

    /**
     * Gets profiling data grouped by plugin name.
     *
     * @return a map of plugin names to total execution times
     */
    @NotNull Map<String, Long> getProfilingDataByPlugin();

    /**
     * Gets profiling data filtered by a specific NamespacedKey identifier.
     *
     * @param id the NamespacedKey identifier to filter by
     * @return a map of profiled blocks to their execution times for the given ID
     */
    @NotNull Map<ProfiledBlock, Long> getProfilingDataByID(@NotNull NamespacedKey id);

    /**
     * Gets profiling data filtered by a specific chunk position.
     *
     * @param chunkPosition the chunk position to filter by
     * @return a map of profiled blocks to their execution times for the given chunk
     */
    @NotNull Map<ProfiledBlock, Long> getProfilingDataByChunk(@NotNull ChunkPosition chunkPosition);

    /**
     * Gets profiling data filtered by a specific plugin name.
     *
     * @param pluginName the plugin name to filter by
     * @return a map of profiled blocks to their execution times for the given plugin
     */
    @NotNull Map<ProfiledBlock, Long> getProfilingDataByPlugin(@NotNull String pluginName);

    /**
     * Gets the total count of machines for a specific NamespacedKey identifier.
     *
     * @param id the NamespacedKey identifier to count machines for
     * @return the number of machines for the given ID, between 0 and Integer.MAX_VALUE
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getTotalMachine(@NotNull NamespacedKey id);

    /**
     * Clears all collected profiling data.
     */
    void clearProfilingData();

    /**
     * Starts profiling at the specified location.
     *
     * @param location the location to start profiling at
     */
    void startProfiling(@NotNull Location location);

    /**
     * Stops profiling at the specified location.
     *
     * @param location the location to stop profiling at
     */
    void stopProfiling(@NotNull Location location);

    @NotNull
    TickerTask getTask();

    @NotNull
    PerformanceSummary getSummary();
}