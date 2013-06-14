package kirby.utils.proxy;

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
		return StringTranslate.getInstance().getCurrentLanguage();
	}
	
	@Override
	public void registerRenderInformation() {
	//	RenderingRegistry.registerEntityRenderingHandler(EntityXXX.class, new RenderXXX());
	}
}
