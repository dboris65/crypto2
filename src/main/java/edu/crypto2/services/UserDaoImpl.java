/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.crypto2.entities.TestValues;
import edu.crypto2.entities.User;
import edu.crypto2.pages.Login;
import edu.crypto2.pages.SubBytesPG;
import edu.crypto2.rest.HibernateUtil;

/***********************************************************************
 * 
 */
public class UserDaoImpl implements UserDao{
private static int CurrentId = 0;
    
	@Inject
    private Session session;
    
	private List<User> database;
	
	public void reload(){
		if (session.isOpen())
			session.close();
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		database = session.createQuery("from User").list();
		transaction.commit();
	}
	
	public UserDaoImpl(){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			database = session.createQuery("from User").list();
			transaction.commit();
			if (database.isEmpty())
			{
				transaction = null;
				transaction = session.beginTransaction();
				User user = new User();
				user.setName("Boris Damjanovic");
				user.setUserName("dboris");
				user.setPassword("damjanovic");
				user.setEmail("dboris65@gmail.com");
				session.persist(user);
				transaction.commit();
				CurrentId = user.getId().intValue();
			}
			
			transaction = null;
			transaction = session.beginTransaction();
			database = session.createQuery("from User").list();
			transaction.commit();
			
		}catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public List<User> findAllUsers(){
		return database;
	}
	
	public void save(User user){
		Transaction transaction = null;
		transaction = session.beginTransaction();
		database.add(user);
		session.persist(user);
		transaction.commit();
		CurrentId = user.getId().intValue();
		
	}
	
	public void update(User user){
		Transaction transaction = null;
		transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
	}

	public User find(long id){ 
		for (User user: database){
			if(user.getId()==id){
				CurrentId = user.getId().intValue();
				return user;
			}	 
		}
		return null;
	}
	
	public User findUserName(String userName){
		for (User user: database){
			if(user.getUserName().equals(userName)){
				CurrentId = user.getId().intValue();
				return user;
			}	
		}
		return null;
	}

	public User findPassword(String password){
		for (User user: database){
			if(user.getPassword() == password){
				CurrentId = user.getId().intValue();
				return user;
			}	
		}
		return null;
	}
	
	public User getCurrent(){
		for (User user: database) {
			if(user.getId()==CurrentId){
				return user;
			}
		}
		return null;
	}
	
	public void delete(User user){
		if(user!=null && database.contains(user)){
			String hql = "delete from User where id = " + user.getId();
			Transaction transaction = null;
			transaction = session.getTransaction();
			if (transaction.isActive()) 
				transaction.commit();
			transaction = session.beginTransaction();
			database.remove(user);
			
			Query query = session.createQuery(hql);
			int row = query.executeUpdate();
			transaction.commit();
		}
		CurrentId = user.getId().intValue();
	}
	
	public boolean isValid(String userName, String password){

		User findUser;
		findUser = this.findUserName(userName);
		if ((findUser != null) )
		{
			if ((findUser.getUserName().equals(userName)) )
				return false;
		}
		findUser = this.findPassword(password);
		if ((findUser != null) ){
			if ((findUser.getPassword().equals(password)) )
				//password exists
				return false;
		}
		return true;
	}
	
}
	
