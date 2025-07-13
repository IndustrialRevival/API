package org.irmc.industrialrevival.api.display;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.display.builder.AbstractModelBuilder;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A group of display entities that can be managed collectively.
 * <p>
 * This class provides functionality to manage multiple display entities as a single unit.
 * It allows adding displays with specific offsets from a center location, and provides
 * methods to show, hide, or remove all displays in the group simultaneously.
 * </p>
 * <p>
 * Each display in the group is tagged with metadata to identify which addon created it,
 * allowing for proper cleanup and management.
 * </p>
 *
 * @author balugaq
 */
@Getter
public class DisplayGroup {
    private static final String DISPLAY_GROUP_METADATA_KEY = KeyUtil.customKey("display_group").toString();
    private final @NotNull IndustrialRevivalAddon addon;
    private final List<Display> displays = new ArrayList<>();
    private Location center;

    /**
     * Constructs a new DisplayGroup for the specified addon.
     *
     * @param addon the addon that owns this display group
     * @throws UnsupportedOperationException if the addon's plugin is not enabled
     */
    public DisplayGroup(@NotNull IndustrialRevivalAddon addon) {
        if (!addon.getPlugin().isEnabled()) {
            throw new UnsupportedOperationException("Plugin is not enabled");
        }
        this.addon = addon;
    }

    /**
     * Sets the center location for this display group.
     * The center is adjusted to the center of the block (adding 0.5 to each coordinate).
     *
     * @param location the center location
     * @return this display group instance
     */
    @CanIgnoreReturnValue
    public @NotNull DisplayGroup center(@NotNull Location location) {
        this.center = location.clone().add(0.5D, 0.5D, 0.5D);
        return this;
    }

    /**
     * Adds a display to this group at the center location.
     *
     * @param display the display to add
     * @return this display group instance
     */
    @CanIgnoreReturnValue
    public @NotNull DisplayGroup add(@NotNull Display display) {
        return add(display, 0.0D, 0.0D, 0.0D);
    }

    /**
     * Adds a display to this group with the specified offset from the center.
     *
     * @param display the display to add
     * @param x the x offset
     * @param y the y offset
     * @param z the z offset
     * @return this display group instance
     */
    @CanIgnoreReturnValue
    public @NotNull DisplayGroup add(@NotNull Display display, double x, double y, double z) {
        return add(display, new Vector(x, y, z));
    }

    /**
     * Adds a display to this group with the specified offset vector from the center.
     *
     * @param display the display to add
     * @param offset the offset vector
     * @return this display group instance
     * @throws UnsupportedOperationException if the center location is not set
     */
    @CanIgnoreReturnValue
    public @NotNull DisplayGroup add(@NotNull Display display, @NotNull Vector offset) {
        if (center == null) {
            throw new UnsupportedOperationException("Center location is not set");
        }

        display.teleport(center.clone().add(offset.getX(), offset.getY(), offset.getZ()));
        // When add display to a display group, set metadata to the display to identify its addons
        display.setMetadata(DISPLAY_GROUP_METADATA_KEY, new FixedMetadataValue(IRDock.getPlugin(), addon.getPlugin().getName()));
        return this;
    }

    /**
     * Adds multiple text displays directly to this group without positioning them.
     *
     * @param displays the collection of text displays to add
     * @return this display group instance
     */
    @CanIgnoreReturnValue
    public @NotNull DisplayGroup addDirectly(@NotNull Collection<TextDisplay> displays) {
        for (Display display : displays) {
            addDirectly(display);
        }
        return this;
    }

    /**
     * Adds a display directly to this group without positioning it.
     * The display is only tagged with metadata and added to the internal list.
     *
     * @param display the display to add
     * @return this display group instance
     * @throws UnsupportedOperationException if the center location is not set
     */
    @CanIgnoreReturnValue
    public @NotNull DisplayGroup addDirectly(@NotNull Display display) {
        if (center == null) {
            throw new UnsupportedOperationException("Center location is not set");
        }

        // When add display to a display group, set metadata to the display to identify its addons
        display.setMetadata(DISPLAY_GROUP_METADATA_KEY, new FixedMetadataValue(IRDock.getPlugin(), addon.getPlugin().getName()));
        return this;
    }

    /**
     * Adds a display created from a model builder at the center location.
     *
     * @param modelBuilder the model builder to create the display from
     * @return this display group instance
     */
    @CanIgnoreReturnValue
    public @NotNull DisplayGroup add(@NotNull AbstractModelBuilder modelBuilder) {
        return add(modelBuilder, 0.0D, 0.0D, 0.0D);
    }

    /**
     * Adds a display created from a model builder with the specified offset.
     *
     * @param modelBuilder the model builder to create the display from
     * @param x the x offset
     * @param y the y offset
     * @param z the z offset
     * @return this display group instance
     */
    @CanIgnoreReturnValue
    public @NotNull DisplayGroup add(@NotNull AbstractModelBuilder modelBuilder, double x, double y, double z) {
        return add(modelBuilder, new Vector(x, y, z));
    }

    /**
     * Adds a display created from a model builder with the specified offset vector.
     *
     * @param modelBuilder the model builder to create the display from
     * @param offset the offset vector
     * @return this display group instance
     * @throws UnsupportedOperationException if the center location is not set
     */
    @CanIgnoreReturnValue
    public @NotNull DisplayGroup add(@NotNull AbstractModelBuilder modelBuilder, @NotNull Vector offset) {
        if (center == null) {
            throw new UnsupportedOperationException("Center location is not set");
        }

        Display display = modelBuilder.buildAt(center.clone().add(offset.getX(), offset.getY(), offset.getZ()));
        if (display == null) {
            return this;
        }
        // When add display to a display group, set metadata to the display to identify its addons
        display.setMetadata(DISPLAY_GROUP_METADATA_KEY, new FixedMetadataValue(IRDock.getPlugin(), addon.getPlugin().getName()));
        return add(display);
    }

    /**
     * Hides all displays in this group by setting them to invisible.
     *
     * @return this display group instance
     */
    @CanIgnoreReturnValue
    public @NotNull DisplayGroup hide() {
        for (Display display : displays) {
            display.setInvisible(true);
        }
        return this;
    }

    /**
     * Shows all displays in this group by setting them to visible.
     *
     * @return this display group instance
     */
    @CanIgnoreReturnValue
    public @NotNull DisplayGroup show() {
        for (Display display : displays) {
            display.setInvisible(false);
        }
        return this;
    }

    /**
     * Removes all displays in this group from the world and clears the internal list.
     *
     * @return this display group instance
     */
    @CanIgnoreReturnValue
    public @NotNull DisplayGroup remove() {
        for (Display display : displays) {
            display.remove();
        }
        displays.clear();
        return this;
    }
}
