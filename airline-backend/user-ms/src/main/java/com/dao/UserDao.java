package com.dao;

import java.util.Optional;

import com.entity.UserEntity;

public interface UserDao {
	Optional<UserEntity> findById(Long userId);
	UserEntity save(UserEntity user);
	Optional<UserEntity> findByEmail(String email);
}