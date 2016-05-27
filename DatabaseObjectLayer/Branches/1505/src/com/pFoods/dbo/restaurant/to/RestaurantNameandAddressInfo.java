package com.pFoods.dbo.restaurant.to;

public class RestaurantNameandAddressInfo {
	
	private String name =null;
	private String town = null;
	private String state = null;
	private String zipCode = null;
	
	public RestaurantNameandAddressInfo(String name, String town, String state, String zipCode){
		this.name = name;
		this.town = town;
		this.state =state;
		this.zipCode = zipCode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	
			

}
