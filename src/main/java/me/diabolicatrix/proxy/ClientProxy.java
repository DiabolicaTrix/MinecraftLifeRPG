package me.diabolicatrix.proxy;

import org.lwjgl.input.Keyboard;

import me.diabolicatrix.entities.EntityLifeTrader;
import me.diabolicatrix.entities.RenderLifeTrader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    public static boolean isLoaded = false;
    public static String[] playerList;
    public static KeyBinding debug;
    public static KeyBinding playerMenu;

    public static String[] getPlayerList()
    {
        return playerList;
    }

    public static void setPlayerList(String[] playerList)
    {
        ClientProxy.playerList = playerList;
    }

    public static boolean isLoaded()
    {
        return isLoaded;
    }

    public static void setLoaded(boolean isLoaded)
    {
        ClientProxy.isLoaded = isLoaded;
    }

    @Override
    public void registerRenders()
    {
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        RenderingRegistry.registerEntityRenderingHandler(EntityLifeTrader.class, new RenderLifeTrader(renderManager, new ModelBiped(), 0.5F));
    }

    @Override
    public void registerKeybinds()
    {
        debug = new KeyBinding("mclrpg.keybind.debug", Keyboard.KEY_DELETE, "mclrpg.keybind.categories");
        playerMenu = new KeyBinding("mclrpg.keybind.playerMenu", Keyboard.KEY_Y, "mclrpg.keybind.categories.player");

        ClientRegistry.registerKeyBinding(debug);
    }

}
