package org.irmc.industrialrevival.core.packet;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSetSlot;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerWindowItems;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.core.translation.TranslateContext;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.pigeonlib.language.LanguageManager;
import org.jetbrains.annotations.NotNull;

//TODO: move to impl
public class ItemPacketHandler implements PacketListener {
    @Override
    public void onPacketSend(@NotNull PacketSendEvent e) {
        Player p = e.getPlayer();

        switch (e.getPacketType()) {
            case PacketType.Play.Server.SET_SLOT -> {
                WrapperPlayServerSetSlot packet = new WrapperPlayServerSetSlot(e);
                if (packet.getWindowId() != 0) return;
                ItemStack item = SpigotConversionUtil.toBukkitItemStack(packet.getItem());
                if (isTarget(item)) {
                    packet.setItem(SpigotConversionUtil.fromBukkitItemStack(rename(p, item)));
                }
            }
            case PacketType.Play.Server.WINDOW_ITEMS -> {
                WrapperPlayServerWindowItems packet = new WrapperPlayServerWindowItems(e);
                if (packet.getWindowId() != 0) return;
                var items = packet.getItems();
                boolean changed = false;
                for (int i = 0; i < items.size(); i++) {
                    ItemStack it = SpigotConversionUtil.toBukkitItemStack(items.get(i));
                    if (isTarget(it)) {
                        items.set(i, SpigotConversionUtil.fromBukkitItemStack(rename(p, it)));
                        changed = true;
                    }
                }
                if (changed) {
                    packet.setItems(items);
                }
            }
            default -> {}
        }
    }

    private boolean isTarget(ItemStack item) {
        return item != null && !item.getType().isAir() && IndustrialRevivalItem.getByItem(item) != null;
    }

    private ItemStack rename(Player p, ItemStack item) {
        ItemStack clone = item.clone();
        ItemMeta meta = clone.getItemMeta();
        if (meta == null) {
            return clone;
        }

        IndustrialRevivalItem ir = IndustrialRevivalItem.getByItem(clone);
        if (ir == null) {
            return clone;
        }

        if (!ir.isAutoTranslation()) {
            return clone;
        }

        LanguageManager lm = IRDock.getLanguageManager();
        TranslateContext tc = ir.getTranslateContext();
        if (tc != null) {
            meta.displayName(tc.apply(p, item, lm.getItemName(ir.getId())));
            meta.lore(tc.apply(p, item, lm.getItemLore(ir.getId())));
        } else {
            meta.displayName(lm.getItemName(ir.getId()));
            meta.lore(lm.getItemLore(ir.getId()));
        }

        clone.setItemMeta(meta);

        return clone;
    }
}
