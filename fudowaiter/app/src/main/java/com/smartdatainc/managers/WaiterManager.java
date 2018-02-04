package com.smartdatainc.managers;

import android.content.Context;

import com.smartdatainc.async.ApiClient;
import com.smartdatainc.dataobject.AppInstance;
import com.smartdatainc.dataobject.CancelOrderRequestModel;
import com.smartdatainc.dataobject.WaiterModel;
import com.smartdatainc.interfaces.ApiInterface;
import com.smartdatainc.interfaces.ServiceRedirection;
import com.smartdatainc.utils.Constants;
import com.smartdatainc.utils.ResponseCodes;
import com.smartdatainc.utils.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import sdei.support.lib.interfaces.CallBack;

/**
 * Created by aniketraut on 24/1/18.
 */

public class WaiterManager implements CallBack {

    private final static String TAG = WaiterManager.class.getSimpleName();
    Context mContext;
    Utility utilObj;
    CommunicationManager mCommunicationManager;
    ServiceRedirection serviceRedirectionObj;
    int mTasksId;
    //Retrofit Interface
    ApiInterface apiService;
    String authentication = "";// you should change Authentication according to your requirement

    public WaiterManager(Context contextObj, ServiceRedirection successRedirectionListener) {

        mContext = contextObj;
        utilObj = new Utility(contextObj);
        serviceRedirectionObj = successRedirectionListener;
        apiService = ApiClient.createService(ApiInterface.class, authentication);
    }


    public void getHotelList(String employeeId) {

        mCommunicationManager = new CommunicationManager(this.mContext);
        mTasksId = Constants.TaskID.WAITER_LIST;
        Call call = apiService.getMenuCardDetails(employeeId);
        mCommunicationManager.CallWebService(this, mTasksId, call);
    }

    public void rejectOrder(CancelOrderRequestModel cancelOrderRequestModel) {
        mCommunicationManager = new CommunicationManager(this.mContext);
        mTasksId = Constants.TaskID.REJECT_ORDER;
        Call call = apiService.cancelOrder(cancelOrderRequestModel);
        mCommunicationManager.CallWebService(this, mTasksId, call);
    }


    public void acceptOrder(WaiterModel requestModel) {
        mCommunicationManager = new CommunicationManager(this.mContext);
        mTasksId = Constants.TaskID.ACCEPT_ORDER;
        Call call = apiService.acceptOrder(requestModel);
        mCommunicationManager.CallWebService(this, mTasksId, call);
    }

    @Override
    public void onResult(Response data, int tasksID, int statusCode, String message) {

        if (Constants.TaskID.WAITER_LIST == tasksID) {
            if (data != null) {
                if (statusCode == ResponseCodes.Success) {
                    //AppInstance.hotelModal = (HotelModal) data.body();
                    AppInstance.waiterModels = (ArrayList<WaiterModel>) data.body();

                    serviceRedirectionObj.onSuccessRedirection(tasksID);
                } else {
                    serviceRedirectionObj.onFailureRedirection(message);
                }
            } else {
                serviceRedirectionObj.onFailureRedirection(message);
            }
        } else  if (Constants.TaskID.ACCEPT_ORDER == tasksID) {
            if (data != null) {
                if (statusCode == ResponseCodes.Success) {
                    //AppInstance.hotelModal = (HotelModal) data.body();
                    AppInstance.acceptOrderResponse = (String) data.body();

                    serviceRedirectionObj.onSuccessRedirection(tasksID);
                } else {
                    serviceRedirectionObj.onFailureRedirection(message);
                }
            } else {
                serviceRedirectionObj.onFailureRedirection(message);
            }
        }else  if (Constants.TaskID.REJECT_ORDER == tasksID) {
            if (data != null) {
                if (statusCode == ResponseCodes.Success) {
                    //AppInstance.hotelModal = (HotelModal) data.body();
                    AppInstance.rejectResponse = (String) data.body();

                    serviceRedirectionObj.onSuccessRedirection(tasksID);
                } else {
                    serviceRedirectionObj.onFailureRedirection(message);
                }
            } else {
                serviceRedirectionObj.onFailureRedirection(message);
            }
        }
    }

}
