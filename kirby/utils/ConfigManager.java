package kirby.utils;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class ConfigManager{

	private static boolean loaded;

	public static Configuration cfg;

	//Blocks
	public static int idTi;

	/**
	 * @param file
	 */
	public ConfigManager(File file) {
		if (!loaded)
		{
			cfg = new Configuration(file);
			setVaules();
		}
	}


	private void setVaules(){
		try {
			cfg.load();

			idTi = cfg.get(Configuration.CATEGORY_BLOCK,"OreTi",2223).getInt();

		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Kirby mod has a problem loading it's configuration");
		}finally {
			cfg.save();
			ConfigManager.loaded = true;
		}
	}
}
