package com.boum_ivan.nightlifeapp.Log_In_Sign_Up;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by ivan on 28/04/2015.
 */
public class LoginAsyncTask extends AsyncTask<String[], Void, Boolean>{

    private Context context;
    private OnLoginRequestComplete loginRequestComplete;

    public LoginAsyncTask(Context context){
        this.context = context;
        loginRequestComplete = (OnLoginRequestComplete)context;
    }

    protected Boolean doInBackground(String[]...parameters){

        String[] loginParameters = parameters[0];
        String username = loginParameters[0];
        String password = loginParameters[1];
        boolean validLogin = false;

        JSONObject loginObject = new JSONObject();
        try {
            loginObject.put("username", username);
            loginObject.put("password", password);

            //Server Simulation, simulates serverIp has been set, connection gotten etc
            JSONObject serverResponse = ServerSimulation.loginRequest(loginObject);
            validLogin = serverResponse.getBoolean("loginResponse");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return validLogin;
    }

    protected void onPostExecute(Boolean serverResponse){
        loginRequestComplete.onLoginRequestComplete(serverResponse);
    }
}
