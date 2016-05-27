package com.pFoods.dbo.state.StateModal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table (name="STATE")
public class State {

	@Id
	@Column (name="STATE_ID")
	private String state_ID;
	
	@NotNull
	@Column (name="STATE", unique=true)
	private String state;
	
	@OneToMany (fetch=FetchType.EAGER, mappedBy="state")
	private List<Town> town;
	
	public String getState_ID() {
		return state_ID;
	}
	
	public String getState() { 
		return state;
	}

	@SuppressWarnings("unused")
	private void setState_ID(String state_ID) {
		this.state_ID = state_ID;
	}

	@SuppressWarnings("unused")
	private void setState(String state) {
		this.state = state;
	}

	public List<Town> getTown() {
		return town;
	}

	public void setTown(List<Town> town) {
		this.town = town;
	}
	

}
