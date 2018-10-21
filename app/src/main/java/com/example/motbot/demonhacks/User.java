package com.example.motbot.demonhacks;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String username;
    public String password;
    public int coins;
    public int resources;
    public int towerHealth;
    public int towerTier;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String password, int coins, int resources, int towerHealth, int towerTier ) {
        this.username = username;
        this.password = password;
        this.coins = coins;
        this.resources = resources;
        this.towerHealth = towerHealth;
        this.towerTier = towerTier;
    }

    private void writeNewUser(String userId, String username, String password, int coins, int resources, int towerHealth, int towerTier ) {
        User user = new User(username, password, coins, resources, towerHealth, towerTier);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("users").child(userId).setValue(user);
    }

}