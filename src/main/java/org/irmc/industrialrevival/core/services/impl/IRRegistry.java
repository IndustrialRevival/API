package org.irmc.industrialrevival.core.services.impl;

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
import org.irmc.industrialrevival.core.services.IIRRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public final class IRRegistry implements IIRRegistry {
    private final Map<NamespacedKey, ItemGroup> itemGroups = new ConcurrentHashMap<>();
    private final Map<NamespacedKey, ItemDictionary> dictionaries = new ConcurrentHashMap<>();
    private final Map<NamespacedKey, IndustrialRevivalItem> items = new ConcurrentHashMap<>();
    private final Map<NamespacedKey, MachineMenuPreset> machineMenuPresets = new ConcurrentHashMap<>();
    private final Map<NamespacedKey, RecipeType> recipeTypes = new ConcurrentHashMap<>();
    private final Map<NamespacedKey, MultiBlock> multiBlocks = new ConcurrentHashMap<>();
    private final Set<ProduceMethod> produceMethods = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Map<NamespacedKey, MeltedType> meltTypes = new ConcurrentHashMap<>();
    private final Map<MeltedType, Map<TinkerType, TinkerProduct>> tinkerProducts = new ConcurrentHashMap<>();
    private final Map<Integer, ChemicalFormula> chemicalFormulas = new ConcurrentHashMap<>();
    private final Map<EntityType, List<MobDropMethod>> mobDrops = new ConcurrentHashMap<>();
    private final Map<Material, List<BlockDropMethod>> blockDrops = new ConcurrentHashMap<>();
    private final Map<String, ChemicalCompound> chemicals = new ConcurrentHashMap<>();

    @Override
    public @NotNull Map<NamespacedKey, ItemGroup> getItemGroups() {
        return Collections.unmodifiableMap(itemGroups);
    }

    @Override
    public @NotNull List<ItemGroup> getAllItemGroups() {
        return itemGroups.values().stream().sorted(Comparator.comparingInt(ItemGroup::getTier)).toList();
    }

    @Override
    public @Nullable ItemGroup getItemGroup(@NotNull NamespacedKey key) {
        return itemGroups.get(key);
    }

    @Override
    public @NotNull ItemGroup registerItemGroup(@NotNull ItemGroup itemGroup) {
        itemGroups.put(itemGroup.getKey(), itemGroup);
        return itemGroup;
    }

    @Override
    public @Nullable ItemGroup unregisterItemGroup(@NotNull NamespacedKey key) {
        return itemGroups.remove(key);
    }

    @Override
    public @NotNull ItemGroup unregisterItemGroup(@NotNull ItemGroup itemGroup) {
        return itemGroups.remove(itemGroup.getKey());
    }

    @Override
    public @NotNull Map<NamespacedKey, ItemDictionary> getDictionaries() {
        return Collections.unmodifiableMap(dictionaries);
    }

    @Override
    public @NotNull Collection<ItemDictionary> getAllItemDictionaries() {
        return Collections.unmodifiableCollection(dictionaries.values());
    }

    @Override
    public @Nullable ItemDictionary getItemDictionary(@NotNull NamespacedKey key) {
        return dictionaries.get(key);
    }

    @Override
    public @NotNull ItemDictionary registerItemDictionary(@NotNull ItemDictionary dictionary) {
        dictionaries.put(dictionary.getKey(), dictionary);
        return dictionary;
    }

    @Override
    public @Nullable ItemDictionary unregisterItemDictionary(@NotNull NamespacedKey key) {
        return dictionaries.remove(key);
    }

    @Override
    public @NotNull ItemDictionary unregisterItemDictionary(@NotNull ItemDictionary dictionary) {
        return dictionaries.remove(dictionary.getKey());
    }

    @Override
    public @NotNull Map<NamespacedKey, IndustrialRevivalItem> getItems() {
        return Collections.unmodifiableMap(items);
    }

    @Override
    public @NotNull Collection<IndustrialRevivalItem> getAllItems() {
        return Collections.unmodifiableCollection(items.values());
    }

    @Override
    public @Nullable IndustrialRevivalItem getItem(@NotNull NamespacedKey key) {
        return items.get(key);
    }

    @Override
    public @NotNull IndustrialRevivalItem registerItem(@NotNull IndustrialRevivalItem item) {
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public @Nullable IndustrialRevivalItem unregisterItem(@NotNull NamespacedKey key) {
        return items.remove(key);
    }

    @Override
    public @NotNull IndustrialRevivalItem unregisterItem(@NotNull IndustrialRevivalItem item) {
        return items.remove(item.getId());
    }

    @Override
    public @NotNull Map<NamespacedKey, MachineMenuPreset> getMenuPresets() {
        return Collections.unmodifiableMap(machineMenuPresets);
    }

    @Override
    public @NotNull Collection<MachineMenuPreset> getAllMenuPresets() {
        return Collections.unmodifiableCollection(machineMenuPresets.values());
    }

    @Override
    public @Nullable MachineMenuPreset getMenuPreset(@NotNull NamespacedKey key) {
        return machineMenuPresets.get(key);
    }

    @Override
    public @NotNull MachineMenuPreset registerMenuPreset(@NotNull MachineMenuPreset menuPreset) {
        machineMenuPresets.put(menuPreset.getId(), menuPreset);
        return menuPreset;
    }

    @Override
    public @Nullable MachineMenuPreset unregisterMenuPreset(@NotNull NamespacedKey key) {
        return machineMenuPresets.remove(key);
    }

    @Override
    public @NotNull MachineMenuPreset unregisterMenuPreset(@NotNull MachineMenuPreset menuPreset) {
        return machineMenuPresets.remove(menuPreset.getId());
    }

    @Override
    public @NotNull Map<NamespacedKey, RecipeType> getRecipeTypes() {
        return Collections.unmodifiableMap(recipeTypes);
    }

    @Override
    public @NotNull Collection<RecipeType> getAllRecipeTypes() {
        return Collections.unmodifiableCollection(recipeTypes.values());
    }

    @Override
    public @Nullable RecipeType getRecipeType(@NotNull NamespacedKey key) {
        return recipeTypes.get(key);
    }

    @Override
    public @NotNull RecipeType registerRecipeType(@NotNull RecipeType recipeType) {
        recipeTypes.put(recipeType.getKey(), recipeType);
        return recipeType;
    }

    @Override
    public @Nullable RecipeType unregisterRecipeType(@NotNull NamespacedKey key) {
        return recipeTypes.remove(key);
    }

    @Override
    public @NotNull RecipeType unregisterRecipeType(@NotNull RecipeType recipeType) {
        return recipeTypes.remove(recipeType.getKey());
    }

    @Override
    public @NotNull Map<NamespacedKey, MultiBlock> getMultiBlocks() {
        return Collections.unmodifiableMap(multiBlocks);
    }

    @Override
    public @NotNull Collection<MultiBlock> getAllMultiBlocks() {
        return Collections.unmodifiableCollection(multiBlocks.values());
    }

    @Override
    public @Nullable MultiBlock getMultiBlock(@NotNull NamespacedKey key) {
        return multiBlocks.get(key);
    }

    @Override
    public @NotNull MultiBlock registerMultiBlock(@NotNull MultiBlock multiBlock) {
        multiBlocks.put(multiBlock.getKey(), multiBlock);
        return multiBlock;
    }

    @Override
    public @Nullable MultiBlock unregisterMultiBlock(@NotNull NamespacedKey key) {
        return multiBlocks.remove(key);
    }

    @Override
    public @NotNull MultiBlock unregisterMultiBlock(@NotNull MultiBlock multiBlock) {
        return multiBlocks.remove(multiBlock.getKey());
    }

    @Override
    public @NotNull Set<ProduceMethod> getAllProduceMethods() {
        return produceMethods;
    }

    @Override
    public @NotNull Set<ProduceMethod> getProduceMethodByInput(@NotNull Collection<ItemStack> inputs) {
        return produceMethods.stream().filter(method -> {
            List<ItemStack> methodInput = Arrays.asList(method.getIngredients());
            return new HashSet<>(methodInput).containsAll(inputs);
        }).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Set<ProduceMethod> getProduceMethodByInput(@NotNull ItemStack @NotNull ... inputs) {
        return getProduceMethodByInput(List.of(inputs));
    }

    @Override
    public @NotNull Set<ProduceMethod> getProduceMethodByInput(@Nullable ItemStack input) {
        return getProduceMethodByInput(input == null ? List.of() : List.of(input));
    }

    @Override
    public @NotNull Set<ProduceMethod> getProduceMethodByOutput(@NotNull Collection<ItemStack> outputs) {
        return produceMethods.stream()
                .filter(method -> {
                    List<ItemStack> methodOutputs = List.of(method.getOutput());
                    return new HashSet<>(methodOutputs).containsAll(outputs);
                })
                .collect(Collectors.toSet());
    }

    @Override
    public @NotNull Set<ProduceMethod> getProduceMethodByOutput(@NotNull ItemStack @NotNull ... outputs) {
        return getProduceMethodByOutput(List.of(outputs));
    }

    @Override
    public @NotNull Set<ProduceMethod> getProduceMethodByOutput(@Nullable ItemStack output) {
        return getProduceMethodByOutput(output == null ? List.of() : List.of(output));
    }

    @Override
    public @NotNull Set<ProduceMethod> getProduceMethodByRecipeType(@NotNull RecipeType recipeType) {
        return produceMethods.stream().filter(method -> method.getRecipeType().equals(recipeType)).collect(Collectors.toSet());
    }

    @Override
    public @NotNull <T extends ProduceMethod> Set<T> getAllProduceMethod(@NotNull Class<T> clazz) {
        return produceMethods.stream().filter(clazz::isInstance).map(clazz::cast).collect(Collectors.toSet());
    }

    @Override
    public @NotNull ProduceMethod registerProduceMethod(@NotNull ProduceMethod produceMethod) {
        produceMethods.add(produceMethod);
        return produceMethod;
    }

    @Override
    public @NotNull ProduceMethod registerProduceMethod(@NotNull RecipeType recipeType, @NotNull ItemStack @Nullable [] ingredients, @Nullable ItemStack @NotNull [] outputs) {
        return recipeType.warp(ingredients, outputs);
    }

    @Override
    public @NotNull ProduceMethod unregisterProduceMethod(@NotNull ProduceMethod produceMethod) {
        produceMethods.remove(produceMethod);
        return produceMethod;
    }

    @Override
    public @Nullable ProduceMethod unregisterProduceMethod(@NotNull RecipeType recipeType, @NotNull ItemStack @NotNull [] ingredients, @Nullable ItemStack @NotNull [] outputs) {
        ProduceMethod produceMethod = produceMethods.stream().filter(method -> {
            return method.getRecipeType().equals(recipeType) && Arrays.equals(method.getIngredients(), ingredients) && Arrays.equals(method.getOutput(), outputs);
        }).findFirst().orElse(null);

        if (produceMethod != null) {
            unregisterProduceMethod(produceMethod);
        }
        return produceMethod;
    }

    @Override
    public @NotNull Map<MeltedType, Map<TinkerType, TinkerProduct>> getTinkerMap() {
        return Collections.unmodifiableMap(tinkerProducts);
    }

    @Override
    public @NotNull Map<NamespacedKey, MeltedType> getMeltedTypes() {
        return Collections.unmodifiableMap(meltTypes);
    }

    @Override
    public @NotNull Collection<MeltedType> getAllMeltedTypes() {
        return meltTypes.values();
    }

    @Override
    public @Nullable MeltedType getMeltedType(@NotNull NamespacedKey key) {
        return meltTypes.get(key);
    }

    @Override
    public @NotNull MeltedType registerMeltedType(@NotNull MeltedType type) {
        meltTypes.put(type.getIdentifier(), type);
        return type;
    }

    @Override
    public @Nullable MeltedType unregisterMeltedType(@NotNull NamespacedKey key) {
        return meltTypes.remove(key);
    }

    @Override
    public @NotNull MeltedType unregisterMeltedType(@NotNull MeltedType type) {
        return meltTypes.remove(type.getIdentifier());
    }

    @Override
    public @NotNull Map<TinkerType, TinkerProduct> getTinkerRecipes(@NotNull MeltedType type) {
        return Collections.unmodifiableMap(tinkerProducts.getOrDefault(type, Collections.emptyMap()));
    }

    @Override
    public @NotNull TinkerProduct registerTinkerRecipe(@NotNull MeltedType type, @NotNull TinkerProduct product) {
        tinkerProducts.computeIfAbsent(type, k -> new ConcurrentHashMap<>()).put(product.getTinkerType(), product);
        return product;
    }

    @Override
    public @NotNull Map<TinkerType, TinkerProduct> unregisterTinkerRecipes(@NotNull MeltedType type) {
        Map<TinkerType, TinkerProduct> removed = tinkerProducts.remove(type);
        return removed != null ? removed : Collections.emptyMap();
    }

    @Override
    public @NotNull TinkerProduct unregisterTinkerRecipe(@NotNull TinkerProduct product) {
        tinkerProducts.forEach((type, map) -> map.remove(product.getTinkerType()));
        return product;
    }

    @Override
    public @NotNull Map<Integer, ChemicalFormula> getChemicalFormulas() {
        return Collections.unmodifiableMap(chemicalFormulas);
    }

    @Override
    public @NotNull Collection<ChemicalFormula> getAllChemicalFormulas() {
        return Collections.unmodifiableCollection(chemicalFormulas.values());
    }

    @Override
    public @Nullable ChemicalFormula getChemicalFormula(int id) {
        return chemicalFormulas.get(id);
    }

    @Override
    public @NotNull ChemicalFormula registerChemicalFormula(@NotNull ChemicalFormula formula) {
        chemicalFormulas.put(formula.getId(), formula);
        return formula;
    }

    @Override
    public @Nullable ChemicalFormula unregisterChemicalFormula(int id) {
        return chemicalFormulas.remove(id);
    }

    @Override
    public @NotNull ChemicalFormula unregisterChemicalFormula(@NotNull ChemicalFormula formula) {
        return chemicalFormulas.remove(formula.getId());
    }

    @Override
    public @NotNull Map<EntityType, List<MobDropMethod>> getMobDrops() {
        return Collections.unmodifiableMap(mobDrops);
    }

    @Override
    public @NotNull List<MobDropMethod> getMobDrops(@NotNull EntityType entityType) {
        return mobDrops.getOrDefault(entityType, Collections.emptyList());
    }

    @Override
    public @NotNull MobDropMethod registerMobDrop(@NotNull MobDropMethod dropMethod) {
        mobDrops.computeIfAbsent(dropMethod.getMobType(), k -> new CopyOnWriteArrayList<>()).add(dropMethod);
        return dropMethod;
    }

    @Override
    public @NotNull MobDropMethod unregisterMobDrop(@NotNull MobDropMethod dropMethod) {
        List<MobDropMethod> list = mobDrops.get(dropMethod.getMobType());
        if (list != null) {
            list.remove(dropMethod);
        }
        return dropMethod;
    }

    @Override
    public @NotNull Map<Material, List<BlockDropMethod>> getBlockDrops() {
        return Collections.unmodifiableMap(blockDrops);
    }

    @Override
    public @NotNull List<BlockDropMethod> getBlockDrops(@NotNull Material material) {
        return blockDrops.getOrDefault(material, Collections.emptyList());
    }

    @Override
    public @NotNull BlockDropMethod registerBlockDrop(@NotNull BlockDropMethod dropMethod) {
        blockDrops.computeIfAbsent(dropMethod.getBlockType(), k -> new CopyOnWriteArrayList<>()).add(dropMethod);
        return dropMethod;
    }

    @Override
    public @NotNull BlockDropMethod unregisterBlockDrop(@NotNull BlockDropMethod dropMethod) {
        List<BlockDropMethod> list = blockDrops.get(dropMethod.getBlockType());
        if (list != null) {
            list.remove(dropMethod);
        }
        return dropMethod;
    }

    @Override
    public @NotNull Map<String, ChemicalCompound> getChemicalCompounds() {
        return Collections.unmodifiableMap(chemicals);
    }

    @Override
    public @Nullable ChemicalCompound getChemicalCompound(@NotNull String name) {
        return chemicals.get(name);
    }

    @Override
    public @NotNull ChemicalCompound registerChemicalCompound(@NotNull ChemicalCompound compound) {
        chemicals.put(compound.getName(), compound);
        return compound;
    }

    @Override
    public @Nullable ChemicalCompound unregisterChemicalCompound(@NotNull ChemicalCompound compound) {
        return chemicals.remove(compound.getName());
    }

    @Override
    public @Nullable ChemicalCompound unregisterChemicalCompound(@NotNull String name) {
        return chemicals.remove(name);
    }

    @Override
    public @NotNull <T extends IndustrialRevivalItem & MobDropItem> T registerMobDrop(@NotNull T item) {
        registerItem(item);
        for (MobDropMethod method : item.getDropMethods()) {
            registerProduceMethod(method);
        }
        return item;
    }

    @Override
    public @NotNull <T extends IndustrialRevivalItem & BlockDropItem> T registerBlockDrop(@NotNull T item) {
        registerItem(item);
        for (BlockDropMethod method : item.getDropMethods()) {
            registerProduceMethod(method);
        }
        return item;
    }
}