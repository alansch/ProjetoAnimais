package br.com.projetoanimais.business;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.projetoanimais.db.AnimalDAO;

public class PersistenceHelper extends SQLiteOpenHelper {

    public static final String NOME_BANCO =  "OsAnimais";
    public static final int VERSAO =  1;

    private static PersistenceHelper instance;

    private PersistenceHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    public static PersistenceHelper getInstance(Context context) {
        if(instance == null)
            instance = new PersistenceHelper(context);

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AnimalDAO.SCRIPT_LIMPEZA_TABELA);
        db.execSQL(AnimalDAO.SCRIPT_CRIACAO_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AnimalDAO.SCRIPT_LIMPEZA_TABELA);
        onCreate(db);
    }



}