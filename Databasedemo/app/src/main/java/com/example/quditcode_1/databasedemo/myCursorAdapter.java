package com.example.quditcode_1.databasedemo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by QuditCode-1 on 11/11/2016.
 */
public class myCursorAdapter extends CursorAdapter {
    public myCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvBody = (TextView) view.findViewById(R.id.textView4);

        String data = cursor.getString(cursor.getColumnIndex("FIRSTNAME"));
        tvBody.setText(data);
    }
}
