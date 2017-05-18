package executive_orders;

import politics.parties.Party;

public interface ExecutiveOrder {
	public void execute();
	public String getDescription();
	public Party getParty();
}
