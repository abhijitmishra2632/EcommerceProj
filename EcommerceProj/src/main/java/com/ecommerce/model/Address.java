package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "UserAddress")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long addressId;
	private String streetAddress; 
    private String landmark;
    private String city;  
    private String zipCode;  
    private String country;
    private String tag;
    @JsonBackReference
    @ManyToOne(optional = false ,fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address() {
		super();
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
    
}
