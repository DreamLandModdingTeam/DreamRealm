package kirby.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityNightmare extends EntityMob{
	
	private int field_70826_g = 0;
	
	public EntityNightmare(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub
		this.texture = "/mob/enderman.png";
        this.moveSpeed = 0.2F;
        this.setSize(0.6F, 2.9F);
        this.stepHeight = 1.0F;
	}

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 60;
	}
	
	 protected Entity findPlayerToAttack()
	    {
	        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);

	        if (entityplayer != null)
	        {
	            if (this.shouldAttackPlayer(entityplayer))
	            {
	                if (this.field_70826_g == 0)
	                {
	                    this.worldObj.playSoundAtEntity(entityplayer, "mob.endermen.stare", 1.0F, 1.0F);
	                }

	                if (this.field_70826_g++ == 5)
	                {
	                    this.field_70826_g = 0;
	                    this.setScreaming(true);
	                    return entityplayer;
	                }
	            }
	            else
	            {
	                this.field_70826_g = 0;
	            }
	        }

	        return null;
	    }
	
	private boolean shouldAttackPlayer(EntityPlayer par1EntityPlayer)
    {
 //       ItemStack itemstack = par1EntityPlayer.inventory.armorInventory[3];
        ItemStack itemstack1 = par1EntityPlayer.inventory.getCurrentItem();

        if (itemstack1 != null && itemstack1.itemID == Block.tnt.blockID)
        {
        	Vec3 vec3 = par1EntityPlayer.getLook(1.0F).normalize();
            Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX - par1EntityPlayer.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - (par1EntityPlayer.posY + (double)par1EntityPlayer.getEyeHeight()), this.posZ - par1EntityPlayer.posZ);
            double d0 = vec31.lengthVector();
            vec31 = vec31.normalize();
            double d1 = vec3.dotProduct(vec31);
            return d1 > 1.0D - 0.025D / d0 ? par1EntityPlayer.canEntityBeSeen(this) : false;
        }
        else
        {
            return false;
        }
    }

	public void setScreaming(boolean par1)
    {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte)(par1 ? 1 : 0)));
    }

	protected boolean teleportRandomly()
    {
        double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.posY + (double)(this.rand.nextInt(64) - 32);
        double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
        return this.teleportTo(d0, d1, d2);
    }
	
	  protected boolean teleportTo(double par1, double par3, double par5)
	    {
	        EnderTeleportEvent event = new EnderTeleportEvent(this, par1, par3, par5, 0);
	        if (MinecraftForge.EVENT_BUS.post(event)){
	            return false;
	        }

	        double d3 = this.posX;
	        double d4 = this.posY;
	        double d5 = this.posZ;
	        this.posX = event.targetX;
	        this.posY = event.targetY;
	        this.posZ = event.targetZ;
	        boolean flag = false;
	        int i = MathHelper.floor_double(this.posX);
	        int j = MathHelper.floor_double(this.posY);
	        int k = MathHelper.floor_double(this.posZ);
	        int l;

	        if (this.worldObj.blockExists(i, j, k))
	        {
	            boolean flag1 = false;

	            while (!flag1 && j > 0)
	            {
	                l = this.worldObj.getBlockId(i, j - 1, k);

	                if (l != 0 && Block.blocksList[l].blockMaterial.blocksMovement())
	                {
	                    flag1 = true;
	                }
	                else
	                {
	                    --this.posY;
	                    --j;
	                }
	            }

	            if (flag1)
	            {
	                this.setPosition(this.posX, this.posY, this.posZ);

	                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
	                {
	                    flag = true;
	                }
	            }
	        }

	        if (!flag)
	        {
	            this.setPosition(d3, d4, d5);
	            return false;
	        }
	        else
	        {
	            short short1 = 128;

	            for (l = 0; l < short1; ++l)
	            {
	                double d6 = (double)l / ((double)short1 - 1.0D);
	                float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
	                float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
	                float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
	                double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
	                double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double)this.height;
	                double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
	                this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
	            }

	            this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
	            this.playSound("mob.endermen.portal", 1.0F, 1.0F);
	            return true;
	        }
	    }

	

}
