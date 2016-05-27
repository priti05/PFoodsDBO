package com.pFoods.dbo.order.helper;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.pFoods.dbo.admin.to.AdminResponseTo;
import com.pFoods.dbo.admin.vo.RestaurantAdminVO;
import com.pFoods.dbo.order.constant.OrderConstant;
import com.pFoods.dbo.order.modal.ItemList;
import com.pFoods.dbo.order.modal.Order;
import com.pFoods.dbo.order.modal.Order.DelieverPickUp;
import com.pFoods.dbo.order.modal.Order.Paid;
import com.pFoods.dbo.order.modal.Order.PaymentType;
import com.pFoods.dbo.order.modal.Order.Status;
import com.pFoods.dbo.order.modal.OrderDetail;
import com.pFoods.dbo.order.to.CheckInRequestTO;
import com.pFoods.dbo.order.to.OrderRequestTO;
import com.pFoods.dbo.order.to.OrderResponseTO;
import com.pFoods.dbo.restaurant.couponModal.Coupon;
import com.pFoods.dbo.restaurant.imageModel.Images;
import com.pFoods.dbo.restaurant.imageModel.Images.Profile;
import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.restaurant.menuModel.Menu;
import com.pFoods.dbo.restaurant.profileModal.RestaurantAddress;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile;
import com.pFoods.dbo.restaurant.to.RestaurantSearchRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantSearchResponseTO;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.dbo.user.modal.CheckIn;
import com.pFoods.dbo.userProfile.loginModal.LoginInfo;
import com.pFoods.dbo.userProfile.profileModal.Address;


public class OrderHelper {

