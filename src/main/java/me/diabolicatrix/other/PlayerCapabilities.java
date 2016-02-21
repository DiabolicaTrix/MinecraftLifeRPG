package me.diabolicatrix.other;

import java.util.concurrent.Callable;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.packets.PacketSyncCapabilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class PlayerCapabilities implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
{
    public int side;
    
    private EntityPlayer player;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(PlayerCapabilities.class, new PlayerCapabilities.Storage(), new PlayerCapabilities.Factory());
    }

    public PlayerCapabilities(EntityPlayer player)
    {
        this.side = 0;
        this.player = player;
    }

    public void sync()
    {
        PacketSyncCapabilities packetSync = new PacketSyncCapabilities(this.getSide());
        if(!this.player.worldObj.isRemote)
        {
            EntityPlayerMP playerMP = (EntityPlayerMP)player;
            MinecraftLifeRPG.network.sendTo(packetSync, playerMP);
        }
        else
        {
            MinecraftLifeRPG.network.sendToServer(packetSync);
        }
    }

    public void setSide(int side)
    {
        this.side = side;
    }

    public int getSide()
    {
        return this.side;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return MinecraftLifeRPG.PLAYER_CAP != null && capability == MinecraftLifeRPG.PLAYER_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return MinecraftLifeRPG.PLAYER_CAP != null && capability == MinecraftLifeRPG.PLAYER_CAP ? (T)this : null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("Side", this.getSide());
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound)
    {
        this.setSide(compound.getInteger("Side"));
    }

    public static class Storage implements Capability.IStorage<PlayerCapabilities>
    {

        @Override
        public NBTBase writeNBT(Capability<PlayerCapabilities> capability, PlayerCapabilities instance, EnumFacing side)
        {
            return null;
        }

        @Override
        public void readNBT(Capability<PlayerCapabilities> capability, PlayerCapabilities instance, EnumFacing side, NBTBase nbt)
        {

        }

    }

    public static class Factory implements Callable<PlayerCapabilities>
    {
        @Override
        public PlayerCapabilities call() throws Exception
        {
            return null;
        }
    }
}
