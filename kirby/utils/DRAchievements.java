package kirby.utils;

import kirby.blocks.InitBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author kirby
 * 成就以及成就页
 *备用╮(￣▽￣)╭
 */
public class DRAchievements{
	
	public static Achievement[] dreamAchievements = new Achievement[3];

	/** 到达美梦 */
	public static Achievement dream;

	/** 到达噩梦 */
	public static Achievement nightmare;

	/** 成就页 */
	public static AchievementPage achievementPage;

	public static void init(ConfigManager conf) {
		try {

			dreamAchievements[0] = (new Achievement(conf.getInteger(
					"achDream", 102), "Dream", 0, 0,
					Item.bed, (Achievement) null))
					.registerAchievement();
			
			dreamAchievements[1] = (new Achievement(conf.getInteger("achNightmare",
					103), "Arrive Nightmare", 2, 0, Item.bed,
					(Achievement) null)).registerAchievement();
			
			dream = (new Achievement(conf.getInteger(
					"Dream", 100), "Dream", 1, 2,
					Item.bed, dreamAchievements[0]))
					.registerAchievement();
			
			nightmare = (new Achievement(conf.getInteger("nightmare", 101),
					"nightmare", 3, 0, Item.bed, (Achievement) null))
					.registerAchievement();
			
			
			System.out.println("DreamRealm Achievements");
			
			achievementPage = 
					new AchievementPage("DreamRealm", 
														dreamAchievements[0],
														dreamAchievements[1],  
														dream,
														nightmare);

			AchievementPage.registerAchievementPage(achievementPage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getAchievement(EntityPlayer player, Achievement ach) {
		player.addStat(ach, 1);
	}

}
