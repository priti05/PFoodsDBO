package com.pFoods.dbo.userProfile.TO;

import com.pFoods.dbo.userProfile.profileModal.Address;

public class UpdateAddressDBOTO {
	
	private String userID;
	private Address address;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}
