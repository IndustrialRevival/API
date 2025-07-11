package org.irmc.industrialrevival.core.guide;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.industrialrevival.utils.GuideUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GuideHistory {
    private final String playerName;
    private final List<GuideEntry> entries;

    public GuideHistory(String playerName) {
        this.entries = new ArrayList<>();
        this.playerName = playerName;
    }

    public void addMenu(SimpleMenu menu) {
        entries.add(GuideEntry.warp(IRDock.getGuide(Bukkit.getPlayer(getPlayerName())), menu));
    }

    public void removeLast() {
        if (entries.isEmpty()) {
            return;
        }

        entries.removeLast();
    }

    public void goBackMainMenu() {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            return;
        }

        entries.clear();
        GuideUtil.openMainMenu(player);
    }

    public void goBack() {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            return;
        }

        int index = entries.size() - 1;
        if (index <= -1) {
            player.closeInventory();
            return;
        }

        GuideEntry lastEntry = entries.get(index);
        if (lastEntry != null) {
            lastEntry.getContent().open(player);
            entries.remove(lastEntry);
        }
    }
}
