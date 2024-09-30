package com.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString

@Entity
@Table(name = "flights")
public class Flights {
	
	@Id
	@GeneratedValue
//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "flightID")
	private int flight_id;
	private String source_airport;
	private String destination_airport;
	private LocalTime departure_time;
	private LocalTime arrival_time;
	private LocalDate departure_date; 
	private LocalDate arival_date;
	private int economy_seats;
	private int premium_economy_seats;
	private int business_seats;
	private double economy_ticket_cost;
	private double premium_ticket_economy_cost;
	private double business_ticket_cost;
	private int economy_baggage_allowance;
	private int premium_economy_baggage_allowance;
	private int business_baggage_allowance;
	private boolean isflight_disabled;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "flightID")
	private FlightSeatAvailablity flightSeatAvailablityID;
	
	
	//@OneToOne(cascade = CascadeType.ALL)
	private String airline_unique_code;
	
	public enum flight_status{
		ON_TIME,
		DELAYED,
		CANCELLED
	}
	
	@Enumerated(EnumType.STRING)
	private flight_status status = flight_status.ON_TIME;
	
	private String airlineName;
	private String airlineImage;

	
	
	public Flights(String source_airport, String destination_airport, LocalTime departure_time, LocalTime arrival_time,
			LocalDate departure_date, LocalDate arival_date, int economy_seats, int premium_economy_seats,
			int business_seats, double economy_ticket_cost, double premium_ticket_economy_cost,
			double business_ticket_cost, int economy_baggage_allowance, int premium_economy_baggage_allowance,
			int business_baggage_allowance, boolean isflight_disabled, String airline_unique_code, 
			String airlineName, String airlineImage) {
		super();
		this.source_airport = source_airport;
		this.destination_airport = destination_airport;
		this.departure_time = departure_time;
		this.arrival_time = arrival_time;
		this.departure_date = departure_date;
		this.arival_date = arival_date;
		this.economy_seats = economy_seats;
		this.premium_economy_seats = premium_economy_seats;
		this.business_seats = business_seats;
		this.economy_ticket_cost = economy_ticket_cost;
		this.premium_ticket_economy_cost = premium_ticket_economy_cost;
		this.business_ticket_cost = business_ticket_cost;
		this.economy_baggage_allowance = economy_baggage_allowance;
		this.premium_economy_baggage_allowance = premium_economy_baggage_allowance;
		this.business_baggage_allowance = business_baggage_allowance;
		this.isflight_disabled = isflight_disabled;
		this.airline_unique_code = airline_unique_code;
		this.airlineImage=airlineImage;
		this.airlineName=airlineName;
	}
	
	
	
		
	
	
	
	
	
	
	
	
//	public void getIsflight_disabled() {
//		return isfl
//	}
	
	
	

}
