package de.ghse.tgi.rezepteapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import de.ghse.tgi.rezepteapp.Recipe;

 public class AppDatabase extends SQLiteOpenHelper {
    private static final String databaseRecipe   ="recipeData";


     public AppDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
         super(context, name, factory, version, errorHandler);
     }



     @Override
     public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

     }

     public AppDatabase(Context c){
         super(c,databaseRecipe,null,1 );

     }
     @Override
     public void onCreate(SQLiteDatabase database) {
         // on below line we are creating
         // an sqlite query and we are
         // setting our column names
         // along with their data types.

         database.execSQL("CREATE TABLE zutat   (ZID INTEGER PRIMARY KEY AUTOINCREMENT,name CHAR,einheit text,vorratsmenge CHAR);");
         database.execSQL("CREATE TABLE recipe  (RID INTEGER PRIMARY KEY AUTOINCREMENT,name CHAR,beschreibung CHAR,Bild INTEGER);");
         database.execSQL("CREATE TABLE event   (EID INTEGER PRIMARY KEY AUTOINCREMENT,datum CHAR,stunden INTEGER,minuten INTEGER);");
         database.execSQL("CREATE TABLE rZutat  (ZRID INTEGER PRIMARY KEY AUTOINCREMENT,RID INTEGER,ZID INTEGER,menge DOUBLE,FOREIGN KEY(RID) REFERENCES recipe(RID),FOREIGN KEY(ZID) REFERENCES zutat(ZID));");
         database.execSQL("CREATE TABLE rEvent  (ERID INTEGER PRIMARY KEY AUTOINCREMENT,RID INTEGER,FOREIGN KEY(RID) REFERENCES recipe(RID));");
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
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("name",name);
        c.put("beschreibung",beschreibung);
        database.insert("recipe",null,c);
        database.close();

    }

    public void addZutatToDataBase (Recipe a){
        for(int i = a.getIngredient().size();i>0;i--){
            SQLiteDatabase database = this.getWritableDatabase();
            //database.
            //for (int k = i ;k<0;k--){    TODO probleme mit hinzufügen der Zutat in der Datenbank "Nachan"

                //if() {
                    String zutat = a.getIngredient().get(i).getIngredient();
                    String einheit = a.getIngredient().get(i).getUnit();
                    double menge = a.getIngredient().get(i).getAmount();
                    ContentValues c = new ContentValues();
                    ContentValues cv = new ContentValues();
                    c.put("name", zutat);
                    c.put("einheit", einheit);
                    cv.put("menge", menge);
                    database.insert("zutat", null, c);
                    database.insert("rZutat", null, cv);
                    database.close();
                //}
            //}

        }
    }

    public void addEventToDatabase(StorageRecipe store){

    }

    public void deleteRezept(Recipe a){


    }

    public void deleteZutaten(){

    }

    public void deleteEvent(){


    }

    public void getRecipe(){


    }

    public void getIngredient(){


    }
}
