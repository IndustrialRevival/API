package org.irmc.industrialrevival.api.recipes;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

public record VanillaRecipeContent(@NotNull RecipeType recipeType, @NotNull RecipeChoice @NotNull [] recipe,
                                   @NotNull ItemStack result) {
}
