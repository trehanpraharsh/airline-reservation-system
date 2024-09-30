package com.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.secureUser;

public interface SecureUserRepo extends JpaRepository<secureUser, Integer> {
	
	public Optional<secureUser> findByUsername(String secureUEmail);

}
