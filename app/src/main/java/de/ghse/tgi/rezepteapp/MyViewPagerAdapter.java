package de.ghse.tgi.rezepteapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import de.ghse.tgi.rezepteapp.fragments.Calendar.CalendarFragment;
import de.ghse.tgi.rezepteapp.fragments.Home.HomeFragment;
import de.ghse.tgi.rezepteapp.fragments.Settings.SettingsFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    private MainActivity main;
    public MyViewPagerAdapter(@NonNull MainActivity mainActivity) {
        super(mainActivity);
        main = mainActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment(this);
            case 1:
                return new CalendarFragment(this);
            case 2:
                return new SettingsFragment(this);
            default:
                return new HomeFragment(this);
        }
    }

    public MainActivity getMainActivity(){return main;}
    @Override
    public int getItemCount() {
        return 3;
    }
}
