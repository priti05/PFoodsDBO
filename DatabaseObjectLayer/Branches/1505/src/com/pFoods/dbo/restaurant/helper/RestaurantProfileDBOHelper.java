package com.pFoods.dbo.restaurant.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.pFoods.dbo.reserveTable.modal.ReserveTable.Status;
import com.pFoods.dbo.restaurant.constants.RestuarantProfileDBOConstant;
import com.pFoods.dbo.restaurant.constants.RestuarantProfileDBOConstant.SpecialEntry;
import com.pFoods.dbo.restaurant.couponModal.Coupon;
import com.pFoods.dbo.restaurant.couponModal.DiscountOnAmount;
import com.pFoods.dbo.restaurant.couponModal.DiscountOnItem;
import com.pFoods.dbo.restaurant.imageModel.Images;
import com.pFoods.dbo.restaurant.imageModel.Images.Profile;
import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.restaurant.menuModel.EntryType;
import com.pFoods.dbo.restaurant.menuModel.Menu;
import com.pFoods.dbo.restaurant.menuModel.Taste;
import com.pFoods.dbo.restaurant.profileModal.RestaurantAddress;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile.Deliever;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile.PickUp;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile.ReserveTable;
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
import com.pFoods.dbo.restaurant.vo.CouponVO;
import com.pFoods.dbo.restaurant.vo.DiscountOnAmountVO;
import com.pFoods.dbo.restaurant.vo.DiscountOnItemVO;
import com.pFoods.dbo.restaurant.vo.MenuVO;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.dbo.user.modal.RestaurantRating;
import com.pFoods.dbo.userProfile.loginModal.LoginInfo;

public class RestaurantProfileDBOHelper {


	public void insertRestaurantProfile(RestaurantRegistrationDBORequestTO restaurantRegistrationDBORequestTO,
			Session session) {
		RestaurantLoginInfo restaurantLoginInfo = restaurantRegistrationDBORequestTO.getRestaurantLoginInfo();
		int tasteId = restaurantRegistrationDBORequestTO.getTasteId();
		if(restaurantLoginInfo!=null){
			String rid = restaurantLoginInfo.getUserId();
			if(ridAvailibilityCheck(rid, session)){ 
				RestaurantProfile restaurantProfile = restaurantRegistrationDBORequestTO.getRestaurantProfile();
				RestaurantAddress restaurantAddress = restaurantRegistrationDBORequestTO.getRestaurantAddress();
				if(restaurantProfile!=null && restaurantAddress!=null){
					Taste taste = (Taste)session.get(Taste.class,tasteId);
					restaurantProfile.setTaste(taste);
						/*
						 * must be inserted in same order as per their dependencies
						 */
						session.save(restaurantLoginInfo);
						session.save(restaurantProfile);
						session.save(restaurantAddress);
						//Successfully inserted
						ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.DBO_000;
				}else{
					//Invalid input
					ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
				}
			}else{
				//user ID not available
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_102;
			}
		}else{
			//Invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
		}
		
	}
	
	public boolean ridAvailibilityCheck(String rid, Session session){
		if(StringUtils.isNotBlank(rid)){
			if((RestaurantLoginInfo)session.get(RestaurantLoginInfo.class,rid)==null){
				return true;
			}
		}else{
			//invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
		}
		return false;
	}

	public void insertRestaurantMenu(RestaurantMenuRegistrationDBORequestTO restaurantMenuRegistrationDBORequestTO,
			Session session) {
		String rid = restaurantMenuRegistrationDBORequestTO.getRid();
		List<Menu> menuList = restaurantMenuRegistrationDBORequestTO.getMenuList();
		if(StringUtils.isNotBlank(rid)){
			RestaurantLoginInfo restaurantLoginInfo = (RestaurantLoginInfo)session.get(RestaurantLoginInfo.class,rid);
			int batchCounter = 0;
			if(restaurantLoginInfo!=null){
				if(menuList!=null && menuList.size()>0){
					for(Menu menu : menuList){
						if(menu!=null){
							menu.setRestaurantLoginInfo(restaurantLoginInfo);
							session.saveOrUpdate(menu);
							batchCounter++;
							if( batchCounter % 50 == 0 ) { // Same as the JDBC batch size
						        //flush a batch of inserts and release memory:
						        session.flush();
						        session.clear();
						        batchCounter = 0;
						    }
						}
					}
				}
				
				//Successfully inserted
				ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.DBO_000;
			}else{
				//user ID not available
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_102;
			}
		}else{
			//invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
		}
			
	}

