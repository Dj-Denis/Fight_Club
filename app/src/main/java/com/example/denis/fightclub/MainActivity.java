package com.example.denis.fightclub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Denis on 06.05.2016.
 */
public class MainActivity extends AppCompatActivity {

    private SharedPreferences session;
    public static final String Game_Session = "Session";
    public static final String Is_Game_End = "End";
    public static boolean isContinue;
    Button gameCont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameCont = (Button) findViewById(R.id.continueGame);
        gameCont.setClickable(false);
        gameCont.setVisibility(View.INVISIBLE);
        session = getSharedPreferences(Game_Session,MODE_PRIVATE);
        if (session.getBoolean(Is_Game_End,false) == false){
            gameCont.setClickable(true);
            gameCont.setVisibility(View.VISIBLE);
        }
    }


    public void newGame(View view) {
        isContinue = false;
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }


    public void continueGame(View view) {
        isContinue = true;
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }
}
