package edu.crypto2.pages;

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
	
		user = userDao.findUserName(userName);
		
		if ((user != null) )
		{
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
        if (!isValid(userName, password))
        {
            form.recordError(passwordField, "Invalid user name or password.");
        }
	}
    String onSuccess()
    {


        return "Index";
    }

}
