package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.secureAdmin;
import com.entity.secureUser;
import com.model.Airline;
import com.model.User;
import com.model.adminValidation;
import com.model.userValidation;
import com.service.AuthLService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	private AuthLService service;

	
	@PostMapping("/secureuser")
	public secureUser registerSecuredUser(@RequestBody secureUser user) {
		return service.registerSecureUser(user);
	}
	
	@PostMapping("/secureadmin")
	public secureAdmin registerSecuredAdmin(@RequestBody secureAdmin admin) {
		return service.registerSecureAdmin(admin);
	}
	
	
	@PutMapping("/updatesecureuser")
	public secureUser updateSecuredUser(@RequestBody secureUser updatedUser) {
		return service.registerSecureUser(updatedUser);
	}
	
	
//	@PutMapping("/updatesecureadmin")
//	public secureAdmin updateSecuredAdmin(@RequestBody secureAdmin updatedAdmin) {
//		return service.registerSecureAdmin(updatedAdmin);
//	}
	
	
	@PostMapping("/loginUser")
    public Optional<User> loginUser(@RequestBody userValidation userValidation) {
        try {
            return Optional.of(service.loginUser(userValidation.getEmail(), userValidation.getPassword()));
        } catch (RuntimeException e) {
            System.out.println("Login failed: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    
    
    @PostMapping("/loginAdmin")
  	public Optional<Airline> checkAdminLogin(@RequestBody adminValidation adminValidation) {
  		try {
            return Optional.of(service.loginAdmin(adminValidation.getAirlineEmail(), adminValidation.getAirlinePassword()));
        } catch (RuntimeException e) {
            System.out.println("Login failed: " + e.getMessage());
            return Optional.empty();
        }
  	}
	
	
	
}
