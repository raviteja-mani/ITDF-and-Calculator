package com.example.employeedetails;

import android.content.Context;
import android.content.SharedPreferences;
//all about app data
public class AppSession {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefsEditor;
    private String regimeType;
    public AppSession(Context context) {
        this.context=context;
        sharedPreferences= context.getSharedPreferences("employeeDetails",Context.MODE_PRIVATE);
        this.prefsEditor = sharedPreferences.edit();
        regimeType=sharedPreferences.getString("regimeType",null);
    }
    public String getUserName(){
        return sharedPreferences.getString("username",null);
    }
    public String getUserPassword(){
        return sharedPreferences.getString("userPassword",null);
    }
    public void logOut(){
        prefsEditor.putString("username",null).commit();
        prefsEditor.putString("userPassword",null).commit();
        prefsEditor.putString("regimeType",null).commit();
    }
    public void setUserName(String name){
        prefsEditor.putString("username",name).commit();
    }
    public void setUserPassword(String password){
        prefsEditor.putString("userPassword",password).commit();
    }
    public void setRegimeType(String type){
        prefsEditor.putString("regimeType",type).commit();
        regimeType=type;
    }
    public String getRegimeType(){
//        if(regimeType==null) return "notSelected";
//        else
        return sharedPreferences.getString("regimeType",null);
    }
}
