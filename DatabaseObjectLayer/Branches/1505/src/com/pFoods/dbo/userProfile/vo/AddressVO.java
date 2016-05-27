package com.pFoods.dbo.userProfile.vo;

public class AddressVO {
	
	private long addressId;
	private String streetNumer;	
	private String apt_Numer;
	private String street;	
	private String town;	
	private String state;
	private String zip;
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getStreetNumer() {
		return streetNumer;
	}
	public void setStreetNumer(String streetNumer) {
		this.streetNumer = streetNumer;
	}
	public String getApt_Numer() {
		return apt_Numer;
	}
	public void setApt_Numer(String apt_Numer) {
		this.apt_Numer = apt_Numer;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
}
