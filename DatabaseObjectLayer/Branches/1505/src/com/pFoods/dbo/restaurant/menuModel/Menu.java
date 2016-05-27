package com.pFoods.dbo.restaurant.menuModel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.pFoods.dbo.order.modal.ItemList;
import com.pFoods.dbo.restaurant.constants.RestuarantProfileDBOConstant.SpecialEntry;
import com.pFoods.dbo.restaurant.couponModal.Coupon;
import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;

@Entity
@Table (name = "MENU")
public class Menu {
	
	@Id
	@Column (name="MENU_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long menuId;
	
	@NotNull
	@Column(name="NAME")
	private String name;
	
	@Lob
	@NotNull
	@Column(name="DESCRIPTION")
	private String description;
	
	@NotNull
	@Column(name="PRICE")
	private String price;
	
	@Column(name="RATING")
	private int rating;
	
	@Column(name="PEOPLE_RATED")
	private int numberOfPeopleRated;
	
	@NotNull
	@Column (name="SPECIAL_ENTRY")
	@Enumerated (EnumType.ORDINAL)
	private SpecialEntry specialEntry;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name="ENTRY_TYPE")
	private EntryType entryType;
	
	@Lob
	@Column (name="IMAGE")
	private byte[] image;
	
	/**
	 * FK
	 */
	@NotNull
	@ManyToOne
	@JoinColumn (name="RID")
	private RestaurantLoginInfo restaurantLoginInfo;
	
	@OneToMany (mappedBy="menuId")
	@Cascade({CascadeType.SAVE_UPDATE})
	private List<ItemList> itemList;
	
	@OneToMany (mappedBy="menu")
	@Cascade({CascadeType.SAVE_UPDATE ,CascadeType.DELETE })
	private List<Coupon> copon;

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
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

	public SpecialEntry getSpecialEntry() {
		return specialEntry;
	}

	public void setSpecialEntry(SpecialEntry specialEntry) {
		this.specialEntry = specialEntry;
	}

	public EntryType getEntryType() {
		return entryType;
	}

	public void setEntryType(EntryType entryType) {
		this.entryType = entryType;
	}
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public RestaurantLoginInfo getRestaurantLoginInfo() {
		return restaurantLoginInfo;
	}

	public void setRestaurantLoginInfo(RestaurantLoginInfo restaurantLoginInfo) {
		this.restaurantLoginInfo = restaurantLoginInfo;
	}

	public List<ItemList> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemList> itemList) {
		this.itemList = itemList;
	}

	public List<Coupon> getCopon() {
		return copon;
	}

	public void setCopon(List<Coupon> copon) {
		this.copon = copon;
	}

	
	
	

}
