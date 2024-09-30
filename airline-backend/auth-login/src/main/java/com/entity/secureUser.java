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
@Table(name = "secureuser")
public class secureUser {

	@Id
	@GeneratedValue
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
