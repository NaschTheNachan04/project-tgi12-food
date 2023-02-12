package de.ghse.tgi.rezepteapp.fragments.ListRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.Recipe;

public class ListRecipeListViewAdapte extends BaseAdapter {
    private Context ctxt;
    private ArrayList<de.ghse.tgi.rezepteapp.Recipe> Recipe;
    private LayoutInflater inflater;


    public ListRecipeListViewAdapte(Context ctx, ArrayList<Recipe> pRecipe){
        ctxt = ctx;
        Recipe = pRecipe;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return Recipe.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_content_view_recipe,null);
        TextView tVName = view.findViewById(R.id.tVRecipeName);
        ImageView iVRecipeImage = view.findViewById(R.id.iVRecipeImage);
        tVName.setText(Recipe.get(i).getName());
        iVRecipeImage.setImageResource(Recipe.get(i).getImage());
        return view;
    }
}
