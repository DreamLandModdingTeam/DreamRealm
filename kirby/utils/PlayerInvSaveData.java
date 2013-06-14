package kirby.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.WorldSavedData;

public class PlayerInvSaveData extends WorldSavedData {
	ItemStack[] playerInv;
	ItemStack[] playerArmor;
	public PlayerInvSaveData(String par1Str) {
		super(par1Str);
		
	}
public void setData(EntityPlayer player){
	this.playerInv=player.inventory.mainInventory.clone();
	this.playerArmor=player.inventory.armorInventory.clone();
}
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		NBTTagList nbttaglist = nbttagcompound.getTagList("inventory");
        this.playerInv = new ItemStack[36];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.playerInv.length)
            {
                this.playerInv[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
        NBTTagList nbttaglist2 = nbttagcompound.getTagList("armor");
        this.playerArmor = new ItemStack[4];

        for (int i = 0; i < nbttaglist2.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist2.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.playerArmor.length)
            {
                this.playerArmor[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
        
        
	}

	/**
	 * @return the playerInv
	 */
	public ItemStack[] getPlayerInv() {
		return playerInv;
	}
	/**
	 * @return the playerArmor
	 */
	public ItemStack[] getPlayerArmor() {
		return playerArmor;
	}
	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		 NBTTagList nbttaglist = new NBTTagList();

	        for (int i = 0; i < playerInv.length; ++i)
	        {
	            if (playerInv[i] != null)
	            {
	                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	                nbttagcompound1.setByte("Slot", (byte)i);
	                playerInv[i].writeToNBT(nbttagcompound1);
	                nbttaglist.appendTag(nbttagcompound1);
	            }
	        }

	        nbttagcompound.setTag("inventory", nbttaglist);
	        
	        NBTTagList nbttaglist2 = new NBTTagList();

	        for (int i = 0; i < playerArmor.length; ++i)
	        {
	            if (playerArmor[i] != null)
	            {
	                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	                nbttagcompound1.setByte("Slot", (byte)i);
	                playerArmor[i].writeToNBT(nbttagcompound1);
	                nbttaglist.appendTag(nbttagcompound1);
	            }
	        }

	        nbttagcompound.setTag("armor", nbttaglist2);
	        

	}

}
