package org.irmc.industrialrevival.api.data.sql;

import io.github.lijinhong11.mdatabase.serialization.annotations.Table;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;

/**
 * This interface is used to mark a class as a data store point.
 * The data store point is a serializable object that can be stored in the data storage.
 * <p>
 * NOTE:<br>
 * BEFORE USE IT, PLEASE ANNOTATE IT WITH {@link Table}
 * IN YOUR CLASS.
 * </p>
 *
 * @author lijinhong11
 */
public interface DataStorePoint {
    /**
     * Gets the IndustrialRevivalAddon instance associated with this data store point.
     *
     * @return the IndustrialRevivalAddon instance
     */
    IndustrialRevivalAddon getAddon();
}