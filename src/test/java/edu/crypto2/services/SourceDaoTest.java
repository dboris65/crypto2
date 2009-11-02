/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.services;

import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/***********************************************************************
 * 
 */
public class SourceDaoTest {
	SourceDao sourceDao;
	protected Session session;
    protected Criteria criteria;
    protected Serializable id;
    protected SessionFactory sessionFactory;
    
    protected SourceDao createTestedObject(){
    	return new SourceDaoImpl();
    }
    @BeforeMethod
    public void setUp() throws Exception {
    	System.out.println("SourceDao setUp1 ----------------------- ");
    	sourceDao = createTestedObject();
    	System.out.println("SourceDao setUp2 ----------------------- ");
        session = createStrictMock(Session.class);
    	System.out.println("SourceDao setUp3 ----------------------- ");
        sessionFactory = createStrictMock(SessionFactory.class);
    	System.out.println("SourceDao setUp4 ----------------------- ");
        criteria = createStrictMock(Criteria.class);
    	System.out.println("SourceDao setUp5 ----------------------- ");
    }
    @Test()
    public void testGetByType(){
    	expect(sessionFactory.getCurrentSession()).andReturn(session);
    	expect(session.createCriteria(Object.class)).andReturn(criteria);
    	List queryResult = new LinkedList();
        expect(criteria.list()).andReturn(queryResult);
    }
    
}
