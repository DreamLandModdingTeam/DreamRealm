package kirby.utils.event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author kirby
 *处理声音的类
 */
@SideOnly(Side.CLIENT)
public class DRSoundManager extends SoundManager {
	public DRSoundManager(ResourceManager par1ResourceManager,
			GameSettings par2GameSettings, File par3File) {
		super(par1ResourceManager, par2GameSettings, par3File);
		// TODO Auto-generated constructor stub
	}

	private static final String SOUND_PATH = "/mods/DreamRealm/sounds/";
	

	 /**
	 * @param event
	 * 注册声音文件
	 */
		@ForgeSubscribe
	    public void onSound(SoundLoadEvent event)
	    {
	        registerStreaming(event.manager, "fx/Bogaaru.ogg", SOUND_PATH+"fx/Bogaaru.ogg");
	        registerStreaming(event.manager, "fx/Bogaaru2.ogg", SOUND_PATH+"fx/Bogaaru2.ogg");
	     
	    }

	    private void registerSound(SoundManager manager, String name, String path)
	    {
	        try
	        {
	            URL filePath = DRSoundManager.class.getResource(path);
	            if (filePath != null) manager.soundPoolSounds.addSound(name, filePath);
	            else throw new FileNotFoundException();
	        } catch (Exception ex)
	        {
	            System.out.println(String.format("Warning: unable to load sound file %s", new Object[]{path}));
	        }
	    }

	    private void registerStreaming(SoundManager manager, String name, String path)
	    {
	    }
	}

