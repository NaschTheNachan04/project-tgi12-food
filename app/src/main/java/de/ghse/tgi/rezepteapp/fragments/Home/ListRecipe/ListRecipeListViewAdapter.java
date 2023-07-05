package de.ghse.tgi.rezepteapp.fragments.Home.ListRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.R;

/**
 * A {@link BaseAdapter} subclass.
 * Adapter for {@link android.widget.ListView} in {@link ListRecipeFragment}.
 * Manages the shown ListItems
 */
public class ListRecipeListViewAdapter extends BaseAdapter {
    private ArrayList<Integer> filteredRecipe;
    private LayoutInflater inflater;
    private boolean isUnfiltered = true;

    /**
     * Class constructor.
     * inflates itself.
     *
     * @param ctx Context of associated Activity
     */
    public ListRecipeListViewAdapter(Context ctx){
        inflater = LayoutInflater.from(ctx);
    }

    /**
     * method to only show specific {@link de.ghse.tgi.rezepteapp.Recipe}s.
     *
     * @param pRecipe List of ID's of the filtered {@link de.ghse.tgi.rezepteapp.Recipe}s
     */
    public void setFilteredRecipe(ArrayList<Integer> pRecipe){
        filteredRecipe = pRecipe;
    }

    /**
     * method to switch between filtered and unfiltered List.
     *
     * @param unfiltered true: all {@link de.ghse.tgi.rezepteapp.Recipe}s; false: only shows the filtered List.
     */
    public void setUnfiltered(boolean unfiltered){isUnfiltered=unfiltered;}

    /**
     * @return amount of Recipes shown according to the filters.
     */
    @Override
    public int getCount() {
        if (isUnfiltered){return MainActivity.getStorage().getCount();}
        else{return filteredRecipe.size();}
    }

    /**
     * @param i position in the List
     * @return ID of Recipe at position i.
     */
    @Override
    public Object getItem(int i) {
        if (isUnfiltered){return i;}
        else{return filteredRecipe.get(i);}
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.activity_content_view_recipe, null);
            holder = new ViewHolder();
            holder.txt = view.findViewById(R.id.tVRecipeName);
            holder.img = view.findViewById(R.id.iVRecipeImage);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (isUnfiltered)   setView(position+1,holder);
        else                setView(filteredRecipe.get(position),holder);
        return view;
    }

    /**
     * method to fill each ListViewElement with its corresponding data
     * @param recipeID ID of the Recipe
     * @param holder {@link ViewHolder} the UI elements are cached in
     */
    private void setView(int recipeID,ViewHolder holder){
        holder.txt.setText(MainActivity.getStorage().getRecipeName(recipeID));                      //set the Name of RecipeListViewItem as Recipe with ID position.
        holder.img.setImageBitmap(MainActivity.getStorage().getRecipeImage(recipeID));            //set the Image of RecipeListViewItem as Recipe with ID position.
    }

    /**
     * cache for the UI-elements
     * used to prevent calling {@link View#findViewById(int)} every time {@link android.widget.ListView} is loaded
     */
    static class ViewHolder{
         TextView txt;
         ImageView img;
    }

}
