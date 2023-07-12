package de.ghse.tgi.rezepteapp.Database.Home.Input;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.Database.Home.ListRecipe.ListRecipeFragment;
import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.Database.Home.HomeFragment;

/**
 * A {@link Fragment} subclass.
 * View(MVC) to create new {@link de.ghse.tgi.rezepteapp.Recipe}.
 */

public class InputFragment extends Fragment {
    private InputControl control;
    private ListView lVIngredients;
    private View view;
    private final HomeFragment homeFragment;
    //header
    private EditText etName;
    private ImageView ivAddImage;
    private Uri imageUri;
    // footer
    private EditText etDescription;
    private AutoCompleteTextView actvIngredient;
    private EditText etUnit;
    private EditText etAmount;

    private final static int REQUEST_CODE = 42;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null) {
            view = inflater.inflate(R.layout.fragment_listview, container, false);
            lVIngredients = view.findViewById(R.id.listView);
            InputListViewAdapter adapter = new InputListViewAdapter(getContext());
            lVIngredients.setAdapter(adapter);
            control = new InputControl(this, adapter);
            setListViewHeaderAndFooter();
        }
        return view;
    }

    /**
     * called to leave this Fragment and return to {@link ListRecipeFragment}
     */
    public void finishTransaction() {
        clearTextFields();
        homeFragment.replaceFragment(HomeFragment.LIST_RECIPE);                //at buttonClick (save) switch Fragment to RecipeList
    }

    /**
     * called to initialise the TextFields outside the {@link ListView}.
     */
    private void setListViewHeaderAndFooter(){
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.content_fragment_input_top,lVIngredients,false);
        etName = header.findViewById(R.id.etInputName);
        setImageView(header);
        lVIngredients.addHeaderView(header);


        ViewGroup footer = (ViewGroup) getLayoutInflater().inflate(R.layout.content_fragment_input_bottom,lVIngredients,false);

        actvIngredient = footer.findViewById(R.id.aCTVIngredient);
        etUnit = footer.findViewById(R.id.eTInputIngredientUnit);
        etAmount = footer.findViewById(R.id.eTInputIngredientAmount);
        Button btSaveIngredient = footer.findViewById(R.id.btInputIngredientSave);

        ArrayList<String> ingredientList = MainActivity.getStorage().getIngredients();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, ingredientList);
        actvIngredient.setAdapter(adapter);

        btSaveIngredient.setOnClickListener(view -> control.saveIngredient());

        etDescription = footer.findViewById(R.id.etInputDescription);
        Button btSave = footer.findViewById(R.id.bSave);
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
    public String getIngredientName(){return actvIngredient.getText().toString();}
    /**
     * Use this method to get the Unit the user wrote.
     *
     * @return The unit the user selected
     */
    public String getIngredientUnit(){
        if (etUnit.getText()==null) return "";
        return etUnit.getText().toString();}
    /**
     * Use this method to get the amount the user wrote.
     *
     * @return The amount the user selected
     */
    public String getIngredientAmount(){return etAmount.getText().toString();}

    /**
     * Use this method to get the URI of the selected Image
     *
     * @return The URI of the image the user selected
     */
    public Uri getImageUri(){return imageUri;}
    /**
     * called to clear the textFields used to add an Ingredient
     * needs to be called to add another Ingredient
     */
    public void clearAddIngredientTextFields(){
        actvIngredient.setText("");
        etUnit.setText("");
        etAmount.setText("");
    }

    /**
     * called to reset the Fragment to its original state.
     * needs to be called to ann another Recipe
     */
    public void clearTextFields(){
        if (view != null) {
            etName.setText("");
            etDescription.setText("");
            imageUri = null;
            ivAddImage.setImageDrawable(null);
            clearAddIngredientTextFields();
        }
    }

    /**
     * called to initialize the {@link ImageView}
     * @param header view, the {@link ImageView} is in.
     */
    private void setImageView(ViewGroup header){
        ivAddImage = header.findViewById(R.id.IVAddImage);
        ivAddImage.setOnClickListener(view ->{
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            startActivityForResult(intent,REQUEST_CODE);
        });
    }

    /**
     * Receive the image from a previous call to
     * {@link #startActivityForResult(Intent, int)}.  This follows the
     * related Activity API as described there in
     * {@link Fragment#onActivityResult(int, int, Intent)}.
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return selected image data to the caller
     *                    (various data can be attached to Intent "extras").
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK & requestCode == REQUEST_CODE & data != null){
            imageUri = data.getData();
            requireContext().getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            ivAddImage.setImageURI(imageUri);
        }
    }
}