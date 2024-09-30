package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Airline {
	
	private Long airlineId;
    private String airlineName;
    private String airlineImage;
    private String airlinePassword;
    private String airlineUniqueCode;
    private String airlineEmail;
    
	public Airline(String airlineName, String airlineImage, String airlinePassword, String airlineUniqueCode, String airlineEmail) {
		super();
		this.airlineName = airlineName;
		this.airlineImage = airlineImage;
		this.airlinePassword = airlinePassword;
		this.airlineUniqueCode = airlineUniqueCode;
		this.airlineEmail = airlineEmail;
	}
    
    

}
