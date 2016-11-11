package com.example.quditcode_1.databasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstName,lastName,moblie;
    Button add,show;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        firstName = (EditText)findViewById(R.id.fName);
        lastName = (EditText)findViewById(R.id.lName);
        moblie = (EditText)findViewById(R.id.mNo);

        add = (Button)findViewById(R.id.button);
        show = (Button)findViewById(R.id.button2);

        AddData();

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,showRecord.class);
                startActivity(intent);
            }
        });

    }

    public void AddData()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInsert = myDb.insertData(firstName.getText().toString(),
                        lastName.getText().toString(),
                        moblie.getText().toString());

                if (isInsert == true)
                    Toast.makeText(MainActivity.this,"Data Inserted....",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Inserted!!!!",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
