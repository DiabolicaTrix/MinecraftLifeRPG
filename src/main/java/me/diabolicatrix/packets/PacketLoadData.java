package me.diabolicatrix.packets;

import io.netty.buffer.ByteBuf;
import me.diabolicatrix.other.SideEEP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketLoadData implements IMessage
{

    public PacketLoadData()
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {

    }

    @Override
    public void toBytes(ByteBuf buf)
    {

    }

    public static class Handler implements IMessageHandler<PacketLoadData, IMessage>
    {

        @Override
        public IMessage onMessage(PacketLoadData message, MessageContext ctx)
        {
            SideEEP.loadProxyData(ctx.getServerHandler().playerEntity);
            return null;
        }
    }

}
