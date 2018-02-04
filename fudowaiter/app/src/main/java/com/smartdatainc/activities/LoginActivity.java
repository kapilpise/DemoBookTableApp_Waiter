package com.smartdatainc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartdatainc.dataobject.AppInstance;
import com.smartdatainc.interfaces.DialogActionCallback;
import com.smartdatainc.interfaces.ServiceRedirection;
import com.smartdatainc.dataobject.User;
import com.smartdatainc.managers.LoginManager;
import com.smartdatainc.fudowaiter.R;
import com.smartdatainc.utils.Constants;
import com.smartdatainc.utils.SharedPreferenceUtils;
import com.smartdatainc.utils.Utility;


/**
 * Created by Anurag Sethi
 * The activity is used for handling the login screen actions
 */
public class LoginActivity extends AppActivity implements ServiceRedirection {

    private String email;
    private String password;
    private EditText emailObj;
    private EditText passwordObj;
    private Button btnLoginObj;
    private Button btnSignUpObj;
    private TextView textViewObj;
    private Utility utilObj;
    private String message;
    private User userObj;
    private LoginManager loginManagerObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.login);

        initData();
        bindControls();

    }


    /**
     * Default method of activity life cycle to handle the actions required once the activity starts
     * checks if the network is available or not
     *
     * @return none
     */

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        if (!isNetworkAvailable(this)) {
            //utilObj.showAlertDialog(this,this.getResources().getString(R.string.network_service_message_title),this.getResources().getString(R.string.network_service_message), this.getResources().getString(R.string.Ok), null, Constants.ButtonTags.TAG_NETWORK_SERVICE_ENABLE, 0);


            utilObj.showAlertDialog(this, R.string.network_service_message_title, R.string.network_service_message, R.string.ok, 0, new DialogActionCallback() {

                @Override
                public void doOnPositive() {
                    // Do what needs to be done on click of positive button
                    utilObj.showToast(getApplicationContext(), getResources().getString(R.string.network_service_message), 2);


                }

                @Override
                public void doOnNegative() {
                    // Do what needs to be done on click of negative button
                }
            });


        }


    }

    /**
     * Default activity life cycle method
     */
    @Override
    public void onStop() {
        super.onStop();


    }


    /**
     * Initializes the objects
     *
     * @return none
     */
    @Override
    public void initData() {
        emailObj = (EditText) findViewById(R.id.email);
        passwordObj = (EditText) findViewById(R.id.password);
        btnLoginObj = (Button) findViewById(R.id.btnSignIn);
        btnSignUpObj = (Button) findViewById(R.id.btnSignup);
        textViewObj = (TextView) findViewById(R.id.errorMessage);
        utilObj = new Utility(this);
        userObj = new User();
        loginManagerObj = new LoginManager(this, this);


    }

    /**
     * Binds the UI controls
     *
     * @return none
     */
    @Override
    public void bindControls() {

        //Login Button click
        btnLoginObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailObj.getText().toString();
                password = passwordObj.getText().toString();

                if (validatingRequired()) {

                    utilObj.startLoader(LoginActivity.this, R.drawable.image_for_rotation);

                    //assigning the data to the user object
                    userObj.setUserName(email);
                    userObj.setPassWord(password);
                    loginManagerObj.authenticateLogin(userObj);
                }

            }
        });

        //SignUp
        btnSignUpObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentObj = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intentObj);
            }
        });


    }


    /**
     * The method is used to validate the required fields
     *
     * @return boolean true if fields are validated else false
     **/

    private boolean validatingRequired() {
        message = "";
        email = emailObj.getText().toString();
        password = passwordObj.getText().toString();

        //validate the content
        if (email.isEmpty()) {
            message = getResources().getString(R.string.EmailErrorMessage);
            utilObj.showError(this, message, textViewObj, emailObj);
        } else if (password.isEmpty()) {
            message = getResources().getString(R.string.PasswordErrorMessage);
            utilObj.showError(this, message, textViewObj, passwordObj);
        }

        if (message.equalsIgnoreCase("") || message == null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * The method handles the result from the Facebook
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }


    /**
     * The interface method implemented in the java files
     *
     * @param taskID the id based on which the relevant action is performed
     * @return none
     */
    @Override
    public void onSuccessRedirection(int taskID) {
        utilObj.stopLoader();

        if (taskID == Constants.TaskID.LOGIN_TASK_ID) {
            //call the intent for the next activity
            SharedPreferenceUtils.setUserId(this, AppInstance.userObj.getUserId());
            SharedPreferenceUtils.setIsLogin(this, true);
            Intent intentObj = new Intent(this, WaiterDashBoard.class);
            startActivity(intentObj);
        }
    }

    /**
     * The interface method implemented in the java files
     *
     * @param errorMessage the error message to be displayed
     * @return none
     */
    @Override
    public void onFailureRedirection(String errorMessage) {
        utilObj.stopLoader();
        utilObj.showError(this, errorMessage, textViewObj, null);
    }


}
