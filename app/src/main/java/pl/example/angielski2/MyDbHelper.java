package pl.example.angielski2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "English.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "english";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ENG = "english";
    private static final String COLUMN_PL= "polish";
    private static final String COLUMN_SCORE= "score";

    public MyDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create Table "+TABLE_NAME+"("+
                    COLUMN_ID+" INTEGER primary key, " +
                    COLUMN_ENG + " TEXT, " +
                    COLUMN_PL + " TEXT, " +
                    COLUMN_SCORE + " INTEGER);");


        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        }

        public void addData(String eng, String pl, String score){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_ENG, eng);
            cv.put(COLUMN_PL, pl);
            cv.put(COLUMN_SCORE, Integer.valueOf(score.trim()));

            long result = db.insert(TABLE_NAME, null, cv);

            if (result ==-1){
                Toast.makeText(context, "failed ", Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(context, "added successfully ", Toast.LENGTH_LONG).show();
        }
    }

        public Cursor getData() {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor  = DB.rawQuery("Select * from "+TABLE_NAME, null);
            return cursor;
        }

    public Boolean deleteUserdata(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from "+TABLE_NAME+" where name = ?", new String[]{name});
        if(cursor.getCount()>0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{name});
            if(result==-1) {
                return  false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }
}

