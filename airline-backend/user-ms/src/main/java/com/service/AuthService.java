//package com.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.HttpStatusCodeException;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import com.entity.UserEntity;
//import com.model.Airline;
//import com.model.Flights;
//import com.repo.UserRepo;
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private UserRepo userRepo; // Directly using UserRepo here
//    
//    
//    @Lazy 
//	@Autowired
//	@Qualifier("webclient")
//	private WebClient.Builder builder;
//    
//
//    public UserEntity loginUser(String email, String password) {
//        // Find user by email
//        Optional<UserEntity> userOptional = userRepo.findByEmail(email);
//        if (userOptional.isPresent()) {
//            UserEntity user = userOptional.get();
//            
//            // Check if the provided password matches the stored hashed password
//            if (BCrypt.checkpw(password, user.getPassword())) {
//                return user;
//            } else {
//                throw new RuntimeException("Invalid password");
//                
//            }
//        } else {
//            throw new RuntimeException("User not found");
//        }
//    }
//    
//    
//    //admin-login check which returns the airline object
//    public Airline loginAdmin(String airlineUniqueCode, String airlinePassword) {
//    	
//    	String loginAirlineURL = "http://localhost:8083/airlines/getairlinebycode/{airlineUniqueCode}";
//    	
//    	Airline airlineOptional = builder.build()
//				.get()
//				.uri(loginAirlineURL, airlineUniqueCode)
//				.retrieve()
//				.bodyToMono(Airline.class)
//				.block();
//    	
//    	
//    	if (airlineOptional != null) {
////            Airline airlineObj = airlineOptional;
//            
//            // Check if the provided password matches the stored hashed password
//            if (BCrypt.checkpw(airlinePassword, airlineOptional.getAirlinePassword())) {
//                return airlineOptional;
//            } else {
//                throw new RuntimeException("Invalid password");
//                
//            }
//        } else {
//            throw new RuntimeException("User not found");
//        }
//    			
//    	
//    }
//    
//    
//    
//}
//
