package kirby.items;

import kirby.utils.ConfigManager;

public class InitItems {
	
	
	ItemTest itemtest;
	
	public InitItems(){
		itemtest = new ItemTest(ConfigManager.idTest);


		registerItem();
	}

	private void registerItem() {

	}
	
}
