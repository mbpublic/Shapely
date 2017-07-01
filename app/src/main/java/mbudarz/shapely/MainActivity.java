package mbudarz.shapely;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
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
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            String[] columns = new String[] {
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
                    this, R.layout.activity_main,
                    mycursor,
                    columns,
                    to,
                    0);

            //create listview with adapter
             ListView listView = (ListView) findViewById(R.id.listView1);
            listView.setAdapter(dataAdapter);

            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> listView, View view,
                    int position, long id){
                    //get cursor positioned to corresponding row
                    Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                    //get task
                    String tasktake = cursor.getString(cursor.getColumnCount("task"));
                    Toast.makeText(getApplicationContext(), tasktake, Toast.LENGTH_SHORT).show();

                }
                });











        /////////////////////////////////////////////////
        ////////////////////MODERN IMPLEMENTATION//////////
        //////////////////////////////////////////////////////

        Cursor mycursor = myDataBase.getAllData();

        //instantiate adapter & listview
        MainCursorAdapter myadapter = new MainCursorAdapter(this, mycursor);
        mylistview = (ListView) findViewById(R.id.);
        mylistview.setAdapter(myadapter);


        ///////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////
        //////////////////////////////////////////////////////


        /////////////////////////////////////////////
        // OLD INSTANTIATION OF LIST WORKS PROPERLY
        /////////////////////////////////////////////



        //Instantiate TaskList
        ArrayList<String> mytasks = new ArrayList<>();


        //Fill in the tasklist
        Cursor mycurrent = myDataBase.getAllData();
        if (mycurrent.getCount() != 0){
            while (mycurrent.moveToNext()) {
                if (mycurrent.getString(1) != null) {
                    mytasks.add(mycurrent.getString(1));
                }
            }
        }

        //Display TaskList in simple item list format
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getListView().getContext(), android.R.layout.simple_list_item_1, mytasks);
        getListView().setAdapter(adapter);


        ////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////



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


    //////////////////////
    /////return button////
    //////////////////////
//
//    getListView().setOnItemClickListener(new OnItemClickListener(){
//        public void onItemClick(AdapterView<?> adapter, View v, int position, long id){
//    }
//        )};

    //////////////////////
    //////////////////////
    //////////////////////

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
