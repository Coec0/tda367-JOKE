package com.example.illegalaliens.models.executive_orders;

public interface ExecutiveOrderObserver {
	void actOnExecutiveOrdersChange(String EO, boolean onCD);
}
