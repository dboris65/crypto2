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

import edu.crypto2.entities.Source;
import edu.crypto2.pages.MetaTransformationPG;
import edu.crypto2.rest.HibernateUtil;

/***********************************************************************
 * 
 */
public class SourceDaoImpl implements SourceDao{
	private static int CurrentId = 0;
    
	@Inject
    private Session session;
    
	private List<Source> database; 
	
	public void reload(){
		if (session.isOpen())
			session.close();
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		database = session.createQuery("from Source").list();
		transaction.commit();
	}

	public SourceDaoImpl(){
		
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			//transaction = null;
			transaction = session.beginTransaction();
			database = session.createQuery("from Source").list();
			transaction.commit();
			if (database.isEmpty())
			{
				transaction = null;
				transaction = session.beginTransaction();
				Source source = new Source();
				source.setSourceCode(
						"// AES128 from FIPS197 pg.33-34\n"+
						"// First try test vector no.1\n"+
						"// (3243f6a8885a308d313198a2e0370734)\n"+
						"// Then try to experiment.\n"+
						"key_len = 128;\n"+
						"init_key = \"2b7e151628aed2a6abf7158809cf4f3c\";\n"+
						"keyExpansion.KeyExpansion128();\n"+ 
						"Nb = 4;  //br.kolona //number of columns\n"+
						"Nr = 10; //br.rundi // number of rounds\n"+
						"addRoundKey.transform_state(0);\n"+
						"for (runda = 1; runda <= Nr-1; runda++)\n"+
						"{\n"+
						"	subBytes.transform_state();\n"+
						"	shiftRows.transform_state();\n"+
						"	mixColumns.transform_state();\n"+
						"	addRoundKey.transform_state(4*runda*Nb );\n"+
						"	runda2=runda;\n"+
						"}\n"+
						"runda2++;\n"+
						"subBytes.transform_state();\n"+
						"// Final round\n"+
						"shiftRows.transform_state();\n"+
						"addRoundKey.transform_state( 4*runda2*Nb );");
				
				//source.setId(nextId);
				session.persist(source);
				transaction.commit();
				
				transaction = session.beginTransaction();
				source = new Source();
				source.setSourceCode(
						"// AES128 from FIPS197 pg.35-36\n"+
						"// First try test vector no.2\n"+
						"// (3243f6a8885a308d313198a2e0370734)\n"+
						"// Then try to experiment.\n"+
						"key_len = 128;\n"+
						"init_key = \"000102030405060708090a0b0c0d0e0f\";\n"+
						"keyExpansion.KeyExpansion128();\n"+ 
						"Nb = 4;  //br.kolona //number of columns\n"+
						"Nr = 10; //br.rundi // number of rounds\n"+
						"addRoundKey.transform_state(0);\n"+
						"for (runda = 1; runda <= Nr-1; runda++)\n"+
						"{\n"+
						"	subBytes.transform_state();\n"+
						"	shiftRows.transform_state();\n"+
						"	mixColumns.transform_state();\n"+
						"	addRoundKey.transform_state(4*runda*Nb );\n"+
						"	runda2=runda;\n"+
						"}\n"+
						"runda2++;\n"+
						"subBytes.transform_state();\n"+
						"// Final round\n"+
						"shiftRows.transform_state();\n"+
						"addRoundKey.transform_state( 4*runda2*Nb );");
				
				//source.setId(nextId);
				session.persist(source);
				transaction.commit();

				transaction = session.beginTransaction();
				source = new Source();
				source.setSourceCode(
						"// AES192 from FIPS197 pg.38-39\n"+
						"// First, try test vector no.3\n"+
						"// (00112233445566778899aabbccddeeff)\n"+
						"// Then try to experiment.\n"+
						"key_len = 192;\n"+
						"init_key = \"000102030405060708090a0b0c0d0e0f1011121314151617\";\n"+
						"keyExpansion.KeyExpansion192();\n"+ 
						"Nb = 4;  //br.kolona //number of columns\n"+
						"Nr = 12; //br.rundi // number of rounds\n"+
						"addRoundKey.transform_state(0);\n"+
						"for (runda = 1; runda <= Nr-1; runda++)\n"+
						"{\n"+
						"	subBytes.transform_state();\n"+
						"	shiftRows.transform_state();\n"+
						"	mixColumns.transform_state();\n"+
						"	addRoundKey.transform_state(4*runda*Nb );\n"+
						"	runda2=runda;\n"+
						"}\n"+
						"runda2++;\n"+
						"subBytes.transform_state();\n"+
						"// Final round\n"+
						"shiftRows.transform_state();\n"+
						"addRoundKey.transform_state( 4*runda2*Nb );");
				
				//source.setId(nextId);
				session.persist(source);
				transaction.commit();
				
				transaction = session.beginTransaction();
				source = new Source();
				source.setSourceCode(
						"// AES256 from FIPS197 pg.42\n"+
						"// First, try test vector no.4\n"+
						"// (00112233445566778899aabbccddeeff)\n"+
						"// Then try to experiment.\n"+
						"key_len = 256;\n"+
						"init_key = \"000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f\";\n"+
						"keyExpansion.KeyExpansion256();\n"+ 
						"Nb = 4;  //br.kolona //number of columns\n"+
						"Nr = 14; //br.rundi // number of rounds\n"+
						"addRoundKey.transform_state(0);\n"+
						"for (runda = 1; runda <= Nr-1; runda++)\n"+
						"{\n"+
						"	subBytes.transform_state();\n"+
						"	shiftRows.transform_state();\n"+
						"	mixColumns.transform_state();\n"+
						"	addRoundKey.transform_state(4*runda*Nb );\n"+
						"	runda2=runda;\n"+
						"}\n"+
						"runda2++;\n"+
						"subBytes.transform_state();\n"+
						"// Final round\n"+
						"shiftRows.transform_state();\n"+
						"addRoundKey.transform_state( 4*runda2*Nb );");
				
				//source.setId(nextId);
				session.persist(source);
				transaction.commit();
				CurrentId = source.getId().intValue();
			}
			
			transaction = null;
			transaction = session.beginTransaction();
			database = session.createQuery("from Source").list();
			
			final Logger logger = Logger.getLogger(SourceDaoImpl.class);
			logger.debug("createQuery(from Source)--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("---->>>>" + database);
			
			transaction.commit();
			
		}catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<Source> findAllSources() {
		final Logger logger = Logger.getLogger(SourceDaoImpl.class);
		logger.debug("database--------------------------------------------------------------");
		logger.debug("--------------------------------------------------------------");
		logger.debug("--------------------------------------------------------------");
		logger.debug("--------------------------------------------------------------");
		logger.debug("--------------------------------------------------------------");
		logger.debug("--------------------------------------------------------------");
		logger.debug("--------------------------------------------------------------");
		logger.debug("--------------------------------------------------------------");
		logger.debug("---->>>>" + database);
		return database;
		
		
	}


	public void save(Source source) {
		Transaction transaction = null;
		transaction = session.beginTransaction();
		database.add(source);
		session.persist(source);
		transaction.commit();
		CurrentId = source.getId().intValue();
		//session.persist(database);
		//source.setId(nextId++);
		//session.persist(database);
	}

	public void update(Source source) {
		Transaction transaction = null;
		transaction = session.beginTransaction();
		//database.set(CurrentId, source);
		//database.set((int)CurrentId, source);
		session.update(source);
		transaction.commit();
		//session.persist(database);
		//source.setId(nextId++);
		//session.persist(database);
	}
	
	public Source find(long id){
		final Logger logger = Logger.getLogger(SourceDaoImpl.class);
		logger.debug("Find--------------------------------------------");
		for (Source source: database) {
			if(source.getId()==id){
				CurrentId = source.getId().intValue();
				
				logger.debug("Find--------------------------------------------");
				logger.debug("------------------------------------------------");
				logger.debug(source.getSourceCode());
				return source;
			}	
		}
		return null;
	}
	
	public Source getCurrent(){
		for (Source source: database) {
			if(source.getId()==CurrentId){
				final Logger logger = Logger.getLogger(SourceDaoImpl.class);
				logger.debug("GetCurrent--------------------------------------------");
				logger.debug("------------------------------------------------------");
				logger.debug(source.getSourceCode());

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
			// ako je row = 0, onda error
			transaction.commit();
		}
		CurrentId = source.getId().intValue();
	};


/*	
	public void save(Source source) {
		session.persist(database);
		database.add(source);
		source.setId(nextId++);
	}
*/


				

}
