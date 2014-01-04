package br.com.projetoanimais.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import br.com.projetoanimais.R;
import br.com.projetoanimais.db.AnimalDAO;
import br.com.projetoanimais.model.Animal;

public class TelaPerguntas extends Activity {

    int total_animais = 0;

    // elementos
    TextView pergunta;
    Button sim;
    Button nao;

    // animais do sistema
    ArrayList<Animal> animais = new ArrayList<Animal>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // define o visual da tela de perguntas
        setContentView(R.layout.f_tela_pergunta);

        // define componentes iniciais
        componentes();

    }

    void componentes(){
        pergunta = (TextView) findViewById(R.id.tela_pergunta_quest);
        sim = (Button) findViewById(R.id.tela_pergunta_botao_sim);
        nao = (Button) findViewById(R.id.tela_pergunta_botao_nao);

        // caso o animal viva na agua
        sim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculaTerreno(true);
            }
        });

        // caso o animal nao viva na agua
        nao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculaTerreno(false);
            }
        });

    }

    // verifica se o terreno eh agua, e separa os POSSIVEIS animais
    void calculaTerreno(boolean agua){
        ArrayList<Animal> ani_temp = AnimalDAO.getInstance(TelaPerguntas.this).recuperarTodos();

        if(agua){
            for(Animal a : ani_temp){
                if(a.getIdTerreno() == 1){
                    animais.add(a);
                }
            }
        } else {
            for(Animal a : ani_temp){
                if(a.getIdTerreno() == 0){
                    animais.add(a);
                }
            }
        }

        total_animais = animais.size();

        perguntaAnimais();

    }

    void perguntaAnimais(){
        Iterator<Animal> it = animais.iterator();
        while(it.hasNext()){
            Animal a = it.next();
            pergunta.setText(getResources().getString(R.string.quest_animal_pensado) + " "+ a.getNome());
        }


    }

}
