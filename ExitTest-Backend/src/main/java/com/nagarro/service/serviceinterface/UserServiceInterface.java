package com.nagarro.service.serviceinterface;

import com.nagarro.entity.User;

public interface UserServiceInterface {
//	public User insertUser(User user);
//	
//	public User fetchUserByEmail(String email);
//	
//	public User authenticateUser(String uname,String pwd)throws Exception;
	public User addUser(User user);

	public User createUser(User user) throws Exception;

	public User getUser(String email);

	public void deleteUser(String email);
}
