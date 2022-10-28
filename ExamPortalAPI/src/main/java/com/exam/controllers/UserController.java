package com.exam.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entities.Role;
import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.exceptions.UserAlreadyExistsException;
import com.exam.exceptions.UserNotFoundException;
import com.exam.helpers.Helper;
import com.exam.helpers.SuccessMessage;
import com.exam.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) throws UserAlreadyExistsException {
		user.setProfile("default.png");
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		Set<UserRole> roles = new HashSet<>();
		Role role = new Role();
		
//		role.setRoleId(45L);
//		role.setRoleName(Helper.ADMIN_USER);
		role.setRoleId(44L);
		role.setRoleName(Helper.NORMAL_USER);

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);

		roles.add(userRole);
		User savedUser = this.userService.createUser(user, roles);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}

	@GetMapping("/get/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username)
			throws UserNotFoundException {
		User user = this.userService.getUserByUsername(username);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/get/email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) throws UserNotFoundException {
		User user = this.userService.getUserByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{userId}")
	public SuccessMessage deleteUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
		return this.userService.deleteUser(userId);
	}
	
	//endpoint for update user
	@PutMapping("/update/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws UserAlreadyExistsException {
		User userToSave = this.userService.getUserByEmail(email);
		userToSave.setProfile("default.png");
		userToSave.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		Set<UserRole> roles = new HashSet<>();
		Role role = new Role();
		
//		role.setRoleId(45L);
//		role.setRoleName(Helper.ADMIN_USER);
		role.setRoleId(44L);
		role.setRoleName(Helper.NORMAL_USER);

		UserRole userRole = new UserRole();
		userRole.setUser(userToSave);
		userRole.setRole(role);

		roles.add(userRole);
		User savedUser = this.userService.updateUser(userToSave, roles);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}

}
