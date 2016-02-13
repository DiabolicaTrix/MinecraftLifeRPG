package me.diabolicatrix.registries;

import me.diabolicatrix.items.ItemDebug;
import me.diabolicatrix.items.ItemTradeConfigurator;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry
{
    public static Item debugItem;
    public static Item traderConfiguratorItem;
    
    public static void registerItems()
    {
        debugItem = new ItemDebug();
        traderConfiguratorItem = new ItemTradeConfigurator();
        GameRegistry.registerItem(debugItem, "itemDebug");
        GameRegistry.registerItem(traderConfiguratorItem, "itemTraderConfigurator");
    }
    
    public static void registerRecipes()
    {
        
    }
}
