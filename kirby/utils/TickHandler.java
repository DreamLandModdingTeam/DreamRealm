package kirby.utils;

import java.util.EnumSet;
import java.util.Random;

import kirby.core.Kirby;
import kirby.entities.ParticleRegistry;
import kirby.world.world1.TeleporterDream;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.Teleporter;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler {
	
	PlayerInvSaveData invData;
	int health;
	private boolean raining;
	private boolean overlay;
	public static int defaultFog;
	//DRFIXME
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		EntityPlayer player = (EntityPlayer) tickData[0];
		boolean nightvision = false;

		if(player.dimension == Kirby.IdDream)
		{
			if(Minecraft.getMinecraft().gameSettings.renderDistance < (nightvision ? 1 : 2))
			{
				defaultFog = Minecraft.getMinecraft().gameSettings.renderDistance;
				Minecraft.getMinecraft().gameSettings.renderDistance = nightvision ? 1 : 2;
			}

			if(player.worldObj.isRaining())
			{
				raining = true;
				if(Minecraft.getMinecraft().gameSettings.renderDistance < (nightvision ? 2 : 3))
				{
					Minecraft.getMinecraft().gameSettings.renderDistance = nightvision ? 2 : 3;
				}

				Random random = new Random();
				int particlesPerTick = (3 - Minecraft.getMinecraft().gameSettings.particleSetting) * 6;
				for(int i = 0; i < particlesPerTick; i++)
				{
					float x = random.nextInt(4)-2;
					float z = random.nextInt(4)-2;
					float y = (random.nextFloat() - 0.7F)*2F;

					float vx = 0.1F + random.nextFloat() * 0.1F;
					float vz = 0.1F + random.nextFloat() * 0.1F;

					ParticleRegistry.spawnParticle("sand", player.worldObj, player.posX + x, player.posY+y, player.posZ+z, vx + player.motionX, 0.0D, vz + player.motionZ);
				}
			}
			else
			{
				if(raining == true && defaultFog < (nightvision ? 1 : 2))
				{
					raining = false;
					Minecraft.getMinecraft().gameSettings.renderDistance = nightvision ? 1 : 2;

				}
			}
		}
		
	}

	/**
	 * 
	 */
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		EntityPlayerMP player =(EntityPlayerMP) tickData[0];
		if(player.sleepTimer>50){
				if (player.dimension != Kirby.IdDream) {
					player.timeUntilPortal = 9000;
					player.mcServer.getConfigurationManager()
						.transferPlayerToDimension
							(player,Kirby.IdDream,new TeleporterDream
								(player.mcServer.worldServerForDimension(Kirby.IdDream)));
					saveInventory(player);
				//	health = player.getHealth();
						player.inventory.clearInventory(-1, -1);

			}
		
//				player.sendChatToPlayer(COLOR_WHITE+Localization.get("xxx.ooo.text"));
			
		}
		/**
		 * 在梦世界中PosY小于等于5就醒来
		 */
		if(player.dimension == Kirby.IdDream){
			if(player.posY<=5){
			player.mcServer.getConfigurationManager()
			.transferPlayerToDimension(player,0,new Teleporter
					(player.mcServer.worldServerForDimension(0)));
			loadInventory(player);
			player.inventory.mainInventory=invData.getPlayerInv();
			player.inventory.armorInventory=invData.getPlayerArmor();
			player.wakeUpPlayer(true, false, false);
			player.heal(health);
//			player.worldObj.setWorldTime(currentTime+currentTime/10);
			}
		}
		
		
	}

	/**
	 * @param player
	 */
	private void loadInventory(EntityPlayer player) {
        String s = "Inv"+player.getEntityName();
		PlayerInvSaveData invData = (PlayerInvSaveData) FMLCommonHandler
				.instance().getMinecraftServerInstance()
				.worldServerForDimension(0)
				.loadItemData(PlayerInvSaveData.class, s);

        if (invData == null)
        {
        	invData = new PlayerInvSaveData(s);
        	FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0).setItemData(s, invData);
        }

       this.invData=invData;
		
	}

	/**
	 * @param player
	 */
	private void saveInventory(EntityPlayerMP player) {
		invData = new PlayerInvSaveData("Inv"+player.getEntityName());
		invData.setData(player);
		invData.markDirty();
        FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0).setItemData("Inv"+player.getEntityName(), invData);
		
	}

	@Override
	public EnumSet<TickType> ticks() {

		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return "Sweven Tick";
	}

}
