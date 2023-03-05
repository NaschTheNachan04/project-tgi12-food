package de.ghse.tgi.rezepteapp.SQL;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import de.ghse.tgi.rezepteapp.Recipe;

@Dao
public interface ReceptDao {
    @Query("SELECT * FROM recipe")
    List<Recipe> getAll();

    @Query("SELECT * FROM recipe WHERE uid IN (:userIds)")
    List<Recipe> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM recipe WHERE name LIKE :name")
    Recipe  searchName (String name);

    @Query("SELECT * FROM recipe WHERE name LIKE :description")
    Recipe  searchDescription (String description);

    @Query("SELECT * FROM recipe WHERE name LIKE :ingredient")
    Recipe  searchIngredient (String ingredient);

    @Query("SELECT * FROM recipe WHERE name LIKE :image")
    Recipe  searchImage (int image);


}
