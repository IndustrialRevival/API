package org.irmc.industrialrevival.core.services;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.recipes.VanillaRecipeContent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service interface for managing Minecraft recipes in the IndustrialRevival plugin.
 * Provides methods to retrieve and register vanilla-like recipes.
 *
 * @author balugaq
 */
public interface IMinecraftRecipeService {
    /**
     * Retrieves all recipes that produce the given item stack.
     *
     * @param itemStack the item stack to find recipes for
     * @return a list of recipe contents that produce the given item stack
     */
    @NotNull
    List<VanillaRecipeContent> getRecipes(@NotNull ItemStack itemStack);

    /**
     * Registers a new vanilla-like recipe with the server.
     *
     * @param vanillaRecipeContent the recipe content to register
     */
    void registerRecipe(@NotNull VanillaRecipeContent vanillaRecipeContent);
}