package com.pFoods.dbo.restaurant.to;

import java.util.List;

import com.pFoods.dbo.restaurant.imageModel.Images;

public class RestaurantInsertImagesRequestTO {

	private String rid;
	private List<Images> imageList;
	
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public List<Images> getImageList() {
		return imageList;
	}
	public void setImageList(List<Images> imageList) {
		this.imageList = imageList;
	}
	
	
	
}
