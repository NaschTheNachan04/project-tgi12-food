package de.ghse.tgi.rezepteapp.fragments.Calendar.Calendar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.fragments.Calendar.CalendarFragment;


public class MainCalendar extends Fragment {

    private Calendar calendar;
    private TextView tvMonthYear;
    private CalendarAdapter adapter;
    private String[] months;
    private View view;
    private final CalendarFragment main;
    private int month,year;
    public MainCalendar(CalendarFragment main) {
        this.main = main;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main_calendar, container, false);
            tvMonthYear = view.findViewById(R.id.monthYearTV);
            RecyclerView recyclerView = view.findViewById(R.id.calendarRecyclerView);
            calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            adapter = new CalendarAdapter(this);
            months = getResources().getStringArray(R.array.months);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 7));
            recyclerView.setAdapter(adapter);
            updateGUI();

            Button previousMonth = view.findViewById(R.id.bPreviousMonth);
            Button nextMonth = view.findViewById(R.id.bNextMonth);
            previousMonth.setOnClickListener(view -> {
                calendar.add(Calendar.MONTH, -1);
                updateGUI();
            });
            nextMonth.setOnClickListener(view -> {
                calendar.add(Calendar.MONTH, 1);
                updateGUI();
            });
        }
        return view;
    }

    private void updateGUI(){
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        int firstDayOfWeekInMonth = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInCurrentMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        adapter.setFirstDayOfWeekInCurrentMonth(firstDayOfWeekInMonth);
        adapter.setDaysInCurrentMonth(daysInCurrentMonth);
        adapter.setMonth(month);
        adapter.setYear(year);
        adapter.notifyDataSetChanged();

        String ym = months[month]+" "+ year;
        tvMonthYear.setText(ym);
    }

    public void goToEvent(String day) {
        main.goToDay(Integer.parseInt(day),month+1,year);
        main.replaceFragment(CalendarFragment.SHOW_FRAGMENT_DAY);
    }


}


