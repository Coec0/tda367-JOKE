package politics;

import com.badlogic.gdx.utils.Array;

import politics.parties.Party;

public class Parliament {

	private Array<Party> parties;
	
	public Parliament(){
		parties = new Array<Party>(false, 5);
	}
	
	public void voteParty(Party party){
		if(!parties.contains(party, false)){
			addParty(party);
		} else {
			parties.get(parties.indexOf(party, false)).addVotes(party.getVotes());
		}
	}
	
	public Array<Party> getParties(){
		return parties;
	}
	
	private void addParty(Party party){
		parties.add(party);
	}
	
	

}
