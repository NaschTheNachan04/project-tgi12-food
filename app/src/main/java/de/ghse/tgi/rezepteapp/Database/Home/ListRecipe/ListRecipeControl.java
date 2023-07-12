package de.ghse.tgi.rezepteapp.Database.Home.ListRecipe;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.MainActivity;

/**
 * Controller Class (MVC) of {@link ListRecipeFragment}(View).
 */
public class ListRecipeControl {
    private final ListRecipeFragment gui;
    private final ListRecipeListViewAdapter adapter;

    /**
     * class constructor.
     * implements the associated View and ListAdapter
     * @param gui associated View
     * @param adapter ListAdapter of {@link android.widget.ListView} of {@link ListRecipeFragment}
     */
    public ListRecipeControl(ListRecipeFragment gui, ListRecipeListViewAdapter adapter){
        this.gui = gui;
        this.adapter = adapter;
    }

    /**
     * method to filter the shown {@link de.ghse.tgi.rezepteapp.Recipe}s according to SearchedText in {@link ListRecipeFragment}
     * updates the listView.
     */
    public void filter(){
        String filter = gui.getSearchText();                                    // get the filterString from GUI
        if (filter.isEmpty()) {
            gui.setUnfiltered(true);
            adapter.setUnfiltered(true);                                        // if TextField is empty, show all the Recipes
            adapter.notifyDataSetChanged();
        }else {
            ArrayList<Integer> filteredList = MainActivity.getStorage().getFilteredIndexList(filter);
            gui.setUnfiltered(false);
            gui.setFilteredRecipe(filteredList);
            adapter.setFilteredRecipe(filteredList);    // else show all the Recipes that contain "filter"
            adapter.setUnfiltered(false);                                       // notify ListViewAdapter
            adapter.notifyDataSetChanged();
        }
    }
}
