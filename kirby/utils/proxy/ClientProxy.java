package kirby.utils.proxy;

import kirby.entities.ParticleRegistry;
import kirby.entities.fx.EntitySandFX;
import net.minecraft.src.ModLoader;
import net.minecraft.util.StringTranslate;

/**
 * @author kirby
 *客户端代理
 */
public class ClientProxy extends CommonProxy{
	
	@Override
	public void onLoad() {

		
	}
	@Override
	public String getCurrentLanguage() {
		return ModLoader.getMinecraftInstance().func_135016_M().func_135041_c().func_135034_a();
	}
	
	@Override
	public void registerRenderInformation() {
	//	RenderingRegistry.registerEntityRenderingHandler(EntityXXX.class, new RenderXXX());
	}

	
}
