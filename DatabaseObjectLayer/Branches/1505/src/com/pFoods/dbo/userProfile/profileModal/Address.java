package com.pFoods.dbo.userProfile.profileModal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.pFoods.dbo.order.modal.Order;
import com.pFoods.dbo.userProfile.loginModal.LoginInfo;

@Entity
@Table (name="ADDRESS")
public class Address {
	
	/**
	 * PF
	 */
	@Id
	@Column (name="AUTO_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long autoID;
	
	/**
	 * FK
	 */
	@NotNull
	@ManyToOne
	@JoinColumn (name="USER_ID")
	private LoginInfo loginInfo;
	
	@NotNull
	@Column (name="STREET_NUMBER")
	private String street_Numer;
	
	@Column (name ="APT_NUMBER")
	private String apt_Numer;
	
	@NotNull
	@Column (name="STREET")
	private String street;
	
	@NotNull
	@Column (name="TOWN")
	private String town;
	
	@NotNull
	@Column (name="STATE")
	private String state;
	
	@NotNull
	@Column (name="ZIP")
	private String zip;
	
	@NotNull
	@Column (name="ADDRESS_TYPE")
	@Enumerated (EnumType.STRING)
	private AddressType addressType;
	
	@OneToMany (mappedBy="address")
	@Cascade({CascadeType.SAVE_UPDATE})
	private List<Order> order;
	
	public enum AddressType {
		p,
		s 
	}
	
	public long getAutoID() {
		return autoID;
	}
	public void setAutoID(long autoID) {
		this.autoID = autoID;
	}
	
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	public String getStreet_Numer() {
		return street_Numer;
	}
	public void setStreet_Numer(String street_Numer) {
		this.street_Numer = street_Numer;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getApt_Numer() {
		return apt_Numer;
	}
	public void setApt_Numer(String apt_Numer) {
		this.apt_Numer = apt_Numer;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public AddressType getAddressType() {
		return addressType;
	}
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
		
}
