package com.model;

import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString

public class Flights {
	
	
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
	

}
