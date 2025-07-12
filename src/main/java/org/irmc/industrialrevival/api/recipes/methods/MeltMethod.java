package org.irmc.industrialrevival.api.recipes.methods;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.melt.MeltedObject;
import org.irmc.industrialrevival.api.recipes.RecipeType;

import java.util.List;

/**
 * @author baluagq
 */
@Getter
public abstract class MeltMethod implements ProduceMethod {
    private final List<MeltedObject> inputs;
    private final List<MeltedObject> outputs;

    public MeltMethod(List<MeltedObject> inputs, List<MeltedObject> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public MeltMethod(MeltedObject input, MeltedObject output) {
        this.inputs = List.of(input);
        this.outputs = List.of(output);
    }

    @Override
    public ItemStack[] getIngredients() {
        return new ItemStack[0];
    }

    @Override
    public ItemStack[] getOutput() {
        return new ItemStack[0];
    }
}
