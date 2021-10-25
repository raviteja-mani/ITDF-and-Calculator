package com.example.employeedetails;

import android.content.SharedPreferences;

import com.example.employeedetails.ModalClasses.Otherimcome;

public class SharedPreferencesOSLiveData extends SharedPreferenceLiveData<Otherimcome> {
    public SharedPreferencesOSLiveData(SharedPreferences prefs, String key, Otherimcome defValue) {
        super(prefs, key, defValue);
    }

    @Override
    Otherimcome getValueFromPreferences(String key, Otherimcome defValue) {
        return null;
    }
}
