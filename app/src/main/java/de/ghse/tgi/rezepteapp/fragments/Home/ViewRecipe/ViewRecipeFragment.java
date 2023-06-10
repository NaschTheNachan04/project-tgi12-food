package de.ghse.tgi.rezepteapp.fragments.Home.ViewRecipe;

import android.graphics.Bitmap;
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
import de.ghse.tgi.rezepteapp.fragments.Home.HomeFragment;

/**
 * A {@link Fragment} subclass.
 * View(MVC) to show a single {@link de.ghse.tgi.rezepteapp.Recipe}.
 */
public class ViewRecipeFragment extends Fragment {

    private View view;
    private ViewRecipeControl ctrl;
    private final HomeFragment homeFragment;

    //footer/header
    private TextView name;
    private TextView description;
    private ImageView image;
    private ListView listView;


    /**
     * Class constructor.
     *
     * @param homeFragment to notify if shown {@link Fragment} should be changed.
     */
    public ViewRecipeFragment(HomeFragment homeFragment) {
        super();
        this.homeFragment =homeFragment;
    }

    @Override
    public void onResume(){
        super.onResume();
        int itemId = homeFragment.getCurrentRecipe();
        ctrl.setRecipe(itemId);                                             //update the Page to show the selected Recipe
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.fragment_listview, container, false);
            listView = view.findViewById(R.id.listView);
            ListIngredientsViewRecipeAdapter adapter = new ListIngredientsViewRecipeAdapter(homeFragment.getMainActivity());
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
    public void setImage(Bitmap image){
        this.image.setImageBitmap(image);}

    /**
     * initializes the View above and below the Ingredients
     */
    private void setListViewFooterAndHeader(){
        View header = getLayoutInflater().inflate(R.layout.content_fragment_view_recipe_header,listView,false);

        name = header.findViewById(R.id.tVViewRecipeName);
        image = header.findViewById(R.id.iVViewRecipeImage);
        Button btBack = header.findViewById(R.id.btBackOnViewRecipe);
        btBack.setOnClickListener(view -> {
            homeFragment.replaceFragment(HomeFragment.LIST_RECIPE);                        //onButtonPressed return to HomePage
        });

        listView.addHeaderView(header);

        View footer = getLayoutInflater().inflate(R.layout.content_fragment_view_recipe_footer,listView,false);
        description = footer.findViewById(R.id.tVViewRecipeDescription);
        listView.addFooterView(footer);
    }
}