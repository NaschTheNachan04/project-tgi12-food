package de.ghse.tgi.rezepteapp.fragments.Settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import de.ghse.tgi.rezepteapp.MyViewPagerAdapter;
import de.ghse.tgi.rezepteapp.R;

/**
 * A {@link PreferenceFragmentCompat} subclass
 * {@link androidx.fragment.app.Fragment} that manages the Settings of the App.
 */
public class SettingsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}