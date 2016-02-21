package com.tobuy.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.tobuy.domain.Product;
import com.tobuy.domain.ProductList;
import com.tobuy.domain.User;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}

	public void run() {
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = buildSessionFactory();
			session = sessionFactory.openSession();
			persistProduct(session);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}

	private static SessionFactory buildSessionFactory() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.buildSessionFactory();
		return sessionFactory;
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
			Product p2 = new Product("чешуя");
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