package com.majithg.sqlite2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Button button,button2,button3,button4,button5,button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
//        button5 = (Button) findViewById(R.id.button5);
//        button6 = (Button) findViewById(R.id.button6);


        dbHelper = new DBHelper(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrieveData();
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int counts = countAll();

                Toast.makeText(getApplicationContext(), "no. of rows: "+counts, Toast.LENGTH_SHORT).show();
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteAllData();

                Toast.makeText(getApplicationContext(), "Table has been successfully deleted... ", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void insertData(){



        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUMN_2, "steve" );
        contentValues.put(dbHelper.COLUMN_3, "jobs");

        sqLiteDatabase.insert(dbHelper.TABLE_NAME, null, contentValues);
        contentValues.clear();


//        SQLiteDatabase sqLiteDatabase2 = dbHelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase2.rawQuery("SELECT  * FROM " + dbHelper.TABLE_NAME + ";", null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT  * FROM " + dbHelper.TABLE_NAME + ";", null);
        int cnt = cursor.getCount();

        cursor.close();

        sqLiteDatabase.close();
//        sqLiteDatabase2.close();
        dbHelper.close();

        Toast.makeText(this, cnt+"th Row has been added...", Toast.LENGTH_SHORT).show();

    }

    public void retrieveData() {

//        SQLiteDatabase db = openOrCreateDatabase("names2.db", MODE_PRIVATE, null);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(" SELECT * FROM NAMES; ", null);

        if (cursor.moveToFirst()){

            do{
                // cycle through all records
//                String clm1 =cursor.getString(1);
//                String clm1 =cursor.getString( cursor.getColumnIndex(dbHelper.COLUMN_1) );  //or
                int clm1 =cursor.getInt( cursor.getColumnIndex(dbHelper.COLUMN_1) );

//                String clm1 =cursor.getString(1);
                String clm2 =cursor.getString( cursor.getColumnIndex(dbHelper.COLUMN_2) );
//                String clm2 =cursor.getString(2);
                String clm3 =cursor.getString( cursor.getColumnIndex(dbHelper.COLUMN_3) );


                int i = cursor.getPosition()+1;

                Toast.makeText(getBaseContext(), "Raw no."+i+":  id_"+clm1 +" " +clm2 +" "+clm3, Toast.LENGTH_LONG).show();
            }while (cursor.moveToNext());
        }
        else {

            Toast.makeText(getBaseContext(),"Error retrieving data ... ", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }


    public int countAll() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + dbHelper.TABLE_NAME + ";", null);

        int cnt = cursor.getCount();

        cursor.close();
        db.close();

        // return count
        return cnt;
    }


    public void deleteAllData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String whrereClause = "";
//        String[] whereAgrs = {};
//        db.delete(dbHelper.TABLE_NAME, whrereClause, whereAgrs );

        db.close();
    }

}
