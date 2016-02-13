package me.diabolicatrix.events;

import me.diabolicatrix.gui.GuiRespawnMenu;
import me.diabolicatrix.gui.GuiSideSelection;
import me.diabolicatrix.other.PlayerEEP;
import me.diabolicatrix.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PlayerEventHandler
{

    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event)
    {
        if(event.player != null)
        {
            ClientProxy.setLoaded(false);
        }
    }

    @SubscribeEvent
    public void onPlayerLoginTick(TickEvent.PlayerTickEvent event)
    {
        if(event.player != null && !ClientProxy.isLoaded())
        {
            if(!(Minecraft.getMinecraft().currentScreen instanceof GuiSideSelection))
            {
                Minecraft.getMinecraft().displayGuiScreen(new GuiSideSelection(event.player));
                System.out.println("Gui Opened!");
            }
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if(ClientProxy.debug.isPressed())
        {
           PlayerEEP props = PlayerEEP.get(Minecraft.getMinecraft().thePlayer);
           System.out.println(props.getSide());
           //par2World.spawnEntityInWorld(new EntityTNTPrimed(par2World));
           //par1ItemStack.damageItem(1, par3EntityPlayer);
        }
    }
    
    @SubscribeEvent
    public void onGuiOpened(GuiOpenEvent event)
    {
        if(event.gui instanceof GuiGameOver)
        {
            event.setCanceled(true);
            Minecraft.getMinecraft().displayGuiScreen(new GuiRespawnMenu(Minecraft.getMinecraft().thePlayer));
        }
    }

}