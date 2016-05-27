package com.pFoods.dbo.restaurant.couponModal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class DiscountOnAmount {

	
	@NotNull
	@Column (name="AMOUNT_DISCOUNT_ON")
	private String amountDiscountOn;
	
	@NotNull
	@Column (name="PERCENT_OF_DISCOUNT")
	private String percentOfDiscount;

	public String getAmountDiscountOn() {
		return amountDiscountOn;
	}

	public void setAmountDiscountOn(String amountDiscountOn) {
		this.amountDiscountOn = amountDiscountOn;
	}

	public String getPercentOfDiscount() {
		return percentOfDiscount;
	}

	public void setPercentOfDiscount(String percentOfDiscount) {
		this.percentOfDiscount = percentOfDiscount;
	}
	
	
}
