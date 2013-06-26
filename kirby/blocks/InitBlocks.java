package kirby.blocks;

import kirby.core.Kirby;
import kirby.utils.ConfigManager;
import kirby.utils.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InitBlocks {
	CommonProxy proxy = Kirby.proxy;
	
	
	
	public static Block BlockOreTi;


	
	public InitBlocks() {
		BlockOreTi = new BlockOreTi(ConfigManager.idTi, Material.rock);
		registerBlock();
	}

	/**
	 * 注册方块
	 */
	private void registerBlock() {


		proxy.registerBlock(BlockOreTi);

	}
}
