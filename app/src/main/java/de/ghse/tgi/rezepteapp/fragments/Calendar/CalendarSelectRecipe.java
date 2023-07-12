package de.ghse.tgi.rezepteapp.fragments.Calendar;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;


import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.Database.Home.ListRecipe.ListRecipeControl;
import de.ghse.tgi.rezepteapp.Database.Home.ListRecipe.ListRecipeFragment;
import de.ghse.tgi.rezepteapp.Database.Home.ListRecipe.ListRecipeListViewAdapter;


public class CalendarSelectRecipe extends ListRecipeFragment {
private final CalendarFragment calendarFragment;


    public CalendarSelectRecipe(CalendarFragment calendarFragment) {
        super(null);
        this.calendarFragment = calendarFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null){
            view = inflater.inflate(R.layout.fragment_calendar_select_recipe, container, false);
            ViewHolder holder = new ViewHolder();
            holder.list = view.findViewById(R.id.LVSelect);
            holder.adapter = new ListRecipeListViewAdapter(getContext());
            holder.list.setAdapter(holder.adapter);
            holder.list.setOnItemClickListener((adapterView, view, i, l) -> {
                if (isUnfiltered) clickedItem = i+1;                                                                 //index
                else clickedItem = filteredRecipe.get(i);
                calendarFragment.setEventRecipe(clickedItem);
                calendarFragment.replaceFragment(CalendarFragment.ADD_EVENT_FRAGMENT);                                // on click on ListViewItem, show Recipe at index.
            });
            ctrl = new ListRecipeControl(this,holder.adapter);

            holder.etSearchRecipe = view.findViewById(R.id.ETSearch);
            holder.etSearchRecipe.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    ctrl.filter();                                                                                   // if searchedText is edited, update ListView.
                }
            });
            view.setTag(holder);
        }
        return view;
    }
    /**
     * Has to be called when dataSet has been changed.
     * Updates the Listview
     */
    @Override
    protected void dataSetChanged(){
        if (view != null) {
            ViewHolder holder = (ViewHolder) view.getTag();
            if (holder.adapter != null ) {
                holder.adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     *
     * @return Return the SearchText the {@link de.ghse.tgi.rezepteapp.Recipe}s should be filtered with.
     */
    @Override
    public String getSearchText(){
        ViewHolder holder = (ViewHolder) view.getTag();
        return holder.etSearchRecipe.getText().toString();
    }

    private static class ViewHolder{
        EditText etSearchRecipe;
        ListView list;
        ListRecipeListViewAdapter adapter;
    }
}
