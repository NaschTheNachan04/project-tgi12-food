package de.ghse.tgi.rezepteapp.fragments.Settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import de.ghse.tgi.rezepteapp.MyViewPagerAdapter;
import de.ghse.tgi.rezepteapp.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    private MyViewPagerAdapter pager;

    public SettingsFragment(MyViewPagerAdapter p){
        pager = p;
    }
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}