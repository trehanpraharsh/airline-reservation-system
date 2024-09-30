package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "secureadmin")
public class secureAdmin {

	@Id
	@GeneratedValue
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
