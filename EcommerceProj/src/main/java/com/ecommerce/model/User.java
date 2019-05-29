package com.ecommerce.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	private String userName;
	
	private long primaryMobileNumber;
	private long secondaryMobileNumber;
	private String userEmailId;
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	private Set<Address> userAddress = new HashSet<Address>();
	private LocalDate dob;
	public User() {
		super();
	}

	public long getPrimaryMobileNumber() {
		return primaryMobileNumber;
	}

	public void setPrimaryMobileNumber(long primaryMobileNumber) {
		this.primaryMobileNumber = primaryMobileNumber;
	}

	public long getSecondaryMobileNumber() {
		return secondaryMobileNumber;
	}

	public void setSecondaryMobileNumber(long secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JsonManagedReference
	public Set<Address> getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Set<Address> userAddress) {
		this.userAddress = userAddress;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
}
