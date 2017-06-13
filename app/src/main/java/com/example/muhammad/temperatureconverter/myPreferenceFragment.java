package com.example.muhammad.temperatureconverter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.example.muhammad.temperatureconverter.R;

public class myPreferenceFragment extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_preference);

        ListPreference splashList = (ListPreference) findPreference("decimals");
        splashList.setSummary(splashList.getEntry());

        splashList.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String nv = (String) newValue;

                if (preference.getKey().equals("decimals")) {
                    ListPreference splashList = (ListPreference) preference;
                    splashList.setSummary(splashList.getEntries()[splashList.findIndexOfValue(nv)]);
                }
                return true;
            }

        });


    }


}