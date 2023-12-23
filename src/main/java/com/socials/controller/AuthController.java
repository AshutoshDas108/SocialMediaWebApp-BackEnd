package com.socials.controller;

import com.socials.exceptions.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socials.config.JwtProvider;
import com.socials.models.User;
import com.socials.repository.UserRepository;
import com.socials.request.LoginRequest;
import com.socials.response.AuthResponse;
import com.socials.service.CustomUserDetailService;

@RestController
@RequestMapping("/auth") // start of end point of each method
public class AuthController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailService customUserDetail;
	
	// Actual end point :  localhost:5454/auth/signup
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws AuthException {
		
		User isExist = userRepository.findByEmail(user.getEmail());
		
		if(isExist != null) {
			throw new AuthException("Email Already Exists, Try with another Email");
		}
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setGender(user.getGender());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(newUser);
		
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse(token, "Registered Sucessfully");
		
		return authResponse;
		
		
	}
	
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		
         String token = JwtProvider.generateToken(authentication);
		
		 AuthResponse authResponse = new AuthResponse(token, "Loggedin Sucessfully");
		 
			return authResponse;
	}

	//verify password
	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customUserDetail.loadUserByUsername(email);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid UserName/Email");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Wrong Password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
