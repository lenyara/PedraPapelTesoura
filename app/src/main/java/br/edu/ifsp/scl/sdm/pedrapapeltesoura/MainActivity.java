package br.edu.ifsp.scl.sdm.pedrapapeltesoura;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.Random;

import br.edu.ifsp.scl.sdm.pedrapapeltesoura.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;

    int jogadores = 2;
    int rodadas = 1;
    int contaRodadas = 0;
    int contaRodadasUsuario = 0;
    int contaRodadasComputador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        Intent intentRecebe = getIntent();
        Bundle parametros = intentRecebe.getExtras();

        if (parametros != null) {
            jogadores = parametros.getInt("chave_jogadores");
            rodadas = parametros.getInt("chave_rodadas");
        }

        setContentView(activityMainBinding.getRoot());

        activityMainBinding.papelBt.setOnClickListener(this);
        activityMainBinding.pedraBt.setOnClickListener(this);
        activityMainBinding.tesouraBt.setOnClickListener(this);

        if (jogadores == 2) {
            activityMainBinding.jogadaUsuarioIv.setImageResource(0);
            activityMainBinding.jogadaComputador1Iv.setImageResource(0);
            activityMainBinding.jogadaComputador2Iv.setImageResource(0);
            activityMainBinding.resultadoTv.setText("".toString());
        }

        if (jogadores == 3) {
            activityMainBinding.mostrarJogador3Ll.setVisibility(View.VISIBLE);
            activityMainBinding.jogadaUsuarioIv.setImageResource(0);
            activityMainBinding.jogadaComputador1Iv.setImageResource(0);
            activityMainBinding.jogadaComputador2Iv.setImageResource(0);
            activityMainBinding.resultadoTv.setText("".toString());
        }

    }

    @Override
    public void onClick(View view) {
        int jogada = -1;
        int imagemJogadaUsuario = -1;

        if (contaRodadas == 0) {
            activityMainBinding.resultadoFinalTv.setText("");
            activityMainBinding.rodadasTv.setText(String.valueOf(contaRodadas));
            activityMainBinding.rodadasUsuarioTv.setText(String.valueOf(contaRodadasUsuario));
            activityMainBinding.rodadasComputadorTv.setText(String.valueOf(contaRodadasComputador));
        }

            switch (view.getId()) {
                case R.id.pedraBt:
                    jogada = 0;
                    imagemJogadaUsuario = R.mipmap.pedra;
                    break;
                case R.id.papelBt:
                    jogada = 1;
                    imagemJogadaUsuario = R.mipmap.papel;
                    break;
                case R.id.tesouraBt:
                    jogada = 2;
                    imagemJogadaUsuario = R.mipmap.tesoura;
                    break;
                default:
                    break;
            }

            activityMainBinding.jogadaUsuarioIv.setImageResource(imagemJogadaUsuario);

            contaRodadas += 1;
            activityMainBinding.rodadasTv.setText(String.valueOf(contaRodadas));

            jogarJoKenPo(jogada);

    }

    @SuppressLint("SetTextI18n")
    private void jogarJoKenPo(int jogada) {
        Random random = new Random(System.currentTimeMillis());

        int jogadaComputador1 = random.nextInt(3);
        int imagemJogadaComputador1 = -1;

        int jogadaComputador2 =-1;
        int imagemJogadaComputador2 = -1;

        StringBuilder resultadoSb = new StringBuilder();

              switch (jogadaComputador1) {
                case 0:
                    imagemJogadaComputador1 = R.mipmap.pedra;
                    break;
                case 1:
                    imagemJogadaComputador1 = R.mipmap.papel;
                    break;
                case 2:
                    imagemJogadaComputador1 = R.mipmap.tesoura;
                    break;
                default:
                    break;
            }
            activityMainBinding.jogadaComputador1Iv.setImageResource(imagemJogadaComputador1);

            if (jogadores == 3) {
                jogadaComputador2 = random.nextInt(3);
                switch (jogadaComputador2) {
                    case 0:
                        imagemJogadaComputador2 = R.mipmap.pedra;
                        break;
                    case 1:
                        imagemJogadaComputador2 = R.mipmap.papel;
                        break;
                    case 2:
                        imagemJogadaComputador2 = R.mipmap.tesoura;
                        break;
                    default:
                        break;
                }
                activityMainBinding.jogadaComputador2Iv.setImageResource(imagemJogadaComputador2);
            }


            if (jogada == jogadaComputador1) {
                resultadoSb.append("EMPATE!");
            } else if ((jogada - jogadaComputador1 == -2) || (jogada - jogadaComputador1 == 1)) {
                resultadoSb.append("Você GANHOU!");
                if (jogadores == 2) {
                    contaRodadasUsuario += 1;
                    activityMainBinding.rodadasUsuarioTv.setText(String.valueOf(contaRodadasUsuario));
                }
            } else {
                resultadoSb.append("Você PERDEU!");
                if (jogadores == 2) {
                    contaRodadasComputador += 1;
                    activityMainBinding.rodadasComputadorTv.setText(String.valueOf(contaRodadasComputador));
                }
            }

            if (jogadores == 3) {
                resultadoSb.setLength(0);
                if (((jogada == jogadaComputador2) && (jogada == jogadaComputador1)) || ((jogada != jogadaComputador1) && (jogada != jogadaComputador2) && (jogadaComputador1 != jogadaComputador2))) {
                    resultadoSb.append("EMPATE!");
                } else if ((jogada - jogadaComputador2 == -2) || (jogada - jogadaComputador2 == 1)) {
                    resultadoSb.append("Você GANHOU!");
                    contaRodadasUsuario += 1;
                    activityMainBinding.rodadasUsuarioTv.setText(String.valueOf(contaRodadasUsuario));
                } else {
                    resultadoSb.append("Você PERDEU!");
                    contaRodadasComputador += 1;
                    activityMainBinding.rodadasComputadorTv.setText(String.valueOf(contaRodadasComputador));
                }
            }
        activityMainBinding.resultadoTv.setText(resultadoSb.toString());

        //Toast.makeText(MainActivity.this, "Contador Rodadas " +contaRodadas+ "Rodadas Conf " +rodadas, Toast.LENGTH_SHORT).show();

            if (contaRodadas == rodadas) {

                if (contaRodadasUsuario > contaRodadasComputador) {
                    activityMainBinding.resultadoFinalTv.setText("Fim de Jogo! Você GANHOU!");
                    contaRodadas = 0;
                    contaRodadasUsuario = 0;
                    contaRodadasComputador = 0;
                } else if (contaRodadasUsuario < contaRodadasComputador) {
                    activityMainBinding.resultadoFinalTv.setText("Fim de Jogo! Você PERDEU!");
                    contaRodadas = 0;
                    contaRodadasUsuario = 0;
                    contaRodadasComputador = 0;
                } else {
                        activityMainBinding.resultadoFinalTv.setText("Fim de Jogo! EMPATE!");
                        contaRodadas = 0;
                        contaRodadasUsuario = 0;
                        contaRodadasComputador = 0;
                    }


            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.configuracoesMi){
            Intent i = new Intent(MainActivity.this,ConfiguracoesActivity.class);
            Bundle parametrosSalvos = new Bundle();

            parametrosSalvos.putInt("chave_jogadores_salvo", jogadores);
            parametrosSalvos.putInt("chave_rodadas_salvo", rodadas);

            i.putExtras(parametrosSalvos);

            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


}

