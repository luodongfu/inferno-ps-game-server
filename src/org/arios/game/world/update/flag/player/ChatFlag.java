package org.arios.game.world.update.flag.player;

import org.arios.game.node.entity.player.info.Rights;
import org.arios.game.world.update.flag.UpdateFlag;
import org.arios.game.world.update.flag.context.ChatMessage;
import org.arios.net.packet.IoBuffer;
import org.arios.tools.StringUtils;

/**
 * Handles the chat flag.
 *
 * @author Emperor
 */
public class ChatFlag extends UpdateFlag<ChatMessage> {

    /**
     * Constructs a new {@code ChatFlag.java} {@code Object}.
     *
     * @param context The context.
     */
    public ChatFlag(ChatMessage context) {
        super(context);
    }

    @Override
    public void write(IoBuffer buffer) {
        byte[] chatStr = new byte[256];
        chatStr[0] = (byte) context.getText().length();
        int offset = 1 + StringUtils.encryptPlayerChat(chatStr, 0, 1, context.getText().length(), context.getText().getBytes());
        buffer.putLEShortA(context.getEffects()); // 0x8000 does something
        // (you'd need to send
        // something extra.
        buffer.put((byte) (context.getPlayer().getIronmanManager().isIronman() ? 0 : Rights.getChatIcon(context.getPlayer())));
        buffer.putS(offset + 1);
        buffer.putReverseA(chatStr, 0, offset);
    }

    @Override
    public int data() {
        return maskData();
    }

    @Override
    public int ordinal() {
        return 9;
    }

    /**
     * Gets the mask data of the chat update flag.
     *
     * @return The mask data.
     */
    public static int maskData() {
        return 0x10;
    }
}