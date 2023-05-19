package de.ghse.tgi.rezepteapp.fragments.Home.ViewRecipe;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.Database.StorageRecipe;

/**
 * Controller(VMC) of {@link ViewRecipeFragment} (View)
 */
public class ViewRecipeControl {
    private ViewRecipeFragment gui;
    private StorageRecipe storage;

    /**
     * Class constructor.
     * @param gui
     */
    public ViewRecipeControl(ViewRecipeFragment gui){
        this.gui = gui;
        this.storage = MainActivity.getStorage();
    }

    /**
     * method that updates View to show {@link de.ghse.tgi.rezepteapp.Recipe}  given index.
     *
     * @param itemId index of the {@link de.ghse.tgi.rezepteapp.Recipe} tht should be shown.
     */
    public void onCreate(int itemId){
        gui.setDescription(storage.getRecipeDescription(itemId));           //set the description of selected Recipe
        gui.setRName(storage.getRecipeName(itemId));                        //set the Name of selected Recipe
        gui.setImage(storage.getRecipeImage(itemId));                       //set the Picture of selected Recipe
    }
}