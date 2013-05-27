package kirby.worldgen.dream1;

import kirby.core.Kirby;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderDream extends WorldProvider {


	public ChunkCoordinates getEntrancePortalLocation()
    {
        return null;
    }

	@Override
	public void registerWorldChunkManager() {
		super.registerWorldChunkManager();
		this.dimensionId =Kirby.Dimid;
	}
	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderDream(worldObj, worldObj.getSeed());
	}
	@Override
	public float calculateCelestialAngle(long l, float f) {
		return 0;
	}
	@Override
	public float[] calcSunriseSunsetColors(float f, float f1) {
		return null;
	}
@Override
@SideOnly(Side.CLIENT)
public Vec3 getFogColor(float par1, float par2)
{
	return this.worldObj.getWorldVec3Pool().getVecFromPool(1.1D, 1.1D, 1.1D);
	}
@Override
	public boolean isSkyColored() {
		return false;
	}
@Override
	public float getCloudHeight() {
		return 8F;
	}
@Override
	public boolean canCoordinateBeSpawn(int i, int j) {
		int k = worldObj.getFirstUncoveredBlock(i, j);

		if (k == 0) {
			return false;
		} else {
			return Block.blocksList[k].blockMaterial.isSolid();
		}

	}
	@Override
	public boolean canRespawnHere()
    {
        return false;
    }
	@Override
	public double getHorizon()
    {
        return 3.0D;
    }
	@Override
	public boolean canDoRainSnowIce(Chunk chunk)
    {
        return false;
    }
	@Override
	public String getSaveFolder() {
		return "DIM-Dream";
	}

	@Override
	public String getWelcomeMessage() {
		return "Entering the Dream";
	}

	@Override
	public String getDepartMessage() {
		return "Leaving the Dream";
	}

	@Override
	public String getDimensionName() {
		return "Dream";
	}


	@Override
	public boolean getWorldHasVoidParticles() {
		return false;
	}

	@Override
	public boolean doesXZShowFog(int par1, int par2) {
		return false;
	}

	public double getMovementFactor() {
		return 10.0;
	}
	@SideOnly(Side.CLIENT)
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
    {
        return worldObj.getSkyColorBody(cameraEntity, partialTicks);
    }
}