package de.ghse.tgi.rezepteapp.fragments.Calendar;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

import de.ghse.tgi.rezepteapp.Event;
import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.fragments.Home.ListRecipe.ListRecipeListViewAdapter;


public class AddEventFragment extends Fragment {
    private CalendarFragment GUI;
    private ListRecipeListViewAdapter adapter;
    private ArrayList<Integer> recipeList = new ArrayList<>();
    private int hour, minute, day, month, year;
    private EditText timeET, titleET;


    public AddEventFragment(CalendarFragment GUI) {
        this.GUI = GUI;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ListRecipeListViewAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_event, container, false);
        titleET = view.findViewById(R.id.titleET);
        adapter.setFilteredRecipe(recipeList);
        adapter.setUnfiltered(false);
        ListView listView = view.findViewById(R.id.LV);
        listView.setAdapter(adapter);
        //Time
        timeET = view.findViewById(R.id.timeET);
        timeET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),new onTimeListener(),hour,minute,true);
                timePickerDialog.show();
            }
        });
        //Buttons
        Button addRecipeB = view.findViewById(R.id.addRecipeB);
        addRecipeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GUI.replaceFragment(CalendarFragment.SELECT_RECIPE);
            }
        });

        Button saveEventB = view.findViewById(R.id.saveEventB);
        saveEventB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event event = new Event();
                event.setDay(day);
                event.setMonth(month);
                event.setYear(year);
                event.setTitle(titleET.getText().toString());
                event.setHour(hour);
                event.setMinute(minute);
                event.setRecipe(recipeList);
                MainActivity.getStorage().addEvent(event);
                GUI.replaceFragment(CalendarFragment.CALENDAR);
            }
        });
        return view;
    }

    public void addEventRecipe(int ID){
        recipeList.add(ID);
    }

    public void setDay(int day, int  month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    private class onTimeListener implements TimePickerDialog.OnTimeSetListener{

        @Override
        public void onTimeSet(TimePicker timePicker,int phour, int pminute) {
            minute = pminute;
            hour = phour;
            timeET.setText(hour+":"+minute);

        }
    }


}