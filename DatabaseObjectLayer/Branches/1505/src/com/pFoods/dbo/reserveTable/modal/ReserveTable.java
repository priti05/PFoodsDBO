package com.pFoods.dbo.reserveTable.modal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.userProfile.loginModal.LoginInfo;

@Entity
@Table (name="RESERVATION")
public class ReserveTable {
	
	@Id
	@Column (name="RESERVE_TABLE_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long reserveTableId;
	
	/**
	 * FK
	 */
	@NotNull
	@ManyToOne
	@JoinColumn (name="UID")
	private LoginInfo loginInfo;
	
	/**
	 * FK
	 */
	@NotNull
	@ManyToOne
	@JoinColumn (name="RID")
	private RestaurantLoginInfo restaurantLoginInfo;
	
	/**
	 * Date reservation has been made for (need to pass by user)
	 */
	@NotNull
	@Column (name="DATE_TIME_OF_RESERVATION")
	private Date dateTimeOfReservation;
	
	@NotNull
	@Column (name="PERSON")
	private int numberOfPerson;
	
	@NotNull
	@Column (name="STATUS")
	private Status status;
	
	/**
	 * Date reservation has been made
	 */
	@NotNull
	@Column (name="RESERVE_TABLE_TIMESTAMP")
	private Date reserveTableTimeStamp;
	
	public enum Status{
		pending,
		rejected,
		accepted,
		closed
	}

	public long getReserveTableId() {
		return reserveTableId;
	}

	public void setReserveTableId(long reserveTableId) {
		this.reserveTableId = reserveTableId;
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

	public Date getDateTimeOfReservation() {
		return dateTimeOfReservation;
	}

	public void setDateTimeOfReservation(Date dateTimeOfReservation) {
		this.dateTimeOfReservation = dateTimeOfReservation;
	}

	public int getNumberOfPerson() {
		return numberOfPerson;
	}

	public void setNumberOfPerson(int numberOfPerson) {
		this.numberOfPerson = numberOfPerson;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getReserveTableTimeStamp() {
		return reserveTableTimeStamp;
	}

	public void setReserveTableTimeStamp(Date reserveTableTimeStamp) {
		this.reserveTableTimeStamp = reserveTableTimeStamp;
	}
	
	public void setReserveTableTimeStamp() {
		reserveTableTimeStamp = new Date();
	}
	
	
}
