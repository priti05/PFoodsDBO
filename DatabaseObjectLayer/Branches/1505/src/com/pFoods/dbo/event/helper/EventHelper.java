package com.pFoods.dbo.event.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.pFoods.dbo.event.constant.EventConstant;
import com.pFoods.dbo.event.modal.Event;
import com.pFoods.dbo.event.modal.GuestList;
import com.pFoods.dbo.event.modal.GuestList.Status;
import com.pFoods.dbo.event.modal.InvitedNonUser;
import com.pFoods.dbo.event.to.ContactBookRequestTO;
import com.pFoods.dbo.event.to.CreateEventRequestTO;
import com.pFoods.dbo.event.to.EventListDetailsRequestTO;
import com.pFoods.dbo.event.to.EventListDetailsResponseTO;
import com.pFoods.dbo.event.to.UpdateStatusRequestTO;
import com.pFoods.dbo.event.vo.AdminVO;
import com.pFoods.dbo.event.vo.ContactBookVO;
import com.pFoods.dbo.event.vo.EventVO;
import com.pFoods.dbo.event.vo.GuestVO;
import com.pFoods.dbo.event.vo.NonUserGuestVO;
import com.pFoods.dbo.event.vo.RestaurantVO;
import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.dbo.userProfile.loginModal.LoginInfo;
import com.pFoods.dbo.userProfile.profileModal.UserProfile;
import com.pFoods.dbo.userProfile.profileModal.UserProfile.VerificationType;

public class EventHelper {

