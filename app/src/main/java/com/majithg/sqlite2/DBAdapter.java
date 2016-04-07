package com.majithg.sqlite2;

/**
 * Created by majithg on 4/6/2016.
 */
public class DBAdapter {

   /* private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DBAdapter(Context context) {

        // here we are creating an object of DBHelper class tht we have already created...
        dbHelper = new DBHelper(context.getApplicationContext());
    }

    // Create open method and close method
     public DBAdapter open() throws SQLDataException{
         sqLiteDatabase = dbHelper.getWritableDatabase();
         return this;
     }
     public void close(){
         dbHelper.close();
     }





    private Cursor getAllEntries(){
        String[] columns = new String[1];
        columns[0] = "name";
        return sqLiteDatabase.query("names", columns, null, null, null,null, null);
    }

    public List<String> getAllNames(){

        ArrayList<String> names = new ArrayList<>();

        Cursor cursor = getAllEntries();

        if(cursor.moveToNext()){
            do{
                names.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        return names;
    }
*/

}
