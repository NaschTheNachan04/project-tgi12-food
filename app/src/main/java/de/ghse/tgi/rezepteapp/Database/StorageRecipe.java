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
        database.deleteRezept(a);
        emptyList.remove(emptyList.size()-1);
    }
    public int getCount(){
        return list.size();
    }

    public ArrayList<Integer> getFilteredIndexList(String filter){
        filter.toLowerCase();
        ArrayList<Integer> filteredList = new ArrayList<Integer>();
        for (int i=0;i<list.size();i++){
            if (list.get(i).getName().toLowerCase().contains(filter)){
                filteredList.add(i);
            }
        }
        return filteredList;
    }
    public String getRecipeName(int index){return list.get(index).getName();}
    public int getRecipeImage(int index){return list.get(index).getImage();}
    public String getRecipeDescription(int index){return list.get(index).getDescription();}
    public ArrayList<String> getIngredients(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("tomato");
        list.add("apple");
        list.add("egg");
        list.add("fish");
        list.add("cucumber");
        list.add("salmon");
        return list;
    }
    private int getIngredientCount(int ID){
        return list.get(ID).getIngredient().size();
    }
    public double[] getIngredientsAmount(int id){
        int count = getIngredientCount(id);
        double[] amount = new double[count];
        for(int i = 0; i<count;i++){
            amount[i] = list.get(id).getIngredient().get(i).getAmount();
        }
        return amount;
    }
    public String[] getIngredientsUnit(int id){
        int count = getIngredientCount(id);
        String[] unit = new String[count];
        for(int i = 0; i<count;i++){
            unit[i] = list.get(id).getIngredient().get(i).getUnit();
        }
        return unit;
    }
    public String[] getIngredientsName(int id){
        int count = getIngredientCount(id);
        String[] name = new String[count];
        for(int i = 0; i<count;i++){
            name[i] = list.get(id).getIngredient().get(i).getIngredient();
        }
        return name;
    }


}
