package com.example.motbot.demonhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView)findViewById(R.id.tv);
                EditText email = (EditText)findViewById(R.id.email);
                EditText password = (EditText)findViewById(R.id.password);
                //Snackbar.make(view, "Login success", 3000);

                //Check the account - server check
                String e = email.toString();
                String p = password.toString();
                //if e is new
                User u = new User(e, p );
                LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                Location location = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                GeoCoordinate userLocation = new GeoCoordinate(location.getLatitude(), location.getLongitude(), 0.0);
                u.setT(new Tower(userLocation));
                //if e is old, check p and then run bottom
                //import User u
                //Has to run no matter what
                Tower userTower = u.getT();
                Intent game = new Intent(getApplicationContext(), Game.class);
                startActivity(game);
                /*
                if(email.getText().toString().equals(emails) && password.getText().toString().equals(passwords))
                {
                    tv.setText("Confirmed");
                    Intent game = new Intent(getApplicationContext(), Game.class);
                    startActivity(game);
                }

                else
                    tv.setText("False");

            }
            */
        });

        vendortest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchVendor = new Intent(getApplicationContext(),vendor.class);
                startActivity(switchVendor);
            }
        });

    }
}
