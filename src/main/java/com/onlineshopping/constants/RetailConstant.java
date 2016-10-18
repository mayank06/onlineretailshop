package com.onlineshopping.constants;

public class RetailConstant {

	public static final String ALL_CATEGORIES = "select c_Id, c_Name FROM category";

	public static final String ALL_PRODUCTS = "select p_Id, p_Name, cat_Id, p_Description, p_Unit, p_Price"
			+ " FROM products"
			+ " where cat_Id = ? ";

	public static final String PRODUCT_DESCRIPTION = "SELECT p_Id, cat_Id, p_Name, p_Description, p_Unit, p_Price"
			+ " FROM products"
			+ " where p_Id = ? ";
	
	public static final String ADD_TO_CART = "insert into mycart (customer_Id, prod_Id, quantity) VALUES (?, ?, ?)";
	
	public static final String CHECKOUT_MYCART = "select c.cust_Id as customerId, c.cust_Name as customerName, c.cust_email as email, c.cust_address as address,"
	+ " p.p_Id as productId, p.p_Name as productName, p.p_Description as productDesc, p.p_Unit as unitOfItem, mc.quantity as quantity, p.p_Price as price,"
	+ " t.t_Value as taxValue, ct.c_Id as categoryId, ct.c_Name as category"
	+ " FROM customer c, products p, tax t, category ct, mycart mc"
	+ " where mc.customer_Id = c.cust_Id"
	+ " and mc.prod_Id = p.p_Id"
	+ " and p.p_Id = ct.c_Id"
	+ " and ct.tax_Id = t.t_Id"
	+ " and mc.customer_Id = ? ";
}
