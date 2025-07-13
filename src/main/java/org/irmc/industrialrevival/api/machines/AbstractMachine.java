package org.irmc.industrialrevival.api.machines;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.objects.ItemStackReference;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.utils.CleanedItemGetter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Abstract base class for all IndustrialRevival machines.
 * <p>
 * This class provides the foundation for machine implementations in the IndustrialRevival plugin.
 * It extends {@link IndustrialRevivalItem} and adds machine-specific functionality such as
 * recipe management, energy consumption, and processing time.
 * </p>
 * <p>
 * Machines can have multiple recipes with different input/output combinations, energy requirements,
 * and processing times. The machine recipes are managed through the {@link MachineRecipes} class.
 * </p>
 *
 * @author balugaq
 * @see IndustrialRevivalItem
 * @see MachineRecipe
 * @see MachineRecipes
 */
public abstract class AbstractMachine extends IndustrialRevivalItem {
    @Getter
    private static RecipeType recipeType = null;
    private static ItemStack recipeTypeIcon = null;
    protected final MachineRecipes machineRecipes = new MachineRecipes();

    /**
     * Adds a recipe to this machine with arrays of input and output items.
     *
     * @param processTime the time in ticks required to process this recipe
     * @param energy the energy consumption in RF/tick for this recipe
     * @param consume the array of input items required for this recipe
     * @param produce the array of output items produced by this recipe
     * @return this machine instance for method chaining
     */
    public AbstractMachine addRecipe(int processTime, int energy, ItemStack[] consume, ItemStack[] produce) {
        checkRegistered();
        machineRecipes.addRecipe(processTime, energy, consume, produce);
        return this;
    }

    /**
     * Adds a recipe to this machine with an array of input items and a single output item.
     *
     * @param processTime the time in ticks required to process this recipe
     * @param energy the energy consumption in RF/tick for this recipe
     * @param consume the array of input items required for this recipe
     * @param produce the single output item produced by this recipe
     * @return this machine instance for method chaining
     */
    public AbstractMachine addRecipe(int processTime, int energy, ItemStack[] consume, ItemStack produce) {
        checkRegistered();
        machineRecipes.addRecipe(processTime, energy, Arrays.asList(consume), Collections.singletonList(produce));
        return this;
    }

    /**
     * Adds a recipe to this machine with a single input item and a single output item.
     *
     * @param processTime the time in ticks required to process this recipe
     * @param energy the energy consumption in RF/tick for this recipe
     * @param consume the single input item required for this recipe
     * @param produce the single output item produced by this recipe
     * @return this machine instance for method chaining
     */
    public AbstractMachine addRecipe(int processTime, int energy, ItemStack consume, ItemStack produce) {
        checkRegistered();
        machineRecipes.addRecipe(processTime, energy, Collections.singletonList(consume), Collections.singletonList(produce));
        return this;
    }

    /**
     * Adds a recipe to this machine with a single input item and an array of output items.
     *
     * @param processTime the time in ticks required to process this recipe
     * @param energy the energy consumption in RF/tick for this recipe
     * @param consume the single input item required for this recipe
     * @param produce the array of output items produced by this recipe
     * @return this machine instance for method chaining
     */
    public AbstractMachine addRecipe(int processTime, int energy, ItemStack consume, ItemStack[] produce) {
        checkRegistered();
        machineRecipes.addRecipe(processTime, energy, Collections.singletonList(consume), Arrays.asList(produce));
        return this;
    }

    /**
     * Adds a pre-configured recipe to this machine.
     *
     * @param recipe the machine recipe to add
     * @return this machine instance for method chaining
     */
    public AbstractMachine addRecipe(MachineRecipe recipe) {
        checkRegistered();
        machineRecipes.addRecipe(recipe);
        return this;
    }

    /**
     * Finds the input items required to produce a specific output item.
     * <p>
     * This method searches through all recipes to find one that produces the specified output.
     * If multiple recipes produce the same output, it prioritizes recipes with single outputs.
     * </p>
     *
     * @param output the output item to search for
     * @return a map of input items and their quantities, or null if no recipe is found
     */
    public Map<ItemStackReference, Integer> findInputByOutput(ItemStack output) {
        MachineRecipe foundRecipe = null;
        for (MachineRecipe recipe : machineRecipes.getRecipes()) {
            if (recipe.getOutputs().size() == 1 && recipe.getOutputs().containsKey(output)) {
                foundRecipe = recipe;
                break;
            }
        }

        if (foundRecipe == null) {
            for (MachineRecipe recipe : machineRecipes.getRecipes()) {
                if (recipe.getOutputs().containsKey(output)) {
                    foundRecipe = recipe;
                    break;
                }
            }
        }

        if (foundRecipe == null) {
            return null;
        }

        return foundRecipe.getInputs();
    }

    @Override
    public void postRegister() {
        super.postRegister();
    }

    /**
     * Gets the icon used for the recipe type of this machine.
     * <p>
     * If no custom recipe type icon is set, it returns a cleaned version of the machine's icon.
     * </p>
     *
     * @return the recipe type icon for this machine
     */
    public ItemStack getRecipeTypeIcon() {
        if (recipeTypeIcon == null) {
            return CleanedItemGetter.clean(getIcon());
        }

        return CleanedItemGetter.clean(recipeTypeIcon);
    }

    /**
     * Sets the icon used for the recipe type of this machine.
     *
     * @param recipeTypeIcon the icon to use for the recipe type
     * @return this machine instance for method chaining
     */
    public AbstractMachine setRecipeTypeIcon(ItemStack recipeTypeIcon) {
        checkRegistered();
        this.recipeTypeIcon = recipeTypeIcon;
        this.recipeType = new RecipeType(getAddon(), getId(), getRecipeTypeIcon());
        return this;
    }

    @Override
    public void preRegister() throws Exception {
        super.preRegister();
        if (recipeTypeIcon == null) {
            this.recipeTypeIcon = CleanedItemGetter.clean(getIcon());
            this.recipeType = new RecipeType(getAddon(), getId(), getRecipeTypeIcon());
        }
    }
}
