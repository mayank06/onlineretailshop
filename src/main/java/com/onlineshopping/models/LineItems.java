package com.onlineshopping.models;

public class LineItems {

	private int productId;
	private String productName;
	private String productDesc;
	private int quantity;
	private float price;
	private int taxValue;
	private int categoryId;
	private String category;
	private float amount;
	private String unitOfItem;
	
	public String getUnitOfItem() {
		return unitOfItem;
	}
	public void setUnitOfItem(String unitOfItem) {
		this.unitOfItem = unitOfItem;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getTaxValue() {
		return taxValue;
	}
	public void setTaxValue(int taxValue) {
		this.taxValue = taxValue;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
	
}
