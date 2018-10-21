package com.example.motbot.demonhacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Launches whenever player enters a shop
 * Can purchase a weapon or a soldier to increase health
 */
public class vendor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        //Fetch player's maximum money available to spend

        final Tower userTower = (Tower) getIntent().getExtras().get("tower");
        int currency = userTower.getCoins();

  

        //Instantiate textfield and both buttons in vending scene
        final TextView coinsLabel = (TextView) findViewById(R.id.coinsLabel);
        TextView needLabel = (TextView) findViewById(R.id.needLabel); //tells user how many more coins they need to afford the item
        final ImageButton gunButton = (ImageButton) findViewById(R.id.gunButton);
        Button exit = (Button) findViewById(R.id.exit);

        //Display the user's budget
        coinsLabel.setText(currency + " Coins Available");

        //Fetch player's weapon data

        Gun currentUserGun = userTower.getGun();
        int gunTier = currentUserGun.getGunTier();
        int gunPrice;

        //If the user's weapon is maxed out, make trade unavailable
        if (gunTier == 5) //max tier
        {
            gunButton.setEnabled(false); //disables clicking the object
            gunButton.setImageResource(R.drawable.gun5max);
            gunPrice = 99999;
        }
        //Otherwise fetch the information for the next available weapon


        else {
            gunPrice = new Gun(gunTier + 1).getCost();
        }
            //If user cannot afford to purchase a new weapon, don't allow them to
            if (currency < gunPrice) {
                gunButton.setEnabled(false);
                needLabel.setText("You need " + (userTower.getCoins() - gunPrice) + "more coins!");
                //Display the proper image for next purchase
                switch (gunTier + 1) {
                    case (2):
                        gunButton.setImageResource(R.drawable.gun2unavailable);
                        break;
                    case (3):
                        gunButton.setImageResource(R.drawable.gun3unavailable);
                        break;
                    case (4):
                        gunButton.setImageResource(R.drawable.gun4unavailable);
                        break;
                    case (5):
                        gunButton.setImageResource(R.drawable.gun5unavailable);
                        break;
                }

            }
            //If user can afford it, display proper weapon
            else {
                switch (gunTier + 1) {
                    case (2):
                        gunButton.setImageResource(R.drawable.gun2withresources);
                        break;
                    case (3):
                        gunButton.setImageResource(R.drawable.gun3withresources);
                        break;
                    case (4):
                        gunButton.setImageResource(R.drawable.gun4withresources);
                        break;
                    case (5):
                        gunButton.setImageResource(R.drawable.gun5withresources);
                        break;

                }

                //Subtract money, disable box, change image after purchase
                gunButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Need to recapture gunPrice and and gunTier
                        Gun storeGun = new Gun(userTower.getGun().getGunTier());
                        int gunTier = storeGun.getGunTier();
                        int gunPrice = storeGun.getCost();
                        int currency = userTower.getCoins();

                        currency = currency - gunPrice;
                        userTower.setCoins(currency);
                        gunButton.setEnabled(false);
                        coinsLabel.setText(currency + " Coins Remaining");
                        gunButton.setEnabled(false);
                        switch (gunTier + 1) {
                            case (2):
                                gunButton.setImageResource(R.drawable.gun2unavailable);
                                break;
                            case (3):
                                gunButton.setImageResource(R.drawable.gun3unavailable);
                                break;
                            case (4):
                                gunButton.setImageResource(R.drawable.gun4unavailable);
                                break;
                            case (5):
                                gunButton.setImageResource(R.drawable.gun5unavailable);
                                break;
                        }
                    }
                });
            }



            //Exit the shop scene when button pressed
            exit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            finish();
                                        }
                                    }

            );
    }//end onCreate
}//end class
