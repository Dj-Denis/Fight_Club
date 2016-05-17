package com.example.denis.fightclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by Denis on 11.05.2016.
 */
public class PortraitActivity extends AppCompatActivity {
    public int character;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portrait);
        fullscreen();
    }

    public void setPortrait(){
        MainActivity.player.setPortrait(character);
        Intent returnIntent = new Intent();
        setResult(RESULT_OK,returnIntent);
        super.onBackPressed();
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

    public void character1(View view) {
    character = R.drawable.character1;
        setPortrait();
    }

    public void character2(View view) {
        character = R.drawable.character2;
        setPortrait();
    }

    public void character3(View view) {
        character = R.drawable.character3;
        setPortrait();
    }

    public void character4(View view) {
        character = R.drawable.character4;
        setPortrait();
    }

    public void character5(View view) {
        character = R.drawable.character5;
        setPortrait();
    }

    public void character6(View view) {
        character = R.drawable.character6;
        setPortrait();
    }

    public void character7(View view) {
        character = R.drawable.character7;
        setPortrait();
    }

    public void character8(View view) {
        character = R.drawable.character8;
        setPortrait();
    }

    public void character9(View view) {
        character = R.drawable.character9;
        setPortrait();
    }

    public void character10(View view) {
        character = R.drawable.character10;
        setPortrait();
    }

    public void character11(View view) {
        character = R.drawable.character11;
        setPortrait();
    }

    public void character12(View view) {
        character = R.drawable.character12;
        setPortrait();
    }

    public void character13(View view) {
        character = R.drawable.character13;
        setPortrait();
    }

    public void character14(View view) {
        character = R.drawable.character14;
        setPortrait();
    }

    public void character15(View view) {
        character = R.drawable.character15;
        setPortrait();
    }

    public void character16(View view) {
        character = R.drawable.character16;
        setPortrait();
    }

    public void character17(View view) {
        character = R.drawable.character17;
        setPortrait();
    }

    public void character18(View view) {
        character = R.drawable.character18;
        setPortrait();
    }

    public void character19(View view) {
        character = R.drawable.character19;
        setPortrait();
    }

    public void character20(View view) {
        character = R.drawable.character20;
        setPortrait();
    }

    public void character21(View view) {
        character = R.drawable.character21;
        setPortrait();
    }

    public void character22(View view) {
        character = R.drawable.character22;
        setPortrait();
    }

    public void character23(View view) {
        character = R.drawable.character23;
        setPortrait();
    }

    public void character24(View view) {
        character = R.drawable.character24;
        setPortrait();
    }

    public void character25(View view) {
        character = R.drawable.character25;
        setPortrait();
    }

    public void character26(View view) {
        character = R.drawable.character26;
        setPortrait();
    }

    public void character27(View view) {
        character = R.drawable.character27;
        setPortrait();
    }

    public void character28(View view) {
        character = R.drawable.character28;
        setPortrait();
    }

    public void character29(View view) {
        character = R.drawable.character29;
        setPortrait();
    }

    public void character30(View view) {
        character = R.drawable.character30;
        setPortrait();
    }

    public void character31(View view) {
        character = R.drawable.character31;
        setPortrait();
    }

    public void character32(View view) {
        character = R.drawable.character32;
        setPortrait();
    }

    public void character33(View view) {
        character = R.drawable.character33;
        setPortrait();
    }

    public void character34(View view) {
        character = R.drawable.character34;
        setPortrait();
    }

    public void character35(View view) {
        character = R.drawable.character35;
        setPortrait();
    }
}
