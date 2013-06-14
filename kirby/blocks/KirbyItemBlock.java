package kirby.blocks;

import kirby.utils.Localization;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class KirbyItemBlock extends ItemBlock {
	public KirbyItemBlock(int id) {
		super(id);
		
	}

	@Override
	public int getMetadata(int i) {
		return i;
	}

	@Override
	public String getItemDisplayName(ItemStack itemstack) {
		return Localization.localize(getUnlocalizedName(itemstack));
	}
}
