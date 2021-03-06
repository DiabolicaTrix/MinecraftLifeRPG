package me.diabolicatrix.packets;

import io.netty.buffer.ByteBuf;
import me.diabolicatrix.proxy.ClientProxy;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketPlayerList implements IMessage
{

    public String[] playerList;
    
    public PacketPlayerList()
    {
    }

    public PacketPlayerList(String[] playerList)
    {
        this.playerList = playerList;
    }
    
    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.playerList = new String[buf.readInt()];
        for(int i = 0; i < this.playerList.length; i++)
        {
            this.playerList[i] = ByteBufUtils.readUTF8String(buf);
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.playerList.length);
        for(String username : this.playerList)
        {
            ByteBufUtils.writeUTF8String(buf, username);
        }
    }
    
    public static class Handler implements IMessageHandler<PacketPlayerList, IMessage>
    {

        @Override
        public IMessage onMessage(PacketPlayerList message, MessageContext ctx)
        {
            ClientProxy.setPlayerList(message.playerList);
            return null;
        }
        
    }

}
