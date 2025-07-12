package org.irmc.industrialrevival.api.data;

import com.google.common.base.Preconditions;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A registry class for managing named {@link DataStorePoint} instances.
 * <p>
 * This class provides thread-safe operations for registering, retrieving,
 * and listing all data store points in the system.
 * </p>
 */
public class DataStorePoints {
    private static final Map<String, DataStorePoint> points = new ConcurrentHashMap<>();

    /**
     * Registers a new data store point with the specified name.
     *
     * @param name  the unique name to identify this data store point
     * @param point the data store point instance to register
     * @throws IllegalArgumentException if name is blank, null, or already exists
     * @throws IllegalArgumentException if point is null
     */
    public static void register(String name, DataStorePoint point) {
        Preconditions.checkArgument(!points.containsKey(name), "DataStorePoint with name %s already exists", name);
        Preconditions.checkArgument(!name.isBlank(), "DataStorePoint name cannot be null or blank");
        Preconditions.checkArgument(point != null, "DataStorePoint cannot be null");
        points.put(name, point);
    }

    /**
     * Retrieves a registered data store point by its name.
     *
     * @param name the name of the data store point to retrieve
     * @return the data store point instance, or null if not found
     * @throws IllegalArgumentException if name is blank or null
     */
    public static DataStorePoint get(String name) {
        Preconditions.checkArgument(!name.isBlank(), "DataStorePoint name cannot be null or blank");
        return points.get(name);
    }

    /**
     * Gets an unmodifiable collection of all registered data store points.
     *
     * @return a collection containing all registered data store points
     */
    public static Collection<DataStorePoint> getAll() {
        return Collections.unmodifiableCollection(points.values());
    }
}
