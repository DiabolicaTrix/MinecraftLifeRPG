package me.diabolicatrix.events;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.other.PlayerCapabilities;
import me.diabolicatrix.other.PlayerEEP;
import me.diabolicatrix.proxy.ClientProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class CommonEventHandler
{
    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event)
    {
        if(event.player != null)
        {
            //NBTTagCompound compound = event.player.getNBTTagCompound();
            //PlayerEEP.get(event.player).loadNBTData(compound);
            //PlayerEEP.get(event.player).sync();
        }
        ClientProxy.setLoaded(false);
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer && PlayerEEP.get((EntityPlayer)event.entity) == null)
        {
           // PlayerEEP.register((EntityPlayer)event.entity);
        }
    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event)
    {
        if(event.wasDeath)
        {
            if(event.original.hasCapability(MinecraftLifeRPG.PLAYER_CAP, null))
            {
                System.out.println("CAPA");
                PlayerCapabilities cap = event.original.getCapability(MinecraftLifeRPG.PLAYER_CAP, null);
                PlayerCapabilities newCap = event.entityPlayer.getCapability(MinecraftLifeRPG.PLAYER_CAP, null);
                newCap.setTest(cap.getTest());
            }
        }
    }
    
    @SubscribeEvent
    public void onAttachCapability(AttachCapabilitiesEvent.Entity event)
    {
        if(event.getEntity() instanceof EntityPlayer && !event.getEntity().worldObj.isRemote)
        {
            System.out.println("Kappa");
            event.addCapability(new ResourceLocation(MinecraftLifeRPG.MODID + ":" + event.getEntity().getUniqueID()), new PlayerCapabilities(0));
        }
    }
    
}
