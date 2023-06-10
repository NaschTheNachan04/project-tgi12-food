package de.ghse.tgi.rezepteapp.Database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.Ingredient;
import de.ghse.tgi.rezepteapp.Recipe;


public class StorageRecipe {
    private ArrayList<Recipe> list = new ArrayList<>();

    public AppDatabase database;
    private final Context context;


    public StorageRecipe (Context c){
        context = c;
        database= new AppDatabase(c);
    }


    public void addRecipe(Recipe a){
        list.add(a);
        database.addRezeptToDataBase(a);
    }
    public void deleteRecipe(Recipe a) {
        list.remove(a);
        database.deleteRezept(a);
    }
    public int getCount(){
        return list.size();
    }

    public ArrayList<Integer> getFilteredIndexList(String filter){
        filter = filter.toLowerCase();
        ArrayList<Integer> filteredList = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            if (list.get(i).getName().toLowerCase().contains(filter)){
                filteredList.add(i);
            }
        }
        return filteredList;
    }
    public String getRecipeName(int index){return list.get(index).getName();}

    public Bitmap getRecipeImage(int index) throws IOException {
        Uri uri = list.get(index).getImageUri();
        if (uri == null) return null;

        InputStream iStream = context.getContentResolver().openInputStream(uri);                // save the image with Uri uri in a byte Array. (DB: Blob)
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int length;
        while ((length = iStream.read(buffer)) != -1) byteArrayOutputStream.write(buffer, 0, length);
        byte[] image = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        iStream.close();                                                                         // finish converting Uri to byte[]

        return BitmapFactory.decodeByteArray(image,0,image.length);                        //convert the byteArray to a Bitmap
    }

    public String getRecipeDescription(int index){return list.get(index).getDescription();}
    public ArrayList<String> getIngredients(){
        ArrayList<String> list = new ArrayList<>();
        list.add("tomato");
        list.add("apple");
        list.add("egg");
        list.add("fish");
        list.add("cucumber");
        list.add("salmon");
        return list;
    }
    private int getIngredientCount(int ID) {
        return list.get(ID).getIngredient().size();
    }
    public double[] getIngredientsAmount(int id){
        int count = getIngredientCount(id);
        ArrayList<Ingredient> ingredient = list.get(id).getIngredient();
        double[] amount = new double[count];
        for(int i = 0; i<count;i++){
            amount[i] = ingredient.get(i).getAmount();
        }
        return amount;
    }
    public String[] getIngredientsUnit(int id){
        int count = getIngredientCount(id);
        String[] unit = new String[count];
        ArrayList<Ingredient> ingredient = list.get(id).getIngredient();
        for(int i = 0; i<count;i++){
            unit[i] = ingredient.get(i).getUnit();
        }
        return unit;
    }
    public String[] getIngredientsName(int id){
        int count = getIngredientCount(id);
        String[] name = new String[count];
        ArrayList<Ingredient> ingredient = list.get(id).getIngredient();
        for(int i = 0; i<count;i++){
            name[i] = ingredient.get(i).getIngredient();
        }
        return name;
    }




}
