package com.example.illegalaliens.models.executive_orders;

import com.example.illegalaliens.models.politics.parties.Party;

public interface ExecutiveOrder {
	void execute();
	String getDescription();
	Party getParty();
}
