package com.example.motbot.demonhacks;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapCircle;
import com.here.android.mpa.mapping.MapFragment;
import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Tower userTower = new Tower(new GeoCoordinate(41.878306, -87.625926,0.0));
    String emails = "matas";
    String passwords = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent about = new Intent(getApplicationContext(), about.class);
        startActivity(about);

        boolean firstLaunch = true;
        Button login = (Button)findViewById(R.id.login);
        Button vendortest = (Button)findViewById(R.id.vendortest);
        Button towerinfotest = (Button)findViewById(R.id.towerinfotest);
        //myRef.child("users").child("0vzWqJzkIQVGEkpyOG8w").child("username").getValue();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.tv);
                EditText email = (EditText) findViewById(R.id.email);
                EditText password = (EditText) findViewById(R.id.password);

                //Snackbar.make(view, "Login success", 3000);

                //Check the account - server check
                String e = email.toString();
                String p = password.toString();
                //if e is new
                User u = new User(e, p);
                //if e is old, check p and then run bottom
                //import User u
                //Has to run no matter what
                //Tower userTower = u.getT();



                Intent game = new Intent(getApplicationContext(), Game.class);
               // game.putExtra("tower", userTower);
                startActivity(game);
            }});
        vendortest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vendorr = new Intent(getApplicationContext(), vendor.class);
                //vendorr.putExtra("tower",userTower);
                startActivity(vendorr);
            }
        });

        towerinfotest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent towerinfo = new Intent(getApplicationContext(), TowerInfo.class);
                //towerinfo.putExtra("tower",userTower);
                startActivity(towerinfo);
            }
        });
    }
}



