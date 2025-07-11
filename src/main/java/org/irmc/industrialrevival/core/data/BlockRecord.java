package org.irmc.industrialrevival.core.data;

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
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.jetbrains.annotations.ApiStatus;

import java.io.StringReader;

@Table(name = "blocks")
@NoArgsConstructor
@AllArgsConstructor
@ApiStatus.Internal
public class BlockRecord {
    @Column
    private String id;

    @Column
    @PrimaryKey
    private String world;

    @Column
    @PrimaryKey
    private int x;

    @Column
    @PrimaryKey
    private int y;

    @Column
    @PrimaryKey
    private int z;

    @Column
    private String data;

    public static BlockRecord warp(Location location, NamespacedKey id) {
        return new BlockRecord(id.toString(), location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), "");
    }

    public static BlockRecord warp(IRBlockData blockData) {
        return new BlockRecord(blockData.getId().toString(), blockData.getLocation().getWorld().getName(), blockData.getLocation().getBlockX(), blockData.getLocation().getBlockY(), blockData.getLocation().getBlockZ(), blockData.getData().saveToString());
    }

    public Location getLocation() {
        World bk = Bukkit.getWorld(world);
        return bk == null ? new Location(null, x, y, z) : new Location(bk, x, y, z);
    }

    public void setLocation(Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }

    public NamespacedKey getMachineId() {
        return NamespacedKey.fromString(id);
    }

    public ConfigurationSection getData() {
        StringReader reader = new StringReader(data);
        return YamlConfiguration.loadConfiguration(reader);
    }
}
