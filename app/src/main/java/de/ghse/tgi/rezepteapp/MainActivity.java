package de.ghse.tgi.rezepteapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import de.ghse.tgi.rezepteapp.SQL.AppDatabase;
import de.ghse.tgi.rezepteapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private AppDatabase database;
    private ActivityMainBinding binding;
    private MyViewPagerAdapter myViewPagerAdapter;
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;
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
        myViewPagerAdapter = new MyViewPagerAdapter(this);
        viewPager2.setAdapter(myViewPagerAdapter);
        viewPager2.setCurrentItem(0);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.calendar:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.settings:
                        viewPager2.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.home);
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
}