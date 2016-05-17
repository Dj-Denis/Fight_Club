package com.example.denis.fightclub;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.fightclub.entity.Enemy;
import com.example.denis.fightclub.entity.Player;


public class GameActivity extends AppCompatActivity  {


    ProgressBar playerHealthBar;
    ProgressBar enemyHealthBar;
    TextView playerHealtText;
    TextView enemyHealthText;
    Button Go;
    Button menu;
    Button newGame;
    Player player;
    Enemy enemy;
    ImageView playerPortrait, enemyPortrait;
    TextView playerName,enemyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        fullscreen();
        playerName = (TextView) findViewById(R.id.playerName);
        playerHealthBar =  (ProgressBar) findViewById(R.id.playerHealthBar);
        enemyHealthBar =  (ProgressBar) findViewById(R.id.enemyHealthBar);
        playerHealtText = (TextView) findViewById(R.id.playerHealth);
        enemyHealthText = (TextView) findViewById(R.id.enemyHealth);
        playerPortrait = (ImageView) findViewById(R.id.playerPortrait);
        enemyPortrait = (ImageView) findViewById(R.id.enemyPortrait);
        enemyName = (TextView) findViewById(R.id.enemyName);
        menu = (Button) findViewById(R.id.Menu);
        newGame = (Button) findViewById(R.id.newGame);
        Go = (Button) findViewById(R.id.Start);
        Go.setClickable(true);
        player = MainActivity.player;
        //player = new Player(this);
        playerName.setText(player.getName());
        enemy = new Enemy(player.numberOfPoints());
        enemyName.setText(enemy.getName(this));
        playerHealthBar.setMax(player.getMaxHealth());
        playerHealthBar.setProgress(player.getMaxHealth());
        playerHealtText.setText(""+player.getMaxHealth());

        Bitmap playerPort = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),player.getPortrait()),400,426,false);
        playerPortrait.setImageBitmap(playerPort);
        Bitmap enemyPort = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),enemy.getPortrait()),400,426,false);
        enemyPortrait.setImageBitmap(enemyPort);
        enemyHealthBar.setMax(enemy.getMaxHealth());
        enemyHealthBar.setProgress(enemy.getMaxHealth());
        enemyHealthText.setText(""+enemy.getMaxHealth());
    }
    public void gameCycle(){
        enemy.takeDamage(player.doDamage(),player.getAttackVector());
        player.takeDamage(enemy.doDamage(),enemy.getAttackVector());
        if (player.getCurrentHealth() <= 0 || enemy.getHealth() <= 0)
        {end();}
    }



    public void defenceRadio(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.playerHead:
                if (checked){
                    player.setDefenceVector(1);
                } break;
            case R.id.playerArms:
                if (checked){
                    player.setDefenceVector(2);
                } break;
            case R.id.playerChest:
                if (checked){
                    player.setDefenceVector(3);
                } break;
            case R.id.playerLegs:
                if (checked){
                    player.setDefenceVector(4);
                } break;
        }
    }

    public void attackRadio(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.enemyHead:
                if (checked){
                    player.setAttackVector(1);
                } break;
            case R.id.enemyArm:
                if (checked){
                    player.setAttackVector(2);
                } break;
            case R.id.enemyChest:
                if (checked){
                    player.setAttackVector(3);
                } break;
            case R.id.enemyLegs:
                if (checked){
                    player.setAttackVector(4);
                }break;
        }
    }


    public void buttonStart(View view) {
        gameCycle();
        playerHealthBar.setProgress(player.getCurrentHealth());
        playerHealtText.setText("" + player.getCurrentHealth());
        enemyHealthBar.setProgress(enemy.getHealth());
        enemyHealthText.setText("" + enemy.getHealth());
    }


    public void end() {
        if (player.getCurrentHealth() <= 0 && enemy.getHealth() <= 0)
        {
            Toast.makeText(getApplicationContext(), "Draw", Toast.LENGTH_LONG).show();
            Go.setClickable(false);
        }
        if (player.getCurrentHealth() <= 0 && enemy.getHealth() > 0) {
            Toast.makeText(getApplicationContext(), "You lose", Toast.LENGTH_LONG).show();
            Go.setClickable(false);
        }

        if (enemy.getHealth() <= 0 && player.getCurrentHealth() > 0) {
            Toast.makeText(getApplicationContext(), "You win", Toast.LENGTH_LONG).show();
            if (player.isYouLucky()){
                Toast.makeText(getApplication(),"You get an extra skill point", Toast.LENGTH_LONG).show();
            }

            Go.setClickable(false);
        }


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
        player.newGame();
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
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
}
