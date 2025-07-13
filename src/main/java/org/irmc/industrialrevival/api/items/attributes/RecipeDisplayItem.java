package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.recipes.DefaultRecipeDisplay;
import org.irmc.industrialrevival.api.recipes.RecipeType;

/**
 * This interface defines a machine which needs to show a custom recipe instead of `item.getRecipe()`.
 *
 * @author lijinhong11
 * @see RecipeType
 * @see DefaultRecipeDisplay
 */
public interface RecipeDisplayItem extends ItemAttribute {
    /**
     * @param maker The recipe type's industrial revival item.
     * @return the recipe for the machine.
     */
    ItemStack[] getRecipe(IndustrialRevivalItem maker);
}
