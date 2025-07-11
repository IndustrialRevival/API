package org.irmc.industrialrevival.api.menu.gui;

import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.player.PlayerProfile;

import javax.annotation.ParametersAreNonnullByDefault;

public interface Pageable {
    @ParametersAreNonnullByDefault
    void previousPage(Player player, PlayerProfile profile, int currentPage);

    @ParametersAreNonnullByDefault
    void nextPage(Player player, PlayerProfile profile, int currentPage);
}
