package de.ghse.tgi.rezepteapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager2.widget.ViewPager2;

import de.ghse.tgi.rezepteapp.databinding.ActivityMainBinding;
import de.ghse.tgi.rezepteapp.fragments.ViewRecipe.ViewRecipeControl;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private MyViewPagerAdapter myViewPagerAdapter;
    private ViewPager2 viewPager2;
    private ViewRecipeControl VRCtrl;
    private static StorageRecipe storage = new StorageRecipe();

    public static StorageRecipe getStorage(){
        return storage;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setUserInputEnabled(false);
        myViewPagerAdapter = new MyViewPagerAdapter(this);
        viewPager2.setAdapter(myViewPagerAdapter);
        viewPager2.setCurrentItem(0);
        viewPager2.setUserInputEnabled(false);
    }

    public void setFrag(int pos){
        viewPager2.setCurrentItem(pos);
        if(pos == 2){
            VRCtrl.onCreate();
        }
    }
    public void addViewRecipeControl(ViewRecipeControl VRCtrl){this.VRCtrl = VRCtrl;}
}