package com.pFoods.dbo.restaurant.to;

import java.util.List;

import com.pFoods.dbo.restaurant.menuModel.Menu;

public class RestaurantMenuRegistrationDBORequestTO {
	
	private String rid;
	private List<Menu> menuList;
	
	
	
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
	
	

}
