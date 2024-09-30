package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.reactive.function.client.WebClient;

import com.entity.secureAdmin;
import com.entity.secureUser;
import com.model.Airline;
import com.model.User;
import com.repo.SecureAdminRepo;
import com.repo.SecureUserRepo;

@Service
public class AuthLService {
	
	 
	@Autowired
	@Qualifier("webclient")
	private WebClient.Builder builder;
	
	@Autowired
	private SecureAdminRepo secureAdminRepo;
	
	@Autowired
	private SecureUserRepo secureUserRepo;

    
	public secureUser registerSecureUser(secureUser securedUser) {
		return secureUserRepo.save(securedUser);
	}
	
	public secureAdmin registerSecureAdmin(secureAdmin securedAdmin) {
		return secureAdminRepo.save(securedAdmin);
	}
	
	public User loginUser(String email, String password) {
        // Find user by email
        Optional<secureUser> userOptional = secureUserRepo.findByUsername(email);
        if (userOptional.isPresent()) {
            secureUser user = userOptional.get();
            
            // Check if the provided password matches the stored hashed password
            if (BCrypt.checkpw(password, user.getPassword())) {
            	String getUserEntityURL = "http://user-ms/users/finduserbyemail/{email}";
            	
            	User userObj = builder.build()
            					.get()
            					.uri(getUserEntityURL, email)
            					.retrieve()
            					.bodyToMono(User.class)
            					.block();
            	
                return userObj;
                
            } else {
                throw new RuntimeException("Invalid password");
                
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
	
	
	public Airline loginAdmin(String email, String password) {
        // Find user by email
        Optional<secureAdmin> adminOptional = secureAdminRepo.findByUsername(email);
        if (adminOptional.isPresent()) {
            secureAdmin admin = adminOptional.get();
            
            // Check if the provided password matches the stored hashed password
            if (BCrypt.checkpw(password, admin.getPassword())) {
            	
            	String getAdminEntityURL = "http://owner-ms/owner/findadminbyemail/{email}";
            	
            	Airline adminObj = builder.build()
            					.get()
            					.uri(getAdminEntityURL, email)
            					.retrieve()
            					.bodyToMono(Airline.class)
            					.block();
            	
                return adminObj;
            	
                
            } else {
                throw new RuntimeException("Invalid password");
                
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
	
	
}

