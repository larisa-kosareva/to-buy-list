package com.tobuy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.tobuy.domain.ProductList;
import com.tobuy.domain.User;

public class UserDao {
	private SessionFactory sessionFactory;
	
	public User createUser(String name, String password){
		User user = new User(name, password);
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		
		return user;
	}
	public User getUserByName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("login", userName));
		User user = (User) criteria.uniqueResult();
		return user;
		
	}
	
	public void deleteUser(int userId){
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		session.delete(user);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
