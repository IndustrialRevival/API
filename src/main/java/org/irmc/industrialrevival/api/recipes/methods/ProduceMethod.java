package org.irmc.industrialrevival.api.recipes.methods;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.recipes.RecipeType;

/**
 * ProduceMethod interface defines the contract for all production methods.
 * It provides access to recipe type information and input/output items.
 *
 * @author balugaq
 * @since 1.0
 */
public interface ProduceMethod {
    /**
     * Gets the type of recipe used for production.
     *
     * @return the recipe type
     */
    RecipeType getRecipeType();

    /**
     * Gets the array of ingredient items required for the recipe.
     *
     * @return the array of ingredient items
     */
    ItemStack[] getIngredients();

    /**
     * Gets the array of output items produced by the recipe.
     *
     * @return the array of output items
     */
    ItemStack[] getOutput();
}