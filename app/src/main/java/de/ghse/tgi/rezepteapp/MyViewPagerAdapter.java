package de.ghse.tgi.rezepteapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import de.ghse.tgi.rezepteapp.fragments.Input.InputFragment;
import de.ghse.tgi.rezepteapp.fragments.ListRecipe.ListRecipeFragment;
import de.ghse.tgi.rezepteapp.fragments.ViewRecipe.ViewRecipeFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    private MainActivity main;
    public MyViewPagerAdapter(@NonNull MainActivity mainActivity) {
        super(mainActivity);
        main = mainActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ListRecipeFragment(this);
            case 1:
                return new InputFragment(this);
            case 2:
                return new ViewRecipeFragment(this);
            default:
                return new ListRecipeFragment(this);
        }
    }

    public MainActivity getMainActivity(){return main;}
    @Override
    public int getItemCount() {
        return 3;
    }
}
