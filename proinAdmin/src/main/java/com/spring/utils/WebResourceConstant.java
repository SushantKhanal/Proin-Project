package com.spring.utils;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 *         The class stores all the RESTful Web Service's endpoints
 */
public class WebResourceConstant {

    public static final String API_BASE = "/api";

    public interface AdminSetupCtrl {
        public static final String ADMIN_ACCOUNT_BASE = "/adminAccount";
        public static final String FETCH_COUNTRIES = "/getCountries/";
        public static final String CLIENT_ACCOUNT_REQUESTS = "/clientAccountRequests/";
        public static final String FETCH_SEARCH_RESULTS = "/getResults/";
        public static final String FETCH_CLIENT_PROFILE = "/getClientProfile/";
    }


}
