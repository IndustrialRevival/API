package org.irmc.industrialrevival.api.display;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;

/**
 * Represents a rectangular area in 3D space with defined minimum and maximum coordinates.
 * This class is used to describe the boundaries of objects in a Minecraft world.
 *
 * @author balugaq
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Corners {
    private final float minX;
    private final float maxX;
    private final float minY;
    private final float maxY;
    private final float minZ;
    private final float maxZ;
    public WeakReference<World> world;

    /**
     * Constructs a new Corners instance with specified boundaries.
     *
     * @param world reference to the Minecraft world
     * @param minX minimum X coordinate
     * @param maxX maximum X coordinate
     * @param minY minimum Y coordinate
     * @param maxY maximum Y coordinate
     * @param minZ minimum Z coordinate
     * @param maxZ maximum Z coordinate
     */
    public Corners(WeakReference<World> world, float minX, float maxX, float minY, float maxY, float minZ, float maxZ) {
        this.world = world;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
    }

    /**
     * Constructs a new Corners instance with specified boundaries.
     *
     * @param world the Minecraft world
     * @param minX minimum X coordinate
     * @param maxX maximum X coordinate
     * @param minY minimum Y coordinate
     * @param maxY maximum Y coordinate
     * @param minZ minimum Z coordinate
     * @param maxZ maximum Z coordinate
     */
    public Corners(World world, float minX, float maxX, float minY, float maxY, float minZ, float maxZ) {
        this.world = new WeakReference<>(world);
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
    }

    /**
     * Creates a Corners instance representing a single block.
     *
     * @param block the block to create corners for
     * @return a Corners instance representing the block's boundaries
     */
    public static Corners of(Block block) {
        return of(block.getLocation()).merge(of(block.getLocation().clone().add(1, 1, 1)));
    }

    /**
     * Creates a Corners instance representing a single location point.
     *
     * @param location the location to create corners for
     * @return a Corners instance representing the location as a point
     */
    public static Corners of(Location location) {
        return Corners.builder()
                .world(new WeakReference<>(location.getWorld()))
                .minX((float) location.getX())
                .maxX((float) location.getX())
                .minY((float) location.getY())
                .maxY((float) location.getY())
                .minZ((float) location.getZ())
                .maxZ((float) location.getZ())
                .build();
    }

    /**
     * Creates a Corners instance representing the area between two blocks.
     *
     * @param block1 the first block
     * @param block2 the second block
     * @return a Corners instance representing the area between the two blocks
     */
    public static Corners of(Block block1, Block block2) {
        return of(block1).merge(of(block2));
    }

    /**
     * Creates a Corners instance representing the area between two locations.
     *
     * @param location1 the first location
     * @param location2 the second location
     * @return a Corners instance representing the area between the two locations
     */
    public static Corners of(Location location1, Location location2) {
        return of(location1).merge(of(location2));
    }

    /**
     * Merges this Corners instance with another to create a new instance
     * that encompasses both areas.
     *
     * @param corners the Corners instance to merge with
     * @return a new Corners instance representing the combined area
     */
    public Corners merge(Corners corners) {
        return Corners.builder()
                .world(world)
                .minX(Math.min(minX, corners.minX))
                .maxX(Math.max(maxX, corners.maxX))
                .minY(Math.min(minY, corners.minY))
                .maxY(Math.max(maxY, corners.maxY))
                .minZ(Math.min(minZ, corners.minZ))
                .maxZ(Math.max(maxZ, corners.maxZ))
                .build();
    }

    /**
     * Gets the world reference, which may be null if the world has been garbage collected.
     *
     * @return the world reference, or null if the world is no longer available
     */
    public @Nullable World getWorld() {
        return world.get();
    }

    /**
     * Calculates the distance between the minimum and maximum X coordinates.
     *
     * @return the absolute difference between maxX and minX
     */
    public float getDistanceX() {
        return Math.abs(maxX - minX);
    }

    /**
     * Calculates the distance between the minimum and maximum Y coordinates.
     *
     * @return the absolute difference between maxY and minY
     */
    public float getDistanceY() {
        return Math.abs(maxY - minY);
    }

    /**
     * Calculates the distance between the minimum and maximum Z coordinates.
     *
     * @return the absolute difference between maxZ and minZ
     */
    public float getDistanceZ() {
        return Math.abs(maxZ - minZ);
    }

    /**
     * Calculates the center point of this area.
     *
     * @return a Location object representing the center of this area
     */
    public Location center() {
        return new Location(getWorld(), (minX + maxX) / 2, (minY + maxY) / 2, (minZ + maxZ) / 2);
    }
}
