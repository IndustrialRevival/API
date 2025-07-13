package org.irmc.industrialrevival.api.multiblock;

import lombok.Getter;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.jetbrains.annotations.NotNull;

// todo: structure display in guide
/**
 * Abstract base class for all multiblock structures in the IndustrialRevival system.
 * <p>
 * A multiblock is a complex structure composed of multiple blocks arranged in a specific pattern.
 * This class provides the foundation for defining, registering, and interacting with multiblock structures.
 * It extends {@link IndustrialRevivalItem} and implements {@link Keyed} for unique identification.
 * </p>
 * <p>
 * Subclasses should implement the {@link #onInteract(PlayerInteractEvent)} method to define custom
 * interaction behavior when a player interacts with the multiblock.
 * </p>
 *
 * @author balugaq
 * @see Structure
 * @see IndustrialRevivalItem
 */
@Getter
@SuppressWarnings("unused")
public abstract class MultiBlock extends IndustrialRevivalItem implements Keyed {
    private final NamespacedKey key;
    private Structure structure;
    private int[] center;

    /**
     * Constructs a new MultiBlock with the specified key.
     *
     * @param key the unique key for this multiblock
     */
    public MultiBlock(NamespacedKey key) {
        this.key = key;
    }

    /**
     * Checks if the structure at the given location matches this multiblock's structure definition.
     *
     * @param location the location to check
     * @return true if the structure at the location matches, false otherwise
     */
    public boolean matchStructure(@NotNull Location location) {
        return structure.isValid(location);
    }

    /**
     * Sets the structure definition for this multiblock.
     *
     * @param structure the structure to set
     * @return this MultiBlock instance for method chaining
     */
    public MultiBlock setStructure(@NotNull Structure structure) {
        checkRegistered();
        this.structure = structure;
        this.center = structure.getCenter();
        return this;
    }

    /**
     * Sets the addon for this multiblock.
     *
     * @param addon the addon to set
     * @return this MultiBlock instance for method chaining
     */
    @Override
    public MultiBlock setAddon(@NotNull IndustrialRevivalAddon addon) {
        super.setAddon(addon);
        return this;
    }

    /**
     * Adds an item group to this multiblock.
     *
     * @param group the item group to add
     * @return this MultiBlock instance for method chaining
     */
    @Override
    public MultiBlock addItemGroup(@NotNull ItemGroup group) {
        super.addItemGroup(group);
        return this;
    }

    /**
     * Sets the recipe handler for this multiblock.
     *
     * @param handler the recipe handler to set
     * @return this MultiBlock instance for method chaining
     */
    @Override
    public MultiBlock recipe(@NotNull IndustrialRevivalItem.ProduceMethodGetter handler) {
        super.recipe(handler);
        return this;
    }

    /**
     * Sets the wiki text for this multiblock.
     *
     * @param wikiText the wiki text to set
     * @return this MultiBlock instance for method chaining
     */
    @Override
    public MultiBlock setWikiText(@NotNull String wikiText) {
        super.setWikiText(wikiText);
        return this;
    }

    /**
     * Sets whether this multiblock is disabled in a specific world.
     *
     * @param world the world to set
     * @param disabled whether to disable the multiblock
     * @param saveToConfig whether to save the change to config
     * @return this MultiBlock instance for method chaining
     */
    @Override
    public MultiBlock setDisabledInWorld(@NotNull World world, boolean disabled, boolean saveToConfig) {
        super.setDisabledInWorld(world, disabled, saveToConfig);
        return this;
    }

    /**
     * Sets whether this multiblock is disabled.
     *
     * @param disabled whether to disable the multiblock
     * @param saveToConfig whether to save the change to config
     * @return this MultiBlock instance for method chaining
     */
    @Override
    public MultiBlock setDisabled(boolean disabled, boolean saveToConfig) {
        super.setDisabled(disabled, saveToConfig);
        return this;
    }

    /**
     * Adds an item dictionary to this multiblock.
     *
     * @param dictionary the item dictionary to add
     * @return this MultiBlock instance for method chaining
     */
    @Override
    public MultiBlock addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    /**
     * Sets whether this multiblock is enchantable.
     *
     * @param enchantable whether the multiblock is enchantable
     * @param saveToConfig whether to save the change to config
     * @return this MultiBlock instance for method chaining
     */
    @Override
    public MultiBlock setEnchantable(boolean enchantable, boolean saveToConfig) {
        super.setEnchantable(enchantable, saveToConfig);
        return this;
    }

    /**
     * Sets whether this multiblock is disenchantable.
     *
     * @param disenchantable whether the multiblock is disenchantable
     * @param saveToConfig whether to save the change to config
     * @return this MultiBlock instance for method chaining
     */
    @Override
    public MultiBlock setDisenchantable(boolean disenchantable, boolean saveToConfig) {
        super.setDisenchantable(disenchantable, saveToConfig);
        return this;
    }

    /**
     * Sets whether this multiblock is hidden in the guide.
     *
     * @param hideInGuide whether to hide the multiblock in the guide
     * @param saveToConfig whether to save the change to config
     * @return this MultiBlock instance for method chaining
     */
    @Override
    public MultiBlock setHideInGuide(boolean hideInGuide, boolean saveToConfig) {
        super.setHideInGuide(hideInGuide, saveToConfig);
        return this;
    }

    /**
     * Called when a player interacts with this multiblock.
     * <p>
     * Subclasses should implement this method to define custom interaction behavior.
     * </p>
     *
     * @param event the player interaction event
     */
    public abstract void onInteract(@NotNull PlayerInteractEvent event);
}
