package com.exam.services;

import java.util.Set;

import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.exceptions.UserAlreadyExistsException;
import com.exam.exceptions.UserNotFoundException;
import com.exam.helpers.SuccessMessage;

public interface UserService {
	public User createUser(User user, Set<UserRole> userRoles) throws UserAlreadyExistsException;
	public User getUserByUsername(String username) throws UserNotFoundException;
	public User getUserByEmail(String email) throws UserNotFoundException;
	public SuccessMessage deleteUser(Long userId) throws UserNotFoundException;
}
