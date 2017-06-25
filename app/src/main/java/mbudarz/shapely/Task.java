package mbudarz.shapely;

/**
 * Created by Michael on 6/24/2017.
 */

public class Task {
    String name;
    int importance;
    int date;

    Task(String n, int i, int d){
        name = n;
        importance = i;
        date = d;
    }

    Task(int n, int i, int d){
        name = String.valueOf(n);
        importance = i;
        date = d;
    }
}
