package de.ghse.tgi.rezepteapp;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private String description;
    private ArrayList<Ingredient> ingredient;
    private int Image = R.drawable.mealpic;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Ingredient> getIngredient() {
        return ingredient;
    }
    public void setIngredient(ArrayList<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public int getImage() {return Image;}
}
