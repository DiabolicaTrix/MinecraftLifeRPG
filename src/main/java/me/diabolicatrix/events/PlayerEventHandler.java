package me.diabolicatrix.events;

import me.diabolicatrix.gui.GuiSideSelection;
import me.diabolicatrix.other.PlayerEEP;
import me.diabolicatrix.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PlayerEventHandler
{

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
    public void onActionPerformed(ActionPerformedEvent.Pre event)
    {
        if(event.gui instanceof GuiGameOver && event.button.id == 0)
        {
            //PlayerEEP.loadProxyData(Minecraft.getMinecraft().thePlayer);
        }
    }

}
