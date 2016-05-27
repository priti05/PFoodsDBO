package com.pFoods.dbo.event.modal;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.userProfile.loginModal.LoginInfo;

@Entity
@Table (name="EVENT")
public class Event {
	
	@Id
	@Column (name="EVENT_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long eventId;
	
	/*
	 * Admin
	 */
	@NotNull
	@ManyToOne
	@JoinColumn (name="UID")
	private LoginInfo loginInfo;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name="RID")
	private RestaurantLoginInfo restaurantLoginInfo;
	
	@NotNull
	@Column (name="NAME")
	private String eventName;
	
	@Lob
	@Column (name="DESCIRPTION")
	private String eventDescription;
	
	@NotNull
	@Column (name="DATE_TIME")
	private Date eventTimeNDate;
	
	@NotNull
	@Column (name="CREATTION_TIMESTAMP")
	private Date eventCreatedTimeStamp;
	
	@OneToMany (mappedBy="event")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<GuestList> guestList;
	
	@OneToMany (mappedBy="event")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<InvitedNonUser> invitedNonUser;

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public RestaurantLoginInfo getRestaurantLoginInfo() {
		return restaurantLoginInfo;
	}

	public void setRestaurantLoginInfo(RestaurantLoginInfo restaurantLoginInfo) {
		this.restaurantLoginInfo = restaurantLoginInfo;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Date getEventTimeNDate() {
		return eventTimeNDate;
	}

	public void setEventTimeNDate(Date eventTimeNDate) {
		this.eventTimeNDate = eventTimeNDate;
	}

	public Date getEventCreatedTimeStamp() {
		return eventCreatedTimeStamp;
	}

	public void setEventCreatedTimeStamp(Date eventCreatedTimeStamp) {
		this.eventCreatedTimeStamp = eventCreatedTimeStamp;
	}
	
	public void setEventCreatedTimeStamp() {
		this.eventCreatedTimeStamp = new Date();
	}

	public List<GuestList> getGuestList() {
		return guestList;
	}

	public void setGuestList(List<GuestList> guestList) {
		this.guestList = guestList;
	}

	public List<InvitedNonUser> getInvitedNonUser() {
		return invitedNonUser;
	}

	public void setInvitedNonUser(List<InvitedNonUser> invitedNonUser) {
		this.invitedNonUser = invitedNonUser;
	}
	
	
	
	
	

}
