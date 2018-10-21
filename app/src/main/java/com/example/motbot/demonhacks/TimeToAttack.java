package com.example.motbot.demonhacks;
import java.lang.Math;
import java.time.LocalDateTime;
public class TimeToAttack{
    private int minutes1;
    private int minutes2;
    private int minutes3;
    private int minutes4;
    private Tower t = new Tower();
    //Constructors
    public TimeToAttack(Tower t) {
        this.t = t;
        LocalDateTime time = LocalDateTime.now();
        minutes1 = (time.getHour()*60 + time.getMinute()) - 480;
        minutes2 = 180 + (int) Math.ceil(Math.random()*60) + minutes1;
        minutes3 = 180 + (int) Math.ceil(Math.random()*60) + minutes2;
        minutes4 = 180 + (int) Math.ceil(Math.random()*60) + minutes3;
    }
    //Methods
    public void sendAttack() {
        Attack a = new Attack(t);
        String message = a.getMessage();
        t.setHealth(t.getHealth() - a.getDamage());
        t.setCoins(t.getCoins() + a.getCoins());
    }
}