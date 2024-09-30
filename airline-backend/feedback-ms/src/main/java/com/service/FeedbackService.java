package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.FeedbackDAOImpl;
import com.entity.Feedback;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackDAOImpl feedbackDAOImpl;
	
	public Feedback createNewFeedback(Feedback feedback) {
		return feedbackDAOImpl.createFeedback(feedback);
	}
	
	public List<Feedback> displayAllFeedbacks(){
		return feedbackDAOImpl.loadAllFeedbacks();
	}

}
