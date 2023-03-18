package de.ghse.tgi.rezepteapp.fragments.Home.Input;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.fragments.Home.HomeFragment;

/**
 * A {@link Fragment} subclass.
 * View(MVC) to create new {@link de.ghse.tgi.rezepteapp.Recipe}.
 */

public class InputFragment extends Fragment {
    private Button btSave;
    private EditText etName;
    private EditText etDescription;
    private View view;
    private HomeFragment homeFragment;
    private InputControl controlInput;


    /**
     * Class constructor.
     * implements the associated homeFragment for navigation between Fragments.
     *
     * @param homeFragment HomeFragment to notify when page should be changed.
     */
    public InputFragment(HomeFragment homeFragment) {
        super();
        this.homeFragment = homeFragment;
        controlInput = new InputControl(this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_input, container, false);
        etName = view.findViewById(R.id.etInputName);
        etDescription = view.findViewById(R.id.etInputDescription);
        btSave = view.findViewById(R.id.bSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlInput.save();                              //save the recipe
                homeFragment.replaceFragment(0);                //at buttonClick (save) switch Fragment to RecipeList
            }
        });
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
    public ArrayList<String> getRecipeIngredients(){
        ArrayList<String> list = new ArrayList<>();
        return list;
    }
    /**
     * Use this method to clear the textFields after all data has been saved.
     */
    public void clearTextFields(){
        etName.setText(null);
        etDescription.setText(null);

    }
}