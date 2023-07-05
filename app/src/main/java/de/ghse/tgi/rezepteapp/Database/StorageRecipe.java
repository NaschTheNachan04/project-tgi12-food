package de.ghse.tgi.rezepteapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.Ingredient;
import de.ghse.tgi.rezepteapp.Recipe;


public class StorageRecipe extends SQLiteOpenHelper {
    private final ArrayList<Recipe> list = new ArrayList<>();
    private static final String databaseRecipe   ="recipeData";

    private final Context context;


    public StorageRecipe (Context c){
        super(c,databaseRecipe,null,1 );
        context = c;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.

        database.execSQL("CREATE TABLE IF NOT EXISTS zutat   (ZID INTEGER PRIMARY KEY AUTOINCREMENT,name CHAR,vorratsEinheit text,vorratsmenge CHAR);");
        database.execSQL("CREATE TABLE IF NOT EXISTS recipe  (RID INTEGER PRIMARY KEY AUTOINCREMENT,name CHAR,beschreibung CHAR,Bild BLOB);");
        database.execSQL("CREATE TABLE IF NOT EXISTS event   (EID INTEGER PRIMARY KEY AUTOINCREMENT,datum CHAR,stunden INTEGER,minuten INTEGER);");
        database.execSQL("CREATE TABLE IF NOT EXISTS rZutat  (ZRID INTEGER PRIMARY KEY AUTOINCREMENT,RID INTEGER,ZID INTEGER,menge DOUBLE,einheit text,FOREIGN KEY(RID) REFERENCES recipe(RID),FOREIGN KEY(ZID) REFERENCES zutat(ZID));");
        database.execSQL("CREATE TABLE IF NOT EXISTS rEvent  (ERID INTEGER PRIMARY KEY AUTOINCREMENT,RID INTEGER,FOREIGN KEY(RID) REFERENCES recipe(RID));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void addRecipe(Recipe a){
        list.add(a);
        ArrayList<Ingredient> ingredient=a.getIngredient();
        String name = a.getName();
        String beschreibung = a.getDescription();
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("name",name);
        c.put("beschreibung",beschreibung);
        database.insert("recipe",null,c);
        database.close();

        database =this.getReadableDatabase();
        Cursor cursor= database.rawQuery("SELECT r.RID FROM recipe.r WHERE r.name ="+name+"AND r.beschreibung="+beschreibung,null);
        cursor.moveToFirst();
        int rid = cursor.getInt(0);
        cursor.close();
        for(int i = 0;i<ingredient.size();i++){
            addIngredient(ingredient.get(i),rid);

        }

    }

    public int getCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(r.RID) FROM recipe r",null);
        int count=0;
        if(cursor.moveToFirst()) {
            count=cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public ArrayList<Integer> getFilteredIndexList(String filter){
        ArrayList<Integer> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT r.RID FROM recipe r WHERE r.name LIKE  '"+filter+"%'" ,null);
        if (cursor.moveToFirst()) {
           do {
               list.add(cursor.getInt(0));
           } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public String getRecipeName(int index){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT recipe.name FROM recipe  WHERE rID="+index,null);
        cursorRecipe.moveToFirst();
        String name = cursorRecipe.getString(0);
        cursorRecipe.close();
        return name;
    }

    public Bitmap getRecipeImage(int index) throws IOException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT recipe.bild FROM recipe WHERE rID="+index, null);
        cursorRecipe.moveToFirst();
        byte [] image = cursorRecipe.getBlob(1);
        cursorRecipe.close();

        return BitmapFactory.decodeByteArray(image,0,image.length);                        //convert the byteArray to a Bitmap
    }

    public String getRecipeDescription(int index){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT recipe.beschreibung FROM  recipe WHERE rID="+index, null);
        cursorRecipe.moveToFirst();
        String description = cursorRecipe.getString(0);
        cursorRecipe.close();
        return description;
    }
    private void addIngredient (Ingredient ingredient,int rid){
        SQLiteDatabase wdb = this.getWritableDatabase();
        SQLiteDatabase rdb = this.getReadableDatabase();

        Cursor cursor = rdb.rawQuery("SELECT COUNT(z.name) FROM zutat z WHERE z.name LIKE '"+ingredient.getName()+"'",null);
        cursor.moveToFirst();
        if(cursor.getInt(0)==0){
            ContentValues  c = new ContentValues();
            String name = ingredient.getName();
            String unit= ingredient.getUnit();
            c.put("name",name);
            c.put("vorratsEinheit",unit);
            c.put("vorratsmenge",0);
            wdb.insert("zutat",null,c);
            wdb.close();
        }
        cursor.close();
        //Add Ingredient in rZutat
        cursor =rdb.rawQuery("SELECT z.ZID FROM zutat z WHERE z.name LIKE'"+ingredient.getName()+"'",null);
        cursor.moveToFirst();
        int zid=cursor.getInt(0);
        cursor.close();
        double menge=ingredient.getAmount();
        String einheit=ingredient.getUnit();
        ContentValues  c = new ContentValues();
        c.put("menge",menge);
        c.put("einheit",einheit);
        c.put("ZID",zid);
        c.put("RID",rid);
        wdb.insert("rZutat",null,c);
        wdb.close();
        rdb.close();

    }
    public ArrayList<String> getIngredients(){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT z.name FROM zutat z ", null);
        cursorRecipe.moveToFirst();
        do{
            list.add(cursorRecipe.getString(0));
        } while (cursorRecipe.moveToNext());
        cursorRecipe.close();
        return  list;
    }
    private int getIngredientCount(int id) {
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor= db.rawQuery("SELECT COUNT(rz.ID) FROM rZutat rz  WHERE rz.RID ="+id,null);
      int count=0;
      if(cursor.moveToFirst()){
          count = cursor.getInt(0);
      }
      cursor.close();
      return count;
    }
    public double[] getIngredientsAmount(int id){
        double[] list = new double[getIngredientCount(id)];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rz.menge FROM rZutat rz WHERE rz.RID ="+id+" ORDER BY rz.ZID",null);
        if(cursor.moveToFirst()){
            for(int i=0;i<list.length;i++){
                list[i]=cursor.getDouble(0);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }
    public String[] getIngredientsUnit(int id){
        String[] list = new String[getIngredientCount(id)];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rz.einheit FROM rZutat rz WHERE rz.RID ="+id+" ORDER BY rz.ZID",null);
        if(cursor.moveToFirst()){
            for(int i=0;i<list.length;i++){
                list[i]=cursor.getString(0);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }
    public String[] getIngredientsName(int id){
        String[] list = new String[getIngredientCount(id)];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT z.name FROM zutat z,rZuat rz WHERE rz.ZID = z.ZID AND rz.RID ="+id+" ORDER BY rz.ZID",null);
        if(cursor.moveToFirst()){
            for(int i=0;i<list.length;i++){
                list[i]=cursor.getString(0);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    public double getZutatVorratsmenge(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT z.vorratsmenge FROM zutat z WHERE z.ZID="+id, null);
        cursorRecipe.moveToFirst();
        cursorRecipe.moveToPosition(id);
        double menge = cursorRecipe.getInt(0);
        cursorRecipe.close();
        return menge;
    }
    public void deleteZutaten(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("zutat","zid=?"+id,null);
        db.close();
    }

    public void deleteEvent(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("event","eid=?"+id,null);
        db.close();
    }



    public String getEventDatum(int i){
        String getDatum = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT event.name FROM " +  "event", null);
        cursorRecipe.moveToFirst();
        cursorRecipe.moveToPosition(i);
        getDatum = cursorRecipe.getString(1);
        cursorRecipe.close();
        return getDatum;
    }

    public int getEventStunden(int i){
        int getStunden = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT event.stunden FROM " +  "event", null);
        cursorRecipe.moveToFirst();
        cursorRecipe.moveToPosition(i);
        getStunden = cursorRecipe.getInt(1);
        cursorRecipe.close();
        return getStunden;
    }

    public int getEventMinuten(int i){
        int getMinuten=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT event.minuten FROM " +  "event", null);
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


