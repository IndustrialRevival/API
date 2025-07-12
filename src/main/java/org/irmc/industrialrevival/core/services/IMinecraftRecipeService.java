package org.irmc.industrialrevival.core.services;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.recipes.VanillaRecipeContent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IMinecraftRecipeService {
    @NotNull
    List<VanillaRecipeContent> getRecipes(@NotNull ItemStack itemStack);
    void registerRecipe(@NotNull VanillaRecipeContent vanillaRecipeContent);
}
