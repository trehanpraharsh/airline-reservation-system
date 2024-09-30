package com.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.FlightsDAOImpl;
import com.entity.FlightSeatAvailablity;
import com.entity.Flights;
import com.entity.Flights.flight_status;
import com.repo.FlightSeatAvailablityRepo;
import com.repo.FlightsRepo;

@Service
public class FlightsService {
	
	@Autowired
	private FlightsDAOImpl flightsDAOImpl;
	
	@Autowired
	private FlightsRepo flightsRepo;
	
	@Autowired
	private FlightSeatAvailablityRepo flightSeatAvailablityRepo;
	
	public Flights createNewFlight(Flights flight) {
		return flightsDAOImpl.createFlight(flight);
	}
	
	public List<Flights> displayFlights(){
		return flightsDAOImpl.loadFlights();
	}
	
	public Flights updateFlightListing(int flightID, Boolean is_disabled_val) {
		return flightsDAOImpl.updateFlightListing(flightID, is_disabled_val);
	}
	
	public Flights updateFlightStatus(int flightID, flight_status status) {
		return flightsDAOImpl.updateFlightStatus(flightID, status);
	}
	
	public void deleteFlight(int flightID) {
		flightsDAOImpl.deleteFlightByID(flightID);
	}
	
	public Flights searchFlightByID(int flightID) {
		return flightsDAOImpl.searchByID(flightID);
	}
	
	public List<Flights> searchFlight(String destination_city, String source_city, LocalDate departure_date){
		return flightsDAOImpl.searchFlight(destination_city, source_city, departure_date);
	}
	
	public List<Flights> loadFlightsByAirlineCode(String airline_code){
		return flightsDAOImpl.loadFlightsByAirlineCode(airline_code);
	}
	
	//just to create the seatavailablity
	public void createSeatAvailablity(int flightID, FlightSeatAvailablity availablity) {
		
		Flights theFlight = flightsRepo.getById(flightID);
		FlightSeatAvailablity seatAvailablity = new FlightSeatAvailablity(availablity.getEconomy_seats_left(), availablity.getPremium_economy_seats_left(), availablity.getBusiness_seats_left());
		seatAvailablity.setFlightID(theFlight);
		
		flightSeatAvailablityRepo.save(seatAvailablity);
	}
	
	
	public Flights createFlightAndSeatAvailablity(Flights flight) {
		
		Flights newFlight = new Flights(flight.getSource_airport(), flight.getDestination_airport(), flight.getDeparture_time(), flight.getArrival_time(), flight.getDeparture_date(), flight.getArival_date(), flight.getEconomy_seats(), flight.getPremium_economy_seats(), flight.getBusiness_seats(), flight.getEconomy_ticket_cost(), flight.getPremium_ticket_economy_cost(), flight.getBusiness_ticket_cost(), flight.getEconomy_baggage_allowance(), flight.getPremium_economy_baggage_allowance(), flight.getBusiness_baggage_allowance(), flight.isIsflight_disabled(), flight.getAirline_unique_code(), flight.getAirlineImage(), flight.getAirlineName());
		
		FlightSeatAvailablity newFlightSeatAvailablity = new FlightSeatAvailablity(flight.getEconomy_seats(), flight.getPremium_economy_seats(), flight.getBusiness_seats());
		
		
		flightsRepo.save(newFlight);
		
		newFlightSeatAvailablity.setFlightID(newFlight);
		
		flightSeatAvailablityRepo.save(newFlightSeatAvailablity);
		
		return newFlight;
	}
	
	
	public List<Flights> searchEconomyFlights(String destination_city, String source_city, LocalDate departure_date){
		return flightsDAOImpl.searchEconomyFlights(destination_city, source_city, departure_date);
	}
	
	public List<Flights> searchPremiumEconomyFlights(String destination_city, String source_city, LocalDate departure_date){
		return flightsDAOImpl.searchPremiumEconomyFlights(destination_city, source_city, departure_date);	
	}
	
	public List<Flights> searchBusinessFlights(String destination_city, String source_city, LocalDate departure_date){
		return flightsDAOImpl.searchBusinessFlights(destination_city, source_city, departure_date);	
	}
	
	
	public FlightSeatAvailablity updateSeatCount(int flightID, String seat_type) {
		FlightSeatAvailablity flightSeatStatus = flightSeatAvailablityRepo.getByFlight_id(flightID);
		switch (seat_type) {
		case "ECONOMY":
			int eco_seat_left = flightSeatStatus.getEconomy_seats_left();
			eco_seat_left = eco_seat_left - 1;
			flightSeatStatus.setEconomy_seats_left(eco_seat_left);
			flightSeatAvailablityRepo.save(flightSeatStatus);
			break;
		
		case "PREMIUM ECONOMY":
			int prem_seat_left = flightSeatStatus.getPremium_economy_seats_left();
			prem_seat_left = prem_seat_left - 1;
			flightSeatStatus.setPremium_economy_seats_left(prem_seat_left);
			flightSeatAvailablityRepo.save(flightSeatStatus);
			break;
			
		case "BUSINESS":
			int business_seat_left = flightSeatStatus.getBusiness_seats_left();
			business_seat_left = business_seat_left - 1;
			flightSeatStatus.setBusiness_seats_left(business_seat_left);
			flightSeatAvailablityRepo.save(flightSeatStatus);
			break;
		}
		return flightSeatStatus;
	}
	
	
	public FlightSeatAvailablity updateCancelledSeatCount(int flightID, String seat_type) {
		
		FlightSeatAvailablity flightSeatStatus = flightSeatAvailablityRepo.getByFlight_id(flightID);
		switch (seat_type) {
		case "economy":
			int eco_seat_left = flightSeatStatus.getEconomy_seats_left();
			eco_seat_left = eco_seat_left + 1;
			flightSeatStatus.setEconomy_seats_left(eco_seat_left);
			flightSeatAvailablityRepo.save(flightSeatStatus);
			break;
		
		case "premium_economy":
			int prem_seat_left = flightSeatStatus.getPremium_economy_seats_left();
			prem_seat_left = prem_seat_left + 1;
			flightSeatStatus.setPremium_economy_seats_left(prem_seat_left);
			flightSeatAvailablityRepo.save(flightSeatStatus);
			break;
			
		case "business":
			int business_seat_left = flightSeatStatus.getBusiness_seats_left();
			business_seat_left = business_seat_left + 1;
			flightSeatStatus.setBusiness_seats_left(business_seat_left);
			flightSeatAvailablityRepo.save(flightSeatStatus);
			break;
		}
		return flightSeatStatus;
		
	}

}
