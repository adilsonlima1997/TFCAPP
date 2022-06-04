package Base_dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBClinicas extends SQLiteOpenHelper {

    private Context context;
    private  static final String DATABASE_NAME="CLINICAS.db";
    private  static final int DATABASE_VERSION= 1 ;

    private  static final String TABLE_NAME= "Clinicas";
    private  static final String COLUMN_ID= "_id";
    private  static final String COLUMN_CLINICA= "nome_clinica";
    private  static final String COLUMN_LOCALIZACAO= "localizacao_clinica";
    private  static final String COLUMN_HORARIO= "horario_clinica";

    public DBClinicas(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CLINICA + " TEXT, " +
                COLUMN_LOCALIZACAO + " TEXT, " + COLUMN_HORARIO +  " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public void addClinica(String nome_clinica, String local_clinica, String horario_clinica){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_CLINICA, nome_clinica);
        values.put(COLUMN_LOCALIZACAO, local_clinica);
        values.put(COLUMN_HORARIO, horario_clinica);
        long result = db.insert(TABLE_NAME, null, values);
        //verificando se os dados foram inseridos ou não
        if (result == -1){
            Toast.makeText(context, "Falha na inserção", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Dados inseridos com Sucesso", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    //função que ira ler todos os dados da nossa base de dados
    public Cursor readAllData2(){
        String query = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        //verificar se temos algum dado na nossa tabela da base de dados
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public void updateDataClinica(String row_id, String nome_clinica, String local_clinica, String horario_clinica){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CLINICA, nome_clinica);
        cv.put(COLUMN_LOCALIZACAO, local_clinica);
        cv.put(COLUMN_HORARIO, horario_clinica);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        //se não tiver dados envia uma mensagem de erro
        if (result == -1){
            Toast.makeText(context, "Failed to Update!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteOneRow2(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Falha em Apagar", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Apagado com Sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData2(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
