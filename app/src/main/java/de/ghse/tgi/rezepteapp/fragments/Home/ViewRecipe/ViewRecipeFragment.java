package de.ghse.tgi.rezepteapp.fragments.Home.ViewRecipe;

import android.os.Bundle;

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
    private TextView name;
    private TextView description;
    private ImageView image;
    private ListView listView;
    private ListIngredientsViewRecipeAdapter adapter;
    private Button btBack;
    private View view;
    private int  itemId;
    private ViewRecipeControl ctrl;
    private HomeFragment homeFragment;

    /**
     * Class constructor.
     *
     * @param homeFragment to notify if shown {@link Fragment} should be changed.
     * @param clickedItem index of the {@link de.ghse.tgi.rezepteapp.Recipe} that should be shown.
     */
    public ViewRecipeFragment(HomeFragment homeFragment,int clickedItem) {
        super();
        this.homeFragment =homeFragment;
        itemId = clickedItem;
        ctrl = new ViewRecipeControl(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view_recipe, container, false);
        name = view.findViewById(R.id.tVViewRecipeName);
        image = view.findViewById(R.id.iVViewRecipeImage);
        description = view.findViewById(R.id.tVViewRecipeDescription);
        ctrl.onCreate(itemId);                                             //update the Page to show the selected Recipe
        listView = view.findViewById(R.id.lVListIngredients);
        adapter = new ListIngredientsViewRecipeAdapter(homeFragment.getMainActivity(),itemId,ctrl);
        listView.setAdapter(adapter);
        btBack = view.findViewById(R.id.btBackOnViewRecipe);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeFragment.replaceFragment(0);                        //onButtonPressed return to HomePage
            }
        });
        ctrl.setListViewHeight(listView);
        return view;
    }

    public ListView getListView(){ return listView;}

    /**
     * Shows the Name given.
     *
     * @param name
     */
    public void setRName(String name){
        this.name.setText(name);
    }

    /**
     * Shows the description given.
     * @param description
     */
    public void setDescription(String description) {this.description.setText(description);}

    /**
     * Shows the Image given.
     *
     * @param img Image
     */
    public void setImage(int img){this.image.setImageResource(img);}



}