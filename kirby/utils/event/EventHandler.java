package kirby.utils.event;

import static kirby.core.lib.Colors.COLOR_PURE_WHITE;
import kirby.core.Kirby;
import kirby.utils.Localization;
import kirby.worldgen.dream1.TeleporterDream;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * @author kirby
 * DRFIXME 这里好多东西task要搞定啊……
 */
public class EventHandler {
	
	long currentTime;
	int health;
	
	/**
	 * @param event
	 * 	DRTODO:入睡后才传送
	 *  DRTODO 睡觉前保存背包里的东西
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
							health = thePlayer.getHealth();
							currentTime=thePlayer.worldObj.getWorldTime();
							thePlayer.inventory.clearInventory(-1, -1);

				}										
					thePlayer.sendChatToPlayer(COLOR_PURE_WHITE+Localization.get("xxx.ooo.text"));
				}
			}
		}
	
	/**
	 * @param event
	 * DONE:在梦境里posY不高于10.0D时，玩家醒来_(:3J ∠)_	
	 * DRXXX 玩家在梦境里死去就醒来(死后不弹窗)
	 * DRFIXME BUG DR-0001 从梦中掉下来以后醒来结果摔死了……
	 * DRTODO 醒后背包物品恢复
	 */
	@ForgeSubscribe
	public void backToOverworld(LivingEvent event) {    
		if (event.entityLiving.dimension == Kirby.Dimid) {			
			if(event.entityLiving instanceof EntityPlayerMP){	
				EntityPlayerMP thePlayer = (EntityPlayerMP) event.entityLiving;
				if(thePlayer.posY <= 10.0D/* || thePlayer.getHealth() <= 2*/){
					thePlayer.mcServer.getConfigurationManager()
					.transferPlayerToDimension(thePlayer,0,new TeleporterDream
							(thePlayer.mcServer.worldServerForDimension(0)));
					thePlayer.wakeUpPlayer(true, false, false);
					thePlayer.heal(health);
					thePlayer.worldObj.setWorldTime(currentTime+currentTime/10);
				}
			}
		}
	}
	
//	/**
//	 * @param event
//	 * 名字好长_(:3J ∠)_34个字母……	
//	 * DRTODO 在睡觉之前储存当前背包的东西，醒来后背包的东西不丢失(好吧我发现其实好像不用这个方法)
//	 */
//	@ForgeSubscribe
//	public void theDifferenceBetweenDreamAndReality(PlayerEvent event){
//		
//	}
}
