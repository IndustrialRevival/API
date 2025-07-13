package org.irmc.industrialrevival.api.data.sql;

import io.github.lijinhong11.mdatabase.serialization.annotations.Column;
import io.github.lijinhong11.mdatabase.serialization.annotations.PrimaryKey;
import io.github.lijinhong11.mdatabase.serialization.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.data.runtime.IRBlockData;
import org.jetbrains.annotations.ApiStatus;

import java.io.StringReader;

/**
 * BlockRecord class represents a record of block data in the database.
 *
 * @author lijinhong11
 */
@Table(name = "blocks")
@NoArgsConstructor
@AllArgsConstructor
@ApiStatus.Internal
public class BlockRecord {
    /**
     * Unique identifier of the block.
     */
    @Column
    private String id;

    /**
     * World name where the block is located.
     */
    @Column
    @PrimaryKey
    private String world;

    /**
     * X coordinate of the block's location.
     */
    @Column
    @PrimaryKey
    private int x;

    /**
     * Y coordinate of the block's location.
     */
    @Column
    @PrimaryKey
    private int y;

    /**
     * Z coordinate of the block's location.
     */
    @Column
    @PrimaryKey
    private int z;

    /**
     * Data associated with the block, stored as a string.
     */
    @Column
    private String data;

    /**
     * Creates a new BlockRecord instance from a Location and NamespacedKey.
     *
     * @param location The location of the block.
     * @param id       The unique identifier of the block.
     * @return A BlockRecord instance.
     */
    public static BlockRecord warp(Location location, NamespacedKey id) {
        return new BlockRecord(id.toString(), location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), "");
    }

    /**
     * Creates a new BlockRecord instance from an IRBlockData object.
     *
     * @param blockData The block data to convert.
     * @return A BlockRecord instance.
     */
    public static BlockRecord warp(IRBlockData blockData) {
        return new BlockRecord(blockData.getId().toString(), blockData.getLocation().getWorld().getName(), blockData.getLocation().getBlockX(), blockData.getLocation().getBlockY(), blockData.getLocation().getBlockZ(), blockData.getData().saveToString());
    }

    /**
     * Gets the location of the block.
     *
     * @return The block's location.
     */
    public Location getLocation() {
        World bk = Bukkit.getWorld(world);
        return bk == null ? new Location(null, x, y, z) : new Location(bk, x, y, z);
    }

    /**
     * Sets the location of the block based on a given Location object.
     *
     * @param location The new location of the block.
     */
    public void setLocation(Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }

    /**
     * Gets the machine ID associated with the block.
     *
     * @return The machine ID as a NamespacedKey.
     */
    public NamespacedKey getMachineId() {
        return NamespacedKey.fromString(id);
    }

    /**
     * Gets the configuration section representing the block's data.
     *
     * @return The block data as a ConfigurationSection.
     */
    public ConfigurationSection getData() {
        StringReader reader = new StringReader(data);
        return YamlConfiguration.loadConfiguration(reader);
    }
}