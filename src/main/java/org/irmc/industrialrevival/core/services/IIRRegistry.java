package org.irmc.industrialrevival.core.services;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.elements.melt.MeltedType;
import org.irmc.industrialrevival.api.elements.tinker.TinkerType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.BlockDropItem;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.items.attributes.TinkerProduct;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.multiblock.MultiBlock;
import org.irmc.industrialrevival.api.physics.ContainerType;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.api.recipes.methods.BlockDropMethod;
import org.irmc.industrialrevival.api.recipes.methods.MobDropMethod;
import org.irmc.industrialrevival.api.recipes.methods.ProduceMethod;
import org.irmc.industrialrevival.core.guide.GuideMode;
import org.irmc.industrialrevival.core.guide.GuideImplementation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface for managing and registering all core components of the IndustrialRevival plugin.
 * Provides access to registries for items, recipes, machines, and other industrial elements.
 */
public interface IIRRegistry {
    /**
     * Gets an unmodifiable map of all registered item groups.
     *
     * @return a map of NamespacedKey to ItemGroup objects
     */
    @NotNull Map<NamespacedKey, ItemGroup> getItemGroups();

    /**
     * Gets a list of all registered item groups.
     *
     * @return a list containing all item groups
     */
    @NotNull List<ItemGroup> getAllItemGroups();

    /**
     * Retrieves an item group by its key.
     *
     * @param key the NamespacedKey identifying the item group
     * @return the item group or null if not found
     */
    @Nullable ItemGroup getItemGroup(@NotNull NamespacedKey key);

    /**
     * Registers a new item group.
     *
     * @param itemGroup the item group to register
     * @return the registered item group
     */
    @CanIgnoreReturnValue
    @NotNull ItemGroup registerItemGroup(@NotNull ItemGroup itemGroup);

    /**
     * Unregisters an item group by its key.
     *
     * @param key the key of the item group to unregister
     * @return the unregistered item group or null if not found
     */
    @CanIgnoreReturnValue
    @Nullable ItemGroup unregisterItemGroup(@NotNull NamespacedKey key);

    /**
     * Unregisters a specific item group.
     *
     * @param itemGroup the item group to unregister
     * @return the unregistered item group
     */
    @CanIgnoreReturnValue
    @NotNull ItemGroup unregisterItemGroup(@NotNull ItemGroup itemGroup);

    /**
     * Gets an unmodifiable map of all registered item dictionaries.
     *
     * @return a map of NamespacedKey to ItemDictionary objects
     */
    @NotNull Map<NamespacedKey, ItemDictionary> getDictionaries();

    /**
     * Gets a collection of all registered item dictionaries.
     *
     * @return a collection containing all item dictionaries
     */
    @NotNull Collection<ItemDictionary> getAllItemDictionaries();

    /**
     * Retrieves an item dictionary by its key.
     *
     * @param key the NamespacedKey identifying the item dictionary
     * @return the item dictionary or null if not found
     */
    @Nullable ItemDictionary getItemDictionary(@NotNull NamespacedKey key);

    /**
     * Registers a new item dictionary.
     *
     * @param dictionary the item dictionary to register
     * @return the registered item dictionary
     */
    @CanIgnoreReturnValue
    @NotNull ItemDictionary registerItemDictionary(@NotNull ItemDictionary dictionary);

    /**
     * Unregisters an item dictionary by its key.
     *
     * @param key the key of the item dictionary to unregister
     * @return the unregistered item dictionary or null if not found
     */
    @CanIgnoreReturnValue
    @Nullable ItemDictionary unregisterItemDictionary(@NotNull NamespacedKey key);

    /**
     * Unregisters a specific item dictionary.
     *
     * @param dictionary the item dictionary to unregister
     * @return the unregistered item dictionary
     */
    @CanIgnoreReturnValue
    @NotNull ItemDictionary unregisterItemDictionary(@NotNull ItemDictionary dictionary);

    /**
     * Gets an unmodifiable map of all registered items.
     *
     * @return a map of NamespacedKey to IndustrialRevivalItem objects
     */
    @NotNull Map<NamespacedKey, IndustrialRevivalItem> getItems();

    /**
     * Gets a collection of all registered items.
     *
     * @return a collection containing all items
     */
    @NotNull Collection<IndustrialRevivalItem> getAllItems();

    /**
     * Retrieves an item by its key.
     *
     * @param key the NamespacedKey identifying the item
     * @return the item or null if not found
     */
    @Nullable IndustrialRevivalItem getItem(@NotNull NamespacedKey key);

