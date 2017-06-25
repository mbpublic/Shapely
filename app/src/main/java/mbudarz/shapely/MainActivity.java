package mbudarz.shapely;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends ListActivity {
    SQLiteDB myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate the database
        myDataBase = new SQLiteDB(this);

        //Instantiate TaskList
        ArrayList<String> mytasks = new ArrayList<>();

        //Fill in the tasklist
        Cursor mycurrent = myDataBase.getAllData();
        if (mycurrent.getCount() != 0){
            int i = 0;
            while (mycurrent.moveToNext()) {
                mytasks.add(mycurrent.getString(0));
                System.out.println("the first element is: " + mytasks.get(i));
                i++;
            }
        }

        //Display TaskList in simple item list format
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getListView().getContext(), android.R.layout.simple_list_item_1, mytasks);
        getListView().setAdapter(adapter);

        //Create button and onclick action
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on click create new activity to start storing tasks
                Intent Storetask = new Intent(MainActivity.this, AddActivity.class);
                startActivity(Storetask);
            }
        });
    }


    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
