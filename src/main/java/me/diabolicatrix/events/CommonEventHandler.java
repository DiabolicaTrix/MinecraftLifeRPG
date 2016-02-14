package me.diabolicatrix.events;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.other.PermEEP;
import me.diabolicatrix.other.SideEEP;
import me.diabolicatrix.proxy.ClientProxy;
import me.diabolicatrix.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public class CommonEventHandler
{
    @SubscribeEvent
    public void onPlayerContruct(EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer && SideEEP.get((EntityPlayer)event.entity) == null)
        {
            SideEEP.register((EntityPlayer)event.entity);
        }
        if(event.entity instanceof EntityPlayer && PermEEP.get((EntityPlayer)event.entity) == null)
        {
            PermEEP.register((EntityPlayer)event.entity);
        }
    }

    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event)
    {
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            SideEEP.saveProxyData((EntityPlayer)event.entity);
            PermEEP.saveProxyData((EntityPlayer)event.entity);
        }
    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerLoggedOutEvent event)
    {
        if(event.player.worldObj.isRemote)
        {
            ClientProxy.setLoaded(false);
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            PermEEP.loadProxyData((EntityPlayer) event.entity);
        }
    }
}
