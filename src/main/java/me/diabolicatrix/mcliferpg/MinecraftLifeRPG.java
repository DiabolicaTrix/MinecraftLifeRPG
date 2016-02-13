package me.diabolicatrix.mcliferpg;

import me.diabolicatrix.events.OtherEventHandler;
import me.diabolicatrix.events.PlayerEventHandler;
import me.diabolicatrix.packets.PacketPlayerEEP;
import me.diabolicatrix.packets.PacketPlayerList;
import me.diabolicatrix.packets.PacketRequestPlayerList;
import me.diabolicatrix.proxy.CommonProxy;
import me.diabolicatrix.registries.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
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
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        network = NetworkRegistry.INSTANCE.newSimpleChannel("MCLRPG");
        network.registerMessage(PacketRequestPlayerList.Handler.class, PacketRequestPlayerList.class, 0, Side.SERVER);
        network.registerMessage(PacketPlayerList.Handler.class, PacketPlayerList.class, 1, Side.CLIENT);  
        network.registerMessage(PacketPlayerEEP.Handler.class, PacketPlayerEEP.class, 2, Side.SERVER);
        network.registerMessage(PacketPlayerEEP.Handler.class, PacketPlayerEEP.class, 2, Side.CLIENT);
        proxy.registerKeybinds();
        ItemRegistry.registerItems();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        if(event.getSide().isClient())
        {
            MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
        }
        MinecraftForge.EVENT_BUS.register(new OtherEventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        
        proxy.registerEntities();
        proxy.registerRenders();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}