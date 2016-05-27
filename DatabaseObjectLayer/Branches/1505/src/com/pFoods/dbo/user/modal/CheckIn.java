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
@Table (name="CHECK_IN")
public class CheckIn {

	@Id
	@Column (name="CHECK_IN_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long AutoId;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name="UID")
	private LoginInfo loginInfo;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name="RID")
	private RestaurantLoginInfo restaurantLoginInfo;
	
	@NotNull
	@Column (name="CHECK_IN_TIMESTAMP")
	private Date checkInTimeStamp;

	public long getAutoId() {
		return AutoId;
	}

	public void setAutoId(long autoId) {
		AutoId = autoId;
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public Date getCheckInTimeStamp() {
		return checkInTimeStamp;
	}

	public void setCheckInTimeStamp(Date checkInTimeStamp) {
		this.checkInTimeStamp = checkInTimeStamp;
	}
	
	public void setCheckInTimeStamp() {
		this.checkInTimeStamp = new Date();
	}

	public RestaurantLoginInfo getRestaurantLoginInfo() {
		return restaurantLoginInfo;
	}

	public void setRestaurantLoginInfo(RestaurantLoginInfo restaurantLoginInfo) {
		this.restaurantLoginInfo = restaurantLoginInfo;
	}
	
	
}
