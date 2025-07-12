package org.irmc.industrialrevival.api.menu.gui;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.objects.enums.GuideMode;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.industrialrevival.utils.DataUtil;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.industrialrevival.utils.MenuUtil;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class NormalGroupMenu extends PageableMenu<IndustrialRevivalItem> {
    private ItemGroup itemGroup;

    public NormalGroupMenu(Player player, ItemGroup itemGroup) {
        this(player, itemGroup, 1);
    }

    public NormalGroupMenu(Player player, ItemGroup itemGroup, int page) {
        this(itemGroup.getIcon().displayName(), player, PlayerProfile.getProfile(player), page, itemGroup.getItems(), new HashMap<>());
        this.itemGroup = itemGroup;
    }

    public NormalGroupMenu(Component title, Player player, PlayerProfile playerProfile, int currentPage, List<IndustrialRevivalItem> items, Map<Integer, PageableMenu<IndustrialRevivalItem>> pages) {
        super(title, player, playerProfile, currentPage, items, pages);
        drawer.addExplain(objSymbol, "Item");

        ClickHandler clickHandler = (p, i, s, m, t) -> {
            var guideMode = playerProfile.getGuideSettings(GuideSettings.GUIDE_MODE);

            if (i == null) {
                return false;
            }

            var n = NamespacedKey.fromString(DataUtil.getPDC(i.getItemMeta(), PageableMenu.ITEM_KEY, PersistentDataType.STRING));
            if (n != null) {
                var item = IRDock.getPlugin().getRegistry().getItems().get(n);
                if (item != null) {
                    if (guideMode == GuideMode.CHEAT && p.isOp()) {
                        tryGiveItem(p, item, i, t.isRightClick() ? i.getMaxStackSize() : 1);
                    } else {
                        GuideUtil.lookupItem(player, i, 1);
                    }
                }
            }

            return false;
        };

        List<IndustrialRevivalItem> cropped = crop(currentPage);
        for (var item : cropped) {
            if (!insertFirstEmpty(getDisplayItem(item), clickHandler, drawer.getCharPositions(objSymbol))) {
                break;
            }
        }

        GuideUtil.addToHistory(playerProfile.getGuideHistory(), this);
    }

    public static void tryGiveItem(Player player, IndustrialRevivalItem ir, ItemStack itemStack, int amount) {
        player.getInventory().addItem(ir.getIcon().asQuantity(amount));
        player.sendMessage(Component.text("已获取 " + amount + "x ").append(ir.getIcon().displayName()));
    }

    @Override
    public PageableMenu<IndustrialRevivalItem> newMenu(PageableMenu<IndustrialRevivalItem> menu, int newPage) {
        return new NormalGroupMenu(menu.getTitle(), menu.getPlayer(), menu.getPlayerProfile(), newPage, menu.getItems(), menu.getPages());
    }

    public ItemStack getDisplayItem(IndustrialRevivalItem item) {
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
    public MatrixMenuDrawer explainDrawer(MatrixMenuDrawer matrixMenuDrawer) {
        return matrixMenuDrawer
                .addExplain("B", "Background", MenuUtil.BACKGROUND, ClickHandler.DEFAULT)
                .addExplain("T", "Settings", GuideUtil.getSettingsButton(getPlayer()), GuideUtil::openSettings)
                .addExplain("S", "Search", GuideUtil.getSearchButton(getPlayer()), GuideUtil::openSearch)
                .addExplain("b", "Back", GuideUtil.getBackButton(getPlayer()), GuideUtil::backHistory)
                .addExplain("P", "Previous Page", getPreviousPageButton(), getPreviousPageClickHandler())
                .addExplain("N", "Next Page", getNextPageButton(), getNextPageClickHandler());
    }
}
