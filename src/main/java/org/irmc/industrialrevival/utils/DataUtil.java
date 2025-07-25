package org.irmc.industrialrevival.utils;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.data.runtime.IRBlockData;
import org.irmc.industrialrevival.dock.IRDock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This utility class provides methods for managing block data and PersistentDataContainer (PDC) operations
 * within the IndustrialRevival plugin. It includes functionalities for retrieving, setting, and removing
 * block data, as well as managing PDC values.
 *
 * @author balugaq
 */
@Deprecated
@UtilityClass
@SuppressWarnings("unused")
public class DataUtil {

    /**
     * Retrieves the IndustrialRevivalItem associated with the block at the specified location.
     *
     * @param location The location of the block.
     * @return The IndustrialRevivalItem associated with the block, or null if no item is found.
     */
    @Nullable
    public static IndustrialRevivalItem getItem(@NotNull Location location) {
        IRBlockData blockData = getBlockData(location);
        if (blockData == null) {
            return null;
        }
        return IndustrialRevivalItem.getById(blockData.getId());
    }

    /**
     * Retrieves the IRBlockData associated with the block at the specified location.
     *
     * @param location The location of the block.
     * @return The IRBlockData associated with the block, or null if no data is found.
     */
    @Nullable
    public static IRBlockData getBlockData(@NotNull Location location) {
        return IRDock.getPlugin().getDataManager().getBlockData(location);
    }

    /**
     * Sets a key-value pair in the block's configuration data at the specified location.
     *
     * @param location The location of the block.
     * @param key      The key to set.
     * @param value    The value to associate with the key.
     */
    public static void setData(@NotNull Location location, @NotNull String key, String value) {
        getBlockData(location).getConfig().set(key, value);
    }

    /**
     * Retrieves the value associated with the specified key from the block's configuration data.
     *
     * @param location The location of the block.
     * @param key      The key to retrieve the value for.
     * @return The value associated with the key, or null if the key does not exist.
     */
    @Nullable
    public static String getData(@NotNull Location location, @NotNull String key) {
        return getBlockData(location).getConfig().getString(key);
    }

    /**
     * Checks if the block's configuration data contains the specified key.
     *
     * @param location The location of the block.
     * @param key      The key to check.
     * @return True if the key exists, false otherwise.
     */
    public static boolean hasData(@NotNull Location location, @NotNull String key) {
        return getData(location, key) != null;
    }

    /**
     * Associates the specified IRBlockData with the block at the given location.
     *
     * @param location  The location of the block.
     * @param blockData The IRBlockData to associate with the block.
     */
    public static void setBlockData(Location location, IRBlockData blockData) {
        IRDock.getPlugin().getDataManager().getBlockDataMap().put(location, blockData);
    }

    /**
     * Checks if the block at the specified location has associated IRBlockData.
     *
     * @param location The location of the block.
     * @return True if IRBlockData exists, false otherwise.
     */
    public static boolean hasBlockData(@NotNull Location location) {
        return getBlockData(location) != null;
    }

    /**
     * Removes the IRBlockData associated with the block at the specified location.
     *
     * @param location The location of the block.
     * @return The removed IRBlockData, or null if no data was found.
     */
    @CanIgnoreReturnValue
    @Nullable
    public static IRBlockData removeBlockData(@NotNull Location location) {
        IRDock.getPlugin().getSQLDataManager().deleteBlockRecord(location);
        return IRDock.getPlugin().getDataManager().breakBlock(location);
    }

    /**
     * Removes the value associated with the specified key from the block's configuration data.
     *
     * @param location The location of the block.
     * @param key      The key to remove.
     */
    public static void removeData(@NotNull Location location, @NotNull String key) {
        setData(location, key, null);
    }

    /**
     * Retrieves the MachineMenu associated with the block at the specified location.
     *
     * @param location The location of the block.
     * @return The MachineMenu associated with the block, or null if no menu is found.
     */
    @Nullable
    public static MachineMenu getMachineMenu(@NotNull Location location) {
        return getBlockData(location).getMachineMenu();
    }

