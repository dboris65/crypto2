/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.services;

import java.util.List;

import edu.crypto2.entities.User;
/***********************************************************************
 * 
 */
public interface UserDao {
	List<User> findAllUsers();
	 
	User find(long id);
	
	User findUserName(String userName);
	 
	User findPassword(String password);
	
	
	User getCurrent();

	public void update(User source);
	
	void save(User _source);

	void delete(User _source);
	
	public void reload();

}
