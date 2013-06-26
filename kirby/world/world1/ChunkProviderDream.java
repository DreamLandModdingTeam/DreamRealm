package kirby.world.world1;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ChunkProviderDream
    implements IChunkProvider
{

    public static int gumCount;
    private Random random;
    private NoiseGeneratorOctaves noiseGenerator1;
    private NoiseGeneratorOctaves noiseGenerator2;
    private NoiseGeneratorOctaves noiseGenerator3;
    private NoiseGeneratorOctaves noiseGenerator4;
    private NoiseGeneratorOctaves noiseGenerator5;
    public NoiseGeneratorOctaves noiseGenerator6;
    public NoiseGeneratorOctaves noiseGenerator7;
    public NoiseGeneratorOctaves noiseGenerator8;
    private World worldObj;
    private double densities[];
    private double field_28079_r[];
    private double field_28078_s[];
    private double field_28077_t[];
    private MapGenBase mapGenCaves;
    double field_28093_d[];
    double field_28092_e[];
    double field_28091_f[];
    double field_28090_g[];
    double field_28089_h[];
    int field_28088_i[][];
    public byte topBlock;
    public byte fillerBlock;



    public ChunkProviderDream(World world, long l)
    {
        field_28079_r = new double[256];
        field_28078_s = new double[256];
        field_28077_t = new double[256];
        mapGenCaves = new MapGenCaves();
        field_28088_i = new int[32][32];
        worldObj = world;
        random = new Random(l);
        noiseGenerator1 = new NoiseGeneratorOctaves(random, 16);
        noiseGenerator2 = new NoiseGeneratorOctaves(random, 16);
        noiseGenerator3 = new NoiseGeneratorOctaves(random, 8);
        noiseGenerator4 = new NoiseGeneratorOctaves(random, 4);
        noiseGenerator5 = new NoiseGeneratorOctaves(random, 4);
        noiseGenerator6 = new NoiseGeneratorOctaves(random, 10);
        noiseGenerator7 = new NoiseGeneratorOctaves(random, 16);
        noiseGenerator8 = new NoiseGeneratorOctaves(random, 8);
    }

    public void func_28071_a(int i, int j, byte abyte0[])
    {
        byte byte0 = 2;
        int k = byte0 + 1;
        byte byte1 = 33;
        int l = byte0 + 1;
        densities = initializeNoiseField(densities, i * byte0, 0, j * byte0, k, byte1, l);
        for(int i1 = 0; i1 < byte0; i1++)
        {
            for(int j1 = 0; j1 < byte0; j1++)
            {
                for(int k1 = 0; k1 < 32; k1++)
                {
                    double d = 0.25D;
                    double d1 = densities[((i1 + 0) * l + (j1 + 0)) * byte1 + (k1 + 0)];
                    double d2 = densities[((i1 + 0) * l + (j1 + 1)) * byte1 + (k1 + 0)];
                    double d3 = densities[((i1 + 1) * l + (j1 + 0)) * byte1 + (k1 + 0)];
                    double d4 = densities[((i1 + 1) * l + (j1 + 1)) * byte1 + (k1 + 0)];
                    double d5 = (densities[((i1 + 0) * l + (j1 + 0)) * byte1 + (k1 + 1)] - d1) * d;
                    double d6 = (densities[((i1 + 0) * l + (j1 + 1)) * byte1 + (k1 + 1)] - d2) * d;
                    double d7 = (densities[((i1 + 1) * l + (j1 + 0)) * byte1 + (k1 + 1)] - d3) * d;
                    double d8 = (densities[((i1 + 1) * l + (j1 + 1)) * byte1 + (k1 + 1)] - d4) * d;
                    for(int l1 = 0; l1 < 4; l1++)
                    {
                        double d9 = 0.125D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for(int i2 = 0; i2 < 8; i2++)
                        {
                            int j2 = i2 + i1 * 8 << 11 | 0 + j1 * 8 << 7 | k1 * 4 + l1;
                            char c = '\200';
                            double d14 = 0.125D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;
                            for(int k2 = 0; k2 < 8; k2++)
                            {
                                int l2 = 0;
                                if(d15 > 0.0D)
                                {
                                    l2 = Block.stone.blockID;
                                }
                                abyte0[j2] = (byte)l2;
                                j2 += c;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }

                }

            }

        }

    }

    public void func_28072_a(int i, int j, byte abyte0[])
    {
        double d = 0.03125D;
        field_28079_r = noiseGenerator4.generateNoiseOctaves(field_28079_r, i * 16, j * 16, 0, 16, 16, 1, d, d, 1.0D);
        field_28078_s = noiseGenerator4.generateNoiseOctaves(field_28078_s, i * 16, 109, j * 16, 16, 1, 16, d, 1.0D, d);
        field_28077_t = noiseGenerator5.generateNoiseOctaves(field_28077_t, i * 16, j * 16, 0, 16, 16, 1, d * 2D, d * 2D, d * 2D);
        for(int k = 0; k < 16; k++)
        {
            for(int l = 0; l < 16; l++)
            {
                int i1 = (int)(field_28077_t[k + l * 16] / 3D + 3D + random.nextDouble() * 0.25D);
                int j1 = -1;
                topBlock = (byte)Block.grass.blockID;
                fillerBlock = (byte)Block.dirt.blockID;
                byte byte0 = topBlock;
                byte byte1 = fillerBlock;
                byte byte2 = (byte)Block.stone.blockID;
                if(byte0 < 0)
                {
                    byte0 += 0;
                }
                if(byte1 < 0)
                {
                    byte1 += 0;
                }
                if(byte2 < 0)
                {
                    byte2 += 0;
                }
                for(int k1 = 127; k1 >= 0; k1--)
                {
                    int l1 = (l * 16 + k) * 128 + k1;
                    byte byte3 = abyte0[l1];
                    if(byte3 == 0)
                    {
                        j1 = -1;
                        continue;
                    }
                    if(byte3 != byte2)
                    {
                        continue;
                    }
                    if(j1 == -1)
                    {
                        if(i1 <= 0)
                        {
                            byte0 = 0;
                            byte1 = byte2;
                        }
                        j1 = i1;
                        if(k1 >= 0)
                        {
                            abyte0[l1] = byte0;
                        } else
                        {
                            abyte0[l1] = byte1;
                        }
                        continue;
                    }
                    if(j1 > 0)
                    {
                        j1--;
                        abyte0[l1] = byte1;
                    }
                }

            }

        }

    }

   
    public Chunk provideChunk(int i, int j)
    {
        random.setSeed((long)i * 0x4f9939f508L + (long)j * 0x1ef1565bd5L);
        byte abyte0[] = new byte[32768];
        func_28071_a(i, j, abyte0);
        func_28072_a(i, j, abyte0);        
        mapGenCaves.generate(this, worldObj, i, j, abyte0);
        Chunk chunk = new Chunk(worldObj, abyte0, i, j);
        chunk.generateSkylightMap();
        return chunk;
    }

    private double[] initializeNoiseField(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
        if(ad == null)
        {
            ad = new double[l * i1 * j1];
        }
        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        field_28090_g = noiseGenerator6.generateNoiseOctaves(field_28090_g, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        field_28089_h = noiseGenerator7.generateNoiseOctaves(field_28089_h, i, k, l, j1, 200D, 200D, 0.5D);
        d *= 2D;
        field_28093_d = noiseGenerator3.generateNoiseOctaves(field_28093_d, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        field_28092_e = noiseGenerator1.generateNoiseOctaves(field_28092_e, i, j, k, l, i1, j1, d, d1, d);
        field_28091_f = noiseGenerator2.generateNoiseOctaves(field_28091_f, i, j, k, l, i1, j1, d, d1, d);
        int k1 = 0;
        int l1 = 0;
        for(int j2 = 0; j2 < l; j2++)
        {
            for(int l2 = 0; l2 < j1; l2++)
            {
                double d2 = 1.0D;
                d2 *= d2;
                d2 *= d2;
                d2 = 1.0D - d2;
                double d3 = (field_28090_g[l1] + 256D) / 512D;
                d3 *= d2;
                if(d3 > 1.0D)
                {
                    d3 = 1.0D;
                }
                double d4 = field_28089_h[l1] / 8000D;
                if(d4 < 0.0D)
                {
                    d4 = -d4 * 0.29999999999999999D;
                }
                d4 = d4 * 3D - 2D;
                if(d4 > 1.0D)
                {
                    d4 = 1.0D;
                }
                d4 /= 8D;
                d4 = 0.0D;
                if(d3 < 0.0D)
                {
                    d3 = 0.0D;
                }
                d3 += 0.5D;
                d4 = (d4 * (double)i1) / 16D;
                l1++;
                double d5 = (double)i1 / 2D;
                for(int j3 = 0; j3 < i1; j3++)
                {
                    double d6 = 0.0D;
                    double d7 = (((double)j3 - d5) * 8D) / d3;
                    if(d7 < 0.0D)
                    {
                        d7 *= -1D;
                    }
                    double d8 = field_28092_e[k1] / 512D;
                    double d9 = field_28091_f[k1] / 512D;
                    double d10 = (field_28093_d[k1] / 10D + 1.0D) / 2D;
                    if(d10 < 0.0D)
                    {
                        d6 = d8;
                    } else
                    if(d10 > 1.0D)
                    {
                        d6 = d9;
                    } else
                    {
                        d6 = d8 + (d9 - d8) * d10;
                    }
                    d6 -= 8D;
                    int k3 = 32;
                    if(j3 > i1 - k3)
                    {
                        double d11 = (float)(j3 - (i1 - k3)) / ((float)k3 - 1.0F);
                        d6 = d6 * (1.0D - d11) + -30D * d11;
                    }
                    k3 = 8;
                    if(j3 < k3)
                    {
                        double d12 = (float)(k3 - j3) / ((float)k3 - 1.0F);
                        d6 = d6 * (1.0D - d12) + -30D * d12;
                    }
                    ad[k1] = d6;
                    k1++;
                }

            }

        }

        return ad;
    }

    public boolean chunkExists(int i, int j)
    {
        return true;
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        BiomeGenSkyland biomegensky = new BiomeGenSkyland();
        BlockSand.fallInstantly = true;
        int k = i * 16;
        int l = j * 16;
        random.setSeed(worldObj.getSeed());
        long l1 = (random.nextLong() / 2L) * 2L + 1L;
        long l2 = (random.nextLong() / 2L) * 2L + 1L;
        random.setSeed((long)i * l1 + (long)j * l2 ^ worldObj.getSeed());
        if(random.nextInt(9) == 0)
        {
        }
        for(int i10 = 0; i10 < 50; i10++)
        {
            int i15 = k + random.nextInt(16) + 8;
            int i18 = random.nextInt(random.nextInt(120) + 8);
            int k18 = l + random.nextInt(16) + 8;
            (new WorldGenLakes(Block.waterMoving.blockID)).generate(worldObj, random, i15, i18, k18);
        }
       

        for(int j1 = 0; j1 < 20; j1++)
        {
            int l5 = k + random.nextInt(16);
            int l10 = random.nextInt(128);
            int k15 = l + random.nextInt(16);
            (new WorldGenMinable(Block.dirt.blockID, 32)).generate(worldObj, random, l5, l10, k15);
        }

      /*  for(int k1 = 0; k1 < 2; k1++)
        {
            int i6 = k + random.nextInt(16) + 8;
            int i11 = random.nextInt(128);
            int l15 = l + random.nextInt(16) + 8;
            (new SkyGenFlowers(InitBlock.skyFlower.blockID)).generate(worldObj, random, i6, i11, l15);
        }*/

        byte byte0 = 3;
        for(int j9 = 0; j9 < byte0; j9++)
        {
            int j14 = k + random.nextInt(16) + 8;
            int k17 = l + random.nextInt(16) + 8;
            WorldGenerator worldgenerator = biomegensky.getRandomWorldGenForTrees(random);
            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
            worldgenerator.generate(worldObj, random, j14, worldObj.getHeightValue(j14, k17), k17);
        }

      
        if(random.nextInt(49) == 0)
        {
        }

        for(int i10 = 0; i10 < 40; i10++)
        {
            int i15 = k + random.nextInt(16) + 8;
            int i18 = random.nextInt(random.nextInt(120) + 8);
            int k18 = l + random.nextInt(16) + 8;
            (new DreamGenLiquids(Block.waterMoving.blockID)).generate(worldObj, random, i15, i18, k18);
        }

        SpawnerAnimals.performWorldGenSpawning(worldObj, biomegensky, k + 8, l + 8, 16, 16, random);
        BlockSand.fallInstantly = false;
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        return true;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }
    @Override
    public String makeString()
    {
        return "RandomLevelSource";
    }
    @Override
    public boolean canSave()
    {
        return true;
    }
  
    
    @Override
    public Chunk loadChunk(int i, int j)
    {
        return provideChunk(i, j);
    }

   

	@SuppressWarnings("rawtypes")
	@Override
	public List getPossibleCreatures(EnumCreatureType var1, int var2, int var3,
			int var4) {

        WorldChunkManager worldchunkmanager = worldObj.getWorldChunkManager();
        BiomeGenBase biomegenbase = worldchunkmanager.getBiomeGenAt(var2 >> 4, var4 >> 4);
        if(biomegenbase == null)
        {
            return null;
        } else
        {
            return biomegenbase.getSpawnableList(var1);
        }
//		return null;
	}


	public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return null;
    }

	@Override
	public int getLoadedChunkCount() {

		return 0;
	}

	@Override
	public void recreateStructures(int var1, int var2) {


	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}
}