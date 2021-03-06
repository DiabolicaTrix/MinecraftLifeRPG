package me.diabolicatrix.entities;

import me.diabolicatrix.gui.GuiMarket;
import me.diabolicatrix.gui.GuiTraderConfigurator;
import me.diabolicatrix.items.ItemTradeConfigurator;
import me.diabolicatrix.other.LifeTraderEEP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLifeTrader extends EntityLiving
{

    public EntityLifeTrader(World world)
    {
        super(world);
        DataWatcher dw = this.getDataWatcher();
        dw.addObject(20, "default");
        /*
         * This is the shop to open
         * 1 - Market
         */
        dw.addObject(21, 1);
        this.registerExtendedProperties(LifeTraderEEP.EXT_PROP_NAME, new LifeTraderEEP());
    }
       
    @Override
    public void onLivingUpdate() {}
    
    @Override
    public boolean isEntityInvulnerable(DamageSource source)
    {
        return true;
    }
    
    @Override
    protected boolean interact(EntityPlayer player)
    {
        if(player.getHeldItem() != null)
        {
            if(player.getHeldItem().getItem() instanceof ItemTradeConfigurator)
            {
                this.openGuiScreen(new GuiTraderConfigurator(player, this));
                return true;
            }
        }
        
        DataWatcher dw = this.getDataWatcher();
        switch(dw.getWatchableObjectInt(21))
        {
            case 1:
                this.openGuiScreen(new GuiMarket());
                break;
             default:
                break;
        }
        return true;
    }

    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        DataWatcher dw = this.getDataWatcher();
        nbt.setString("Texture", dw.getWatchableObjectString(20));
        nbt.setInteger("Shop", dw.getWatchableObjectInt(21));
    }

    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        DataWatcher dw = this.getDataWatcher();
        dw.updateObject(20, nbt.getString("Texture"));
        dw.updateObject(21, nbt.getInteger("Shop"));
    }
    
    @SideOnly(Side.CLIENT)
    public void openGuiScreen(GuiScreen gui)
    {
        Minecraft.getMinecraft().displayGuiScreen(gui);
    }

}
