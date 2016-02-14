package me.diabolicatrix.events;

import me.diabolicatrix.gui.GuiRespawnMenu;
import me.diabolicatrix.gui.GuiSideSelection;
import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.other.SideEEP;
import me.diabolicatrix.packets.PacketLoadData;
import me.diabolicatrix.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
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
            SideEEP.loadProxyData(Minecraft.getMinecraft().thePlayer);
        }
    }

}
