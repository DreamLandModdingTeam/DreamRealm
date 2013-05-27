package kirby.utils.proxy;

import kirby.blocks.KirbyItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import static kirby.core.lib.ModInfo.*;

public class CommonProxy {

	public void onLoad() {
	//	TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);
	}
	
	public void registerBlock(Block block) {
		Item.itemsList[block.blockID] = null;
		Item.itemsList[block.blockID] = new KirbyItemBlock(block.blockID - 256, block.getUnlocalizedName());
	}
	
	public void registerItem(Item item) {
		GameRegistry.registerItem(item,item.getUnlocalizedName(), MOD_NAME);
	}
	
	public String getCurrentLanguage() {
		return null;
	}

	public void registerRenderInformation() {
		// TODO Auto-generated method stub
		
	}
}
