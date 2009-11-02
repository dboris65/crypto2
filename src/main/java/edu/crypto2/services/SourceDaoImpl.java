/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.services;

import java.util.List;

import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.crypto2.data.Data;
import edu.crypto2.entities.Source;
import edu.crypto2.entities.User;
import edu.crypto2.rest.HibernateUtil;

/***********************************************************************
 * 
 */
public class SourceDaoImpl implements SourceDao{
	@SessionState(create=false)
	private User user;
	
	
	
    public boolean getLoggedIn() {
        if (user != null)
            return true;
        else
            return false;
    }

	private static int CurrentId = 0;
    
	@Inject
    private Session session;
    
	private List<Source> database; 
	
	public void reload(long userId){
		UserDao userDao = new UserDaoImpl();
		user = userDao.find(userId);
		String qry = "from Source where USERID=" + user.getId();
		if (session.isOpen())
			session.close();
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		database = session.createQuery(qry).list();
		if (database.isEmpty())
		{
			transaction = null;
			transaction = session.beginTransaction();
			Source source = new Source();
			source.setSourceCode(Data.source_code_template_0);
			// Here, UserId is always 1
			source.setUserId(userId);

			session.persist(source);
			transaction.commit();
			transaction = session.beginTransaction();
			source = new Source();
			source.setSourceCode(Data.source_code_template_1);
			// Here, UserId is always 1
			source.setUserId(userId);

			session.persist(source);
			transaction.commit();

			transaction = session.beginTransaction();
			source = new Source();
			source.setSourceCode(Data.source_code_template_2);
			
			// Here, UserId is always 1
			source.setUserId(userId);

			session.persist(source);
			transaction.commit();
			
			transaction = session.beginTransaction();
			source = new Source();
			source.setSourceCode(Data.source_code_template_3);
			
			// Here, UserId is always 1
			source.setUserId(userId);

			session.persist(source);
			transaction.commit();
			CurrentId = source.getId().intValue();
		}
		
		transaction = null;
		transaction = session.beginTransaction();
		String qry2 = "from Source where USERID=" + user.getId();
		database = session.createQuery(qry2).list();
		
		transaction.commit();
	}

	public SourceDaoImpl(){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			database = session.createQuery("from Source").list();
			if (database.isEmpty())
			{
				transaction = null;
				transaction = session.beginTransaction();
				Source source = new Source();
				source.setSourceCode(Data.source_code_template_0);
				// Here, UserId is always 1
				source.setUserId((long)1);

				session.persist(source);
				transaction.commit();
				transaction = session.beginTransaction();
				source = new Source();
				source.setSourceCode(Data.source_code_template_1);
				// Here, UserId is always 1
				source.setUserId((long)1);

				session.persist(source);
				transaction.commit();
				
				transaction = session.beginTransaction();
				source = new Source();
				source.setSourceCode(Data.source_code_template_2);
				// Here, UserId is always 1
				source.setUserId((long)1);
				session.persist(source);
				transaction.commit();
				transaction = session.beginTransaction();
				source = new Source();
				source.setSourceCode(Data.source_code_template_3);
				// Here, UserId is always 1
				source.setUserId((long)1);  

				session.persist(source);
				transaction.commit();
				CurrentId = source.getId().intValue();
			}
			transaction = null;
			transaction = session.beginTransaction();
			database = session.createQuery("from Source").list();
			transaction.commit();
		}catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<Source> findAllSources() {
		return database;
	}


	public void save(Source source) {
		Transaction transaction = null;
		transaction = session.beginTransaction();
		database.add(source);
		session.persist(source);
		transaction.commit();
		CurrentId = source.getId().intValue();
	}

	public void update(Source source) {
		Transaction transaction = null;
		transaction = session.beginTransaction();
		session.update(source);
		transaction.commit();
	}
	
	public Source find(long id){
		for (Source source: database) {
			if(source.getId()==id){
				CurrentId = source.getId().intValue();
				return source;
			}	
		}
		return null;
	}
	
	public Source getCurrent(){
		for (Source source: database) {
			if(source.getId()==CurrentId){
				return source;
			}	
		}
		return null;		
	}
	
	public void delete(Source source){
		if(source!=null && database.contains(source)){
			String hql = "delete from Source where id = " + source.getId();
			Transaction transaction = null;
			transaction = session.getTransaction();
			if (transaction.isActive()) 
				transaction.commit();
			transaction = session.beginTransaction();
			database.remove(source);
			
			Query query = session.createQuery(hql);
			int row = query.executeUpdate();
			transaction.commit();
		}
		CurrentId = source.getId().intValue();
	};
				

}
