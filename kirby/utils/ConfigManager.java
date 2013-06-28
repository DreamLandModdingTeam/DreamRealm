package kirby.utils;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class ConfigManager{

	private static boolean loaded;

	public static Configuration config;

	
	/**
	 * 方块
	 */
	public static int idTi;
	
	
	/**
	 * 物品
	 */
	public static int idTest;

	/**
	 * @param file
	 */
	public ConfigManager(File file) {
		if (!loaded)
		{
			config = new Configuration(file);
			setVaules();
		}
	}


	/**
====================================================================
====================================================================
	 */
	private void setVaules(){
		try {
			config.load();

			idTi = this.getBlockID("Titanium ore",2223);
			
			idTest = this.getItemID("tset", 10086);

		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "DreamRealm mod has a problem loading it's configuration");
		}finally {
			config.save();
			ConfigManager.loaded = true;
		}
	}
	
	
	/**
	 * @param PropertyName
	 * @param DefaultValue
	 * @return
	 * @throws Exception
	 */
	public String getGeneralProperties(String PropertyName, String DefaultValue) throws Exception
	{
	    if(this == null)
	    {
	        throw new NullPointerException();
	    }
	    return config.get("general", PropertyName, DefaultValue).getString();
	}

	/**
	 * @param name
	 * @param defaultValue
	 * @return
	 * @throws Exception
	 */
	public Boolean getBoolean(String name, Boolean defaultValue)throws Exception{
		if(this == null){
			throw new NullPointerException();
		}
		return config.get("general", name, defaultValue).getBoolean(defaultValue);
	}

	/**
	 * @param name
	 * @param defaultValue
	 * @return
	 * @throws Exception
	 */
	public int getInteger(String name, Integer defaultValue)throws Exception{
		if(this == null){
			throw new NullPointerException();
		}
		return config.get("general", name, defaultValue).getInt();
	}

	public int getItemID(String ItemName, int DefaultValue) throws Exception
	{
	    if(this == null)
	    {
	        throw new NullPointerException();
	    }
	    return config.getItem("item", "ID." + ItemName, DefaultValue).getInt();
	}

	public int getBlockID(String BlockName, int DefaultID) throws Exception
	{
	    if( this  == null)
	    {
	        throw new NullPointerException();
	    }
	    return config.getBlock("ID." + BlockName, DefaultID).getInt();
	}

	public int getKeyCode(String keyName, int defaultKey) throws Exception{
		if(this == null)
			 throw new NullPointerException();
		return config.get("key", "KB." + keyName, defaultKey).getInt();
	}

}
