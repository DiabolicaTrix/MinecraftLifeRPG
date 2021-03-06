package me.diabolicatrix.proxy;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.util.Color;

import me.diabolicatrix.entities.EntityLifeTrader;
import me.diabolicatrix.gui.GuiSideSelection;
import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CommonProxy implements IGuiHandler
{
    public static final Map<String, NBTTagCompound> extendedPermEntityData = new HashMap<String, NBTTagCompound>();

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == GuiSideSelection.ID)
            return new GuiSideSelection(player);
        return null;
    }

    public void registerKeybinds()
    {

    }

    public void registerRenders()
    {

    }

    public void registerEntities()
    {
        EntityRegistry.registerModEntity(EntityLifeTrader.class, "lifeTrader", 0, MinecraftLifeRPG.instance, 50, 1, true);
    }

    public static void storePermEntityData(String uuid, NBTTagCompound compound)
    {
        extendedPermEntityData.put(uuid, compound);
    }

    public static NBTTagCompound getPermEntityData(String uuid)
    {
        return extendedPermEntityData.remove(uuid);
    }

}
