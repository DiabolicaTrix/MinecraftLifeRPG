package me.diabolicatrix.other;

import java.util.ArrayList;
import java.util.List;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.packets.PacketPlayerEEP;
import me.diabolicatrix.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerEEP implements IExtendedEntityProperties
{
    public final static String EXT_PROP_NAME = "PlayerEEP";

    private EntityPlayer player;

    public List<String> licenses;
    public int side;

    public PlayerEEP(EntityPlayer player)
    {
        this.player = player;
        this.side = 0;
        this.licenses = new ArrayList<String>();
    }

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(PlayerEEP.EXT_PROP_NAME, new PlayerEEP(player));
    }

    public static final PlayerEEP get(EntityPlayer player)
    {
        return (PlayerEEP)player.getExtendedProperties(EXT_PROP_NAME);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();
        
        int licenseCount = this.licenses.size();
        
        properties.setInteger("Side", this.side);
        
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
        
        this.side = properties.getInteger("Side");
        
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
            MinecraftLifeRPG.network.sendTo(new PacketPlayerEEP(this.licenses, this.side), player1);
        }
        else
        {
            MinecraftLifeRPG.network.sendToServer(new PacketPlayerEEP(this.licenses, this.side));
        }
    }

    private static String getSaveKey(EntityPlayer player)
    {
        return player.getUniqueID().toString() + ":" + EXT_PROP_NAME;
    }

    public static void saveProxyData(EntityPlayer player)
    {
        PlayerEEP playerData = PlayerEEP.get(player);
        NBTTagCompound savedData = new NBTTagCompound();

        playerData.saveNBTData(savedData);
        CommonProxy.storePermEntityData(getSaveKey(player), savedData);
    }

    public static void loadProxyData(EntityPlayer player)
    {
        PlayerEEP playerData = PlayerEEP.get(player);
        NBTTagCompound savedData = CommonProxy.getPermEntityData(getSaveKey(player));

        System.out.println(savedData);
        
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
