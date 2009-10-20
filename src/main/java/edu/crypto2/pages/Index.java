/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.pages;

import java.util.Date;

import org.apache.tapestry5.annotations.SessionState;

import edu.crypto2.data.Data;

/**
 * Start page of application crypto2.
 */
public class Index
{

	
	/*
    @Inject
    private Session session;
    
    public List<TestValues> getTestValues()
    {
        return session.createCriteria(TestValues.class).list();
    }
    */

	
	@SessionState
	private String SesionValueBeforeSubBytes = "";
	
	public Date getCurrentTime() 
	{ 
		return new Date(); 
	}
}
