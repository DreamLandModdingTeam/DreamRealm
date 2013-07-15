package kirby.utils.event;

import kirby.utils.TickHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;



/**
 * @author kirby
 *代码已经清空
 */
public class EventHandler {
	//add sound;
	@ForgeSubscribe
    public void onSound(SoundLoadEvent event)
    {
		String[] soundFile={"Bogaaru","Bogaaru2"};
		for(String sound:soundFile)
        event.manager.addSound("dreamrealm:"+sound+ ".ogg");
     
    }
	
	//DRXXX 漏洞百出的掉落解决
	@ForgeSubscribe
    public void realFall(LivingFallEvent event)
    {
       if(event.entity instanceof EntityPlayer&&event.distance>4){
    	  if( TickHandler.notFall.contains(((EntityPlayer)event.entity).username)){
    		  event.distance=0;
    		  TickHandler.notFall.remove(((EntityPlayer)event.entity).username);
    	  }
       }
     
    }


}
