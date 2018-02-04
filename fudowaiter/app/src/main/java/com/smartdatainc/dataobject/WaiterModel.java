package com.smartdatainc.dataobject;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aniketraut on 24/1/18.
 */

public class WaiterModel implements Serializable {

    @SerializedName("CustomerID")
    @Expose
    private Integer customerID;
    @SerializedName("UserType")
    @Expose
    private String userType;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    @SerializedName("TableID")
    @Expose
    private Integer tableId;

    @SerializedName("TotalQuantity")
    @Expose
    private Integer TotalQuantity;

    @SerializedName("OrderItemDetails")
    @Expose
    private ArrayList<OrderItemDetail> orderItemDetails = null;
    @SerializedName("OrderID")
    @Expose
    private Integer orderID;
    @SerializedName("HotelId")
    @Expose
    private Integer hotelId;
    @SerializedName("DishId")
    @Expose
    private Integer dishId;
    @SerializedName("IsApproveStatus")
    @Expose
    private Integer isApproveStatus;
    @SerializedName("ApprovalName")
    @Expose
    private Object approvalName;
    @SerializedName("TotalAmount")
    @Expose
    private float totalAmount;
    @SerializedName("OrderPaymentID")
    @Expose
    private Integer orderPaymentID;

    protected WaiterModel(Parcel in) {
        if (in.readByte() == 0) {
            customerID = null;
        } else {
            customerID = in.readInt();
        }
        if (in.readByte() == 0) {
            orderID = null;
        } else {
            orderID = in.readInt();
        }
        if (in.readByte() == 0) {
            hotelId = null;
        } else {
            hotelId = in.readInt();
        }
        if (in.readByte() == 0) {
            dishId = null;
        } else {
            dishId = in.readInt();
        }
        if (in.readByte() == 0) {
            isApproveStatus = null;
        } else {
            isApproveStatus = in.readInt();
        }
        if (in.readByte() == 0) {
            totalAmount = 0;
        } else {
            totalAmount = in.readInt();
        }
        if (in.readByte() == 0) {
            orderPaymentID = null;
        } else {
            orderPaymentID = in.readInt();
        }
    }



    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public ArrayList<OrderItemDetail> getOrderItemDetails() {
        return orderItemDetails;
    }

    public void setOrderItemDetails(ArrayList<OrderItemDetail> orderItemDetails) {
        this.orderItemDetails = orderItemDetails;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getIsApproveStatus() {
        return isApproveStatus;
    }

    public void setIsApproveStatus(Integer isApproveStatus) {
        this.isApproveStatus = isApproveStatus;
    }

    public Object getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(Object approvalName) {
        this.approvalName = approvalName;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getOrderPaymentID() {
        return orderPaymentID;
    }

    public void setOrderPaymentID(Integer orderPaymentID) {
        this.orderPaymentID = orderPaymentID;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
