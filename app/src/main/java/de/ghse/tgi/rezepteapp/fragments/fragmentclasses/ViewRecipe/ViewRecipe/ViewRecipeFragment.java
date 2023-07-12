package de.ghse.tgi.rezepteapp.fragments.fragmentclasses.ViewRecipe.ViewRecipe;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import de.ghse.tgi.rezepteapp.R;

/**
 * A {@link Fragment} subclass to show a specific Recipe
 * View(MVC) to show a single {@link de.ghse.tgi.rezepteapp.Recipe}.
 */
public abstract class ViewRecipeFragment extends Fragment {

    private View view;
    protected ViewRecipeControl ctrl;

    //footer/header
    private TextView name;
    private TextView description;
    private ImageView image;
    private ListView listView;
    protected int itemId;


    @Override
    public void onResume(){
        super.onResume();
        getItemID();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.fragment_listview, container, false);
            listView = view.findViewById(R.id.listView);
            ListIngredientsViewRecipeAdapter adapter = new ListIngredientsViewRecipeAdapter(getActivity());
            listView.setAdapter(adapter);
            ctrl = new ViewRecipeControl(this,adapter);
            setListViewFooterAndHeader();
        }return view;
    }

    /**
     * Displays the Name given.
     * @param name name of the Recipe
     */
    public void setRName(String name){
        this.name.setText(name);
    }

    /**
     * Displays the description given.
     * @param description of the Recipe
     */
    public void setDescription(String description) {
        this.description.setText(description);}

    /**
     * Displays the Image given.
     * @param image Image of the Recipe
     */
    public void setImage(Uri image){
        try {
            this.image.setImageURI(image);
        }catch (Exception e){
            this.image.setImageResource(R.drawable.ic_baseline_hide_image_24);
        }

    }

    /**
     * initializes the View above and below the Ingredients
     */
    private void setListViewFooterAndHeader(){
        View header = getLayoutInflater().inflate(R.layout.content_fragment_view_recipe_header,listView,false);

        name = header.findViewById(R.id.tVViewRecipeName);
        image = header.findViewById(R.id.iVViewRecipeImage);
        Button btBack = header.findViewById(R.id.btBackOnViewRecipe);
        btBack.setOnClickListener(view -> returnToPreviousFragment());
        listView.addHeaderView(header);

        View footer = getLayoutInflater().inflate(R.layout.content_fragment_view_recipe_footer,listView,false);
        description = footer.findViewById(R.id.tVViewRecipeDescription);
        listView.addFooterView(footer);
    }

    /**
     * method is called to leave this View
     */
    protected abstract void returnToPreviousFragment();

    /**
     * method is called to get the Recipe it should show. <p>
     * It is mandatory to call {@link  #ctrl#setRecipe(int)} during this process.
     */
    protected abstract void getItemID();
}