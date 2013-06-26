package kirby.world.world1;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenSkyland extends BiomeGenBase
{
	public static BiomeGenSkyland me = null;
    public BiomeGenSkyland()
    {
        super(9);
        this.createBiomeDecorator();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();   
        this.theBiomeDecorator.bigMushroomsPerChunk = 20;
        this.theBiomeDecorator.sandPerChunk = 30;
        this.minHeight = 0.5F;
        this.maxHeight = 5.3F;
        me = this;
    }
    @Override
    public int getSkyColorByTemp(float f)
    {
        return 0xc0c0ff;
    }

   
}