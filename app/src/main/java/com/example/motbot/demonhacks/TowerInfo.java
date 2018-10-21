package com.example.motbot.demonhacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TowerInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tower_info);

        //Create all our labels and bars
        TextView healthLabel = (TextView)findViewById(R.id.healthLabel);
        TextView timeSurvivedProgress = (TextView)findViewById(R.id.timeSurvivedProgress);
        ProgressBar towerHealthBar = (ProgressBar)findViewById(R.id.towerHealthBar);

        //Setup progressbar values
        towerHealthBar.setMax(userTower.getMax_health());
        towerHealthBar.setProgress(userTower.getHealth());

        //Setup label texts
        healthLabel.setText("Current Health : " + userTower.getHealth() + "/ " + userTower.getMax_health());
        timeSurvivedProgress.setText(userTower.getTimeSurvived()/(60*60) + " Days");
    }
}
