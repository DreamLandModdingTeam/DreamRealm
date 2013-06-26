package kirby.world;

import kirby.world.world1.BiomeGenSkyland;
import kirby.world.world2.BiomeGenDarkForest;
import net.minecraft.world.biome.BiomeGenBase;

public class DRBiomeGenBase{

	public static BiomeGenBase darkForest;
    public static BiomeGenBase skyLand;
    
	public void init(){
		darkForest = 
				(new BiomeGenDarkForest(24)).setBiomeName("DarkForest").setTemperatureRainfall(0.3F, 0.6F);
		skyLand = 
				(new BiomeGenSkyland()).setBiomeName("SkyLand").setTemperatureRainfall(0.2F, 0.1F);
	}

	
}
