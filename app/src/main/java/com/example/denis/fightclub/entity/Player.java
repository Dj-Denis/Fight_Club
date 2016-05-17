package com.example.denis.fightclub.entity;

import android.content.Context;
import android.content.Intent;

import com.example.denis.fightclub.util.DBManipulator;

import java.util.Random;

/**
 * Created by Denis on 09.05.2016.
 */
public class Player {

    private int currentHealth;
    private int attack;
    private int defence;
    private int attackVector = 0;
    private int defenceVector = 0;
    private int strength;
    private int agility;
    private int critMult;
    private int critChance;
    private int luck;
    private int maxHealth;
    private int portrait;
    private int points;
    private String name;

    final Random random = new Random();
    DBManipulator dbManipulator;

    public Player(Context context){
        dbManipulator = new DBManipulator(context);
    }

    public void newGame(){
        setCurrentHealth(maxHealth);
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    private int getAttack() {
        attack = random.nextInt(10) + 1;
        return attack;
    }

    public int doDamage (){
       int damage = getAttack();
        if (critChance() == true){
            damage = (int) (getAttack() * (1.5 + (0.5 * getCritMult())));
        }
        return damage;
    }

    public void takeDamage (int enemeDamage, int enemyAttackeVector){
        boolean tmp = evesion();
        if (tmp != true){
        if (enemyAttackeVector == defenceVector){
        currentHealth = currentHealth -(enemeDamage - getDefence());
        }else {
            currentHealth = currentHealth - enemeDamage;}}
    }

    public int getDefence() {
        defence = random.nextInt(5) + 1;
        return defence;
    }

    public void setAttackVector(int attackVector) {
        this.attackVector = attackVector;
    }

    public void setDefenceVector(int defenceVector) {
        this.defenceVector = defenceVector;
    }

    public int getAttackVector() {
        return attackVector;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        dbManipulator.setCritChance(critChance);
        this.critChance = critChance;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        dbManipulator.setLuck(luck);
        this.luck = luck;
    }


    public int getPortrait() {
        return portrait;
    }

    public void setPortrait(int portrait) {
        dbManipulator.setCharacterPortrait(portrait);
        this.portrait = portrait;
    }

    public void refresh(){
        int[] s;
        s = dbManipulator.getAll();
        strength = s[0];
        agility = s[1];
        critMult = s[2];
        critChance = s[3];
        luck = s[4];
        portrait = s[5];
        points = s[6];
        name = dbManipulator.getName();
        maxHealth = 100 + (strength * 50);
    }

    public int getCritMult() {
        return critMult;
    }

    public void setCritMult(int critMult) {
        dbManipulator.setCritChance(critMult);
        this.critMult = critMult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        dbManipulator.setName(name);
        this.name = name;
    }

    private boolean evesion(){
        boolean evesion = false;
        if (1 == (random.nextInt(1001 - (10 * agility))) + 1){
            evesion = true;
        }
        return evesion;
    }



    private boolean critChance(){
        boolean crit = false;
        if (1 == (random.nextInt(1001 - (10 * critChance))) + 1){
            crit = true;
        }
        return crit;
    }

    public  boolean isYouLucky(){
        if (1 == (random.nextInt(1001 - (10 * luck))) + 1){
        setPoints(points + 1);
        return true;
        }else{
        return false;}
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        dbManipulator.setPoint(points);
        this.points = points;
    }

    public String[] getAll(){
        String[] all = new String[8];
        all[0] = Integer.toString(strength);
        all[1] = Integer.toString(agility);
        all[2] = Integer.toString(critChance);
        all[3] = Integer.toString(critMult);
        all[4] = Integer.toString(luck);
        all[5] = Integer.toString(portrait);
        all[6] = Integer.toString(points);
        all[7] = name;
        return all;
    }

    public void writeToDB(String[] all){
        dbManipulator.setStrenght(Integer.parseInt(all[0]));
        dbManipulator.setAgility(Integer.parseInt(all[1]));
        dbManipulator.setCritChance(Integer.parseInt(all[2]));
        dbManipulator.setCritMult(Integer.parseInt(all[3]));
        dbManipulator.setLuck(Integer.parseInt(all[4]));
        dbManipulator.setCharacterPortrait(Integer.parseInt(all[5]));
        dbManipulator.setPoint(Integer.parseInt(all[6]));
        dbManipulator.setName(name);
    }

    public int numberOfPoints(){
        int tmp;
        tmp = strength + agility + critChance + critMult + luck;
        return tmp;
    }
}
