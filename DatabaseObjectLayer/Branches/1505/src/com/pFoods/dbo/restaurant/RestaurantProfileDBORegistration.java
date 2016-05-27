package com.pFoods.dbo.restaurant;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pFoods.dbo.restaurant.constants.RestuarantProfileDBOConstant;
import com.pFoods.dbo.restaurant.couponModal.Coupon;
import com.pFoods.dbo.restaurant.helper.RestaurantProfileDBOHelper;
import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.restaurant.menuModel.Menu;
import com.pFoods.dbo.restaurant.to.RestaurantInsertImagesRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantMenuRegistrationDBORequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantNameandAddressInfo;
import com.pFoods.dbo.restaurant.to.RestaurantProfileInformationRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantProfileInformationResponseTO;
import com.pFoods.dbo.restaurant.to.RestaurantRatingRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantRegistrationDBORequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantReserveTableRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantSearchRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantSearchResponseTO;
import com.pFoods.dbo.restaurant.to.RestaurantValidateLoginCredentialRequestTO;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.dbo.singleton.SessionFactoryBuilderSinglton;

public class RestaurantProfileDBORegistration implements  IRestaurantProfileDBORegistration{

	@Override
	public boolean registerRestaurantMenuProfile(RestaurantMenuRegistrationDBORequestTO restaurantMenuRegistrationDBORequestTO) {
		Session session=null;
		boolean flag=false;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(restaurantMenuRegistrationDBORequestTO!=null && session!=null){
				session.beginTransaction();
				RestaurantProfileDBOHelper restaurantProfileDBOHelper = new RestaurantProfileDBOHelper();
				restaurantProfileDBOHelper.insertRestaurantMenu(restaurantMenuRegistrationDBORequestTO, session);
				session.getTransaction().commit();
				flag=true;
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return flag;
	}

	@Override
	public boolean registerRestaurant(RestaurantRegistrationDBORequestTO RestaurantRegistrationDBORequestTO) {
		Session session=null;
		boolean flag=false;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(RestaurantRegistrationDBORequestTO!=null && session!=null){
				session.beginTransaction();
				RestaurantProfileDBOHelper restaurantProfileDBOHelper = new RestaurantProfileDBOHelper();
				restaurantProfileDBOHelper.insertRestaurantProfile(RestaurantRegistrationDBORequestTO, session);
				session.getTransaction().commit();
				flag=true;
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return flag;
	}

	@Override
	public boolean validateLoginRestaurant(
			RestaurantValidateLoginCredentialRequestTO restaurantValidateLoginCredentialRequestTO) {
		// TODO Auto-generated method stub
		Session session=null;
		boolean flag=false;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(restaurantValidateLoginCredentialRequestTO!=null && session!=null){
				session.beginTransaction();
				RestaurantProfileDBOHelper restaurantProfileDBOHelper = new RestaurantProfileDBOHelper();
				flag = restaurantProfileDBOHelper.validateCredentialHelper(restaurantValidateLoginCredentialRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return flag;
	}

	@Override
	public void insertRestuarantProfileImages(RestaurantInsertImagesRequestTO restaurantInsertImagesRequestTO) {
		Session session=null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(restaurantInsertImagesRequestTO!=null && session!=null){
				session.beginTransaction();
				RestaurantProfileDBOHelper restaurantProfileDBOHelper = new RestaurantProfileDBOHelper();
				restaurantProfileDBOHelper.insertRestaurantImages(restaurantInsertImagesRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
	}

	@Override
	public List<RestaurantNameandAddressInfo> retrieveRestaurantNameandAddressInfo() {
		Session session=null;
		List<RestaurantNameandAddressInfo> restaurantNameandAddressInfo= null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(session!=null){
				session.beginTransaction();
				RestaurantProfileDBOHelper restaurantProfileDBOHelper = new RestaurantProfileDBOHelper();
				restaurantNameandAddressInfo = restaurantProfileDBOHelper.retrieveRestaurantNameandAddressInfoHelper(session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}			
		}
		return restaurantNameandAddressInfo;
	}

	@Override
	public List<RestaurantSearchResponseTO> searchRestaurantDBO(RestaurantSearchRequestTO restaurantSearchRequestTO) {
		Session session=null;
		List<RestaurantSearchResponseTO> restaurantSearchResponseTOList= null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(session!=null){
				session.beginTransaction();
				RestaurantProfileDBOHelper restaurantProfileDBOHelper = new RestaurantProfileDBOHelper();
				restaurantSearchResponseTOList = restaurantProfileDBOHelper.searchRestaurantDBO(restaurantSearchRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}			
		}
		return restaurantSearchResponseTOList;
	}

	@Override
	public RestaurantProfileInformationResponseTO getRestaurantProfile(
			RestaurantProfileInformationRequestTO restaurantProfileInformationRequestTO) {
		Session session=null;
		RestaurantProfileInformationResponseTO restaurantProfileInformationResponseTO= null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(session!=null){
				session.beginTransaction();
				RestaurantProfileDBOHelper restaurantProfileDBOHelper = new RestaurantProfileDBOHelper();
				restaurantProfileInformationResponseTO = restaurantProfileDBOHelper.retrieveRestaurantInformation(restaurantProfileInformationRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}			
		}
		return restaurantProfileInformationResponseTO;
	}

	@Override
	public void syncRating(RestaurantRatingRequestTO restaurantRatingRequestTO) {
		Session session=null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(restaurantRatingRequestTO!=null && session!=null){
				session.beginTransaction();
				RestaurantProfileDBOHelper restaurantProfileDBOHelper = new RestaurantProfileDBOHelper();
				restaurantProfileDBOHelper.syncRatingHelperDBO(restaurantRatingRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
	}

	@Override
	public void reserveTable(RestaurantReserveTableRequestTO restaurantReserveTableRequestTO) {
		Session session=null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(restaurantReserveTableRequestTO!=null && session!=null){
				session.beginTransaction();
				RestaurantProfileDBOHelper restaurantProfileDBOHelper = new RestaurantProfileDBOHelper();
				restaurantProfileDBOHelper.reserveTableDBOHelper(restaurantReserveTableRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
	}

	@Override
	public void insertCoupon(Coupon coupon, boolean isMenuItem, long menuID) {
		Session session=null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(coupon!=null && session!=null){
				session.beginTransaction();
				coupon.setRestaurantLoginInfo((RestaurantLoginInfo)session.get(RestaurantLoginInfo.class,"mat123"));
				if(isMenuItem){
					coupon.setMenu((Menu)session.get(Menu.class,menuID));
				}
				session.saveOrUpdate(coupon);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
	}

}
