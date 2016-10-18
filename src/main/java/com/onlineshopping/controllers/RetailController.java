package com.onlineshopping.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlineshopping.models.Cart;
import com.onlineshopping.models.CartCheckout;
import com.onlineshopping.models.Category;
import com.onlineshopping.models.Product;
import com.onlineshopping.services.RetailService;

@Controller
@RequestMapping("/")
public class RetailController {

	private static final Logger logger = Logger.getLogger(RetailController.class);


	@Autowired
	RetailService retailService;


	/**
	 * to fetch the list of all categories present in the online store
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/categories", method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody List<Category> allCategories(final HttpServletRequest request, final HttpServletResponse response) {

		logger.debug("in allCategories() : Resource class");
		logger.info("request from mobile client for allCategories : "+System.currentTimeMillis());

		List<Category> categories = null;

		try {
			categories = retailService.allCategories();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("response from middleware for allCategories : "+System.currentTimeMillis());
		return categories;
	}

	
	/**
	 * to get the list of products present in a particular category
	 * @param request
	 * @param response
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/products/{categoryId}", method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody List<Product> allProductOnCategory(final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable(value="categoryId") int categoryId) {

		logger.debug("in allProductOnCategory() : Resource class");
		logger.info("request from mobile client for allCategories : "+System.currentTimeMillis());

		List<Product> products = null;

		try {
			products = retailService.allProductOnCategory(categoryId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("response from middleware for allProductOnCategory : "+System.currentTimeMillis());
		return products;
	}
	
	
	/**
	 * to get the details of a particular product
	 * @param request
	 * @param response
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="/details/{productId}", method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody Product productDetails(final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable(value="productId") int productId) {

		logger.debug("in productDetails() : Resource class");
		logger.info("request from mobile client for allCategories : "+System.currentTimeMillis());

		Product product = null;

		try {
			product = retailService.productDetails(productId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("response from middleware for productDetails : "+System.currentTimeMillis());
		return product;
	}
	
	
	/** 
	 * to add a product into the "mycart" of the customer
	 * @param cart
	 * @return
	 */
	@RequestMapping(value="/addToCart", method=RequestMethod.POST,consumes={"application/json;charset=UTF-8"},produces={"application/json;charset=UTF-8"})
	@ResponseBody Cart addToMyCart(Cart cart) {

		logger.debug("in productDetails() : Resource class");
		logger.info("request from mobile client for allCategories : "+System.currentTimeMillis());

		try {
			cart = retailService.addToMyCart(cart);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("response from middleware for productDetails : "+System.currentTimeMillis());
		return cart;
	}
	
	
	/** 
	 * to checkout the customer's mycart and generate the final bill with levy added on the MRP values of a product
	 * @param request
	 * @param response
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value="/checkout/{customerId}", method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody CartCheckout cartCheckout(final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable(value="customerId") int customerId) {

		logger.debug("in cartCheckout() : Resource class");
		logger.info("request from mobile client for allCategories : "+System.currentTimeMillis());

		CartCheckout cartCheckout = null;

		try {
			cartCheckout = retailService.cartCheckout(customerId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("response from middleware for cartCheckout : "+System.currentTimeMillis());
		return cartCheckout;
	}
	
	
}
