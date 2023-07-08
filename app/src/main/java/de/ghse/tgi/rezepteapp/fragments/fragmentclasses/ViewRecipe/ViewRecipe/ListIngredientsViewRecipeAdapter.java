package de.ghse.tgi.rezepteapp.fragments.fragmentclasses.ViewRecipe.ViewRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import de.ghse.tgi.rezepteapp.R;

/**
 * a {@link BaseAdapter} subclass
 * manages the Ingredients of {@link ViewRecipeFragment}s Listview
 */
public class ListIngredientsViewRecipeAdapter extends BaseAdapter {
    private final LayoutInflater inflater;

    private double[] ingredientAmount;
    private String[] ingredientUnit;
    private String[] ingredientName;
    private int count=0;

    /**
     * class constructor
     * @param ctx Context of the Activity
     */
    public ListIngredientsViewRecipeAdapter(Context ctx ){
        inflater = LayoutInflater.from(ctx);
    }


    @Override
    public int getCount() {return count;}

    @Override
    public Object getItem(int i) {return i;}

    @Override
    public long getItemId(int i) {return i;}

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        //link view and TextViews from xml to java
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.content_list_ingredients,null);
            holder.textView[0] = view.findViewById(R.id.tVIngredientAmount);
            holder.textView[1] = view.findViewById(R.id.tVIngredientUnit);
            holder.textView[2] = view.findViewById(R.id.tVIngredientName);
            view.setTag(holder);
        }else holder = (ViewHolder) view.getTag();

        //set the params of selected Ingredient
        holder.textView[0].setText(String.valueOf(ingredientAmount[position]));
        holder.textView[1].setText(ingredientUnit[position]);
        holder.textView[2].setText(ingredientName[position]);
        return view;
    }

    /**
     * method to set how much of each Ingredient is needed
     * @param ingredientAmount Array of the Amounts needed for each Ingredient
     */
    public void setIngredientAmount(double[] ingredientAmount) {this.ingredientAmount = ingredientAmount;}

    /**
     * method to set in what unit each Ingredient is measured
     * @param ingredientUnit Array of the Units used by each Ingredient
     */
    public void setIngredientUnit(String[] ingredientUnit) {
        this.ingredientUnit = ingredientUnit;
    }

    /**
     * method to set the names of each Ingredient
     * @param ingredientName Array of the Ingredient's names
     */
    public void setIngredientName(String[] ingredientName) {
        this.ingredientName = ingredientName;
        if (ingredientName != null) count= ingredientName.length;
    }

    /**
     * cache for the TextFields
     * used to prevent calling {@link View#findViewById(int)} every time {@link android.widget.ListView} is loaded
     */
    static class ViewHolder{
        TextView[] textView = new TextView[3];
    }
}
