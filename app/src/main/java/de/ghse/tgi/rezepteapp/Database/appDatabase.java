package de.ghse.tgi.rezepteapp.Database;

import android.database.sqlite.SQLiteDatabase;

public class appDatabase {
    final String databaseRecipe   ="recipe.DB";
    public int RID=1;
    public void createDatabase(){
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseRecipe,null);
        database.execSQL("CREATE TABLE recipe (RID int,name String,beschreibung String,ZRID int,Bild int)");
        database.execSQL("CREATE TABLE zutaten (ZID int,name String,einheit String,vorratsmenge String,ZRID int)");
        database.execSQL("CREATE TABLE rZutat  (ZRID int,RID int,ZID int,menge double)");
        database.execSQL("CREATE TABLE event   (EID int,datum String,stunden int,minuten int)");
        database.execSQL("CREATE TABLE rEvent   (ERID int,RID int)");
    }

    public void addRezeptToDataBase (StorageRecipe store){
        String name = store.getRecipeName(RID);
        String beschreibung = store.getRecipeDescription(RID);
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseRecipe,null);
        database.execSQL("INSERT INTO recipe VALUES(RID,name,beschreibung)");
    }

    public void addZutatToDataBase (){

    }

    public void addEventToDatabase(){

    }

    public void deleteRezept(){


    }

    public void deleteZutaten(){

    }

    public void deleteEvent(){


    }
}
