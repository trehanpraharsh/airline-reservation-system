package com.dao;

import java.util.List;

import com.entity.Feedback;

public interface FeedbackDAO {
	
	public Feedback createFeedback(Feedback feedback);
	
	public List<Feedback> loadAllFeedbacks();
	

}
