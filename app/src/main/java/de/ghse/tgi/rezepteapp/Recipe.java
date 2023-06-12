package de.ghse.tgi.rezepteapp;

import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private String description;
    private ArrayList<Ingredient> ingredient;
    private Uri image;
    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }



    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    private int rid;


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

    public void setImageUri(Uri uri){image = uri;}
    public Uri getImageUri() {return image;}


}
