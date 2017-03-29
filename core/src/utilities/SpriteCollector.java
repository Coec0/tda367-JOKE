package utilities;

public class SpriteCollector {
	static SpriteCollector SC;
	
	private SpriteCollector(){
		//Singleton
	}
	
	public SpriteCollector getInstace(){
		if (SC == null)
			SC = new SpriteCollector();
		return SC;
	}
}
