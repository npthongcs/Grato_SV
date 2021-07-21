package com.example.grato_sv;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.grato_sv.Model.LoginResponse;
import com.google.gson.Gson;


public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "sessionLoginSV";
    final String LOGIN_RESPONSE_KEY = "loginResponseKey";


    private static SessionManagement mInstance = null;

    private SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // khởi tạo mInstance
    public static SessionManagement getInstance(Context context){
        if(mInstance == null){
            mInstance = new SessionManagement(context);
        }
        return mInstance;
    }

//    public SessionManagement(Context context){
//        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//    }

    public void saveSession(LoginResponse loginResponse){
        //save session of user whenever user is logged in
        Gson gson = new Gson();
        String loginResponseJson = gson.toJson(loginResponse);

        editor.putString(LOGIN_RESPONSE_KEY,loginResponseJson).commit();
    }

    public String getSession(){
        //return user token whose session is saved
        return sharedPreferences.getString(LOGIN_RESPONSE_KEY, null);
    }

    public void removeSession(){
        editor.putString(LOGIN_RESPONSE_KEY,null).commit();
    }
}
