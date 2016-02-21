package com.tobuy.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tobuy.dao.ProductsDao;
import com.tobuy.dao.UserDao;
import com.tobuy.domain.Product;
import com.tobuy.domain.ProductList;
import com.tobuy.domain.User;

public class ToBuyService {
	private ProductsDao productsDao;
	private UserDao userDao;

	@Transactional
	public List<ProductList> getAllUndoneLists(String userName) {
		User user = userDao.getUserByName(userName);
		return productsDao.getUndoneListByUser(user);
	}

	@Transactional
	public ProductList createNewListForUser(String userName) {
		User user = userDao.getUserByName(userName);
		Date ñurrentDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String day = simpleDateFormat.format(ñurrentDate);
		ProductList productList = productsDao.createProductList(user, day);
		return productList;
	}

	@Transactional
	public Product addProductToList(long productListId, String productName) {
		ProductList list = productsDao.getProductListById(productListId);
		Product product = productsDao.addProduct(list, productName);
		return product;
	}
	
	@Transactional
	public boolean markAsDone(long productListId){
		ProductList list = productsDao.getProductListById(productListId);
		list.setDone(true);
		return true;
	}

	public ProductsDao getProductsDao() {
		return productsDao;
	}

	public void setProductsDao(ProductsDao productsDao) {
		this.productsDao = productsDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
