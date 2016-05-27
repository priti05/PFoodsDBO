package com.pFoods.dbo.restaurant.profileModal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;

@Entity
@Table (name="RESTAURANT_ADDRESS")
public class RestaurantAddress {

	@Id
	@Column (name="AUTO_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long autoId;
	
	@NotNull
	@Column (name="R_STREET_NUMBER")
	private String streetNumber;
	
	@NotNull
	@Column (name="R_STREET")
	private String street;
	
	@Column (name="R_ADDRESS1")
	private String address1;
	
	@Column (name="R_ADDRESS2")
	private String address2;
	
	@NotNull
	@Column (name="R_TOWN")
	private String town;
	
	@NotNull
	@Column (name="R_STATE")
	private String state;
	
	@NotNull
	@Column (name="R_ZIP")
	private String zip;
	
	@NotNull
	@OneToOne
	@JoinColumn (name="RID")
	private RestaurantLoginInfo restaurantLoginInfo;
	
	public long getAutoId() {
		return autoId;
	}
	public void setAutoId(long autoId) {
		this.autoId = autoId;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public RestaurantLoginInfo getRestaurantLoginInfo() {
		return restaurantLoginInfo;
	}
	public void setRestaurantLoginInfo(RestaurantLoginInfo restaurantLoginInfo) {
		this.restaurantLoginInfo = restaurantLoginInfo;
	}
	
	
	
	
	
}
