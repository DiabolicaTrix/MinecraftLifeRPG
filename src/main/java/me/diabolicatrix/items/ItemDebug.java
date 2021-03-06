package me.diabolicatrix.items;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.other.PlayerCapabilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemDebug extends Item
{

    public ItemDebug()
    {
        this.maxStackSize = 1;
        this.setUnlocalizedName("debug");   
    }
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        /*EntityLifeTrader trader = new EntityLifeTrader(worldIn);
        float yaw = playerIn.getRotationYawHead() * -1;
        if(!worldIn.isRemote)
        {
            
            pos = pos.offset(side);
            
            System.out.println(yaw);
            trader.setPositionAndRotation( (double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, yaw, 0.0f);
            trader.rotationYawHead = yaw;
            trader.renderYawOffset = yaw;
            worldIn.spawnEntityInWorld(trader);
            trader.moveEntityWithHeading(0.0f, 0.5f);
            trader.moveEntityWithHeading(0.0f, -0.5f);
            System.out.println(yaw);
        }*/
        if(playerIn.hasCapability(MinecraftLifeRPG.PLAYER_CAP, null))
        {
            PlayerCapabilities cap = playerIn.getCapability(MinecraftLifeRPG.PLAYER_CAP, null);
            System.out.println(" Value: " + cap.getSide());
            if(playerIn.isSneaking())
            {
                cap.setSide(1);
            }
        }
        
        return true;
    }
    
}
