package me.diabolicatrix.other;

import java.util.concurrent.Callable;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class PlayerCapabilities implements ICapabilityProvider
{
    public int test;
    
    public static void register()
    {
        CapabilityManager.INSTANCE.register(PlayerCapabilities.class, new PlayerCapabilities.Storage(), new PlayerCapabilities.Factory());
    }
    
    public PlayerCapabilities(int test)
    {
        this.test = test;
    }
    
    public void setTest(int test) {this.test = test;}
    public int getTest() {return this.test;}

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return MinecraftLifeRPG.PLAYER_CAP != null && capability == MinecraftLifeRPG.PLAYER_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (MinecraftLifeRPG.PLAYER_CAP != null && capability == MinecraftLifeRPG.PLAYER_CAP) return (T)this;
        return null;
    }

    public static class Storage implements Capability.IStorage<PlayerCapabilities>
    {

        @Override
        public NBTBase writeNBT(Capability<PlayerCapabilities> capability, PlayerCapabilities instance, EnumFacing side)
        {
            NBTTagCompound compound = new NBTTagCompound();
            
            compound.setInteger("Test", instance.getTest());
            
            return compound;
        }

        @Override
        public void readNBT(Capability<PlayerCapabilities> capability, PlayerCapabilities instance, EnumFacing side, NBTBase nbt)
        {
            NBTTagCompound compound = (NBTTagCompound) nbt;
            
            instance.setTest(compound.getInteger("Test"));
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
