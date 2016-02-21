package me.diabolicatrix.other;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.packets.PacketSyncCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ScheduledPacketTask implements Runnable
{
    private EntityPlayer player;
    private PacketSyncCapabilities message;

    public ScheduledPacketTask(EntityPlayer player, PacketSyncCapabilities message)
    {
        this.player = player;
        this.message = message;
    }
    
    @Override
    public void run()
    {
        EntityPlayer player = this.player == null ? getPlayer() : this.player;
        player.getCapability(MinecraftLifeRPG.PLAYER_CAP, null).setSide(message.side);
    }

    @SideOnly(Side.CLIENT)
    private EntityPlayer getPlayer()
    {
        return Minecraft.getMinecraft().thePlayer;
    }

}
