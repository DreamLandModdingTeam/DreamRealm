package kirby.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOreTi extends Block {

	public BlockOreTi(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName("block.ti");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("dreamrealm:ti");
	}
}
