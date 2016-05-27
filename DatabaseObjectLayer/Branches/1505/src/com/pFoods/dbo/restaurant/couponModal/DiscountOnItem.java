package com.pFoods.dbo.restaurant.couponModal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;


@Embeddable
public class DiscountOnItem {
	
	
	@NotNull
	@Column (name="NUMBER_OF_ITEM_REQUIRE")
	private int numberOfItemRequire;
	
	@NotNull
	@Column (name="NUMBER_OF_FREE_ITEM")
	private int numberOfFreeItem;


	public int getNumberOfItemRequire() {
		return numberOfItemRequire;
	}

	public void setNumberOfItemRequire(int numberOfItemRequire) {
		this.numberOfItemRequire = numberOfItemRequire;
	}

	public int getNumberOfFreeItem() {
		return numberOfFreeItem;
	}

	public void setNumberOfFreeItem(int numberOfFreeItem) {
		this.numberOfFreeItem = numberOfFreeItem;
	}
	
	
	

}
