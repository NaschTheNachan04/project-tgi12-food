package de.ghse.tgi.rezepteapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

import de.ghse.tgi.rezepteapp.Database.StorageRecipe;
import de.ghse.tgi.rezepteapp.databinding.ActivityMainBinding;

/**
 * A {@link AppCompatActivity} subclass.
 * Primary Activity.
 * Manages interactions between {@link androidx.fragment.app.Fragment}s.
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;
    private static StorageRecipe storage;

    /**
     * method to get the connection to the database.
     * @return Returns the Model(MVC) of the App.
     */
    public static StorageRecipe getStorage(){
        return storage;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        storage = new StorageRecipe(this);
        updateLanguage();
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        viewPager2 = findViewById(R.id.viewPager2);
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this);
        viewPager2.setAdapter(myViewPagerAdapter);
        viewPager2.setCurrentItem(0);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    viewPager2.setCurrentItem(0);                               //connect the BottomNavigationMenu with Viewpager2
                    break;
                case R.id.calendar:
                    viewPager2.setCurrentItem(1);
                    break;
                case R.id.settings:
                    viewPager2.setCurrentItem(2);
                    break;
            }
            return true;
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.home);          //connect the Viewpager2 with BottomNavigationMenu
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.calendar);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.settings);
                        break;
                }
            }
        });
    }

    /**
     * method that updates the Language of the App.
     */
    private void updateLanguage(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String languageCode = preferences.getString("language",getString(R.string.english));        //get selected Language
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
    }
}