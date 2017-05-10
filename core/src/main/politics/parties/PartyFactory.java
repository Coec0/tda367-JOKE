package politics.parties;

public class PartyFactory {

	public static Party Republican(int votes){
		return new Party("Republican", votes);
	}
	
	public static Party Democrat(int votes){
		return new Party("Democrat", votes);
	}
	
}
