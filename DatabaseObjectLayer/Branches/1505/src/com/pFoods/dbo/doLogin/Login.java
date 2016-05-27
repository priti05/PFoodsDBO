package com.pFoods.dbo.doLogin;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pFoods.dbo.doLogin.constant.LoginConstants;
import com.pFoods.dbo.doLogin.to.CheckVerifiedStatusRequestTO;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.dbo.singleton.SessionFactoryBuilderSinglton;
import com.pFoods.dbo.userProfile.loginModal.LoginInfo;
import com.pFoods.dbo.userProfile.profileModal.UserProfile;
import com.pFoods.dbo.userProfile.profileModal.UserProfile.VerificationType;

public class Login implements Ilogin {
	

	/**
	 * This method return password or appropriate error
	 * messages to SL
	 * parameter is userID
	 */
	@Override
	public boolean retrievePassword(String userID, String password) {
		Session session = null;
		try{
			boolean isSoftLock = false, isHardLock = false;
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			session.beginTransaction();
			LoginInfo loginInfo = null;
			if(StringUtils.isNotBlank(userID) && StringUtils.isNotBlank(password)){
				loginInfo = (LoginInfo)session.get(LoginInfo.class,userID);
				if(loginInfo!=null){
					
					if(loginInfo.isHardLock()){
						Date date = loginInfo.getHardLockDate();
						if(date!=null){
							Calendar hardTempLockCal = Calendar.getInstance(); // creates calendar
							hardTempLockCal.setTime(date); // sets calendar time/date
							hardTempLockCal.add(Calendar.HOUR_OF_DAY, 6); // adds one hour
							Date hardLock  = hardTempLockCal.getTime();
							
							if(!(new Date()).after(hardLock)){
								isHardLock = true;
							}
						}
					}
					
					if(!isHardLock){
						if(loginInfo.isSoftLock()){
							Date date = loginInfo.getSoftLockDate();
							if(date!=null){
								Calendar softTempLockCal = Calendar.getInstance(); // creates calendar
								softTempLockCal.setTime(date); // sets calendar time/date
								softTempLockCal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
								Date softLock  = softTempLockCal.getTime();
								
								if(!(new Date()).after(softLock)){
									isSoftLock = true;
								}
							}
							
						}
						if(!isSoftLock){
							if(StringUtils.equals(loginInfo.getPassword(), password)){
								loginInfo.setWorngAttempToLogin(0);
								loginInfo.setSoftLock(false);
								loginInfo.setHardLock(false);	
								loginInfo.setSoftLockDate(null);
								loginInfo.setHardLockDate(null);
								session.save(loginInfo);
								session.getTransaction().commit();
								ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=LoginConstants.SUCCESS; ResultDBOHelper.ERROR=LoginConstants.DBO_000;
								return true;
							}else{
								int wrontAttempt = loginInfo.getWorngAttempToLogin() + 1;
								if(wrontAttempt<=4){
									loginInfo.setWorngAttempToLogin(wrontAttempt);
									session.save(loginInfo);
									//wrong attempt
									if(wrontAttempt>2){
										ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=LoginConstants.FAILURE; ResultDBOHelper.ERROR=LoginConstants.LGN_DBO_904;
									}else{
										ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=LoginConstants.FAILURE; ResultDBOHelper.ERROR=LoginConstants.LGN_DBO_900;
									}
								}else if(wrontAttempt==5){
									loginInfo.setWorngAttempToLogin(wrontAttempt);
									loginInfo.setSoftLock(true);
									loginInfo.setSoftLockDate(new Date());
									session.save(loginInfo);
									//soft lock
									ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=LoginConstants.FAILURE; ResultDBOHelper.ERROR=LoginConstants.LGN_DBO_901;
								}
								session.getTransaction().commit();
								return false;
							}	
						}else{
							int wrontAttempt = loginInfo.getWorngAttempToLogin() + 1;
							if(wrontAttempt>5){
								loginInfo.setWorngAttempToLogin(wrontAttempt);
								if(wrontAttempt<=7){
									loginInfo.setSoftLock(true);
									loginInfo.setSoftLockDate(new Date());
								}else{
									loginInfo.setHardLock(true);
									loginInfo.setHardLockDate(new Date());
								}
								session.save(loginInfo);
							//error with softlocked
							ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=LoginConstants.FAILURE; ResultDBOHelper.ERROR=LoginConstants.LGN_DBO_901;
							}
							session.getTransaction().commit();
						}
						
					}else{
						int wrontAttempt = loginInfo.getWorngAttempToLogin() + 1;
						if(wrontAttempt>7){
							loginInfo.setWorngAttempToLogin(wrontAttempt);
							loginInfo.setHardLock(true);
							loginInfo.setHardLockDate(new Date());
							session.save(loginInfo);
							//hard lock
						ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=LoginConstants.FAILURE; ResultDBOHelper.ERROR=LoginConstants.LGN_DBO_902;
						}
						session.getTransaction().commit();
					}
				}else{
					//User Id not in database
					ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=LoginConstants.FAILURE; ResultDBOHelper.ERROR=LoginConstants.LGN_DBO_100;
				}
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=LoginConstants.FAILURE; ResultDBOHelper.ERROR=LoginConstants.LGN_DBO_101;
			}
		}catch(Exception e){
			//any exception
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=LoginConstants.FAILURE; ResultDBOHelper.ERROR=LoginConstants.LGN_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return false;
	}

	@Override
	public boolean checkVerifiedStatus(CheckVerifiedStatusRequestTO checkVerifiedStatusRequestTO) {
		Session session = null;
		boolean flag=false;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			session.beginTransaction();
			if(checkVerifiedStatusRequestTO!=null && StringUtils.isNotBlank(checkVerifiedStatusRequestTO.getUid())){
				LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,StringUtils.trim(checkVerifiedStatusRequestTO.getUid()));
				if(loginInfo!=null){
					UserProfile userProfile  = loginInfo.getUserProfile();
					if(userProfile!=null){
						if(userProfile.getVerifiedStatus()==VerificationType.yes){
							flag = true;
							ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=LoginConstants.SUCCESS; ResultDBOHelper.ERROR=LoginConstants.DBO_000;
						}else{
							flag=false;
						}
					}
				}
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=LoginConstants.FAILURE; ResultDBOHelper.ERROR=LoginConstants.LGN_DBO_101;
			}
		}catch(Exception e){
			//any exception
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=LoginConstants.FAILURE; ResultDBOHelper.ERROR=LoginConstants.LGN_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return flag;
	}
}
