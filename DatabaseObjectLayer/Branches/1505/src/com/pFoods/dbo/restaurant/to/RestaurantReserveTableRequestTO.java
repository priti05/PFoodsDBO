package com.pFoods.dbo.restaurant.to;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RestaurantReserveTableRequestTO {

	private String uid;
	private String rid;
	private String dataAndTimeOfReservation;
	private Date timeStamp;
	private int numberOfPerson;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public Date getDataAndTimeOfReservation() {
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd,HH:mm:ss zzz yyyy");
		Date date=null;
		try {
			date = formatter.parse(dataAndTimeOfReservation);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	public void setDataAndTimeOfReservation(String dataAndTimeOfReservation) {
		this.dataAndTimeOfReservation = dataAndTimeOfReservation;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getNumberOfPerson() {
		return numberOfPerson;
	}
	public void setNumberOfPerson(int numberOfPerson) {
		this.numberOfPerson = numberOfPerson;
	}
	
	
	
	
}
