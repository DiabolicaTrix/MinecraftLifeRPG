package me.diabolicatrix.other;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.packets.PacketPermEEP;
import me.diabolicatrix.packets.PacketSideEEP;
import me.diabolicatrix.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PermEEP implements IExtendedEntityProperties
{
    public final static String EXT_PROP_NAME = "PermEEP";

    private EntityPlayer player;

    public List<String> licenses;

    public PermEEP(EntityPlayer player)
    {
        this.player = player;
        this.licenses = new ArrayList<String>();
    }

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(PermEEP.EXT_PROP_NAME, new PermEEP(player));
    }

    public static final PermEEP get(EntityPlayer player)
    {
        return (PermEEP)player.getExtendedProperties(EXT_PROP_NAME);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();

        int licenseCount = this.licenses.size();
        
        properties.setInteger("LicenseCount", licenseCount);
        
        for(int i = 0; licenseCount > 0 && i < licenseCount; i++)
        {
            System.out.println("License Save");
            properties.setString("License" + i, this.licenses.get(i));
        }

        compound.setTag(EXT_PROP_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(EXT_PROP_NAME);
        
        int licenseCount = properties.getInteger("LicenseCount");
        
        for(int i = 0; licenseCount > 0 && i < licenseCount; i++)
        {
            System.out.println("License Load");
            this.licenses.add(properties.getString("License" + i));
        }
    }

    public void sync()
    {
        if(!player.worldObj.isRemote)
        {
            EntityPlayerMP player1 = (EntityPlayerMP)player;
            MinecraftLifeRPG.network.sendTo(new PacketPermEEP(this.licenses), player1);
        }
        else
        {
            MinecraftLifeRPG.network.sendToServer(new PacketPermEEP(this.licenses));
        }
    }

    private static String getSaveKey(EntityPlayer player)
    {
        return player.getUniqueID().toString() + ":" + EXT_PROP_NAME;
    }

    public static void saveProxyData(EntityPlayer player)
    {
        PermEEP playerData = PermEEP.get(player);
        NBTTagCompound savedData = new NBTTagCompound();

        playerData.saveNBTData(savedData);
        CommonProxy.storePermEntityData(getSaveKey(player), savedData);
    }

    public static void loadProxyData(EntityPlayer player)
    {
        PermEEP playerData = PermEEP.get(player);
        NBTTagCompound savedData = CommonProxy.getPermEntityData(getSaveKey(player));

        if(savedData != null)
        {
            playerData.loadNBTData(savedData);
        }
        
        playerData.sync();
    }

    public List<String> getLicenses()
    {
        return licenses;
    }

    public void setLicenses(List<String> licenses)
    {
        this.licenses = licenses;
        this.sync();
    }
    
    public void addLicense(String license)
    {
        this.licenses.add(license);
        this.sync();
    }

    @Override
    public void init(Entity entity, World world)
    {

    }

}
