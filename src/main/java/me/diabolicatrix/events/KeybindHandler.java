package me.diabolicatrix.events;

import me.diabolicatrix.gui.GuiPlayerMenu;
import me.diabolicatrix.gui.GuiTraderConfigurator;
import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.other.PlayerCapabilities;
import me.diabolicatrix.other.PlayerEEP;
import me.diabolicatrix.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeybindHandler
{

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if(ClientProxy.debug.isPressed())
        {
            // Minecraft.getMinecraft().displayGuiScreen(new GuiTraderConfigurator(Minecraft.getMinecraft().thePlayer, null));
            // System.out.println(PlayerEEP.get(player).getSide());
            if(player.hasCapability(MinecraftLifeRPG.PLAYER_CAP, null))
            {
                PlayerCapabilities cap = player.getCapability(MinecraftLifeRPG.PLAYER_CAP, null);
                System.out.println(" Value: " + cap.getTest());
                if(player.isSneaking())
                {
                    cap.setTest(1);
                }
            }
        }
        else if(ClientProxy.playerMenu.isPressed())
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiPlayerMenu());
        }

    }

}
