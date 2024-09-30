package com.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.FlightSeatAvailablity;
import com.entity.Flights;
import com.entity.Flights.flight_status;
import com.repo.FlightSeatAvailablityRepo;
import com.repo.FlightsRepo;

import jakarta.transaction.Transactional;

@Component
public class FlightsDAOImpl implements FlightsDAO {
	
	@Autowired
	private FlightsRepo flightsRepo;
	
	@Autowired
	private FlightSeatAvailablityRepo seatAvailablityRepo;
	

	@Override
	@Transactional
	public Flights createFlight(Flights flight) {
		// TODO Auto-generated method stub
		return flightsRepo.save(flight);
	}

	@Override
	@Transactional
	public List<Flights> loadFlights() {
		// TODO Auto-generated method stub
		return flightsRepo.findAll();
	}

	@Override
	@Transactional
	//This functionality can be used by the owner to disable/enable flights
	public Flights updateFlightListing(int flightID, Boolean is_disabled_val) {
		Flights currentFlight = flightsRepo.getById(flightID);
		currentFlight.setIsflight_disabled(is_disabled_val);
		
		return flightsRepo.save(currentFlight);
	}
	
	
	
	public Flights updateFlightStatus(int flightID, flight_status status) {
		Flights currentFlight = flightsRepo.getById(flightID);
		currentFlight.setStatus(status);
		
		return flightsRepo.save(currentFlight);
	}
	
	
//	public Flights updateFlight(Flights flight) {
//		// TODO Auto-generated method stub
//		Flights currentFlight = flightsRepo.getById(flight.getFlight_id());
//		currentFlight.setSource_airport(flight.getSource_airport());
//		currentFlight.setDestination_airport(flight.getDestination_airport());
//		currentFlight.setDeparture_time(flight.getDeparture_time());
//		currentFlight.setArrival_time(flight.getArrival_time());
//		currentFlight.setDeparture_date(flight.getDeparture_date());
//		currentFlight.setArival_date(flight.getArival_date());
//		currentFlight.setEconomy_seats(flight.getEconomy_seats());
//		currentFlight.setPremium_economy_seats(flight.getPremium_economy_seats());
//		currentFlight.setBusiness_seats(flight.getBusiness_seats());
//		currentFlight.setEconomy_ticket_cost(flight.getEconomy_ticket_cost());
//		currentFlight.setPremium_ticket_economy_cost(flight.getPremium_ticket_economy_cost());
//		currentFlight.setBusiness_ticket_cost(flight.getBusiness_ticket_cost());
//		currentFlight.setEconomy_baggage_allowance(flight.getEconomy_baggage_allowance());
//		currentFlight.setPremium_economy_baggage_allowance(flight.getPremium_economy_baggage_allowance());
//		currentFlight.setBusiness_baggage_allowance(flight.getBusiness_baggage_allowance());
//		//this logic because we want to store the negiattion of flight status
//		currentFlight.setIsflight_disabled(!flight.isIsflight_disabled());
//		
//		currentFlight.setAirline_unique_code(flight.getAirline_unique_code());
//		
//		return currentFlight;
//	}

	@Override
	@Transactional
	public void deleteFlightByID(int flightID) {
		// TODO Auto-generated method stub
		flightsRepo.deleteById(flightID);
	}

	@Override
	@Transactional
	public List<Flights> searchFlight(String destination_city, String source_city, LocalDate departure_date) {
		// TODO Auto-generated method stub
		//yaha bhi same repo me create karke vohi way se jaana pdega as select my these many
		//parameter is not possible, like select * from flights where dest_city = param1 etc.
		return flightsRepo.searchFlight(destination_city, source_city, departure_date);
	}

	@Override
	@Transactional
	public List<Flights> loadFlightsByAirlineCode(String airline_code) {
		// TODO Auto-generated method stub
		return flightsRepo.loadFlightsByAirlineUniqueCode(airline_code);
	}

	@Override
	@Transactional
	public Flights searchByID(int flightID) {
		// TODO Auto-generated method stub
		return flightsRepo.findById(flightID).get();
	}

	@Override
	public List<Flights> searchEconomyFlights(String destination_city, String source_city, LocalDate departure_date) {
		// TODO Auto-generated method stub
		List<Flights> searchedFlights = flightsRepo.searchFlight(destination_city, source_city, departure_date);
		return searchedFlights.stream().filter(flight -> {
			FlightSeatAvailablity seatAvailablity = seatAvailablityRepo.getByFlight_id(flight.getFlight_id());
			return seatAvailablity.getEconomy_seats_left() > 0;
		})
		.collect(Collectors.toList());
	}

	@Override
	public List<Flights> searchPremiumEconomyFlights(String destination_city, String source_city,
			LocalDate departure_date) {
		// TODO Auto-generated method stub
		List<Flights> searchedFlights = flightsRepo.searchFlight(destination_city, source_city, departure_date);
		return searchedFlights.stream().filter(flight -> {
			FlightSeatAvailablity seatAvailablity = seatAvailablityRepo.getByFlight_id(flight.getFlight_id());
			return seatAvailablity.getPremium_economy_seats_left() > 0;
		})
		.collect(Collectors.toList());
	}

	@Override
	public List<Flights> searchBusinessFlights(String destination_city, String source_city, LocalDate departure_date) {
		// TODO Auto-generated method stub
		List<Flights> searchedFlights = flightsRepo.searchFlight(destination_city, source_city, departure_date);
		return searchedFlights.stream().filter(flight -> {
			FlightSeatAvailablity seatAvailablity = seatAvailablityRepo.getByFlight_id(flight.getFlight_id());
			return seatAvailablity.getBusiness_seats_left() > 0;
		})
		.collect(Collectors.toList());
	}

}
