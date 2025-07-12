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
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.api.recipes.methods.BlockDropMethod;
import org.irmc.industrialrevival.api.recipes.methods.MobDropMethod;
import org.irmc.industrialrevival.api.recipes.methods.ProduceMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IIRRegistry {
    @NotNull Map<NamespacedKey, ItemGroup> getItemGroups();

    @NotNull List<ItemGroup> getAllItemGroups();

    @Nullable ItemGroup getItemGroup(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull ItemGroup registerItemGroup(@NotNull ItemGroup itemGroup);

    @CanIgnoreReturnValue
    @Nullable ItemGroup unregisterItemGroup(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull ItemGroup unregisterItemGroup(@NotNull ItemGroup itemGroup);

    @NotNull Map<NamespacedKey, ItemDictionary> getDictionaries();

    @NotNull Collection<ItemDictionary> getAllItemDictionaries();

    @Nullable ItemDictionary getItemDictionary(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull ItemDictionary registerItemDictionary(@NotNull ItemDictionary dictionary);

    @CanIgnoreReturnValue
    @Nullable ItemDictionary unregisterItemDictionary(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull ItemDictionary unregisterItemDictionary(@NotNull ItemDictionary dictionary);

    @NotNull Map<NamespacedKey, IndustrialRevivalItem> getItems();

    @NotNull Collection<IndustrialRevivalItem> getAllItems();

    @Nullable IndustrialRevivalItem getItem(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull IndustrialRevivalItem registerItem(@NotNull IndustrialRevivalItem item);

    @CanIgnoreReturnValue
    @Nullable IndustrialRevivalItem unregisterItem(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull IndustrialRevivalItem unregisterItem(@NotNull IndustrialRevivalItem item);

    @NotNull Map<NamespacedKey, MachineMenuPreset> getMenuPresets();

    @NotNull Collection<MachineMenuPreset> getAllMenuPresets();

    @Nullable MachineMenuPreset getMenuPreset(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull MachineMenuPreset registerMenuPreset(@NotNull MachineMenuPreset menuPreset);

    @CanIgnoreReturnValue
    @Nullable MachineMenuPreset unregisterMenuPreset(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull MachineMenuPreset unregisterMenuPreset(@NotNull MachineMenuPreset menuPreset);

    @NotNull Map<NamespacedKey, RecipeType> getRecipeTypes();

    @NotNull Collection<RecipeType> getAllRecipeTypes();

    @Nullable RecipeType getRecipeType(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull RecipeType registerRecipeType(@NotNull RecipeType recipeType);

    @CanIgnoreReturnValue
    @Nullable RecipeType unregisterRecipeType(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull RecipeType unregisterRecipeType(@NotNull RecipeType recipeType);

    @NotNull Map<NamespacedKey, MultiBlock> getMultiBlocks();

    @NotNull Collection<MultiBlock> getAllMultiBlocks();

    @Nullable MultiBlock getMultiBlock(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull MultiBlock registerMultiBlock(@NotNull MultiBlock multiBlock);

    @CanIgnoreReturnValue
    @Nullable MultiBlock unregisterMultiBlock(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull MultiBlock unregisterMultiBlock(@NotNull MultiBlock multiBlock);

    @NotNull Set<ProduceMethod> getAllProduceMethods();

    @NotNull Set<ProduceMethod> getProduceMethodByInput(@NotNull Collection<ItemStack> inputs);

    @NotNull Set<ProduceMethod> getProduceMethodByInput(@NotNull ItemStack @NotNull ... inputs);

    @NotNull Set<ProduceMethod> getProduceMethodByInput(@Nullable ItemStack input);

    @NotNull Set<ProduceMethod> getProduceMethodByOutput(@NotNull Collection<ItemStack> outputs);

    @NotNull Set<ProduceMethod> getProduceMethodByOutput(@NotNull ItemStack @NotNull ... outputs);

    @NotNull Set<ProduceMethod> getProduceMethodByOutput(@Nullable ItemStack output);

    @NotNull Set<ProduceMethod> getProduceMethodByRecipeType(@NotNull RecipeType recipeType);

    @NotNull <T extends ProduceMethod> Set<T> getAllProduceMethod(@NotNull Class<T> clazz);

    @CanIgnoreReturnValue
    @NotNull ProduceMethod registerProduceMethod(@NotNull ProduceMethod produceMethod);

    @CanIgnoreReturnValue
    @NotNull ProduceMethod registerProduceMethod(@NotNull RecipeType recipeType, @NotNull ItemStack @NotNull [] ingredients, @Nullable ItemStack @NotNull [] outputs);

    @CanIgnoreReturnValue
    @NotNull ProduceMethod unregisterProduceMethod(@NotNull ProduceMethod produceMethod);

    @CanIgnoreReturnValue
    @Nullable ProduceMethod unregisterProduceMethod(@NotNull RecipeType recipeType, @NotNull ItemStack @NotNull [] ingredients, @Nullable ItemStack @NotNull [] outputs);

    @NotNull Map<MeltedType, Map<TinkerType, TinkerProduct>> getTinkerMap();

    @NotNull Map<NamespacedKey, MeltedType> getMeltedTypes();

    @NotNull Collection<MeltedType> getAllMeltedTypes();

    @Nullable MeltedType getMeltedType(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull MeltedType registerMeltedType(@NotNull MeltedType type);

    @CanIgnoreReturnValue
    @Nullable MeltedType unregisterMeltedType(@NotNull NamespacedKey key);

    @CanIgnoreReturnValue
    @NotNull MeltedType unregisterMeltedType(@NotNull MeltedType type);

    @NotNull Map<TinkerType, TinkerProduct> getTinkerRecipes(@NotNull MeltedType type);

    @CanIgnoreReturnValue
    @NotNull TinkerProduct registerTinkerRecipe(@NotNull MeltedType type, @NotNull TinkerProduct product);

    @CanIgnoreReturnValue
    @NotNull Map<TinkerType, TinkerProduct> unregisterTinkerRecipes(@NotNull MeltedType type);

    @CanIgnoreReturnValue
    @NotNull TinkerProduct unregisterTinkerRecipe(@NotNull TinkerProduct product);

    @NotNull Map<Integer, ChemicalFormula> getChemicalFormulas();

    @NotNull Collection<ChemicalFormula> getAllChemicalFormulas();

    @Nullable ChemicalFormula getChemicalFormula(int id);

    @CanIgnoreReturnValue
    @NotNull ChemicalFormula registerChemicalFormula(@NotNull ChemicalFormula formula);

    @CanIgnoreReturnValue
    @Nullable ChemicalFormula unregisterChemicalFormula(int id);

    @CanIgnoreReturnValue
    @NotNull ChemicalFormula unregisterChemicalFormula(@NotNull ChemicalFormula formula);

    @NotNull Map<EntityType, List<MobDropMethod>> getMobDrops();

    @NotNull List<MobDropMethod> getMobDrops(@NotNull EntityType entityType);

    @CanIgnoreReturnValue
    @NotNull MobDropMethod registerMobDrop(@NotNull MobDropMethod dropMethod);

    @CanIgnoreReturnValue
    @NotNull MobDropMethod unregisterMobDrop(@NotNull MobDropMethod dropMethod);

    @NotNull Map<Material, List<BlockDropMethod>> getBlockDrops();

    @NotNull List<BlockDropMethod> getBlockDrops(@NotNull Material material);

    @CanIgnoreReturnValue
    @NotNull BlockDropMethod registerBlockDrop(@NotNull BlockDropMethod dropMethod);

    @CanIgnoreReturnValue
    @NotNull BlockDropMethod unregisterBlockDrop(@NotNull BlockDropMethod dropMethod);

    @NotNull Map<String, ChemicalCompound> getChemicalCompounds();

    @Nullable ChemicalCompound getChemicalCompound(@NotNull String name);

    @CanIgnoreReturnValue
    @NotNull ChemicalCompound registerChemicalCompound(@NotNull ChemicalCompound compound);

    @CanIgnoreReturnValue
    @Nullable ChemicalCompound unregisterChemicalCompound(@NotNull ChemicalCompound compound);

    @CanIgnoreReturnValue
    @Nullable ChemicalCompound unregisterChemicalCompound(@NotNull String name);

    @CanIgnoreReturnValue
    @NotNull <T extends IndustrialRevivalItem & MobDropItem> T registerMobDrop(@NotNull T item);

    @CanIgnoreReturnValue
    @NotNull <T extends IndustrialRevivalItem & BlockDropItem> T registerBlockDrop(@NotNull T item);
}
