package de.ghse.tgi.rezepteapp;

import java.util.ArrayList;

public class StorageRecipe {
    private ArrayList<Recipe> list = new ArrayList<>();

    public ArrayList<Recipe> getList(){return list;}
    public void addRecipe(Recipe a){
        list.add(a);
    }
    public void deleteRecipe(Recipe a) {list.remove(a);}
}
