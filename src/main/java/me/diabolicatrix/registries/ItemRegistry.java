package me.diabolicatrix.registries;

import me.diabolicatrix.items.ItemDebug;
import me.diabolicatrix.items.ItemFarmingArea;
import me.diabolicatrix.items.ItemTradeConfigurator;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry
{
    public static Item debugItem;
    public static Item traderConfiguratorItem;
    public static Item farmingArea;
    
    public static void registerItems()
    {
        debugItem = new ItemDebug();
        traderConfiguratorItem = new ItemTradeConfigurator();
        farmingArea = new ItemFarmingArea();
        
        GameRegistry.registerItem(debugItem, "itemDebug");
        GameRegistry.registerItem(traderConfiguratorItem, "itemTraderConfigurator");
        GameRegistry.registerItem(farmingArea, "itemFarmingArea");
    }
    
    public static void registerRecipes()
    {
        
    }
}
