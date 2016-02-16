package me.diabolicatrix.events;

import me.diabolicatrix.other.PlayerEEP;
import me.diabolicatrix.proxy.ClientProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
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
            //PlayerEEP.get(event.player).sync();
        }
        ClientProxy.setLoaded(false);
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer && PlayerEEP.get((EntityPlayer)event.entity) == null)
        {
            PlayerEEP.register((EntityPlayer)event.entity);
        }
    }
    
    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        NBTTagCompound nbt = new NBTTagCompound();
        PlayerEEP.get(event.original).saveNBTData(nbt);
        PlayerEEP.get(event.entityPlayer).loadNBTData(nbt);
    }
    
    @SubscribeEvent
    public void onPlayerJoinWorld(EntityJoinWorldEvent event)
    {
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            PlayerEEP.get((EntityPlayer) event.entity).sync();
        }
    }
}
