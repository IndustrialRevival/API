package org.irmc.industrialrevival.core.packet;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientHeldItemChange;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPickItem;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

public class ItemPacketHandler implements PacketListener {
    @Override
    public void onPacketReceive(@NotNull PacketReceiveEvent e) {

    }
}
