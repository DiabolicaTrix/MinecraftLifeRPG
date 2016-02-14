package me.diabolicatrix.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemFarmingArea extends Item {

	public ItemFarmingArea()
	{
		this.setUnlocalizedName("itemFarmingArea");
        this.maxStackSize = 1;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		//Work In Progresss
		return itemStackIn;
	}
	
}
