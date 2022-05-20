package Base_dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBFarmacias extends SQLiteOpenHelper {

    private Context context;
    private  static final String DATABASE_NAME="FARMACIAS.db";
    private  static final int DATABASE_VERSION= 1 ;

    private  static final String TABLE_NAME= "Farmacias";
    private  static final String COLUMN_ID= "_id";
    private  static final String COLUMN_FARMACIA= "nome_farmacia";
    private  static final String COLUMN_LOCALIZACAO= "localizacao_farmacia";


    public DBFarmacias(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FARMACIA + " TEXT, " +
                COLUMN_LOCALIZACAO + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addFarmacias1(String nome_farmacia, String local_farmacia){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FARMACIA, nome_farmacia);
        values.put(COLUMN_LOCALIZACAO, local_farmacia);
        long result = db.insert(TABLE_NAME, null, values);
        //verificando se os dados foram inseridos ou não
        if (result == -1){
            Toast.makeText(context, "Falha na inserção", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Dados inseridos com Sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData1(){
        String query = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateData1(String row_id, String nome, String loca){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FARMACIA, nome);
        cv.put(COLUMN_LOCALIZACAO, loca);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to Update!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneRow1(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Falha em Apagar", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Apagado com Sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData1(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
