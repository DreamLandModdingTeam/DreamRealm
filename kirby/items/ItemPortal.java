package kirby.items;

import kirby.core.Kirby;
import kirby.worldgen.dream1.TeleporterDream;
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
		// TODO Auto-generated constructor stub
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer){
			if (par3EntityPlayer instanceof EntityPlayerMP) {
				EntityPlayerMP thePlayer = (EntityPlayerMP) par3EntityPlayer;
				if (thePlayer.dimension != Kirby.Dimid) {
					thePlayer.mcServer.getConfigurationManager()
							.transferPlayerToDimension(thePlayer,Kirby.Dimid,new TeleporterDream
									(thePlayer.mcServer.worldServerForDimension(Kirby.Dimid)));
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

