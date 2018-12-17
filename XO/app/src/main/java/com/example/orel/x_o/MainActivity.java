package com.example.orel.x_o;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];//Botones del juego

    private boolean turnojugador1 = true;//Primer jugador inicia con la "X"

    private int conteorondas;//Conteo de las rondas jugadas

    private int puntosjugador1;//Puntos Jugador1
    private int puntosjugador2;//Puntos Jugador2

    private TextView textViewjugador1;//Demuestra puntos del Jugador
    private TextView textViewjugador2;//Demuestra puntos del Jugador

    MediaPlayer mp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewjugador1 = findViewById(R.id.text_view_p1);
        textViewjugador2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i ++){
            for (int j = 0; j <3; j++){
                String botonID = "button_" + i + j;
                int resID = getResources().getIdentifier(botonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button botonReset = findViewById(R.id.button_reset);
        botonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetJuego();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")) {//Hace o demuestra que el boton ya fue usado, que ya fue marcado.
            return;
        }
        if (turnojugador1){
            ((Button)v).setText("X");
        }else{
            ((Button) v).setText("O");
        }
            conteorondas++;
        if (chequearganador()){//Condicion de los ganadores.
            if (turnojugador1){
                jugador1gana();
            }else{
                jugador2gana();
            }
        }else if (conteorondas == 9){//si todos los botones estan dibujados, no hay ganador.
            dibujar();
        }else{
            turnojugador1 = !turnojugador1;
        }
    }

    private boolean chequearganador(){//Posibles maneras de ganar, y en el arrays el boton incrementa.
        String[][] campos = new String[3][3];
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                campos[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i=0; i<3; i ++){
            if(campos[i][0].equals(campos[i][1])
                    && campos[i][0].equals(campos[i][2])
                    && !campos[i][0].equals("")){
                return true;
            }
        }
        for (int i=0; i<3; i ++){
            if(campos[0][i].equals(campos[1][i])
                    && campos[0][i].equals(campos[2][i])
                    && !campos[0][i].equals("")){
                return true;
            }
        }
        if(campos[0][0].equals(campos[1][1])
                && campos[0][0].equals(campos[2][2])
                && !campos[0][0].equals("")){
            return true;
        }
        if(campos[0][2].equals(campos[1][1])
                && campos[0][2].equals(campos[2][0])
                && !campos[0][2].equals("")){
            return true;
        }
        return false;
    }
    private void jugador1gana(){
        puntosjugador1++;
        Toast.makeText(this, "¡Jugador 1 GANÓ!", Toast.LENGTH_LONG).show();
        mp3 = MediaPlayer.create(this, R.raw.fatalitysound);
        mp3.start();
        actualizarPuntos();
        borrarPantalla();
    }

    private void jugador2gana(){
        puntosjugador2++;
        Toast.makeText(this, "¡Jugador 2 GANÓ!", Toast.LENGTH_LONG).show();
        mp3 = MediaPlayer.create(this, R.raw.fatalitysound);
        mp3.start();
        actualizarPuntos();
        borrarPantalla();}

    private void dibujar(){
        Toast.makeText(this, "¡Nadie Gana!", Toast.LENGTH_LONG).show();
    }

    private void actualizarPuntos(){
        textViewjugador1.setText("Jugador 1: " + puntosjugador1);
        textViewjugador2.setText("Jugador 2: " + puntosjugador2);
    }

    private void borrarPantalla(){
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                buttons[i][j].setText("");
            }
        }
        conteorondas = 0;
        turnojugador1 = true;
    }
    private void resetJuego(){
        puntosjugador1 = 0;
        puntosjugador2 = 0;
        actualizarPuntos();
        borrarPantalla();
    }
}
