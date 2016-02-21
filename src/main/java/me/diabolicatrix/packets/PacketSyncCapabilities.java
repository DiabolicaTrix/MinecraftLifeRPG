package me.diabolicatrix.packets;

import io.netty.buffer.ByteBuf;
import me.diabolicatrix.other.ScheduledPacketTask;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSyncCapabilities implements IMessage
{
    public int side;
    
    public PacketSyncCapabilities() {}

    public PacketSyncCapabilities(int side) 
    {
        this.side = side;
    }
    
    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.side = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.side);
    }
    
    public static class ServerHandler implements IMessageHandler<PacketSyncCapabilities, IMessage>
    {
        
        @Override
        public IMessage onMessage(PacketSyncCapabilities message, MessageContext ctx)
        {
            MinecraftServer.getServer().addScheduledTask(new ScheduledPacketTask(ctx.getServerHandler().playerEntity, message));
            return null;
        }
        
    }
    
    @SideOnly(Side.CLIENT)
    public static class ClientHandler implements IMessageHandler<PacketSyncCapabilities, IMessage>
    {

        @Override
        public IMessage onMessage(PacketSyncCapabilities message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(new ScheduledPacketTask(null, message));
            return null;
        }
        
    }

}
