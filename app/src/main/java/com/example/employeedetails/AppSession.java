package com.example.employeedetails;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Base64;

import com.example.employeedetails.ModalClasses.User;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

//all about app data
public class AppSession {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefsEditor;
    private String regimeType;
    private User user;
    public AppSession(Context context) {
        this.context=context;
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
//        sharedPreferences= context.getSharedPreferences("employeeDetails",Context.MODE_PRIVATE);
        this.prefsEditor = sharedPreferences.edit();
        regimeType=sharedPreferences.getString("regimeType",null);
        user=new User();
//        user.setFirst_name("Akula ");
//        user.setLast_name("Ravi Teja");
//        user.setCompanyName("Ascent consulting");
    }
    public String getUserName(){
        return getUser().getUsername();
    }
    public String getUserPassword(){
        return sharedPreferences.getString("userPassword",null);
    }
    public void logOut(){
        prefsEditor.putString("username",null).commit();
        prefsEditor.putString("userPassword",null).commit();
        prefsEditor.putString("regimeType",null).commit();
        prefsEditor.putString("currentUser",null).commit();
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

    public void setImageBitmap(Bitmap map) {
        prefsEditor.putString("ProfileImage",BitMapToString(map)).commit();

    }
    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    public Bitmap getProfileBitmap(){
            Bitmap val= StringToBitMap(sharedPreferences.getString("ProfileImage", BitMapToString(BitmapFactory.decodeResource(context.getResources(),R.drawable.profile))));
        return val;
    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return BitmapFactory.decodeResource(context.getResources(),R.drawable.profile);
        }
    }

    public User getUser() {
        Gson gson=new Gson();
        return gson.fromJson(sharedPreferences.getString("currentUser",gson.toJson(new User())),User.class);
    }
    public void setUser(User users){
        Gson gson=new Gson();
        System.out.println(users.getCompanyName());
        prefsEditor.putString("currentUser",gson.toJson(users)).commit();

    }
    public boolean isLoggedIn(){
        return sharedPreferences.getString("currentUser",null)==null?false:true;
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        return isConnected;
    }
}
