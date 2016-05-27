package com.pFoods.dbo.admin.to;

import java.util.List;

import com.pFoods.dbo.admin.vo.RestaurantAdminVO;

public class AdminResponseTo {
	
	List<RestaurantAdminVO> restaurantAdminVOList;

	public List<RestaurantAdminVO> getRestaurantAdminVOList() {
		return restaurantAdminVOList;
	}

	public void setRestaurantAdminVOList(List<RestaurantAdminVO> restaurantAdminVOList) {
		this.restaurantAdminVOList = restaurantAdminVOList;
	}
	
	

}
