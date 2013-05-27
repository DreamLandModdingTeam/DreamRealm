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
import kirby.utils.event.DRSoundManager;
import kirby.utils.event.EventHandler;
import kirby.utils.proxy.CommonProxy;
import kirby.worldgen.dream1.WorldProviderDream;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION)//v0.0.2a
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
channels={PACKET_CHANEL})
public class Kirby {
	
		@SidedProxy(clientSide = "kirby.utils.proxy.ClientProxy", 
								serverSide = "kirby.utils.proxy.CommonProxy")
		public static CommonProxy proxy;
	
		@Instance("mod_Kirby")
		public static Kirby instance;

		public static final String CONFIG_FILE = "kirby/main.conf";
		
		public static int Dimid = DimensionManager.getNextFreeDimId();
		public static int Dimid2 = DimensionManager.getNextFreeDimId();
		
		public static CreativeTabs customTab = new TabDreamRealm("customTab");
		
		@PreInit
	    public void preInit(FMLPreInitializationEvent event)
	    {
			new ConfigManager(new File(event.getModConfigurationDirectory(), CONFIG_FILE));
			
			Localization.addLocalization("/kirby/lang/", "en_US");
			
			MinecraftForge.EVENT_BUS.register(new DRSoundManager());
			
			MinecraftForge.EVENT_BUS.register(new EventHandler());

			DimensionManager.registerProviderType(Dimid,
					WorldProviderDream.class, true);
			DimensionManager.registerDimension(Dimid, Dimid);

	    }
		
		@Init
	    public void load(FMLInitializationEvent event)
	    {
			new InitBlocks();
			
			new InitItems();
			
			new InitEntities();

			LanguageRegistry.instance().addStringLocalization
													("itemGroup.customTab", Localization.get("creativeTab.text"));
	    }
		
		/* 
		 *  DONE: 版本0.0.1
		 *  
		 *  -
		 *  
		 *  
	 	 *	TODO: 下一个版本 0.0.2
	 	 *
	 	 * -区别梦与现实(在梦里死去就醒来)                                                                                                                                       好讽刺
	 	 * -入睡后才入梦
		 * -入睡后有一定几率不入梦
		 * -添加睡眠机器(带模型，可以百分百入梦)
		 * -添加外部化语言文件
		 *
		 *  TODO:  版本0.0.3 (Pretty Scary Update)
		 * 
		 * -添加噩梦
		 * -添加梦魇(模型已经完成)
		 * -添加噩梦Boss(LS+1)
		 * -添加噩梦主题方块
		 * 
		 *  TODO: 版本0.0.4 (Wonderful Update)
		 *  
		 *  -添加美梦
		 *  
		 *  TODO: 版本0.0.5 (Story Update)
		 *  
		 *  -添加剧情(做一个有剧情的mod)
		 *  
		 *  TODO: 版本0.0.6 ()
		 *  
		 *  
		 */
}
