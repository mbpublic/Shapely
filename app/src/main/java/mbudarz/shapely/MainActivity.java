package mbudarz.shapely;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private SQLiteDB myDataBase;
    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //instantiate the database & cursor
        myDataBase = SQLiteDB.getInstance(this);
        displayListView();

        //Create button and onclick action
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on click create new activity to start storing tasks
                Intent Storetask = new Intent(MainActivity.this, AddActivity.class);
                startActivity(Storetask);
            }
        });
    }

    private void displayListView() {

            //look at data
            Cursor mycursor = myDataBase.getAllData();

            //data at cursor
            String[] columns = new String[]{
                    myDataBase.COL_2,
                    myDataBase.COL_3,
                    myDataBase.COL_4,
            };

            int[] to = new int[]{
                    R.id.taskmain,
                    R.id.importancemain,
                    R.id.datemain,
            };


            //adapter using cursor

            dataAdapter = new SimpleCursorAdapter(
                    this, R.layout.list_main,
                    mycursor,
                    columns,
                    to,
                    0);

            //create listview with adapter
            ListView lv = (ListView) findViewById(R.id.listView1);
            lv.setAdapter(dataAdapter);
            lv.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> listView, View view,
                                        int position, long id) {

                    //Capture position, and change activity view
                    //Toast.makeText(getApplicationContext(), position, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, AddActivityExisting.class);
                    i.putExtra("position", position);
                    startActivity(i);

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