    /**
     * Registers a new item.
     *
     * @param item the item to register
     * @return the registered item
     */
    @CanIgnoreReturnValue
    @NotNull IndustrialRevivalItem registerItem(@NotNull IndustrialRevivalItem item);

    /**
     * Unregisters an item by its key.
     *
     * @param key the key of the item to unregister
     * @return the unregistered item or null if not found
     */
    @CanIgnoreReturnValue
    @Nullable IndustrialRevivalItem unregisterItem(@NotNull NamespacedKey key);

    /**
     * Unregisters a specific item.
     *
     * @param item the item to unregister
     * @return the unregistered item
     */
    @CanIgnoreReturnValue
    @NotNull IndustrialRevivalItem unregisterItem(@NotNull IndustrialRevivalItem item);

    /**
     * Gets an unmodifiable map of all registered machine menu presets.
     *
     * @return a map of NamespacedKey to MachineMenuPreset objects
     */
    @NotNull Map<NamespacedKey, MachineMenuPreset> getMenuPresets();

    /**
     * Gets a collection of all registered machine menu presets.
     *
     * @return a collection containing all machine menu presets
     */
    @NotNull Collection<MachineMenuPreset> getAllMenuPresets();

    /**
     * Retrieves a machine menu preset by its key.
     *
     * @param key the NamespacedKey identifying the machine menu preset
     * @return the machine menu preset or null if not found
     */
    @Nullable MachineMenuPreset getMenuPreset(@NotNull NamespacedKey key);

    /**
     * Registers a new machine menu preset.
     *
     * @param menuPreset the machine menu preset to register
     * @return the registered machine menu preset
     */
    @CanIgnoreReturnValue
    @NotNull MachineMenuPreset registerMenuPreset(@NotNull MachineMenuPreset menuPreset);

    /**
     * Unregisters a machine menu preset by its key.
     *
     * @param key the key of the machine menu preset to unregister
     * @return the unregistered machine menu preset or null if not found
     */
    @CanIgnoreReturnValue
    @Nullable MachineMenuPreset unregisterMenuPreset(@NotNull NamespacedKey key);

    /**
     * Unregisters a specific machine menu preset.
     *
     * @param menuPreset the machine menu preset to unregister
     * @return the unregistered machine menu preset
     */
    @CanIgnoreReturnValue
    @NotNull MachineMenuPreset unregisterMenuPreset(@NotNull MachineMenuPreset menuPreset);

    /**
     * Gets an unmodifiable map of all registered recipe types.
     *
     * @return a map of NamespacedKey to RecipeType objects
     */
    @NotNull Map<NamespacedKey, RecipeType> getRecipeTypes();

    /**
     * Gets a collection of all registered recipe types.
     *
     * @return a collection containing all recipe types
     */
    @NotNull Collection<RecipeType> getAllRecipeTypes();

    /**
     * Retrieves a recipe type by its key.
     *
     * @param key the NamespacedKey identifying the recipe type
     * @return the recipe type or null if not found
     */
    @Nullable RecipeType getRecipeType(@NotNull NamespacedKey key);

    /**
     * Registers a recipe type.
     *
     * @param recipeType the recipe type to register
     * @return the registered recipe type
     */
    @CanIgnoreReturnValue
    @NotNull RecipeType registerRecipeType(@NotNull RecipeType recipeType);

    /**
     * Unregisters a recipe type by key.
     *
     * @param key the key of the recipe type to unregister
     * @return the unregistered recipe type or null if not found
     */
    @CanIgnoreReturnValue
    @Nullable RecipeType unregisterRecipeType(@NotNull NamespacedKey key);

    /**
     * Unregisters a specific recipe type.
     *
     * @param recipeType the recipe type to unregister
     * @return the unregistered recipe type
     */
    @CanIgnoreReturnValue
    @NotNull RecipeType unregisterRecipeType(@NotNull RecipeType recipeType);

    /**
     * Gets an unmodifiable map of all registered multiblocks.
     *
     * @return a map of NamespacedKey to MultiBlock objects
     */
    @NotNull Map<NamespacedKey, MultiBlock> getMultiBlocks();

    /**
     * Gets a collection of all registered multiblocks.
     *
     * @return a collection containing all multiblocks
     */
    @NotNull Collection<MultiBlock> getAllMultiBlocks();

    /**
     * Retrieves a multiblock by its key.
     *
     * @param key the NamespacedKey identifying the multiblock
     * @return the multiblock or null if not found
     */
    @Nullable MultiBlock getMultiBlock(@NotNull NamespacedKey key);

