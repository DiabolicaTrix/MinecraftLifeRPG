package me.diabolicatrix.events;

import me.diabolicatrix.other.PlayerEEP;
import me.diabolicatrix.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public class CommonEventHandler
{
    @SubscribeEvent
    public void onPlayerContruct(EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer && PlayerEEP.get((EntityPlayer)event.entity) == null)
        {
            PlayerEEP.register((EntityPlayer)event.entity);
        }
    }

    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event)
    {
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            
            PlayerEEP.saveProxyData((EntityPlayer) event.entity);
        }
    }
    
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if(!event.world.isRemote && event.entity instanceof EntityPlayer)
        {
            System.out.println("Join World");
            PlayerEEP.loadProxyData((EntityPlayer) event.entity);
        }
    }
    
    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event)
    {
        
        if(!event.player.worldObj.isRemote && event.player instanceof EntityPlayer)
        {
            PlayerEEP.loadProxyData((EntityPlayer) event.player);
        }
        ClientProxy.setLoaded(false);
    }
}
