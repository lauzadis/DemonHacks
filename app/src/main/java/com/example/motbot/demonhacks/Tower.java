package com.example.motbot.demonhacks;

import com.here.android.mpa.common.GeoCoordinate;

public class Tower {
    private int level = 1;
    private int max_health = 1500 * level;
    private int max_shield = 0;
    private int health;
    private int shield;
    private Gun gun = new Gun(1);
    private int resources = 0;
    private int coins = 0;
    private GeoCoordinate geo = new GeoCoordinate(0,0,0);
    //Constructor
    public Tower(GeoCoordinate geo) {
        this.geo = geo;
        setHealth(getMax_health());
        setShield(getMax_shield());
    }
    public Tower() {
        setHealth(getMax_health());
        setShield(getMax_shield());
    }
    //Getters and Setters
    public Gun getGun() {
        return gun;
    }

    public GeoCoordinate getGeoCoordinate() {
        return geo;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public void setGeo(GeoCoordinate geo) {
        this.geo = geo;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMax_health(int max_health) {
        this.max_health = max_health;
    }

    public void setMax_shield(int max_shield) {
        this.max_shield = max_shield;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getMax_shield() {
        return max_shield;
    }

    public int getMax_health() {
        return max_health;
    }
    //Tower Functions
    //Upgrades tower to next level using resources while healing to maximum health
    public void upgrade() {
        switch(getLevel()) {
            case 1:
                if(getResources() >= 500) {
                    setLevel(2);
                    setResources(getResources() - 500);
                    setMax_health(getLevel() * 1500);
                    setHealth(getMax_health());
                }
                break;
            case 2:
                if(getResources() >= 1000) {
                    setLevel(3);
                    setResources(getResources() - 1000);
                    setMax_health(getLevel() * 1500);
                    setHealth(getMax_health());
                }
                break;
            case 3:
                if(getResources() >= 5000) {
                    setLevel(4);
                    setResources(getResources() - 5000);
                    setMax_health(getLevel() * 1500);
                    setHealth(getMax_health());
                }
                break;
            case 4:
                if(getResources() >= 10000) {
                    setLevel(5);
                    setResources(getResources() - 10000);
                    setMax_health(getLevel() * 1500);
                    setHealth(getMax_health());
                }
                break;
            default:
                break;
        }
    }
    //Repairs the tower to maximum health using resources
    public void repair() {
        int missing_health = getMax_health() - getHealth();
        if(getResources() > missing_health) {
            setHealth(getMax_health());
            setResources(getResources() - missing_health);
        }

    }
}//end Tower