package kirby.utils.event;

import static kirby.core.lib.Colors.COLOR_BLUE_DARK;

import java.util.Iterator;
import java.util.List;

import kirby.core.Kirby;
import kirby.utils.Localization;
import kirby.worldgen.dream1.TeleporterDream;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * @author kirby
 *
 */
public class EventHandler {

	/**
	 * @param event
	 * 	TODO:入睡后才传送
	 */
	@ForgeSubscribe
	public void tpToDreamland(LivingEvent event) {
		if (event.entityLiving.isPlayerSleeping() == true){	
				if (event.entityLiving instanceof EntityPlayerMP) {
					EntityPlayerMP thePlayer = (EntityPlayerMP) event.entityLiving;
					if (thePlayer.dimension != Kirby.Dimid) {
						thePlayer.mcServer.getConfigurationManager()
							.transferPlayerToDimension
								(thePlayer,Kirby.Dimid,new TeleporterDream
									(thePlayer.mcServer.worldServerForDimension(Kirby.Dimid)));
				}										
					thePlayer.sendChatToPlayer(COLOR_BLUE_DARK+Localization.get("xxx.ooo.text"));
				}
			}
		}
	
	/**
	 * @param event
	 * DONE:在梦境里posY不高于10.0D时，玩家醒来_(:3J ∠)_	
	 * TODO:玩家在梦境里死去就醒来
	 */
	@ForgeSubscribe
	public void backToOverworld(LivingEvent event) {    
		if (event.entityLiving.dimension == Kirby.Dimid) {			
			if(event.entityLiving instanceof EntityPlayerMP){	
				EntityPlayerMP thePlayer = (EntityPlayerMP) event.entityLiving;
				if(thePlayer.posY <= 10.0D){
					thePlayer.mcServer.getConfigurationManager()
					.transferPlayerToDimension(thePlayer,0,new TeleporterDream
							(thePlayer.mcServer.worldServerForDimension(0)));
					thePlayer.wakeUpPlayer(true, false, false);				
				}
			}
		}
	}
	
	/**
	 * @param event
	 * 名字好长_(:3J ∠)_34个字母……	
	 * TODO:在睡觉之前储存当前背包的东西，醒来后背包的东西不丢失
	 */
	@ForgeSubscribe
	public void theDifferenceBetweenDreamAndReality(LivingEvent event){
		
	}
}
