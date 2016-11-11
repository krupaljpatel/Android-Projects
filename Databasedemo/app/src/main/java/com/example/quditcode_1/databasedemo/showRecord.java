package com.example.quditcode_1.databasedemo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class showRecord extends AppCompatActivity {

    ListView listView;
    DatabaseHelper myDb;
    SimpleCursorAdapter myAdapter;
    String TAG = "Cursor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record);

        myDb = new DatabaseHelper(this);


        final String[] from = new String[] { DatabaseHelper.Col_2};
        final int[] to = new int[] {R.id.textView4 };


        listView = (ListView)findViewById(R.id.listView);

        Cursor cursor = myDb.getAllData();

        if (cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    String data = cursor.getString(cursor.getColumnIndex("FIRSTNAME"));
                    Log.i(TAG, data);
                }while (cursor.moveToNext());

            }
        }
            /*

        myCursorAdapter todoAdapter = new myCursorAdapter(this, cursor);

        listView.setAdapter(todoAdapter);*/



            myAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to, 0);

            listView.setAdapter(myAdapter);

    }
}
