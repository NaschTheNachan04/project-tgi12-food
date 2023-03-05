package de.ghse.tgi.rezepteapp.fragments.Home.ListRecipe;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.StorageRecipe;

public class ListRecipeControl {
    private ListRecipeFragment gui;
    private ListRecipeListViewAdapter adapter;
    private StorageRecipe storage;
    public ListRecipeControl(ListRecipeFragment gui, ListRecipeListViewAdapter adapter){
        this.gui = gui;
        this.adapter = adapter;
        storage = MainActivity.getStorage();
    }

    public void filter(){
        String filter = gui.getSearchText();
        if (filter.isEmpty()) {
            adapter.setFilteredRecipe(storage.getEmptyList());
            adapter.setUnfiltered(true);
        }else {
            adapter.setFilteredRecipe(storage.getFilteredIndexList(filter));
            adapter.setUnfiltered(false);
        }
    }
}
