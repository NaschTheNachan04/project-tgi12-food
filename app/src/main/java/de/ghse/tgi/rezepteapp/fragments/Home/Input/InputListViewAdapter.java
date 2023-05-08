package de.ghse.tgi.rezepteapp.fragments.Home.Input;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.Ingredient;
import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.R;

public class InputListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Ingredient> ingredient = new ArrayList<>();
    private Context context;
    private InputControl control;
    private EditText etName;
    private Button btSave;
    private EditText etDescription;

    public InputListViewAdapter(Context context, InputFragment inputFragment){
        inflater = LayoutInflater.from(context);
        this.context =context;
        control = new InputControl(inputFragment,this);
    }

    @Override
    public int getCount() {
        return ingredient.size()+3;
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
       if(position==0){
            view = inflater.inflate(R.layout.content_fragment_input_top, null);
            etName = view.findViewById(R.id.etInputName);
       }else if (position == getCount()-2) {
           view = inflater.inflate(R.layout.content_add_ingredient, null);
           AutoCompleteTextView textView_auto = view.findViewById(R.id.aCTVIngredient);
           EditText editUnit = view.findViewById(R.id.eTInputIngredientUnit);
           EditText editAmount = view.findViewById(R.id.eTInputIngredientAmount);
           Button save = view.findViewById(R.id.btInputIngredientSave);

           ArrayList<String> ingredientList = MainActivity.getStorage().getIngredients();
           ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, ingredientList);
           textView_auto.setAdapter(adapter);

           save.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (!(editUnit.getText().toString().isEmpty() || editAmount.getText().toString().isEmpty() || textView_auto.getText().toString().isEmpty())) {
                       Ingredient i = new Ingredient();
                       i.setUnit(editUnit.getText().toString());
                       i.setAmount(Double.parseDouble(editAmount.getText().toString()));
                       i.setIngredient(textView_auto.getText().toString());
                       ingredient.add(i);
                       notifyDataSetChanged();
                   }
               }
           });
       }else if (position == getCount()-1) {
           view = inflater.inflate(R.layout.content_fragment_input_bottom, null);
           etDescription = view.findViewById(R.id.etInputDescription);
           btSave = view.findViewById(R.id.bSave);
           btSave.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   control.save();                              //save the recipe
               }
           });
       }else {
            int pos = position-1;
            view = inflater.inflate(R.layout.content_list_ingredients,null);
            TextView amount = view.findViewById(R.id.tVIngredientAmount);
            TextView unit = view.findViewById(R.id.tVIngredientUnit);
            TextView name = view.findViewById(R.id.tVIngredientName);
            amount.setText(String.valueOf(ingredient.get(pos).getAmount()));
            unit.setText(ingredient.get(pos).getUnit());
            name.setText(ingredient.get(pos).getIngredient());
        }
        return view;
    }
    /**
     * Use this method to get the text the user wrote
     * in the Text field of "recipeName".
     *
     * @return The Text, written in the Name EditText
     */
    public String getRecipeName(){
        return etName.getText().toString();
    }
    /**
     * Use this method to get the text the user wrote
     * in the Text field of "recipeDescription".
     *
     * @return The Text, written in the Description EditText
     */
    public String getRecipeDescription(){
        return etDescription.getText().toString();
    }
    /**
     * Use this method to get the list of ingredients the user
     * wrote.
     *
     * @return The list of ingredients the user selected
     */
    public ArrayList<Ingredient> getRecipeIngredients(){return ingredient;}
    /**
     * Use this method to clear the textFields after all data has been saved.
     */
    public void clearTextFields() {
        etName.setText(null);
        etDescription.setText(null);
        ingredient.clear();
        notifyDataSetChanged();
    }
}
