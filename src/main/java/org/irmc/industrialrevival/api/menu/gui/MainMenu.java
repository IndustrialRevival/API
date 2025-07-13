package org.irmc.industrialrevival.api.menu.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.industrialrevival.utils.DataUtil;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainMenu extends PageableMenu<ItemGroup> {
    public MainMenu(@NotNull Player player) {
        this(player, 1);
    }

    public MainMenu(@NotNull Player player, int page) {
        this(Component.text("工业复兴指南书", TextColor.color(0xFF5700)), player, PlayerProfile.getProfile(player), page, getDisplayableItemGroups(player), new HashMap<>());
    }

    public MainMenu(Component title, Player player, PlayerProfile playerProfile, int currentPage, List<ItemGroup> items, Map<Integer, PageableMenu<ItemGroup>> pages) {
        super(title, player, playerProfile, currentPage, items, pages);
        drawer.addExplain(objSymbol, "Item group");

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
        for (var item : cropped) {
            if (!insertFirstEmpty(getDisplayItem(item), clickHandler, drawer.getCharPositions(objSymbol))) {
                break;
            }
        }

    }

    public static @NotNull List<ItemGroup> getDisplayableItemGroups(@NotNull Player player) {
        List<ItemGroup> itemGroups = new ArrayList<>();
        for (var i : IRDock.getRegistry().getItemGroups().values()) {
            if (!i.isOnlyVisibleByAdmins() || player.isOp()) {
                itemGroups.add(i);
            }
        }

        return itemGroups;
    }

    @Override
    public void open(Player @NotNull ... players) {
        for (Player p : players) {
            open(p);
        }
    }

    public void open(@NotNull Player player) {
        GuideUtil.addToHistory(PlayerProfile.getProfile(player).getGuideHistory(), this);
        super.open(player);
    }

    @Override
    public PageableMenu<ItemGroup> newMenu(@NotNull PageableMenu<ItemGroup> menu, int newPage) {
        return new MainMenu(menu.getTitle(), menu.getPlayer(), menu.getPlayerProfile(), newPage, menu.getItems(), menu.getPages());
    }

    public ItemStack getDisplayItem(@NotNull ItemGroup group) {
        return PageableMenu.getDisplayItem0(group);
    }

    @Nonnull
    public MatrixMenuDrawer newDrawer() {
        this.drawer = new MatrixMenuDrawer(54)
                .addLine("BTBBBBBSB")
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
