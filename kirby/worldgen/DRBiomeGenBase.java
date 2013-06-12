package kirby.worldgen;

import kirby.worldgen.dream1.BiomeGenSkyland;
import kirby.worldgen.dream2.BiomeGenDarkForest;
import net.minecraft.world.biome.BiomeGenBase;

public class DRBiomeGenBase extends BiomeGenBase{

	public DRBiomeGenBase(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}

	public static final BiomeGenBase DarkForest = 
			(new BiomeGenDarkForest(24)).setBiomeName("DarkForest").setTemperatureRainfall(0.3F, 0.6F);
	public static final BiomeGenBase SkyLand = 
			(new BiomeGenSkyland()).setBiomeName("SkyLand").setTemperatureRainfall(0.2F, 0.1F);
}
