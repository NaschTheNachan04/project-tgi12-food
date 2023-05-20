package de.ghse.tgi.rezepteapp.fragments.Home.ViewRecipe;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.StorageRecipe;

/**
 * Controller(VMC) of {@link ViewRecipeFragment} (View)
 */
public class ViewRecipeControl {
    private ViewRecipeFragment gui;
    private StorageRecipe storage;
    private int recipeID;
    private double[] ingredientAmount;
    private String[] ingredientUnit;
    private String[] ingredientName;

    /**
     * Class constructor.
     * @param gui
     */
    public ViewRecipeControl(ViewRecipeFragment gui){
        this.gui = gui;
        this.storage = MainActivity.getStorage();
    }

    /**
     * called to tell {@link ViewRecipeControl} which recipe it shall show.
     * @param itemId index of the {@link de.ghse.tgi.rezepteapp.Recipe} that should be shown.
     */
    public void setRecipe(int itemId){
        recipeID = itemId;
        ingredientAmount = MainActivity.getStorage().getIngredientsAmount(itemId);
        ingredientUnit = MainActivity.getStorage().getIngredientsUnit(itemId);
        ingredientName = MainActivity.getStorage().getIngredientsName(itemId);
        updateGUI();
    }
    /**
     * method that updates View to display the given{@link de.ghse.tgi.rezepteapp.Recipe}
     */
    private void updateGUI(){
        gui.setDescription(storage.getRecipeDescription(recipeID));           //set the description of selected Recipe
        gui.setRName(storage.getRecipeName(recipeID));                        //set the Name of selected Recipe
        gui.setImage(storage.getRecipeImage(recipeID));                       //set the Picture of selected Recipe
    }

    /**
     * called to get the amount of ingredients of current Recipe
     * needs {@link #setRecipe(int)} to be called first.
     * @return amount af Ingredients
     */
    public int getIngredientCount(){
        if (ingredientName == null){ return 0;}
        return ingredientName.length;}

    /**
     * called to get the name of the ingredient at position "pos".
     * needs {@link #setRecipe(int)} to be called first
     *
     * @param pos position at which the Ingredient is saved
     * @return name of Ingredient at position "pos"
     */
    public String getIngredientName(int pos){return ingredientName[pos];}

    /**
     * called to get the unit of the ingredient at position "pos".
     * needs {@link #setRecipe(int)} to be called first
     *
     * @param pos position at which the Ingredient is saved
     * @return unit of Ingredient at position "pos"
     */
    public String getIngredientUnit(int pos){return ingredientUnit[pos];}

    /**
     * called to get the amount of the ingredient at position "pos".
     * needs {@link #setRecipe(int)} to be called first
     *
     * @param pos position at which the Ingredient is saved
     * @return amount of Ingredient at position "pos"
     */
    public double getIngredientAmount(int pos){return ingredientAmount[pos];}
}