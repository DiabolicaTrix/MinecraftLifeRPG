package me.diabolicatrix.packets;

import io.netty.buffer.ByteBuf;
import me.diabolicatrix.other.PlayerEEP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSyncPlayerEEP implements IMessage
{
    public int side;
    
    public PacketSyncPlayerEEP() {}

    public PacketSyncPlayerEEP(int side) 
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
    
    public static class Handler implements IMessageHandler<PacketSyncPlayerEEP, IMessage>
    {

        @Override
        public IMessage onMessage(PacketSyncPlayerEEP message, MessageContext ctx)
        {
            System.out.println("Packet Received!");
            if(ctx.netHandler instanceof NetHandlerPlayClient)
            {
                System.out.println("Client: " + message.side);
                PlayerEEP.get(getPlayer()).setSide(message.side);
            }
            else if (ctx.netHandler instanceof NetHandlerPlayServer)
            {
                System.out.println("Server: " + message.side);
                PlayerEEP.get(ctx.getServerHandler().playerEntity).setSide(message.side);
            }
            return null;
        }
        
        @SideOnly(Side.CLIENT)
        public EntityPlayer getPlayer()
        {
            return Minecraft.getMinecraft().thePlayer;
        }
        
    }

}
