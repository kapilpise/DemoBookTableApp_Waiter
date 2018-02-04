package com.smartdatainc.dataobject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anurag Sethi
 * The class handles all the objects associated with the User entity
 */
public class User {

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("PassWord")
    @Expose
    private String passWord;
    @SerializedName("LastName")
    @Expose
    private String lastName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
