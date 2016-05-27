package com.pFoods.dbo.state.StateModal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="TOWN")
public class Town {

	
	@NotNull
	@Column (name="CITY")
	private String city;
	
	@Id
	@Column (name="ZIP_CODE")
	private String zipCode;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name="STATE_ID")
	private State state;

	public String getCity() {
		return city;
	}

	public String getZipCode() {
		return zipCode;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public State getState() {
		return state;
	}

	
	@SuppressWarnings("unused")
	private void setState(State state) {
		this.state = state;
	}
	
	
	
	

	
}
