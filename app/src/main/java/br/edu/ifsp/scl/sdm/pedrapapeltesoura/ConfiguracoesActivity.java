package br.edu.ifsp.scl.sdm.pedrapapeltesoura;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.edu.ifsp.scl.sdm.pedrapapeltesoura.databinding.ActivityConfiguracoesBinding;

public class ConfiguracoesActivity extends AppCompatActivity {

    private ActivityConfiguracoesBinding activityConfiguracoesBinding;
    int jogadores_salvo = 2;
    int rodadas_salvo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        activityConfiguracoesBinding = ActivityConfiguracoesBinding.inflate(getLayoutInflater());

        Intent intentRecebe = getIntent();
        Bundle parametros = intentRecebe.getExtras();

        if (parametros != null) {
            jogadores_salvo = parametros.getInt("chave_jogadores_salvo");
            rodadas_salvo = parametros.getInt("chave_rodadas_salvo");
        }

        if (jogadores_salvo == 2){
            activityConfiguracoesBinding.jogador2Rb.setChecked(true);
        }else{
            activityConfiguracoesBinding.jogador3Rb.setChecked(true);
        }

        if (rodadas_salvo == 1){
            activityConfiguracoesBinding.rodadas1Rb.setChecked(true);
        }else if (rodadas_salvo == 3) {
            activityConfiguracoesBinding.rodadas3Rb.setChecked(true);
        }else{
            activityConfiguracoesBinding.rodadas5Rb.setChecked(true);
        }

        setContentView(activityConfiguracoesBinding.getRoot());

    }

    public void onClickBtnSalvarConf (View view) {

        int jogadores = 0;
        int rodadas   = 0;

        if (activityConfiguracoesBinding.jogador2Rb.isChecked()){
            jogadores = 2;
        }
        else {
            jogadores = 3;
        }

        if (activityConfiguracoesBinding.rodadas1Rb.isChecked()){
            rodadas = 1;
        }
        else if (activityConfiguracoesBinding.rodadas3Rb.isChecked()){
            rodadas = 3;
        }
        else {
            rodadas = 5;
        }

        Intent intentSalvar = new Intent(getApplicationContext(),MainActivity.class);
        Bundle parametros = new Bundle();

        parametros.putInt("chave_jogadores", jogadores);
        parametros.putInt("chave_rodadas", rodadas);

        intentSalvar.putExtras(parametros);

        startActivity(intentSalvar);

    }

    public void onClickBtnCancelarConf (View view) {

        if (jogadores_salvo == 2){
            activityConfiguracoesBinding.jogador2Rb.setChecked(true);
        }else{
            activityConfiguracoesBinding.jogador3Rb.setChecked(true);
        }

        if (rodadas_salvo == 1){
            activityConfiguracoesBinding.rodadas1Rb.setChecked(true);
        }else if (rodadas_salvo == 3) {
            activityConfiguracoesBinding.rodadas3Rb.setChecked(true);
        }else{
            activityConfiguracoesBinding.rodadas5Rb.setChecked(true);
        }

        Intent intentSalvar = new Intent(getApplicationContext(),MainActivity.class);
        Bundle parametros = new Bundle();

        parametros.putInt("chave_jogadores", jogadores_salvo);
        parametros.putInt("chave_rodadas", rodadas_salvo);

        intentSalvar.putExtras(parametros);

        startActivity(intentSalvar);

    }
}