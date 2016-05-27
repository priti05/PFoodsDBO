package com.pFoods.dbo.event.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventVO {
	
	private long eventId;
	
	private String eventName;

	private boolean admin;
	
	private AdminVO adminVO;
	
	private RestaurantVO restaurantVO;
	
	private String description;
	
	private String eventTimeNDate;
	
	private List<GuestVO> guestVOList;
	
	private List<NonUserGuestVO> nonUserGuestVOList;
	
	//Only if not an admin
	private String guestStatus;
	private long guestId;

	public String getGuestStatus() {
		return guestStatus;
	}

	public void setGuestStatus(String guestStatus) {
		this.guestStatus = guestStatus;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public AdminVO getAdminVO() {
		return adminVO;
	}

	public void setAdminVO(AdminVO adminVO) {
		this.adminVO = adminVO;
	}

	public RestaurantVO getRestaurantVO() {
		return restaurantVO;
	}

	public void setRestaurantVO(RestaurantVO restaurantVO) {
		this.restaurantVO = restaurantVO;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEventTimeNDate() {
		return eventTimeNDate;
	}

	public void setEventTimeNDate(Date eventTimeNDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd,HH:mm:ss zzz yyyy");
		this.eventTimeNDate = formatter.format(eventTimeNDate);
	}

	public List<GuestVO> getGuestVOList() {
		return guestVOList;
	}

	public void setGuestVOList(List<GuestVO> guestVOList) {
		this.guestVOList = guestVOList;
	}

	public List<NonUserGuestVO> getNonUserGuestVOList() {
		return nonUserGuestVOList;
	}

	public void setNonUserGuestVOList(List<NonUserGuestVO> nonUserGuestVOList) {
		this.nonUserGuestVOList = nonUserGuestVOList;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getGuestId() {
		return guestId;
	}

	public void setGuestId(long guestId) {
		this.guestId = guestId;
	}
	
	
	
}
