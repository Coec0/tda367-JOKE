package com.example.illegalaliens.models.politics.parties;

public class Party {
	private int votes;
	private String name;
	private int points;
	


	public Party(String name, int votes, int points){
		this.setVotes(votes);
		this.setName(name);
		this.setPoints(points);
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	public void addVotes(int votes){
		setVotes(this.votes+votes);
	}
	
	public void removeVotes(int votes){
		setVotes(this.votes-votes);
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void addPoints(int points){
		setPoints(this.points + points);
	}
	
	public void removePoints(int points){
		setPoints(this.points - points);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Party other = (Party) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
