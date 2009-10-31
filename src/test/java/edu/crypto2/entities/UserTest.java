/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.entities;
/***********************************************************************
 * 
 */
import static org.testng.AssertJUnit.assertSame;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserTest {
	private User userToTest;
	
	@BeforeMethod
	public void begin(){
		userToTest = new User();
	}
	@Test
	public void testAddUserName(){
		String userName = "dboris";
		userToTest.setUserName(userName);
		assertSame(userName, userToTest.getUserName());
		
	}
	@Test
	public void testAddPassword(){
		String password = "1234";
		userToTest.setPassword(password);
		assertSame(password, userToTest.getPassword());
	}
	@Test
	public void testAddName(){
		String name = "Boris Damjanovic";
		userToTest.setName(name);
		assertSame(name, userToTest.getName());
	}
	public void testAddEmail(){
		String email = "dboris65@gmail.com";
		userToTest.setEmail(email);
		assertSame(email, userToTest.getEmail());
	}
	
}
