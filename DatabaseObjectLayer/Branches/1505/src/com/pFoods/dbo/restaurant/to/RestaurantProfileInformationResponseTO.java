package com.pFoods.dbo.restaurant.to;


import java.util.List;

import com.pFoods.dbo.restaurant.vo.CouponVO;
import com.pFoods.dbo.restaurant.vo.MenuVO;

public class RestaurantProfileInformationResponseTO {
	
	private String rid;
	private String name;
	private String description;
	private String taste;
	private String phoneNumber;
	private boolean deliever = false;
	private boolean pickUp = false;
	private boolean reserveTable =  false;
	private String delieveryMinimum;
	private String delieveryCharge;
	private int rating;
	private int numberOfPeopleRate;
	private List<CouponVO> conponVOList;
	private byte[] profileImage;
	private List<byte[]> imagesList;
	private List<MenuVO> menuVoList;
	private String address;
	private int currentUserRating;
	
	
	
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
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
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isDeliever() {
		return deliever;
	}
	public void setDeliever(boolean deliever) {
		this.deliever = deliever;
	}
	public boolean isPickUp() {
		return pickUp;
	}
	public void setPickUp(boolean pickUp) {
		this.pickUp = pickUp;
	}
	public boolean isReserveTable() {
		return reserveTable;
	}
	public void setReserveTable(boolean reserveTable) {
		this.reserveTable = reserveTable;
	}
	public String getDelieveryMinimum() {
		return delieveryMinimum;
	}
	public void setDelieveryMinimum(String delieveryMinimum) {
		this.delieveryMinimum = delieveryMinimum;
	}
	public String getDelieveryCharge() {
		return delieveryCharge;
	}
	public void setDelieveryCharge(String delieveryCharge) {
		this.delieveryCharge = delieveryCharge;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getNumberOfPeopleRate() {
		return numberOfPeopleRate;
	}
	public void setNumberOfPeopleRate(int numberOfPeopleRate) {
		this.numberOfPeopleRate = numberOfPeopleRate;
	}
	public List<CouponVO> getConponVOList() {
		return conponVOList;
	}
	public void setConponVOList(List<CouponVO> conponVOList) {
		this.conponVOList = conponVOList;
	}
	public byte[] getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}
	public List<byte[]> getImagesList() {
		return imagesList;
	}
	public void setImagesList(List<byte[]> imagesList) {
		this.imagesList = imagesList;
	}
	public List<MenuVO> getMenuVoList() {
		return menuVoList;
	}
	public void setMenuVoList(List<MenuVO> menuVoList) {
		this.menuVoList = menuVoList;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCurrentUserRating() {
		return currentUserRating;
	}
	public void setCurrentUserRating(int currentUserRating) {
		this.currentUserRating = currentUserRating;
	}
	


}
