package com.pFoods.dbo.restaurant.imageModel;

import java.io.Serializable;

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;

@Entity
@Table(name="RESTAURANT_IMAGES")
public class Images implements Serializable {
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -1260011745186916614L;


	@Id
	@Column (name="IMAGE_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long imageId;
	
	
	@Lob
	@NotNull
	@Column(name="IMAGE")
	private byte[] image;
	
	/**
	 * The one selected by user as profile should be yes only and rest
	 * should be No
	 */
	@NotNull
	@Column (name="PROFILE_IMAGE")
	@Enumerated (EnumType.ORDINAL)
	private Profile profileImage;
	
	
	@NotNull
	@ManyToOne
	@JoinColumn (name="RID")
	private RestaurantLoginInfo restaurantLoginInfo;
	
	
	public enum Profile {
		yes,
		no
	}


	public long getImageId() {
		return imageId;
	}


	public void setImageId(long imageId) {
		this.imageId = imageId;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}
	

	public Profile getProfileImage() {
		return profileImage;
	}


	public void setProfileImage(Profile profileImage) {
		this.profileImage = profileImage;
	}


	public RestaurantLoginInfo getRestaurantLoginInfo() {
		return restaurantLoginInfo;
	}


	public void setRestaurantLoginInfo(RestaurantLoginInfo restaurantLoginInfo) {
		this.restaurantLoginInfo = restaurantLoginInfo;
	}
	
	

}
