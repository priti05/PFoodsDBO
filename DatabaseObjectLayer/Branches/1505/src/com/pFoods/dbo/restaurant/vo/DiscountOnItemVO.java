package com.pFoods.dbo.restaurant.vo;


public class DiscountOnItemVO {

	private long menuId;
	private int numberOfItemRequire;
	private int numberOfFreeItem;
	
	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

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
