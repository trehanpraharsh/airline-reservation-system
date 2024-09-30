package com.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    
    @Query(value = "select * from users where email=?1", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);
}
