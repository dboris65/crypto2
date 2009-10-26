package edu.crypto2.pages;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.ioc.annotations.Inject;

import edu.crypto2.entities.User;
import edu.crypto2.services.UserDao;
				    


public class Login
{
	@SessionState
	@Property
	private User user;
	
    @Persist
    @Property
    private String userName;
    @Property
    private String password;
    
    @Inject
    private UserDao userDao;
    


    @Component(id = "password")
    private PasswordField passwordField;

    @Component
    private Form form;
    
	@SetupRender
	boolean setup() throws Exception {
		userDao.reload();
		if (userDao != null)
			return true;
		else
			return false;
	}

	public boolean isValid(String userName, String password){
    	final Logger logger = Logger.getLogger(Login.class);
    	logger.debug(userName + "---isValid 1--" + password);
    	logger.debug(userDao + "---isValid 2 UserDao--" );
		
		user = userDao.findUserName(userName);
		logger.debug(user + "---isValid 3 user --" );
		logger.debug((user != null) + "---isValid 4 (user != null) --" );
		
		if ((user != null) )
		{
			logger.debug("user != null ....... if user.getUserName()...." + user.getUserName() + "-----" );
			if ((user.getUserName() != null) && (user.getPassword() != null)){
				if ((user.getUserName().equals(userName)) && (user.getPassword().equals(password))){
					return true;
				}
				else
					return false;
			}
			else
				return false;
		}
		else
		{
			return false;
		}
	}
	
	void onValidateForm() {
    	final Logger logger = Logger.getLogger(Login.class);
    	logger.debug(userName + "-----" + password);
        if (!isValid(userName, password))
        {
        	logger.debug("--Prikazi gresku---" );
            form.recordError(passwordField, "Invalid user name or password.");
        }
	}
    String onSuccess()
    {


        return "Index";
    }

/*    
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        password = password;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        userName = userName;
    }
*/
}
