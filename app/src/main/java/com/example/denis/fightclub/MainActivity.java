package com.example.denis.fightclub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.denis.fightclub.entity.Player;


/**
 * Created by Denis on 06.05.2016.
 */
public class MainActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "Settings";
    public static final String RUN_ONCE = "run";
    private SharedPreferences sharedPreferences;
    static Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (!(sharedPreferences.getBoolean(RUN_ONCE,false))){

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(RUN_ONCE,true);
            editor.apply();
        }

        player = new Player(this);
        player.refresh();
        fullscreen();
    }


    @Override
    protected void onResume() {
        super.onResume();
        fullscreen();
    }

    public void newGame(View view) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        player.newGame();
        startActivity(intent);
    }


    public void fullscreen(){
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }

    public void Hero(View view) {
        Intent intent = new Intent(MainActivity.this,HeroActivity.class);
        startActivity(intent);
    }
}

