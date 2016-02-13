package me.diabolicatrix.events;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.other.PlayerEEP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OtherEventHandler
{
    @SubscribeEvent
    public void onPlayerContruct(EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer && PlayerEEP.get((EntityPlayer)event.entity) == null)
        {
            PlayerEEP.register((EntityPlayer)event.entity);
        }
    }

    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event)
    {
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            NBTTagCompound playerData = new NBTTagCompound();
            ((PlayerEEP)(event.entity.getExtendedProperties(PlayerEEP.EXT_PROP_NAME))).saveNBTData(playerData);
            MinecraftLifeRPG.proxy.storeEntityData(((EntityPlayer)event.entity).getUniqueID().toString(), playerData);
            PlayerEEP.saveProxyData((EntityPlayer)event.entity);
        }
    }
}
