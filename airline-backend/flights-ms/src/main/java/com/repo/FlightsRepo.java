package com.repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Flights;

import jakarta.transaction.Transactional;

@Repository
public interface FlightsRepo extends JpaRepository<Flights, Integer> {
	
	@Query(value = "select * from Flights where source_airport=?2 and destination_airport=?1 and departure_date=?3 and isflight_disabled=false", nativeQuery = true)
	@Transactional
	public List<Flights> searchFlight(String destination_city, String source_city, LocalDate departure_date); 

	
	@Query(value = "select * from Flights where airline_unique_code=?1", nativeQuery = true)
	@Transactional
	public List<Flights> loadFlightsByAirlineUniqueCode(String airline_code);
	
	
}

