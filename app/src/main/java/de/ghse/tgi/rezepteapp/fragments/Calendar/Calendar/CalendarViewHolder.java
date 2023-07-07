package de.ghse.tgi.rezepteapp.fragments.Calendar.Calendar;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.ghse.tgi.rezepteapp.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvDay;


    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);
        tvDay = itemView.findViewById(R.id.tvDay);
    }

    public void setDay(String day){tvDay.setText(day);}
}