package de.ghse.tgi.rezepteapp.fragments.Calendar.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.R;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{

    private int daysInCurrentMonth, emptyDays, month,year;
    private final MainCalendar main;

    public CalendarAdapter(MainCalendar main) {
        this.main = main;
    }

    public void setDaysInCurrentMonth(int daysInCurrentMonth) {
        this.daysInCurrentMonth = daysInCurrentMonth;
    }

    public void setFirstDayOfWeekInCurrentMonth(int firstDayOfWeekInCurrentMonth) {
        emptyDays = firstDayOfWeekInCurrentMonth-2;
        if (emptyDays== -1) emptyDays= 6;
    }

    public void setMonth(int month){this.month =month;}

    public void setYear(int year){this.year =year;}



    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cellView = inflater.inflate(R.layout.calendar_cell, parent, false);
        cellView.setOnClickListener(view -> {
            TextView tv = view.findViewById(R.id.tvDay);
            String day = tv.getText().toString();
            main.goToEvent(day);
        });
        return new CalendarViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        if (position<emptyDays) holder.setDay(null);
        else {
            int day = position+1-emptyDays;
            holder.setDay(day);
            if (MainActivity.getStorage().getRecipeOnDayCount(day,month,year)!= 0) holder.setMarkedDay();
        }
    }

    @Override
    public int getItemCount() {
        return daysInCurrentMonth+emptyDays;
    }




}