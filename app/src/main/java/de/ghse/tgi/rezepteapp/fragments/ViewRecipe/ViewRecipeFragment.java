package de.ghse.tgi.rezepteapp.fragments.ViewRecipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.ghse.tgi.rezepteapp.MyViewPagerAdapter;
import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.StorageRecipe;

public class ViewRecipeFragment extends Fragment {
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView name;
    private TextView description;
    private ImageView image;
    private View view;
    private ViewRecipeControll ctrl;

    public ViewRecipeFragment(MyViewPagerAdapter p) {
        super();
        myViewPagerAdapter = p;
        ctrl = new ViewRecipeControll(this,p.getMainActivity());
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
        ctrl.onCreate();
        return view;
    }
    public void setRName(String name){
        this.name.setText(name);
    }
    public void setDescription(String description) {this.description.setText(description);}
    public void setImage(int img){this.image.setImageResource(img);}
}