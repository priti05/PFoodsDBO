package com.pFoods.dbo.userProfile;

import java.util.List;

import com.pFoods.dbo.userProfile.TO.NewSecurityCodeRequestTO;
import com.pFoods.dbo.userProfile.TO.RegistrationDBORequestTO;
import com.pFoods.dbo.userProfile.TO.RetrieveUserAddressListRequestTO;
import com.pFoods.dbo.userProfile.TO.UpdateAddressDBOTO;
import com.pFoods.dbo.userProfile.TO.VerifySecurityCodeTO;
import com.pFoods.dbo.userProfile.vo.AddressVO;

public interface IUserProfileDBORegistration {

	/**
	 * This method is created to register user.
	 * It also check if user has skipped for address or
	 * not. If success then ResultDBOHelper.SUCCESS = true;
	 * RESULT = "success"; ERROR = " ";
	 * @author Priti Patel
	 * @param registrationRequestTO 
	 * @exception RegistrationDBOConstant.REG_DBO_102 userid not available, already taken by other user;
	              RegistrationDBOConstant.REG_DBO_101 invalid entered data;
				  RegistrationDBOConstant.REG_GEN_111 Generic error;
	 */
	public String submitUserProfile(RegistrationDBORequestTO registrationRequestTO);
	
	/**
	 * This method is created to add user address. User can't modify existing address with it.
	 * If success then ResultDBOHelper.SUCCESS = true;
	 * RESULT = "success"; ERROR = " ";
	 * @author Priti Patel
	 * @param updateAddressto
	 * @exception RegistrationDBOConstant.REG_DBO_101 invalid entered data; RegistrationDBOConstant.REG_GEN_111 Generic error;
	 */
	public long updateAddress(UpdateAddressDBOTO updateAddressto);
	
	public List<AddressVO> retriveUserAddresses(RetrieveUserAddressListRequestTO RetrieveUserAddressListRequestTO);
	
	public boolean userIdAvaibility(String userID);
	
	public boolean phoneNumberAvailibility(String phoneNumber);
	
	public boolean verifySecurityCode(VerifySecurityCodeTO verifySecurityCodeTO);
	
	public String newSecurityCode(NewSecurityCodeRequestTO wewSecurityCodeRequestTO);
	
	
}
