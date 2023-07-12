package de.ghse.tgi.rezepteapp.Database.Home.ViewRecipe;

import androidx.fragment.app.Fragment;

import de.ghse.tgi.rezepteapp.Database.Home.HomeFragment;
import de.ghse.tgi.rezepteapp.fragments.fragmentclasses.ViewRecipe.ViewRecipe.ViewRecipeFragment;

public class ViewRecipeFromHomeFragment extends ViewRecipeFragment {

    private final HomeFragment homeFragment;

    /**
     * Class constructor.
     *
     * @param homeFragment to notify if shown {@link Fragment} should be changed.
     */
    public ViewRecipeFromHomeFragment(HomeFragment homeFragment){
        super();
        this.homeFragment = homeFragment;
    }

    @Override
    protected void returnToPreviousFragment() {
        homeFragment.replaceFragment(HomeFragment.LIST_RECIPE);
    }

    /**
     * method is called to get the Recipe it should show.
     */
    @Override
    protected void getItemID() {
        itemId = homeFragment.getCurrentRecipe();
        ctrl.setRecipe(itemId);                                             //update the Page to show the selected Recipe
    }
}
