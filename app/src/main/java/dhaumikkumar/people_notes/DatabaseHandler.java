package dhaumikkumar.people_notes;


/**
 * Created by dhaumikkumar on 2014-07-23.
 */


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHandler extends SQLiteOpenHelper
{
    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "PEOPLE";
    //Table Name
    private static final String TABLE_TEST = "people_data";
    //Column Name
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TEST + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_AGE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);
        onCreate(db);
    }

    //Insert Value
    public void adddata(Context context,String movieId,String songId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, movieId);
        values.put(KEY_AGE, songId);
        db.insert(TABLE_TEST, null, values);
        db.close();
    }

    //Get Row Count
    public int getCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TEST;
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }

    //Delete Query
    public void removeFav(int id) {
        String countQuery = "DELETE FROM " + TABLE_TEST + " where " + KEY_ID + "= " + id ;
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(countQuery);
    }

    //Get FavList
    public List<FavoriteList> getFavList(){
        String selectQuery = "SELECT  * FROM " + TABLE_TEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<FavoriteList> FavList = new ArrayList<FavoriteList>();
        if (cursor.moveToFirst()) {
            do {
                FavoriteList list = new FavoriteList();
                list.setId(Integer.parseInt(cursor.getString(0)));
                list.setName(cursor.getString(1));
                list.setAge(cursor.getString(2));
                FavList.add(list);
            } while (cursor.moveToNext());
        }
        return FavList;
    }

}
