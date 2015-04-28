package com.boum_ivan.nightlifeapp.Log_In_Sign_Up;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.boum_ivan.nightlifeapp.R;


public class LoginSignUpMainActivity extends ActionBarActivity {

    private LoginSignUpActions loginSignUpActions;
    private Intent nextActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_sign_up);

        Button loginBtn = (Button)findViewById(R.id.loginBtn);
        Button signUpBtn = (Button)findViewById(R.id.signUpBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSignUpActions = LoginSignUpActions.LOG_IN;
                buttonHandler(loginSignUpActions);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSignUpActions = LoginSignUpActions.SIGN_UP;
                buttonHandler(loginSignUpActions);
            }
        });
    }


    public void buttonHandler(LoginSignUpActions loginSignUpActions){
        switch(loginSignUpActions){
            case LOG_IN:
                nextActivity = new Intent(this, LoginActivity.class);
                break;
            case SIGN_UP:
                nextActivity = new Intent(this, SignUpActivity.class);
                break;
        }

        startNextActivity();
    }

    public void startNextActivity(){
        startActivity(nextActivity);
    }

}
