package com.pFoods.dbo.restaurant.vo;

import java.math.BigDecimal;

public class MenuVO {
	private long id;
	private String name;
	private String description;
	private BigDecimal price;
	private int rating;
	private int numberOfPeopleRated;
	private boolean specialEntry;
	private String entryType;
	private byte[] image;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getNumberOfPeopleRated() {
		return numberOfPeopleRated;
	}
	public void setNumberOfPeopleRated(int numberOfPeopleRated) {
		this.numberOfPeopleRated = numberOfPeopleRated;
	}
	public boolean isSpecialEntry() {
		return specialEntry;
	}
	public void setSpecialEntry(boolean specialEntry) {
		this.specialEntry = specialEntry;
	}
	public String getEntryType() {
		return entryType;
	}
	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
	
	
}
