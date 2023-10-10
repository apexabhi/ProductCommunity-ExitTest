//Controller for User Registration
package com.nagarro.controller;
//importing packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.entity.User;
import com.nagarro.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class RegistrationController {
	@Autowired
	UserService service;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;	
	@PostMapping("user/register")
	public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		return ResponseEntity.ok().body(service.createUser(user));
	}

	@GetMapping("user/{email}")
	public ResponseEntity<User> getUser(@PathVariable("email") String email) {
		User user=this.service.getUser(email);
		return ResponseEntity.ok().body(user);
	}
	
	@DeleteMapping("user/{email}")
	public void deleteUser(@PathVariable("email") String email) {
		this.service.deleteUser(email);
	}
	
	@PutMapping("user/update")
	public User updateUser(@RequestBody User user) throws Exception {
		return this.service.createUser(user);
	}


}
