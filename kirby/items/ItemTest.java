package kirby.items;

import kirby.blocks.InitBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTest extends Item{

	int find = InitBlocks.BlockOreTi.blockID;
	
	public ItemTest(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
	EntityPlayer par3EntityPlayer) {
	par3EntityPlayer.getFoodStats().setFoodLevel(10);

	int x = (int) par3EntityPlayer.posX;
	int y = (int) par3EntityPlayer.posY;
	int z = (int) par3EntityPlayer.posZ;

	int range = 64;
	Loop: for (int i = x - range; i < x + range; i++)
	for (int j = y - range; j < y + range; j++)
	for (int k = z - range; k < z + range; k++) {
	if (par2World.getBlockId(i, j, k) == find) {
	if (!par2World.isRemote) {
	par3EntityPlayer.addChatMessage("找到啦▔▽▔");
	par3EntityPlayer.addChatMessage("x:" + i + " y:" + j + " z:" + k);
	}



	par3EntityPlayer.setPosition(i,j+2,k);
	break Loop;
	}
	}


	return par1ItemStack;
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister par1IconRegister)
    {
        this.iconIndex = par1IconRegister.registerIcon("roc:ti");
	}
	}

