package com.onlineshopping.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.onlineshopping.constants.RetailConstant;
import com.onlineshopping.models.Cart;
import com.onlineshopping.models.CartCheckout;
import com.onlineshopping.models.Category;
import com.onlineshopping.models.Customer;
import com.onlineshopping.models.LineItems;
import com.onlineshopping.models.Product;

public class RetailDao {

	private static final Logger logger = Logger.getLogger(RetailDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public List<Category> allCategories() {

		logger.debug("request received from resource @"+System.currentTimeMillis());

		List<Category> categories = new ArrayList<Category>();
		Category category = null;

		try {
			logger.debug(jdbcTemplate.getDataSource().getConnection());
			logger.debug("sql query ::"+ RetailConstant.ALL_CATEGORIES);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(RetailConstant.ALL_CATEGORIES);

			if(rows!=null){
				logger.debug("Records ::" + rows.size());
			}else{
				logger.debug("no rows found");
			}

			
			for(Map row : rows)
			{
				category = new Category();
				category.setId(Integer.parseInt(row.get("c_Id").toString()));
				category.setCategory(row.get("c_Name").toString());
				categories.add(category);
			}
			

		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception ", e);
		}

		return categories;
	}

	public List<Product> allProductOnCategory(int categoryId) {
		logger.debug("request received from resource @"+System.currentTimeMillis());

		List<Product> products = null;
		Product product = null;

		try {
			logger.debug(jdbcTemplate.getDataSource().getConnection());
			logger.debug("sql query ::"+ RetailConstant.ALL_PRODUCTS);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(RetailConstant.ALL_PRODUCTS, categoryId);

			if(rows!=null){
				logger.debug("Records ::" + rows.size());
			}else{
				logger.debug("no rows found");
			}

			products = new ArrayList<Product>();
			for(Map row : rows)
			{
				product = new Product();
				product.setProductId((Integer)row.get("p_Id"));
				product.setProductName((String)row.get("p_Name"));
				product.setCategoryId((Integer)row.get("cat_Id"));
				product.setDescription((String)row.get("p_Description"));
				product.setUnit((String)row.get("p_qty"));
				product.setPriceValue((Float)row.get("p_Price"));
				products.add(product);
			}


		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception ", e);
		}

		return products;
	}

	public Product productDetails(int productId) {
		logger.debug("request received from resource @"+System.currentTimeMillis());

		Product product = new Product();

		try {
			logger.debug(jdbcTemplate.getDataSource().getConnection());
			logger.debug("sql query ::"+ RetailConstant.PRODUCT_DESCRIPTION);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(RetailConstant.PRODUCT_DESCRIPTION, productId);

			if(rows!=null){
				logger.debug("Records ::" + rows.size());
			}else{
				logger.debug("no rows found");
			}

			for(Map row : rows)
			{
				
				product.setProductId((Integer)row.get("p_Id"));
				product.setProductName((String)row.get("p_Name"));
				product.setCategoryId((Integer)row.get("cat_Id"));
				product.setDescription((String)row.get("p_Description"));
				product.setUnit((String)row.get("p_amount"));
				product.setPriceValue((Float)row.get("p_Price"));
			}


		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception ", e);
		}

		return product;
	}

	public Cart addToMyCart(Cart cart) {
		logger.debug("request received from resource @"+System.currentTimeMillis());

		
		try {
			logger.debug(jdbcTemplate.getDataSource().getConnection());
			logger.debug("sql query ::"+ RetailConstant.ADD_TO_CART);

			String sql = RetailConstant.ADD_TO_CART;
			/*Map<String, Object> rows = new HashMap<String, Object>();
			rows.put("customer_Id", cart.getCustomerId());
			rows.put("prod_Id", cart.getProductId());*/
			
			int rowAffected = jdbcTemplate.update(sql, new Object[]{cart.getCustomerId(), cart.getProductId(), cart.getQuantity()});
			if(rowAffected!=0)
			{
				logger.debug("item added to mycart");
			}

		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception ", e);
		}

		return cart;
	}

	public CartCheckout cartCheckout(int customerId) {


		logger.debug("request received from resource @"+System.currentTimeMillis());

		CartCheckout cartCheckout = null;
		Customer customer = null;
		LineItems lineItem = null;
		List<LineItems> lineItems = null;
		
	

		try {
			logger.debug(jdbcTemplate.getDataSource().getConnection());
			logger.debug("sql query ::"+ RetailConstant.CHECKOUT_MYCART);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(RetailConstant.CHECKOUT_MYCART, customerId);

			if(rows!=null){
				logger.debug("Records ::" + rows.size());
			}else{
				logger.debug("no rows found");
			}

			cartCheckout = new CartCheckout();
			
			customer = new Customer();
			customer.setCustomerId((Integer)rows.get(0).get("customerId"));
			customer.setCustomerName((String)rows.get(0).get("customerName"));
			customer.setAddress((String)rows.get(0).get("address"));
			customer.setEmail((String)rows.get(0).get("email"));
			
			
			lineItems = new ArrayList<LineItems>();
			for(Map row : rows)
			{
				lineItem = new LineItems();
				lineItem.setCategory((String)row.get("category"));
				lineItem.setCategoryId((Integer)row.get("categoryId"));
				lineItem.setPrice((Float)row.get("price"));
				lineItem.setProductDesc((String)row.get("productDesc"));
				lineItem.setProductId((Integer)row.get("productId"));
				lineItem.setProductName((String)row.get("productName"));
				lineItem.setUnitOfItem((String)row.get("unitOfItem"));
				lineItem.setQuantity((Integer)row.get("quantity"));
				lineItem.setTaxValue((Integer)row.get("taxValue"));
				
				//calculation of levy + qty 
				if(lineItem.getTaxValue()==0)
				{
					float totalAmt = (float)lineItem.getQuantity() * lineItem.getPrice() ;
					lineItem.setAmount(totalAmt);
				}
				else
				{
					float totalAmt = (float) (lineItem.getQuantity() * (lineItem.getPrice() + lineItem.getTaxValue() * 0.01 * lineItem.getPrice())) ;
					lineItem.setAmount(totalAmt);
				}
				
				
				lineItems.add(lineItem);
			}
			
			cartCheckout.setCustomer(customer);
			cartCheckout.setLineItems(lineItems);

		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception ", e);
		}

		return cartCheckout;
	
	}

}
