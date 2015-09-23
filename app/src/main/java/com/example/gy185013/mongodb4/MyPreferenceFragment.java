package com.example.gy185013.mongodb4;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by gy185013 on 9/15/2015.
 */
public class MyPreferenceFragment extends PreferenceFragment
{
    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
