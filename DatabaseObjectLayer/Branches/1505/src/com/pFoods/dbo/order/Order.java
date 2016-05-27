package com.pFoods.dbo.order;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pFoods.dbo.admin.to.AdminResponseTo;
import com.pFoods.dbo.order.constant.OrderConstant;
import com.pFoods.dbo.order.helper.OrderHelper;
import com.pFoods.dbo.order.to.CheckInRequestTO;
import com.pFoods.dbo.order.to.OrderRequestTO;
import com.pFoods.dbo.order.to.OrderResponseTO;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.dbo.singleton.SessionFactoryBuilderSinglton;

public class Order implements IOrder {

	@Override
	public OrderResponseTO enterOrder(OrderRequestTO orderRequestTO) {
		Session session=null;
		OrderResponseTO orderResponseTO= null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(session!=null){
				session.beginTransaction();
				OrderHelper orderHelper = new OrderHelper();
				orderResponseTO = orderHelper.enterOrderDBOHelper(orderRequestTO, session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_DBO_101;
				
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_GEN_111;
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
		return orderResponseTO;

	}

	@Override
	public AdminResponseTo adminInfoDBO() {
		Session session=null;
		AdminResponseTo adminResponseTo= null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(session!=null){
				session.beginTransaction();
				OrderHelper orderHelper = new OrderHelper();
				adminResponseTo = orderHelper.adminInfoDBOHelper(session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_DBO_101;
				
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}			
		}
		return adminResponseTo;
	}

	@Override
	public void checkInDBO(CheckInRequestTO checkInRequestTO) {
		Session session=null;
		try{
			ResultDBOHelper.clearResult();
			SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
			session = sessionFactory.openSession();
			if(session!=null){
				session.beginTransaction();
				OrderHelper orderHelper = new OrderHelper();
				orderHelper.checkInDBOHelper(checkInRequestTO , session);
				session.getTransaction().commit();
			}else{
				//invalid input
				ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_DBO_101;				
			}	
		}catch(Exception e){
			//System down (generic error)
			ResultDBOHelper.SUCCESS = false; ResultDBOHelper.RESULT=OrderConstant.FAILURE; ResultDBOHelper.ERROR=OrderConstant.ORD_GEN_111;
		}finally{
			if(session!=null){
				session.close();
			}			
		}
		
	}

}
