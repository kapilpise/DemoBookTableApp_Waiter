package com.smartdatainc.interfaces;



import com.smartdatainc.dataobject.CancelOrderRequestModel;
import com.smartdatainc.dataobject.User;

import com.smartdatainc.dataobject.WaiterModel;
import com.smartdatainc.utils.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ashutoshm on 19/7/16.
 * The interface method implemented in the java(Manager) files
 */
public interface ApiInterface {

    
    /**
     * For post method  use @POST
     *  @param Web services url
     *  @Body User model class data
     * @return
     */

    @GET(Constants.WebServices.WS_USER_AUTHENTICATION)
    Call<User> loginUser( @Query("userName") String userName,
                          @Query("Password") String Password);

    @GET(Constants.WebServices.WS_WAITER_LIST)
    Call<ArrayList<WaiterModel>> getMenuCardDetails(@Query("employeeId") String employeeId);



    @POST(Constants.WebServices.WS_POST_CANCEL_ORDER)
    Call <String> cancelOrder(@Body CancelOrderRequestModel cancelOrderRequestModel);

    @POST(Constants.WebServices.WS_POST_UPDATE_ORDER)
    Call <String> acceptOrder(@Body WaiterModel responseModel);

    
}