    /**
     * Registers a multiblock.
     *
     * @param multiBlock the multiblock to register
     * @return the registered multiblock
     */
    @CanIgnoreReturnValue
    @NotNull MultiBlock registerMultiBlock(@NotNull MultiBlock multiBlock);

    /**
     * Unregisters a multiblock by key.
     *
     * @param key the key of the multiblock to unregister
     * @return the unregistered multiblock or null if not found
     */
    @CanIgnoreReturnValue
    @Nullable MultiBlock unregisterMultiBlock(@NotNull NamespacedKey key);

    /**
     * Unregisters a specific multiblock.
     *
     * @param multiBlock the multiblock to unregister
     * @return the unregistered multiblock
     */
    @CanIgnoreReturnValue
    @NotNull MultiBlock unregisterMultiBlock(@NotNull MultiBlock multiBlock);

    /**
     * Gets all produce methods.
     *
     * @return a set containing all produce methods
     */
    @NotNull Set<ProduceMethod> getAllProduceMethods();

    /**
     * Gets produce methods by input items.
     *
     * @param inputs a collection of input item stacks
     * @return a set of produce methods matching the input items
     */
    @NotNull Set<ProduceMethod> getProduceMethodByInput(@NotNull Collection<ItemStack> inputs);

    /**
     * Gets produce methods by input items.
     *
     * @param inputs an array of input item stacks
     * @return a set of produce methods matching the input items
     */
    @NotNull Set<ProduceMethod> getProduceMethodByInput(@NotNull ItemStack @NotNull [] inputs);

    /**
     * Gets produce methods by a single input item.
     *
     * @param input the input item stack (may be null)
     * @return a set of produce methods matching the input item
     */
    @NotNull Set<ProduceMethod> getProduceMethodByInput(@Nullable ItemStack input);

    /**
     * Gets produce methods by output items.
     *
     * @param outputs a collection of output item stacks
     * @return a set of produce methods matching the output items
     */
    @NotNull Set<ProduceMethod> getProduceMethodByOutput(@NotNull Collection<ItemStack> outputs);

    /**
     * Gets produce methods by output items.
     *
     * @param outputs an array of output item stacks
     * @return a set of produce methods matching the output items
     */
    @NotNull Set<ProduceMethod> getProduceMethodByOutput(@NotNull ItemStack @NotNull [] outputs);

    /**
     * Gets produce methods by a single output item.
     *
     * @param output the output item stack (may be null)
     * @return a set of produce methods matching the output item
     */
    @NotNull Set<ProduceMethod> getProduceMethodByOutput(@Nullable ItemStack output);

    /**
     * Gets produce methods by recipe type.
     *
     * @param recipeType the recipe type to match
     * @return a set of produce methods matching the recipe type
     */
    @NotNull Set<ProduceMethod> getProduceMethodByRecipeType(@NotNull RecipeType recipeType);

    /**
     * Gets all produce methods of a specific type.
     *
     * @param clazz the class of the desired produce method type
     * @return a set of produce methods of the specified type
     */
    @NotNull <T extends ProduceMethod> Set<T> getAllProduceMethod(@NotNull Class<T> clazz);

    /**
     * Registers a produce method.
     *
     * @param produceMethod the produce method to register
     * @return the registered produce method
     */
    @CanIgnoreReturnValue
    @NotNull ProduceMethod registerProduceMethod(@NotNull ProduceMethod produceMethod);

    /**
     * Registers a produce method with specific ingredients and outputs.
     *
     * @param recipeType  the recipe type for this produce method
     * @param ingredients the required ingredients
     * @param outputs     the resulting outputs
     * @return the registered produce method
     */
    @CanIgnoreReturnValue
    @NotNull ProduceMethod registerProduceMethod(@NotNull RecipeType recipeType, @NotNull ItemStack @NotNull [] ingredients, @Nullable ItemStack @NotNull [] outputs);

    /**
     * Unregisters a produce method.
     *
     * @param produceMethod the produce method to unregister
     * @return the unregistered produce method
     */
    @CanIgnoreReturnValue
    @NotNull ProduceMethod unregisterProduceMethod(@NotNull ProduceMethod produceMethod);

    /**
     * Unregisters a produce method by recipe type, ingredients, and outputs.
     *
     * @param recipeType  the recipe type for this produce method
     * @param ingredients the required ingredients
     * @param outputs     the resulting outputs
     * @return the unregistered produce method or null if not found
     */
    @CanIgnoreReturnValue
    @Nullable ProduceMethod unregisterProduceMethod(@NotNull RecipeType recipeType, @NotNull ItemStack @NotNull [] ingredients, @Nullable ItemStack @NotNull [] outputs);

