package de.ghse.tgi.rezepteapp.fragments.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.MyViewPagerAdapter;
import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.fragments.Home.Input.InputFragment;
import de.ghse.tgi.rezepteapp.fragments.Home.ListRecipe.ListRecipeFragment;
import de.ghse.tgi.rezepteapp.fragments.Home.ViewRecipe.ViewRecipeFromHomeFragment;
import de.ghse.tgi.rezepteapp.fragments.fragmentclasses.ViewRecipe.ViewRecipe.ViewRecipeFragment;

/**
 * A {@link Fragment} subclass.
 * Manages Fragments: {@link InputFragment},{@link ViewRecipeFragment} and {@link ListRecipeFragment}
 */
public class HomeFragment extends Fragment {
    private final ListRecipeFragment listRecipeFragment = new ListRecipeFragment(this);
    private final InputFragment inputFragment = new InputFragment(this);
    private final ViewRecipeFromHomeFragment viewRecipeFragment = new ViewRecipeFromHomeFragment(this);
    public static final int LIST_RECIPE = 0;
    public static final int INPUT = 1;
    public static final int VIEW_RECIPE = 2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(LIST_RECIPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    /**
     * method replaces the current Page with another
     *
     * @param i index of Page.<p>
     * {@link #LIST_RECIPE}: {@link ListRecipeFragment},<p>
     * {@link #INPUT}: {@link InputFragment},<p>
     * {@link #VIEW_RECIPE}: {@link ViewRecipeFragment}.
     */
    public void replaceFragment(int i){
        Fragment frag;
        switch (i){
            case LIST_RECIPE:
                frag = listRecipeFragment;
                break;
            case INPUT:
                frag = inputFragment;
                break;
            case VIEW_RECIPE:
                frag = viewRecipeFragment;
                break;
            default:
                frag = listRecipeFragment;
                break;
        }
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,frag);
        fragmentTransaction.commit();
    }

    public int getCurrentRecipe(){return listRecipeFragment.getClickedItem();}
}