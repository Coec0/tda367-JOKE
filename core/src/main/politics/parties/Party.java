package politics.parties;

public class Party {
	private int votes;
	private String name;
	
	public Party(String name, int votes){
		this.setVotes(votes);
		this.setName(name);
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
