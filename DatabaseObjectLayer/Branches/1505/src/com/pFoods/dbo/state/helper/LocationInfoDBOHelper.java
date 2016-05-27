package com.pFoods.dbo.state.helper;

import java.util.List;

import org.hibernate.Session;

import com.pFoods.dbo.state.StateModal.State;


public class LocationInfoDBOHelper {

	@SuppressWarnings("unchecked")
	public List<State> retrieveStateListHelper(Session session) {
		String hql = "FROM com.pFoods.dbo.state.StateModal.State";
		return (List<State>)session.createQuery(hql).list(); 
	}		
		
		
	

}
