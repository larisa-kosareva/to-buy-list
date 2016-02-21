package com.tobuy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.tobuy.domain.Product;
import com.tobuy.domain.ProductList;
import com.tobuy.domain.User;

public class ProductsDao {

	private SessionFactory sessionFactory;
	
	
	public ProductList createProductList(User user, String date){
		ProductList productList = new ProductList(date);
		productList.setUser(user);
		Session session = sessionFactory.getCurrentSession();
		session.save(productList);
		return productList;
	}
	
	public Product addProduct(ProductList productList, String name) {
		Product product = new Product(name);
		productList.addProduct(product);
		Session session = sessionFactory.getCurrentSession();
		session.save(product);
		
		return product;
	}
	public List<ProductList> getUndoneListByUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ProductList.class);
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("done", false));
		@SuppressWarnings("unchecked")
		List<ProductList> list = criteria.list();
		return list;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void deleteProductList(long productListId){
		Session session = sessionFactory.getCurrentSession();
		ProductList productList = session.get(ProductList.class, productListId);
		session.delete(productList);
	}
	
	public void deleteProduct(long productId){
		Session session = sessionFactory.getCurrentSession();
		Product product = session.get(Product.class, productId);
		session.delete(product);
	}
	
	public ProductList getProductListById(long productListId){
		Session session = sessionFactory.getCurrentSession();
		return session.get(ProductList.class, productListId);
	}

}
