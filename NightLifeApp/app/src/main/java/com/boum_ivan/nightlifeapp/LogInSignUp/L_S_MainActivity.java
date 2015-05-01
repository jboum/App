package com.boum_ivan.nightlifeapp.LogInSignUp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.boum_ivan.nightlifeapp.R;


public class L_S_MainActivity extends ActionBarActivity {

    private L_S_Actions L_S_Actions;
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
                L_S_Actions = L_S_Actions.LOG_IN;
                buttonHandler(L_S_Actions);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_S_Actions = L_S_Actions.SIGN_UP;
                buttonHandler(L_S_Actions);
            }
        });
    }


    public void buttonHandler(L_S_Actions L_S_Actions){
        switch(L_S_Actions){
            case LOG_IN:
                nextActivity = new Intent(this, L_Activity.class);
                break;
            case SIGN_UP:
                nextActivity = new Intent(this, S_Activity.class);
                break;
        }

        startNextActivity();
    }

    public void startNextActivity(){
        startActivity(nextActivity);
    }

}
