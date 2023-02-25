package de.ghse.tgi.rezepteapp.fragments.ViewRecipe;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.StorageRecipe;
import de.ghse.tgi.rezepteapp.fragments.ListRecipe.ListRecipeFragment;

public class ViewRecipeControl {
    private ViewRecipeFragment gui;
    private StorageRecipe storage;
    private MainActivity main;
    public ViewRecipeControl(ViewRecipeFragment gui, MainActivity main){
        this.gui = gui;
        this.main = main;
        main.addViewRecipeControl(this);
        this.storage = main.getStorage();
    }
    public void onCreate(){
        gui.setDescription(storage.getRecipeDescription(ListRecipeFragment.getClickedItem()));
        gui.setRName(storage.getRecipeName(ListRecipeFragment.getClickedItem()));
        gui.setImage(storage.getRecipeImage(ListRecipeFragment.getClickedItem()));
    }
}
