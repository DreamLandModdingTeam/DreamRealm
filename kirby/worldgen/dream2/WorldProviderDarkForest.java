package kirby.worldgen.dream2;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kirby.core.Kirby;
import kirby.worldgen.DRBiomeGenBase;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderDarkForest extends WorldProvider {
	@Override
	public void registerWorldChunkManager() {
		worldChunkMgr = new WorldChunkManagerHell(DRBiomeGenBase.DarkForest,
				1.0F, 0.0F);
		this.dimensionId = Kirby.Dimid2;
	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderDarkForest(worldObj, worldObj.getSeed(), true);
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		return 0.5F;
	}
	@Override
	public String getDimensionName() {
		return "DarkForest";
	}
	
	@Override
	public String getSaveFolder() {
		return "DIM-DarkForest";
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
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float par1, float par2){
		return this.worldObj.getWorldVec3Pool().getVecFromPool(0.1D, 0.1D, 0.1D);
	}
	
	@Override
	public double getHorizon()
    {
        return 69.0D;
    }
}
