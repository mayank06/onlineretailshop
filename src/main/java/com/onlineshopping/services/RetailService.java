package com.onlineshopping.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.onlineshopping.dao.RetailDao;
import com.onlineshopping.models.Cart;
import com.onlineshopping.models.CartCheckout;
import com.onlineshopping.models.Category;
import com.onlineshopping.models.Product;

public class RetailService {

	private static final Logger logger = Logger.getLogger(RetailService.class);

	@Autowired
	RetailDao retailDao;
	
	public List<Category> allCategories() {
		logger.debug("in allCategories() : Service class");
		return retailDao.allCategories();
	}

	public List<Product> allProductOnCategory(int categoryId) {
		logger.debug("in allProductOnCategory() : Service class");
		return retailDao.allProductOnCategory(categoryId);
	}

	public Product productDetails(int productId) {
		logger.debug("in productDetails() : Service class");
		return retailDao.productDetails(productId);
	}

	public Cart addToMyCart(Cart cart) {
		logger.debug("in addToMyCart() : Service class");
		return retailDao.addToMyCart(cart);
	}

	public CartCheckout cartCheckout(int customerId) {
		logger.debug("in cartCheckout() : Service class");
		return retailDao.cartCheckout(customerId);
	}

}
