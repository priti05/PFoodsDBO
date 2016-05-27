package com.pFoods.dbo.order.to;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.pFoods.dbo.order.vo.AddressVO;

public class OrderRequestTO {

	private String rid;
	private String uid;
	private AddressVO addressVO;
	private boolean delievery;
	private boolean paid;
	private boolean creditPaid;
	private Date orderTimeStamp;
	private String userNote;
	private BigDecimal totalWithOutTax;
	private BigDecimal tax;
	private boolean couponUsed;
	private long couponId;
	private BigDecimal discount;
	private BigDecimal total;
	private Map<Long, Integer> menuAndQuantity;
	private long addressId;
	 
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	
	public Date getOrderTimeStamp() {
		return orderTimeStamp;
	}
	public void setOrderTimeStamp(Date orderTimeStamp) {
		this.orderTimeStamp = orderTimeStamp;
	}
	public String getUserNote() {
		return userNote;
	}
	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}
	
	public long getCouponId() {
		return couponId;
	}
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	public Map<Long, Integer> getMenuAndQuantity() {
		return menuAndQuantity;
	}
	public void setMenuAndQuantity(Map<Long, Integer> menuAndQuantity) {
		this.menuAndQuantity = menuAndQuantity;
	}
	public BigDecimal getTotalWithOutTax() {
		return totalWithOutTax;
	}
	public void setTotalWithOutTax(BigDecimal totalWithOutTax) {
		this.totalWithOutTax = totalWithOutTax;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public boolean isDelievery() {
		return delievery;
	}
	public void setDelievery(boolean delievery) {
		this.delievery = delievery;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public boolean isCreditPaid() {
		return creditPaid;
	}
	public void setCreditPaid(boolean creditPaid) {
		this.creditPaid = creditPaid;
	}
	public boolean isCouponUsed() {
		return couponUsed;
	}
	public void setCouponUsed(boolean couponUsed) {
		this.couponUsed = couponUsed;
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	
	
	
	
	
}
