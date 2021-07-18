package com.exam.services.implementations;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.exceptions.UserAlreadyExistsException;
import com.exam.exceptions.UserNotFoundException;
import com.exam.helpers.Helper;
import com.exam.helpers.SuccessMessage;
import com.exam.repositories.RoleRepository;
import com.exam.repositories.UserRepository;
import com.exam.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws UserAlreadyExistsException {
		User localUsername = this.userRepository.findByUsername(user.getUsername());
		User localEmail = this.userRepository.findByEmail(user.getEmail());
		User savedUser;
		if (localUsername != null || localEmail != null) {
			throw new UserAlreadyExistsException(Helper.USER_ALREADY_EXISTS);
		} else {
			for (UserRole userRole : userRoles) {
				this.roleRepository.save(userRole.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			savedUser = this.userRepository.save(user);
		}
		return savedUser;
	}

	@Override
	public User getUserByUsername(String username) throws UserNotFoundException {
		User user = this.userRepository.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException(Helper.USER_NOT_FOUND);
		}

		return user;
	}

	@Override
	public User getUserByEmail(String email) throws UserNotFoundException {
		User user = this.userRepository.findByEmail(email);
		if (user == null) {
			throw new UserNotFoundException(Helper.USER_NOT_FOUND);
		}
		return user;
	}

	@Override
	public SuccessMessage deleteUser(Long userId) throws UserNotFoundException {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(Helper.USER_NOT_FOUND));
		this.userRepository.deleteById(user.getId());
		return new SuccessMessage(Helper.USER_DELETED);
	}

}
