package de.ghse.tgi.rezepteapp.Database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import de.ghse.tgi.rezepteapp.Recipe;

 public class AppDatabase extends SQLiteOpenHelper {
    final String databaseRecipe   ="recipeData.DB";


     public AppDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
         super(context, name, factory, version, errorHandler);
     }



     @Override
     public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

     }

     public AppDatabase(Context c){
         super(c,"recipeData",null,1 );

     }
     @Override
     public void onCreate(SQLiteDatabase db) {
         // on below line we are creating
         // an sqlite query and we are
         // setting our column names
         // along with their data types.
         SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseRecipe,null,null);

         database.execSQL("CREATE TABLE recipeData.recipe (RID INT ,name CHAR,beschreibung CHAR,ZRID int,Bild INT);");
         database.execSQL("CREATE TABLE recipeData.zutaten (ZID INT,name CHAR,einheit text,vorratsmenge CHAR,ZRID INT);");
         database.execSQL("CREATE TABLE recipeData.rZutat  (ZRID INT,RID INT,ZID INT,menge DOUBLE);");
         database.execSQL("CREATE TABLE recipeData.event   (EID INT,datum CHAR,stunden INT,minuten INT);");
         database.execSQL("CREATE TABLE recipeData.rEvent   (ERID INT,RID INT);");

     }
     /*public void createDatabase(){
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseRecipe,null,null);

        database.execSQL("CREATE TABLE recipeData.recipe (RID INT ,name CHAR,beschreibung CHAR,ZRID int,Bild INT);");
        database.execSQL("CREATE TABLE recipeData.zutaten (ZID INT,name CHAR,einheit text,vorratsmenge CHAR,ZRID INT);");
        database.execSQL("CREATE TABLE recipeData.rZutat  (ZRID INT,RID INT,ZID INT,menge DOUBLE);");
        database.execSQL("CREATE TABLE recipeData.event   (EID INT,datum CHAR,stunden INT,minuten INT);");
        database.execSQL("CREATE TABLE recipeData.rEvent   (ERID INT,RID INT);");
        database.close();
    }*/

    public void addRezeptToDataBase (Recipe a){
        String name = a.getName();
        String beschreibung = a.getDescription();
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseRecipe,null);
        database.execSQL("INSERT INTO recipe VALUES(RID,name,beschreibung)");

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
