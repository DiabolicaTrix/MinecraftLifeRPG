package me.diabolicatrix.other;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.packets.PacketSyncPlayerEEP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerEEP implements IExtendedEntityProperties
{
    private final EntityPlayer player;
    private int side;

    private static final String EEPNAME = MinecraftLifeRPG.MODID + "playerEEP";

    public PlayerEEP(EntityPlayer player)
    {
        this.player = player;
        this.side = 0;
    }

    public static PlayerEEP get(EntityPlayer player)
    {
        return (PlayerEEP)player.getExtendedProperties(EEPNAME);
    }

    public static void register(EntityPlayer player)
    {
        player.registerExtendedProperties(EEPNAME, new PlayerEEP(player));
    }

    public boolean isServerSide()
    {
        return this.player instanceof EntityPlayerMP;
    }

    public void sync()
    {
        System.out.println("Sync method");
        if(this.isServerSide())
        {
            System.out.println("Sync server");
            MinecraftLifeRPG.network.sendTo(new PacketSyncPlayerEEP(this.getSide()), (EntityPlayerMP) this.player);
        }
        else
        {
            System.out.println("Sync client");
            MinecraftLifeRPG.network.sendToServer(new PacketSyncPlayerEEP(this.getSide()));
        }
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound props = new NBTTagCompound();
        
        props.setInteger("Side", this.getSide());
        
        compound.setTag(EEPNAME, props);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound props = (NBTTagCompound)compound.getTag(EEPNAME);
        
        this.setSide(props.getInteger("Side"));
    }

    @Override
    public void init(Entity entity, World world)
    {

    }

    public int getSide()
    {
        return side;
    }

    public void setSide(int side)
    {
        this.side = side;
    }

}
