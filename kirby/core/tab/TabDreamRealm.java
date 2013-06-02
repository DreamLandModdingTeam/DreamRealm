package kirby.core.tab;

import kirby.blocks.InitBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabDreamRealm extends CreativeTabs {
	
	/**
	 * @param label
	 */
	public TabDreamRealm(String label) 
	{
	    super(label);
	}	

	@Override
	public ItemStack getIconItemStack() 
	{
	    return new ItemStack(InitBlocks.BlockOreTi);
	}
}
