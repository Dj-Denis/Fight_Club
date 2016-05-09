package com.example.denis.fightclub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Random;

public class GameActivity extends AppCompatActivity  {

    public int dPlayer [] = new int[4];
    public int aPlayer [] = new int[4];

    public int dEnemy [] = new int[4];
    public int aEnemy [] = new int[4];

    public int playerHealth = 100;
    public int enemyHealth = 100;

    public boolean gameEnd = false;

    private SharedPreferences session;
    public static final String Game_Session = "Session";
    public static final String Player_Health = "Player";
    public static final String Enemy_Health = "Enemy";
    public static final String Is_Game_End = "End";

    final Random random = new Random();

    ProgressBar playerHealthBar;
    ProgressBar enemyHealthBar;
    TextView playerHealtText;
    TextView enemyHealthText;
    TextView battleLog;
    Button Go;
    Button menu;
    Button newGame;
    ScrollView logScroll;

    public String log = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        playerHealthBar =  (ProgressBar) findViewById(R.id.playerHealthBar);
        enemyHealthBar =  (ProgressBar) findViewById(R.id.enemyHealthBar);
        playerHealtText = (TextView) findViewById(R.id.playerHealth);
        enemyHealthText = (TextView) findViewById(R.id.enemyHealth);
        battleLog =(TextView) findViewById(R.id.battleLog);
        menu = (Button) findViewById(R.id.Menu);
        newGame = (Button) findViewById(R.id.newGame);
        logScroll = (ScrollView) findViewById(R.id.scrollLog);
        Go = (Button) findViewById(R.id.Start);
        Go.setClickable(true);
        dPlayer [0] = 1;
        aPlayer [0] = 1;
        session = getSharedPreferences(Game_Session,MODE_PRIVATE);

