package br.edu.ifsp.scl.sdm.pedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import java.util.Random;

import br.edu.ifsp.scl.sdm.pedrapapeltesoura.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(activityMainBinding.getRoot());

        activityMainBinding.papelBt.setOnClickListener(this);
        activityMainBinding.pedraBt.setOnClickListener(this);
        activityMainBinding.tesouraBt.setOnClickListener(this);

        activityMainBinding.mostrarOpcoesSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean mostrarOpcoes) {
                activityMainBinding.selecionarLl.setVisibility(mostrarOpcoes ? View.VISIBLE : View.GONE);
            }

        });

        activityMainBinding.jogador2Rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                activityMainBinding.jogadaUsuarioIv.setImageResource(0);
                activityMainBinding.jogadaComputador1Iv.setImageResource(0);
                activityMainBinding.jogadaComputador2Iv.setImageResource(0);
                activityMainBinding.resultadoTv.setText("".toString());
            }
        });

        activityMainBinding.jogador3Rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                activityMainBinding.mostrarJogador3Ll.setVisibility(b ? View.VISIBLE : View.GONE);
                activityMainBinding.jogadaUsuarioIv.setImageResource(0);
                activityMainBinding.jogadaComputador1Iv.setImageResource(0);
                activityMainBinding.jogadaComputador2Iv.setImageResource(0);
                activityMainBinding.resultadoTv.setText("".toString());
            }
        });

    }

    @Override
    public void onClick(View view) {
        int jogada = -1;
        int imagemJogadaUsuario = -1;
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

        jogarJoKenPo(jogada);
    }

    private void jogarJoKenPo(int jogada) {
        Random random = new Random(System.currentTimeMillis());
        Boolean continuaJogar = false;
        Boolean perdeuJogo = false;

        int jogadaComputador1 = random.nextInt(3);
        int imagemJogadaComputador1 = -1;

        int jogadaComputador2 =-1;
        int imagemJogadaComputador2 = -1;

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

        if (activityMainBinding.jogador3Rb.isChecked()) {
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

        StringBuilder resultadoSb = new StringBuilder();

        if (jogada == jogadaComputador1) {
            resultadoSb.append("EMPATE!");
            continuaJogar = true;
            //continuaJogar = false;
        } else if ((jogada - jogadaComputador1 == -2) || (jogada - jogadaComputador1 == 1)) {
            resultadoSb.append("Você GANHOU!");
            continuaJogar = true;
        } else {
            resultadoSb.append("Você PERDEU!");
            continuaJogar = false;
            perdeuJogo = true;
        }

        if ((activityMainBinding.jogador3Rb.isChecked()) && (continuaJogar)){
            resultadoSb.setLength(0);
            if (jogada == jogadaComputador2) {
                resultadoSb.append("EMPATE!");
            } else if ((jogada - jogadaComputador2 == -2) || (jogada - jogadaComputador2 == 1)) {
                resultadoSb.append("Você GANHOU!");
             } else {
                resultadoSb.append("Você PERDEU!");
            }
        }

        if ((activityMainBinding.jogador3Rb.isChecked()) && (perdeuJogo)){
            resultadoSb.setLength(0);
            if (jogada == jogadaComputador2) {
                resultadoSb.append("EMPATE!");
            }else {
                resultadoSb.append("Você PERDEU!");
            }
        }

        activityMainBinding.resultadoTv.setText(resultadoSb.toString());
    }
}
