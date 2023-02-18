package de.ghse.tgi.rezepteapp.fragments.ViewRecipe;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.Recipe;
import de.ghse.tgi.rezepteapp.StorageRecipe;
import de.ghse.tgi.rezepteapp.fragments.ListRecipe.ListRecipeFragment;

public class ViewRecipeControll {
    private ViewRecipeFragment gui;
    private StorageRecipe storage;
    private MainActivity main;
    public ViewRecipeControll(ViewRecipeFragment gui, MainActivity main){
        this.gui = gui;
        this.main = main;
        main.addViewRecipeControll(this);
        this.storage = main.getStorage();
    }
    public void onCreate(){
        ArrayList<Recipe> list = storage.getList();
        if(list.size() == 0){
            gui.setRName("No Recipe");
            gui.setDescription("No Description");
            gui.setImage(R.drawable.mealpic);
        }else {
            gui.setDescription(list.get(ListRecipeFragment.getClickedItem()).getDesription());
            gui.setRName(storage.getList().get(ListRecipeFragment.getClickedItem()).getName());
            gui.setImage(storage.getList().get(ListRecipeFragment.getClickedItem()).getImage());
        }
    }
}