package com.boum_ivan.nightlifeapp.Log_In_Sign_Up;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.boum_ivan.nightlifeapp.Home_Screen.MainActivityHomeScreen;
import com.boum_ivan.nightlifeapp.R;


public class LoginActivity extends ActionBarActivity implements OnLoginRequestComplete{

    private EditText username;
    private EditText password;
    private Button loginBtn;
    private LoginSignUpActions loginSignUpActions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        loginBtn = (Button)findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSignUpActions = LoginSignUpActions.LOG_IN;
                buttonHandler(loginSignUpActions);
            }
        });
    }


    public void buttonHandler(LoginSignUpActions loginSignUpActions){
        switch (loginSignUpActions){
            case LOG_IN:
                getLoginParameters();
                break;
        }
    }

    public void getLoginParameters(){
        String[] loginParameters = new String[2];
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();
        loginParameters[0] = usernameInput;
        loginParameters[1] = passwordInput;

        if(usernameInput.equals("") || passwordInput.equals(""))
            displayMessage(LoginSignUpMessageTypes.ERROR_BLANK_SPACE);
        else{
            new LoginAsyncTask(this).execute(loginParameters);
        }
    }

    public void displayMessage(LoginSignUpMessageTypes messageType){
        switch(messageType){
            case ERROR_BLANK_SPACE:
                Toast.makeText(this,"Error, One or more input fields are empty",Toast.LENGTH_SHORT).show();
                break;
            case ERROR_LOG_IN:
                Toast.makeText(this,"Error, Invalid login parameters",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onLoginRequestComplete(boolean serverResponse){
        if(serverResponse)
            startHomePageActivity();
        else
            displayMessage(LoginSignUpMessageTypes.ERROR_LOG_IN);
    }

    public void startHomePageActivity(){
        Intent intent = new Intent(this, MainActivityHomeScreen.class);
        startActivity(intent);
    }
}
