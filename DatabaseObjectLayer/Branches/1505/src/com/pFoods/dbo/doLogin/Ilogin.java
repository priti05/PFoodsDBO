package com.pFoods.dbo.doLogin;

import com.pFoods.dbo.doLogin.to.CheckVerifiedStatusRequestTO;

public interface Ilogin {
	/**
	 * It takes userID. If success then return password; ResultDBOHelper.SUCCESS = true;
	 * RESULT = "success"; ERROR = " ";
	 * Otherwise null with ResultDBOHelper.
	 * @author Priti Patel
	 * @param userID, password
	 * @exception LGN_DBO_100 user id not in database;
	              LGN_DBO_101 invalid entered data;
				  LGN_GEN_111 Generic error;
	 */
	public boolean retrievePassword(String userID, String password);
	
	public boolean checkVerifiedStatus(CheckVerifiedStatusRequestTO checkVerifiedStatusRequestTO);
}
