/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

/***********************************************************************
 * 
 */

/**
 * class User<br>
 * Holds registreted users.
 */
@Entity
@Table(name="User") 
public class User {
	

    
	/**
	 * Long id<br>
	 * Row identifier.
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	private Long id;

	/**
	 * String UserName<br>
	 * Holds UserName. 
	*/	
	@Validate("required")
	private String UserName;
		
	/**
	 * String Password<br>
	 * Holds Password.
	*/	
	@Validate("required")
	private String Password;
	
	/**
	 * String Name<br>
	 * Holds Name. Name is real name of user.
	*/	
	@Validate("required")
	private String Name;
	

	/**
	 * String Email<br>
	 * Holds Email.
	*/	
	@Validate("required")
	private String Email;

	/**
	 * Id getter.
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Id setter.
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * UserName getter.
	 * @return the userName
	 */
	public String getUserName() {
		return UserName;
	}

	/**
	 * UserName setter.
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}

	/**
	 * Password getter.
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * Password setter.
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * Name getter. Name is real name of user.
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Name setter. Name is real name of user.
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Email getter.
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * Email setter.
	 * @param email the email to set
	 */
	public void setEmail(String Email) {
		this.Email = Email;
	}
	 
	


}
