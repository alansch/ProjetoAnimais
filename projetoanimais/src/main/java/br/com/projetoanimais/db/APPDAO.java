package br.com.projetoanimais.db;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import br.com.projetoanimais.model.Animal;

/**
 * Created by jhonattas on 03/01/14.
 */
public class APPDAO {

    Context c;

    // cachorro
    // elefante
    // cavalo
    // foca

    public APPDAO(Context c){
        this.c = c;

        ArrayList<Animal> animais = new ArrayList<Animal>();
        animais.add(new Animal(1, "cachorro", 0));
        animais.add(new Animal(2, "elefante", 0));
        animais.add(new Animal(3, "cavalo", 0));
        animais.add(new Animal(4, "foca", 1));

        for(Animal a : animais){
            AnimalDAO.getInstance(c).salvar(a);
            Log.d("APPDAO", "animal: "+a.toString());
        }


    }

}
