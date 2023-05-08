package de.ghse.tgi.rezepteapp.fragments.Home.Input;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.Recipe;
import de.ghse.tgi.rezepteapp.StorageRecipe;

/**
 * Controller (MVC) of {@link InputFragment} View
 */
public class InputControl {
    private StorageRecipe storage;
    private InputFragment gui;
    private InputListViewAdapter adapter;

    /**
     * Class constructor.
     * Implements the associated View ({@link InputFragment})
     *
     * @param pGui
     */
    public InputControl(InputFragment pGui,InputListViewAdapter adapter){
        gui = pGui;
        this.adapter = adapter;
        this.storage = MainActivity.getStorage();
    }

    /**
     * Use this method to save the created {@link Recipe}.
     * Clears the TextFields after Recipe is saved for next use.
     */
    public void save(){
        if(!adapter.getRecipeName().isEmpty()) {
            Recipe a = new Recipe();                            //create new Recipe
            a.setName(adapter.getRecipeName());                     //set RecipeName
            a.setDescription(adapter.getRecipeDescription());       //set RecipeDescription
            a.setIngredient(adapter.getRecipeIngredients());        //set RecipeIngredient
            storage.addRecipe(a);                             //save it in storage
        }
        adapter.clearTextFields();                                // clear the textFields to input another Recipe
        gui.finishTransaction();
    }
}
