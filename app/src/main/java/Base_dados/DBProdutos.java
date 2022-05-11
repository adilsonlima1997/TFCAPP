package Base_dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBProdutos extends SQLiteOpenHelper {

    private Context context;
    private  static final String DATABASE_NAME="PRODUTOS.db";
    private  static final int DATABASE_VERSION= 1 ;

    private  static final String TABLE_NAME= "Remedios";
    private  static final String COLUMN_ID= "_id";
    private  static final String COLUMN_NOME= "nome_remedio";
    private  static final String COLUMN_QUANTIDADE= "quantidade_remedio";
    private  static final String COLUMN_DESCRICAO= "descricao_remedio";


    public DBProdutos(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_QUANTIDADE + " INTEGER, " +
                COLUMN_DESCRICAO + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addRemedios(String nome_remedio, int quantidade, String descr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NOME, nome_remedio);
        values.put(COLUMN_QUANTIDADE, quantidade);
        values.put(COLUMN_DESCRICAO, descr);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1){
            Toast.makeText(context, "Falha na inserção", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Dados inseridos com Sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
