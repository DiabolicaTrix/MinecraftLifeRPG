package me.diabolicatrix.other;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.packets.PacketSideEEP;
import me.diabolicatrix.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SideEEP implements IExtendedEntityProperties
{
    public final static String EXT_PROP_NAME = "SideEEP";

    private EntityPlayer player;
    
    public int side;

    public SideEEP(EntityPlayer player)
    {
        this.player = player;
        this.side = 0;
    }

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(SideEEP.EXT_PROP_NAME, new SideEEP(player));
    }

    public static final SideEEP get(EntityPlayer player)
    {
        return (SideEEP)player.getExtendedProperties(EXT_PROP_NAME);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();
        
        properties.setInteger("Side", this.side);

        compound.setTag(EXT_PROP_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(EXT_PROP_NAME);
        
        this.side = properties.getInteger("Side");
    }

    public void sync()
    {
        if(!player.worldObj.isRemote)
        {
            EntityPlayerMP player1 = (EntityPlayerMP)player;
            MinecraftLifeRPG.network.sendTo(new PacketSideEEP(this.side), player1);
        }
        else
        {
            MinecraftLifeRPG.network.sendToServer(new PacketSideEEP(this.side));
        }
    }

    private static String getSaveKey(EntityPlayer player)
    {
        return player.getUniqueID().toString() + ":" + EXT_PROP_NAME;
    }

    public static void saveProxyData(EntityPlayer player)
    {
        SideEEP playerData = SideEEP.get(player);
        NBTTagCompound savedData = new NBTTagCompound();

        playerData.saveNBTData(savedData);
        CommonProxy.storeEntityData(getSaveKey(player), savedData);
    }

    public static void loadProxyData(EntityPlayer player)
    {
        SideEEP playerData = SideEEP.get(player);
        NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));

        if(savedData != null)
        {
            playerData.loadNBTData(savedData);
        }
        
        playerData.sync();
    }

    public int getSide()
    {
        return side;
    }

    public void setSide(int side)
    {
        this.side = side;
        this.sync();
    }

    @Override
    public void init(Entity entity, World world)
    {

    }

}
