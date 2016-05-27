package com.pFoods.dbo.userProfile;




import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.dbo.singleton.SessionFactoryBuilderSinglton;
import com.pFoods.dbo.userProfile.TO.NewSecurityCodeRequestTO;
import com.pFoods.dbo.userProfile.TO.RegistrationDBORequestTO;
import com.pFoods.dbo.userProfile.TO.RetrieveUserAddressListRequestTO;
import com.pFoods.dbo.userProfile.TO.UpdateAddressDBOTO;
import com.pFoods.dbo.userProfile.TO.VerifySecurityCodeTO;
import com.pFoods.dbo.userProfile.constants.RegistrationDBOConstant;
import com.pFoods.dbo.userProfile.helper.UserProfileDBORegistrationHelper;
import com.pFoods.dbo.userProfile.vo.AddressVO;

public class UserProfileDBORegistration implements IUserProfileDBORegistration {

	@Override
	public String submitUserProfile(RegistrationDBORequestTO registrationRequestTO) {
		Session session=null;
		String securityCode = null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(registrationRequestTO!=null && session!=null){
				session.beginTransaction();
				UserProfileDBORegistrationHelper userProfDBORegHlp = new UserProfileDBORegistrationHelper();
				securityCode = userProfDBORegHlp.insertUserProfile(registrationRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
			}	
		}catch(Exception e){
			//System down (generic error)
			securityCode = null;
			try{
				session.getTransaction().rollback();
			}catch(Exception ex){
				
			}
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return securityCode;
	}

	@Override
	public long updateAddress(UpdateAddressDBOTO updateAddressDBOto) {
		Session session =null;
		long addressId = 0;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(updateAddressDBOto!=null && session!=null){
				session.beginTransaction();
				UserProfileDBORegistrationHelper userProfDBORegHlp = new UserProfileDBORegistrationHelper();
				addressId = userProfDBORegHlp.updateAddressHelper(updateAddressDBOto,session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
			}
			
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return addressId;
	}

	@Override
	public boolean userIdAvaibility(String userID) {
		Session session =null;
		boolean flag= false;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(StringUtils.isNotBlank(userID) && session!=null){
				session.beginTransaction();
				UserProfileDBORegistrationHelper userProfDBORegHlp = new UserProfileDBORegistrationHelper();
				flag = userProfDBORegHlp.userIDAvailibilityCheck(userID,session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
			}
			
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
		return flag;
	}

	@Override
	public boolean phoneNumberAvailibility(String phoneNumber) {
		Session session =null;
		boolean flag= false;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(StringUtils.isNotBlank(phoneNumber) && session!=null){
				session.beginTransaction();
				UserProfileDBORegistrationHelper userProfDBORegHlp = new UserProfileDBORegistrationHelper();
				flag = userProfDBORegHlp.phoneNumberExist(phoneNumber,session, true);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
			}
			
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
		return flag;
	}

	@Override
	public boolean verifySecurityCode(VerifySecurityCodeTO VerifySecurityCodeTO) {
		boolean flag = false;
		Session session =null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(VerifySecurityCodeTO!=null && session!=null){
				session.beginTransaction();
				UserProfileDBORegistrationHelper userProfDBORegHlp = new UserProfileDBORegistrationHelper();
				flag = userProfDBORegHlp.verifySecurityCodeHelper(VerifySecurityCodeTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
			}
			
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return flag;
		
	}

	@Override
	public List<AddressVO> retriveUserAddresses(RetrieveUserAddressListRequestTO retrieveUserAddressListRequestTO) {
		List<AddressVO> addressVOList = null;
		Session session =null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(retrieveUserAddressListRequestTO!=null && session!=null){
				session.beginTransaction();
				UserProfileDBORegistrationHelper userProfDBORegHlp = new UserProfileDBORegistrationHelper();
				addressVOList = userProfDBORegHlp.retriveUserAddressesHelper(retrieveUserAddressListRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
			}
			
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return addressVOList;
	}

	@Override
	public String newSecurityCode(NewSecurityCodeRequestTO newSecurityCodeRequestTO) {
		String newSecCode  = null;
		Session session =null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(newSecurityCodeRequestTO!=null && session!=null){
				session.beginTransaction();
				UserProfileDBORegistrationHelper userProfDBORegHlp = new UserProfileDBORegistrationHelper();
				newSecCode = userProfDBORegHlp.newSecurityCodeHelper(newSecurityCodeRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_DBO_101;
			}
			
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RegistrationDBOConstant.FAILURE; ResultDBOHelper.ERROR=RegistrationDBOConstant.REG_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return newSecCode;
		
	}



}
