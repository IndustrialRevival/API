package org.irmc.industrialrevival.core.services.impl;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.elements.melt.MeltedType;
import org.irmc.industrialrevival.api.elements.tinker.TinkerType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
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

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
    public @NotNull Collection<ItemGroup> getAllItemGroups() {
        return Collections.unmodifiableCollection(itemGroups.values());
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
        machineMenuPresets.put(menuPreset.getKey(), menuPreset);
        return menuPreset;
    }

    @Override
    public @Nullable MachineMenuPreset unregisterMenuPreset(@NotNull NamespacedKey key) {
        return machineMenuPresets.remove(key);
    }

    @Override
    public @NotNull MachineMenuPreset unregisterMenuPreset(@NotNull MachineMenuPreset menuPreset) {
        return machineMenuPresets.remove(menuPreset.getKey());
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
    public @NotNull Map<NamespacedKey, ProduceMethod> getProduceMethods() {
        return Collections.unmodifiableMap(produceMethods.stream().collect(Collectors.toMap(ProduceMethod::getKey, m -> m)));
    }

    @Override
    public @NotNull Collection<ProduceMethod> getAllProduceMethods() {
        return Collections.unmodifiableCollection(produceMethods);
    }

    @Override
    public @NotNull Collection<ProduceMethod> getProduceMethodByInput(@NotNull List<ItemStack> inputs) {
        return produceMethods.stream()
                .filter(method -> method.getInput().containsAll(inputs))
                .toList();
    }

    @Override
    public @NotNull Collection<ProduceMethod> getProduceMethodByInput(@NotNull ItemStack @NotNull ... inputs) {
        return getProduceMethodByInput(Arrays.asList(inputs));
    }

    @Override
    public @NotNull Collection<ProduceMethod> getProduceMethodByInput(ItemStack inputs) {
        return getProduceMethodByInput(Collections.singletonList(inputs));
    }

    @Override
    public @NotNull Collection<ProduceMethod> getProduceMethodByOutput(@NotNull List<ItemStack> outputs) {
        return produceMethods.stream()
                .filter(method -> outputs.stream().allMatch(method.getOutput()::contains))
                .toList();
    }

    @Override
    public @NotNull Collection<ProduceMethod> getProduceMethodByOutput(@NotNull ItemStack @NotNull ... outputs) {
        return getProduceMethodByOutput(Arrays.asList(outputs));
    }

    @Override
    public @NotNull Collection<ProduceMethod> getProduceMethodByOutput(ItemStack outputs) {
        return getProduceMethodByOutput(Collections.singletonList(outputs));
    }

    @Override
    public @NotNull Collection<ProduceMethod> getProduceMethodByRecipeType(@NotNull RecipeType recipeType) {
        return produceMethods.stream()
                .filter(method -> method.getRecipeType().equals(recipeType))
                .toList();
    }

    @Override
    public @NotNull <T extends ProduceMethod> Collection<T> getAllProduceMethod(@NotNull Class<T> clazz) {
        return produceMethods.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .toList();
    }

    @Override
    public @NotNull Collection<ProduceMethod> registerProduceMethod(@NotNull ProduceMethod produceMethod) {
        produceMethods.add(produceMethod);
        return Collections.singletonList(produceMethod);
    }

    @Override
    public @Nullable ProduceMethod unregisterProduceMethod(@NotNull NamespacedKey key) {
        return produceMethods.stream()
                .filter(method -> method.getKey().equals(key))
                .findFirst()
                .map(method -> {
                    produceMethods.remove(method);
                    return method;
                })
                .orElse(null);
    }

    @Override
    public @NotNull Collection<ProduceMethod> unregisterProduceMethod(@NotNull ProduceMethod produceMethod) {
        produceMethods.remove(produceMethod);
        return Collections.singletonList(produceMethod);
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
    public @NotNull Set<MeltedType> getAllMeltedTypes() {
        return Collections.unmodifiableSet(meltTypes.values().stream().collect(Collectors.toSet()));
    }

    @Override
    public @Nullable MeltedType getMeltedType(@NotNull NamespacedKey key) {
        return meltTypes.get(key);
    }

    @Override
    public @NotNull MeltedType registerMeltedType(@NotNull MeltedType type) {
        meltTypes.put(type.getKey(), type);
        return type;
    }

    @Override
    public @Nullable MeltedType unregisterMeltedType(@NotNull NamespacedKey key) {
        return meltTypes.remove(key);
    }

    @Override
    public @NotNull MeltedType unregisterMeltedType(@NotNull MeltedType type) {
        return meltTypes.remove(type.getKey());
    }

    @Override
    public @NotNull Map<TinkerType, TinkerProduct> getTinkerRecipes(@NotNull MeltedType type) {
        return Collections.unmodifiableMap(tinkerProducts.getOrDefault(type, Collections.emptyMap()));
    }

    @Override
    public @NotNull TinkerProduct registerTinkerRecipe(@NotNull MeltedType type, @NotNull TinkerProduct product) {
        tinkerProducts.computeIfAbsent(type, k -> new ConcurrentHashMap<>()).put(product.getType(), product);
        return product;
    }

    @Override
    public @NotNull Map<TinkerType, TinkerProduct> unregisterTinkerRecipes(@NotNull MeltedType type) {
        Map<TinkerType, TinkerProduct> removed = tinkerProducts.remove(type);
        return removed != null ? removed : Collections.emptyMap();
    }

    @Override
    public @NotNull TinkerProduct unregisterTinkerRecipe(@NotNull TinkerProduct product) {
        tinkerProducts.forEach((type, map) -> map.remove(product.getType()));
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
        mobDrops.computeIfAbsent(dropMethod.getEntityType(), k -> new CopyOnWriteArrayList<>()).add(dropMethod);
        return dropMethod;
    }

    @Override
    public @NotNull MobDropMethod unregisterMobDrop(@NotNull MobDropMethod dropMethod) {
        List<MobDropMethod> list = mobDrops.get(dropMethod.getEntityType());
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
        blockDrops.computeIfAbsent(dropMethod.getMaterial(), k -> new CopyOnWriteArrayList<>()).add(dropMethod);
        return dropMethod;
    }

    @Override
    public @NotNull BlockDropMethod unregisterBlockDrop(@NotNull BlockDropMethod dropMethod) {
        List<BlockDropMethod> list = blockDrops.get(dropMethod.getMaterial());
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
}