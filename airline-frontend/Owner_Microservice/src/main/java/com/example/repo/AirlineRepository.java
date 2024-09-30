package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
