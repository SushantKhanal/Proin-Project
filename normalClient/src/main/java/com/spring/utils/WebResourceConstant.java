package com.spring.utils;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 *         The class stores all the RESTful Web Service's endpoints
 */

public class WebResourceConstant {

    public static final String API_BASE = "/api";

    public interface SignUpCtrl {
        public static final String SIGN_UP_BASE = "/signUp/";
        public static final String CREATE_USER = "/createUser/";
    }
//api/signIn/fetchNormalUser/
    public interface SignInCtrl {
        public static final String SIGN_IN_BASE = "/signIn";
        public static final String FETCH_NORMAL_USER = "/fetchNormalUser/";

    }

    public interface NormalAccountCtrl {
        public static final String Normal_ACCOUNT_BASE = "/normalAccount";
        public static final String UPDATE_USER = "/updateUser/";
        public static final String UPDATE_PROFILE_PIC = "/updateProfilePic/";

    }


}
