package me.diabolicatrix.mcliferpg;

import me.diabolicatrix.events.CommonEventHandler;
import me.diabolicatrix.events.KeybindHandler;
import me.diabolicatrix.events.PlayerEventHandler;
import me.diabolicatrix.other.PlayerCapabilities;
import me.diabolicatrix.packets.PacketPlayerList;
import me.diabolicatrix.packets.PacketRequestPlayerList;
import me.diabolicatrix.packets.PacketSyncCapabilities;
import me.diabolicatrix.proxy.CommonProxy;
import me.diabolicatrix.registries.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = "mclrpg", name = "Minecraft Life RPG", version = "0.1")
public class MinecraftLifeRPG
{
    public static final String MODID = "mclrpg";

    public static SimpleNetworkWrapper network;

    @Instance(MODID)
    public static MinecraftLifeRPG instance;

    @SidedProxy(clientSide = "me.diabolicatrix.proxy.ClientProxy", serverSide = "me.diabolicatrix.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    @CapabilityInject(PlayerCapabilities.class)
    public static final Capability<PlayerCapabilities> PLAYER_CAP = null;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        network = NetworkRegistry.INSTANCE.newSimpleChannel("MCLRPG");
        network.registerMessage(PacketRequestPlayerList.Handler.class, PacketRequestPlayerList.class, 0, Side.SERVER);
        network.registerMessage(PacketPlayerList.Handler.class, PacketPlayerList.class, 1, Side.CLIENT);
        network.registerMessage(PacketSyncCapabilities.ClientHandler.class, PacketSyncCapabilities.class, 3, Side.CLIENT);
        network.registerMessage(PacketSyncCapabilities.ServerHandler.class, PacketSyncCapabilities.class, 3, Side.SERVER);
        proxy.registerKeybinds();
        ItemRegistry.registerItems();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        if(event.getSide().isClient())
        {
            MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
            MinecraftForge.EVENT_BUS.register(new KeybindHandler());
        }
        MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        
        PlayerCapabilities.register();
        
        proxy.registerEntities();
        proxy.registerRenders();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
