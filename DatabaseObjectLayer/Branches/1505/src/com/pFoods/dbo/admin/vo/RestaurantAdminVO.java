package com.pFoods.dbo.admin.vo;

public class RestaurantAdminVO {
	
	private String rid;
	private String name;
	private String phoneNumber;
	private String taste;
	private int numberOfOrders;
	private int numberOfEvents;
	private int numberOfReservation;
	private int rating;
	private int numberOfPeopleRated;
	private int numberofCheckIn;
	private int numberOfDeliveryOrder;
	private int numberOfPickUpOrder;
	private int numberOfCreditOrder;
	private int numberOfCashOrder;
	
	public int getNumberOfCreditOrder() {
		return numberOfCreditOrder;
	}
	public void setNumberOfCreditOrder(int numberOfCreditOrder) {
		this.numberOfCreditOrder = numberOfCreditOrder;
	}
	public int getNumberOfCashOrder() {
		return numberOfCashOrder;
	}
	public void setNumberOfCashOrder(int numberOfCashOrder) {
		this.numberOfCashOrder = numberOfCashOrder;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public int getNumberOfOrders() {
		return numberOfOrders;
	}
	public void setNumberOfOrders(int numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}
	public int getNumberOfEvents() {
		return numberOfEvents;
	}
	public void setNumberOfEvents(int numberOfEvents) {
		this.numberOfEvents = numberOfEvents;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getNumberOfPeopleRated() {
		return numberOfPeopleRated;
	}
	public void setNumberOfPeopleRated(int numberOfPeopleRated) {
		this.numberOfPeopleRated = numberOfPeopleRated;
	}
	public int getNumberofCheckIn() {
		return numberofCheckIn;
	}
	public void setNumberofCheckIn(int numberofCheckIn) {
		this.numberofCheckIn = numberofCheckIn;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getNumberOfReservation() {
		return numberOfReservation;
	}
	public void setNumberOfReservation(int numberOfReservation) {
		this.numberOfReservation = numberOfReservation;
	}
	public int getNumberOfDeliveryOrder() {
		return numberOfDeliveryOrder;
	}
	public void setNumberOfDeliveryOrder(int numberOfDeliveryOrder) {
		this.numberOfDeliveryOrder = numberOfDeliveryOrder;
	}
	public int getNumberOfPickUpOrder() {
		return numberOfPickUpOrder;
	}
	public void setNumberOfPickUpOrder(int numberOfPickUpOrder) {
		this.numberOfPickUpOrder = numberOfPickUpOrder;
	}
	
	

}
