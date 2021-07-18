package com.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
	public User findByEmail(String email);
}
