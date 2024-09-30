package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Flights;
import com.model.Flights.flight_status;
import com.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	
	
	//this @lazy is done to lazy load one component so that dependencies of some of the 
	//beans in the application context do not form a cycle
	@Lazy
	@Autowired
	private AdminService service;
	
	
//	@GetMapping("/loadallregisteredflights")
//	public List<Flights> loadAllRegisteredFlights(){
//		return service.loadAllFlights();
//	}
	
	@GetMapping("/loadairlinesflight/{airline_code}")
	public List<Flights> loadAirlinesFlight(@PathVariable String airline_code){
		return service.loadFlightByAirlineCode(airline_code);
	}
	
	@PostMapping("/addnewflight")
	public Flights addNewFlight(@RequestBody Flights flight) {
		return service.createNewFlight(flight);
	}
	
	@PutMapping("/updateflightstatus/{flightID}/{status}")
	public Flights updateFlightDetails(@PathVariable int flightID, @PathVariable flight_status status) {
		return service.updateFlightStatus(flightID, status);
	}
	
	@GetMapping("/searchbyid/{flightID}")
	public Flights searchByID(@PathVariable int flightID) {
		return service.searchByID(flightID);
	}
	
	@DeleteMapping("/deleteflightbyid/{flightID}")
	public void deleteFlightByID(@PathVariable int flightID) {
		service.deleteFlight(flightID);
	}
	

}
