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
import de.ghse.tgi.rezepteapp.fragments.Home.ViewRecipe.ViewRecipeFragment;

/**
 * A {@link Fragment} subclass.
 * Manages Fragments: {@link InputFragment},{@link ViewRecipeFragment} and {@link ListRecipeFragment}
 */
public class HomeFragment extends Fragment {
    private MyViewPagerAdapter pager;
    private ListRecipeFragment listRecipeFragment = new ListRecipeFragment(this);
    private View view;

    /**
     * Class constructor.
     *
     * @param p adapter to notify when page should be changed.
     */
    public HomeFragment(MyViewPagerAdapter p) {
        pager = p;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    /**
     * method replaces the current Page with another.
     *
     * @param i index of Page. 0: {@link ListRecipeFragment}, 1: {@link InputFragment}, 2: {@link ViewRecipeFragment}.
     */
    public void replaceFragment(int i){
        Fragment frag;
        switch (i){
            case 0:
                frag = listRecipeFragment;
                listRecipeFragment.dataSetChanged();
                break;
            case 1:
                frag = new InputFragment(this);
                break;
            case 2:
                frag = new ViewRecipeFragment(this,listRecipeFragment.getClickedItem());
                break;
            default:
                frag = listRecipeFragment;
                break;
        }
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,frag);
        fragmentTransaction.commit();
    }

    /**
     * method to recieve the {@link android.content.Context} of the Fragment.
     * @return Returns active Activity (context) of the Fragment
     */
    public MainActivity getMainActivity(){
        return pager.getMainActivity();
    }
}