package com.pFoods.dbo.order.to;

import com.pFoods.dbo.restaurant.to.RestaurantSearchRequestTO;

public class CheckInRequestTO {

	private String uid;
	
	private RestaurantSearchRequestTO restaurantSearchRequestTO;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public RestaurantSearchRequestTO getRestaurantSearchRequestTO() {
		return restaurantSearchRequestTO;
	}

	public void setRestaurantSearchRequestTO(RestaurantSearchRequestTO restaurantSearchRequestTO) {
		this.restaurantSearchRequestTO = restaurantSearchRequestTO;
	}
	
	
	
}
