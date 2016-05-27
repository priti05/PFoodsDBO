package com.pFoods.dbo.restaurant;

import java.util.List;

import com.pFoods.dbo.restaurant.couponModal.Coupon;
import com.pFoods.dbo.restaurant.to.RestaurantInsertImagesRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantMenuRegistrationDBORequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantNameandAddressInfo;
import com.pFoods.dbo.restaurant.to.RestaurantProfileInformationRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantProfileInformationResponseTO;
import com.pFoods.dbo.restaurant.to.RestaurantRatingRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantRegistrationDBORequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantReserveTableRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantSearchRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantSearchResponseTO;
import com.pFoods.dbo.restaurant.to.RestaurantValidateLoginCredentialRequestTO;

public interface IRestaurantProfileDBORegistration {
	
	/**
	 * This method should be used to register restaurant Profile
	 * @return 
	 * 
	 */
	public boolean registerRestaurant(RestaurantRegistrationDBORequestTO RestaurantRegistrationDBORequestTO);
	
	/**
	 * This method should be used to register restaurant Menu Profile
	 * @return 
	 */
	public boolean registerRestaurantMenuProfile(RestaurantMenuRegistrationDBORequestTO restaurantMenuRegistrationDBORequestTO);
	
	/**
	 * 
	 * @param restaurantValidateLoginCredentialRequestTO
	 * @return
	 */
	public boolean validateLoginRestaurant(RestaurantValidateLoginCredentialRequestTO restaurantValidateLoginCredentialRequestTO);
	
	/**
	 * 
	 * @param restaurantInsertImagesRequestTO
	 */
	public void insertRestuarantProfileImages(RestaurantInsertImagesRequestTO restaurantInsertImagesRequestTO);
	
	
	public List<RestaurantNameandAddressInfo>  retrieveRestaurantNameandAddressInfo();
	
	public List<RestaurantSearchResponseTO> searchRestaurantDBO(RestaurantSearchRequestTO restaurantSearchRequestTO);
	
	public RestaurantProfileInformationResponseTO getRestaurantProfile(RestaurantProfileInformationRequestTO restaurantProfileInformationRequestTO); 
	
	public void syncRating(RestaurantRatingRequestTO restaurantRatingRequestTO);  
	
	public void reserveTable(RestaurantReserveTableRequestTO restaurantReserveTableRequestTO);
	
	public void insertCoupon(Coupon coupon, boolean isMenuItem, long menuID);
	
	
}
