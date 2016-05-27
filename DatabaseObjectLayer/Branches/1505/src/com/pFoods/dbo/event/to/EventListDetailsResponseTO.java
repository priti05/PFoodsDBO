package com.pFoods.dbo.event.to;

import java.util.List;

import com.pFoods.dbo.event.vo.EventVO;

public class EventListDetailsResponseTO {

	private List<EventVO> eventVOList;
	

	public List<EventVO> getEventVOList() {
		return eventVOList;
	}

	public void setEventVOList(List<EventVO> eventVOList) {
		this.eventVOList = eventVOList;
	}
	
	
	
	
}
