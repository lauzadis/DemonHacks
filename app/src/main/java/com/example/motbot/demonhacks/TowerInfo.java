package com.example.motbot.demonhacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.here.android.mpa.common.GeoCoordinate;

public class TowerInfo extends AppCompatActivity {
    int cycleCounter = 1; //will be used to demonstrate proof of concept that weapons can be upgraded

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tower_info);

       // final Tower userTower = (Tower) getIntent().getExtras().get("tower");
         final Tower userTower = new Tower(new GeoCoordinate(41.878306,-87.625926));
        userTower.setCoins(200); // demo 200 coins

        //Create all our labels and bars
        TextView healthLabel = (TextView)findViewById(R.id.healthlabel);
        TextView timeSurvivedProgress = (TextView)findViewById(R.id.timeSurvivedProgress);
        ProgressBar towerHealthBar = (ProgressBar)findViewById(R.id.towerHealthBar);
        final ImageView gunImage = (ImageView)findViewById((R.id.gunImage));

        //Creates Action Buttons to create actions for repair and upgrade
        Button upgrade = (Button)findViewById(R.id.upgrade);
        upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userTower.upgrade();
            }
        });
        Button repair = (Button)findViewById(R.id.repair);
        repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userTower.repair();
            }
        });
        Button devGunCycle = (Button)findViewById(R.id.devGunChanger);


        //Setup progressbar values
        towerHealthBar.setMax(userTower.getMax_health());
        towerHealthBar.setProgress(userTower.getHealth());

        //Setup label texts
        healthLabel.setText("Current Health : " + userTower.getHealth() + "/ " + userTower.getMax_health());
        timeSurvivedProgress.setText(userTower.getTimeSurvived()/(60*60) + " Days");

            devGunCycle.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                    switch(cycleCounter){
                        case (1):
                            gunImage.setImageResource(R.drawable.gun1);
                            cycleCounter++;
                            break;
                        case (2):
                            gunImage.setImageResource(R.drawable.gun2);
                            cycleCounter++;
                            break;
                        case (3):
                            gunImage.setImageResource(R.drawable.gun3);
                            cycleCounter++;
                            break;
                        case (4):
                            gunImage.setImageResource(R.drawable.gun4);
                            cycleCounter++;
                            break;
                        case (5):
                            gunImage.setImageResource(R.drawable.gun5);
                            cycleCounter++;
                            break;
                        case(6):
                            cycleCounter = 1;

                    }
                }
    }
        );}}
