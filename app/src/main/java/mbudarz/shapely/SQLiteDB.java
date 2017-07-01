package mbudarz.shapely;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteDB extends SQLiteOpenHelper {

    private static SQLiteDB mydatabase;

    public static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_NAME = "tasks_list";


    public static final String COL_1 = "_id";
    public static final String COL_2 = "Task";
    public static final String COL_3 = "Importance";
    public static final String COL_4 = "Date";


    //singleton implementation
    public static synchronized SQLiteDB getInstance(Context context){
        if (mydatabase == null){
            mydatabase = new SQLiteDB(context.getApplicationContext());
        }
        return mydatabase;
    }

    //private constructor to prevent direct instantiation. Call getInstance() instead.
    private SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (_id INT PRIMARY KEY, Task TEXT, Importance TEXT, Date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String task, String imp, String d){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int myid = this.getAllData().getCount() + 1;
        contentValues.put(COL_1, myid);
        contentValues.put(COL_2, task);
        contentValues.put(COL_3, imp);
        contentValues.put(COL_4, d);

        long result = db.insert(TABLE_NAME, null, contentValues);               //returns -1 on failure
        if (result == -1){
            return false;
        }
        else return true;
    }


    public void deleteData(String key){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + "=" + key, null);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor myres = db.rawQuery("select * from " +TABLE_NAME, null);
        return myres;
    }
}

