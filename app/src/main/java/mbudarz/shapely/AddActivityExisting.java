package mbudarz.shapely;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Michael on 7/1/2017.
 */

public class AddActivityExisting extends Activity {

    SQLiteDB myDataBase;
    Cursor cursor;
    String task_take;
    int myval;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_existing);

        //on creation set the value
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            myval = getIntent().getIntExtra("position",0);
        }

        //affirm database
        myDataBase = SQLiteDB.getInstance(this);

        //set cursor to correct row
        cursor = myDataBase.getAllData();
        cursor.moveToPosition(myval);


        final TextView task = (TextView) findViewById(R.id.editText);
        task.setText(cursor.getString(1));


        final TextView imp = (TextView) findViewById(R.id.editText6);
        imp.setText(cursor.getString(2));

        final TextView date = (TextView) findViewById(R.id.editText5);
        date.setText(cursor.getString(3));


        ///////////////////////////////////BUTTONS//////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////


        //Go Back Button
        final Button goback = findViewById(R.id.button2);
        goback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent rev = new Intent(AddActivityExisting.this, MainActivity.class);
                startActivity(rev);

            }
        });


        //Create button and onclick action
        FloatingActionButton trashfab = findViewById(R.id.trash);
        trashfab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //on click create new activity to start storing tasks
                myDataBase.deleteData(cursor.getString(0));
                Intent Storetask = new Intent(AddActivityExisting.this, MainActivity.class);
                startActivity(Storetask);
            }
        });


        //Create button and onclick action
        final Button acceptfab = findViewById(R.id.button);
        acceptfab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int myid = Integer.valueOf(cursor.getString(0));
                myDataBase.deleteData(cursor.getString(0));
                boolean isInserted = myDataBase.insertDataID(task.getText().toString(),imp.getText().toString(),date.getText().toString(),myid);
                if (isInserted) {
                    Toast.makeText(AddActivityExisting.this, "Data was updated", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(AddActivityExisting.this, "Data NOT updated", Toast.LENGTH_LONG).show();
                }
                //on click create new activity to start storing tasks
                Intent Storetask = new Intent(AddActivityExisting.this, MainActivity.class);
                startActivity(Storetask);
            }
        });

    }}