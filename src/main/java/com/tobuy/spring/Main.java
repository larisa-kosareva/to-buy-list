package com.tobuy.spring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tobuy.dao.ProductsDao;
import com.tobuy.dao.UserDao;
import com.tobuy.domain.Product;
import com.tobuy.domain.ProductList;
import com.tobuy.domain.User;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}

	public void run() {
		ApplicationContext context = 
		    	  new ClassPathXmlApplicationContext("context.xml");
		try {
			dupa(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void dupa(ApplicationContext context) {
		ProductsDao productsDao = context.getBean("productsDao", ProductsDao.class);
		UserDao userDao = context.getBean("userDao", UserDao.class);
		User user = userDao.createUser("user","user");
		ProductList productList = productsDao.createProductList(user, "02.01.2016");
		productsDao.addProduct(productList,"чешук");
		productsDao.addProduct(productList,"памук");
		productsDao.addProduct(productList,"бамбук");
		
	}

	private void persistProduct(Session session) throws Exception {
		try {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			
			User user = new User("user","user");
			session.save(user);
			
			ProductList productList = new ProductList("27.12.2015");
			Product p1 = new Product("затычки");
			productList.addProduct(p1);
			Product p2 = new Product("чешу€");
			productList.addProduct(p2);
			Product p3 = new Product("мастика");
			productList.addProduct(p3);
			productList.setUser(user);
			session.save(productList);
			session.save(p1);
			session.save(p2);
			session.save(p3);
			
			
			ProductList productList1 = new ProductList("28.12.2015");
			Product p11 = new Product("плетка");
			productList1.addProduct(p11);
			Product p22 = new Product("вазелин");
			productList1.addProduct(p22);
			Product p33 = new Product("нож");
			productList1.addProduct(p33);
			productList1.setUser(user);
			session.save(productList1);
			session.save(p11);
			session.save(p22);
			session.save(p33);
			
			transaction.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

}