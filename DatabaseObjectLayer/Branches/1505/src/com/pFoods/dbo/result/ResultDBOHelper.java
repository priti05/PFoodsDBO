package com.pFoods.dbo.result;

public class ResultDBOHelper {

	public static boolean SUCCESS = false;
	public static String RESULT = null;
	public static String ERROR = null;
	
	/**
	 * This will set ResultDBOHelper's values to default,
	 * this should called before each method that you call
	 * from SL. This has to be done to be safe in case of 
	 * error scene in previous call
	 */
	public static void clearResult(){
		SUCCESS=false;
		RESULT=null;
		ERROR=null;
	}
	
	
}
