package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class secureAdmin {
	
	private int secureAdID;
	private String username;
	private String password;
	private String roles;
	
	public secureAdmin(String username, String password, String roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	

}
