package kirby.entities;

import kirby.core.Kirby;
import kirby.models.ModelDog;
import kirby.render.RenderDog;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * @author kirby
 * 
 */
public class InitEntities {
	EntityNightmare nightmare;

	public InitEntities() {
		// EntityRegistry.registerGlobalEntityID
		// (EntityNightmare.class, "nightmare", 250, 435, 245);
		// EntityRegistry.registerModEntity
		// (EntityNightmare.class, "nightmare", 250, Kirby.instance, 250, 5,
		// false);
		//
		// EntityRegistry.registerGlobalEntityID
		// (EntityBoss.class, "boss", 251, 435, 5555);
		// EntityRegistry.registerModEntity
		// (EntityBoss.class, "nightmare", 251, Kirby.instance, 250, 5, false);

		EntityRegistry.registerGlobalEntityID(EntityDog.class,
				"Dog", 250, 435, 245);
		EntityRegistry.registerModEntity(EntityDog.class, "Dog",
				250, Kirby.instance, 250, 5, false);
		RenderingRegistry.registerEntityRenderingHandler(EntityDog.class, new RenderDog(new ModelDog(), new ModelDog(), 1.5F));

	}
}
