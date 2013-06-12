package kirby.items;

import kirby.core.Kirby;
import kirby.worldgen.dream1.TeleporterDream;
import kirby.worldgen.dream2.TeleporterDrakForest;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPortal extends Item{

	public ItemPortal(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer){
			if (par3EntityPlayer instanceof EntityPlayerMP) {
				EntityPlayerMP thePlayer = (EntityPlayerMP) par3EntityPlayer;
				if (thePlayer.dimension != Kirby.Dimid2) {
					thePlayer.mcServer.getConfigurationManager()
							.transferPlayerToDimension(thePlayer,Kirby.Dimid2,new TeleporterDrakForest
									(thePlayer.mcServer.worldServerForDimension(Kirby.Dimid2)));
				}
				thePlayer.sendChatToPlayer("GOOD LUCK!");		
	}
		return par1ItemStack ;
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister par1IconRegister)
    {
        this.iconIndex = par1IconRegister.registerIcon("roc:ti");
	}
	}

