package com.ecommerce.model;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserPassword")
public class UserPassword {
	@Id
	private long primaryMobileNumber;
	private String userEmailId;
	private byte[] password;
	private LocalDate passwordExpiryDate;
	public UserPassword() {
		super();
	}
	public long getPrimaryMobileNumber() {
		return primaryMobileNumber;
	}
	public void setPrimaryMobileNumber(long primaryMobileNumber) {
		this.primaryMobileNumber = primaryMobileNumber;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] password) {
		this.password = password;
	}
	public LocalDate getPasswordExpiryDate() {
		return passwordExpiryDate;
	}
	public void setPasswordExpiryDate(LocalDate passwordExpiryDate) {
		this.passwordExpiryDate = passwordExpiryDate;
	}
	@Override
	public String toString() {
		return "UserPassword [primaryMobileNumber=" + primaryMobileNumber + ", userEmailId=" + userEmailId
				+ ", password=" + Arrays.toString(password) + ", passwordExpiryDate=" + passwordExpiryDate + "]";
	}
	

}
