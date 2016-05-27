package com.pFoods.dbo.restaurant.to;

import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.restaurant.profileModal.RestaurantAddress;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile;

public class RestaurantRegistrationDBORequestTO {

	private RestaurantLoginInfo restaurantLoginInfo;
	
	private RestaurantProfile restaurantProfile;
	
	private RestaurantAddress restaurantAddress;
	
	private int tasteId;
	
	public int getTasteId() {
		return tasteId;
	}

	public void setTasteId(int tasteId) {
		this.tasteId = tasteId;
	}

	public RestaurantLoginInfo getRestaurantLoginInfo() {
		return restaurantLoginInfo;
	}

	public void setRestaurantLoginInfo(RestaurantLoginInfo restaurantLoginInfo) {
		this.restaurantLoginInfo = restaurantLoginInfo;
	}

	public RestaurantProfile getRestaurantProfile() {
		return restaurantProfile;
	}

	public void setRestaurantProfile(RestaurantProfile restaurantProfile) {
		this.restaurantProfile = restaurantProfile;
	}

	public RestaurantAddress getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(RestaurantAddress restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	
	
	
}
