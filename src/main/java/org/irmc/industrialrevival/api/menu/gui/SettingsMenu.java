package org.irmc.industrialrevival.api.menu.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Warning;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.player.GuideSetting;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsMenu extends PageableMenu<GuideSetting<?>> {
    public SettingsMenu(@NotNull Player player) {
        this(player, 1);
    }

    public SettingsMenu(@NotNull Player p, int page) {
        this(Component.text("设置", TextColor.color(0x4abfa0)), p, PlayerProfile.getProfile(p), page, new ArrayList<>(PlayerProfile.getProfile(p).getGuideSettings().getSettings().values()), new HashMap<>());
    }

    public SettingsMenu(@NotNull Component title, Player p, @NotNull PlayerProfile playerProfile, int currentPage, List<GuideSetting<?>> settings, Map<Integer, PageableMenu<GuideSetting<?>>> pages) {
        super(title, p, playerProfile, currentPage, settings, pages);
        drawer.addExplain(objSymbol, "Settings");

        ClickHandler clickHandler = (p2, i, s, m, t) -> {
            int[] slots = getDrawer().getCharPositions(objSymbol);
            int index = 0;
            for (var s2 : slots) {
                if (s2 != s) {
                    index++;
                } else {
                    break;
                }
            }

            GuideSetting<?> settings2 = getItems().get(index * getCurrentPage());
            settings2.onClick(p2, i, s, m, t);
            GuideUtil.removeLastHistory(playerProfile);
            GuideUtil.openSettings(p2, currentPage);
            return false;
        };

        List<GuideSetting<?>> cropped = crop(currentPage);
        for (var item : cropped) {
            if (!insertFirstEmpty(getDisplayItem0(p, playerProfile, item), clickHandler, getDrawer().getCharPositions('i'))) {
                break;
            }
        }

        GuideUtil.addToHistory(playerProfile.getGuideHistory(), this);
    }

    @Warning(reason = "Not implemented, use getDisplayItem0(Player, PlayerSettings) instead.")
    @Override
    public @Nullable ItemStack getDisplayItem(GuideSetting<?> item) {
        return null;
    }

    @Nonnull
    public MatrixMenuDrawer newDrawer() {
        this.drawer = new MatrixMenuDrawer(54)
                .addLine("bTBBBBBSB")
                .addLine("BiiiiiiiB")
                .addLine("BiiiiiiiB")
                .addLine("BiiiiiiiB")
                .addLine("BiiiiiiiB")
                .addLine("BPBBBBBNB");

        return drawer;
    }

    @Nonnull
    public MatrixMenuDrawer explainDrawer(@NotNull MatrixMenuDrawer matrixMenuDrawer) {
        return matrixMenuDrawer
                .addExplain("B", "Background", MenuUtil.BACKGROUND, ClickHandler.DEFAULT)
                .addExplain("T", "Settings", GuideUtil.getSettingsButton(getPlayer()), GuideUtil::openSettings)
                .addExplain("S", "Search", GuideUtil.getSearchButton(getPlayer()), GuideUtil::openSearch)
                .addExplain("b", "Back", GuideUtil.getBackButton(getPlayer()), ((player, clickedItem, clickedSlot, clickedMenu, clickType) -> {
                    GuideUtil.backHistory(player, clickedItem, clickedSlot, clickedMenu, clickType);
                    GuideUtil.backHistory(player, clickedItem, clickedSlot, clickedMenu, clickType);
                    return false;
                }))
                .addExplain("P", "Previous Page", getPreviousPageButton(), getPreviousPageClickHandler())
                .addExplain("N", "Next Page", getNextPageButton(), getNextPageClickHandler());
    }

    @Override
    public PageableMenu<GuideSetting<?>> newMenu(@NotNull PageableMenu<GuideSetting<?>> menu, int newPage) {
        return new SettingsMenu(menu.getTitle(), menu.getPlayer(), menu.getPlayerProfile(), newPage, menu.getItems(), menu.getPages());
    }
}
