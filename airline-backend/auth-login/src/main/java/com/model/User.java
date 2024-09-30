package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	 	private Long userId;
	    private String email;
	    private String password;
	    private String firstName;
	    private String lastName;
	    private String phoneNumber;
	    
		public User(String email, String password, String firstName, String lastName, String phoneNumber) {
			super();
			this.email = email;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.phoneNumber = phoneNumber;
		}

}
