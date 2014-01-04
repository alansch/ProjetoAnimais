package br.com.projetoanimais.db;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.projetoanimais.business.PersistenceHelper;
import br.com.projetoanimais.model.Animal;

public class AnimalDAO {

    public static final String SCRIPT_CRIACAO_TABELA =
            "CREATE TABLE animal(id INTEGER PRIMARY KEY,  " +
            "nome TEXT,"+
            "id_terreno INTEGER);";

    public static final String SCRIPT_LIMPEZA_TABELA =
            "DROP TABLE IF EXISTS animal";

    private SQLiteDatabase dataBase = null;
    private static AnimalDAO instance;

    public static AnimalDAO getInstance(Context context) {
        if(instance == null){
            instance = new AnimalDAO(context);
        }
        return instance;
    }

    private AnimalDAO(Context context) {
        PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(context);
        dataBase = persistenceHelper.getWritableDatabase();
    }

    public void salvar(Animal animal) {
        ContentValues values = gerarContentValeuesAnimal(animal);
        dataBase.insert("animal", null, values);
    }

    public ArrayList<Animal> recuperarTodos() {
        String queryReturnAll = "SELECT * FROM animal";
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        ArrayList<Animal> animal = construirAnimalPorCursor(cursor);

        return animal;
    }


    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close();
    }


    private ArrayList<Animal> construirAnimalPorCursor(Cursor cursor) {
        ArrayList<Animal> animais = new ArrayList<Animal>();
        if(cursor == null)
            return animais;

        try {

            if (cursor.moveToFirst()) {
                do {

                    int indexID = cursor.getColumnIndex("id");
                    int indexNome = cursor.getColumnIndex("nome");
                    int indexTerreno = cursor.getColumnIndex("id_terreno");

                    int id = cursor.getInt(indexID);
                    String nome = cursor.getString(indexNome);
                    int idTerreno = cursor.getInt(indexTerreno);


                    Animal animal = new Animal(id, nome, idTerreno);
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
        values.put("nome", animal.getNome());
        values.put("id_terreno", animal.getIdTerreno());
        return values;
    }
}