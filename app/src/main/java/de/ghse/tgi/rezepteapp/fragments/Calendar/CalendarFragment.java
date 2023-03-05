package de.ghse.tgi.rezepteapp.fragments.Calendar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.ghse.tgi.rezepteapp.MyViewPagerAdapter;
import de.ghse.tgi.rezepteapp.R;

public class CalendarFragment extends Fragment {
    private MyViewPagerAdapter pager;
    private View view;

    public CalendarFragment() {
        super();
    }
    public CalendarFragment(MyViewPagerAdapter p) {
        super();
        pager =p;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        return view;
    }
}