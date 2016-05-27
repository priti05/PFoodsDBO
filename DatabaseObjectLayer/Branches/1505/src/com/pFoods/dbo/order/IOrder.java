package com.pFoods.dbo.order;

import com.pFoods.dbo.admin.to.AdminResponseTo;
import com.pFoods.dbo.order.to.CheckInRequestTO;
import com.pFoods.dbo.order.to.OrderRequestTO;
import com.pFoods.dbo.order.to.OrderResponseTO;


public interface IOrder {
	
	public OrderResponseTO enterOrder(OrderRequestTO orderRequestTO);
	public AdminResponseTo adminInfoDBO();
	public void checkInDBO(CheckInRequestTO checkInRequestTO);

}
