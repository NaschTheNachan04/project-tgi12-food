package de.ghse.tgi.rezepteapp.fragments.Home.Input;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.Ingredient;
import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.fragments.Home.HomeFragment;

/**
 * A {@link Fragment} subclass.
 * View(MVC) to create new {@link de.ghse.tgi.rezepteapp.Recipe}.
 */

public class InputFragment extends Fragment {
    private ListView lVIngredients;
    private View view;
    private InputListViewAdapter adapter;
    private HomeFragment homeFragment;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_input, container, false);
        lVIngredients = view.findViewById(R.id.lVIngredients);
        adapter = new InputListViewAdapter(homeFragment.getMainActivity(), this);
        lVIngredients.setAdapter(adapter);
        return view;
    }

    public void finishTransaction() {
        homeFragment.replaceFragment(0);                //at buttonClick (save) switch Fragment to RecipeList
    }
}