    /**
     * Retrieves the MachineMenu associated with the specified block.
     *
     * @param block The block to retrieve the menu for.
     * @return The MachineMenu associated with the block, or null if no menu is found.
     */
    @Nullable
    public static MachineMenu getMachineMenu(@NotNull Block block) {
        return getBlockData(block.getLocation()).getMachineMenu();
    }

    /**
     * Checks if the block at the specified location has an associated MachineMenu.
     *
     * @param location The location of the block.
     * @return True if a MachineMenu exists, false otherwise.
     */
    public static boolean hasMachineMenu(@NotNull Location location) {
        return getMachineMenu(location) != null;
    }

    /**
     * Checks if the specified block has an associated MachineMenu.
     *
     * @param block The block to check.
     * @return True if a MachineMenu exists, false otherwise.
     */
    public static boolean hasMachineMenu(@NotNull Block block) {
        return hasMachineMenu(block.getLocation());
    }

    /**
     * Retrieves the MachineMenuPreset associated with the specified ID.
     *
     * @param id The ID of the MachineMenuPreset.
     * @return The MachineMenuPreset associated with the ID, or null if no preset is found.
     */
    @Nullable
    public static MachineMenuPreset getMachineMenuPresetById(NamespacedKey id) {
        return IRDock.getPlugin().getRegistry().getMenuPresets().get(id);
    }

    /**
     * Checks if the block at the specified location matches the given ID.
     *
     * @param location The location of the block.
     * @param id       The ID to compare against.
     * @return True if the block's ID matches the specified ID, false otherwise.
     */
    public static boolean isBlock(@NotNull Location location, NamespacedKey id) {
        return getBlockData(location).getId().equals(id);
    }

    /**
     * Sets a key-value pair in the PersistentDataContainer (PDC) of the specified holder.
     *
     * @param holder The PersistentDataHolder to set the data in.
     * @param key    The key to set.
     * @param value  The value to associate with the key.
     */
    public static void setPDC(@NotNull PersistentDataHolder holder, @NotNull NamespacedKey key, @NotNull String value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
    }

    /**
     * Retrieves the value associated with the specified key from the PersistentDataContainer (PDC).
     *
     * @param holder The PersistentDataHolder to retrieve the data from.
     * @param key    The key to retrieve the value for.
     * @return The value associated with the key, or null if the key does not exist.
     */
    @Nullable
    public static <T> T getPDC(@NotNull PersistentDataHolder holder, @NotNull NamespacedKey key, @NotNull PersistentDataType<?, T> type) {
        return holder.getPersistentDataContainer().get(key, type);
    }

    /**
     * Retrieves the value associated with the specified key from the PersistentDataContainer (PDC).
     *
     * @param itemStack The ItemStack to retrieve the data from.
     * @param key       The key to retrieve the value for.
     * @return The value associated with the key, or null if the key does not exist.
     */
    @Nullable
    public static <T> T getPDC(@NotNull ItemStack itemStack, @NotNull NamespacedKey key, @NotNull PersistentDataType<?, T> type) {
        return getPDC(itemStack.getItemMeta(), key, type);
    }

    /**
     * Removes the specified key from the PersistentDataContainer (PDC) of the holder.
     *
     * @param holder The PersistentDataHolder to remove the key from.
     * @param key    The key to remove.
     */
    public static void removePDC(@NotNull PersistentDataHolder holder, @NotNull NamespacedKey key) {
        holder.getPersistentDataContainer().remove(key);
    }

    /**
     * Checks if the PersistentDataContainer (PDC) of the holder contains the specified key.
     *
     * @param holder The PersistentDataHolder to check.
     * @param key    The key to check for.
     * @return True if the key exists, false otherwise.
     */
    public static boolean hasPDC(@NotNull PersistentDataHolder holder, @NotNull NamespacedKey key) {
        return holder.getPersistentDataContainer().has(key);
    }

    /**
     * Checks if the PersistentDataContainer (PDC) of the holder contains the specified key with the given value.
     *
     * @param holder The PersistentDataHolder to check.
     * @param key    The key to check for.
     * @param value  The value to check for.
     * @return True if the key exists and has the specified value, false otherwise.
     */
    public static boolean hasPDC(@NotNull PersistentDataHolder holder, @NotNull NamespacedKey key, String value) {
        return holder.getPersistentDataContainer().has(key, PersistentDataType.STRING);
    }
}
