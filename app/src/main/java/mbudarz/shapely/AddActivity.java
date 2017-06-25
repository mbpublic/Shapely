package mbudarz.shapely;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Michael on 6/23/2017.
 *This activity is the new window that appears when you click the + sign on main screen
 * From here we add an activity to the list and then return to main activity
 */

public class AddActivity extends Activity {
    SQLiteDB myDataBase;
    EditText editTask, editImportance, editDate;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        myDataBase = new SQLiteDB(this);


        //set parameters when button is pressed
        editTask = (EditText)findViewById(R.id.editText);
        editImportance = (EditText)findViewById(R.id.editText6);
        editDate = (EditText)findViewById(R.id.editText5);


        final Button accept = findViewById(R.id.button);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                boolean isInserted = myDataBase.insertData(editTask.getText().toString(),
                                                            editImportance.getText().toString(),
                                                            editDate.getText().toString()   );

                if (isInserted) {
                    Toast.makeText(AddActivity.this, "Data was Inserted", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(AddActivity.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();
                }
                Intent rev = new Intent(AddActivity.this, MainActivity.class);
                startActivity(rev);

            }
        });
    }
    }


