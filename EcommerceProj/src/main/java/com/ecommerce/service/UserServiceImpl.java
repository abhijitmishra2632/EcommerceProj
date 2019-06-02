package com.ecommerce.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;


import com.ecommerce.constants.ConstantDetails;
import com.ecommerce.gist.UpdateUserProfile;
import com.ecommerce.model.Address;
import com.ecommerce.model.User;
import com.ecommerce.model.UserPassword;

public class UserServiceImpl {
	public static void updateUser(User user, UserPassword userPassword, UpdateUserProfile updateUserProfile) {
		if(updateUserProfile.getSecondaryMobileNumber()!=null && !updateUserProfile.getSecondaryMobileNumber().isEmpty())
			user.setSecondaryMobileNumber(Long.parseLong(updateUserProfile.getSecondaryMobileNumber()));
		user.setUserName(updateUserProfile.getUserName());
		user.setUserEmailId(updateUserProfile.getUserEmailId());
		String dob = updateUserProfile.getDob();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ConstantDetails.dateFormatter);
		LocalDate localDate = LocalDate.parse(dob , formatter);
		user.setDob(localDate);
		Set<Address> addresses = user.getUserAddress();
		Set<Address> updatedaddresses = updateUserProfile.getUserAddress();
		addresses.addAll(updatedaddresses);
		userPassword.setPassword(updateUserProfile.getUserPassword());
		userPassword.setPasswordExpiryDate(LocalDate.now().plusDays(ConstantDetails.passwordExpiryPeriod));
	}

}
