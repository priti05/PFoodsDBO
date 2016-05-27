package com.pFoods.dbo.restaurant.profileModal;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.restaurant.menuModel.Taste;

@Entity
@Table (name="RESTAURANT_PROFILE")
public class RestaurantProfile {
	
	/**
	 * PK
	 */
	@Id
	@Column (name="AUTO_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long autoId;
	
	@NotNull
	@Column (name="R_NAME")
	private String name;
	
	/**
	 * FK
	 */
	@NotNull
	@ManyToOne
	@JoinColumn (name="R_TASTE")
	private Taste taste;
	
	/**
	 * we can keep this unique and verified upon registration
	 * but let's just assume, owner would not ruin it's business
	 */
	@NotNull
	@Column (name="PHONE_NUMBER")
	private String phoneNumber;
	
	@NotNull
	@Column (name="DELIEVER_OPTION")
	@Enumerated (EnumType.ORDINAL)
	private Deliever delieverOption;
	
	@NotNull
	@Column (name="PICK_UP_OPTION")
	@Enumerated (EnumType.ORDINAL)
	private PickUp pickUpOption;
	
	@NotNull
	@Column (name="RESERVE_TABLE_OPTION")
	@Enumerated (EnumType.ORDINAL)
	private ReserveTable reserveTableOption;
	
	@NotNull
	@Column (name="DELIEVERY_MINIMUM")
	private String delieveryMinimum;
	
	@NotNull
	@Column (name="DELIEVERY_CHARGE")
	private String delieveryCharge;
	
	@Column (name="RATING")
	private int rating;
	
	@Column (name="NUMBER_OF_PEOPLE_RATED")
	private int numberOfPeopleRate;
	
	@NotNull
	@Lob
	@Column (name="DESCRIPTION")
	private String description;
	
	/**
	 * FK
	 */
	@NotNull
	@OneToOne
	@JoinColumn (name="RID")
	private RestaurantLoginInfo restaurantLoginInfo;
	
	public enum Deliever {
		yes,
		no 
	}
	
	public enum PickUp {
		yes,
		no
	}
	
	public enum ReserveTable{
		yes,
		no	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Taste getTaste() {
		return taste;
	}

	public void setTaste(Taste taste) {
		this.taste = taste;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public PickUp getPickUpOption() {
		return pickUpOption;
	}

	public void setPickUpOption(PickUp pickUpOption) {
		this.pickUpOption = pickUpOption;
	}

	public ReserveTable getReserveTableOption() {
		return reserveTableOption;
	}

	public void setReserveTableOption(ReserveTable reserveTableOption) {
		this.reserveTableOption = reserveTableOption;
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

	public long getAutoId() {
		return autoId;
	}

	public void setAutoId(long autoId) {
		this.autoId = autoId;
	}

	public Deliever getDelieverOption() {
		return delieverOption;
	}

	public void setDelieverOption(Deliever delieverOption) {
		this.delieverOption = delieverOption;
	}

	public RestaurantLoginInfo getRestaurantLoginInfo() {
		return restaurantLoginInfo;
	}

	public void setRestaurantLoginInfo(RestaurantLoginInfo restaurantLoginInfo) {
		this.restaurantLoginInfo = restaurantLoginInfo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberOfPeopleRate() {
		return numberOfPeopleRate;
	}

	public void setNumberOfPeopleRate(int numberOfPeopleRate) {
		this.numberOfPeopleRate = numberOfPeopleRate;
	}
	
	
	
	

}
