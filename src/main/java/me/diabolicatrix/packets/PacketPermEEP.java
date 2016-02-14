package me.diabolicatrix.packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import me.diabolicatrix.other.PermEEP;
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

public class PacketPermEEP implements IMessage
{
    public List<String> licenses;

    public PacketPermEEP()
    {

    }

    public PacketPermEEP(List<String> licenses)
    {
        this.licenses = licenses;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        int licenseCount = buf.readInt();
        
        this.licenses = new ArrayList<String>();
        
        for(int i = 0; licenseCount > 0 && i < licenseCount; i++)
        {
            System.out.println("License Packet Load");
            this.licenses.add(ByteBufUtils.readUTF8String(buf));
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        int licenseCount = this.licenses.size();

        buf.writeInt(licenseCount);
        
        for(int i = 0; licenseCount > 0 && i < licenseCount; i++)
        {
            System.out.println("License Packet Save");
            ByteBufUtils.writeUTF8String(buf, this.licenses.get(i));
        }
    }

    public static class Handler implements IMessageHandler<PacketPermEEP, IMessage>
    {

        @Override
        public IMessage onMessage(PacketPermEEP message, MessageContext ctx)
        {
            if(ctx.netHandler instanceof NetHandlerPlayServer)
            {
                PermEEP props = PermEEP.get(ctx.getServerHandler().playerEntity);
                props.licenses = message.licenses;
            }
            else if(ctx.netHandler instanceof NetHandlerPlayClient)
            {
                if(getPlayer() != null)
                {
                    PermEEP props = PermEEP.get(getPlayer());
                    props.licenses = message.licenses;
                }
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