	@SuppressWarnings("unchecked")
	public void createEventHelperDBO(CreateEventRequestTO createEventRequestTO, Session session) {
		Date timeStamp = createEventRequestTO.getEventCreatedTimeStamp();
		String dscr = createEventRequestTO.getEventDescription();
		String eventName = createEventRequestTO.getEventName();
		Date eventDateAndTime = createEventRequestTO.getEventTimeNDate();
		String rid = StringUtils.trim(createEventRequestTO.getRid());
		String uid = StringUtils.trim(createEventRequestTO.getUid());
		List<String> phoneList = createEventRequestTO.getPhoneNumber();
		List<String> invitedGuestList = createEventRequestTO.getInvitedGuestList();
		if(StringUtils.isNotBlank(eventName) && eventDateAndTime!=null 
				&& StringUtils.isNotBlank(rid) && StringUtils.isNotBlank(uid) ){
			LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,uid);
			RestaurantLoginInfo restaurantLoginInfo = (RestaurantLoginInfo)session.get(RestaurantLoginInfo.class,rid);
			if(loginInfo!=null && restaurantLoginInfo!=null){
				Event event = new Event();
				if(timeStamp==null){
					event.setEventCreatedTimeStamp();
				}else{
					event.setEventCreatedTimeStamp(timeStamp);
				}
				
				if(StringUtils.isNotBlank(dscr)){
					event.setEventDescription(dscr);
				}
				event.setEventName(eventName);
				event.setEventTimeNDate(eventDateAndTime);
				event.setLoginInfo(loginInfo);
				event.setRestaurantLoginInfo(restaurantLoginInfo);
				session.save(event);
				List<LoginInfo> loginInfoList = new ArrayList<LoginInfo>();
				for(String number:phoneList){
					if(StringUtils.isNotBlank(number)){
						 String hql = "FROM com.pFoods.dbo.userProfile.profileModal.UserProfile WHERE Phone_number=?";
						 List<UserProfile> recordList= (List<UserProfile>)session.createQuery(hql).setString(0,
								 StringUtils.trim(number)).list();
						 if(recordList!=null && recordList.size()>0){
							 for(UserProfile userProfile :recordList){
								 loginInfoList.add(userProfile.getLoginInfo());
								 break;
							 }
						 }
					}
				}
				if(loginInfoList!=null && loginInfoList.size()>0){
					int i = 0;
					for(LoginInfo guest:loginInfoList){
						if(guest!=null){
							GuestList guestList = new GuestList();
							guestList.setEvent(event);
							guestList.setLoginInfo(guest);
							guestList.setStatus(Status.pending);
							session.save(guestList);
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
				if(invitedGuestList!=null && invitedGuestList.size()>0){
					int i = 0;
					for(String nonUser:invitedGuestList){
						if(StringUtils.isNotBlank(nonUser)){
							InvitedNonUser invitedNonUser = new InvitedNonUser();
							invitedNonUser.setEvent(event);
							invitedNonUser.setIdentifier(nonUser);
							session.save(invitedNonUser);
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
				
				
				//Successfully created
				ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=EventConstant.SUCCESS; ResultDBOHelper.ERROR=EventConstant.DBO_000;
			}else{
				//invalid
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_DBO_101;
			}
			}else{
			//invalid
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_DBO_101;			
		}
		
		
	}

	@SuppressWarnings({ "unchecked"})
	public EventListDetailsResponseTO everetrieveEventListDBOHelper(
			EventListDetailsRequestTO eventListDetailsRequestTO, Session session) {
		EventListDetailsResponseTO eventListDetailsResponseTO = new EventListDetailsResponseTO();
		String uid = StringUtils.trim(eventListDetailsRequestTO.getUid());
		if(StringUtils.isNotBlank(uid)){
			LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,uid);
			if(loginInfo!=null){
				List<EventVO> eventvoList = new ArrayList<EventVO>();
				eventListDetailsResponseTO.setEventVOList(eventvoList);
				List<Event> eventList = loginInfo.getEvent();
				if(eventList!=null && eventList.size()>0){
					for(Event event : eventList){
						if(event!=null && event.getEventTimeNDate().after(new Date())){
							/*
							 * Create EventVO
							 */
							EventVO eventVO = new EventVO();
							eventvoList.add(eventVO);
							eventVO.setAdmin(true);
							eventVO.setEventId(event.getEventId());
							
							/*
							 *Create AdminVO 
							 */
							 String hql = "FROM com.pFoods.dbo.userProfile.profileModal.UserProfile WHERE loginInfo=?";
							 List<UserProfile> recordList= (List<UserProfile>)session.createQuery(hql).setParameter(0,loginInfo)
									 .list();
							 if(recordList!=null && recordList.size()>0){
								 for(UserProfile userProfile:recordList){
									 if(userProfile!=null){
										 	AdminVO adminVO = new AdminVO();
											adminVO.setName(userProfile.getFirstname()+" " + userProfile.getLastName());
											adminVO.setPhoneNumber(userProfile.getPhone_number());
											eventVO.setAdminVO(adminVO);
											break;
									 }
								 }
							 }
							 
							 eventVO.setDescription(event.getEventDescription());
							 eventVO.setEventName(event.getEventName());
							 eventVO.setEventTimeNDate(event.getEventTimeNDate());
							 
							 
							 List<GuestVO> guestVOList = new ArrayList<GuestVO>();
							 eventVO.setGuestVOList(guestVOList);
							 List<GuestList> guestList = event.getGuestList();
							 if(guestList!=null && guestList.size()>0){
								 for(GuestList guest :guestList){
									 if(guest!=null){
										 LoginInfo guestLoginInfo = guest.getLoginInfo(); 
										 if(guestLoginInfo!=null){
											 
											 List<UserProfile> recordGuestList= (List<UserProfile>)session.createQuery(hql).setParameter(0,guestLoginInfo)
													 .list();
											 if(recordGuestList!=null && recordGuestList.size()>0){
												 for(UserProfile userProfile:recordGuestList){
													 if(userProfile!=null){
														 GuestVO guestVO = new GuestVO();
														 guestVOList.add(guestVO);
														 guestVO.setName(userProfile.getFirstname()+" "+ userProfile.getLastName());
														 guestVO.setPhoneNumber(userProfile.getPhone_number());
														 if(guest.getStatus()==Status.pending){
															 guestVO.setPending(true);
														 }else if(guest.getStatus()==Status.accepted){
															 guestVO.setJoining(true);
														 }else{
															 guestVO.setNotComing(true);
														 }
														 break;
													 }
												 }
											 }
										 }
									 }
								 }
							 }
							 
							 List<NonUserGuestVO> nonUserGuestVOList = new ArrayList<NonUserGuestVO>();
							 eventVO.setNonUserGuestVOList(nonUserGuestVOList);
							 List<InvitedNonUser> invitedNonUserList = event.getInvitedNonUser();
							 if(invitedNonUserList!=null && invitedNonUserList.size()>0){
								 for(InvitedNonUser invitedNonUser :invitedNonUserList){
									 if(invitedNonUser!=null){
										 NonUserGuestVO nonUserGuestVO = new NonUserGuestVO();
										 nonUserGuestVO.setPhoneNumber(invitedNonUser.getIdentifier());
										 nonUserGuestVOList.add(nonUserGuestVO);
									 }
								 }
							 }
							 
							 RestaurantVO restaurantVO = new RestaurantVO();
							 eventVO.setRestaurantVO(restaurantVO);
							 RestaurantLoginInfo restaurantLoginInfo = event.getRestaurantLoginInfo();
							 if(restaurantLoginInfo!=null){
								 String restaurantProfileHQL = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantProfile  WHERE restaurantLoginInfo=?";
									List<RestaurantProfile> restaurantProfileList= (List<RestaurantProfile>)session.createQuery(restaurantProfileHQL).setEntity(0,restaurantLoginInfo).list();
									if(restaurantProfileList!=null && restaurantProfileList.size()>0){
										for(RestaurantProfile restaurantProfile :restaurantProfileList){
											if(restaurantProfile!=null){
												restaurantVO.setRid(restaurantLoginInfo.getUserId());
												restaurantVO.setName(restaurantProfile.getName());
												break;
											}
										}
									}
							 }
							
							
							
						}
					}
				}
				
				//-------------------//
				
				List<GuestList> guestList = loginInfo.getGuestList();
				if(guestList!=null && guestList.size()>0){
					for(GuestList guest: guestList){
						if(guest!=null){
							Event guestEvent = guest.getEvent();
							if(guestEvent!=null && guestEvent.getEventTimeNDate().after(new Date())){
								EventVO eventVO = new EventVO();
								eventvoList.add(eventVO);
								eventVO.setEventId(guestEvent.getEventId());
								eventVO.setEventName(guestEvent.getEventName());
								eventVO.setDescription(guestEvent.getEventDescription());
								eventVO.setEventTimeNDate(guestEvent.getEventTimeNDate());
								eventVO.setGuestStatus(guest.getStatus().toString());
								eventVO.setGuestId(guest.getGuestListId());
								LoginInfo loginInfoAdmin = guestEvent.getLoginInfo();
								String hql = "FROM com.pFoods.dbo.userProfile.profileModal.UserProfile WHERE loginInfo=?";
								if(loginInfoAdmin!=null){
									/*
									 *Create AdminVO 
									 */
									 List<UserProfile> recordList= (List<UserProfile>)session.createQuery(hql).setParameter(0,loginInfoAdmin)
											 .list();
									 if(recordList!=null && recordList.size()>0){
										 for(UserProfile userProfile:recordList){
											 if(userProfile!=null){
												 AdminVO adminVO = new AdminVO();
												 adminVO.setName(userProfile.getFirstname()+" "+userProfile.getLastName());
												 adminVO.setPhoneNumber(userProfile.getPhone_number());
												 eventVO.setAdminVO(adminVO);
												 break;
											 }
										 }
									 }									 
								}
								 List<GuestVO> guestVOList = new ArrayList<GuestVO>();
								 eventVO.setGuestVOList(guestVOList);
								 List<GuestList> currentEventGuestList = guestEvent.getGuestList();
								 if(currentEventGuestList!=null && currentEventGuestList.size()>0){
									 for(GuestList eachGuest :currentEventGuestList){
										 if(eachGuest!=null){
											 LoginInfo eachGuestLoginInfo = eachGuest.getLoginInfo(); 
											 if(eachGuestLoginInfo!=null){
												 
												 List<UserProfile> recordGuestList= (List<UserProfile>)session.createQuery(hql).setParameter(0,eachGuestLoginInfo)
														 .list();
												 if(recordGuestList!=null && recordGuestList.size()>0){
													 for(UserProfile userProfile:recordGuestList){
														 if(userProfile!=null){
															 GuestVO guestVO = new GuestVO();
															 guestVOList.add(guestVO);
															 guestVO.setName(userProfile.getFirstname()+" "+ userProfile.getLastName());
															 guestVO.setPhoneNumber(userProfile.getPhone_number());
															 if(eachGuest.getStatus()==Status.pending){
																 guestVO.setPending(true);
															 }else if(eachGuest.getStatus()==Status.accepted){
																 guestVO.setJoining(true);
															 }else{
																 guestVO.setNotComing(true);
															 }
															 break;
														 }
													 }
												 }
											 }
										 }
									 }
								 }
								 
								 
								 List<NonUserGuestVO> nonUserGuestVOList = new ArrayList<NonUserGuestVO>();
								 eventVO.setNonUserGuestVOList(nonUserGuestVOList);
								 List<InvitedNonUser> invitedNonUserList = guestEvent.getInvitedNonUser();
								 if(invitedNonUserList!=null && invitedNonUserList.size()>0){
									 for(InvitedNonUser invitedNonUser :invitedNonUserList){
										 if(invitedNonUser!=null){
											 NonUserGuestVO nonUserGuestVO = new NonUserGuestVO();
											 nonUserGuestVO.setPhoneNumber(invitedNonUser.getIdentifier());
											 nonUserGuestVOList.add(nonUserGuestVO);
										 }
									 }
								 }
								 
								 RestaurantVO restaurantVO = new RestaurantVO();
								 eventVO.setRestaurantVO(restaurantVO);
								 RestaurantLoginInfo restaurantLoginInfo = guestEvent.getRestaurantLoginInfo();
								 if(restaurantLoginInfo!=null){
									 String restaurantProfileHQL = "FROM com.pFoods.dbo.restaurant.profileModal.RestaurantProfile  WHERE restaurantLoginInfo=?";
										List<RestaurantProfile> restaurantProfileList= (List<RestaurantProfile>)session.createQuery(restaurantProfileHQL).setEntity(0,restaurantLoginInfo).list();
										if(restaurantProfileList!=null && restaurantProfileList.size()>0){
											for(RestaurantProfile restaurantProfile :restaurantProfileList){
												if(restaurantProfile!=null){
													restaurantVO.setRid(restaurantLoginInfo.getUserId());
													restaurantVO.setName(restaurantProfile.getName());
													break;
												}
											}
										}
								 }
								 
								 
								 
							}
						}
					}
				}
				//Successfully created
				ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=EventConstant.SUCCESS; ResultDBOHelper.ERROR=EventConstant.DBO_000;
				
			}else{
				//invalid
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_DBO_101;
			}
		}else{
			//invalid
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_DBO_101;
		}
		return eventListDetailsResponseTO;
	}
	
	
	public void addNewGuest(LoginInfo loginInfo, Event event, Session session){
		if(loginInfo!=null && event!=null && session!=null){
			GuestList guestList = new GuestList();
			guestList.setLoginInfo(loginInfo);
			guestList.setEvent(event);
			guestList.setStatus(Status.pending);
			session.saveOrUpdate(guestList);
		}
	}
	
	public void addNewNonUser(String Identifier, Event event, Session session){
		if(StringUtils.isNotBlank(Identifier)&& event!=null && session!=null){
			InvitedNonUser invitedNonUser = new InvitedNonUser();
			invitedNonUser.setEvent(event);
			invitedNonUser.setIdentifier(Identifier);
			session.saveOrUpdate(invitedNonUser);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ContactBookVO> checkWhoIsUserDBOHelper(ContactBookRequestTO contactBookRequestTO, Session session) {
		List<ContactBookVO> contactBookVOList = new ArrayList<ContactBookVO>();
		List<String> contactBookList = contactBookRequestTO.getContactBook();
		if(contactBookList!=null && contactBookList.size()>0){
				for(String phoneNumber:contactBookList){
					if(StringUtils.isNotBlank(phoneNumber)){
						String hql = "FROM com.pFoods.dbo.userProfile.profileModal.UserProfile WHERE Phone_number=?";
						List<UserProfile> recordList= (List<UserProfile>)session.createQuery(hql).setString(0,phoneNumber).list();
						ContactBookVO contactBookVO = new ContactBookVO();
						if(recordList!=null && recordList.size()>0){
							for(UserProfile userProfile:recordList){
								if(userProfile!=null){
									if(userProfile.getVerifiedStatus()==VerificationType.yes){
										String fullName = userProfile.getFirstname()+" "+userProfile.getLastName();
										byte[] image = userProfile.getProfilePicture();
										contactBookVO.setPhoneNumber(phoneNumber);
										contactBookVO.setProfileImage(image);
										contactBookVO.setUser(true);
										contactBookVO.setUserName(fullName);
										contactBookVOList.add(contactBookVO);
										break;
									}
								}
							}
						}
						if(!contactBookVO.isUser()){
							contactBookVO.setPhoneNumber(phoneNumber);
							contactBookVOList.add(contactBookVO);
						}
					}
				}
				//Successfully created
				ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=EventConstant.SUCCESS; ResultDBOHelper.ERROR=EventConstant.DBO_000;
			
		}else{
			//invalid
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_DBO_101;
		}
		return contactBookVOList;
	}

	public void updateStatusDBOHelper(UpdateStatusRequestTO updateStatusRequestTO, Session session) {
		String uid = updateStatusRequestTO.getUid(); 
		String status = updateStatusRequestTO.getStatus();
		long eventId= updateStatusRequestTO.getEventID();
		LoginInfo loginInfo = (LoginInfo)session.get(LoginInfo.class,uid);
		Event event = (Event)session.get(Event.class,eventId);
		if(loginInfo!=null && event!=null){
			String hql = "FROM com.pFoods.dbo.event.modal.GuestList WHERE event=? and loginInfo=?";
			Query q = session.createQuery(hql);
			q.setParameter(0, event);
			q.setParameter(1, loginInfo);			
			@SuppressWarnings({"unchecked" })
			List<GuestList> recordList= (List<GuestList>)q.list();
			if(recordList!=null && recordList.size()>0){
				for(GuestList guestList :recordList){
					if(guestList!=null){
						if(StringUtils.endsWithIgnoreCase("accepted", status)){
							guestList.setStatus(Status.accepted);
						}else if(StringUtils.endsWithIgnoreCase("rejected", status)){
							guestList.setStatus(Status.rejected);
						}
						session.saveOrUpdate(guestList);
						break;
					}
				}
			}
		}
		
		
	}

}
