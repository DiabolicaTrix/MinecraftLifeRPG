package me.diabolicatrix.events;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.other.PlayerCapabilities;
import me.diabolicatrix.proxy.ClientProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class CommonEventHandler
{
    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event)
    {
        if(event.player != null)
        {
            event.player.getCapability(MinecraftLifeRPG.PLAYER_CAP, null).sync();
        }
        ClientProxy.setLoaded(false);
    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event)
    {
        if(event.wasDeath)
        {
            if(event.original.hasCapability(MinecraftLifeRPG.PLAYER_CAP, null))
            {
                PlayerCapabilities cap = event.original.getCapability(MinecraftLifeRPG.PLAYER_CAP, null);
                PlayerCapabilities newCap = event.entityPlayer.getCapability(MinecraftLifeRPG.PLAYER_CAP, null);
                newCap.setSide(cap.getSide());
            }
        }
    }
    
    @SubscribeEvent
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        if(!event.player.worldObj.isRemote)
        {
            event.player.getCapability(MinecraftLifeRPG.PLAYER_CAP, null).sync();
        }
    }
    
    @SubscribeEvent
    public void onAttachCapability(AttachCapabilitiesEvent.Entity event)
    {
        if(event.getEntity() instanceof EntityPlayer)
        {
            event.addCapability(new ResourceLocation(MinecraftLifeRPG.MODID + ":PLAYER_CAP"), new PlayerCapabilities((EntityPlayer) event.getEntity()));
        }
    }
    
}
