package com.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.FlightSeatAvailablity;
import com.entity.Flights;
import com.entity.Flights.flight_status;
import com.service.FlightsService;

@RestController
@RequestMapping("/flights")
@CrossOrigin(origins = "*")
public class FlightController {
	
	@Autowired
	private FlightsService service;
	
	//EXTRAA NOWW access = admin
	@PostMapping("/addflight")
	public Flights addNewFlight(@RequestBody Flights flight) {
		return service.createNewFlight(flight);
	}
	
	//access = owner
	@GetMapping("/showallflights")
	public List<Flights> showAllFlights(){
		return service.displayFlights();
	}
	
	//access = owner
	@PutMapping("/updateflightlisting/{flightID}/{is_disabled_val}")
	public Flights updateFlightDetails(@PathVariable int flightID, @PathVariable Boolean is_disabled_val) {
		return service.updateFlightListing(flightID, is_disabled_val);
	}
	
	//access = admin
	@PutMapping("/updateflightstatus/{flightID}/{status}")
	public Flights updateFlightStatus(@PathVariable int flightID, @PathVariable flight_status status) {
		return service.updateFlightStatus(flightID, status);
	}
	
	//access = admin
	@DeleteMapping("/deleteflight/{flightID}")
	public void deleteFlight(@PathVariable Integer flightID) {
		service.deleteFlight(flightID);
	}
	
	//access = admin
	@GetMapping("/searchbyid/{flightID}")
	public Flights searchFlightByID(@PathVariable int flightID) {
		return service.searchFlightByID(flightID);
	}
	
	//EXTRAAAA NOW access = user
	@GetMapping("/searchflight/{source_city}/{destination_city}/{departure_date}")
	public List<Flights> searchFlights(@PathVariable String destination_city, @PathVariable String source_city, @PathVariable LocalDate departure_date){
		return service.searchFlight(destination_city, source_city, departure_date);
	}
	
	//acess = admin
	@GetMapping("/searchairlineflights/{airline_code}")
	public List<Flights> searchAirlineFlights(@PathVariable String airline_code){
		return service.loadFlightsByAirlineCode(airline_code);
	}
	
	//extra
	@PostMapping("/seatavailablity/{flightID}")
	public void createNewSeatAvailablity(@PathVariable int flightID, @RequestBody FlightSeatAvailablity seatAvailablity) {
		service.createSeatAvailablity(flightID, seatAvailablity);
	}
	
	//access = admin
	@PostMapping("/createflight")
	public Flights createFlightAndSeatAvailablity(@RequestBody Flights flight) {
		return service.createFlightAndSeatAvailablity(flight);
	}
	
	//access = user-economy
	@GetMapping("/searcheconomyflight/{source_city}/{destination_city}/{departure_date}")
	public List<Flights> searchEconomyFlights(@PathVariable String destination_city, @PathVariable String source_city, @PathVariable LocalDate departure_date){
		return service.searchEconomyFlights(destination_city, source_city, departure_date);
	}
	
	//access = user-premium_economy
	@GetMapping("/searchpremiumeconomyflight/{source_city}/{destination_city}/{departure_date}")
	public List<Flights> searchPremiumEconomyFlights(@PathVariable String destination_city, @PathVariable String source_city, @PathVariable LocalDate departure_date){
		return service.searchPremiumEconomyFlights(destination_city, source_city, departure_date);
	}
		
	//access = user-business
	@GetMapping("/searchbusinessflight/{source_city}/{destination_city}/{departure_date}")
	public List<Flights> searchBusinessFlights(@PathVariable String destination_city, @PathVariable String source_city, @PathVariable LocalDate departure_date){
		return service.searchBusinessFlights(destination_city, source_city, departure_date);
	}
	
	//acess = booking
	@PutMapping("/updateseatcount/{flightID}/{seat_type}")
	public FlightSeatAvailablity updateSeatCount(@PathVariable int flightID, @PathVariable String seat_type) {
		return service.updateSeatCount(flightID, seat_type);
	}
	
	//access = booking
	@PutMapping("/updatecancelledseatcount/{flightID}/{seat_type}")
	public FlightSeatAvailablity updateCancelledSeatCount(@PathVariable int flightID, @PathVariable String seat_type) {
		return service.updateCancelledSeatCount(flightID, seat_type);
	}
	

}
