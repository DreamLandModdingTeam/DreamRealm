package kirby.world.world1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.PortalPosition;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

/**
 * @author kirby DRXXX 1.去掉地狱门 2.不要在高空出生
 */
public class TeleporterDream extends Teleporter {

	private final WorldServer worldServerInstance;


	public TeleporterDream(WorldServer par1WorldServer) {
		super(par1WorldServer);
		worldServerInstance = par1WorldServer;
		
	}

	@Override
	public void placeInPortal(Entity par1Entity, double par2, double par4,
			double par6, float par8) {
		

		int i = MathHelper.floor_double(par1Entity.posX);
		int j = MathHelper.floor_double(par1Entity.posY) - 1;
		int k = MathHelper.floor_double(par1Entity.posZ);
		int te = j;
		while (te > 0) {
			if (!worldServerInstance.isAirBlock(i, te, k))
				break;
			else
				te--;
		}
		if (te <= 0) {
			for (int l = 0; l < 3; l++) {
				worldServerInstance.setBlock(i, j - l, k, Block.glass.blockID);
				worldServerInstance.setBlock(i - 1, j - l, k - 1,
						Block.glass.blockID);
				worldServerInstance.setBlock(i - 1, j - l, k,
						Block.glass.blockID);
				worldServerInstance.setBlock(i - 1, j - l, k + 1,
						Block.glass.blockID);
				worldServerInstance.setBlock(i, j - l, k - 1,
						Block.glass.blockID);
				worldServerInstance.setBlock(i, j - l, k + 1,
						Block.glass.blockID);
				worldServerInstance.setBlock(i + 1, j - l, k - 1,
						Block.glass.blockID);
				worldServerInstance.setBlock(i + 1, j - l, k,
						Block.glass.blockID);
				worldServerInstance.setBlock(i + 1, j - l, k + 1,
						Block.glass.blockID);
			}
		} else {
			j = te;
		}

		j = j + 3;
		par1Entity.setLocationAndAngles(i, j, k, par1Entity.rotationYaw, 0.0F);
		par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;

	}

	@Override
	public boolean placeInExistingPortal(Entity par1Entity, double par2,
			double par4, double par6, float par8) {
		return false;

	}

	@Override
	public boolean makePortal(Entity par1Entity) {
		return false;

	}

}