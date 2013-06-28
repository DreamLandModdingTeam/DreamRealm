package kirby.world.world2;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenDarkForest extends BiomeGenBase
{
    protected WorldGenerator bushGen;
	protected WorldGenerator sandGen;
	   
    public BiomeGenDarkForest(int par1)
    {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
       // this.spawnableCreatureList.add(new SpawnListEntry(xxx.class, 2, 1, 2));
       topBlock = (byte)Block.grass.blockID;
        fillerBlock = (byte)Block.dirt.blockID;
    }

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return (WorldGenerator) (par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree
				: (par1Random.nextInt(2) == 0 ? new WorldGenShrub(3, 0)
						: (par1Random.nextInt(3) == 0 ? new WorldGenDarkForestTrees(
								false) : new WorldGenTrees(false,
								4 + par1Random.nextInt(7), 3, 3, true))));
	}
    
    @Override
    @SideOnly(Side.CLIENT)

    /**
     * 草的颜色
     * DRXXX 这颜色不好看……
     */
    public int getBiomeGrassColor()
    {
        double d0 = (double)this.getFloatTemperature();
        double d1 = (double)this.getFloatRainfall();
        return ((ColorizerGrass.getGrassColor(d0, d1) & 16711422) + 5115470) / 2;
    }
    
    
}
