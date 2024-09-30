package com.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionModel 
{	
	private String airlineName;
    private String promotionCode;
    private String description;
    private double discountPercentage;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private int isActive;
}
