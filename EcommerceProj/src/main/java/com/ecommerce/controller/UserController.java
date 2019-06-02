package com.ecommerce.controller;

import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.gist.UpdateUserProfile;
import com.ecommerce.gist.UserProfile;
import com.ecommerce.model.User;
import com.ecommerce.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello Abhijit...";
	}
	
	
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public List<User> findAllUser() {
		List<User> users = userService.findAllUser();
		return users;
	}
	@RequestMapping(value="/user/{userId}", method = RequestMethod.GET)
	public User findOneUser(@PathVariable long userId) {
		return userService.findUser(userId);
	}
	
	@RequestMapping(value="/userprofile", method = RequestMethod.POST)
	public String addUserProfile(@RequestBody UserProfile userProfile) throws GeneralSecurityException {
		userService.profileUser(userProfile);
		String str = "Succesfully added User:"+userProfile.getUserName();
		return str;
	}
	@RequestMapping(value="/user/{userId}", method = RequestMethod.PUT)
	public String updateUser(@PathVariable long userId,@RequestBody UpdateUserProfile updateUserProfile) {
		return userService.updateUser(userId,updateUserProfile);
	}
	@RequestMapping(value="/user/{userId}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable long userId) {
		return userService.deleteUser(userId);
	}
	
}
