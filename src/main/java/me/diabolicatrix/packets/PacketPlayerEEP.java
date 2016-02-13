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

public class PacketPlayerEEP implements IMessage
{
    public int side;

    public PacketPlayerEEP()
    {

    }

    public PacketPlayerEEP(int side)
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

    public static class Handler implements IMessageHandler<PacketPlayerEEP, IMessage>
    {

        @Override
        public IMessage onMessage(PacketPlayerEEP message, MessageContext ctx)
        {
            if(ctx.netHandler instanceof NetHandlerPlayServer)
            {
                PlayerEEP props = PlayerEEP.get(ctx.getServerHandler().playerEntity);
                props.setSide(message.side);
            }
            else if(ctx.netHandler instanceof NetHandlerPlayClient)
            {
                PlayerEEP props = PlayerEEP.get(getPlayer());
                props.setSide(message.side);
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
