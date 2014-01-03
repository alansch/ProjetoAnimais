package br.com.projetoanimais.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.projetoanimais.model.Animal;

public class AnimalDAO {


    public static final String NOME_TABELA = "Animal";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";



    public static final String SCRIPT_CRIACAO_TABELA_ANIMAIS = "CREATE TABLE " + NOME_TABELA + "("
            + COLUNA_ID + " INTEGER PRIMARY KEY," + COLUNA_NOME + " TEXT," + ")";

    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;



    private SQLiteDatabase dataBase = null;


    private static AnimalDAO instance;

    public static AnimalDAO getInstance(Context context) {
        if(instance == null)
            instance = new AnimalDAO(context);
        return instance;
    }

    private AnimalDAO(Context context) {
        PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(context);
        dataBase = persistenceHelper.getWritableDatabase();
    }

    public void salvar(Animal animal) {
        ContentValues values = gerarContentValeuesAnimal(animal);
        dataBase.insert(NOME_TABELA, null, values);
    }

    public List<Animal> recuperarTodos() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Animal> animal = construirAnimalPorCursor(cursor);

        return animal;
    }


    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close();
    }


    private List<Animal> construirAnimalPorCursor(Cursor cursor) {
        List<Animal> animais = new ArrayList<Animal>();
        if(cursor == null)
            return animais;

        try {

            if (cursor.moveToFirst()) {
                do {

                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexNome = cursor.getColumnIndex(COLUNA_NOME);

                    int id = cursor.getInt(indexID);
                    String nome = cursor.getString(indexNome);


                    Animal animal = new Animal(id, nome);

                    animais.add(animal);

                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }
        return animais;
    }

    private ContentValues gerarContentValeuesAnimal(Animal animal) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, animal.getNome());

        return values;
    }
}