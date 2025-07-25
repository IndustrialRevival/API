package org.irmc.industrialrevival.api.data.runtime;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.data.sql.BlockRecord;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.industrialrevival.utils.DataUtil;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Describer of a {@link IndustrialRevivalItem} blocks
 * <p>
 * A block data object is used to store additional data about a block that is not
 * stored in the block itself. This data is stored in a YAML configuration file
 * and can be accessed using the {@link #config} field.
 * </p>
 *
 * <p>
 * This class provides methods to:
 * <ul>
 *     <li>Access block metadata through {@link #getMapData()}</li>
 *     <li>Create instances from database records via {@link #warp(BlockRecord)}</li>
 *     <li>Store location-specific machine information</li>
 * </ul>
 * </p>
 *
 * @author lijinhong11
 * @see IndustrialRevivalItem
 * @see MachineMenu
 * @see DataUtil
 */
@Getter
public class IRBlockData {
    private final NamespacedKey id;

    @ApiStatus.Experimental
    private final ConfigurationSection config;

    @Nullable
    private final MachineMenu machineMenu;

    private final Location location;
    private final YamlConfiguration data = new YamlConfiguration();

    @ApiStatus.Internal
    public IRBlockData(NamespacedKey id, Location location, @NotNull ConfigurationSection config, @Nullable MachineMenu menu) {
        this.id = id;
        this.location = location;
        this.config = config;
        this.machineMenu = menu;
    }

    public static IRBlockData warp(BlockRecord record) {
        var loc = record.getLocation();
        return new IRBlockData(
                record.getMachineId(),
                loc,
                record.getData(),
                new MachineMenu(loc, IRDock.getRegistry().getMenuPresets().get(record.getMachineId()))
        );
    }

    public Map<String, String> getMapData() {
        Map<String, String> map = new HashMap<>();
        for (var key : data.getKeys(false)) {
            map.put(key, data.getString(key));
        }

        return map;
    }
}
