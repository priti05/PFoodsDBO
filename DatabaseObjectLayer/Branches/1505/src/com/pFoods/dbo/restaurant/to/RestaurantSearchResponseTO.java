package com.pFoods.dbo.restaurant.to;

public class RestaurantSearchResponseTO {
	
	
	private String rid;
    private String Name;
    private String type;
    private String address;
    private int rating;
    private byte[] pfImage;
    
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public byte[] getPfImage() {
		return pfImage;
	}
	public void setPfImage(byte[] pfImage) {
		this.pfImage = pfImage;
	}
    
    

}
