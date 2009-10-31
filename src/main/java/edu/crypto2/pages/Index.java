/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.pages;

import java.util.Date;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.crypto2.entities.User;
import edu.crypto2.services.SourceDao;
import edu.crypto2.services.TestValuesDao;


/**
 * Start page of application crypto2.
 */

public class Index
{
	private Session session = null;
	
	@Inject
	private TestValuesDao testValuesDao;
	@Inject
	private SourceDao sourceDao;

	
	@SessionState(create=false)
	private User user;
	
	@Property
	private String UserName = "";
	

    public boolean getLoggedIn() {
        if (user != null)
            return true;
        else
            return false;
    }
    
    @SetupRender
    boolean setup() throws Exception {
            
            if (user != null){
                    UserName = (user.getName());
            }
            else{
                    UserName = "";
            }

            sourceDao.reload();
            testValuesDao.reload();
                            
            return true;
            
    }
	
    @InjectPage
    private Index index;
	Object onActionFromLogOut(){
		user = null;
		return index;
	}

	
	public Date getCurrentTime() 
	{ 
		return new Date(); 
	}
}
