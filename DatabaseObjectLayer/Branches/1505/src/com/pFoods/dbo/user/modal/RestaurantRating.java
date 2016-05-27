package com.pFoods.dbo.user.modal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.userProfile.loginModal.LoginInfo;

@Entity
@Table (name="RESTAURANT_RATING")
public class RestaurantRating {
	
	@Id
	@Column (name="RESTAURANT_RATING_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long restaurantRatingId;
	
	@NotNull
	@Column (name="RATE")
	private int rate;
	
	@NotNull
	@Column (name="RATED_TIMESTAMP")
	private Date ratedTimeStamp;
	
	/**
	 * FK
	 */
	@NotNull
	@ManyToOne
	@JoinColumn (name="RID")
	private RestaurantLoginInfo restaurantLoginInfo;
	
	/**
	 * FK
	 */
	@NotNull
	@ManyToOne
	@JoinColumn (name="UID")
	private LoginInfo loginInfo;

	public RestaurantLoginInfo getRestaurantLoginInfo() {
		return restaurantLoginInfo;
	}

	public void setRestaurantLoginInfo(RestaurantLoginInfo restaurantLoginInfo) {
		this.restaurantLoginInfo = restaurantLoginInfo;
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	public long getRestaurantRatingId() {
		return restaurantRatingId;
	}

	public void setRestaurantRatingId(long restaurantRatingId) {
		this.restaurantRatingId = restaurantRatingId;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Date getRatedTimeStamp() {
		return ratedTimeStamp;
	}

	public void setRatedTimeStamp(Date ratedTimeStamp) {
		this.ratedTimeStamp = ratedTimeStamp;
	}
	
	public void setRatedTimeStamp() {
		this.ratedTimeStamp = new Date();
	}
	

}
