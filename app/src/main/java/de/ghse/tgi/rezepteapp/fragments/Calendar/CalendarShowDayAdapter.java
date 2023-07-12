package de.ghse.tgi.rezepteapp.fragments.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.R;

public class CalendarShowDayAdapter extends BaseAdapter {
    private int day, month, year;
    private ArrayList<Integer> recipeId;
    private ArrayList<Integer[]> time;
    private final LayoutInflater layoutInflater;

    public CalendarShowDayAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public int getRecipeID(int position){
        return recipeId.get(position);
    }

    public void setDay(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        recipeId = MainActivity.getStorage().getRecipeId(day, month, year);
        time = MainActivity.getStorage().getEventTime(day, month, year);
    }


    @Override
    public int getCount() {
        return MainActivity.getStorage().getRecipeOnDayCount(day, month, year) * 2;
    }

    @Override
    public Object getItem(int i) {
        return recipeId.get(i/2);
    }

    @Override
    public long getItemId(int i) {
        return recipeId.get(i/2);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (position % 2 == 0) {

            view = layoutInflater.inflate(R.layout.textview_oneline,parent,false);
            TextView textView = view.findViewById(R.id.singleLineTV);
            String t = time.get(position/2)[0]+":"+time.get(position/2)[1];
            textView.setText(t);
        }else {
            view = layoutInflater.inflate(R.layout.activity_content_view_recipe,parent,false);
            ImageView imageView = view.findViewById(R.id.iVRecipeImage);
            try {
                imageView.setImageURI(MainActivity.getStorage().getRecipeImage(recipeId.get((position-1)/2)));
            }catch (Exception e){
                imageView.setImageResource(R.drawable.ic_baseline_hide_image_24);
            }
            TextView name = view.findViewById(R.id.tVRecipeName);
            name.setText(MainActivity.getStorage().getRecipeName(recipeId.get((position-1)/2)));
        }
    return view;
    }
}
