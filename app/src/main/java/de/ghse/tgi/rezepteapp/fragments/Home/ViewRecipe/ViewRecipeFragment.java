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

    private View view;
    private int  itemId;
    private ViewRecipeControl ctrl;
    private HomeFragment homeFragment;

    //footer/header
    private TextView name;
    private TextView description;
    private ImageView image;
    private ListView listView;
    private ListIngredientsViewRecipeAdapter adapter;
    private Button btBack;


    /**
     * Class constructor.
     *
     * @param homeFragment to notify if shown {@link Fragment} should be changed.
     */
    public ViewRecipeFragment(HomeFragment homeFragment) {
        super();
        this.homeFragment =homeFragment;
        ctrl = new ViewRecipeControl(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        itemId = homeFragment.getCurrentRecipe();
        ctrl.setRecipe(itemId);                                             //update the Page to show the selected Recipe
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.fragment_listview, container, false);
            listView = view.findViewById(R.id.listView);
            setListViewFooterAndHeader();
            adapter = new ListIngredientsViewRecipeAdapter(homeFragment.getMainActivity(),ctrl);
            listView.setAdapter(adapter);
        }return view;
    }

    /**
     * Displays the Name given.
     * @param name
     */
    public void setRName(String name){
        this.name.setText(name);
    }

    /**
     * Displays the description given.
     * @param description
     */
    public void setDescription(String description) {
        this.description.setText(description);}

    /**
     * Displays the Image given.
     * @param img Image
     */
    public void setImage(int img){
        this.image.setImageResource(img);}

    /**
     * cache for UI-elements
     * used to prevent calling {@link View#findViewById(int)} every time {@link ViewRecipeFragment} is loaded
     */


    private void setListViewFooterAndHeader(){
        View header = getLayoutInflater().inflate(R.layout.content_fragment_view_recipe_header,listView,false);

        name = header.findViewById(R.id.tVViewRecipeName);
        image = header.findViewById(R.id.iVViewRecipeImage);
        btBack = header.findViewById(R.id.btBackOnViewRecipe);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeFragment.replaceFragment(HomeFragment.LIST_RECIPE);                        //onButtonPressed return to HomePage
            }
        });

        listView.addHeaderView(header);

        View footer = getLayoutInflater().inflate(R.layout.content_fragment_view_recipe_footer,listView,false);
        description = footer.findViewById(R.id.tVViewRecipeDescription);
        listView.addFooterView(footer);
    }
}