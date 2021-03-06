package org.arios.net.packet.out;

import org.arios.net.packet.IoBuffer;
import org.arios.net.packet.OutgoingPacket;
import org.arios.net.packet.context.ConfigContext;

/**
 * The config outgoing packet.
 *
 * @author Emperor
 */
public class Config implements OutgoingPacket<ConfigContext> {

    @Override
    public void send(ConfigContext context) {
        IoBuffer buffer;
        if (context.getValue() < Byte.MIN_VALUE || context.getValue() > Byte.MAX_VALUE) {
            buffer = new IoBuffer(9);
            buffer.putShort(context.getId());
            buffer.putIntA(context.getValue());
        } else {
            buffer = new IoBuffer(185);
            buffer.putS(context.getValue());
            buffer.putLEShortA(context.getId());
        }
        context.getPlayer().getDetails().getSession().write(buffer);
    }
}