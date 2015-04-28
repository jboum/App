package com.boum_ivan.nightlifeapp.Log_In_Sign_Up;

import org.json.JSONObject;

/**
 * Created by ivan on 28/04/2015.
 */
public class ServerSimulation {


    public static JSONObject loginRequest(JSONObject jsonObject){
        JSONObject loginJsonObject = jsonObject;
        JSONObject jsonResponse = new JSONObject();
        String username = "";
        String password = "";

        try {
                username = loginJsonObject.getString("username");
                password = loginJsonObject.getString("password");

                /*Simulates data comparison with database entries
                * returns true in case of success, other wise false*/
                if (username.equals("admin") && password.equals("password"))
                    jsonResponse.put("loginResponse", true);
                 else
                    jsonResponse.put("loginResponse", false);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return jsonResponse;
    }


    public static JSONObject signUpRequest(JSONObject jsonObject){
        JSONObject loginJsonObject = jsonObject;
        JSONObject jsonResponse = new JSONObject();
        String firstName = "";
        String lastName = "";
        String email = "";
        String password = "";

        try {
            firstName = loginJsonObject.getString("firstName");
            lastName = loginJsonObject.getString("lastName");
            email = loginJsonObject.getString("email");
            password = loginJsonObject.getString("password");

            //if storage in database successful, return true, else return false
            return jsonResponse.put("signUpResponse",true);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return jsonResponse;
    }
}
