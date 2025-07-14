package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.guide.GuideImplementation;
import org.irmc.industrialrevival.core.guide.GuideMode;
import org.irmc.industrialrevival.core.guide.impl.CheatGuide;
import org.irmc.industrialrevival.core.guide.impl.SurvivalGuide;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;

public class GuideListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            if (item != null && item.getType() != Material.AIR) {
                String smode = PersistentDataAPI.getString(item.getItemMeta(), Constants.ItemStackKeys.GUIDE_ITEM_KEY, null);
                if (smode == null) {
                    return;
                }

                GuideMode mode = GuideMode.valueOf(smode);
                if (mode == null) {
                    return;
                }

                Player player = e.getPlayer();
                GuideImplementation guide = IRDock.getRegistry().getGuide(mode);
                GuideUtil.openMainMenu(player, guide);
            }
        }
    }
}
