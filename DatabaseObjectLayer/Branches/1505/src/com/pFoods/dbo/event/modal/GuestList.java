package com.pFoods.dbo.event.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.pFoods.dbo.userProfile.loginModal.LoginInfo;

@Entity
@Table (name="GUEST_LIST")
public class GuestList {
	
	@Id
	@Column (name="GUEST_LIST_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long guestListId;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name="EVENT_ID")
	private Event event;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name="UID")
	private LoginInfo loginInfo;
	
	@NotNull
	@Column (name="STATUS")
	@Enumerated (EnumType.STRING)
	private Status status;
	
	public enum Status {
		pending,
		accepted,
		rejected
	}

	public long getGuestListId() {
		return guestListId;
	}

	public void setGuestListId(long guestListId) {
		this.guestListId = guestListId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
