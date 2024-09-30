package com.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.entity.Flights;
import com.entity.Flights.flight_status;

public interface FlightsDAO {
	
	public Flights createFlight(Flights flight);
	
	public List<Flights> loadFlights();
	
	//This method is normal in ideal case it will be updated via flight_ID
	public Flights updateFlightListing(int flightID, Boolean is_disabled_val);
	
	//for admin to update the flight status
	public Flights updateFlightStatus(int flightID, flight_status status);
	
	//searchByID when we click the update button it should fetch all the details and fill the
	//text inputs also the disabled ones
	public Flights searchByID(int flightID) ;
	
	public void deleteFlightByID(int flightID);
	
	//this is the ideal case jab humko frontend se bhejna hoga data(as in flight id) to the
	//backend taaki uspe phir operations perform ho
	//public Flights updateFlightbyID(int flightID);
	
	//is_disabled, status check (This has to be extended for round trip also later on 
	//method overriding se - bhavya)
	public List<Flights> searchFlight(String destination_city, String source_city, LocalDate departure_date);
	
	//for the admin to search for his specific airlines(ig this has to be implemented
	//the repo way, like repo me create karke functionality and pass karke @query me)
	public List<Flights> loadFlightsByAirlineCode(String airline_code);
	
	
	//Searching and displaying seats to user based upon his preference
	public List<Flights> searchEconomyFlights(String destination_city, String source_city, LocalDate departure_date);

	public List<Flights> searchPremiumEconomyFlights(String destination_city, String source_city, LocalDate departure_date);
	
	public List<Flights> searchBusinessFlights(String destination_city, String source_city, LocalDate departure_date);	
	
	
	//Send the ticket cost to the bookings
}
