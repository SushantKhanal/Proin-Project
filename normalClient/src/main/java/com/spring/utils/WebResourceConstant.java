package com.spring.utils;

import org.omg.CORBA.PUBLIC_MEMBER;

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

    public interface SignInCtrl {
        public static final String SIGN_IN_BASE = "/signIn";
        public static final String FETCH_NORMAL_USER = "/fetchNormalUser/";

    }

    public interface NormalAccountCtrl {
        public static final String Normal_ACCOUNT_BASE = "/normalAccount";
        public static final String UPDATE_USER = "/updateUser/";
        public static final String UPDATE_PROFILE_PIC = "/updateProfilePic/";
        public static final String FETCH_PROFILE_PIC = "/fetchProfilePic/";
        public static final String GET_COUNTRIES = "/getCountries/";
        public static final String SEARCH_PRO_USERS = "/getMatchedProUsers/";
        public static final String FETCH_PRO_USER = "/getProUserProfile/";
        public static final String LOAD_FAV_USERNAMES = "/loadFavUsernames/";
        public static final String FETCH_FOLLOWINGS = "/fetchFollowings/";
        public static final String SEND_CUSTOM_EMAIL = "/sendCustomEmail/";
//        ##########################################################################
        public static final String FETCH_VALUE = "/fetchValue/";

    }

    public interface ViewProAccountCtrl {
        public static final String VIEW_PRO_ACCOUNT_BASE = "/viewProAccount";
        public static final String FETCH_PRO_PROFILE_PIC = "/getProProfilePic/";
        public static final String FETCH_TAGS = "/receiveTags/";
        public static final String FETCH_ALL_EXPERIENCE = "/getAllExperience/";
        public static final String FETCH_ALL_ACADEMICS = "/getAllAcademics/";
        public static final String FETCH_REVIEWS = "/getReviews/";
        public static final String NORMAL_SENDS_PRO_FAV = "/sendFav/";
        public static final String NORMAL_DELETES_PRO_Fav = "/deleteFav/";
        public static final String NORMAL_CHECKS_PRO_FAV = "/checkIfFav/";
        public static final String POST_REVIEW = "/writeReview/";
        public static final String SEND_FOLLOW_REQUEST = "/sendFollowRequest/";
        public static final String CHECK_IF_FOLLOWED = "/checkIfFollowed/";
        public static final String UNFOLLOW = "/unFollow/";
    }


}
