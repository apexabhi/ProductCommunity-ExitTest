package com.nagarro.service;
//importing packages
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarro.entity.User;
import com.nagarro.exceptions.UserNotFoundException;
import com.nagarro.repository.UserRepository;
import com.nagarro.service.serviceinterface.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface{
	@Autowired
	private UserRepository repo;
	@Override
	public User addUser(User user) {
		repo.save(user);
		return user;
	}
	//to create new user
	@Override
	public User createUser(User user) throws Exception {
		 User local = this.repo.findByEmail(user.getEmail());
		 if (local != null) {
			 System.out.println("User is already there!");
			 throw new Exception("User already present!");
		 } else {
			local = this.repo.save(user);
		 }
		 return local;
	}
	//to get user details through email
	@Override
	public User getUser(String email) {
		User user= this.repo.findByEmail(email);
		if(Objects.nonNull(user)) {
			return user;
		}
		else {
			throw new UserNotFoundException("No user found ");
		}
	}
	//to delete user
	@Override
	public void deleteUser(String email) {
		User entity = repo.findByEmail(email);
		repo.delete(entity);
	}
}
