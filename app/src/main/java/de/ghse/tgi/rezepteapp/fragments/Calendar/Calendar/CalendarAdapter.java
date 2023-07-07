package de.ghse.tgi.rezepteapp.fragments.Calendar.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.fragments.Calendar.Calendar.CalendarViewHolder;
import de.ghse.tgi.rezepteapp.fragments.Calendar.Calendar.MainCalendar;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{

    private int daysInCurrentMonth;
    private int emptyDays;
    private MainCalendar main;

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
        else holder.setDay(String.valueOf(position+1-emptyDays));
    }

    @Override
    public int getItemCount() {
        return daysInCurrentMonth+emptyDays;
    }




}