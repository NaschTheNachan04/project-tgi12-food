package de.ghse.tgi.rezepteapp.fragments.Home.Input;

import android.widget.Toast;

import de.ghse.tgi.rezepteapp.R.string;
import de.ghse.tgi.rezepteapp.Ingredient;
import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.Recipe;
import de.ghse.tgi.rezepteapp.Database.StorageRecipe;

/**
 * Controller (MVC) of {@link InputFragment} View
 */
public class InputControl {
    private final StorageRecipe storage;
    private final InputFragment gui;
    private final InputListViewAdapter adapter;

    /**
     * Class constructor.
     * Implements the associated View ({@link InputFragment})
     *
     * @param pGui associated View (MVC)
     * @param adapter {@link InputListViewAdapter} of the {@link android.widget.ListView} of associated View(MVC)
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
    public void save() {
        if (!((gui.getRecipeName().isEmpty())&& (gui.getImageUri() == null))) {
            Recipe a = new Recipe();                            //create new Recipe
            a.setName(gui.getRecipeName());                     //set RecipeName
            a.setDescription(gui.getRecipeDescription());       //set RecipeDescription
            a.setIngredient(adapter.getRecipeIngredients());    //set RecipeIngredient
            a.setImageUri(gui.getImageUri());                   //set RecipeImage
            storage.addRecipe(a);                                //save the Recipe in storage
            gui.finishTransaction();
        }else Toast.makeText(gui.getContext(), string.addName, Toast.LENGTH_LONG).show();        //if not already done, tell the user to fill in a name
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
            i.setName(name);
            adapter.addIngredient(i);
            adapter.notifyDataSetChanged();
        }
        gui.clearAddIngredientTextFields();
        adapter.clearTextFields();              // clears the TextFields used, to be able to input another Ingredient
    }
}
