package de.ghse.tgi.rezepteapp.fragments.Home.ViewRecipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.fragments.Home.HomeFragment;

public class ViewRecipeFragment extends Fragment {
    private TextView name;
    private TextView description;
    private ImageView image;
    private Button btBack;
    private View view;
    private ViewRecipeControl ctrl;
    private HomeFragment homeFragment;

    public ViewRecipeFragment(HomeFragment h) {
        super();
        homeFragment =h;
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
        btBack = view.findViewById(R.id.btBackOnViewRecipe);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeFragment.replaceFragment(0);
            }
        });
        ctrl.onCreate();
        return view;
    }
    public void setRName(String name){
        this.name.setText(name);
    }
    public void setDescription(String description) {this.description.setText(description);}
    public void setImage(int img){this.image.setImageResource(img);}



}