package com.pFoods.dbo.restaurant.loginModal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.pFoods.dbo.event.modal.Event;
import com.pFoods.dbo.order.modal.Order;
import com.pFoods.dbo.reserveTable.modal.ReserveTable;
import com.pFoods.dbo.restaurant.couponModal.Coupon;
import com.pFoods.dbo.restaurant.imageModel.Images;
import com.pFoods.dbo.restaurant.menuModel.Menu;
import com.pFoods.dbo.restaurant.profileModal.RestaurantAddress;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile;
import com.pFoods.dbo.user.modal.CheckIn;
import com.pFoods.dbo.user.modal.RestaurantRating;

@Entity
@Table (name="RESTAURANT_LOGIN_INFO")
public class RestaurantLoginInfo {
	
	/**
	 * PK
	 */
	@Id
	@Column (name="RID")
	private String userId;
	
	@NotNull
	@Column (name="RPASSWORD")
	private String password;
	
	@OneToMany (mappedBy="restaurantLoginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Menu> menuList; 
	
	@OneToMany (mappedBy="restaurantLoginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Images> imageList;
	
	@OneToMany (mappedBy="restaurantLoginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Coupon> coupon;
	
	@OneToMany (mappedBy="restaurantLoginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<RestaurantRating> rastaurantRating;
	
	@OneToMany (mappedBy="restaurantLoginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<ReserveTable> reserveTable;
	
	@OneToMany (mappedBy="restaurantLoginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Event> event;
	
	@OneToMany (mappedBy="restaurantLoginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Order> order;
	
	@OneToMany (mappedBy="restaurantLoginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<CheckIn> checkIn;
	
	@OneToOne (mappedBy="restaurantLoginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private RestaurantAddress restaurantAddress;
	
	@OneToOne (mappedBy="restaurantLoginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private RestaurantProfile restaurantProfile;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	public List<Images> getImageList() {
		return imageList;
	}
	public void setImageList(List<Images> imageList) {
		this.imageList = imageList;
	}
	
	public List<Coupon> getCoupon() {
		return coupon;
	}
	public void setCoupon(List<Coupon> coupon) {
		this.coupon = coupon;
	}
	
	public List<RestaurantRating> getRastaurantRating() {
		return rastaurantRating;
	}
	public void setRastaurantRating(List<RestaurantRating> rastaurantRating) {
		this.rastaurantRating = rastaurantRating;
	}
	public List<ReserveTable> getReserveTable() {
		return reserveTable;
	}
	public void setReserveTable(List<ReserveTable> reserveTable) {
		this.reserveTable = reserveTable;
	}
	public List<Event> getEvent() {
		return event;
	}
	public void setEvent(List<Event> event) {
		this.event = event;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	public List<CheckIn> getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(List<CheckIn> checkIn) {
		this.checkIn = checkIn;
	}
	public RestaurantAddress getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(RestaurantAddress restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public RestaurantProfile getRestaurantProfile() {
		return restaurantProfile;
	}
	public void setRestaurantProfile(RestaurantProfile restaurantProfile) {
		this.restaurantProfile = restaurantProfile;
	}
	
	
}
