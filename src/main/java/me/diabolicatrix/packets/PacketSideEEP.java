package me.diabolicatrix.packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import me.diabolicatrix.other.SideEEP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSideEEP implements IMessage
{
    public int side;

    public PacketSideEEP()
    {

    }

    public PacketSideEEP(int side)
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

    public static class Handler implements IMessageHandler<PacketSideEEP, IMessage>
    {

        @Override
        public IMessage onMessage(PacketSideEEP message, MessageContext ctx)
        {
            if(ctx.netHandler instanceof NetHandlerPlayServer)
            {
                SideEEP props = SideEEP.get(ctx.getServerHandler().playerEntity);
                props.side = message.side;
            }
            else if(ctx.netHandler instanceof NetHandlerPlayClient)
            {
                if(getPlayer() != null)
                {
                    SideEEP props = SideEEP.get(getPlayer());
                    props.side = message.side;
                }
            }
            return null;
        }
        
        @SideOnly(Side.CLIENT)
        public static EntityPlayer getPlayer()
        {
            return Minecraft.getMinecraft().thePlayer;
        }
    }

}
