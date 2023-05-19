package de.ghse.tgi.rezepteapp.fragments.Home.Input;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.Recipe;
import de.ghse.tgi.rezepteapp.Database.StorageRecipe;

/**
 * Controller (MVC) of {@link InputFragment} View
 */
public class InputControl {
    private StorageRecipe storage;
    private InputFragment gui;

    /**
     * Class constructor.
     * Implements the associated View ({@link InputFragment})
     *
     * @param pGui
     */
    public InputControl(InputFragment pGui){
        gui = pGui;
        this.storage = MainActivity.getStorage();
    }

    /**
     * Use this method to save the created {@link Recipe}.
     * Clears the TextFields after Recipe is saved for next use.
     */
    public void save(){
        if(!gui.getRecipeName().isEmpty()) {
            Recipe a = new Recipe();                            //create new Recipe
            a.setName(gui.getRecipeName());                     //set RecipeName
            a.setDescription(gui.getRecipeDescription());       //set RecipeDescription
            a.setIngredient(gui.getRecipeIngredients());        //set RecipeIngredient
            storage.addRecipe(a);                             //save it in storage
        }
        gui.clearTextFields();                                // clear the textFields to input another Recipe
    }
}
