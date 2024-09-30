package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Feedback;
import com.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	
	@Autowired
	private FeedbackService service;
	
	@PostMapping("/createfeedback")
	public Feedback createFeedback(@RequestBody Feedback feedback) {
		return service.createNewFeedback(feedback);
	}
	
	@GetMapping("/displayfeedback")
	public List<Feedback> displayFeedbacks(){
		return service.displayAllFeedbacks();
	}
	
}
