package com.spring.utils;

public class WebResourceConstant {

    public static final String API_BASE = "/api";

    public interface NormalFollowersCtrl {
        public static final String NORMAL_FOLLOWERS_BASE = "/NormalFollowers";
        public static final String FETCH_FOLLOWERS = "/fetchFollowers/";
        public static final String SEND_CUSTOM_EMAIL = "/sendCustomEmail/";
    }

    public interface AccountCtrl {
        public static final String USER_ACCOUNT_BASE = "/user";
        public static final String UPDATE_USER = "/{id}";
        public static final String POST_DOC = "/postDoc/";
        public static final String CHECK_FOR_UPLOADED_DOCS = "/checkForUploadedDocs/";
        public static final String POST_PROFILE_PIC = "/postProfilePic/";
        public static final String GET_PROFILE_PIC = "/getProfilePic/";
        public static final String GET_FAV_USERS = "/getFavUsers/";
        public static final String GET_USER_PROFILE = "/getUserProfile/";
        public static final String SEND_TAGS = "/sendTags/";
        public static final String RECIEVE_TAGS = "/receiveTags/";
        public static final String USER_EXPERIENCE = "/userExperience/";
        public static final String GET_ALL_EXPERIENCE = "/getAllExperience/";
        public static final String USER_ACADEMICS = "/userAcademics/";
        public static final String GET_ALL_ACADEMICS = "/getAllAcademics/";
        public static final String GET_ACADEMIC_FROM_ID = "/getAcademicFromId/";
        public static final String DELETE_THIS_ACADEMICS = "/deleteThisAcademics/";
        public static final String GET_EXPERIENCE_FROM_ID = "/getExperienceFromId/";
        public static final String DELETE_THIS_EXPERIENCE = "/deleteThisExperience/";
        public static final String CHECK_FOLLOW_REQUESTS = "/checkFollowRequests/";
        public static final String ACCEPT_FOLLOW_REQUESTS = "/acceptFollowRequest/";
        public static final String IGNORE_FOLLOW_REQUESTS = "/ignoreFollowRequest/";
        public static final String GET_IGNORED_REQUESTS = "/getIgnoredRequests/";

    }

}
