package politics.parties;

public class PartyFactory {

	public static Party Republican(int votes){
		return new Party("Republican", votes, votes*10);
	}
	
	public static Party Democrat(int votes){
		return new Party("Democrat", votes, votes*10);
	}
	
}
