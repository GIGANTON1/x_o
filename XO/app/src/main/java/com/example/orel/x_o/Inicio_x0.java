package com.example.orel.x_o;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Inicio_x0 extends AppCompatActivity {

    Button boton_next;
    MediaPlayer mp3;
    EditText nombresjugadores;//Jugadores de los jugadores

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_x0);
        /*------------------------------AVANZAR AL MAIN ACTIVITY---------------------------------*/
        boton_next = (Button) findViewById(R.id.inicio_boton_next);

        mp3 = MediaPlayer.create(this, R.raw.fight1);//Llama el sonido del raw declarandolo en mp3

        boton_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Inicio_x0.this, MainActivity.class);
                startActivity(i);
                mp3.start();//ejecuta sonido al darle click
                /*------------------------------Cambiar los NOmbres-----------------------------------------*/
                /*Intent a = new Intent(this, MainActivity.class);
                Bundle nombres = new Bundle();
                nombres.putString("nombres", nombresjugadores.getText().toString());
                a.putExtras(nombres);
                startActivity(a);*/

            }
        });
        /*------------------------------AVANZAR AL MAIN ACTIVITY---------------------------------*/

    }

}
