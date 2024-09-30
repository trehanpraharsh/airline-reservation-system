package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.FlightSeatAvailablity;

import jakarta.transaction.Transactional;

@Repository
public interface FlightSeatAvailablityRepo extends JpaRepository<FlightSeatAvailablity, Integer> {
	
	@Query(value = "select * from flight_seat_availablity where flight_id=?1", nativeQuery = true)
	@Transactional
	public FlightSeatAvailablity getByFlight_id(int flightID);

}
