package com.pFoods.dbo.userProfile.loginModal;

import java.util.Collection;
import java.util.Date;
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
import com.pFoods.dbo.event.modal.GuestList;
import com.pFoods.dbo.order.modal.Order;
import com.pFoods.dbo.reserveTable.modal.ReserveTable;
import com.pFoods.dbo.user.modal.CheckIn;
import com.pFoods.dbo.user.modal.RestaurantRating;
import com.pFoods.dbo.userProfile.profileModal.Address;
import com.pFoods.dbo.userProfile.profileModal.UserProfile;


@Entity
@Table (name="LOGIN_INFO")
public class LoginInfo {
	
	@Id
	@Column (name="USER_ID")
	private String userId;
	
	@NotNull
	@Column (name="PASSWORD")
	private String password;
	
	@Column (name="SECURITY_CODE")
	private long securityCode;
	 
	@Column (name="WRONG_LOGIN_ATTEMPT")
	private int worngAttempToLogin;
	
	@Column (name="WRONG_SECURITY_CODE_ATTEMPT")
	private int worngAttemptToEnterSecCode;
	
	@Column (name="SECURITY_CODE_LOCK")
	private boolean secCodeLock;
	
	@Column (name="SECURITY_CODE_LOCK_DATE")
	private Date secCodeLockDate;
	
	@Column (name="SOFT_LOCK")
	private boolean softLock;
	
	@Column (name="SOFT_LOCK_DATE")
	private Date softLockDate;
	
	@Column (name="HARD_LOCK")
	private boolean hardLock;
	
	@Column (name="HARD_LOCK_DATE")
	private Date hardLockDate;
	
	@OneToOne (mappedBy="loginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private UserProfile userProfile;
		
	@OneToMany (mappedBy="loginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Collection<Address> address;
	
	@OneToMany (mappedBy="loginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<RestaurantRating> rastaurantRating;
	
	@OneToMany (mappedBy="loginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<CheckIn> checkIn;
	
	@OneToMany (mappedBy="loginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<ReserveTable> reserveTable;
	
	@OneToMany (mappedBy="loginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Event> event;
	
	@OneToMany (mappedBy="loginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<GuestList> guestList;
	
	@OneToMany (mappedBy="loginInfo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Order> order;
	
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
	
	public Collection<Address> getAddress() {
		return address;
	}
	public void setAddress(Collection<Address> address) {
		this.address = address;
	}
	
	public List<RestaurantRating> getRastaurantRating() {
		return rastaurantRating;
	}
	public void setRastaurantRating(List<RestaurantRating> rastaurantRating) {
		this.rastaurantRating = rastaurantRating;
	}
	public List<CheckIn> getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(List<CheckIn> checkIn) {
		this.checkIn = checkIn;
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
	public List<GuestList> getGuestList() {
		return guestList;
	}
	public void setGuestList(List<GuestList> guestList) {
		this.guestList = guestList;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	public long getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(long securityCode) {
		this.securityCode = securityCode;
	}
	public int getWorngAttempToLogin() {
		return worngAttempToLogin;
	}
	public void setWorngAttempToLogin(int worngAttempToLogin) {
		this.worngAttempToLogin = worngAttempToLogin;
	}
	public int getWorngAttemptToEnterSecCode() {
		return worngAttemptToEnterSecCode;
	}
	public void setWorngAttemptToEnterSecCode(int worngAttemptToEnterSecCode) {
		this.worngAttemptToEnterSecCode = worngAttemptToEnterSecCode;
	}
	public boolean isSoftLock() {
		return softLock;
	}
	public void setSoftLock(boolean softLock) {
		this.softLock = softLock;
	}
	public Date getSoftLockDate() {
		return softLockDate;
	}
	public void setSoftLockDate(Date softLockDate) {
		this.softLockDate = softLockDate;
	}
	public boolean isHardLock() {
		return hardLock;
	}
	public void setHardLock(boolean hardLock) {
		this.hardLock = hardLock;
	}
	public Date getHardLockDate() {
		return hardLockDate;
	}
	public void setHardLockDate(Date hardLockDate) {
		this.hardLockDate = hardLockDate;
	}
	public boolean isSecCodeLock() {
		return secCodeLock;
	}
	public void setSecCodeLock(boolean secCodeLock) {
		this.secCodeLock = secCodeLock;
	}
	public Date getSecCodeLockDate() {
		return secCodeLockDate;
	}
	public void setSecCodeLockDate(Date secCodeLockDate) {
		this.secCodeLockDate = secCodeLockDate;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	
	
}
