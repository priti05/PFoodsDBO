package com.pFoods.dbo.userProfile.TO;

import com.pFoods.dbo.userProfile.loginModal.LoginInfo;
import com.pFoods.dbo.userProfile.profileModal.Address;
import com.pFoods.dbo.userProfile.profileModal.UserProfile;

public class RegistrationDBORequestTO {

	private LoginInfo logininfo;
	private UserProfile userProfile;
	private Address address;
	
	public LoginInfo getLogininfo() {
		return logininfo;
	}
	public void setLogininfo(LoginInfo logininfo) {
		this.logininfo = logininfo;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
		
}
