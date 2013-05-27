package kirby.entities.boss;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.world.World;

public class EntityBoss  extends EntityLiving implements IBossDisplayData, IRangedAttackMob{

	public double targetX;
    public double targetY;
    public double targetZ;
	
	public EntityBoss(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub
		this.setEntityHealth(this.getMaxHealth());
        this.texture = "/mob/enderdragon/ender.png";
        this.noClip = true;
        this.isImmuneToFire = true;
        this.targetY = 100.0D;
        this.ignoreFrustumCheck = true;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLiving entityliving, float f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDragonHealth() {
		// TODO Auto-generated method stub
		 return this.dataWatcher.getWatchableObjectInt(16);
	}

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 400;
	}

	 protected void entityInit()
	    {
	        super.entityInit();
	        this.dataWatcher.addObject(16, new Integer(this.getMaxHealth()));
	    }
	 
	 public String getEntityName(){
		 return "xxxxx";
	 }
}
