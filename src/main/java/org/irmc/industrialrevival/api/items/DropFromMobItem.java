package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.recipes.methods.MobDropMethod;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lijinhong11
 */
@Getter
public class DropFromMobItem extends IndustrialRevivalItem implements MobDropItem {
    private final Set<MobDropMethod> dropMethods = new HashSet<>();

    @Override
    public MobDropMethod @NotNull [] getDropMethods() {
        return dropMethods.toArray(new MobDropMethod[0]);
    }

    public DropFromMobItem addDropMethod(@NotNull MobDropMethod dropMethod) {
        checkRegistered();
        dropMethods.add(dropMethod);
        return this;
    }
}
