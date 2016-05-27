package com.pFoods.dbo.order.vo;

public class AddressVO {
	
	private String street_Numer;	
	private String apt_Numer;
	private String street;	
	private String town;	
	private String state;
	private String zip;
	
	public String getStreet_Numer() {
		return street_Numer;
	}
	public void setStreet_Numer(String street_Numer) {
		this.street_Numer = street_Numer;
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
