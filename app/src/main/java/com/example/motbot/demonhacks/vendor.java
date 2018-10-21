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
        final double currency = userTower.getCoins();

        //Instantiate textfield and both buttons in vending scene
        final TextView coinsLabel = (TextView)findViewById(R.id.coinsLabel);
        TextView affordLabel = (TextView)findViewById(R.id.affordLabel); //tells user whether or not they can afford the item
        TextView needLabel = (TextView)findViewById(R.id.needLabel); //tells user how many more coins they need to afford the item
        final Button gunButton = (Button)findViewById(R.id.gunButton);
        Button exit = (Button)findViewById(R.id.exit);

        //Display the user's budget
        coinsLabel.setText(currency + " Coins Available");

        //Fetch player's weapon data
        Gun currentUserGun = user.getGun();
        int gunTier = currentUserGun.getGunTier();

        //default values for troubleshooting which should be overwritten anyways
        int gunPrice = 99999;
        String gunName = "Empty-Handed";

        //If the user's weapon is maxed out, make trade unavailable
        if(gunTier == 5) //max tier
        {
            gunButton.setEnabled(false); //disables clicking the object
            gunButton.setText("Max Weapon Tier");
        }
        //Otherwise fetch the information for the next available weapon
        else{
            for(int i = 1; i <6; i++){
                if(i == gunTier + 1){
                    gunPrice = new Gun(i).getCost(); //captures the price of the weapon
                    gunName = new Gun(i).getGunName();
                }
            }
            gunButton.setEnabled(false); //disables clicking the object
        }
        //If user cannot afford to purchase a new weapon, don't allow them to
        if(currency < gunPrice){
            gunButton.setEnabled(false);
            affordLabel.setText("You cannot afford the " + gunName);
            needLabel.setText("You need " + (user.getCoins() - gunPrice) + "more coins!");
        }
        //If can afford it, highlight border and change textto prompt purchasing
        else{
            gunButton.setHighlightColor(0xFFFF00);
            gunButton.setText("Purchase " + gunName + "for " + gunPrice);
        }

        //Subtract money, disable box, change color, and change button text on purchase
        gunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            currency = currency - gunPrice;
            user.setCoins(currency);
            gunButton.setEnabled(false);
            gunButton.setBackgroundColor(696969);
            gunButton.setHighlightColor(000000);
            coinsLabel.setText(currency + " Coins Remaining");
            gunButton.setText("Thanks for your purchase!");
            }
        });

        //Exit the shop scene when button pressed
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //TODO EXIT SHOP
                }
            }

        );

    }//end onCreate
}//end class
