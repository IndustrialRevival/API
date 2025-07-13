/**
 * A custom item stack representing leather armor with a specific color.
 */
package org.irmc.industrialrevival.api.custom;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.irmc.pigeonlib.items.CustomItemStack;

/**
 * The {@code LeatherItemStack} class extends the functionality of a custom item stack
 * specifically for leather armor items. It allows setting a custom color for the armor.
 *
 * @author balugaq
 */
public class LeatherItemStack extends CustomItemStack {
    /**
     * Constructs a new LeatherItemStack with the specified color and item stack.
     *
     * @param color      the color to apply to the leather armor
     * @param itemStack  the base item stack representing the leather armor
     */
    public LeatherItemStack(Color color, ItemStack itemStack) {
        super(itemStack);
        this.getBukkit().editMeta(LeatherArmorMeta.class, meta -> {
            meta.setColor(color);
        });
    }
}