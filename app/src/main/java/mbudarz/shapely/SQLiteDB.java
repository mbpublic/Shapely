package mbudarz.shapely;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Michael on 6/24/2017.
 */

public class SQLiteDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_NAME = "tasks_list";

    public static final String COL_1 = "Task";
    public static final String COL_2 = "Importance";
    public static final String COL_3 = "Date";



    public SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (Task TEXT PRIMARY KEY, Importance TEXT, Date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String task, String imp, String d){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, task);
        contentValues.put(COL_2, imp);
        contentValues.put(COL_3, d);
        long result = db.insert(TABLE_NAME, null, contentValues);               //returns -1 on failure
        if (result == -1){
            return false;
        }
        else return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor myres = db.rawQuery("select * from " +TABLE_NAME,null);
        return myres;
    }
}
