package de.ghse.tgi.rezepteapp.fragments.ListRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.R;

public class ListRecipeListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Integer> filteredRecipe;
    private LayoutInflater inflater;
    private boolean isUnfiltered = true;


    public ListRecipeListViewAdapter(Context ctx){
        context = ctx;
        inflater = LayoutInflater.from(ctx);
    }
    public void setFilteredRecipe(ArrayList<Integer> pRecipe){
        filteredRecipe = pRecipe;
    }
    public void setUnfiltered(boolean unfiltered){isUnfiltered=unfiltered;}
    @Override
    public int getCount() {
        if (isUnfiltered){return MainActivity.getStorage().getCount();}
        else{return filteredRecipe.size();}
    }

    @Override
    public Object getItem(int i) {
        if (isUnfiltered){return MainActivity.getStorage().getCount();}
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
        if (isUnfiltered){
            holder.txt.setText(MainActivity.getStorage().getRecipeName(position));
            holder.img.setImageResource(MainActivity.getStorage().getRecipeImage(position));
        }
        else {
            holder.txt.setText(MainActivity.getStorage().getRecipeName(filteredRecipe.get(position)));
            holder.img.setImageResource(MainActivity.getStorage().getRecipeImage(filteredRecipe.get(position)));
        }
        return view;
    }
    static class ViewHolder{
         TextView txt;
         ImageView img;
    }

}
