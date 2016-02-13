package me.diabolicatrix.other;

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
    public final static String EXT_PROP_NAME = "MCLRPGEEP";

    private EntityPlayer player;

    private int side;

    public PlayerEEP(EntityPlayer player)
    {
        this.player = player;
        this.side = 0;
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
            MinecraftLifeRPG.network.sendTo(new PacketPlayerEEP(this.side), player1);
        }
        else
        {
            MinecraftLifeRPG.network.sendToServer(new PacketPlayerEEP(this.side));
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
        CommonProxy.storeEntityData(getSaveKey(player), savedData);
    }

    public static void loadProxyData(EntityPlayer player)
    {
        PlayerEEP playerData = PlayerEEP.get(player);
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
