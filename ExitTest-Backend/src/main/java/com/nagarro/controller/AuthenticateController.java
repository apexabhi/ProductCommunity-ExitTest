//Controller for user authentication and login process
package com.nagarro.controller;
//importing packages
import java.security.Principal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.service.JwtUtil;
import com.nagarro.model.JwtRequest;
import com.nagarro.model.JwtResponse;
import com.nagarro.service.UserDetailsServiceImp;
import com.nagarro.exceptions.UserNotFoundException;


@RestController
@CrossOrigin("*") 
public class AuthenticateController {
	@Autowired
	private UserDetailsServiceImp userDetailsServiceImpl;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/token")
	public ResponseEntity<?> genrateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println("Generating token......");
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid Credentials");
		}

		UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println(token);
		return ResponseEntity.ok(new JwtResponse(token));

	}
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		User user=(User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
		if(Objects.nonNull(user)) {
			return user;
		}
		else {
			throw new UserNotFoundException("No user found ");
		}
		
	}

}

