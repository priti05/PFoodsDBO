package com.pFoods.dbo.event.to;

public class UpdateStatusRequestTO {

	private String status;
	private String uid;
	private long eventID;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public long getEventID() {
		return eventID;
	}

	public void setEventID(long eventID) {
		this.eventID = eventID;
	}
	
	
	
	
	
}
