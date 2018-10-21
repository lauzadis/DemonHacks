package com.example.motbot.demonhacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Launches whenever player enters a shop
 * Displays a gun or soldier for purchase
 */
public class vendor extends AppCompatActivity {

    Gun gunForSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        //Fetches user's current gun in order to display the next highest tier for sale
        Gun currentUserGun = userTower.getGun().getGunTier();

        //Fetch and set the gun's price
        int purchasePrice = currentUserGun.getCost();

        //The gun for sale will be the next highest tier
        if(currentUserGun.getGunTier() < 5){
            Gun gunForSale = new Gun(currentUserGun.getGunTier() + 1);
        }

        //If the gun is max tier then it will display that the weapons have been maxed out
        else{
            Gun gunForSale = new Gun(5);
        }

        //Create the gun button
        final ImageButton gunImageButton = (ImageButton)findViewById(R.id.gunImageButton);

        //Determine if user has maxed out weapon
        if(currentUserGun.getGunTier() == 5){
            gunImageButton.setImageResource(R.drawable.gun5max);
            gunImageButton.setEnabled(false);
        }

        //Determine whether the user has enough currency to afford the weapon
        else if(userTower.getCoins() < purchasePrice){
            switch(gunForSale.getGunTier()) {
                case (2):
                    gunImageButton.setImageResource(R.drawable.gun2unavailable);
                    gunImageButton.setEnabled(false);
                    break;
                case (3):
                    gunImageButton.setImageResource(R.drawable.gun3unavailable);
                    gunImageButton.setEnabled(false);
                    break;
                case (4):
                    gunImageButton.setImageResource(R.drawable.gun4unavailable);
                    gunImageButton.setEnabled(false);
                    break;
                case (5):
                    gunImageButton.setImageResource(R.drawable.gun5unavailable);
                    gunImageButton.setEnabled(false);
                    break;
            }
        }

        //Otherwise allow user to purchase a weapon if desired
        else{
            switch(gunForSale.getGunTier()) {
                case (2):
                    gunImageButton.setImageResource(R.drawable.gun2withresources);
                    break;
                case (3):
                    gunImageButton.setImageResource(R.drawable.gun3withresources);
                    break;
                case (4):
                    gunImageButton.setImageResource(R.drawable.gun4withresources);
                    break;
                case (5):
                    gunImageButton.setImageResource(R.drawable.gun5withresources);
                    break;
            }
        }

        //If a weapon is purchased, disable further purchases and decrease user's coins
        gunImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userTower.setCoins(userTower.getCoins()-gunForSale.getCost());

                switch(gunForSale.getGunTier()) {
                    case (2):
                        gunImageButton.setImageResource(R.drawable.gun2unavailable);
                        gunImageButton.setEnabled(false);
                        break;
                    case (3):
                        gunImageButton.setImageResource(R.drawable.gun3unavailable);
                        gunImageButton.setEnabled(false);
                        break;
                    case (4):
                        gunImageButton.setImageResource(R.drawable.gun4unavailable);
                        gunImageButton.setEnabled(false);
                        break;
                    case (5):
                        gunImageButton.setImageResource(R.drawable.gun5unavailable);
                        gunImageButton.setEnabled(false);
                        break;
                }
            }
        });
    }


}
