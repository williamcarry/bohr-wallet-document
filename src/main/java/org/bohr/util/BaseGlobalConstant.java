package org.bohr.util;

public class BaseGlobalConstant {

    public static final String BASE_URL = "http://127.0.0.1:5391/v2.4.0/";
//    public static final String BASE_URL = "http://mainnetapi.bohrweb.org/v2.4.0/";
    public static final String YOUR_API_USERNAME = "YOUR_API_USERNAME1";
    public static final String YOUR_API_PASSWORD = "YOUR_API_PASSWORD1";


    public static final String ACCOUNT_QUERY_URL = BASE_URL + "account?address=";

    public static final String BROADCAST_RAW_TRANSACTION_URL = BASE_URL + "broadcast-raw-transaction?raw=";

    public static final String TRANSACTION_URL = BASE_URL + "transaction?hash=";

    public static final String TRANSACTION_RESULT_URL = BASE_URL + "transaction-result?hash=";


}
