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
        public static final String FETCH_SEARCH_RESULTS = "/getResults";
        public static final String FETCH_CLIENT_PROFILE = "/getClientProfile/";
        public static final String APPROVE_CLIENT_REQUEST = "/approveClientRequest/";
        public static final String DENY_CLIENT_REQUEST = "/denyClientRequest/";
        public static final String FETCH_REQUESTING_CLIENT_INFO = "/getRequestingUserInfo/";
        public static final String FETCH_ADMIN_REQUESTS = "/fetchAdminRequests/";
        public static final String APPROVE_ADMIN_REQUEST = "/approveAdminRequest/";
        public static final String REJECT_ADMIN_REQUEST = "/rejectAdminRequest/";
    }

    public interface SignUpCtrl {
        public static final String SIGNUP_BASE = "/SignUp";
        public static final String CREATE_ADMIN = "/createAdmin";
    }


}
