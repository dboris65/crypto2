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
public class UserDaoTest {
	UserDao userDao;
	protected Session session;
    protected Criteria criteria;
    protected Serializable id;
    protected SessionFactory sessionFactory;
    protected UserDao createTestedObject(){
    	return new UserDaoImpl();
    }
    @BeforeMethod
    public void setUp() throws Exception {
    	userDao = createTestedObject();
        session = createStrictMock(Session.class);
        sessionFactory = createStrictMock(SessionFactory.class);
        criteria = createStrictMock(Criteria.class);
    }
    @Test()
    public void testGetByType(){
    	expect(sessionFactory.getCurrentSession()).andReturn(session);
    	expect(session.createCriteria(Object.class)).andReturn(criteria);
    	List queryResult = new LinkedList();
        expect(criteria.list()).andReturn(queryResult);
    }

}
