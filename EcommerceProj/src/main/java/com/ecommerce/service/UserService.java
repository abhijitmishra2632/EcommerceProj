package com.ecommerce.service;

import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.constants.ConstantDetails;
import com.ecommerce.gist.UpdateUserProfile;
import com.ecommerce.gist.UserProfile;
import com.ecommerce.model.Address;
import com.ecommerce.model.User;
import com.ecommerce.model.UserPassword;
import com.ecommerce.repository.PasswordRepository;
import com.ecommerce.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordRepository passwordRepository;
	/*
	 * public void addDummyUser() { Address address = new Address();
	 * address.setStreetAddress("Vijayanagaram"); address.setCity("Chennai");
	 * address.setZipCode("600100"); address.setTag("home"); Address office = new
	 * Address(); office.setStreetAddress("Shollinganallur");
	 * office.setCity("Chennai"); office.setZipCode("600119");
	 * office.setTag("office"); User user = new User(); address.setUser(user);
	 * office.setUser(user); user.setUserName("Abhijit Mishra"); Set<Address>
	 * addresses = new HashSet<Address>(); addresses.add(address);
	 * addresses.add(office); user.setUserAddress(addresses);
	 * userRepository.save(user); }
	 */

	public void addUser(User user) {
		userRepository.save(user);
	}

	public List<User> findAllUser() {
		List<User> users = userRepository.findAll();
		return users;
	}

	public User findUser(long userId) {
		// TODO Auto-generated method stub
		User user = new User();
		Optional<User> userDB = userRepository.findById(userId);
		if (userDB != null) {
			user = userDB.get();
		}
		return user;

	}

	public void profileUser(UserProfile userProfile) throws GeneralSecurityException {
		if (userProfile != null) {
			createUserInDB(userProfile);
			createPasswordINDB(userProfile);
			// long userMobileNumber = Long.parseLong(userProfile.getUserMobileNumber());
			// printPassword(userMobileNumber);
		}

	}

	/*
	 * private void printPassword(long userMobileNumber) throws
	 * GeneralSecurityException { UserPassword userPassword =
	 * passwordRepository.getOne(userMobileNumber); byte[] password =
	 * userPassword.getPassword(); String emailId = userPassword.getUserEmailId();
	 * String pass = PasswordUtil.decryptUserPassword(password, emailId);
	 * System.out.println("User Password is: " + pass + " and emailId is: " +
	 * emailId); }
	 */

	private void createPasswordINDB(UserProfile userProfile) {
		String password = userProfile.getUserPassword();
		UserPassword userPassword = new UserPassword();
		/*
		 * try { //UserPassword userPassword =
		 * PasswordUtil.encryptUserPassword(password, name);
		 * 
		 * } catch (GeneralSecurityException e) { // TODO Auto-generated catch block
		 * System.out.println(e); }
		 */
		userPassword.setPassword(password);
		long mobile = Long.parseLong(userProfile.getUserMobileNumber());
		userPassword.setPrimaryMobileNumber(mobile);
		userPassword.setUserEmailId(userProfile.getUserEmailId());
		userPassword.setPasswordExpiryDate(LocalDate.now().plusDays(ConstantDetails.passwordExpiryPeriod));

		passwordRepository.save(userPassword);

	}

	private void createUserInDB(UserProfile userProfile) {
		User user = new User();
		user.setUserName(userProfile.getUserName());
		Long mobile = Long.parseLong(userProfile.getUserMobileNumber());
		user.setPrimaryMobileNumber(mobile);
		if (null != userProfile.getSecondaryMobileNumber() && !userProfile.getSecondaryMobileNumber().isEmpty())
			user.setSecondaryMobileNumber(Long.parseLong(userProfile.getSecondaryMobileNumber()));
		user.setUserEmailId(userProfile.getUserEmailId());
		String dob = userProfile.getDob();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ConstantDetails.dateFormatter);
		// convert String to LocalDate
		LocalDate localDate = LocalDate.parse(dob, formatter);
		user.setDob(localDate);
		Set<Address> userAddress = new HashSet<Address>();
		userAddress.addAll(userProfile.getUserAddress());
		for (Address addr : userAddress) {
			addr.setUser(user);
		}
		user.setUserAddress(userAddress);
		userRepository.save(user);
	}

	public String updateUser(long userId, UpdateUserProfile updateUserProfile) {
		User user = userRepository.findById(userId).get();
		UserPassword userPassword = passwordRepository.findById(user.getPrimaryMobileNumber()).get();
		String str = updateUserImpl(user, userPassword, updateUserProfile);
		return str;
	}

	private String updateUserImpl(User user, UserPassword userPassword, UpdateUserProfile updateUserProfile) {
		if (updateUserProfile.getSecondaryMobileNumber() != null
				&& !updateUserProfile.getSecondaryMobileNumber().isEmpty())
			user.setSecondaryMobileNumber(Long.parseLong(updateUserProfile.getSecondaryMobileNumber()));
		if (updateUserProfile.getUserName() != null && !updateUserProfile.getUserName().isEmpty())
			user.setUserName(updateUserProfile.getUserName());
		if (updateUserProfile.getUserEmailId() != null && !updateUserProfile.getUserEmailId().isEmpty())
			user.setUserEmailId(updateUserProfile.getUserEmailId());
		if (updateUserProfile.getDob() != null && !updateUserProfile.getDob().isEmpty()) {
			String dob = updateUserProfile.getDob();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ConstantDetails.dateFormatter);
			LocalDate localDate = LocalDate.parse(dob, formatter);
			user.setDob(localDate);
		}
		if (updateUserProfile.getUserAddress() != null && !updateUserProfile.getUserAddress().isEmpty()) {
			List<Address> addrList = new ArrayList<>();
			addrList.addAll(user.getUserAddress());
			addrList.addAll(updateUserProfile.getUserAddress());
			Map<String, Address> maps = new HashMap<String, Address>();
			for (Address addr : addrList) {
				maps.put(addr.getTag(), addr);
			}
			List<Address> targetList = new ArrayList<>(maps.values());
			Set<Address> updatedaddresses = new HashSet<Address>();
			updatedaddresses.addAll(targetList);
			user.setUserAddress(updatedaddresses);
			for (Address addr : targetList) {
				addr.setUser(user);
			}

		}

		if (updateUserProfile.getUserPassword() != null && !updateUserProfile.getUserPassword().isEmpty()) {
			userPassword.setPassword(updateUserProfile.getUserPassword());
			userPassword.setPasswordExpiryDate(LocalDate.now().plusDays(ConstantDetails.passwordExpiryPeriod));
		}
		userRepository.save(user);
		passwordRepository.save(userPassword);
		return "Success";
	}

	public String deleteUser(long userId) {
		User user = userRepository.findById(userId).get();
		UserPassword userPassword = passwordRepository.findById(user.getPrimaryMobileNumber()).get();
		userRepository.delete(user);
		passwordRepository.delete(userPassword);
		return "Success";
	}

}
