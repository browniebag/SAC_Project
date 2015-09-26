package com.example.jeremy.shutupanddrive;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements LocationListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        try {
            // Ensure TandC is accepted before starting the application
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            Boolean tc = sp.getBoolean("appTandC", false);
            if(!tc) {
                Intent i = new Intent(this, TermsAndConditions2.class);
                startActivityForResult(i, 0);

            }
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }catch (SecurityException se){

        }
        this.onLocationChanged(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_CANCELED){
            finish();
            System.exit(0);
        }
        if(resultCode == RESULT_OK){
            SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(getBaseContext());
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean("appTandC", true);
            edit.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity(new Intent(this, appPreferences.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
       TextView speed = (TextView)this.findViewById(R.id.speedometer);

        if( location == null){
            speed.setText("-.-- mph");

        }
        else{

            double currentSpeed = location.getSpeed();
            currentSpeed =(currentSpeed* 2.23693629);

            double roundSpeed= Math.round(currentSpeed*100.00)/100;
            speed.setText(roundSpeed + "mph");

        }

    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

