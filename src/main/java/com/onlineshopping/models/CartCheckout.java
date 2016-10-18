package com.onlineshopping.models;

import java.util.List;

public class CartCheckout {

	private List<LineItems> lineItems;
	private Customer customer;
	
	public List<LineItems> getLineItems() {
		return lineItems;
	}
	public void setLineItems(List<LineItems> lineItems) {
		this.lineItems = lineItems;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
