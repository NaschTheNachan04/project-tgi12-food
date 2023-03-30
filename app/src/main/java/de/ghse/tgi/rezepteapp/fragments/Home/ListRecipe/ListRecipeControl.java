package de.ghse.tgi.rezepteapp.fragments.Home.ListRecipe;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.StorageRecipe;

/**
 * Controller Class (MVC) of {@link ListRecipeFragment}(View).
 */
public class ListRecipeControl {
    private ListRecipeFragment gui;
    private ListRecipeListViewAdapter adapter;
    private StorageRecipe storage;

    /**
     * class constructor.
     * implements the associated View and ListAdapter
     * @param gui associated View
     * @param adapter ListAdapter of {@link android.widget.ListView} of {@link ListRecipeFragment}
     */
    public ListRecipeControl(ListRecipeFragment gui, ListRecipeListViewAdapter adapter){
        this.gui = gui;
        this.adapter = adapter;
        storage = MainActivity.getStorage();
    }

    /**
     * method to filter the shown {@link de.ghse.tgi.rezepteapp.Recipe}s according to SearchedText in {@link ListRecipeFragment}
     * updates the listView.
     */
    public void filter(){
        String filter = gui.getSearchText();                                    // get the filterString from GUI
        if (filter.isEmpty()) {
            adapter.setUnfiltered(true);                                        // if TextField is empty, show all the Recipes
        }else {
            adapter.setFilteredRecipe(storage.getFilteredIndexList(filter));    // else show all the Recipes that contain "filter"
            adapter.setUnfiltered(false);                                       // notify ListViewAdapter
        }
    }
}
