/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;

import edu.crypto2.entities.User;

/***********************************************************************
 * 
 */
public class QuickStartPG {
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
    @InjectPage
    private Index index;
	Object onActionFromLogOut(){
		user = null;
		return index;
	}

	@SetupRender
	boolean setup() throws Exception {
		if (user != null){
            try {
				UserName = (user.getName());
			} catch (Exception e) {
				user = null;
				UserName = "";
			}
		}
		else{
			UserName = "";
		}
		return true;
	}
}