        if (MainActivity.isContinue == true) {
            playerHealth = session.getInt(Player_Health,0);
            enemyHealth = session.getInt(Enemy_Health,0);
            playerHealthBar.setProgress(playerHealth);
            playerHealtText.setText(""+playerHealth);
            enemyHealthBar.setProgress(enemyHealth);
            enemyHealthText.setText(""+enemyHealth);
        }
    }
    public void gameCycle(){
        int pDamage = random.nextInt(10) + 1;
        int pDef = random.nextInt(5) + 1;
        int eDamage = random.nextInt(10) + 1;
        int eDef = random.nextInt(5) + 1;
        enemy();
        if (indexOfIntArray(aPlayer,1) == indexOfIntArray(dEnemy,1)) {
            enemyHealth = enemyHealth - (pDamage - eDef);
            if (pDamage > eDef){
            log = log + "\nYou deal: " + (pDamage - eDef) + " damage.";}
            else {log = log +"\nEnemy dodged.";}
        } else {enemyHealth = enemyHealth - pDamage;
            log = log + "\nYou deal: " + (pDamage) + " damage.";}

        if (indexOfIntArray(dPlayer,1) == indexOfIntArray(aEnemy,1)) {
            playerHealth = playerHealth - (eDamage - pDef);
            if (eDamage > pDef){
                log = log + "\nYou got: " + (eDamage - pDef) + " damage";
            } else { log = log +"\nYou avoided enemy hit";}
        } else {playerHealth = playerHealth - eDamage;
        log = log + "\nYou recive: " + eDamage +" damage";}
        battleLog.setText(log);
        SystemClock.sleep(100);
        //logScroll.fullScroll(View.FOCUS_DOWN);
        logScroll.scrollTo(0,logScroll.getHeight());

        if (playerHealth <= 0 || enemyHealth <= 0){
            end();
        }

    }



    public void playerRadio(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.playerHead:
                if (checked){
                    radioSwitch("Player","Head");
                } break;
            case R.id.playerArms:
                if (checked){
                    radioSwitch("Player","Arm");
                } break;
            case R.id.playerChest:
                if (checked){
                    radioSwitch("Player","Chest");
                } break;
            case R.id.playerLegs:
                if (checked){
                    radioSwitch("Player","Leg");
                } break;
        }
    }

    public void enemyRadio (View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.enemyHead:
                if (checked){
                    radioSwitch("Enemy","Head");
                } break;
            case R.id.enemyArm:
                if (checked){
                    radioSwitch("Enemy","Arm");
                } break;
            case R.id.enemyChest:
                if (checked){
                    radioSwitch("Enemy","Chest");
                } break;
            case R.id.enemyLegs:
                if (checked){
                    radioSwitch("Enemy","Arm");
                }break;
        }
    }

    public void radioSwitch (String s, String b)
    {
        if (Objects.equals(s, "Player")){
            switch (b){
                case "Head" :
                {   dPlayer [0] = 1;
                    dPlayer [1] = 0;
                    dPlayer [2] = 0;
                    dPlayer [3] = 0;
                } break;
                case "Arm" :{
                    dPlayer [0] = 0;
                    dPlayer [1] = 1;
                    dPlayer [2] = 0;
                    dPlayer [3] = 0;
                } break;
                case "Chest" :{
                    dPlayer [0] = 0;
                    dPlayer [1] = 0;
                    dPlayer [2] = 1;
                    dPlayer [3] = 0;
                } break;
                case "Leg" :{
                    dPlayer [0] = 0;
                    dPlayer [1] = 0;
                    dPlayer [2] = 0;
                    dPlayer [3] = 1;
                } break;
            }
        }

        if (Objects.equals(s, "Enemy"))
        {
            switch (b){
                case "Head" :{
                    aPlayer [0] = 1;
                    aPlayer [1] = 0;
                    aPlayer [2] = 0;
                    aPlayer [3] = 0;
                } break;
                case "Arm" :{
                    aPlayer [0] = 0;
                    aPlayer [1] = 1;
                    aPlayer [2] = 0;
                    aPlayer [3] = 0;
                } break;
                case  "Chest" : {
                    aPlayer [0] = 0;
                    aPlayer [1] = 0;
                    aPlayer [2] = 1;
                    aPlayer [3] = 0;
                } break;
                case  "Leg" : {
                    aPlayer [0] = 0;
                    aPlayer [1] = 0;
                    aPlayer [2] = 0;
                    aPlayer [3] = 1;
                } break;
            }
        }
    }


    public void buttonStart(View view) {
        gameCycle();
        playerHealthBar.setProgress(playerHealth);
        playerHealtText.setText(""+playerHealth);
        enemyHealthBar.setProgress(enemyHealth);
        enemyHealthText.setText(""+enemyHealth);
    }

    public void enemy() {
        for (int i = 0; i < 4; i++ ) {
            dEnemy [i] = 0;
            aEnemy [i] = 0;
        }
        int rand1 = random.nextInt(4);
        int rand2 = random.nextInt(4);
        dEnemy [rand1] = 1;
        aEnemy [rand2] = 1;
    }



    public static int indexOfIntArray(int[] array, int key) {
        int returnvalue = -1;
        for (int i = 0; i < array.length; ++i) {
            if (key == array[i]) {
                returnvalue = i;
                break;
            }
        }
        return returnvalue;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (playerHealth > 0 && enemyHealth > 0)
        {
            SharedPreferences.Editor editor = session.edit();
            editor.putInt(Player_Health,playerHealth);
            editor.putInt(Enemy_Health,enemyHealth);
            editor.apply();
            gameEnd = false;
        }
    }

    public void end() {
        if (playerHealth <= 0) {
            Toast.makeText(getApplicationContext(), "You lose", Toast.LENGTH_LONG).show();
            Go.setClickable(false);
        }

        if (enemyHealth <= 0) {
            Toast.makeText(getApplicationContext(), "You win", Toast.LENGTH_LONG).show();
            Go.setClickable(false);
        }
        SharedPreferences.Editor editor = session.edit();
        battleLog.setVisibility(View.INVISIBLE);
        gameEnd = true;
        editor.putBoolean(Is_Game_End,gameEnd);
        editor.apply();
        menu.setVisibility(View.VISIBLE);
        menu.setClickable(true);
        newGame.setVisibility(View.VISIBLE);
        newGame.setClickable(true);
    }

    public void mainMenu(View view) {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void newGame(View view) {
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(intent);
    }
}
