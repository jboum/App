package com.boum_ivan.nightlifeapp.Log_In_Sign_Up;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by ivan on 28/04/2015.
 */
public class SignUpAsyncTask extends AsyncTask<String [], Void, Boolean> {

    private Context context;
    private OnSignUpRequestComplete signUpRequestComplete;

    public SignUpAsyncTask(Context context){
        this.context = context;
        signUpRequestComplete = (OnSignUpRequestComplete)context;
    }

    public Boolean doInBackground(String[]...parameters){

        String[] signUpParameters = parameters[0];
        JSONObject jsonSignUpObj = new JSONObject();

        try{
            jsonSignUpObj.put("firstName",signUpParameters[0]);
            jsonSignUpObj.put("lastName",signUpParameters[1]);
            jsonSignUpObj.put("email",signUpParameters[2]);
            jsonSignUpObj.put("password",signUpParameters[3]);

            JSONObject serverResponse = ServerSimulation.signUpRequest(jsonSignUpObj);

            return serverResponse.getBoolean("signUpResponse");
        }
        catch (Exception e){


        }

        return false;
    }

    public void onPostExecute(Boolean serverResponse){
            signUpRequestComplete.onSignUpRequestComplete(serverResponse);
    }
}
