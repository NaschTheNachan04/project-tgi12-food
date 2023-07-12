package de.ghse.tgi.rezepteapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.Event;
import de.ghse.tgi.rezepteapp.Ingredient;
import de.ghse.tgi.rezepteapp.Recipe;



public class StorageRecipe extends SQLiteOpenHelper {
    private static final String databaseRecipe   ="recipeData";

    private final Context context;


    public StorageRecipe (Context c){
        super(c,databaseRecipe,null,1 );
        context = c;
    }

    /**
     * method that to creates Databank if not exist
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.

        database.execSQL("CREATE TABLE IF NOT EXISTS zutat   (ZID INTEGER PRIMARY KEY AUTOINCREMENT,name CHAR,vorratsEinheit text,vorratsmenge CHAR);");
        database.execSQL("CREATE TABLE IF NOT EXISTS recipe  (RID INTEGER PRIMARY KEY AUTOINCREMENT,name CHAR,beschreibung CHAR,Bild CHAR);");
        database.execSQL("CREATE TABLE IF NOT EXISTS event   (EID INTEGER PRIMARY KEY AUTOINCREMENT,datum TEXT,stunden INTEGER,minuten INTEGER,titel CHAR);");
        database.execSQL("CREATE TABLE IF NOT EXISTS rZutat  (ZRID INTEGER PRIMARY KEY AUTOINCREMENT,RID INTEGER,ZID INTEGER,menge DOUBLE,einheit text,FOREIGN KEY(RID) REFERENCES recipe(RID),FOREIGN KEY(ZID) REFERENCES zutat(ZID));");
        database.execSQL("CREATE TABLE IF NOT EXISTS rEvent  (ERID INTEGER PRIMARY KEY AUTOINCREMENT,EID INTEGER ,RID INTEGER,FOREIGN KEY(RID) REFERENCES recipe(RID), FOREIGN KEY(EID) REFEReNCES event(EID));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * method that add recipe to database
     */
    public void addRecipe(Recipe a){
        ArrayList<Ingredient> ingredient=a.getIngredient();
        String name = a.getName();
        String beschreibung = a.getDescription();
        String bild = a.getImage().toString();
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("name",name);
        c.put("beschreibung",beschreibung);
        c.put("bild",bild);
        database.insert("recipe",null,c);
        database.close();

        database =this.getReadableDatabase();
        Cursor cursor= database.rawQuery("SELECT r.RID FROM recipe r WHERE r.name = '"+name+"' AND r.beschreibung= '"+beschreibung+"'",null);
        cursor.moveToFirst();
        int rid = cursor.getInt(0);
        cursor.close();
        for(int i = 0;i<ingredient.size();i++){
            addIngredient(ingredient.get(i),rid);
        }
    }
    /**
     * method to get count of database
     */
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
    /**
     * method to get name of database
     */
    public String getRecipeName(int index){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT recipe.name FROM recipe  WHERE rID="+index,null);
        cursorRecipe.moveToFirst();
        String name = cursorRecipe.getString(0);
        cursorRecipe.close();
        return name;
    }
    /**
     * method to get Image of database
     */
    public Uri getRecipeImage(int index)  {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT recipe.bild FROM recipe WHERE rID="+index, null);
        cursorRecipe.moveToFirst();
        Uri image = Uri.parse(cursorRecipe.getString(0));
        cursorRecipe.close();
        return image;
    }
    /**
     * method to get description of database
     */
    public String getRecipeDescription(int index){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT recipe.beschreibung FROM  recipe WHERE rID="+index, null);
        cursorRecipe.moveToFirst();
        String description = cursorRecipe.getString(0);
        cursorRecipe.close();
        return description;
    }
    /**
     * method to add ingredient to database.
     */
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
        rdb.close();
        //Add Ingredient in rZutat
        rdb = getReadableDatabase();
        Cursor cursor1 =rdb.rawQuery("SELECT z.ZID FROM zutat z WHERE z.name LIKE'"+ingredient.getName()+"'",null);
        cursor1.moveToFirst();
        int zid=cursor1.getInt(0);
        cursor1.close();
        double menge=ingredient.getAmount();
        String einheit=ingredient.getUnit();
        ContentValues  c = new ContentValues();
        c.put("menge",menge);
        c.put("einheit",einheit);
        c.put("ZID",zid);
        c.put("RID",rid);
        wdb = getWritableDatabase();
        wdb.insert("rZutat",null,c);
        wdb.close();
        rdb.close();

    }
    /**
     * method to get ingredient of  database.
     */
    public ArrayList<String> getIngredients(){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //check if ingredient exist
        Cursor selectCount =db.rawQuery("SELECT COUNT(z.ZID) FROM Zutat z ",null);
        selectCount.moveToFirst();
        int count =selectCount.getInt(0);
        selectCount.close();
        if(count==0){
            list.add("");
            return list;
        }

        Cursor cursorRecipe = db.rawQuery("SELECT z.name FROM zutat z ", null);
        cursorRecipe.moveToFirst();
        do{
            list.add(cursorRecipe.getString(0));
        } while (cursorRecipe.moveToNext());
        cursorRecipe.close();
        return  list;
    }
    /**
     * method to get count out of database .
     */
    private int getIngredientCount(int id) {
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor= db.rawQuery("SELECT COUNT(rz.ZRID) FROM rZutat rz  WHERE rz.RID ="+id,null);
      int count=0;
      if(cursor.moveToFirst()){
          count = cursor.getInt(0);
      }
      cursor.close();
      return count;
    }
    /**
     * method to get the amount of database.
     */
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
    /**
     * method to get the unit of database.
     */
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
    /**
     * method to get the name(Ingredient) of database.
     */
    public String[] getIngredientsName(int id){
        String[] list = new String[getIngredientCount(id)];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT z.name FROM zutat z,rZutat rz WHERE rz.ZID = z.ZID AND rz.RID ="+id+" ORDER BY rz.ZID",null);
        if(cursor.moveToFirst()){
            for(int i=0;i<list.length;i++){
                list[i]=cursor.getString(0);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }
    /**
     * method to now how much Ingredient is left (No use)
     */
    public double getZutatVorratsmenge(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT z.vorratsmenge FROM zutat z WHERE z.ZID="+id, null);
        cursorRecipe.moveToFirst();
        cursorRecipe.moveToPosition(id);
        double menge = cursorRecipe.getInt(0);
        cursorRecipe.close();
        return menge;
    }
    /**
     * method to delete ingredient (No use)
     */
    public void deleteZutaten(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("zutat","zid=?"+id,null);
        db.close();
    }
    /**
     * method to delete event (No use)
     */
    public void deleteEvent(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("event","eid=?"+id,null);
        db.close();
    }



    /**
     * method to get image easier out of database
     */
    private byte[] getImageFromUri(Uri uri) throws IOException {
        InputStream st =context.getContentResolver().openInputStream(uri );
        ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
        byte[] buffer= new byte[1024];
        int length;
        while ((length=st.read(buffer) )!=-1){
            outputStream.write(buffer,0,length);
        }
        byte[] image=outputStream.toByteArray();
        outputStream.close();
        st.close();
        return  image;
    }
    /**
     * method how much recipe exist on a day
     */
    public int getRecipeOnDayCount(int day, int month, int year) {
        String date = year+"-"+month+"-"+day;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(re.RID) FROM EVENT e, rEvent re WHERE e.EID=re.EID AND e.datum = '"+date+"'",null);
        cursor.moveToFirst();
        int count=cursor.getInt(0);
        cursor.close();
        return  count;
    }
    /**
     * method to get the time from Event
     */
    public ArrayList<Integer[]> getEventTime(int day, int month, int year) {
        String date = year+"-"+month+"-"+day;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT e.stunden,e.minuten FROM EVENT e,rEvent re WHERE e.EID=re.EID  AND e.datum = '"+date+"' ORDER BY e.stunden,e.minuten",null);
        ArrayList<Integer[]> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            list.add(new Integer[]{cursor.getInt(0),cursor.getInt(1)});
            while(cursor.moveToNext()){
                list.add(new Integer[]{cursor.getInt(0),cursor.getInt(1)});
            }
        }
        cursor.close();
        db.close();
        return list;
    }
    /**
     * method to get Recipe in a event
     */
    public ArrayList<Integer> getRecipeId(int day, int month, int year) {
        String date = year+"-"+month+"-"+day;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT re.RID FROM EVENT e,rEvent re WHERE e.EID=re.EID  AND e.datum = '"+date+"' ORDER BY e.stunden,e.minuten",null);
        ArrayList<Integer> list= new ArrayList<>();
        if(cursor.moveToFirst()){
            list.add(cursor.getInt(0));
            while(cursor.moveToNext()){
                list.add(cursor.getInt(0));
            }
        }
        cursor.close();
        db.close();
        return list;
    }
    /**
     * method to add event to database
     */
    public void addEvent(Event e){
        SQLiteDatabase wdb = this.getWritableDatabase();
        ContentValues  c = new ContentValues();
        String date = e.getYear()+"-"+e.getMonth()+"-"+e.getDay();
        String titel =e.getTitle();
        int hour = e.getHour();
        int min = e.getMinute();
        c.put("datum",date);
        c.put("stunden",hour);
        c.put("minuten",min);
        c.put("titel",titel);
        wdb.insert("event",null,c);
        c.clear();
        wdb.close();
        SQLiteDatabase rdb = this.getReadableDatabase();
        Cursor cursor= rdb.rawQuery("SELECT e.EID FROM event e WHERE e.datum = '"+date+"' AND e.stunden= "+hour+" AND e.minuten= "+min+" AND e.titel LIKE '"+titel+"'",null);
        cursor.moveToFirst();
        int eid=cursor.getInt(0);
        cursor.close();
        ArrayList<Integer> list =e.getRecipe();
        wdb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(int i=0;i<list.size();i++){
            contentValues.put("RID",list.get(i));
            contentValues.put("EID",eid);
            wdb.insert("rEvent",null,contentValues);
        }
        wdb.close();
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


