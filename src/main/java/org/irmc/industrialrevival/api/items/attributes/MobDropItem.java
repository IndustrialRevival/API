package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.recipes.methods.MobDropMethod;
import org.irmc.industrialrevival.core.services.IIRRegistry;
import org.jetbrains.annotations.NotNull;

/**
 * This interface defines an item that can be dropped by an entity.<br>
 * <br>
 *
 * @author lijinhong11
 * @author balugaq
 * @see IIRRegistry
 */
public interface MobDropItem {
    @NotNull MobDropMethod @NotNull [] getDropMethods();
}
