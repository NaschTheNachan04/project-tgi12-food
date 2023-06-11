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

     public ArrayList<DatabaseReaderRecipe> getRecipes(){
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursorRecipe = db.rawQuery("SELECT * FROM " +  "recipe", null);
      ArrayList<DatabaseReaderRecipe> DatabaseReaderRecipeArrayList = new ArrayList<>();
         cursorRecipe.moveToFirst();
         if (cursorRecipe.moveToFirst()) {

             do {
                 DatabaseReaderRecipeArrayList.add(new DatabaseReaderRecipe(
                         cursorRecipe.getString(1),
                         cursorRecipe.getString(2),
                         cursorRecipe.getInt(3)));
             } while (cursorRecipe.moveToNext());
         }
      return DatabaseReaderRecipeArrayList;
    }

    public ArrayList<DatabaseReaderIngredient> getIngredients(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorIngredient = db.rawQuery("SELECT * FROM " +  "zutat", null);
        ArrayList<DatabaseReaderIngredient> DatabaseReaderIngredientArrayList = new ArrayList<>();
        cursorIngredient.moveToFirst();
        if (cursorIngredient.moveToFirst()) {
            do {
                DatabaseReaderIngredientArrayList.add(new DatabaseReaderIngredient(
                        cursorIngredient.getString(1),
                        cursorIngredient.getString(2),
                        cursorIngredient.getDouble(3)));
            } while (cursorIngredient.moveToNext());
        }
        return DatabaseReaderIngredientArrayList;

    }

    public ArrayList<DatabaseReaderEvent> getEvent(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorEvent = db.rawQuery("SELECT * FROM " +  "event", null);
        ArrayList<DatabaseReaderEvent> DatabaseReaderEventArrayList = new ArrayList<>();
        cursorEvent.moveToFirst();
        if (cursorEvent.moveToFirst()) {
            do {
                DatabaseReaderEventArrayList.add(new DatabaseReaderEvent(
                        cursorEvent.getString(1),
                        cursorEvent.getInt(2),
                        cursorEvent.getInt(3)));
            } while (cursorEvent.moveToNext());
        }
        return DatabaseReaderEventArrayList;
    }
     public void addZutatToDataBase (Recipe a){
         SQLiteDatabase database = this.getWritableDatabase();
         AppDatabase appDatabase = null;
         ArrayList<Ingredient> ingredients=a.getIngredient();
         ContentValues c = new ContentValues();
         for(int i=0;i<ingredients.size();i++){
             boolean exist=true;
             ArrayList<DatabaseReaderIngredient> databaseIngredients = appDatabase.getIngredients();
             for (int j=databaseIngredients.size();j!=0;j--){
                 if (databaseIngredients.get(j).getNameIngredient()==ingredients.get(i).getIngredient()) {
                     exist=false;
                 }
             }
             if (exist==true){
                 c.put("name",ingredients.get(i).getIngredient());
                 c.put("einheit",ingredients.get(i).getUnit());
                 database.insert("zutat",null,c);
             }
         }
         database.close();
     }
}
