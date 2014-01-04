package br.com.projetoanimais.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.projetoanimais.R;
import br.com.projetoanimais.db.APPDAO;

public class TelaInicio extends Activity {

    Button bt_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_tela_inicio);

        // encontra o botao ok
        bt_ok = (Button) findViewById(R.id.tela_inicio_button_ok);

        // define a acao do botao ok
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inicia a tela de perguntas
                startActivity(new Intent(TelaInicio.this, TelaPerguntas.class));
                // finaliza a tela de inicio
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_gerar:
                new APPDAO(TelaInicio.this);
                return true;
            case R.id.action_destruir:
                Toast.makeText(TelaInicio.this, "Nao faco nada ainda", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}