package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class secureUser {
	
	private int secureUID;
	private String username;
	private String password;
	private String roles;
	
	public secureUser(String username, String password, String roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	

}
