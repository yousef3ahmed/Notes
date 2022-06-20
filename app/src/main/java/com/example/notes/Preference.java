package com.example.notes;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

import androidx.appcompat.app.AppCompatDelegate;


public class Preference extends PreferenceActivity {

    public static SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
        Load_setting();
    }




    private void Load_setting( ){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SwitchPreference OnOff = (SwitchPreference) findPreference("theme_preference");
        boolean isChecked = sharedPreferences.getBoolean("theme_preference", false);

        if (isChecked) {
            getListView().setBackgroundColor(Color.parseColor("#222222"));
        } else {
            getListView().setBackgroundColor(Color.parseColor("#ffffff"));
        }


        OnOff.setOnPreferenceChangeListener(new android.preference.Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(android.preference.Preference preference, Object newValue) {
                boolean isChecked = (boolean) newValue;

                if (isChecked) {
                    OnOff.setSummary("Enabled");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    getListView().setBackgroundColor(Color.parseColor("#222222"));
                } else {
                    OnOff.setSummary("Disabled");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    getListView().setBackgroundColor(Color.parseColor("#ffffff"));

                }
                return true;
            }
        });
    }


    @Override
    protected void onResume() {
        Load_setting();
        super.onResume();
    }


}

