import com.example.motbot.demonhacks.Tower;

import java.util.Random;
package com.example.motbot.demonhacks;

public class Attack {
    private int mobs;
    private Tower tower = new Tower();
    private int coins;
    private int damage;
    private String message;
    //Constructor
    public Attack(Tower t) {
        Random r = new Random();
        this.tower = t;
        switch(t.getLevel()) {
            case 1:
                mobs = r.nextInt(5) + 8;
                damage = r.nextInt(51) + 200;
                break;
            case 2:
                mobs = r.nextInt(9) + 16;
                damage = r.nextInt(101) + 400;
                break;
            case 3:
                mobs = r.nextInt(13) + 24;
                damage = r.nextInt(151) + 600;
                break;
            case 4:
                mobs = r.nextInt(17) + 32;
                damage = r.nextInt(201) + 800;
                break;
            case 5:
                mobs = r.nextInt(21) + 40;
                damage = r.nextInt(251) + 1000;
                break;
            default:
                break;
        }
        coins = mobs + (int) Math.ceil(mobs* r.nextDouble(t.getGun().getGunMultiplier()));
        message = "Your tower was attacked! Your defenses killed " +
                mobs + " enemies who did " + damage + ". You gained " +
                coins + " coins in the attack!";
    }
    public int getMobs() {
        return mobs;
    }
    public void setMobs(int mobs) {
        this.mobs = mobs;
    }
    public Tower getTower() {
        return tower;
    }
    public void setTower(Tower tower) {
        this.tower = tower;
    }
    public int getCoins() {
        return coins;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}