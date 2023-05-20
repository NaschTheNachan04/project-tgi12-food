package de.ghse.tgi.rezepteapp.fragments.Home.Input;

import de.ghse.tgi.rezepteapp.Ingredient;
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
        if(!gui.getRecipeName().isEmpty()) {
            Recipe a = new Recipe();                            //create new Recipe
            a.setName(gui.getRecipeName());                     //set RecipeName
            a.setDescription(gui.getRecipeDescription());       //set RecipeDescription
            a.setIngredient(adapter.getRecipeIngredients());        //set RecipeIngredient
            storage.addRecipe(a);                                //save it in storage
        }
        gui.finishTransaction();
    }

    /**
     * Use this method to add an ingredient.
     * Uses the params returned by {@link InputFragment}'s getIngredient...
     */
    public void saveIngredient(){
        String unit = gui.getIngredientUnit();
        String amount = gui.getIngredientAmount();
        String name = gui.getIngredientName();
        if (!(unit.isEmpty() || amount.isEmpty() || name.isEmpty())) {
            Ingredient i = new Ingredient();
            i.setUnit(unit);
            i.setAmount(Double.parseDouble(amount));
            i.setIngredient(name);
            adapter.addIngredient(i);
            adapter.notifyDataSetChanged();
        }
        gui.clearAddIngredientTextFields();                                        // clears the TextFields used, to be able to input another Ingredient
    }
}
