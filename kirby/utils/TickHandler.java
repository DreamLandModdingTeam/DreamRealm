package kirby.utils;

import static kirby.core.lib.Colors.COLOR_WHITE;

import java.util.EnumSet;

import kirby.core.Kirby;
import kirby.worldgen.dream1.TeleporterDream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapData;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler {
PlayerInvSaveData invData;
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub

	}

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
					
						player.inventory.clearInventory(-1, -1);

			}
		
				player.sendChatToPlayer(COLOR_WHITE+Localization.get("xxx.ooo.text"));
			
		}
		if(player.dimension == Kirby.IdDream){
			if(player.posY<=0){
			player.mcServer.getConfigurationManager()
			.transferPlayerToDimension(player,0,new Teleporter
					(player.mcServer.worldServerForDimension(0)));
			player.setPosition(player.getHomePosition().posX, player.getHomePosition().posY, player.getHomePosition().posZ);
			loadInventory(player);
			player.inventory.mainInventory=invData.getPlayerInv();
			player.inventory.armorInventory=invData.getPlayerArmor();
			player.wakeUpPlayer(true, false, false);
//			player.heal(health);
//			player.worldObj.setWorldTime(currentTime+currentTime/10);
			}
		}

	}

	private void loadInventory(EntityPlayer player) {
        String s = "Inv"+player.getEntityName();
        PlayerInvSaveData invData =  (PlayerInvSaveData) FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0).loadItemData(PlayerInvSaveData.class, s);

        if (invData == null)
        {
        	invData = new PlayerInvSaveData(s);
        	FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0).setItemData(s, invData);
        }

       this.invData=invData;
		
	}

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
