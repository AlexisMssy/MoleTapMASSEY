package org.diiage.massey.moletapmassey;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private static Timer timer;
    private static TextView txtChrono;
    private static TextView txtPoints;
    private static int monChrono;
    private static ArrayList<ImageButton> boutons;
    private static Score score;
    private static ImageButton currentMole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Initialisation
        monChrono = 0;
        timer = new Timer();
        score = new Score();

        //Représente ma liste de bouton
        boutons = new ArrayList<>();

        //Récupération des bouton dans le layout du jeu
        ImageButton mole1 = findViewById(R.id.mole1);
        ImageButton mole2 = findViewById(R.id.mole2);
        ImageButton mole3 = findViewById(R.id.mole3);
        ImageButton mole4 = findViewById(R.id.mole4);
        ImageButton mole5 = findViewById(R.id.mole5);
        ImageButton mole6 = findViewById(R.id.mole6);
        ImageButton mole7 = findViewById(R.id.mole7);
        ImageButton mole8 = findViewById(R.id.mole8);
        ImageButton mole9 = findViewById(R.id.mole9);

        //Ajout des listener
        mole1.setOnClickListener(this);
        mole2.setOnClickListener(this);
        mole3.setOnClickListener(this);
        mole4.setOnClickListener(this);
        mole5.setOnClickListener(this);
        mole6.setOnClickListener(this);
        mole7.setOnClickListener(this);
        mole8.setOnClickListener(this);
        mole9.setOnClickListener(this);

        //Ajout des boutons à ma liste
        boutons.add(mole1);
        boutons.add(mole2);
        boutons.add(mole3);
        boutons.add(mole4);
        boutons.add(mole5);
        boutons.add(mole6);
        boutons.add(mole7);
        boutons.add(mole8);
        boutons.add(mole9);

        //Récupération du texte dans le layout du jeu
        txtChrono = findViewById(R.id.txtChrono);
        txtPoints = findViewById(R.id.txtPoints);

        startTimer();

        //Les taupes se cache
        for (ImageButton btn : boutons) {
            btn.setImageDrawable(null);
        }
    }

    private ImageButton anyItem(ArrayList<ImageButton> list)
    {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(list.size());
        ImageButton item = list.get(index);
        return item;
    }


    @Override
    public void onClick(View view) {
        ImageButton imageClick = (ImageButton)view;
        if (imageClick == currentMole){
            score.setNbPoints(score.getNbPoints() + 1);
            txtPoints.setText(String.valueOf(score.getNbPoints()));
            currentMole.setImageDrawable(null);
            currentMole = null;
        }
        else
        {
            score.setNbTaupeManquees(score.getNbTaupeManquees() + 1);
        }
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "Finish please ! ", Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void startTimer() {
        Boolean isTimerRunning = true;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                monChrono = Integer.parseInt(txtChrono.getText().toString());
                monChrono -= 1;
                mHandler.obtainMessage(1).sendToTarget();
                if (monChrono <= 0){
                    timer.cancel();
                    Intent intent = new Intent(GameActivity.this, MainActivity.class);
                    intent.putExtra("score", score.getNbPoints());
                    intent.putExtra("loupee", score.getNbTaupeManquees());
                    setResult(MainActivity.extra_int, intent);
                    finish();
                }

            }
        }, 0, 1000);
    }

    @SuppressLint("HandlerLeak")
    public static Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            txtChrono.setText(String.valueOf(monChrono)); //this is the textview
            Random randomGenerator = new Random();
            int position = randomGenerator.nextInt((8 - 0)+1) + 0;
            ImageButton btn = boutons.get(position);
            if (currentMole != btn){
                if (currentMole != null){
                    currentMole.setImageDrawable(null);
                }
                btn.setImageResource(R.drawable.lilmole);
                currentMole = btn;
            }
        }
    };
}
