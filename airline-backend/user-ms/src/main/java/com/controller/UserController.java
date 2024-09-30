//package com.controller;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.entity.UserEntity;
////import com.service.AuthService;
//import com.service.UserService;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//	@Autowired
//	private UserService userService;
//	
////	@Autowired
////	private AuthService authService;
//	
////	@PostMapping("/register")
////	public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user){
////		UserEntity registeredUser=userService.registerUser(user);
////		return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);
////	}
//
////	@PostMapping("/login")
////	public ResponseEntity<UserEntity> loginUser(@RequestParam String email,@RequestParam String password){
////		Optional<UserEntity> user=authService.loginUser(email, password);
////		if(user.isPresent()) {
////			return new ResponseEntity<>(user.get(), HttpStatus.OK);
////		}
////		else {
////			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
////		}
////	}
////
////	@GetMapping("/{userId}")
////	public ResponseEntity<UserEntity> getUserbyId(@PathVariable Long userId){
////		Optional<UserEntity> user=userService.getUserbyId(userId);
////		return user.map(value-> new ResponseEntity<>(value, HttpStatus.OK))
////				.orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
////	}
//	
//	@PostMapping("/register")
//	public UserEntity registerUser(@RequestBody UserEntity user) {
//		return userService.registerUser(user);
//	}
//}

package com.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.model.Airline;
import com.model.Flights;
import com.model.adminValidation;
import com.model.userValidation;
import com.entity.UserEntity;
import com.service.UserService;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private AuthService authService;

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity user) {
//        try {
//            userService.registerUser(user);
//            return "User registered successfully";  // Success message
//        } catch (RuntimeException e) {
//            // Handle exception, return a meaningful message
//            return e.getMessage(); // e.g., "User already exists"
//        }
    	return userService.registerUser(user);
    }

//    @PostMapping("/loginUser")
//    public Optional<UserEntity> loginUser(@RequestBody userValidation userValidation) {
//        try {
//            return Optional.of(authService.loginUser(userValidation.getEmail(), userValidation.getPassword()));
//        } catch (RuntimeException e) {
//            System.out.println("Login failed: " + e.getMessage());
//            return Optional.empty();
//        }
//    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestBody userValidation validation) {
        return userService.resetPassword(validation.getEmail(), validation.getPassword());  // Return the response directly
    }
    
    
    //access = user-economy
  	@GetMapping("/searcheconomyflight/{source_city}/{destination_city}/{departure_date}")
  	public List<Flights> searchEconomyFlights(@PathVariable String destination_city, @PathVariable String source_city, @PathVariable LocalDate departure_date){
  		return userService.loadAvailableEconomyTickets(destination_city, source_city, departure_date);
  	}
  	
  	//access = user-premium_economy
  	@GetMapping("/searchpremiumeconomyflight/{source_city}/{destination_city}/{departure_date}")
  	public List<Flights> searchPremiumEconomyFlights(@PathVariable String destination_city, @PathVariable String source_city, @PathVariable LocalDate departure_date){
  		return userService.loadAvailablePremiumEconomyTickets(destination_city, source_city, departure_date);
  	}
  		
  	//access = user-business
  	@GetMapping("/searchbusinessflight/{source_city}/{destination_city}/{departure_date}")
  	public List<Flights> searchBusinessFlights(@PathVariable String destination_city, @PathVariable String source_city, @PathVariable LocalDate departure_date){
  		return userService.loadAvailableBusinessTickets(destination_city, source_city, departure_date);
  		
  	}
  	
//  	@PostMapping("/loginAdmin")
//  	public Optional<Airline> checkAdminLogin(@RequestBody adminValidation adminValidation) {
//  		try {
//            return Optional.of(authService.loginAdmin(adminValidation.getAirlineUniqueCode(), adminValidation.getAirlinePassword()));
//        } catch (RuntimeException e) {
//            System.out.println("Login failed: " + e.getMessage());
//            return Optional.empty();
//        }
//  	}
  	
  	@GetMapping("/finduserbyemail/{userEmail}")
  	public Optional<UserEntity> findUserByEmail(@PathVariable String userEmail){
  		return userService.findByUserEmail(userEmail);
  	}
  	
  	
}
