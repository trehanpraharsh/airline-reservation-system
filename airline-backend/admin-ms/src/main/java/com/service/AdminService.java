package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.model.Flights;
import com.model.Flights.flight_status;

@Service
public class AdminService {
	
	
//	@Bean("webclient")
//	//@LoadBalanced
//	public WebClient.Builder getWebClient(){
//		return WebClient.builder();
//	}
	
	
	//this @lazy is done to lazy load one component so that dependencies of some of the 
	//beans in the application context do not form a cycle
	//@Lazy 
	@Autowired
	@Qualifier("webclient")
	private WebClient.Builder builder;
	
	
	//this is just for owner
//	public List<Flights> loadAllFlights(){
//		String loadAllFlightsURL = "http://localhost:8081/flights/showallflights";
//		List<Flights> allRegisteredFlights = builder.build()
//											.get()
//											.uri(loadAllFlightsURL)
//											.retrieve()
//											.bodyToFlux(Flights.class)
//											.collectList()
//											.block();
//		
//		
//		return allRegisteredFlights;
//		
//	}
	
	//here in order to pass something as a parameter we have to use uri(uriBuilder - >
	//uriBuilder
    //.path("/products/{id}")
    //.build(2))
	public List<Flights> loadFlightByAirlineCode(String airline_code){
		String loadFlightByAirlineCodeURL = "http://flights-ms/flights/searchairlineflights/{airline_code}";
		List<Flights> airlinesFlight = builder.build()
										.get()
										.uri(loadFlightByAirlineCodeURL, airline_code)
										.retrieve()
										.bodyToFlux(Flights.class)
										.collectList()
										.block();
		
		return airlinesFlight;
	}
	
	public Flights createNewFlight(Flights flight) {
		 
		String createFlightURL = "http://flights-ms/flights/createflight";
		Flights newFlight = builder.build()
							.post()
							.uri(createFlightURL)
							.bodyValue(flight)
							.retrieve()
							.bodyToMono(Flights.class)
							.block();
		
		
		return newFlight;
							
	}
	
	public Flights updateFlightStatus(int flightID, flight_status status) {
		 
		String updateFlightURL = "http://flights-ms/flights/updateflightstatus/{flightID}/{status}";
		Flights updatedFlight = builder.build()
							.put()
							.uri(updateFlightURL, flightID, status)
							.retrieve()
							.bodyToMono(Flights.class)
							.block();
		
		return updatedFlight;
							
	}
	
	public void deleteFlight(int flightID) {
		String deleteFlightURL = "http://flights-ms/flights/deleteflight/{flightID}";
		builder.build()
				.delete()
				.uri(deleteFlightURL, flightID)
				.retrieve()
				.bodyToMono(Flights.class)
				.block();
	}
	
	public Flights searchByID(int flightID) {
		String searchedFlightURL = "http://flights-ms/flights/searchbyid/{flightID}";
		Flights searchedFlight = builder.build()
										.get()
										.uri(searchedFlightURL, flightID)
										.retrieve()
										.bodyToMono(Flights.class)
										.block();
		
		return searchedFlight;
	}

}
