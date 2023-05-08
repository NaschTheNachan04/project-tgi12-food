package de.ghse.tgi.rezepteapp.fragments.Home.ViewRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import de.ghse.tgi.rezepteapp.R;

public class ListIngredientsViewRecipeAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int recipe;
    private TextView[] textView = new TextView[3];
    private ViewRecipeControl control;

    public ListIngredientsViewRecipeAdapter(Context ctx,int recipeID, ViewRecipeControl ctrl){
        inflater = LayoutInflater.from(ctx);
        recipe = recipeID;
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
        if (view==null){
            view = inflater.inflate(R.layout.content_list_ingredients,null);
            textView[0] = view.findViewById(R.id.tVIngredientAmount);
            textView[1] = view.findViewById(R.id.tVIngredientUnit);
            textView[2] = view.findViewById(R.id.tVIngredientName);
        }
        textView[0].setText(String.valueOf(control.getIngredientAmount(position)));
        textView[1].setText(control.getIngredientUnit(position));
        textView[2].setText(control.getIngredientName(position));
        return view;
    }
}
