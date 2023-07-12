package de.ghse.tgi.rezepteapp.fragments.Home.ListRecipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.fragments.Home.HomeFragment;

/**
 * A {@link Fragment} subclass.
 * View (MVC) of {@link ListRecipeControl}.
 * Contains a ListView showing the {@link de.ghse.tgi.rezepteapp.Recipe} saved.
 */
public class ListRecipeFragment extends Fragment {
    protected ListRecipeControl ctrl;
    protected View view;
    private final HomeFragment homeFragment;
    protected int clickedItem = 0;
    protected boolean isUnfiltered = true;
    protected ArrayList<Integer> filteredRecipe;


    /**
     * Class constructor.
     * implements the associated homeFragment for navigation between Fragments.
     *
     * @param homeFragment HomeFragment to notify when page should be changed.
     */
    public ListRecipeFragment(HomeFragment homeFragment){
        super();
        this.homeFragment =homeFragment;
    }

    @Override
    public void onResume(){
        super.onResume();
        dataSetChanged();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null){
            ViewHolder holder = new ViewHolder();
            view =inflater.inflate(R.layout.fragment_list_recipe, container, false);

            holder.list = view.findViewById(R.id.listViewRecipe);
            holder.adapter = new ListRecipeListViewAdapter(getContext());
            holder.list.setAdapter(holder.adapter);
            holder.list.setOnItemClickListener((adapterView, view, i, l) -> {
                if (isUnfiltered) clickedItem = i+1;                                                                  //index
                else clickedItem = filteredRecipe.get(i);
                homeFragment.replaceFragment(HomeFragment.VIEW_RECIPE);                                                // on click on ListViewItem, show Recipe at index.
            });
            ctrl = new ListRecipeControl(this,holder.adapter);

            holder.fab =  view.findViewById(R.id.fabAdd);
            holder.fab.setOnClickListener(view -> {
                homeFragment.replaceFragment(HomeFragment.INPUT);                                                // if fab is clicked, open InputFragment
            });

            holder.etSearchRecipe = view.findViewById(R.id.etSearchRecipe);
            holder.etSearchRecipe.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    ctrl.filter();                                                                    // if searchedText is edited, update ListView.
                }
            });
            view.setTag(holder);
        }
        return view;
    }

    /**
     *
     * @return Return the SearchText the {@link de.ghse.tgi.rezepteapp.Recipe}s should be filtered with.
     */
    public String getSearchText(){
        ViewHolder holder = (ViewHolder) view.getTag();
        return holder.etSearchRecipe.getText().toString();
    }

    /**
     * A method to get the latest clicked Item of {@link ListView}.
     * @return The index of the latest clicked ListviewItem
     */
    public  int getClickedItem(){
        return clickedItem;
    }

    /**
     * Has to be called when dataSet has been changed.
     * Updates the Listview
     */
    protected void dataSetChanged(){
        if (view != null) {
            ViewHolder holder = (ViewHolder) view.getTag();
            if (holder.adapter != null ) {
                holder.adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * called to switch between showing only filtered Recipes and showing all of them
     * requires {@link #setFilteredRecipe(ArrayList)} to be called before passing false.
     * @param pIsUnfiltered true to show all Recipes, false to show only the filtered ones.
     */
    public void setUnfiltered(boolean pIsUnfiltered) {
        isUnfiltered = pIsUnfiltered;
    }

    /**
     * called to set the selection of Recipes to be shown
     * @param fR list of the Indexes of all the Recipes that match the filter
     */
    public void setFilteredRecipe(ArrayList<Integer> fR){
        filteredRecipe = fR;
    }

    /**
     * cache for all the UI-elements
     * used to prevent calling {@link View#findViewById(int)} every time {@link ListRecipeFragment} is loaded
     */
    private static class ViewHolder{
         FloatingActionButton fab;
         EditText etSearchRecipe;
         ListView list;
         ListRecipeListViewAdapter adapter;
    }
}