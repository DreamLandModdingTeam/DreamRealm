/**
 * @author DreamLandModdingTeam
 *	Copyright (c) DreamLand Modding Team, 2013
 * 	版权许可：DreamLand 制作小组， 2013.
 */
package kirby.core;



import static kirby.core.lib.ModInfo.MOD_ID;
import static kirby.core.lib.ModInfo.MOD_NAME;
import static kirby.core.lib.ModInfo.MOD_VERSION;
import static kirby.core.lib.ModInfo.PACKET_CHANEL;

import java.io.File;

import kirby.blocks.InitBlocks;
import kirby.core.tab.TabDreamRealm;
import kirby.entities.InitEntities;
import kirby.items.InitItems;
import kirby.utils.ConfigManager;
import kirby.utils.Localization;
import kirby.utils.TickHandler;
import kirby.utils.event.DRSoundManager;
import kirby.utils.event.EventHandler;
import kirby.utils.proxy.CommonProxy;
import kirby.world.WorldGenOres;
import kirby.world.world1.WorldProviderDream;
import kirby.world.world2.WorldProviderDarkForest;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;


/**
 *mod主类
 */
@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION)//v0.0.2a
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
channels={PACKET_CHANEL})
public class Kirby {
	
		/**
		 * 加载代理
		 */
		@SidedProxy(clientSide = "kirby.utils.proxy.ClientProxy", 
							serverSide = "kirby.utils.proxy.CommonProxy")
		public static CommonProxy proxy;
	
		/**
		 * 主类实例
		 */
		@Instance(MOD_ID)
		public static Kirby instance;

		/**
		 * 配置文件路径
		 */
		public static final String CONFIG_FILE_PATH = "kirby/Dreamrealm.conf";
		
		/**
		 * 美梦
		 */
		public static int IdDream =8/*DimensionManager.getNextFreeDimId()*/;
		/**
		 * 噩梦
		 */
		public static int IdNightmare = 9/*DimensionManager.getNextFreeDimId()*/;
		
		/**
		 * Creative Tab
		 */
		public static CreativeTabs customTab = new TabDreamRealm("dreamRealm");
		
		/**
		 * @param event
		 * 预加载（设置、世界生成、注册Event）
		 */
		@PreInit
	    public void preInit(FMLPreInitializationEvent event)
	    {
			new ConfigManager(new File(event.getModConfigurationDirectory(), CONFIG_FILE_PATH));
			
			MinecraftForge.EVENT_BUS.register(new DRSoundManager());
			
			MinecraftForge.EVENT_BUS.register(new EventHandler());

			DimensionManager.registerProviderType(IdDream,WorldProviderDream.class, true);
			DimensionManager.registerDimension(IdDream, IdDream);
			DimensionManager.registerProviderType(IdNightmare,WorldProviderDarkForest.class, true);
			DimensionManager.registerDimension(IdNightmare, IdNightmare);
			
			Localization.addLocalization("/kirby/lang/", "en_US");
			
			GameRegistry.registerWorldGenerator(new WorldGenOres());
	    }
		
		/**
		 * @param event
		 * 加载（方块，物品，实体,网络处理）
		 */
		@Init
	    public void init(FMLInitializationEvent event)
	    {
			
			new InitBlocks();
			
			new InitItems();
			
			new InitEntities();
			
			TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);
			
			LanguageRegistry.instance().addStringLocalization("itemGroup.customTab", Localization.get("creativeTab.text"));
			proxy.onLoad();
			
	    }
		
		/**
		 * @param event
		 * 加载后
		 */
		@PostInit
		public void postInit(FMLPostInitializationEvent event){
			
		}
		
		
		
		/* 
		 * 主线任务↓
		 * 
		 *  DONE: 版本0.0.1
		 *  
		 *  -第一个版本
		 *  
		 *  
	 	 *	DRTODO: 版本 0.0.2
	 	 *
	 	 * -区别梦与现实(在梦里死去就醒来)                                                                                                                                       好讽刺
	 	 * -入睡后才入梦
		 * -入睡后有一定几率不入梦
		 * -添加睡眠机器(带模型，可以百分百入梦)
		 * -DONE:添加外部化语言文件
		 * 
		 * 			DRXXX v0.0.2a
		 * 			-BUG DR-0001 //fixed              ←_←也就是还没fix
		 * 
		 * 
		 *
		 *  DRTODO:  版本0.0.3 (Pretty Scary Update)
		 * 
		 * -添加噩梦
		 * -添加梦魇(模型已经完成)
		 * -添加噩梦Boss(LS+1)
		 * -添加噩梦主题方块
		 * 
		 *  DRTODO: 版本0.0.4 (Wonderful Update)
		 *  
		 *  -添加美梦以及与美梦相关的方块和生物
		 *  
		 *  DRTODO: 版本0.0.5 (Story Update)
		 *  
		 *  -添加剧情(做一个有剧情的mod)
		 *  
		 *  DRTODO: 版本0.0.6 ()
		 *  
		 *  
		 */
		
		/*
		 *支线任务↓
		 *还没有╮(￣▽￣)╭
		 *
		 *
		 *
		 *
		 *
		 */
		
}
