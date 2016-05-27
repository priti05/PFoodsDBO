package com.pFoods.dbo.state;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.dbo.singleton.SessionFactoryBuilderSinglton;
import com.pFoods.dbo.state.StateModal.State;
import com.pFoods.dbo.state.constants.LocationInfoDBOConstant;
import com.pFoods.dbo.state.helper.LocationInfoDBOHelper;


public class LocationInfoDBO implements ILocationInfoDBO {

	@Override
	public List<State> retrieveStateList() {
		Session session=null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(session!=null){
				session.beginTransaction();
				LocationInfoDBOHelper locationInfoDBOHelper = new LocationInfoDBOHelper();
				List<State> stateList = locationInfoDBOHelper.retrieveStateListHelper(session);
				if(stateList!=null && stateList.size()>0){
					ResultDBOHelper.SUCCESS = true; ResultDBOHelper.RESULT=LocationInfoDBOConstant.SUCCESS; ResultDBOHelper.ERROR=LocationInfoDBOConstant.DBO_000;
				}
				session.getTransaction().commit();
				return stateList;
			}else{
				//System down (generic error)
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=LocationInfoDBOConstant.FAILURE; ResultDBOHelper.ERROR=LocationInfoDBOConstant.LCT_GEN_111;
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=LocationInfoDBOConstant.FAILURE; ResultDBOHelper.ERROR=LocationInfoDBOConstant.LCT_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return null;
	}

}