    /**
     * Gets the tinker map.
     *
     * @return a map from MeltedType to a map of TinkerType to TinkerProduct
     */
    @NotNull Map<MeltedType, Map<TinkerType, TinkerProduct>> getTinkerMap();

    /**
     * Gets an unmodifiable map of all registered melted types.
     *
     * @return a map of NamespacedKey to MeltedType objects
     */
    @NotNull Map<NamespacedKey, MeltedType> getMeltedTypes();

    /**
     * Gets a collection of all registered melted types.
     *
     * @return a collection containing all melted types
     */
    @NotNull Collection<MeltedType> getAllMeltedTypes();

    /**
     * Retrieves a melted type by its key.
     *
     * @param key the NamespacedKey identifying the melted type
     * @return the melted type or null if not found
     */
    @Nullable MeltedType getMeltedType(@NotNull NamespacedKey key);

    /**
     * Registers a melted type.
     *
     * @param type the melted type to register
     * @return the registered melted type
     */
    @CanIgnoreReturnValue
    @NotNull MeltedType registerMeltedType(@NotNull MeltedType type);

    /**
     * Unregisters a melted type by key.
     *
     * @param key the key of the melted type to unregister
     * @return the unregistered melted type or null if not found
     */
    @CanIgnoreReturnValue
    @Nullable MeltedType unregisterMeltedType(@NotNull NamespacedKey key);

    /**
     * Unregisters a specific melted type.
     *
     * @param type the melted type to unregister
     * @return the unregistered melted type
     */
    @CanIgnoreReturnValue
    @NotNull MeltedType unregisterMeltedType(@NotNull MeltedType type);

    /**
     * Gets tinker recipes for a specific melted type.
     *
     * @param type the melted type
     * @return a map of TinkerType to TinkerProduct for the specified melted type
     */
    @NotNull Map<TinkerType, TinkerProduct> getTinkerRecipes(@NotNull MeltedType type);

    /**
     * Registers a tinker recipe for a melted type.
     *
     * @param type    the melted type
     * @param product the tinker product to register
     * @return the registered tinker product
     */
    @CanIgnoreReturnValue
    @NotNull TinkerProduct registerTinkerRecipe(@NotNull MeltedType type, @NotNull TinkerProduct product);

    /**
     * Unregisters all tinker recipes for a melted type.
     *
     * @param type the melted type
     * @return a map of TinkerType to TinkerProduct that were unregistered
     */
    @CanIgnoreReturnValue
    @NotNull Map<TinkerType, TinkerProduct> unregisterTinkerRecipes(@NotNull MeltedType type);

    /**
     * Unregisters a specific tinker recipe.
     *
     * @param product the tinker product to unregister
     * @return the unregistered tinker product
     */
    @CanIgnoreReturnValue
    @NotNull TinkerProduct unregisterTinkerRecipe(@NotNull TinkerProduct product);

    @Nullable TinkerProduct getTinkerProduct(@NotNull MeltedType type, @NotNull TinkerType tinkerType);

    /**
     * Gets an unmodifiable map of all registered chemical formulas.
     *
     * @return a map of integer IDs to ChemicalFormula objects
     */
    @NotNull Map<Integer, ChemicalFormula> getChemicalFormulas();

    /**
     * Gets a collection of all registered chemical formulas.
     *
     * @return a collection containing all chemical formulas
     */
    @NotNull Collection<ChemicalFormula> getAllChemicalFormulas();

    /**
     * Retrieves a chemical formula by ID.
     *
     * @param id the ID of the chemical formula
     * @return the chemical formula or null if not found
     */
    @Nullable ChemicalFormula getChemicalFormula(int id);

    /**
     * Registers a chemical formula.
     *
     * @param formula the chemical formula to register
     * @return the registered chemical formula
     */
    @CanIgnoreReturnValue
    @NotNull ChemicalFormula registerChemicalFormula(@NotNull ChemicalFormula formula);

    /**
     * Unregisters a chemical formula by ID.
     *
     * @param id the ID of the chemical formula to unregister
     * @return the unregistered chemical formula or null if not found
     */
    @CanIgnoreReturnValue
    @Nullable ChemicalFormula unregisterChemicalFormula(int id);

    /**
     * Unregisters a specific chemical formula.
     *
     * @param formula the chemical formula to unregister
     * @return the unregistered chemical formula
     */
    @CanIgnoreReturnValue
    @NotNull ChemicalFormula unregisterChemicalFormula(@NotNull ChemicalFormula formula);

