package com.pFoods.dbo.event.to;

import java.util.Date;
import java.util.List;

public class CreateEventRequestTO {
	
	private String uid;
	private String rid;
	private String eventName;
	private String eventDescription; //optional
	private Date eventTimeNDate;
	private Date eventCreatedTimeStamp;
	private String identifier;
	private List<String> phoneNumber;
	private List<String> invitedGuestList;
	
	public List<String> getInvitedGuestList() {
		return invitedGuestList;
	}
	public void setInvitedGuestList(List<String> invitedGuestList) {
		this.invitedGuestList = invitedGuestList;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
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
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public List<String> getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(List<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

}
