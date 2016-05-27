package com.pFoods.dbo.userProfile.profileModal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.pFoods.dbo.userProfile.loginModal.LoginInfo;


@Entity 
@Table (name="USER_PROFILE")
public class UserProfile {
	
	@Id
	@Column (name="AUTO_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long autoID; 
	
	@NotNull
	@Column (name="FIRST_NAME")
	private String firstname;
	
	@NotNull
	@Column (name="LAST_NAME")
	private String lastName;
	
	@NotNull
	@Column (name="PHONE_NUMBER")
	private String Phone_number; 
	
	@Column (name="EMAIL")
	private String email; 
	
	@NotNull
	@Column (name="VERIFICATION_STATUS")
	@Enumerated (EnumType.ORDINAL)
	private VerificationType verifiedStatus;
	
	@NotNull
	@Column (name="CREATION_DATE")
	private Date profileCreationDate;
	
	@NotNull
	@OneToOne
	@JoinColumn (name="USER_ID")
	private LoginInfo loginInfo;
	
	@Lob
	@Column (name="PROFILE_PICTURE")
	private byte[] profilePicture;
	
	public enum VerificationType{
		yes,
		no
	}
	
	public long getAutoID() {
		return autoID;
	}
	public void setAutoID(long autoID) {
		this.autoID = autoID;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone_number() {
		return Phone_number;
	}
	public void setPhone_number(String phone_number) {
		Phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public VerificationType getVerifiedStatus() {
		return verifiedStatus;
	}
	public void setVerifiedStatus(VerificationType verifiedStatus) {
		this.verifiedStatus = verifiedStatus;
	}
	public void setProfileCreationDate(Date profileCreationDate) {
		this.profileCreationDate = profileCreationDate;
	}
	public Date getProfileCreationDate() {
		return profileCreationDate;
	}
	public void setProfileCreationDate() {
		this.profileCreationDate = new Date();
	}
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	public byte[] getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	
}
