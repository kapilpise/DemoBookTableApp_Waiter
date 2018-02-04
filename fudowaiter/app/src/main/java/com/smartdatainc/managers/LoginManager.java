package com.smartdatainc.managers;

import android.content.Context;
import android.widget.Toast;

import com.smartdatainc.async.ApiClient;
import com.smartdatainc.dataobject.AppInstance;
import com.smartdatainc.interfaces.ApiInterface;
import com.smartdatainc.interfaces.ServiceRedirection;
import com.smartdatainc.dataobject.User;
import com.smartdatainc.fudowaiter.R;
import com.smartdatainc.utils.Constants;
import com.smartdatainc.utils.Utility;
import com.smartdatainc.utils.ResponseCodes;

import retrofit2.Call;
import retrofit2.Response;
import sdei.support.lib.interfaces.CallBack;

/**
 * Created by Anurag Sethi
 * The class will handle all the implementations related to the login operations
 */
public class LoginManager implements CallBack{

    Context context;
    Utility utilObj;
    CommunicationManager commObj;
    ServiceRedirection serviceRedirectionObj;
    int tasksID;

      //Retrofit Interface
    ApiInterface apiService;
    String authentication="12345"; // you should change Authentication according to your requirement
    
    
    /**
     * Constructor
     * @param contextObj  The Context from where the method is called
     * @param successRedirectionListener The listener interface for receiving action events
     * @return none
     */
    public LoginManager(Context contextObj, ServiceRedirection successRedirectionListener) {
        context = contextObj;
        utilObj = new Utility(contextObj);
        serviceRedirectionObj = successRedirectionListener;
        
         apiService = ApiClient.createService(ApiInterface.class, authentication);
    }

    /**
     * Calls the Web Service of authenticateLogin
     * @param userObj  having the required data
     * @return none
     */
    public void authenticateLogin(User userObj) {
        commObj = new CommunicationManager(this.context);
        tasksID = Constants.TaskID.LOGIN_TASK_ID;

        Call call = apiService.loginUser(userObj.getUserName(), userObj.getPassWord());
        commObj.CallWebService(this, tasksID, call);
    }

    /**
     * Calls the Web Service of forgot password
     * @param userObj  having the required data
     * @return none
     */
    public void forgotPassword(User userObj) {

    }

    
    /**
     * The interface method implemented in the java files
     *
     * @param data the result returned by the web service
     * @param tasksID the ID to differential multiple webservice calls
     * @param statusCode the statusCode returned by the web service
     * @param message the message returned by the web service
     * @return none
     * @since 2014-08-28
     */


    @Override
    public void onResult(Response data, int tasksID, int statusCode, String message) {


        if(tasksID == Constants.TaskID.LOGIN_TASK_ID) {
            if (data != null) {
                if(statusCode==ResponseCodes.Success) {
                    AppInstance.userObj = (User) data.body();

                    serviceRedirectionObj.onSuccessRedirection(tasksID);
                }else{
                    serviceRedirectionObj.onFailureRedirection(message);
                }
            }
            else{
                serviceRedirectionObj.onFailureRedirection(message);
            }
        }

        else if(tasksID == Constants.TaskID.FORGOT_PASSWORD_TASK_ID) {
            if (data != null) {

                if(statusCode==ResponseCodes.Success) {

                }else{
                    serviceRedirectionObj.onFailureRedirection(message);
                }

            }
            else{
                serviceRedirectionObj.onFailureRedirection(message);
            }

        }
    }
}
