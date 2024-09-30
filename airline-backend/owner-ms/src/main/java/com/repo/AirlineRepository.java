package com.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
	
	public Optional<Airline> findByAirlineUniqueCode(String airlineUniqueCode);
	public Optional<Airline> findByAirlineEmail(String airlineEmail);
}
