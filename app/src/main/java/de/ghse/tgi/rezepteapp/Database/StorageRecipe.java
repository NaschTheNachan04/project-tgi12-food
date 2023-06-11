package de.ghse.tgi.rezepteapp.Database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.Recipe;


public class StorageRecipe {
    private ArrayList<Recipe> list = new ArrayList<>();
    private ArrayList<Integer> emptyList = new ArrayList<>();
    public AppDatabase database;
    public ArrayList<DatabaseReaderRecipe> recipeReader = new ArrayList<>();
    public ArrayList<DatabaseReaderIngredient> ingredientReader = new ArrayList<>();


    public void addContext(Context c){
        database= new AppDatabase(c);
    }


    public void addRecipe(Recipe a){
        //list.add(a);
        database.addRezeptToDataBase(a);
        database.addZutatToDataBase(a);
        //emptyList.add(list.size()+1);
    }
    public void deleteRecipe(Recipe a) {
        list.remove(a);
        database.deleteRezept(a.getName());
        emptyList.remove(emptyList.size()-1);
    }
    public int getCount(){
        //return list.size();
        recipeReader=database.getRecipes();
        return  recipeReader.size();

    }

    public ArrayList<Integer> getFilteredIndexList(String filter){
        recipeReader=database.getRecipes();
        filter.toLowerCase();
        ArrayList<Integer> filteredList = new ArrayList<Integer>();
        for (int i=0;i<recipeReader.size();i++){
            if (recipeReader.get(i).getNameRecipe().toLowerCase().contains(filter)){
                filteredList.add(i);
            }
        }
        return filteredList;
    }
    public String getRecipeName(int index){
        //return list.get(index).getName();
        recipeReader=database.getRecipes();
        return recipeReader.get(index).getNameRecipe();
    }
    public int getRecipeImage(int index){
        //return list.get(index).getImage();
        recipeReader=database.getRecipes();
        return recipeReader.get(index).getImageRecipe();
    }
    public String getRecipeDescription(int index){
        //return list.get(index).getDescription();
        recipeReader=database.getRecipes();
        return recipeReader.get(index).getDescritipionRecipe();
    }
    public ArrayList<DatabaseReaderIngredient> getIngredients(){
        /*ArrayList<String> list = new ArrayList<String>();
        list.add("tomato");
        list.add("apple");
        list.add("egg");
        list.add("fish");
        list.add("cucumber");
        list.add("salmon");
        return list;*/
        ingredientReader= database.getIngredients();
        return ingredientReader;
    }
    private int getIngredientCount(){
        //return list.get(ID).getIngredient().size();
        ingredientReader= database.getIngredients();
        return ingredientReader.size();
    }
    public double[] getIngredientsAmount(int id){
        ingredientReader= database.getIngredients();
        int count = getIngredientCount();
        double[] amount = new double[count];
        for(int i = 0; i<count;i++){
            amount[i] = ingredientReader.get(id).getAmount();
        }
        return amount;
    }
    public String[] getIngredientsUnit(int id){
        ingredientReader= database.getIngredients();
        int count = getIngredientCount();
        String[] unit = new String[count];
        for(int i = 0; i<count;i++){
            unit[i] = ingredientReader.get(id).getUnit();
        }
        return unit;
    }
    public String[] getIngredientsName(int id){
        ingredientReader= database.getIngredients();
        int count = getIngredientCount();
        String[] name = new String[count];
        for(int i = 0; i<count;i++){
            name[i] = ingredientReader.get(id).getNameIngredient();
        }
        return name;
    }



}
