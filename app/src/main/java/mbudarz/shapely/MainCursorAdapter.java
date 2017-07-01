package mbudarz.shapely;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Michael on 6/27/2017.
 */

public class MainCursorAdapter extends CursorAdapter {

    public MainCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_main, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView task = (TextView) view.findViewById(R.id.taskmain);
        task.setText(cursor.getString(cursor.getColumnIndex("task")));

        TextView importance = (TextView) view.findViewById(R.id.importancemain);
        importance.setText(cursor.getString(cursor.getColumnIndex("importance")));

        TextView date = (TextView) view.findViewById(R.id.datemain);
        date.setText(cursor.getString(cursor.getColumnIndex("date")));
    }

}
