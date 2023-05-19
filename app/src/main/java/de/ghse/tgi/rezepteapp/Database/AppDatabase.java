package de.ghse.tgi.rezepteapp.Database;

import android.database.sqlite.SQLiteDatabase;

import de.ghse.tgi.rezepteapp.Recipe;

public class AppDatabase {
    final String databaseRecipe   ="recipe.DB";
    public static int RID=1;
    public void createDatabase(){
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseRecipe,null);
        database.execSQL("CREATE TABLE recipe (RID int,name String,beschreibung String,ZRID int,Bild int)");
        database.execSQL("CREATE TABLE zutaten (ZID int,name String,einheit String,vorratsmenge String,ZRID int)");
        database.execSQL("CREATE TABLE rZutat  (ZRID int,RID int,ZID int,menge double)");
        database.execSQL("CREATE TABLE event   (EID int,datum String,stunden int,minuten int)");
        database.execSQL("CREATE TABLE rEvent   (ERID int,RID int)");
        database.close();
    }

    public void addRezeptToDataBase (Recipe a){
        String name = a.getName();
        String beschreibung = a.getDescription();
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseRecipe,null);
        database.execSQL("INSERT INTO recipe VALUES(RID,name,beschreibung)");
        RID++;
    }

    public void addZutatToDataBase (){

    }

    public void addEventToDatabase(StorageRecipe store){
      store.getList();
      SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseRecipe,null);
      database.execSQL("INSERT INTO recipe VALUES (RID)");
    }

    public void deleteRezept(Recipe a){


    }

    public void deleteZutaten(){

    }

    public void deleteEvent(){


    }
}
