package de.ghse.tgi.rezepteapp.fragments.fragmentclasses.ViewRecipe.ViewRecipe;

import java.io.IOException;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.Database.StorageRecipe;

/**
 * Controller(VMC) of {@link ViewRecipeFragment} (View)
 */
public class ViewRecipeControl {
    private final ViewRecipeFragment gui;
    private final StorageRecipe storage;
    private final ListIngredientsViewRecipeAdapter adapter;

    private int recipeID;

    /**
     * Class constructor.
     * @param gui associated GUI
     */
    public ViewRecipeControl(ViewRecipeFragment gui, ListIngredientsViewRecipeAdapter adapter){
        this.gui = gui;
        this.storage = MainActivity.getStorage();
        this.adapter = adapter;
    }

    /**
     * called to tell {@link ViewRecipeControl} which recipe it shall show.
     * @param itemId index of the {@link de.ghse.tgi.rezepteapp.Recipe} that should be shown.
     */
    public void setRecipe(int itemId) {
        recipeID = itemId;
        updateGUI();
        updateListView();
    }
    /**
     * method that updates View to display the given{@link de.ghse.tgi.rezepteapp.Recipe}
     */
    private void updateGUI()  {
        gui.setDescription(storage.getRecipeDescription(recipeID));           //set the description of selected Recipe
        gui.setRName(storage.getRecipeName(recipeID));                        //set the Name of selected Recipe
        gui.setImage(storage.getRecipeImage(recipeID));                       //set the Picture of selected Recipe
    }

    /**
     * method that updates the ListView inside of View, to display the given
     * Ingredients of given {@link de.ghse.tgi.rezepteapp.Recipe}
     */
    private void updateListView(){
        adapter.setIngredientAmount(storage.getIngredientsAmount(recipeID));
        adapter.setIngredientUnit(storage.getIngredientsUnit(recipeID));
        adapter.setIngredientName(storage.getIngredientsName(recipeID));
        adapter.notifyDataSetChanged();
    }
}