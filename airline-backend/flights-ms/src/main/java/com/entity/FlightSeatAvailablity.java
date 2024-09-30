package com.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString

@Entity
@Table(name = "flight_seat_availablity")
public class FlightSeatAvailablity {
	
	@Id
	@GeneratedValue
	private int seatAvailablity_id;
	
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight_id")
	private Flights flightID;
	
	private int economy_seats_left;
	private int premium_economy_seats_left;
	private int business_seats_left;
	
	
	public FlightSeatAvailablity(int economy_seats_left, int premium_economy_seats_left,
			int business_seats_left) {
		super();
		this.economy_seats_left = economy_seats_left;
		this.premium_economy_seats_left = premium_economy_seats_left;
		this.business_seats_left = business_seats_left;
	}
	
	

}
