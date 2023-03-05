package de.ghse.tgi.rezepteapp.SQL;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import de.ghse.tgi.rezepteapp.Recipe;

@Database(entities = {Recipe.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract ReceptDao receptDao();
}