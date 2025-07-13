package org.irmc.industrialrevival.api.items.groups;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author lijinhong11
 */
@Getter
public class NestedItemGroup extends ItemGroup {
    private final List<ItemGroup> subItemGroups = new ArrayList<>();

    public NestedItemGroup(@NotNull NamespacedKey key, @NotNull ItemStack icon) {
        super(key, icon);
    }

    public NestedItemGroup(@NotNull NamespacedKey key, @NotNull ItemStack icon, int tier) {
        super(key, icon, tier);
    }

    public final void addItem(@NotNull IndustrialRevivalItem item) {
        throw new UnsupportedOperationException("Nested item groups can only contain sub item groups");
    }

    public void onOpen(@NotNull Player p, int page) {
        GuideUtil.openItemGroup(p, this);
    }

    final void addSubItemGroup(@NotNull ItemGroup group) {
        checkLocked();

        subItemGroups.add(group);

        tryResort();
    }

    final void tryResort() {
        List<ItemGroup> sorted = subItemGroups.stream()
                .sorted(Comparator.comparingInt(ItemGroup::getTier))
                .toList();
        subItemGroups.clear();
        subItemGroups.addAll(sorted);
    }

    private void checkLocked() {
        if (locked) {
            throw new IllegalStateException("Cannot modify a locked nested item group");
        }
    }
}
