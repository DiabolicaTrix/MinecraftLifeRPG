package me.diabolicatrix.other;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.packets.PacketSyncCapabilities;
import net.minecraft.entity.player.EntityPlayer;

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
        player.getCapability(MinecraftLifeRPG.PLAYER_CAP, null).setTest(message.side);
        System.out.println("HEHEH");
    }

}
