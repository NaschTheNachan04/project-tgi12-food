package de.ghse.tgi.rezepteapp.fragments.Home.Input;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.Database.DatabaseReaderIngredient;
import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.fragments.Home.HomeFragment;

/**
 * A {@link Fragment} subclass.
 * View(MVC) to create new {@link de.ghse.tgi.rezepteapp.Recipe}.
 */

public class InputFragment extends Fragment {
    private InputControl control;
    private ListView lVIngredients;
    private View view;
    private InputListViewAdapter adapter;
    private HomeFragment homeFragment;
    private EditText etName;
    // footer
    private EditText etDescription;
    private Button btSave;
    private AutoCompleteTextView aCTVIngredient;
    private EditText eTUnit;
    private EditText eTAmount;
    private Button btSaveIngredient;


    /**
     * Class constructor.
     * implements the associated homeFragment for navigation between Fragments.
     *
     * @param homeFragment HomeFragment to notify when page should be changed.
     */
    public InputFragment(HomeFragment homeFragment) {
        super();
        this.homeFragment = homeFragment;
    }


    @Override
    public void onResume(){
        super.onResume();
        clearTextFields();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null) {
            view = inflater.inflate(R.layout.fragment_listview, container, false);
            lVIngredients = view.findViewById(R.id.listView);
            adapter = new InputListViewAdapter(homeFragment.getMainActivity());
            lVIngredients.setAdapter(adapter);
            control = new InputControl(this, adapter);
            setListViewHeaderAndFooter();
        }
        return view;
    }

    /**
     * called to leave this Fragment and return to {@link de.ghse.tgi.rezepteapp.fragments.Home.ListRecipe.ListRecipeFragment}
     */
    public void finishTransaction() {
        homeFragment.replaceFragment(HomeFragment.LIST_RECIPE);                //at buttonClick (save) switch Fragment to RecipeList
    }

    /**
     * called to initialise the TextFields outside the {@link ListView}.
     */
    public void setListViewHeaderAndFooter(){
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.content_fragment_input_top,lVIngredients,false);
        etName = header.findViewById(R.id.etInputName);
        lVIngredients.addHeaderView(header);

        ViewGroup footer = (ViewGroup) getLayoutInflater().inflate(R.layout.content_fragment_input_bottom,lVIngredients,false);

        aCTVIngredient = footer.findViewById(R.id.aCTVIngredient);
        eTUnit = footer.findViewById(R.id.eTInputIngredientUnit);
        eTAmount = footer.findViewById(R.id.eTInputIngredientAmount);
        btSaveIngredient = footer.findViewById(R.id.btInputIngredientSave);

        ArrayList<DatabaseReaderIngredient> ingredientList = MainActivity.getStorage().getIngredients();
        ArrayAdapter<DatabaseReaderIngredient> adapter = new ArrayAdapter<>(homeFragment.getMainActivity(), android.R.layout.simple_dropdown_item_1line, ingredientList);
        aCTVIngredient.setAdapter(adapter);

        btSaveIngredient.setOnClickListener(view -> control.saveIngredient());

        etDescription = footer.findViewById(R.id.etInputDescription);
        btSave = footer.findViewById(R.id.bSave);
        btSave.setOnClickListener(view -> control.save());                              //save the recipe

        lVIngredients.addFooterView(footer);
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
     * Use this method to get the ingredient the user wrote.
     *
     * @return The ingredient the user selected
     */
    public String getIngredientName(){return aCTVIngredient.getText().toString();}
    /**
     * Use this method to get the Unit the user wrote.
     *
     * @return The unit the user selected
     */
    public String getIngredientUnit(){return eTUnit.getText().toString();}
    /**
     * Use this method to get the amount the user wrote.
     *
     * @return The amount the user selected
     */
    public String getIngredientAmount(){return eTAmount.getText().toString();}

    /**
     * called to clear the textFields used to add an Ingredient
     * needs to be called to add another Ingredient
     */
    public void clearAddIngredientTextFields(){
        aCTVIngredient.setText("");
        eTUnit.setText("");
        eTAmount.setText("");
    }

    /**
     * called to reset the Fragment to its original state.
     * needs to be called to ann another Recipe
     */
    private void clearTextFields(){
        if (view != null) {
            etName.setText("");
            etDescription.setText("");
            adapter.clearTextFields();
            clearAddIngredientTextFields();
        }
    }
}