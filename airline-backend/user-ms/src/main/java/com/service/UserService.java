package com.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;

import com.dao.UserDao;
import com.entity.UserEntity;
import com.model.Flights;
import com.model.secureUser;
import com.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepo userRepo;
    
	@Autowired
	@Qualifier("webclient")
	private WebClient.Builder builder;
    

    public Optional<UserEntity> getUserbyId(Long userId) {
        return userDao.findById(userId);
    }
    
    public Optional<UserEntity> findByUserEmail(String userEmail){
    	return userRepo.findByEmail(userEmail);
    }

    public UserEntity registerUser(UserEntity user) {
        
        Optional<UserEntity> existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        // Encrypt the password before saving
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword); // Set the hashed password

        
        //concept to save the secured user in the auth while the time of logging in
        secureUser securedUser = new secureUser(user.getEmail(), user.getPassword(), "USER");
        
        String registerSecuredUserURL = "http://auth-login/auth/secureuser";
        
        secureUser loadedSecureUser = builder.build()
        								.post()
        								.uri(registerSecuredUserURL)
        								.bodyValue(securedUser)
        								.retrieve()
        								.bodyToMono(secureUser.class)
        								.block();
        
       
        return userDao.save(user);
    }
    public String resetPassword(String email, String newPassword) {
        Optional<UserEntity> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            user.setPassword(hashedPassword);
            userDao.save(user);
            System.out.println("Password reset successful");
            return "Password reset successful";  
        } else {
        	System.out.println("Password reset failed");
            return "Password reset failed";  
        }
    }
    
    
    //user-economy
    public List<Flights> loadAvailableEconomyTickets(String destination_city, String source_city, LocalDate departure_date){
    	String loadAvailableEconomyTicketURL = "http://flights-ms/flights/searcheconomyflight/{source_city}/{destination_city}/{departure_date}";
    	List<Flights> availableEconomyFlights = builder.build()
    				.get()
    				.uri(loadAvailableEconomyTicketURL, source_city, destination_city, departure_date)
    				.retrieve()
    				.bodyToFlux(Flights.class)
    				.collectList()
    				.block();
    	
    	return availableEconomyFlights;
    }
    
    
    //user-premium-economy
    public List<Flights> loadAvailablePremiumEconomyTickets(String destination_city, String source_city, LocalDate departure_date){
    	String loadAvailablePremiumEconomyTicketURL = "http://flights-ms/flights/searchpremiumeconomyflight/{source_city}/{destination_city}/{departure_date}";
    	List<Flights> availablePremiumEconomyFlights = builder.build()
    				.get()
    				.uri(loadAvailablePremiumEconomyTicketURL, source_city, destination_city, departure_date)
    				.retrieve()
    				.bodyToFlux(Flights.class)
    				.collectList()
    				.block();
    	
    	return availablePremiumEconomyFlights;
    }
    
    
    //user-business
    public List<Flights> loadAvailableBusinessTickets(String destination_city, String source_city, LocalDate departure_date){
    	String loadAvailableBusinessTicketURL = "http://flights-ms/flights/searchbusinessflight/{source_city}/{destination_city}/{departure_date}";
    	List<Flights> availableBusinessFlights = builder.build()
    				.get()
    				.uri(loadAvailableBusinessTicketURL, source_city, destination_city, departure_date)
    				.retrieve()
    				.bodyToFlux(Flights.class)
    				.collectList()
    				.block();
    	
    	return availableBusinessFlights;
    }
    
    
    
}
