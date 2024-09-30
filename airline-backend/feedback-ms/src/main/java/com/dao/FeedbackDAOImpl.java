package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.Feedback;
import com.repo.FeedbackRepo;

import jakarta.transaction.Transactional;

@Component
public class FeedbackDAOImpl implements FeedbackDAO {
	
	@Autowired
	private FeedbackRepo repo;
	

	@Override
	@Transactional
	public Feedback createFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return repo.save(feedback);
	}

	@Override
	@Transactional
	public List<Feedback> loadAllFeedbacks() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
