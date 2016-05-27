package com.pFoods.dbo.event.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="INVITED_NON_USER")
public class InvitedNonUser {

	@Id
	@Column (name="INVITED_NON_USER_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long invitedNonUserId;
	
	@NotNull
	@Column (name="IDENTIFIER")
	private String identifier;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name="EVENT_ID")
	private Event event;

	public long getInvitedNonUserId() {
		return invitedNonUserId;
	}

	public void setInvitedNonUserId(long invitedNonUserId) {
		this.invitedNonUserId = invitedNonUserId;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
