package kirby.blocks;

import kirby.core.Kirby;
import kirby.utils.ConfigManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InitBlocks {
	
	
	public static Block BlockOreTi;


	/**
	 * 
	 */
	public InitBlocks() {
		BlockOreTi = new BlockOreTi(ConfigManager.idTi, Material.rock);
		registerBlock();
	}

	/**
	 * 
	 */
	private void registerBlock() {
		// TODO Auto-generated method stub

		Kirby.proxy.registerBlock(BlockOreTi);

	}
}
