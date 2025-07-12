/**
 * Interface for running profiler operations.
 * Provides methods for profiling and monitoring system performance.
 */
public interface IRunningProfilerService {
    /**
     * Requests a timing view for performance analysis.
     *
     * @param request the timing view request containing configuration parameters
     */
    void requestTimingView(@NotNull TimingViewRequest request);

    /**
     * Retrieves and removes the current timing view request.
     *
     * @return the current timing view request, or null if none exists
     */
    @Nullable TimingViewRequest pullTimingViewRequest();

    /**
     * Responds to a timing view request with the collected data.
     *
     * @param request the timing view request to respond to
     */
    void respondToTimingView(@Nullable TimingViewRequest request);

    /**
     * Retrieves all profiling data.
     *
     * @return a map of profiled blocks to their execution times
     */
    @NotNull Map<ProfiledBlock, Long> getProfilingData();

    /**
     * Retrieves profiling data for a specific location.
     *
     * @param location the location to get profiling data for
     * @return a map entry containing the profiled block and its execution time, or null if not found
     */
    @Nullable Map.Entry<ProfiledBlock, Long> getProfilingData(@NotNull Location location);

    /**
     * Retrieves all profiling data indexed by NamespacedKey.
     *
     * @return a map of NamespacedKeys to total execution times
     */
    @NotNull Map<NamespacedKey, Long> getProfilingDataByID();

    /**
     * Retrieves all profiling data indexed by chunk position.
     *
     * @return a map of chunk positions to total execution times
     */
    @NotNull Map<ChunkPosition, Long> getProfilingDataByChunk();

    /**
     * Retrieves all profiling data indexed by plugin name.
     *
     * @return a map of plugin names to total execution times
     */
    @NotNull Map<String, Long> getProfilingDataByPlugin();

    /**
     * Retrieves profiling data for a specific NamespacedKey.
     *
     * @param id the NamespacedKey to get data for
     * @return a map of profiled blocks to execution times for the specified key
     */
    @NotNull Map<ProfiledBlock, Long> getProfilingDataByID(@NotNull NamespacedKey id);

    /**
     * Retrieves profiling data for a specific chunk position.
     *
     * @param chunkPosition the chunk position to get data for
     * @return a map of profiled blocks to execution times for the specified chunk
     */
    @NotNull Map<ProfiledBlock, Long> getProfilingDataByChunk(@NotNull ChunkPosition chunkPosition);

    /**
     * Retrieves profiling data for a specific plugin.
     *
     * @param pluginName the name of the plugin to get data for
     * @return a map of profiled blocks to execution times for the specified plugin
     */
    @NotNull Map<ProfiledBlock, Long> getProfilingDataByPlugin(@NotNull String pluginName);

    /**
     * Gets the total number of machines of a specific type.
     *
     * @param id the NamespacedKey identifying the machine type
     * @return the total number of machines of the specified type
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
}