package Base_dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "login.db";

    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table users(user_name TEXT primary key, pass_word TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
    }

    public Boolean inserData(String user, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("user_name", user);
        values.put("pass_word", password);

        long result = db.insert("users", null, values);
        if (result == -1)return false;
        else
            return true;
    }

    //funçao que verificara se foi inserido um nome de usuario ou nao
    public Boolean checkusername(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where user_name=?", new String[] {user});

        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //função que verificara se foi inserido um nome e um password
    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where user_name=? and pass_word=?", new String[]{username, password});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
