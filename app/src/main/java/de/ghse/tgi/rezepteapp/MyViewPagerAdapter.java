package de.ghse.tgi.rezepteapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import de.ghse.tgi.rezepteapp.fragments.Calendar.CalendarFragment;
import de.ghse.tgi.rezepteapp.fragments.Home.HomeFragment;
import de.ghse.tgi.rezepteapp.fragments.Settings.SettingsFragment;

/**
 * A {@link FragmentStateAdapter} subclass
 * Manages {@link Fragment}s: 0: {@link HomeFragment}, 1: {@link CalendarFragment}, 2: {@link SettingsFragment}.
 *
 */
public class MyViewPagerAdapter extends FragmentStateAdapter {
    private MainActivity main;

    /**
     * Class constructor.
     * @param mainActivity associated {@link android.app.Activity}
     */
    public MyViewPagerAdapter(@NonNull MainActivity mainActivity) {
        super(mainActivity);
        main = mainActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment(this);                          // first Fragent is HomeFragment
            case 1:
                return new CalendarFragment(this);                      //second Fragment is CalendarFragment
            case 2:
                return new SettingsFragment(this);                      //third Fragment is SettingsFragment
            default:
                return new HomeFragment(this);
        }
    }

    /**
     *
     * @return Returns associated {@link android.app.Activity} ({@link android.content.Context})
     */
    public MainActivity getMainActivity(){return main;}
    @Override
    public int getItemCount() {
        return 3;
    }
}
