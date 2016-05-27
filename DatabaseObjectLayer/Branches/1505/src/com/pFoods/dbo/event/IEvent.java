package com.pFoods.dbo.event;

import java.util.List;

import com.pFoods.dbo.event.to.ContactBookRequestTO;
import com.pFoods.dbo.event.to.CreateEventRequestTO;
import com.pFoods.dbo.event.to.EventListDetailsRequestTO;
import com.pFoods.dbo.event.to.EventListDetailsResponseTO;
import com.pFoods.dbo.event.to.UpdateStatusRequestTO;
import com.pFoods.dbo.event.vo.ContactBookVO;



public interface IEvent {
	public void createEvent(CreateEventRequestTO createEventRequestTO);
	public EventListDetailsResponseTO retrieveEventList(EventListDetailsRequestTO eventListDetailsRequestTO);
	public List<ContactBookVO> checkWhoIsUser(ContactBookRequestTO ContactBookRequestTO);
	public void updateStatus(UpdateStatusRequestTO updateStatusRequestTO);
}
