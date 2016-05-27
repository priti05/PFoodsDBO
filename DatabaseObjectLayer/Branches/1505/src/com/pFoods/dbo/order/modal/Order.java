package com.pFoods.dbo.order.modal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.userProfile.loginModal.LoginInfo;
import com.pFoods.dbo.userProfile.profileModal.Address;


@Entity
@Table (name="ORDER_MAIN")
public class Order {
	
	@Id
	@Column (name="ORDER_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long orderId;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name="UID")
	private LoginInfo loginInfo;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name="RID")
	private RestaurantLoginInfo restaurantLoginInfo;
	
	@ManyToOne
	@JoinColumn (name="ADDRESS_TO_DELIEVER")
	private Address address;
		
	@NotNull
	@Column (name="DELIEVER_PICKUP")
	@Enumerated (EnumType.STRING)
	private DelieverPickUp delieverPickUp;
	
	@NotNull
	@Column (name="STATUS")
	@Enumerated (EnumType.STRING)
	private Status status;
	
	@Lob
	@Column (name="RESTAURANT_NOTE")
	private String restaurantNote;
	
	@NotNull
	@Column (name="PAID")
	@Enumerated (EnumType.STRING)
	private Paid paid;
	
	@NotNull
	@Column (name="PAYMENT_TYPE")
	@Enumerated (EnumType.STRING)
	private PaymentType paymentType;
	
	@NotNull
	@Column (name="ORDER_TIME")
	private Date orderTimeStamp;
	
	@NotNull
	@OneToOne
	@JoinColumn (name="ORDER_DETAIL_ID")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private OrderDetail orderDetail;
		
	public enum DelieverPickUp{
		d,
		p,
	}
	
	public enum Status{
		pending,
		rejected,
		accepted,
		cooking,
		onTheWay,
		deliver,
		closed
	}
	
	public enum Paid{
		paid,
		notPaid
	}
	
	public enum PaymentType{
		cash,
		credit
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public DelieverPickUp getDelieverPickUp() {
		return delieverPickUp;
	}

	public void setDelieverPickUp(DelieverPickUp delieverPickUp) {
		this.delieverPickUp = delieverPickUp;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getRestaurantNote() {
		return restaurantNote;
	}

	public void setRestaurantNote(String restaurantNote) {
		this.restaurantNote = restaurantNote;
	}

	public Paid getPaid() {
		return paid;
	}

	public void setPaid(Paid paid) {
		this.paid = paid;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Date getOrderTimeStamp() {
		return orderTimeStamp;
	}

	public void setOrderTimeStamp(Date orderTimeStamp) {
		this.orderTimeStamp = orderTimeStamp;
	}
	
	public void setOrderTimeStamp() {
		this.orderTimeStamp = new Date();
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	
	

}
