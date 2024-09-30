package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.dao.AirlineDao;
import com.dao.AirportDao;
import com.dao.ScheduleDao;
import com.entity.Airline;
import com.entity.Airport;
import com.model.FeedbackModel;
import com.model.Flights;
import com.model.OfferModel;
import com.model.OfferResponseModel;
import com.model.PromotionModel;
import com.model.PromotionResponseModel;
import com.model.secureAdmin;
import com.repo.AirlineRepository;

import reactor.core.publisher.Flux;

@Service
public class OwnerService 
{
	@Autowired
	AirlineDao airlineDao;
	
	@Autowired
	AirportDao airportDao;
	
	@Autowired
	ScheduleDao scheduleDao;
	
	 @Autowired
	 @Qualifier("webclient")
	 private WebClient.Builder builder;
	 
	 
	 public Airport addAirport(Airport airport)
	    {
	        airportDao.save(airport);
	        return airport;
	    }

	    public String deleteAirport(String airportUniqueCode)
	    {
	        airportDao.deleteByUniqueCode(airportUniqueCode);
	        return "Airport Deleted Successfully";
	    }
	    
	    public List<Airport> getAllAirports()
	    {
	        return airportDao.findAll();
	    }
	    
	    public List<Airline> getAllAirlines()
	    {
	        return airlineDao.findAll();
	    }
	   
	    
	    public Airline addAirline(Airline airline)
	    {
//	    	//concept to save the secured admin in the auth while the time of logging in
//	        secureAdmin securedAdmin = new secureAdmin(airline.getAirlineEmail(), airline.getAirlinePassword(), "ADMIN");
//	        
//	        String registerSecuredAdminURL = "http://localhost:8089/auth/secureadmin";
//	        
//	        secureAdmin loadedSecureAdmin = builder.build()
//	        								.post()
//	        								.uri(registerSecuredAdminURL)
//	        								.bodyValue(securedAdmin)
//	        								.retrieve()
//	        								.bodyToMono(secureAdmin.class)
//	        								.block();

			String hashedPassword = BCrypt.hashpw(airline.getAirlinePassword(), BCrypt.gensalt());
        airline.setAirlinePassword(hashedPassword); // Set the hashed password
        
      //concept to save the secured admin in the auth while the time of logging in
        secureAdmin securedAdmin = new secureAdmin(airline.getAirlineEmail(), airline.getAirlinePassword(), "ADMIN");
        
        String registerSecuredAdminURL = "http://auth-login/auth/secureadmin";
        
        secureAdmin loadedSecureAdmin = builder.build()
        								.post()
        								.uri(registerSecuredAdminURL)
        								.bodyValue(securedAdmin)
        								.retrieve()
        								.bodyToMono(secureAdmin.class)
        								.block();
	    	
	    	return airlineDao.save(airline);	
	    }
	    
	    public Optional<Airline> findByAirlineEmail(String airlineEmail){
	    	return airlineDao.findByAirlineEmail(airlineEmail);
	    }
	    
	    public String deleteAirline(Long id) {
	        airlineDao.deleteById(id);
	        return "Airport Deleted Successfully";
	    }
	    

	    public String addOffer(@RequestBody OfferModel offer)
	    {
	    	builder.build()
		           .post()
		           .uri("http://offers-ms/offers/createoffer")
		           .bodyValue(offer)
		           .retrieve()
	    		   .bodyToMono(OfferModel.class)
	    		   .block();
	    	
	    	return "Offer added";
	    }

	    //disable / enable offer
	    public String updateOfferList(@PathVariable Long offerId, @PathVariable int status)
	    {
	    	builder.build()
	           .put()
	           .uri("http://offers-ms/offers/updateOfferListing/{offerId}/{status}",offerId,status)
	           .retrieve()
 		   .bodyToMono(Void.class)
 		   .block();
	    	
	    	return "status updated";
	    	
	    }

	    //get offers applied
	    public Flux<OfferResponseModel> getOffers()
	    {
			return builder.build()
					.get()
					.uri("http://offers-ms/offers/list")
					.retrieve()
					.bodyToFlux(OfferResponseModel.class);    	
	    }

	    //add promotional offer
	    public String addPromotion(@RequestBody PromotionModel promotion)
	    {
	    	builder.build()
	           .post()
	           .uri("http://offers-ms/promotions/createpromotion")
	           .bodyValue(promotion)
	           .retrieve()
 		   .bodyToMono(OfferModel.class)
 		   .block();
 	
	    	return "Promotion added";
	    	
	    }

	    public String updatePromotionList(@PathVariable Long promotionId, @PathVariable String status)
	    {
	    	builder.build()
	           .put()
	           .uri("http://offers-ms/promotions/updatePromotionListing/{promotionId}/{status}",promotionId,status)
	           .retrieve()
		   .bodyToMono(Void.class)
		   .block();
	    	
	    	return "promotion updated";
	    	
	    }

	    public Flux<PromotionResponseModel> getPromotion()
	    {
	    	return builder.build()
					.get()
					.uri("http://offers-ms/promotions/list")
					.retrieve()
					.bodyToFlux(PromotionResponseModel.class);  
	    }

	   public List<Flights> displayAllFlights(){
		   
		   String loadAllFlightsURL = "http://flights-ms/flights/showallflights";
		   
		   List<Flights> flights = builder.build()
				   						.get()
				   						.uri(loadAllFlightsURL)
				   						.retrieve()
				   						.bodyToFlux(Flights.class)
				   						.collectList()
				   						.block();
				   		
		   return flights;
		   
	   }
	   
	   public Flights updateFlightListing(int flightID, boolean is_disabled_val) {
		
		   String updateListingURL = "http://flights-ms/flights/updateflightlisting/{flightID}/{is_disabled_val}";
		   
		   Flights updatedFlight = builder.build()
				   						.put()
				   						.uri(updateListingURL, flightID, is_disabled_val)
				   						.retrieve()
				   						.bodyToMono(Flights.class)
				   						.block();
		   
		   return updatedFlight;
		   
	   }
	   
}
