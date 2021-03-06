package me.diabolicatrix.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRequestPlayerList implements IMessage
{
    
    @Override
    public void fromBytes(ByteBuf buf)
    {
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
    }

    public static class Handler implements IMessageHandler<PacketRequestPlayerList, PacketPlayerList> {
        
        @Override
        public PacketPlayerList onMessage(PacketRequestPlayerList message, MessageContext ctx) {
            return new PacketPlayerList(MinecraftServer.getServer().getAllUsernames());
        }
    }
    
}
