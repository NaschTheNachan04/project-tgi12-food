package de.ghse.tgi.rezepteapp.fragments.Home.ViewRecipe;

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
    private LayoutInflater inflater;
    private ViewRecipeControl control;

    /**
     * class constructor
     * @param ctx Context of the Activity
     * @param ctrl control (VMC) of {@link ViewRecipeFragment}.
     */
    public ListIngredientsViewRecipeAdapter(Context ctx, ViewRecipeControl ctrl){
        inflater = LayoutInflater.from(ctx);
        control = ctrl;
    }


    @Override
    public int getCount() {
        return control.getIngredientCount();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

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
        holder.textView[0].setText(String.valueOf(control.getIngredientAmount(position)));
        holder.textView[1].setText(control.getIngredientUnit(position));
        holder.textView[2].setText(control.getIngredientName(position));
        return view;
    }

    /**
     * cache for the TextFields
     * used to prevent calling {@link View#findViewById(int)} every time {@link android.widget.ListView} is loaded
     */
    static class ViewHolder{
        TextView[] textView = new TextView[3];
    }
}
