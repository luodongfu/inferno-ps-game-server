package org.arios.net.packet.out;

import org.arios.game.node.entity.player.Player;
import org.arios.game.node.item.Item;
import org.arios.game.world.map.Location;
import org.arios.net.packet.IoBuffer;
import org.arios.net.packet.OutgoingPacket;
import org.arios.net.packet.context.BuildItemContext;

/**
 * Represents the outgoing packet of clearing ground items.
 *
 * @author Emperor
 */
public final class ClearGroundItem implements OutgoingPacket<BuildItemContext> {

    /**
     * Writes the packet.
     *
     * @param buffer The buffer.
     * @param item   The item.
     */
    public static IoBuffer write(IoBuffer buffer, Item item) {
        Location l = item.getLocation();
        buffer.put(195);
        buffer.putA((l.getChunkOffsetX() << 4) | (l.getChunkOffsetY() & 0x7)).putShort(item.getId());
        return buffer;
    }

    @Override
    public void send(BuildItemContext context) {
        Player player = context.getPlayer();
        Item item = context.getItem();
        Location l = item.getLocation();
        IoBuffer buffer = UpdateAreaPosition.getBuffer(player, item.getLocation().getChunkBase());
        IoBuffer itemBuf = new IoBuffer(195);
        itemBuf.putA((l.getChunkOffsetX() << 4) | (l.getChunkOffsetY() & 0x7)).putShort(item.getId());
        player.getSession().write(buffer);
        player.getSession().write(itemBuf);
    }
}