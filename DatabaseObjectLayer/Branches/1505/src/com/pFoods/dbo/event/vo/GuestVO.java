package com.pFoods.dbo.event.vo;

public class GuestVO {
	
	private String name;
	private String phoneNumber;
	private boolean joining;
	private boolean notComing;
	private boolean pending;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isJoining() {
		return joining;
	}
	public void setJoining(boolean joining) {
		this.joining = joining;
	}
	public boolean isNotComing() {
		return notComing;
	}
	public void setNotComing(boolean notComing) {
		this.notComing = notComing;
	}
	public boolean isPending() {
		return pending;
	}
	public void setPending(boolean pending) {
		this.pending = pending;
	}
	
	

}
