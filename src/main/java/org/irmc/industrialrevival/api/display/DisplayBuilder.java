package org.irmc.industrialrevival.api.display;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Display;
import org.bukkit.util.Vector;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.pigeonlib.objects.Pair;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for creating and positioning display entities in the game world.
 * <p>
 * This class provides a fluent API for building complex display arrangements
 * by positioning individual display entities relative to each other and to
 * a central location. It supports directional positioning using BlockFace
 * and provides methods for both full block and half block positioning.
 * </p>
 *
 * @author balugaq
 */
@Getter
public class DisplayBuilder {
    private final @NotNull List<Pair<Display, Vector>> displays;
    private BlockFace northFace;
    private Location center;
    private Vector currentOffset;

    private DisplayBuilder() {
        displays = new ArrayList<>();
    }

    /**
     * Creates a new DisplayBuilder instance.
     *
     * @return a new DisplayBuilder instance
     */
    public static @NotNull DisplayBuilder create() {
        return new DisplayBuilder();
    }

    /**
     * Gets the left direction relative to the given block face.
     *
     * @param blockFace the reference block face
     * @return the left direction relative to the given face
     */
    @Nonnull
    public static BlockFace getLeft(@NotNull BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> BlockFace.WEST;
            case SOUTH -> BlockFace.EAST;
            case EAST -> BlockFace.NORTH;
            case WEST -> BlockFace.SOUTH;
            default -> BlockFace.SELF;
        };
    }

    /**
     * Gets the right direction relative to the given block face.
     *
     * @param blockFace the reference block face
     * @return the right direction relative to the given face
     */
    @Nonnull
    public static BlockFace getRight(@NotNull BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> BlockFace.EAST;
            case SOUTH -> BlockFace.WEST;
            case EAST -> BlockFace.SOUTH;
            case WEST -> BlockFace.NORTH;
            default -> BlockFace.SELF;
        };
    }

    /**
     * Gets the opposite direction of the given block face.
     *
     * @param blockFace the reference block face
     * @return the opposite direction
     */
    @Nonnull
    public static BlockFace getOpposite(@NotNull BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> BlockFace.SOUTH;
            case SOUTH -> BlockFace.NORTH;
            case EAST -> BlockFace.WEST;
            case WEST -> BlockFace.EAST;
            case UP -> BlockFace.DOWN;
            case DOWN -> BlockFace.UP;
            default -> BlockFace.SELF;
        };
    }

    /**
     * Gets the front direction (same as the given block face).
     *
     * @param blockFace the reference block face
     * @return the front direction
     */
    @Nonnull
    public static BlockFace getFront(@NotNull BlockFace blockFace) {
        return blockFace;
    }

    /**
     * Gets the back direction (opposite of the given block face).
     *
     * @param blockFace the reference block face
     * @return the back direction
     */
    @Nonnull
    public static BlockFace getBack(@NotNull BlockFace blockFace) {
        return getOpposite(blockFace);
    }

    /**
     * Gets the vector offset for the given block face.
     *
     * @param blockFace the block face
     * @return the vector offset representing the direction
     */
    @Nonnull
    public static Vector getOffset(@NotNull BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> new Vector(0, 0, -1);
            case SOUTH -> new Vector(0, 0, 1);
            case EAST -> new Vector(1, 0, 0);
            case WEST -> new Vector(-1, 0, 0);
            default -> new Vector(0, 0, 0);
        };
    }

    /**
     * Sets the north face direction for this builder.
     * Only cardinal directions (NORTH, SOUTH, EAST, WEST) are valid.
     *
     * @param northFace the north face direction
     * @return this builder instance
     * @throws IllegalArgumentException if the block face is not a cardinal direction
     */
    public @NotNull DisplayBuilder northFace(@NotNull BlockFace northFace) {
        if (northFace.ordinal() > 3) {
            throw new IllegalArgumentException("Invalid block face: " + northFace);
        }
        this.northFace = northFace;
        return this;
    }

    /**
     * Gets the current north face direction.
     *
     * @return the north face direction
     */
    public BlockFace northFace() {
        return northFace;
    }

    /**
     * Gets the current offset vector.
     *
     * @return the current offset vector
     */
    public Vector currentOffset() {
        return currentOffset;
    }

    /**
     * Gets the list of display entities and their offsets.
     *
     * @return the list of display-offset pairs
     */
    public @NotNull List<Pair<Display, Vector>> displays() {
        return displays;
    }

    /**
     * Resets the current offset to the origin (0, 0, 0).
     *
     * @return this builder instance
     */
    public @NotNull DisplayBuilder home() {
        this.currentOffset = new Vector(0, 0, 0);
        return this;
    }

    /**
     * Sets the center location for this builder.
     *
     * @param center the center location
     * @return this builder instance
     */
    public @NotNull DisplayBuilder center(Location center) {
        this.center = center;
        return this;
    }

    /**
     * Sets the center display and adds it to the display list.
     *
     * @param display the center display
     * @return this builder instance
     */
    public @NotNull DisplayBuilder center(Display display) {
        displays.add(new Pair<>(display, new Vector(0, 0, 0)));
        return this;
    }

    /**
     * Gets the center location.
     *
     * @return the center location
     */
    public Location center() {
        return center;
    }

    /**
     * Adds a display to the left of the current position.
     *
     * @param display the display to add
     * @return this builder instance
     */
    public @NotNull DisplayBuilder leftBlock(Display display) {
        displays.add(new Pair<>(display, goLeft(1f)));
        return this;
    }

    /**
     * Adds a display to the right of the current position.
     *
     * @param display the display to add
     * @return this builder instance
     */
    public @NotNull DisplayBuilder rightBlock(Display display) {
        displays.add(new Pair<>(display, goRight(1f)));
        return this;
    }

    /**
     * Adds a display to the front of the current position.
     *
     * @param display the display to add
     * @return this builder instance
     */
    public @NotNull DisplayBuilder frontBlock(Display display) {
        displays.add(new Pair<>(display, goFront(1f)));
        return this;
    }

    /**
     * Adds a display to the back of the current position.
     *
     * @param display the display to add
     * @return this builder instance
     */
    public @NotNull DisplayBuilder backBlock(Display display) {
        displays.add(new Pair<>(display, goBack(1f)));
        return this;
    }

    /**
     * Adds a display above the current position.
     *
     * @param display the display to add
     * @return this builder instance
     */
    public @NotNull DisplayBuilder upBlock(Display display) {
        displays.add(new Pair<>(display, goUp(1f)));
        return this;
    }

    /**
     * Adds a display below the current position.
     *
     * @param display the display to add
     * @return this builder instance
     */
    public @NotNull DisplayBuilder downBlock(Display display) {
        displays.add(new Pair<>(display, goDown(1f)));
        return this;
    }

    /**
     * Adds a display to the left of the current position at half block distance.
     *
     * @param display the display to add
     * @return this builder instance
     */
    @Nonnull
    public DisplayBuilder leftHalf(Display display) {
        displays.add(new Pair<>(display, goLeft(0.5f)));
        return this;
    }

    /**
     * Adds a display to the right of the current position at half block distance.
     *
     * @param display the display to add
     * @return this builder instance
     */
    @Nonnull
    public DisplayBuilder rightHalf(Display display) {
        displays.add(new Pair<>(display, goRight(0.5f)));
        return this;
    }

    /**
     * Adds a display to the front of the current position at half block distance.
     *
     * @param display the display to add
     * @return this builder instance
     */
    public @NotNull DisplayBuilder frontHalf(Display display) {
        displays.add(new Pair<>(display, goFront(0.5f)));
        return this;
    }

    /**
     * Adds a display to the back of the current position at half block distance.
     *
     * @param display the display to add
     * @return this builder instance
     */
    public @NotNull DisplayBuilder backHalf(Display display) {
        displays.add(new Pair<>(display, goBack(0.5f)));
        return this;
    }

    /**
     * Adds a display above the current position at half block distance.
     *
     * @param display the display to add
     * @return this builder instance
     */
    public @NotNull DisplayBuilder upHalf(Display display) {
        displays.add(new Pair<>(display, goUp(0.5f)));
        return this;
    }

    /**
     * Moves the current offset to the left by the specified distance.
     *
     * @param distance the distance to move
     * @return the new offset vector
     */
    @Nonnull
    public Vector goLeft(double distance) {
        return currentOffset.add(getOffset(getLeft(northFace)).multiply(distance));
    }

    /**
     * Moves the current offset to the right by the specified distance.
     *
     * @param distance the distance to move
     * @return the new offset vector
     */
    @Nonnull
    public Vector goRight(double distance) {
        return currentOffset.add(getOffset(getRight(northFace)).multiply(distance));
    }

    /**
     * Moves the current offset to the front by the specified distance.
     *
     * @param distance the distance to move
     * @return the new offset vector
     */
    @Nonnull
    public Vector goFront(double distance) {
        return currentOffset.add(getOffset(getFront(northFace)).multiply(distance));
    }

    /**
     * Moves the current offset to the back by the specified distance.
     *
     * @param distance the distance to move
     * @return the new offset vector
     */
    @Nonnull
    public Vector goBack(double distance) {
        return currentOffset.add(getOffset(getBack(northFace)).multiply(distance));
    }

    /**
     * Moves the current offset up by the specified distance.
     *
     * @param distance the distance to move
     * @return the new offset vector
     */
    @Nonnull
    public Vector goUp(double distance) {
        return currentOffset.add(new Vector(0, distance, 0));
    }

    /**
     * Moves the current offset down by the specified distance.
     *
     * @param distance the distance to move
     * @return the new offset vector
     */
    @Nonnull
    public Vector goDown(double distance) {
        return currentOffset.add(new Vector(0, -distance, 0));
    }

    /**
     * Builds a DisplayGroup containing all the positioned displays.
     *
     * @return a DisplayGroup with all the displays positioned relative to the center
     */
    public @NotNull DisplayGroup build() {
        var group = new DisplayGroup(IRDock.getPlugin());
        for (var display : displays) {
            group.addDirectly(display.getFirst());
        }
        return group;
    }
}
