package de.ghse.tgi.rezepteapp.fragments.ListRecipe;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.MyViewPagerAdapter;
import de.ghse.tgi.rezepteapp.Recipe;
import de.ghse.tgi.rezepteapp.StorageRecipe;

public class ListRecipeControl {
    private ListRecipeFragment gui;
    private MyViewPagerAdapter pager;
    private ListRecipeListViewAdapter adapter;
    private StorageRecipe storage;
    public ListRecipeControl(ListRecipeFragment gui, MyViewPagerAdapter pager, ListRecipeListViewAdapter adapter){
        this.gui = gui;
        this.pager = pager;
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
