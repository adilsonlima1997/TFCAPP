package Base_dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

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
}
