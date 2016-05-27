package com.pFoods.dbo.restaurant.menuModel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile;

@Entity
@Table (name="TASTE")
public class Taste {
	
	@Id
	@Column (name="TASTE_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int tasteId;
	
	@NotNull
	@Column (name="TYPE")
	private String type;
	
	@OneToMany (mappedBy="taste")
	@Cascade({CascadeType.SAVE_UPDATE})
	private List<RestaurantProfile> RestaurantProfileList;

	public int getTasteId() {
		return tasteId;
	}

	public void setTasteId(int tasteId) {
		this.tasteId = tasteId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RestaurantProfile> getRestaurantProfileList() {
		return RestaurantProfileList;
	}

	public void setRestaurantProfileList(List<RestaurantProfile> restaurantProfileList) {
		RestaurantProfileList = restaurantProfileList;
	}
	
	
	
	
	
	
	
}
