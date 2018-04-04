package org.diiage.massey.moletapmassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScoresActivity extends AppCompatActivity {

    private Session session;
    private TextView txtPoints1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        //Initialisation
        session = new Session();
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            session = bd.getParcelable("session");
        }

        //Récupération du texte dans le layout du jeu
        txtPoints1 = findViewById(R.id.txtPoints1);

        txtPoints1.setText(String.valueOf(session.getScores().get(0).getNbPoints()));

    }
}
