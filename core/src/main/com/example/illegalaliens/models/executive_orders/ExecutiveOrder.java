package com.example.illegalaliens.models.executive_orders;

import com.example.illegalaliens.models.politics.parties.Party;

public interface ExecutiveOrder {
	public void execute();
	public String getDescription();
	public Party getParty();
}
