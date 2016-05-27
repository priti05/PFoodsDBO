package com.pFoods.dbo.doLogin.constant;

public class LoginConstants {

	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String DBO_000 = "000";
	public static final String LGN_DBO_100 = "LGN_DBO_100"; //user id not in database
	public static final String LGN_DBO_101 = "LGN_DBO_101"; //invalid entered data
	public static final String LGN_GEN_111 = "LGN_GEN_111"; //any unexpected error (generic error)
	public static final String LGN_DBO_900 = "LGN_DBO_900"; // UID and Pass word don't match
	public static final String LGN_DBO_901 = "LGN_DBO_901"; //soft lock
	public static final String LGN_DBO_902 = "LGN_DBO_902"; //hard lock
	public static final String LGN_DBO_904 = "LGN_DBO_904"; //Warning of soft lock and hard lock
}
