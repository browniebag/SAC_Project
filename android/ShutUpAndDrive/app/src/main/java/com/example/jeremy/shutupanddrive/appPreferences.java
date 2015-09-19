package com.example.jeremy.shutupanddrive;

import android.os.Bundle;
import android.preference.PreferenceActivity;
/**
 * Created by Frank on 9/18/2015.
 */
public class appPreferences extends PreferenceActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
