package com.example.denis.fightclub;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Denis on 10.05.2016.
 */
public class HeroActivity extends AppCompatActivity{
    private String name;
    private int strenght;
    private int strengthTmp;
    private int agility;
    private int agilityTmp;
    private int crit_chance;
    private int critChanceTmp;
    private int crit_mult;
    private int critMultTmp;
    private int luck;
    private int luckTmp;
    private int points;
    private int pointTmp;
    private int characterPortrait;

    TextView playerAgility,playerStrenght,playerCritMult,playerCritChance,playerLuck,freePoints;
    EditText playerName;
    ImageView portrait;
    Button setButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);
        playerName = (EditText) findViewById(R.id.PlayerName);
        portrait = (ImageView) findViewById(R.id.characterPortrait);
        playerAgility = (TextView) findViewById(R.id.playerAgility);
        playerStrenght = (TextView) findViewById(R.id.playerStrength);
        playerCritChance = (TextView) findViewById(R.id.playerCrit);
        playerCritMult = (TextView) findViewById(R.id.critMult);
        playerLuck = (TextView) findViewById(R.id.playerLuck);
        freePoints = (TextView) findViewById(R.id.freePoints);
        setButton = (Button) findViewById(R.id.setButton);
        getAll();
        playerName.setBackgroundColor(Color.TRANSPARENT);
        playerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fullscreen();
                name = playerName.getText().toString();
                MainActivity.player.setName(name);
            }
        });
        initialize();
        fullscreen();
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

    private void initialize(){
        playerStrenght.setText("" + strenght);
        playerAgility.setText("" + agility);
        playerCritChance.setText("" + crit_chance);
        playerCritMult.setText("" + crit_mult);
        playerLuck.setText("" + luck);
        playerName.setText(name);
        freePoints.setText("Free points: " + points);
        portrait.setImageResource(characterPortrait);
        strengthTmp = strenght;
        agilityTmp = agility;
        critChanceTmp = crit_chance;
        critMultTmp = crit_mult;
        luckTmp = luck;
        pointTmp = points;
    }

    public void characterPortrait(View view) {
        System.gc();
        Intent intent = new Intent(HeroActivity.this,PortraitActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCharacterPortrait();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                portrait = (ImageView) findViewById(R.id.characterPortrait);
            }
            if (requestCode == RESULT_CANCELED){
                portrait = (ImageView) findViewById(R.id.characterPortrait);
            }
        }
        portrait = (ImageView) findViewById(R.id.characterPortrait);
    }

    public void getAll(){
        String[] tmp;
        tmp = MainActivity.player.getAll();
        strenght = Integer.parseInt(tmp[0]);
        agility = Integer.parseInt(tmp[1]);
        crit_chance = Integer.parseInt(tmp[2]);
        crit_mult = Integer.parseInt(tmp[3]);
        luck = Integer.parseInt(tmp[4]);
        characterPortrait = Integer.parseInt(tmp[5]);
        points = Integer.parseInt(tmp[6]);
        name = tmp[7];
    }

    public void setCharacterPortrait(){
        characterPortrait = MainActivity.player.getPortrait();
        portrait.setImageResource(characterPortrait);
    }

    public void setButton(View view) {
        strenght = strengthTmp;
        agility = agilityTmp;
        crit_chance = critChanceTmp;
        crit_mult = critMultTmp;
        luck = luckTmp;
        points = pointTmp;
        String[] tmp = {Integer.toString(strenght),Integer.toString(agility),Integer.toString(crit_chance),Integer.toString(crit_mult),
        Integer.toString(luck),Integer.toString(characterPortrait),Integer.toString(points),name};
        MainActivity.player.writeToDB(tmp);
        MainActivity.player.refresh();
        freePoints.setText("Free points: "+points);
        setButton.setVisibility(View.INVISIBLE);
    }



    public void agilityPlus(View view) {
        if (pointTmp != 0 && agilityTmp < 100){
            agilityTmp++;
            playerAgility.setText("" + agilityTmp);
            pointTmp--;
            setButton.setVisibility(View.VISIBLE);
        }
    }

    public void agilityMinus(View view) {
        if (agilityTmp == agility){
        } else {
            agilityTmp --;
            pointTmp++;
            playerAgility.setText(""+agilityTmp);
            if (pointTmp == points){
                setButton.setVisibility(View.INVISIBLE);
            }}
    }

    public void strengthPlus(View view) {
        if (pointTmp != 0){
            strengthTmp++;
            playerStrenght.setText("" + strengthTmp);
            pointTmp--;
            setButton.setVisibility(View.VISIBLE);
        }
    }

    public void strengthMinus(View view) {
        if (strengthTmp == strenght){
        } else {
            strengthTmp --;
            pointTmp++;
            playerStrenght.setText(""+strengthTmp);
        if (pointTmp == points){
            setButton.setVisibility(View.INVISIBLE);
        }}
    }

    public void critChancePlus(View view) {
        if (pointTmp != 0 && critChanceTmp < 100){
            critChanceTmp++;
            playerCritChance.setText("" + critChanceTmp);
            pointTmp--;
            setButton.setVisibility(View.VISIBLE);
        }
    }

    public void critChanceMinus(View view) {
        if (critChanceTmp == crit_chance){
        } else {
            critChanceTmp --;
            pointTmp++;
            playerCritChance.setText(""+critChanceTmp);
            if (pointTmp == points){
                setButton.setVisibility(View.INVISIBLE);
            }}
    }


    public void critMultPlus(View view) {
        if (pointTmp != 0 ){
            critMultTmp++;
            playerCritMult.setText("" + critMultTmp);
            pointTmp--;
            setButton.setVisibility(View.VISIBLE);
        }
    }

    public void critMultMinus(View view) {
        if (critMultTmp == crit_mult){
        } else {
            critMultTmp --;
            pointTmp++;
            playerCritMult.setText(""+critMultTmp);
            if (pointTmp == points){
                setButton.setVisibility(View.INVISIBLE);
            }}
    }

    public void luckPlus(View view) {
        if (pointTmp != 0 && luckTmp < 100){
            luckTmp++;
            playerLuck.setText("" + luckTmp);
            pointTmp--;
            setButton.setVisibility(View.VISIBLE);
        }
    }

    public void luckMinus(View view) {
        if (luckTmp == luck){
        } else {
            luckTmp --;
            pointTmp++;
            playerLuck.setText(""+luckTmp);
            if (pointTmp == points){
                setButton.setVisibility(View.INVISIBLE);
            }}
    }

}
