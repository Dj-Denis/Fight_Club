package com.example.denis.fightclub.entity;

import android.content.Context;
import android.content.res.Resources;

import com.example.denis.fightclub.MainActivity;
import com.example.denis.fightclub.R;

import java.util.Random;

/**
 * Created by Denis on 10.05.2016.
 */
public class Enemy {
    private int health;
    private int attack;
    private int defence;
    private int attackVector;
    private int defenceVector;
    private int strength;
    private int agility;
    private int critMult;
    private int critChance;
    private int portrait;

    public int getMaxHealth() {
        return maxHealth;
    }

    private int maxHealth;

    final Random random = new Random();

    public Enemy(int playerPoints){
        for (int i = 0; i < playerPoints; i++){
            switch (random.nextInt(4)){
                case 0: ++strength; break;
                case 1: if (agility < 100){++agility;} break;
                case 2: ++ critMult; break;
                case 3: if (critChance < 100){++critChance;} break;
            }
        }
        maxHealth = (100 + (50 * strength));
        health = maxHealth;
        portrait();
    }

    public int getHealth() {
        return health;
    }


    private int getAttack() {
        attack = random.nextInt(10) + 1;
        return attack;
    }

    public int doDamage() {
        int damage = getAttack();
        if (critChance() == true){
            damage = (int) (getAttack() * (1.5 + (0.5 * critMult)));
        }
        return damage;
    }

    public void takeDamage(int playerDamage, int playerAttackeVector) {
        defenceVector = random.nextInt(4) + 1;
        boolean tmp = evesion();
        if (tmp != true){
            if (playerAttackeVector == defenceVector){
                health = health -(playerDamage - getDefence());
            }else {
                health = health - playerDamage;}}
    }

    private int getDefence() {
        defence = random.nextInt(5) + 1;
        return defence;
    }

    public int getAttackVector() {
        attackVector = random.nextInt(10) + 1;
        return attackVector;
    }

    private boolean critChance(){
        boolean crit = false;
        if (1 == (random.nextInt(1001 - (10 * critChance))) + 1){
            crit = true;
        }
        return crit;
    }

    private boolean evesion(){
        boolean evesion = false;
        if (1 == (random.nextInt(1001 - (10 * agility))) + 1){
            evesion = true;
        }
        return evesion;
    }

    private void portrait(){
        switch (random.nextInt(35)){
            case 0: portrait = R.drawable.character1; break;
            case 1: portrait = R.drawable.character2; break;
            case 2: portrait = R.drawable.character3; break;
            case 3: portrait = R.drawable.character4; break;
            case 4: portrait = R.drawable.character5; break;
            case 5: portrait = R.drawable.character6; break;
            case 6: portrait = R.drawable.character7; break;
            case 7: portrait = R.drawable.character8; break;
            case 8: portrait = R.drawable.character9; break;
            case 9: portrait = R.drawable.character10; break;
            case 10: portrait = R.drawable.character11; break;
            case 11: portrait = R.drawable.character12; break;
            case 12: portrait = R.drawable.character13; break;
            case 13: portrait = R.drawable.character14; break;
            case 14: portrait = R.drawable.character15; break;
            case 15: portrait = R.drawable.character16; break;
            case 16: portrait = R.drawable.character17; break;
            case 17: portrait = R.drawable.character18; break;
            case 18: portrait = R.drawable.character19; break;
            case 19: portrait = R.drawable.character20; break;
            case 20: portrait = R.drawable.character21; break;
            case 21: portrait = R.drawable.character22; break;
            case 22: portrait = R.drawable.character23; break;
            case 23: portrait = R.drawable.character24; break;
            case 24: portrait = R.drawable.character25; break;
            case 25: portrait = R.drawable.character26; break;
            case 26: portrait = R.drawable.character27; break;
            case 27: portrait = R.drawable.character28; break;
            case 28: portrait = R.drawable.character29; break;
            case 29: portrait = R.drawable.character30; break;
            case 30: portrait = R.drawable.character31; break;
            case 31: portrait = R.drawable.character32; break;
            case 32: portrait = R.drawable.character33; break;
            case 33: portrait = R.drawable.character34; break;
            case 34: portrait = R.drawable.character35; break;
        }
    }

    public int getPortrait() {
        return portrait;
    }

    public String getName(Context context){
        String[] name = context.getResources().getStringArray(R.array.enemy);
        return name[random.nextInt(name.length)];
    }
}
