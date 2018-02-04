package com.smartdatainc.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by aniketraut on 24/1/18.
 */

public class SharedPreferenceUtils {

    public static final String SHARED_PREFERENCE_NAME = "FoodOrder";
    private static final String isLogin = "isLogin";
    public static final String SP_KEY_USER_ID = "UserID";
    public static boolean getIsLogin(Context context) {

        return Utility.readDataInSharedPreferences(context, SHARED_PREFERENCE_NAME,
                MODE_PRIVATE,
                isLogin, false);
    }

    public static void setIsLogin(Context context, boolean stripeAccountStatus) {

        Utility.saveDataInSharedPreferences(context, SHARED_PREFERENCE_NAME,
                MODE_PRIVATE,
                isLogin, stripeAccountStatus);
    }

    public static String getUserId(Context context) {

        String userId = Utility.readDataInSharedPreferences(context, SHARED_PREFERENCE_NAME,
                MODE_PRIVATE,
                SP_KEY_USER_ID);

        return userId;
    }

    public static void setUserId(Context context, String userId) {

        Utility.saveDataInSharedPreferences(context, SHARED_PREFERENCE_NAME,
                MODE_PRIVATE,
                SP_KEY_USER_ID, userId);
    }

}
