package com.smartdatainc.utils;

/**
 * Created by Anurag Sethi
 * The class handles the constants used in the application
 */
public class Constants {

    /**
     * Handles the SplashScreen constants
     */
    public static class SplashScreen {
        /**
         * The parameter is used to manage the splash screen delay
         */
        public  static int SPLASH_DELAY_LENGTH = 2000;
    }


    /**
     * Handles webservice constants
     */
    public static class WebServices {

        public static String WS_IMAGE_BASE_URL = "http://108.168.203.227:9100/Content/";
        public static String WS_BASE_URL = "http://108.168.203.227/bookmytable/api/Hotel/";
        public static final String WS_USER_AUTHENTICATION =  "GetUserDetailsWithSuccesLogin";
        public static final String WS_WAITER_LIST =  "GetOrderDetailsForApproval";
        public static int CANCEL_ORDER = 306;
        public static final int ACCEPT_ORDER = 307;
        public static final String WS_POST_CANCEL_ORDER = "CancelOrder";
        public static final String WS_POST_UPDATE_ORDER = "UpdateExistingOrder";

    }

    /**
     * Handles the TaskIDs so as to differentiate the web service return values
     */

    public static class TaskID {

        public static int LOGIN_TASK_ID = 100;
        public static int FORGOT_PASSWORD_TASK_ID = 101;
        public static int WAITER_LIST = 102;
        public static final int REJECT_ORDER = 103;
        public static final int ACCEPT_ORDER = 104;

        
    }

    /**
     * Handles the ButtonTags so as to differentiate them in showAlertDialog()
     */

    public static class ButtonTags {

        public static String TAG_NETWORK_SERVICE_ENABLE = "network services";
        
    }

    /**
     * Handles the JSON Parsing
     */             
    public static class JsonParsing {
        public static int PARSING_JSON_FOR_MESSAGE_ID = 1;
        public static int PARSING_JSON_FOR_RESULT = 2;
    }

    /**
     * Handles the DebugLog constants
     */
    public static class DebugLog {
        /**
         * Will be the name of the project
         */
        public static String APP_TAG = "fudo waiter";
        /**
         * APP_MODE = 1 means Debug Mode
         * APP_MODE = 0 means Live Mode
         * Must change to 0 when going live
         */
        public static int APP_MODE = 1;
        /**
         * Name of the directory in which log file needs to be saved
         */
        public static String APP_ERROR_DIR_NAME = "fudo waiter"; 
        /**
         * Name of the log file
         */
        public static String APP_ERROR_LOG_FILE_NAME = "log.txt";
    }
    
    /**
     * Handles the constant for Google Play Services
     */
    public static class GooglePlayService {
        public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    }
    
    /**
     * Handles the requestCodes
     */
    public static class RequestCodes {
    
        
    }

    
    
    
    
    
    
    
}
