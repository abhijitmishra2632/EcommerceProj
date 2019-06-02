package com.ecommerce.gist;

import java.util.HashSet;
import java.util.Set;

import com.ecommerce.model.Address;

public class UpdateUserProfile {
	private String userName;
	private String secondaryMobileNumber;
	private String userEmailId;
	private String userPassword;
	private String dob;
	private Set<Address> userAddress = new HashSet<Address>();
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSecondaryMobileNumber() {
		return secondaryMobileNumber;
	}
	public void setSecondaryMobileNumber(String secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Set<Address> getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(Set<Address> userAddress) {
		this.userAddress = userAddress;
	}
	

}