    /**
     * Gets an unmodifiable map of mob drops by entity type.
     *
     * @return a map of EntityType to lists of MobDropMethod objects
     */
    @NotNull Map<EntityType, List<MobDropMethod>> getMobDrops();

    /**
     * Gets mob drops for a specific entity type.
     *
     * @param entityType the entity type
     * @return a list of mob drop methods for the specified entity type
     */
    @NotNull List<MobDropMethod> getMobDrops(@NotNull EntityType entityType);

    /**
     * Registers a mob drop method.
     *
     * @param dropMethod the mob drop method to register
     * @return the registered mob drop method
     */
    @CanIgnoreReturnValue
    @NotNull MobDropMethod registerMobDrop(@NotNull MobDropMethod dropMethod);

    /**
     * Unregisters a mob drop method.
     *
     * @param dropMethod the mob drop method to unregister
     * @return the unregistered mob drop method
     */
    @CanIgnoreReturnValue
    @NotNull MobDropMethod unregisterMobDrop(@NotNull MobDropMethod dropMethod);

    /**
     * Gets an unmodifiable map of block drops by material.
     *
     * @return a map of Material to lists of BlockDropMethod objects
     */
    @NotNull Map<Material, List<BlockDropMethod>> getBlockDrops();

    /**
     * Gets block drops for a specific material.
     *
     * @param material the material
     * @return a list of block drop methods for the specified material
     */
    @NotNull List<BlockDropMethod> getBlockDrops(@NotNull Material material);

    /**
     * Registers a block drop method.
     *
     * @param dropMethod the block drop method to register
     * @return the registered block drop method
     */
    @CanIgnoreReturnValue
    @NotNull BlockDropMethod registerBlockDrop(@NotNull BlockDropMethod dropMethod);

    /**
     * Unregisters a block drop method.
     *
     * @param dropMethod the block drop method to unregister
     * @return the unregistered block drop method
     */
    @CanIgnoreReturnValue
    @NotNull BlockDropMethod unregisterBlockDrop(@NotNull BlockDropMethod dropMethod);

    /**
     * Gets an unmodifiable map of chemical compounds by name.
     *
     * @return a map of compound names to ChemicalCompound objects
     */
    @NotNull Map<String, ChemicalCompound> getChemicalCompounds();

    /**
     * Retrieves a chemical compound by name.
     *
     * @param name the name of the chemical compound
     * @return the chemical compound or null if not found
     */
    @Nullable ChemicalCompound getChemicalCompound(@NotNull String name);

    /**
     * Registers a chemical compound.
     *
     * @param compound the chemical compound to register
     * @return the registered chemical compound
     */
    @CanIgnoreReturnValue
    @NotNull ChemicalCompound registerChemicalCompound(@NotNull ChemicalCompound compound);

    /**
     * Unregisters a specific chemical compound.
     *
     * @param compound the chemical compound to unregister
     * @return the unregistered chemical compound or null if not found
     */
    @CanIgnoreReturnValue
    @Nullable ChemicalCompound unregisterChemicalCompound(@NotNull ChemicalCompound compound);

    /**
     * Unregisters a chemical compound by name.
     *
     * @param name the name of the chemical compound to unregister
     * @return the unregistered chemical compound or null if not found
     */
    @CanIgnoreReturnValue
    @Nullable ChemicalCompound unregisterChemicalCompound(@NotNull String name);

    /**
     * Registers a mob drop item.
     *
     * @param item the mob drop item to register
     * @return the registered mob drop item
     */
    @CanIgnoreReturnValue
    @NotNull <T extends IndustrialRevivalItem & MobDropItem> T registerMobDrop(@NotNull T item);

    /**
     * Registers a block drop item.
     *
     * @param item the block drop item to register
     * @return the registered block drop item
     */
    @CanIgnoreReturnValue
    @NotNull <T extends IndustrialRevivalItem & BlockDropItem> T registerBlockDrop(@NotNull T item);

    @Nullable GuideMode getGuideMode(@NotNull String guideModeName);
    @NotNull GuideImplementation getGuide(@NotNull GuideMode guideMode);
    @NotNull ItemStack getGuideIcon(@NotNull GuideMode guide);
    void registerGuide(@NotNull GuideMode guideMode, @NotNull GuideImplementation guide);

    @NotNull ContainerType getContainerType(@NotNull NamespacedKey key);
    void registerContainerType(@NotNull ContainerType containerType);
}