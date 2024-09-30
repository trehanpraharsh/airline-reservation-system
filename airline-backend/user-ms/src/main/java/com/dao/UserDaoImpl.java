package com.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.UserEntity;
import com.repo.UserRepo;

@Component
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private UserRepo userRepo;

    @Override
    public Optional<UserEntity> findById(Long userId) {
        return userRepo.findById(userId);
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepo.save(user);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepo.findByEmail(email); // Using the custom query
    }
}
