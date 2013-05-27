package kirby.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTest extends Block{

	public BlockTest(int par1, Material par2Material) {
		// TODO Auto-generated constructor stub
		super(par1, Material.rock);
		setUnlocalizedName("blockTest");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("roc:ti");
    }
}
