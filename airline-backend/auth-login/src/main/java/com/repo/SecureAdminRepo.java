package com.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.secureAdmin;

public interface SecureAdminRepo extends JpaRepository<secureAdmin, Integer> {
	
	public Optional<secureAdmin> findByUsername(String secureAdEmail);

}
