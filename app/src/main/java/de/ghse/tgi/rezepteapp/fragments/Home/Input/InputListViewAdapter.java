package de.ghse.tgi.rezepteapp.fragments.Home.Input;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.Ingredient;
import de.ghse.tgi.rezepteapp.R;

public class InputListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Ingredient> ingredient = new ArrayList<>();


    public InputListViewAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ingredient.size();
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder =new ViewHolder();
            view = inflater.inflate(R.layout.content_list_ingredients, null);
            holder.amount = view.findViewById(R.id.tVIngredientAmount);
            holder.unit = view.findViewById(R.id.tVIngredientUnit);
            holder.name = view.findViewById(R.id.tVIngredientName);
            view.setTag(holder);
        }else holder = (ViewHolder) view.getTag();

        holder.amount.setText(String.valueOf(ingredient.get(position).getAmount()));
        holder.unit.setText(ingredient.get(position).getUnit());
        holder.name.setText(ingredient.get(position).getIngredient());
        return view;
    }

    /**
     * @return List of {@link Ingredient}s that were added to this recipe
     */
    public ArrayList<Ingredient> getRecipeIngredients(){return ingredient;}
    /**
     * Use this method to clear the textFields after all data has been saved.
     */
    public void clearTextFields() {
        ingredient.clear();
        notifyDataSetChanged();
    }

    /**
     * call this method to add an ingredient to the List
     * @param i ingredient to be added
     */
    public void addIngredient(Ingredient i){
        ingredient.add(i);
    }
    /**
     * cache for the UI-elements
     * used to prevent calling {@link View#findViewById(int)} every time {@link android.widget.ListView} is loaded
     */
    static class ViewHolder{
        TextView amount;
        TextView unit;
        TextView name;
    }
}
