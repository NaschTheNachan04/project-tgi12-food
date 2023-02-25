package de.ghse.tgi.rezepteapp;

import java.util.ArrayList;
import java.util.Locale;

public class StorageRecipe {
    private ArrayList<Recipe> list = new ArrayList<>();
    private ArrayList<Integer> emptyList = new ArrayList<>();

    public ArrayList<Recipe> getList(){return list;}
    public void addRecipe(Recipe a){
        list.add(a);
        emptyList.add(list.size()+1);
    }
    public void deleteRecipe(Recipe a) {
        list.remove(a);
        emptyList.remove(emptyList.size()-1);
    }
    public int getCount(){
        return list.size();
    }

    public ArrayList<Integer> getEmptyList() {
        return emptyList;
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
}
