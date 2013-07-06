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
import kirby.utils.DRAchievements;
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
 *主类
 *各位设置任务标签吧╮(￣▽￣)╭ lDRTODO，DRXXX和DRFIXME
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
		public static int IdDream;
		/**
		 * 噩梦
		 */
		public static int IdNightmare;
		
		/**
		 * Creative Tab
		 */
		public static CreativeTabs customTab = new TabDreamRealm("dreamRealm");
		
		ConfigManager conf;
		
		/**
		 * @param event
		 * 预加载（设置、世界生成、注册Event）
		 * @throws Exception 
		 */
		@PreInit
	    public void preInit(FMLPreInitializationEvent event) throws Exception
	    {
			conf = new ConfigManager(new File(event.getModConfigurationDirectory(), CONFIG_FILE_PATH));
			
			MinecraftForge.EVENT_BUS.register(new DRSoundManager());
			
			MinecraftForge.EVENT_BUS.register(new EventHandler());

			Localization.addLocalization("/kirby/lang/", "en_US");
			
			GameRegistry.registerWorldGenerator(new WorldGenOres());
			
			DRAchievements.init(conf);
			
			IdDream = conf.getInteger("IdDream", 8);
			IdNightmare = conf.getInteger("IdNightmare", 9);
			
			
			
			DimensionManager.registerProviderType(IdDream,WorldProviderDream.class, true);
			DimensionManager.registerDimension(IdDream, IdDream);
			DimensionManager.registerProviderType(IdNightmare,WorldProviderDarkForest.class, true);
			DimensionManager.registerDimension(IdNightmare, IdNightmare);
			
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
		
		
}
