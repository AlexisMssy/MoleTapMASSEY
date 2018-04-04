package org.diiage.massey.moletapmassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Session session;
    public static final int extra_int = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisation
        session = new Session();

        //Récupération des bouton dans le layout principal
        Button btnNewGame = findViewById(R.id.btnNewGame);
        Button btnScores = findViewById(R.id.btnScores);

        //Récupération du texte dans le layout principal
        TextView txtName = findViewById(R.id.txtName);

        //Maj de la session
        session.setDate(Calendar.getInstance().getTime());
        session.setNom(txtName.getText().toString());

        //Affectation de l'ouverture du jeu au clic sur le bouton
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivityForResult(intent, MainActivity.extra_int);
            }
        });

        //Affectation de l'ouverture des scores au clic sur le bouton
        btnScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,ScoresActivity.class);
                intent.putExtra("session", session);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int monScore = 0;
        int nbManquee = 0;
        Score sc = new Score();
        // Check which request we're responding to
        if (requestCode == extra_int) {
            Bundle extras = data.getExtras();
            monScore = (int) extras.get("score");
            nbManquee = (int) extras.get("loupee");
            sc.setNbPoints(monScore);
            sc.setNbTaupeManquees(nbManquee);
            session.addScore(sc);

            Toast toast = Toast.makeText(getApplicationContext(), "Score: " + String.valueOf(sc.getNbPoints()), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
