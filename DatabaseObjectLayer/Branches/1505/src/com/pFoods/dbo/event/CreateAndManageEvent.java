package com.pFoods.dbo.event;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pFoods.dbo.event.constant.EventConstant;
import com.pFoods.dbo.event.helper.EventHelper;
import com.pFoods.dbo.event.to.ContactBookRequestTO;
import com.pFoods.dbo.event.to.CreateEventRequestTO;
import com.pFoods.dbo.event.to.EventListDetailsRequestTO;
import com.pFoods.dbo.event.to.EventListDetailsResponseTO;
import com.pFoods.dbo.event.to.UpdateStatusRequestTO;
import com.pFoods.dbo.event.vo.ContactBookVO;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.dbo.singleton.SessionFactoryBuilderSinglton;

public class CreateAndManageEvent implements IEvent {

	@Override
	public void createEvent(CreateEventRequestTO createEventRequestTO) {
		Session session=null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(createEventRequestTO!=null && session!=null){
				session.beginTransaction();
				EventHelper eventHelper = new EventHelper();
				eventHelper.createEventHelperDBO(createEventRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_DBO_101;
				
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_GEN_111;
			try{
				session.getTransaction().rollback();
    		}catch(RuntimeException rbe){
    			
    		}
    		throw e;
		}finally{
			if(session!=null){
				session.close();
			}			
		}
		

	}

	@Override
	public EventListDetailsResponseTO retrieveEventList(EventListDetailsRequestTO eventListDetailsRequestTO) {
		Session session=null;
		EventListDetailsResponseTO eventListDetailsResponseTO = null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(eventListDetailsRequestTO!=null && session!=null){
				session.beginTransaction();
				EventHelper eventHelper = new EventHelper();
				eventListDetailsResponseTO = eventHelper.everetrieveEventListDBOHelper(eventListDetailsRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_DBO_101;
				
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_GEN_111;
			try{
				session.getTransaction().rollback();
    		}catch(RuntimeException rbe){
    			
    		}
    		throw e;
		}finally{
			if(session!=null){
				session.close();
			}			
		}
		return eventListDetailsResponseTO;
	}

	@Override
	public List<ContactBookVO> checkWhoIsUser(ContactBookRequestTO contactBookRequestTO) {
		Session session=null;
		List<ContactBookVO>  ContactBookVOList= null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(contactBookRequestTO!=null && session!=null){
				session.beginTransaction();
				EventHelper eventHelper = new EventHelper();
				ContactBookVOList = eventHelper.checkWhoIsUserDBOHelper(contactBookRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_DBO_101;
				
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_GEN_111;			
		}finally{
			if(session!=null){
				session.close();
			}			
		}
		return ContactBookVOList;
	}

	@Override
	public void updateStatus(UpdateStatusRequestTO updateStatusRequestTO) {
		Session session=null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(updateStatusRequestTO!=null && session!=null){
				session.beginTransaction();
				EventHelper eventHelper = new EventHelper();
				eventHelper.updateStatusDBOHelper(updateStatusRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_DBO_101;
				
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=EventConstant.FAILURE; ResultDBOHelper.ERROR=EventConstant.EVT_GEN_111;			
		}finally{
			if(session!=null){
				session.close();
			}			
		}
		
	}
		
	

}