	public OrderResponseTO enterOrderDBOHelper(OrderRequestTO orderRequestTO,
			Session session) {
		OrderResponseTO orderResponseTO = new OrderResponseTO();
		if(orderRequestTO!=null){
			Order order = new Order();
			long couponId = orderRequestTO.getCouponId();
			BigDecimal discount = orderRequestTO.getDiscount();
			Map<Long, Integer> MenuAndQuality = orderRequestTO.getMenuAndQuantity();
			Date orderTimeStamp = orderRequestTO.getOrderTimeStamp();
			String rid = orderRequestTO.getRid();
			BigDecimal tax =  orderRequestTO.getTax();
			BigDecimal total = orderRequestTO.getTotal();
			BigDecimal totalWithoutTax = orderRequestTO.getTotalWithOutTax();
			String userNote = orderRequestTO.getUserNote();
			boolean couponUsed = orderRequestTO.isCouponUsed();
			boolean creditPaid  = orderRequestTO.isCreditPaid();
			boolean delivery = orderRequestTO.isDelievery();
			boolean paid = orderRequestTO.isPaid();
			String uid = orderRequestTO.getUid();
			if(StringUtils.isNotBlank(uid) && tax!=null && total!=null &&
					totalWithoutTax!=null && MenuAndQuality!=null && MenuAndQuality.size()>0 && 
					StringUtils.isNotBlank(rid)){
				LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,uid);
				if(loginInfo!=null){
					/*
					 * set Login Info 
					 */
					order.setLoginInfo(loginInfo);
					
					/*
					 * set delivery & Address || pickUp
					 */
					if(delivery){
						order.setDelieverPickUp(DelieverPickUp.d);
						
						long addressId = orderRequestTO.getAddressId();
						if(addressId>0){
							Address address = (Address)session.get(Address.class,addressId);
							if(address!=null){
								order.setAddress(address);
							}else{
								//Did't find matched address
								ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_GEN_111;
								return null;
							}
							
							if(order.getAddress()==null){
								//System down
								ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_GEN_111;
								return null;
							}
						}else{
							//System down
							ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_GEN_111;
							return null;
						}
	
					}else{
						order.setDelieverPickUp(DelieverPickUp.p);
					}
					
					
					/*
					 * Set Restaurant Login Info
					 */
					RestaurantLoginInfo restaurantLoginInfo = (RestaurantLoginInfo)session.get(RestaurantLoginInfo.class,rid);					
					if(restaurantLoginInfo!=null){
						order.setRestaurantLoginInfo(restaurantLoginInfo);
					}else{
						//system Down
						ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_GEN_111;
						return null;
					}
					
					/*
					 * Set Status as pending
					 */
					order.setStatus(Status.pending);
					
					/*
					 * Set Paid or Not Paid
					 * if paid then it must be credit paid or return null with
					 * error
					 */
					if(paid){
						if(creditPaid){
							order.setPaid(Paid.paid);
							order.setPaymentType(PaymentType.credit);
						}else{
							//invalid state of payment
							ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_GEN_111;
							return null;
						}
					}else{						
						order.setPaid(Paid.notPaid);
						if(creditPaid){
							order.setPaymentType(PaymentType.credit);
						}else{
							order.setPaymentType(PaymentType.cash);
						}						
					}
					
					/*
					 * set order timeStamp
					 */
					if(orderTimeStamp==null){
						order.setOrderTimeStamp();
					}else{
						order.setOrderTimeStamp(orderTimeStamp);
					}
					/*
					 * create OrderDetail and insert
					 */
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setUserNote(userNote);
					orderDetail.setTotalWithOutTax(totalWithoutTax.toPlainString());
					orderDetail.setTax(tax.toPlainString());
					orderDetail.setTotal(total.toPlainString());
					if(discount!=null){
						orderDetail.setDiscount(discount.toPlainString());
					}else{
						orderDetail.setDiscount("0.00");
					}
					if(couponUsed){
						Coupon coupon = (Coupon)session.get(Coupon.class,couponId);
						if(coupon!=null){
							orderDetail.setCoupon(coupon);
						}else{
							//system Down
							ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_GEN_111;
							return null;
						}
					}
					session.save(orderDetail);
					/*
					 * set order detail and insert Order
					 */
					order.setOrderDetail(orderDetail);
					session.save(order);
					
					
					int i = 0;
				    for (Map.Entry<Long, Integer> entry : MenuAndQuality.entrySet()) {
				    	Menu menu = (Menu)session.get(Menu.class,entry.getKey());
				    	if(menu!=null){
				    		ItemList  itemList = new ItemList();
				    		itemList.setMenuId(menu);
				    		itemList.setQuantity(entry.getValue());
				    		itemList.setOrderDetail(orderDetail);
				    		session.save(itemList);
				    		i++;
				    		if( i % 50 == 0 ) { // Same as the JDBC batch size
					            //flush a batch of inserts and release memory:
					            session.flush();
					            session.clear();
					            i=0;
					        }
				    	}else{
				    		//system down
							ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_GEN_111;
				    		return null;
				    	}
				    	
					}
				  //SuccessFully Inserted
					ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=OrderConstant.SUCCESS; ResultDBOHelper.ERROR=OrderConstant.DBO_000;
				}else{
					//ain't find you in database
					ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_GEN_111;
				}	
			}else{
				//invalid
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_DBO_101;
			}	
		}else{
			//invalid
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_DBO_101;
		}	
		return orderResponseTO;
	}

	@SuppressWarnings({  "unchecked" })
	public AdminResponseTo adminInfoDBOHelper(Session session) {
		AdminResponseTo adminResponseTo = new AdminResponseTo();
		List<RestaurantAdminVO> restaurantAdminVOList = new ArrayList<RestaurantAdminVO>();
		adminResponseTo.setRestaurantAdminVOList(restaurantAdminVOList);
		 String restaurantLoginHQL = "FROM com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo";
			List<RestaurantLoginInfo> restaurantLoginList= (List<RestaurantLoginInfo>)session.createQuery(restaurantLoginHQL).list();
			if(restaurantLoginList!=null && restaurantLoginList.size()>0){
				for(RestaurantLoginInfo restaurantLoginInfo:restaurantLoginList){
					if(restaurantLoginInfo!=null){
						RestaurantAdminVO restaurantAdminVO = new RestaurantAdminVO();
						restaurantAdminVOList.add(restaurantAdminVO);
						RestaurantProfile restaurantProfile = restaurantLoginInfo.getRestaurantProfile();
						if(restaurantProfile!=null){
							restaurantAdminVO.setName(restaurantProfile.getName());
							if(restaurantLoginInfo.getCheckIn()!=null){
								restaurantAdminVO.setNumberofCheckIn(restaurantLoginInfo.getCheckIn().size());
							}
							if(restaurantLoginInfo.getEvent()!=null){
								restaurantAdminVO.setNumberOfEvents(restaurantLoginInfo.getEvent().size());
							}
							if(restaurantLoginInfo.getOrder()!=null){
								restaurantAdminVO.setNumberOfOrders(restaurantLoginInfo.getOrder().size());
							}
							if(restaurantLoginInfo.getReserveTable()!=null){
								restaurantAdminVO.setNumberOfReservation(restaurantLoginInfo.getReserveTable().size());
							}
							restaurantAdminVO.setNumberOfPeopleRated(restaurantProfile.getNumberOfPeopleRate());
							restaurantAdminVO.setPhoneNumber(restaurantProfile.getPhoneNumber());
							restaurantAdminVO.setRating(restaurantProfile.getRating());
							restaurantAdminVO.setRid(restaurantLoginInfo.getUserId());
							restaurantAdminVO.setTaste(restaurantProfile.getTaste().getType());
							List<Order> orderList = restaurantLoginInfo.getOrder();
							int deliver=0;
							int pickUp=0;
							int cash=0;
							int credit=0;
							if(orderList!=null && orderList.size()>0){
								for(Order order: orderList){
									if(order!=null){
										if(order.getDelieverPickUp()==DelieverPickUp.d){
											deliver++;
										}else if(order.getDelieverPickUp()==DelieverPickUp.p){
											pickUp++;
										}
										if(order.getPaymentType()==PaymentType.credit){
											credit++;
										}else if(order.getPaymentType()==PaymentType.cash){
											cash++;
										}
									}
								}
							}
							restaurantAdminVO.setNumberOfDeliveryOrder(deliver);
							restaurantAdminVO.setNumberOfPickUpOrder(pickUp);
							restaurantAdminVO.setNumberOfCashOrder(cash);
							restaurantAdminVO.setNumberOfCreditOrder(credit);
							
							
						}
						
					}
				}
				//SuccessFul
				ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=OrderConstant.SUCCESS; ResultDBOHelper.ERROR=OrderConstant.DBO_000;
			}
		
		
			
		return adminResponseTo;
	}

	public void checkInDBOHelper(CheckInRequestTO checkInRequestTO, Session session) {
		if(checkInRequestTO!=null){
			String uid = checkInRequestTO.getUid();
			RestaurantSearchRequestTO restaurantSearchRequestTO = checkInRequestTO.getRestaurantSearchRequestTO();
			if(restaurantSearchRequestTO!=null && StringUtils.isNotBlank(uid)){
				String name = restaurantSearchRequestTO.getName();
				String state = restaurantSearchRequestTO.getState();
				String town = restaurantSearchRequestTO.getTown();
				String zipCode = restaurantSearchRequestTO.getZipCode();
				if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(state) && StringUtils.isNotBlank(town) && StringUtils.isNotBlank(zipCode)){
					String hql1 = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantProfile WHERE name=?";
					@SuppressWarnings("unchecked")
					List<RestaurantProfile> restaurantProfileList= (List<RestaurantProfile>)session.createQuery(hql1).setString(0,name).list();
					if(restaurantProfileList!=null && restaurantProfileList.size()>0){
						for(RestaurantProfile restaurantProfile:restaurantProfileList){
							if(restaurantProfile.getRestaurantLoginInfo()!=null){
								RestaurantLoginInfo restaurantLoginInfo = restaurantProfile.getRestaurantLoginInfo();
								String hql2 = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantAddress WHERE restaurantLoginInfo=? and state=? and"
										+ " town=? and zip=?";
								Query q =session.createQuery(hql2);
								q.setEntity(0, restaurantLoginInfo); q.setString(1, state); q.setString(2, town); q.setString(3, zipCode);
								@SuppressWarnings("unchecked")
								List<RestaurantAddress> restaurantAddressList = (List<RestaurantAddress>)q.list();
								if(restaurantAddressList!=null && restaurantAddressList.size()>0){
									for(RestaurantAddress restaurantAddress :restaurantAddressList){
										if(restaurantAddress!=null){
											LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,uid);
											if(loginInfo!=null){
												CheckIn checkIn = new CheckIn();
												checkIn.setLoginInfo(loginInfo);
												checkIn.setRestaurantLoginInfo(restaurantLoginInfo);
												checkIn.setCheckInTimeStamp();
												session.save(checkIn);
											}											
										}//
									}//
								}//						
							}//
						}//
					}//				
				}//
			}//
		}//
		
	}

	

}
