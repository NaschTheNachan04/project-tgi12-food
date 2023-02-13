package de.ghse.tgi.rezepteapp.fragments.Input;

import de.ghse.tgi.rezepteapp.Recipe;
import de.ghse.tgi.rezepteapp.StorageRecipe;
import de.ghse.tgi.rezepteapp.fragments.Input.InputFragment;

public class InputControll {
    private StorageRecipe storage;
    private InputFragment gui;

    public InputControll(InputFragment pgui, StorageRecipe s){
        gui = pgui;
        storage = s;
    }

    public void save(){
        Recipe a = new Recipe();
        a.setName(gui.getRecipeName());
        a.setDesription(gui.getRecipeDescription());
        a.setIngredient(gui.getRecipeIngredients());
        storage.addRecipe(a);
        gui.clearTextFields();
    }
}
