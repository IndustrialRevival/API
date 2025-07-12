package org.irmc.industrialrevival.api.recipes;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.utils.CleanedItemGetter;
import org.jetbrains.annotations.NotNull;

public record VanillaRecipeContent(@NotNull RecipeType recipeType, @NotNull RecipeChoice @NotNull [] recipe, @NotNull ItemStack result) {}
