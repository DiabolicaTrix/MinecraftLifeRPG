package me.diabolicatrix.events;

import me.diabolicatrix.gui.GuiPlayerMenu;
import me.diabolicatrix.other.PlayerEEP;
import me.diabolicatrix.proxy.ClientProxy;
import me.diabolicatrix.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeybindHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if(ClientProxy.debug.isPressed())
        {
            PlayerEEP prop = PlayerEEP.get(player);
            if(player.isSneaking())
            {
                prop.addLicense("Test");
            }
            System.out.println(CommonProxy.extendedPermEntityData);
            System.out.println(prop.getSide());
        	System.out.println(prop.getLicenses());
        }
        else if(ClientProxy.playerMenu.isPressed())
        {
        	Minecraft.getMinecraft().displayGuiScreen(new GuiPlayerMenu());
        }
        	
    }
	
}
