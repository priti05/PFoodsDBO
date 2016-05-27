package com.pFoods.dbo.restaurant.vo;

import java.util.Date;


public class CouponVO {
	
	private long id;
	private String promocode;
	private Date expiratoinDate;
	private String promoDetail;
	private DiscountOnAmountVO discountOnAmountVO;
	private DiscountOnItemVO discountOnItemVO;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPromocode() {
		return promocode;
	}
	public void setPromocode(String promocode) {
		this.promocode = promocode;
	}
	public Date getExpiratoinDate() {
		return expiratoinDate;
	}
	public void setExpiratoinDate(Date expiratoinDate) {
		this.expiratoinDate = expiratoinDate;
	}
	public String getPromoDetail() {
		return promoDetail;
	}
	public void setPromoDetail(String promoDetail) {
		this.promoDetail = promoDetail;
	}
	public DiscountOnAmountVO getDiscountOnAmountVO() {
		return discountOnAmountVO;
	}
	public void setDiscountOnAmountVO(DiscountOnAmountVO discountOnAmountVO) {
		this.discountOnAmountVO = discountOnAmountVO;
	}
	public DiscountOnItemVO getDiscountOnItemVO() {
		return discountOnItemVO;
	}
	public void setDiscountOnItemVO(DiscountOnItemVO discountOnItemVO) {
		this.discountOnItemVO = discountOnItemVO;
	}
	
	

}
