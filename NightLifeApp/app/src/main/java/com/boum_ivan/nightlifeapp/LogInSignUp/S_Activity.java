package com.boum_ivan.nightlifeapp.LogInSignUp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.boum_ivan.nightlifeapp.Home_Screen.MainActivityHomeScreen;
import com.boum_ivan.nightlifeapp.R;


public class S_Activity extends ActionBarActivity implements OnSignUpRequestComplete{


    EditText firstNameInput;
    EditText lastNameInput;
    EditText emailInput;
    EditText password1Input;
    EditText password2Input;

    private String firstName;
    private String lastName;
    private String email;
    private String password1;
    private String password2;

    private L_S_Actions L_S_Actions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstNameInput = (EditText)findViewById(R.id.firstName);
        lastNameInput = (EditText)findViewById(R.id.lastName);
        emailInput = (EditText)findViewById(R.id.email);
        password1Input = (EditText)findViewById(R.id.password);
        password2Input = (EditText)findViewById(R.id.confirmPassword);

        Button signUp = (Button)findViewById(R.id.signUpBtn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_S_Actions = L_S_Actions.SIGN_UP;
                buttonHandler(L_S_Actions);
            }
        });

    }


    public void buttonHandler(L_S_Actions L_S_Actions){
        switch (L_S_Actions){
            case SIGN_UP:
                getInputData();
                validateFields();
                break;
        }
    }

    public void getInputData(){
        firstName = firstNameInput.getText().toString();
        lastName = lastNameInput.getText().toString();
        email = emailInput.getText().toString();
        password1 = password1Input.getText().toString();
        password2 = password2Input.getText().toString();
    }

    public void validateFields(){
        if(firstName.equals("")||lastName.equals("")||email.equals("")||password1.equals("")||password2.equals(""))
            displayMessage(L_S_MessageTypes.ERROR_BLANK_SPACE);
        else if(!(password1.equals(password2)))
            displayMessage(L_S_MessageTypes.ERROR_PASSWORD_MISMATCH);
        else{
               registerUserOnServer();
        }
    }


    public void displayMessage(L_S_MessageTypes messageType){
        switch(messageType){
            case ERROR_BLANK_SPACE:
                Toast.makeText(this,"Error, One or more input fields are be empty",Toast.LENGTH_SHORT).show();
                break;
            case ERROR_PASSWORD_MISMATCH:
                Toast.makeText(this,"Error, Password mismatch occurred",Toast.LENGTH_SHORT).show();
                break;
            case ERROR_SIGN_UP:
                Toast.makeText(this,"An error occurred during Sign Up Process",Toast.LENGTH_SHORT).show();
        }
    }

    public void registerUserOnServer(){
            String[] signUpParameters = new String[4];
            signUpParameters[0] = firstName;
            signUpParameters[1] = lastName;
            signUpParameters[2] = email;
            signUpParameters[3] = password1;

        new S_AsyncTask(this).execute(signUpParameters);
    }


    public void onSignUpRequestComplete(boolean serverResponse){
        if(serverResponse)
            startHomePageActivity();
        else
            displayMessage(L_S_MessageTypes.ERROR_SIGN_UP);

    }

    public void startHomePageActivity(){
        Intent intent = new Intent(this, MainActivityHomeScreen.class);
        startActivity(intent);
    }
}
