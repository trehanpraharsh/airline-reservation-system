package com.dao.impl;

import com.dao.AirlineDao;
import com.entity.Airline;
import com.model.secureAdmin;
import com.repo.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
public class AirlineDaoImpl implements AirlineDao {

    @Autowired
    private AirlineRepository airlineRepository;
    
    
	@Autowired
	@Qualifier("webclient")
	private WebClient.Builder builder;

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    public Optional<Airline> findById(Long id) {
        return airlineRepository.findById(id);
    }
    
    
    public Optional<Airline> findByAirlineEmail(String airlineEmail){
    	return airlineRepository.findByAirlineEmail(airlineEmail);
    }

    @Override
    public Airline save(Airline airline) {
    
        return airlineRepository.save(airline);
    }

    @Override
    public void deleteById(Long id) {
        airlineRepository.deleteById(id);
    }

	@Override
	public Optional<Airline> findByAirlineUniqueCode(String airlineUniqueCode) {
		// TODO Auto-generated method stub
		return airlineRepository.findByAirlineUniqueCode(airlineUniqueCode);
	}
}
