package com.pFoods.dbo.userProfile.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import com.pFoods.dbo.event.helper.EventHelper;
import com.pFoods.dbo.event.modal.InvitedNonUser;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.dbo.userProfile.TO.NewSecurityCodeRequestTO;
import com.pFoods.dbo.userProfile.TO.RegistrationDBORequestTO;
import com.pFoods.dbo.userProfile.TO.RetrieveUserAddressListRequestTO;
import com.pFoods.dbo.userProfile.TO.UpdateAddressDBOTO;
import com.pFoods.dbo.userProfile.TO.VerifySecurityCodeTO;
import com.pFoods.dbo.userProfile.constants.RegistrationDBOConstant;
import com.pFoods.dbo.userProfile.loginModal.LoginInfo;
import com.pFoods.dbo.userProfile.profileModal.Address;
import com.pFoods.dbo.userProfile.profileModal.UserProfile;
import com.pFoods.dbo.userProfile.profileModal.UserProfile.VerificationType;
import com.pFoods.dbo.userProfile.vo.AddressVO;

public class UserProfileDBORegistrationHelper {
	
	/**
	 * 
	 * @param registrationRequestTO
	 * @param session
	 */
	public String insertUserProfile(RegistrationDBORequestTO registrationRequestTO, Session session){
		String securityCode = null;
		LoginInfo logininfo = registrationRequestTO.getLogininfo();
		if(logininfo!=null){
			String userID = logininfo.getUserId();
			if(userIDAvailibilityCheck(userID, session)){ 
				UserProfile userProfile = registrationRequestTO.getUserProfile();
				Address address = registrationRequestTO.getAddress();
				if(userProfile!=null){
					if(phoneNumberExist(userProfile.getPhone_number(), session, true)){
						/*
						 * must be inserted in same order as per their dependencies
						 */
						long secCode = (long) (100000 + (new Random().nextFloat()) * 900000);
						logininfo.setSecurityCode(secCode);
						session.save(logininfo);
						session.save(userProfile);
						if(address!=null){
							session.save(address);
						}
						securityCode = String.valueOf(secCode);
						//Successfully inserted
						ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RegistrationDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RegistrationDBOConstant.DBO_000;
					}else{
						//Phone Number already registered not available
						ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_103;
					}
				}else{
					//Invalid input
					ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
				}
			}else{
				//user ID not available
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_102;
			}
		}else{
			//Invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
		}
		return securityCode;
	}
	
	/**
	 * 
	 * @param userID
	 * @param session
	 * @return
	 */
	public boolean userIDAvailibilityCheck(String userID, Session session){
		if(StringUtils.isNotBlank(userID)){
			if((LoginInfo)session.get(LoginInfo.class,userID)==null){
				return true;
			}
		}else{
			//user ID not available
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
		}
		return false;
	}
	
