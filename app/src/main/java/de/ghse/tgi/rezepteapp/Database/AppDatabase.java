package de.ghse.tgi.rezepteapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.Ingredient;
import de.ghse.tgi.rezepteapp.Recipe;

 public class AppDatabase extends SQLiteOpenHelper {
    private static final String databaseRecipe   ="recipeData";


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
         database.execSQL("CREATE TABLE recipe  (RID INTEGER PRIMARY KEY AUTOINCREMENT,name CHAR,beschreibung CHAR,Bild BLOB);");
         database.execSQL("CREATE TABLE event   (EID INTEGER PRIMARY KEY AUTOINCREMENT,datum CHAR,stunden INTEGER,minuten INTEGER);");
         database.execSQL("CREATE TABLE rZutat  (ZRID INTEGER PRIMARY KEY AUTOINCREMENT,RID INTEGER,ZID INTEGER,menge DOUBLE,FOREIGN KEY(RID) REFERENCES recipe(RID),FOREIGN KEY(ZID) REFERENCES zutat(ZID));");
         database.execSQL("CREATE TABLE rEvent  (ERID INTEGER PRIMARY KEY AUTOINCREMENT,RID INTEGER,FOREIGN KEY(RID) REFERENCES recipe(RID));");
     }


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


    public void addZutatVorratsMenge(){

    }

    public void addEventToDatabase(StorageRecipe store){

    }

    public void deleteRezept(String recipeName){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("recipe","name=?", new String[]{recipeName});
        db.close();
    }

    public void deleteZutaten(String zutatName){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("zutat","name=?", new String[]{zutatName});
        db.close();
    }

    public void deleteEvent(String eventName){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("event","name=?", new String[]{eventName});
        db.close();
    }

     public String getRecipeName(int i){
      String getName = null;
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursorRecipe = db.rawQuery("SELECT recipe.name FROM " +  "recipe", null);
         cursorRecipe.moveToFirst();
         cursorRecipe.moveToPosition(i);
         getName = cursorRecipe.getString(1);
         cursorRecipe.close();
      return getName;
    }

     public String getRecipeDescription(int i){
         String getDescription = null;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursorRecipe = db.rawQuery("SELECT recipe.beschreibung FROM " +  "recipe", null);
         cursorRecipe.moveToFirst();
         cursorRecipe.moveToPosition(i);
         getDescription = cursorRecipe.getString(1);
         cursorRecipe.close();
         return getDescription;
     }

     public byte[] getRecipeBild(int i){
         byte[] getBild = null;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursorRecipe = db.rawQuery("SELECT recipe.beschreibung FROM " +  "recipe", null);
         cursorRecipe.moveToFirst();
         cursorRecipe.moveToPosition(i);
         getBild = cursorRecipe.getBlob(1);
         cursorRecipe.close();
         return getBild;
     }

     public String getZutatName(int i){
         String getName = null;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursorRecipe = db.rawQuery("SELECT recipe.name FROM " +  "recipe", null);
         cursorRecipe.moveToFirst();
         cursorRecipe.moveToPosition(i);
         getName = cursorRecipe.getString(1);
         cursorRecipe.close();
         return getName;
     }

     public String getZutatEinheit(int i){
         String getEinheit = null;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursorRecipe = db.rawQuery("SELECT recipe.beschreibung FROM " +  "recipe", null);
         cursorRecipe.moveToFirst();
         cursorRecipe.moveToPosition(i);
         getEinheit = cursorRecipe.getString(1);
         cursorRecipe.close();
         return getEinheit;
     }

     public int getZutatMenge(int i){
         int getMenge=0;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursorRecipe = db.rawQuery("SELECT recipe.beschreibung FROM " +  "recipe", null);
         cursorRecipe.moveToFirst();
         cursorRecipe.moveToPosition(i);
         getMenge = cursorRecipe.getInt(1);
         cursorRecipe.close();
         return getMenge;
     }

     public String getEventDatum(int i){
         String getDatum = null;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursorRecipe = db.rawQuery("SELECT recipe.name FROM " +  "recipe", null);
         cursorRecipe.moveToFirst();
         cursorRecipe.moveToPosition(i);
         getDatum = cursorRecipe.getString(1);
         cursorRecipe.close();
         return getDatum;
     }

     public int getEventStunden(int i){
         int getStunden = 0;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursorRecipe = db.rawQuery("SELECT recipe.beschreibung FROM " +  "recipe", null);
         cursorRecipe.moveToFirst();
         cursorRecipe.moveToPosition(i);
         getStunden = cursorRecipe.getInt(1);
         cursorRecipe.close();
         return getStunden;
     }

     public int getEventMinuten(int i){
         int getMinuten=0;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursorRecipe = db.rawQuery("SELECT recipe.beschreibung FROM " +  "recipe", null);
         cursorRecipe.moveToFirst();
         cursorRecipe.moveToPosition(i);
         getMinuten = cursorRecipe.getInt(1);
         cursorRecipe.close();
         return getMinuten;
     }
 }
    /*
     public void addZutatToDataBase (Recipe a){
         SQLiteDatabase database = this.getWritableDatabase();
         ArrayList<Ingredient> ingredients=a.getIngredient();
         ContentValues c = new ContentValues();
         for(int i=0;i<ingredients.size();i++){
             boolean exist=true;
             ArrayList<DatabaseReaderIngredient> databaseIngredients = getIngredients();
             for (int j=databaseIngredients.size();j!=0;j--){
                 if (databaseIngredients.get(j).getNameIngredient()==ingredients.get(i).getIngredient()) {
                     exist=false;
                 }
             }
             if (exist){
                 c.put("name",ingredients.get(i).getIngredient());
                 c.put("einheit",ingredients.get(i).getUnit());
                 database.insert("zutat",null,c);
             }
         }
         database.close();
     }
     */
