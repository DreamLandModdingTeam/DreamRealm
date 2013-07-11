package kirby.render;

import kirby.entities.EntityDog;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDog extends RenderLiving
{
    public RenderDog(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
    {
        super(par1ModelBase, par3);
        this.setRenderPassModel(par2ModelBase);
    }

    protected float getTailRotation(EntityDog par1EntityWolf, float par2)
    {
        return par1EntityWolf.getTailRotation();
    }

    protected int func_82447_a(EntityDog par1EntityWolf, int par2, float par3)
    {
        float f1;

        if (par2 == 0 && par1EntityWolf.getWolfShaking())
        {
            f1 = par1EntityWolf.getBrightness(par3) * par1EntityWolf.getShadingWhileShaking(par3);
            this.loadTexture(par1EntityWolf.getTexture());
            GL11.glColor3f(f1, f1, f1);
            return 1;
        }
        else if (par2 == 1 && par1EntityWolf.isTamed())
        {
            this.loadTexture("/mob/wolf_collar.png");
            f1 = 1.0F;
            int j = par1EntityWolf.getCollarColor();
            GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.func_82447_a((EntityDog)par1EntityLiving, par2, par3);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLiving par1EntityLiving, float par2)
    {
        return this.getTailRotation((EntityDog)par1EntityLiving, par2);
    }

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
