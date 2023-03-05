package de.ghse.tgi.rezepteapp.fragments.Home.ViewRecipe;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.StorageRecipe;
import de.ghse.tgi.rezepteapp.fragments.Home.ListRecipe.ListRecipeFragment;

public class ViewRecipeControl {
    private ViewRecipeFragment gui;
    private StorageRecipe storage;
    public ViewRecipeControl(ViewRecipeFragment gui){
        this.gui = gui;
        this.storage = MainActivity.getStorage();
    }
    public void onCreate(){
        gui.setDescription(storage.getRecipeDescription(ListRecipeFragment.getClickedItem()));
        gui.setRName(storage.getRecipeName(ListRecipeFragment.getClickedItem()));
        gui.setImage(storage.getRecipeImage(ListRecipeFragment.getClickedItem()));
    }
}