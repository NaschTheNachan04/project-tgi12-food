package de.ghse.tgi.rezepteapp.fragments.Home.Input;

import de.ghse.tgi.rezepteapp.Recipe;
import de.ghse.tgi.rezepteapp.StorageRecipe;

public class InputControl {
    private StorageRecipe storage;
    private InputFragment gui;

    public InputControl(InputFragment pGui, StorageRecipe s){
        gui = pGui;
        storage = s;
    }

    public void save(){
        if(!gui.getRecipeName().isEmpty()) {
            Recipe a = new Recipe();
            a.setName(gui.getRecipeName());
            a.setDescription(gui.getRecipeDescription());
            a.setIngredient(gui.getRecipeIngredients());
            storage.addRecipe(a);
        }
        gui.clearTextFields();
    }
}