	/**
	 * 
	 * @param restaurantValidateLoginCredentialRequestTO
	 * @param session
	 * @return
	 */
	public boolean validateCredentialHelper(
			RestaurantValidateLoginCredentialRequestTO restaurantValidateLoginCredentialRequestTO, Session session) {
		boolean validated = false;
		String rid = restaurantValidateLoginCredentialRequestTO.getRid();
		String password = restaurantValidateLoginCredentialRequestTO.getPassword();
		if(StringUtils.isNotBlank(rid) && StringUtils.isNotBlank(password)){
			RestaurantLoginInfo restaurantLoginInfo = (RestaurantLoginInfo)session.get(RestaurantLoginInfo.class,rid);
			if(restaurantLoginInfo!=null){
				/*
				 * return boolean value
				 */
				validated = (StringUtils.isNotBlank(restaurantLoginInfo.getPassword()) && StringUtils.equalsIgnoreCase(password, restaurantLoginInfo.getPassword()));
				//SuccessFully called
				ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.DBO_000;
			}else{
				//RID not found
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_103;
			}
			
		}else{
			//Invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
		}
		return validated;
	}

	public void insertRestaurantImages(RestaurantInsertImagesRequestTO restaurantInsertImagesRequestTO, Session session) {
		String rid = restaurantInsertImagesRequestTO.getRid();
		List<Images> imageList = restaurantInsertImagesRequestTO.getImageList();
		if(StringUtils.isNotBlank(rid)){
			RestaurantLoginInfo restaurantLoginInfo = (RestaurantLoginInfo)session.get(RestaurantLoginInfo.class,rid);
			int batchCounter = 0;
			if(restaurantLoginInfo!=null){
				if(imageList!=null && imageList.size()>0){
					for(Images image : imageList){
						if(image!=null){
							image.setRestaurantLoginInfo(restaurantLoginInfo);
							session.saveOrUpdate(image);
							batchCounter++;
							if( batchCounter % 50 == 0 ) { // Same as the JDBC batch size
						        //flush a batch of inserts and release memory:
						        session.flush();
						        session.clear();
						        batchCounter = 0;
						    }
						}
					}
				}
				//Successfully inserted
				ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.DBO_000;
			}else{
				//user ID not available
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_102;
			}
		}else{
			//invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
		}
	}

	@SuppressWarnings("unchecked")
	public List<RestaurantNameandAddressInfo> retrieveRestaurantNameandAddressInfoHelper(Session session) {
		List<RestaurantNameandAddressInfo> restaurantNameandAddressInfoList = new ArrayList<RestaurantNameandAddressInfo>();
		
		String hql = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantAddress";

		 List<RestaurantAddress> restaurantAddressList= (List<RestaurantAddress>)session.createQuery(hql).list();
			if(restaurantAddressList!=null && restaurantAddressList.size()>0){
				for(RestaurantAddress restaurantAddress:restaurantAddressList){
					if(restaurantAddress!=null){
						RestaurantLoginInfo restaurantLoginInfo = restaurantAddress.getRestaurantLoginInfo();
						if(restaurantLoginInfo!=null){
							String restaurantProfileHQL = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantProfile  WHERE restaurantLoginInfo=?";
							List<RestaurantProfile> RestaurantProfileList= (List<RestaurantProfile>)session.createQuery(restaurantProfileHQL).setEntity(0,restaurantLoginInfo).list();
							if(RestaurantProfileList!=null && RestaurantProfileList.size()>0){
								for(RestaurantProfile restaurantProfile:RestaurantProfileList){
									if(restaurantProfile!=null){
										RestaurantNameandAddressInfo restaurantNameandAddressInfo =  new RestaurantNameandAddressInfo(
												restaurantProfile.getName(), restaurantAddress.getTown(), restaurantAddress.getState(), restaurantAddress.getZip());
										restaurantNameandAddressInfoList.add(restaurantNameandAddressInfo);
									}
								}
							}
						}
					}
				}				
				//SuccessFully called
				ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.DBO_000;
			}
		
		return restaurantNameandAddressInfoList;
	}

