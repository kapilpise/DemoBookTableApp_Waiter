package com.smartdatainc.dataobject;

import com.smartdatainc.utils.Constants;

import java.util.ArrayList;

import debug.LogUtility;


/**
 * Created by Anurag Sethi
 * The class is defined on single instance.
 */
public class AppInstance {

    private static AppInstance appInstance = null;
    public static LogUtility logObj;
    public static User userObj;
    public static ArrayList<WaiterModel> waiterModels;
    public static String acceptOrderResponse = "";
    public static String rejectResponse = "";


    /**
     * To initialize the appInstance Object
     *
     * @return singleton instance
     */

    public static AppInstance getAppInstance() {
        if (appInstance == null) {
            appInstance = new AppInstance();

            /**
             * The object will manage the User information
             */
            userObj = new User();
            waiterModels = new ArrayList<>();


            /**
             * the object will manage the logs in the logcat
             */
            logObj = new LogUtility(Constants.DebugLog.APP_MODE, Constants.DebugLog.APP_TAG);
        }

        return appInstance;
    }

}
