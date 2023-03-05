package de.ghse.tgi.rezepteapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Recipe {
    @PrimaryKey
    public int uid;
    @ColumnInfo (name = "name")
    private String name;
    @ColumnInfo(name= "description")
    private String description;
    @ColumnInfo (name ="ingredients")
    private ArrayList<String> ingredient = new ArrayList<>();
    @ColumnInfo (name="image")
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

    public ArrayList getIngredient() {
        return ingredient;
    }
    public void setIngredient(ArrayList ingredient) {
        this.ingredient = ingredient;
    }

    public int getImage() {return Image;}

    public Recipe(String name,String description,ArrayList<String>  ingredient,int Image) {
        this.name = name;
        this.description= description;
        this.ingredient=ingredient;
        this.Image=Image;
    }

    public Recipe() {
    }
}