	@SuppressWarnings("unchecked")
	public List<RestaurantSearchResponseTO> searchRestaurantDBO(RestaurantSearchRequestTO restaurantSearchRequestTO, Session session) {
		List<RestaurantSearchResponseTO> restaurantSearchResponseTOList = new ArrayList<RestaurantSearchResponseTO>();
		if(restaurantSearchRequestTO!=null){
			String name = restaurantSearchRequestTO.getName();
			String state = restaurantSearchRequestTO.getState();
			String town = restaurantSearchRequestTO.getTown();
			String zipCode = restaurantSearchRequestTO.getZipCode();
			if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(state) && StringUtils.isNotBlank(town) && StringUtils.isNotBlank(zipCode)){
				String hql1 = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantProfile WHERE name=?";
				List<RestaurantProfile> restaurantProfileList= (List<RestaurantProfile>)session.createQuery(hql1).setString(0,name).list();
				if(restaurantProfileList!=null && restaurantProfileList.size()>0){
					for(RestaurantProfile restaurantProfile:restaurantProfileList){
						if(restaurantProfile.getRestaurantLoginInfo()!=null){
							RestaurantLoginInfo restaurantLoginInfo = restaurantProfile.getRestaurantLoginInfo();
							String hql2 = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantAddress WHERE restaurantLoginInfo=? and state=? and"
									+ " town=? and zip=?";
							Query q =session.createQuery(hql2);
							q.setEntity(0, restaurantLoginInfo); q.setString(1, state); q.setString(2, town); q.setString(3, zipCode);
							List<RestaurantAddress> restaurantAddressList = (List<RestaurantAddress>)q.list();
							if(restaurantAddressList!=null && restaurantAddressList.size()>0){
								for(RestaurantAddress restaurantAddress :restaurantAddressList){
									if(restaurantAddress!=null){
										RestaurantSearchResponseTO restaurantSearchResponseTO = new RestaurantSearchResponseTO();
										restaurantSearchResponseTO.setRid(restaurantLoginInfo.getUserId());
										restaurantSearchResponseTO.setName(name);
										restaurantSearchResponseTO.setAddress(restaurantAddress.getStreetNumber()+", "+ restaurantAddress.getStreet()
												+" "+restaurantAddress.getAddress1()+restaurantAddress.getAddress2()+", "+ restaurantAddress.getTown()+", "+
												restaurantAddress.getState()+", "+ restaurantAddress.getZip());
										List<Images> imageList = restaurantLoginInfo.getImageList();
										if(imageList!=null && imageList.size()>0){
											for(Images image :imageList){
												if(image!=null && image.getProfileImage()==Profile.yes){
													restaurantSearchResponseTO.setPfImage(image.getImage());
												}
											}
										}									
										restaurantSearchResponseTO.setRating(restaurantProfile.getRating());
										restaurantSearchResponseTO.setType(restaurantProfile.getTaste().getType());	
										restaurantSearchResponseTOList.add(restaurantSearchResponseTO);
									}
								}
							}							
						}
					}
				}				
			}else if(StringUtils.isNotBlank(state) && StringUtils.isNotBlank(town) && StringUtils.isNotBlank(zipCode)){
				String hql1 = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantAddress WHERE state=? and town=? and zip=?";
				Query q =session.createQuery(hql1);
				q.setString(0, state); q.setString(1, town); q.setString(2, zipCode);
				List<RestaurantAddress> restaurantAddressList = (List<RestaurantAddress>)q.list();
				if(restaurantAddressList!=null && restaurantAddressList.size()>0){
					for(RestaurantAddress restaurantAddress :restaurantAddressList){
						if(restaurantAddress!=null){
							RestaurantLoginInfo restaurantLoginInfo = restaurantAddress.getRestaurantLoginInfo();
							String hql2 = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantProfile WHERE restaurantLoginInfo=?";
							Query query =session.createQuery(hql2);
							query.setParameter(0, restaurantLoginInfo);
							List<RestaurantProfile> restaurantProfileList = (List<RestaurantProfile>)query.list();
							if(restaurantProfileList!=null && restaurantProfileList.size()>0){
								for(RestaurantProfile restaurantProfile : restaurantProfileList){
									if(restaurantProfile!=null){
										RestaurantSearchResponseTO restaurantSearchResponseTO = new RestaurantSearchResponseTO();
										restaurantSearchResponseTO.setRid(restaurantLoginInfo.getUserId());
										restaurantSearchResponseTO.setName(restaurantProfile.getName());
										restaurantSearchResponseTO.setAddress(restaurantAddress.getStreetNumber()+", "+ restaurantAddress.getStreet()
												+", "+restaurantAddress.getAddress1()+restaurantAddress.getAddress2()+", "+ restaurantAddress.getTown()+", "+
												restaurantAddress.getState()+", "+ restaurantAddress.getZip());
										List<Images> imageList = restaurantLoginInfo.getImageList();
										if(imageList!=null && imageList.size()>0){
											for(Images image :imageList){
												if(image!=null && image.getProfileImage()==Profile.yes){
													restaurantSearchResponseTO.setPfImage(image.getImage());
												}
											}
										}
										restaurantSearchResponseTO.setRating(restaurantProfile.getRating());
										restaurantSearchResponseTO.setType(restaurantProfile.getTaste().getType());	
										restaurantSearchResponseTOList.add(restaurantSearchResponseTO);										
									}
								}								
							}
						}
					}
				}
			
				
			}else if(StringUtils.isNotBlank(town) && StringUtils.isNotBlank(zipCode)){
				String hql1 = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantAddress WHERE town=? and zip=?";
				Query q =session.createQuery(hql1);
				q.setString(0, town); q.setString(1, zipCode);
				List<RestaurantAddress> restaurantAddressList = (List<RestaurantAddress>)q.list();
				if(restaurantAddressList!=null && restaurantAddressList.size()>0){
					for(RestaurantAddress restaurantAddress :restaurantAddressList){
						if(restaurantAddress!=null){
							RestaurantLoginInfo restaurantLoginInfo = restaurantAddress.getRestaurantLoginInfo();
							String hql2 = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantProfile WHERE restaurantLoginInfo=?";
							Query query =session.createQuery(hql2);
							query.setParameter(0, restaurantLoginInfo);
							List<RestaurantProfile> restaurantProfileList = (List<RestaurantProfile>)query.list();
							if(restaurantProfileList!=null && restaurantProfileList.size()>0){
								for(RestaurantProfile restaurantProfile : restaurantProfileList){
									if(restaurantProfile!=null){
										RestaurantSearchResponseTO restaurantSearchResponseTO = new RestaurantSearchResponseTO();
										restaurantSearchResponseTO.setRid(restaurantLoginInfo.getUserId());
										restaurantSearchResponseTO.setName(restaurantProfile.getName());
										restaurantSearchResponseTO.setAddress(restaurantAddress.getStreetNumber()+", "+ restaurantAddress.getStreet()
												+", "+restaurantAddress.getAddress1()+restaurantAddress.getAddress2()+", "+ restaurantAddress.getTown()+", "+
												restaurantAddress.getState()+", "+ restaurantAddress.getZip());
										List<Images> imageList = restaurantLoginInfo.getImageList();
										if(imageList!=null && imageList.size()>0){
											for(Images image :imageList){
												if(image!=null && image.getProfileImage()==Profile.yes){
													restaurantSearchResponseTO.setPfImage(image.getImage());
												}
											}
										}
										restaurantSearchResponseTO.setRating(restaurantProfile.getRating());
										restaurantSearchResponseTO.setType(restaurantProfile.getTaste().getType());	
										restaurantSearchResponseTOList.add(restaurantSearchResponseTO);										
									}
								}								
							}
						}
					}
				}
			
			
			}else if(StringUtils.isNotBlank(state) && StringUtils.isNotBlank(town)){
				String hql1 = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantAddress WHERE state=? and town=?";
				Query q =session.createQuery(hql1);
				q.setString(0, state); q.setString(1, town);
				List<RestaurantAddress> restaurantAddressList = (List<RestaurantAddress>)q.list();
				if(restaurantAddressList!=null && restaurantAddressList.size()>0){
					for(RestaurantAddress restaurantAddress :restaurantAddressList){
						if(restaurantAddress!=null){
							RestaurantLoginInfo restaurantLoginInfo = restaurantAddress.getRestaurantLoginInfo();
							String hql2 = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantProfile WHERE restaurantLoginInfo=?";
							Query query =session.createQuery(hql2);
							query.setParameter(0, restaurantLoginInfo);
							List<RestaurantProfile> restaurantProfileList = (List<RestaurantProfile>)query.list();
							if(restaurantProfileList!=null && restaurantProfileList.size()>0){
								for(RestaurantProfile restaurantProfile : restaurantProfileList){
									if(restaurantProfile!=null){
										RestaurantSearchResponseTO restaurantSearchResponseTO = new RestaurantSearchResponseTO();
										restaurantSearchResponseTO.setRid(restaurantLoginInfo.getUserId());
										restaurantSearchResponseTO.setName(restaurantProfile.getName());
										restaurantSearchResponseTO.setAddress(restaurantAddress.getStreetNumber()+", "+ restaurantAddress.getStreet()
												+", "+restaurantAddress.getAddress1()+restaurantAddress.getAddress2()+", "+ restaurantAddress.getTown()+", "+
												restaurantAddress.getState()+", "+ restaurantAddress.getZip());
										List<Images> imageList = restaurantLoginInfo.getImageList();
										if(imageList!=null && imageList.size()>0){
											for(Images image :imageList){
												if(image!=null && image.getProfileImage()==Profile.yes){
													restaurantSearchResponseTO.setPfImage(image.getImage());
												}
											}
										}
										restaurantSearchResponseTO.setRating(restaurantProfile.getRating());
										restaurantSearchResponseTO.setType(restaurantProfile.getTaste().getType());	
										restaurantSearchResponseTOList.add(restaurantSearchResponseTO);										
									}
								}								
							}
						}
					}
				}
			
				
			}else{
				restaurantSearchResponseTOList = null;
			}
			//SuccessFully called
			ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.DBO_000;
		}else{			
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
		}		
		
