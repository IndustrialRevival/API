package org.irmc.industrialrevival.api.menu.gui;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.groups.NestedItemGroup;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.industrialrevival.utils.DataUtil;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class NestedGroupMenu extends PageableMenu<ItemGroup> {
    private NestedItemGroup itemGroup;

    public NestedGroupMenu(@NotNull Player player, @NotNull NestedItemGroup itemGroup) {
        this(player, itemGroup, 1);
    }

    public NestedGroupMenu(@NotNull Player player, @NotNull NestedItemGroup itemGroup, int page) {
        this(itemGroup.getIcon().displayName(), player, PlayerProfile.getProfile(player), page, itemGroup.getSubItemGroups(), new HashMap<>());
        this.itemGroup = itemGroup;
    }

    public NestedGroupMenu(@NotNull Component title, Player player, @NotNull PlayerProfile playerProfile, int currentPage, List<ItemGroup> items, Map<Integer, PageableMenu<ItemGroup>> pages) {
        super(title, player, playerProfile, currentPage, items, pages);
        drawer.addExplain(objSymbol, "Item");

        ClickHandler clickHandler = (p, i, s, m, t) -> {
            var n = NamespacedKey.fromString(DataUtil.getPDC(i.getItemMeta(), PageableMenu.GROUP_KEY, PersistentDataType.STRING));
            if (n != null) {
                var group = IRDock.getPlugin().getRegistry().getItemGroups().get(n);
                if (group != null) {
                    group.getMenuGenerator().apply(p).open(p);
                }
            }
            return false;
        };

        List<ItemGroup> cropped = crop(currentPage);
        for (var sub : cropped) {
            if (!insertFirstEmpty(getDisplayItem(sub), clickHandler, drawer.getCharPositions(objSymbol))) {
                break;
            }
        }

        GuideUtil.addToHistory(playerProfile.getGuideHistory(), this);
    }

    public ItemStack getDisplayItem(@NotNull ItemGroup sub) {
        return PageableMenu.getDisplayItem0(sub);
    }

    @Override
    public PageableMenu<ItemGroup> newMenu(@NotNull PageableMenu<ItemGroup> menu, int newPage) {
        return new NestedGroupMenu(menu.getTitle(), menu.getPlayer(), menu.getPlayerProfile(), newPage, menu.getItems(), menu.getPages());
    }

    public @Nullable ItemStack getDisplayItem(@NotNull IndustrialRevivalItem item) {
        return PageableMenu.getDisplayItem0(item);
    }

    @Nonnull
    public MatrixMenuDrawer newDrawer() {
        this.drawer = new MatrixMenuDrawer(54)
                .addLine("BbBBBBBSB")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("BPBBBBBNB");

        return drawer;
    }

    @Nonnull
    public MatrixMenuDrawer explainDrawer(@NotNull MatrixMenuDrawer matrixMenuDrawer) {
        return matrixMenuDrawer
                .addExplain("B", "Background", MenuUtil.BACKGROUND, ClickHandler.DEFAULT)
                .addExplain("T", "Settings", GuideUtil.getSettingsButton(getPlayer()), GuideUtil::openSettings)
                .addExplain("S", "Search", GuideUtil.getSearchButton(getPlayer()), GuideUtil::openSearch)
                .addExplain("b", "Back", GuideUtil.getBackButton(getPlayer()), GuideUtil::backHistory)
                .addExplain("P", "Previous Page", getPreviousPageButton(), getPreviousPageClickHandler())
                .addExplain("N", "Next Page", getNextPageButton(), getNextPageClickHandler());
    }
}