	/**
	 * 
	 * @param phoneNumber
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean phoneNumberExist(String phoneNumber, Session session, boolean checkVerification){
		if(StringUtils.isNotBlank(phoneNumber)){
			 String hql = "FROM com.pFoods.dbo.userProfile.profileModal.UserProfile WHERE Phone_number=?";

			 List<UserProfile> recordList= (List<UserProfile>)session.createQuery(hql).setString(0,phoneNumber).list(); 

			 if(recordList!=null && recordList.size()>0){
				 if(checkVerification){
					 for(UserProfile userProfile:recordList){
						 if(userProfile!=null){
							 if(userProfile.getVerifiedStatus()==VerificationType.no){
								 return true;
							 }
						 }
					 }
				 }
				 return false;
			 }
			 return true; 
		}else{
			//user ID not available
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
		}
		return false;
	}

	/**
	 * 
	 * @param updateAddressto
	 * @param session
	 */
	public long updateAddressHelper(UpdateAddressDBOTO updateAddressto, Session session) {
		long addressID=0;
		String userID = updateAddressto.getUserID();
		Address address = updateAddressto.getAddress();
		if(StringUtils.isNotBlank(userID) && address!=null){
			LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,StringUtils.trim(userID));
			if(loginInfo!=null){
				address.setLoginInfo(loginInfo);
				Collection<Address> addressList = new ArrayList<>();
				addressList.add(address);
				loginInfo.setAddress(addressList);
				//session.save(loginInfo);
				session.save(address);
				addressID = address.getAutoID();
				//Successfully inserted
				ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RegistrationDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RegistrationDBOConstant.DBO_000;
			}else{
				/*
				 * System down (generic error) error shouldn't come at this point, just in case
				 */
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_GEN_111;
			}
		}else{
			//Invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
		}
		return addressID;
	}

	@SuppressWarnings("unchecked")
	public boolean verifySecurityCodeHelper(VerifySecurityCodeTO verifySecurityCodeTO,Session session) {
		String userID = verifySecurityCodeTO.getUid();
		String secCode = verifySecurityCodeTO.getVerifySecurityCode();
		if(StringUtils.isNotBlank(userID) && StringUtils.isNotBlank(secCode)){
			LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,StringUtils.trim(userID));
			if(loginInfo!=null){
				String dbSecCode = String.valueOf(loginInfo.getSecurityCode());
				if(StringUtils.isNotBlank(dbSecCode)){
					boolean verified = StringUtils.equalsIgnoreCase(dbSecCode, secCode);
					if(verified){
						UserProfile userProfile = loginInfo.getUserProfile();
						if(userProfile!=null){
							if(StringUtils.isNotBlank(userProfile.getPhone_number())){
								String HQL = "FROM com.pFoods.dbo.event.modal.InvitedNonUser WHERE identifier=?";
								 List<InvitedNonUser> recordList= (List<InvitedNonUser>)session.createQuery(HQL).setString(0,userProfile.getPhone_number()).list();
								 if(recordList!=null && recordList.size()>0){
									 int i = 0;
									 for(InvitedNonUser invitedNonUser :recordList){
										 if(invitedNonUser!=null){
											 EventHelper eventHelper = new EventHelper();
											 eventHelper.addNewGuest(loginInfo, invitedNonUser.getEvent(), session);
											 i++;
									    		if( i % 50 == 0 ) { // Same as the JDBC batch size
										            //flush a batch of inserts and release memory:
										            session.flush();
										            session.clear();
										            i=0;
										        }
										 }
									 }
								 }
							} 
							userProfile.setVerifiedStatus(VerificationType.yes);
							session.saveOrUpdate(loginInfo);
							ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RegistrationDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RegistrationDBOConstant.DBO_000;
							return true;
						}
					}
				}
			}else{
				/*
				 * System down (generic error) error shouldn't come at this point, just in case
				 */
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_GEN_111;
			}
		}else{
			//Invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
		}
		return false;
	}

	public List<AddressVO> retriveUserAddressesHelper(
			RetrieveUserAddressListRequestTO retrieveUserAddressListRequestTO, Session session) {
		String userID = retrieveUserAddressListRequestTO.getUid();
		if(StringUtils.isNotBlank(userID)){
			LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,StringUtils.trim(userID));
			if(loginInfo!=null){
				List<AddressVO> addressVOList = new ArrayList<AddressVO>();
				Collection<Address> addressList= (Collection<Address>) loginInfo.getAddress();
				if(addressList!=null && addressList.size()>0){
					for(Address address:addressList){
						if(address!=null){
							AddressVO addressVO = new AddressVO();
							addressVO.setAddressId(address.getAutoID());
							addressVO.setApt_Numer(address.getApt_Numer());
							addressVO.setStreetNumer(address.getStreet_Numer());
							addressVO.setStreet(address.getStreet());
							addressVO.setTown(address.getTown());
							addressVO.setState(address.getState());
							addressVO.setZip(address.getZip());
							addressVOList.add(addressVO);
						}
					}
					ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RegistrationDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RegistrationDBOConstant.DBO_000;
					return addressVOList;
				}
			}else{
				/*
				 * System down (generic error) error shouldn't come at this point, just in case
				 */
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_GEN_111;
			}
		}else{
			//Invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
		}
		return null;
	}

	public String newSecurityCodeHelper(NewSecurityCodeRequestTO newSecurityCodeRequestTO, Session session) {
		String userID = newSecurityCodeRequestTO.getUid();
		String newSecCode = null;
		if(StringUtils.isNotBlank(userID) ){
			LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,StringUtils.trim(userID));
			if(loginInfo!=null){
				long secCode = (long) (100000 + (new Random().nextFloat()) * 900000);
				loginInfo.setSecurityCode(secCode);
				session.saveOrUpdate(loginInfo);
				newSecCode =String.valueOf(secCode);
				ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RegistrationDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RegistrationDBOConstant.DBO_000;
			}else{
				/*
				 * System down (generic error) error shouldn't come at this point, just in case
				 */
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_GEN_111;
			}
		}else{
			//Invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
		}
		return newSecCode;
	}

}
