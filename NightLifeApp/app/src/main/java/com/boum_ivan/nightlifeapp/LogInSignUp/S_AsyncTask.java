package com.boum_ivan.nightlifeapp.LogInSignUp;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by ivan on 28/04/2015.
 */
public class S_AsyncTask extends AsyncTask<String [], Void, Boolean> {

    private Context context;
    private OnSignUpRequestComplete signUpRequestComplete;

    public S_AsyncTask(Context context){
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
