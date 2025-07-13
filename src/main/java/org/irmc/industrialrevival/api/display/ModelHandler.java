package org.irmc.industrialrevival.api.display;

import lombok.Getter;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Handles the management of display groups at specific locations.
 * <p>
 * This class provides functionality to store and manage display groups
 * associated with specific locations in the world. It can automatically
 * remove old display groups when new ones are rendered at the same location,
 * preventing display overlap and memory leaks.
 * </p>
 *
 * @author baluagq
 */
@Getter
public class ModelHandler {
    private final Map<Location, DisplayGroup> models;
    private boolean removeOldWhenRenderNew = true;

    /**
     * Constructs a new ModelHandler with default settings.
     * By default, old display groups are removed when new ones are rendered.
     */
    public ModelHandler() {
        this.models = new HashMap<>();
    }

    /**
     * Sets whether old display groups should be removed when new ones are rendered.
     *
     * @param removeOldWhenRenderNew true to remove old groups, false to keep them
     * @return this ModelHandler instance
     */
    public ModelHandler removeOldWhenRenderNew(boolean removeOldWhenRenderNew) {
        this.removeOldWhenRenderNew = removeOldWhenRenderNew;
        return this;
    }

    /**
     * Adds a display group to this handler at the specified location.
     * If removeOldWhenRenderNew is true and there's already a display group
     * at the location, the old one will be removed first.
     *
     * @param location the location to add the display group at
     * @param displayGroup the display group to add
     * @return this ModelHandler instance
     */
    public ModelHandler addDisplayGroup(Location location, DisplayGroup displayGroup) {
        if (removeOldWhenRenderNew) {
            Optional.ofNullable(models.get(location)).ifPresent(DisplayGroup::remove);
        }
        models.put(location, displayGroup);
        return this;
    }
}