		return restaurantSearchResponseTOList;		
	}

	@SuppressWarnings({ "unchecked" })
	public RestaurantProfileInformationResponseTO retrieveRestaurantInformation(
			RestaurantProfileInformationRequestTO restaurantProfileInformationRequestTO,
			Session session) {
		RestaurantProfileInformationResponseTO restaurantProfileInformationResponseTO = new RestaurantProfileInformationResponseTO();
		if(restaurantProfileInformationRequestTO!=null){
			String rid = restaurantProfileInformationRequestTO.getRid();
			if(StringUtils.isNotBlank(rid)){
				String newRid = StringUtils.trim(rid);
				RestaurantLoginInfo restaurantLoginInfo = (RestaurantLoginInfo)session.get(RestaurantLoginInfo.class,newRid);
				if(restaurantLoginInfo!=null){
					String restProfileHQL = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantProfile WHERE restaurantLoginInfo=?";
					Query restProfileQuery =session.createQuery(restProfileHQL);
					restProfileQuery.setParameter(0, restaurantLoginInfo);
					List<RestaurantProfile> restaurantProfileList = (List<RestaurantProfile>)restProfileQuery.list();
					if(restaurantProfileList!=null && restaurantProfileList.size()>0){
						for(RestaurantProfile restaurantProfile :restaurantProfileList){
							if(restaurantProfile!=null){
								restaurantProfileInformationResponseTO.setRid(newRid);
								restaurantProfileInformationResponseTO.setName(restaurantProfile.getName());
								restaurantProfileInformationResponseTO.setDescription(restaurantProfile.getDescription());
								restaurantProfileInformationResponseTO.setDelieveryCharge(restaurantProfile.getDelieveryCharge());
								restaurantProfileInformationResponseTO.setDelieveryMinimum(restaurantProfile.getDelieveryMinimum());
								restaurantProfileInformationResponseTO.setPhoneNumber(restaurantProfile.getPhoneNumber());
								restaurantProfileInformationResponseTO.setNumberOfPeopleRate(restaurantProfile.getNumberOfPeopleRate());
								restaurantProfileInformationResponseTO.setRating(restaurantProfile.getRating());
								Deliever deliever = restaurantProfile.getDelieverOption();
								if(deliever!=null){
									if(Deliever.yes==deliever){
										restaurantProfileInformationResponseTO.setDeliever(true);
									}
								}
								
								PickUp pickUp = restaurantProfile.getPickUpOption();
								if(pickUp!=null){
									if(PickUp.yes==pickUp){
										restaurantProfileInformationResponseTO.setPickUp(true);
									}
								}
								
								ReserveTable reserveTable = restaurantProfile.getReserveTableOption();
								if(reserveTable!=null){
									if(ReserveTable.yes==reserveTable){
										restaurantProfileInformationResponseTO.setReserveTable(true);
									}
								}
								Taste taste = restaurantProfile.getTaste();
								if(taste!=null){
									restaurantProfileInformationResponseTO.setTaste(taste.getType());
								}
								
								List<Coupon> couponList = restaurantLoginInfo.getCoupon();
								if(couponList!=null && couponList.size()>0){
									List<CouponVO> couponVOList = new ArrayList<CouponVO>();
									for(Coupon coupon:couponList){
										if(coupon!=null){
											CouponVO couponVO = new CouponVO();
											couponVO.setId(coupon.getCouponId());
											couponVO.setPromocode(coupon.getPromoCode());
											couponVO.setPromoDetail(coupon.getPromoDetail());
											couponVO.setExpiratoinDate(coupon.getExpirationDate());
											DiscountOnAmount discountOnAmount = coupon.getDiscountOnAmount();
											if(discountOnAmount!=null){
												String amount = discountOnAmount.getAmountDiscountOn();
												String percent = discountOnAmount.getPercentOfDiscount();
												if(StringUtils.isNotBlank(amount) && StringUtils.isNotBlank(percent)){
													DiscountOnAmountVO discountOnAmountVO = new DiscountOnAmountVO();
													discountOnAmountVO.setAmountDiscountOn(amount);
													discountOnAmountVO.setPercentOfDiscount(percent);
													couponVO.setDiscountOnAmountVO(discountOnAmountVO);
												}
											}
											DiscountOnItem discountOnItem = coupon.getDiscountOnItem();
											if(discountOnItem!=null){
												Menu menu = coupon.getMenu();
												if(menu!=null){
													DiscountOnItemVO discountOnItemVO = new DiscountOnItemVO();
													discountOnItemVO.setMenuId(menu.getMenuId());
													discountOnItemVO.setNumberOfItemRequire(discountOnItem.getNumberOfItemRequire());
													discountOnItemVO.setNumberOfFreeItem(discountOnItem.getNumberOfFreeItem());
													couponVO.setDiscountOnItemVO(discountOnItemVO);
												}
											}
											couponVOList.add(couponVO);
										}
									}
									restaurantProfileInformationResponseTO.setConponVOList(couponVOList);
								}
								
								List<Menu> menuList = restaurantLoginInfo.getMenuList();								
								if(menuList!=null && menuList.size()>0){
									List<MenuVO> menuVOList = new ArrayList<MenuVO>();
									for(Menu menu:menuList){
										if(menu!=null){
											MenuVO menuVO = new MenuVO();
											menuVO.setId(menu.getMenuId());
											menuVO.setName(menu.getName());
											menuVO.setDescription(menu.getDescription());
											EntryType entryType = menu.getEntryType();
											if(entryType!=null){
												menuVO.setEntryType(entryType.getEntryType());
											}
											menuVO.setImage(menu.getImage());
											menuVO.setNumberOfPeopleRated(menu.getNumberOfPeopleRated());
											String price = menu.getPrice();
											if(StringUtils.isNotBlank(price)){
												BigDecimal sPrice = new BigDecimal(price);
												menuVO.setPrice(sPrice);
											}
											menuVO.setRating(menu.getRating());
											if(SpecialEntry.yes == menu.getSpecialEntry()){
												menuVO.setSpecialEntry(true);
											}
											menuVOList.add(menuVO);
										}
									}
									restaurantProfileInformationResponseTO.setMenuVoList(menuVOList);	
								}
								
								List<Images> imageList = restaurantLoginInfo.getImageList();
								if(imageList!=null && imageList.size()>0){
									List<byte[]> images = new ArrayList<byte[]>();
									for(Images image: imageList){
										if(image!=null){
											if(Profile.yes==image.getProfileImage()){
												restaurantProfileInformationResponseTO.setProfileImage(image.getImage());
											}else{
												images.add(image.getImage());
											}
										}
									}
									restaurantProfileInformationResponseTO.setImagesList(images);
								}
							}
						}
					}
					
					String addressHQL = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantAddress WHERE restaurantLoginInfo=?";
					Query restAddressQuery =session.createQuery(addressHQL);
					restAddressQuery.setParameter(0, restaurantLoginInfo);
					List<RestaurantAddress> restaurantAddressList = (List<RestaurantAddress>)restAddressQuery.list();
					if(restaurantAddressList!=null && restaurantAddressList.size()>0){
						for(RestaurantAddress restaurantAddress :restaurantAddressList){
							if(restaurantAddress!=null){
								restaurantProfileInformationResponseTO.setAddress(restaurantAddress.getStreetNumber()+", "+ restaurantAddress.getStreet()
										+", "+restaurantAddress.getAddress1()+restaurantAddress.getAddress2()+ restaurantAddress.getTown()+", "+
										restaurantAddress.getState()+", "+ restaurantAddress.getZip());
							}
						}
					}
					
					LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,
							StringUtils.trim(restaurantProfileInformationRequestTO.getUid()));
					if(loginInfo!=null){
						String userRatingHQL = "FROM com.pFoods.dbo.user.modal.RestaurantRating WHERE restaurantLoginInfo=? and loginInfo=?";
						Query userRatinQuery =session.createQuery(userRatingHQL);
						userRatinQuery.setParameter(0, restaurantLoginInfo);
						userRatinQuery.setParameter(1, loginInfo);
						List<RestaurantRating> rastaurantRatingList = (List<RestaurantRating>)userRatinQuery.list();
						if(rastaurantRatingList!=null && rastaurantRatingList.size()>0){
							for(RestaurantRating restaurantRating: rastaurantRatingList){
								if(restaurantRating!=null){
									restaurantProfileInformationResponseTO.setCurrentUserRating(restaurantRating.getRate());
								}
							}
						}
					}
					
					//SuccessFully called
					ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.DBO_000;
				}else{
					//system Down error
					ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_GEN_111;
				}
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
			}
		}else{
			//invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
		}
		return restaurantProfileInformationResponseTO;
	}

	@SuppressWarnings("unchecked")
	public void syncRatingHelperDBO(RestaurantRatingRequestTO restaurantRatingRequestTO, Session session) {
		
		if(StringUtils.isNotBlank(restaurantRatingRequestTO.getRid()) && StringUtils.isNotBlank(restaurantRatingRequestTO.getUid())){
			RestaurantLoginInfo restaurantLoginInfo = (RestaurantLoginInfo)session.get(RestaurantLoginInfo.class,
					StringUtils.trim(restaurantRatingRequestTO.getRid()));
			LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,StringUtils.trim(restaurantRatingRequestTO.getUid()));
			if(restaurantLoginInfo!=null && loginInfo!=null){
				
				String restProfileHQL = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantProfile WHERE restaurantLoginInfo=?";
				Query restProfileQuery =session.createQuery(restProfileHQL);
				restProfileQuery.setParameter(0, restaurantLoginInfo);
				List<RestaurantProfile> restaurantProfileList = (List<RestaurantProfile>)restProfileQuery.list();
				
				String restaurantRatingHQL = "FROM com.pFoods.dbo.user.modal.RestaurantRating WHERE restaurantLoginInfo=? AND loginInfo=?";
				Query restaurantRatingQuery =session.createQuery(restaurantRatingHQL);
				restaurantRatingQuery.setParameter(0, restaurantLoginInfo);
				restaurantRatingQuery.setParameter(1, loginInfo);
				List<RestaurantRating> restaurantratingList = (List<RestaurantRating>)restaurantRatingQuery.list();
				if(restaurantratingList!=null && restaurantratingList.size()>0){
					for(RestaurantRating restaurantRating :restaurantratingList){
						if(restaurantRating!=null){
							restaurantRating.setRate(restaurantRatingRequestTO.getRating());
							restaurantRating.setRatedTimeStamp();
							session.update(restaurantRating);
							if(restaurantProfileList!=null && restaurantProfileList.size()>0){
								for(RestaurantProfile restaurantProfile :restaurantProfileList){
									if(restaurantProfile!=null){
										restaurantProfile.setRating((restaurantProfile.getRating()+restaurantRatingRequestTO.getRating())/2);
										session.update(restaurantProfile);
										break;
									}
								}
							}
							break;
						}
					}
				}else{
					RestaurantRating restaurantRating  = new RestaurantRating();
					restaurantRating.setLoginInfo(loginInfo);
					restaurantRating.setRestaurantLoginInfo(restaurantLoginInfo);
					restaurantRating.setRatedTimeStamp();
					restaurantRating.setRate(restaurantRatingRequestTO.getRating());
					session.save(restaurantRating);
					if(restaurantProfileList!=null && restaurantProfileList.size()>0){
						for(RestaurantProfile restaurantProfile :restaurantProfileList){
							if(restaurantProfile!=null){
								restaurantProfile.setNumberOfPeopleRate(restaurantProfile.getNumberOfPeopleRate()+1);
								restaurantProfile.setRating((restaurantProfile.getRating()+restaurantRatingRequestTO.getRating())/2);
								session.update(restaurantProfile);
								break;
							}
						}
					}
				}
				//Successfully inserted
				ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.DBO_000;
			}else{
				//user ID not available
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_102;
			}
		}else{
			//invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
		}
		
	}

	public void reserveTableDBOHelper(RestaurantReserveTableRequestTO restaurantReserveTableRequestTO, Session session) {
		String rid = StringUtils.trim(restaurantReserveTableRequestTO.getRid());
		String uid = StringUtils.trim(restaurantReserveTableRequestTO.getUid());
		int numberOfPerson = restaurantReserveTableRequestTO.getNumberOfPerson();
		Date dataAndTimeOfReservation = restaurantReserveTableRequestTO.getDataAndTimeOfReservation();
		Date timeStamp = restaurantReserveTableRequestTO.getTimeStamp();
		if(StringUtils.isNotBlank(rid) && StringUtils.isNotBlank(uid) && dataAndTimeOfReservation!=null){
			com.pFoods.dbo.reserveTable.modal.ReserveTable reserveTable = new com.pFoods.dbo.reserveTable.modal.ReserveTable();
			LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,uid);
			RestaurantLoginInfo restaurantLoginInfo = (RestaurantLoginInfo)session.get(RestaurantLoginInfo.class,rid);
			if(loginInfo!=null && restaurantLoginInfo!=null){
				reserveTable.setLoginInfo(loginInfo);
				reserveTable.setRestaurantLoginInfo(restaurantLoginInfo);
				reserveTable.setNumberOfPerson(numberOfPerson);
				reserveTable.setDateTimeOfReservation(dataAndTimeOfReservation);
				if(timeStamp==null){
					reserveTable.setReserveTableTimeStamp();
				}else{
					reserveTable.setReserveTableTimeStamp(timeStamp);
				}
				reserveTable.setStatus(Status.pending);
				session.save(reserveTable);
				//Successfully inserted
				ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.SUCCESS; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.DBO_000;
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
			}
		}else{
			//invalid input
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=RestuarantProfileDBOConstant.FAILURE; ResultDBOHelper.ERROR=RestuarantProfileDBOConstant.RSPF_DBO_101;
		}
		
	}
	
}
