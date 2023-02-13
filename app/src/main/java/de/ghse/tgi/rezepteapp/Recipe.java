package de.ghse.tgi.rezepteapp;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private String desription;
    private ArrayList<String> ingredient = new ArrayList<>();
    private int Image = R.drawable.mealpic;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesription() {
        return desription;
    }
    public void setDesription(String desription) {
        this.desription = desription;
    }

    public ArrayList getIngredient() {
        return ingredient;
    }
    public void setIngredient(ArrayList ingredient) {
        this.ingredient = ingredient;
    }

    public int getImage() {return Image;}
}
