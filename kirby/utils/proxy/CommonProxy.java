package kirby.utils.proxy;

import kirby.blocks.KirbyItemBlock;
import kirby.entities.ParticleRegistry;
import kirby.entities.fx.EntitySandFX;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import static kirby.core.lib.ModInfo.*;

public class CommonProxy {

	public void onLoad() {
		
	}
	
	public void registerBlock(Block block) {
		GameRegistry.registerBlock(block, KirbyItemBlock.class, block.getUnlocalizedName(), MOD_ID);
	}
	
	public void registerItem(Item item) {
		GameRegistry.registerItem(item,item.getUnlocalizedName(), MOD_ID);
	}
	
	public String getCurrentLanguage() {
		return null;
	}

	public void registerRenderInformation() {
		
	}
	
//	public void registerMainMenu()
//    {
//    }
	

}
