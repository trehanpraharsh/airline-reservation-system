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
@Table(name = "feedback")
public class Feedback {
	
	@Id
	@GeneratedValue
	private int feedback_id;
	private int user_id;
	private int booking_id; 
	private String description;
	
	public Feedback(int user_id, int booking_id, String description) {
		super();
		this.user_id = user_id;
		this.booking_id = booking_id;
		this.description = description;
	}
		

}
