package com.example.administrator.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.location.*;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements LocationListener {
    private EditText password;
    private EditText userName;
    private  String city;
    private  double latitude;
    private  double longitude;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("OpenWeatherMap");
        actionBar.setLogo(R.drawable.ic_launcher1);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,this);
    }


    public void onLoginClicked(View view) {
        /*
        */
        if (!userName.getText().toString().equals(Global_data_methods.DEF_VALUE) || !password.getText().toString().equals(Global_data_methods.DEF_VALUE)) {
            if (userName.getText().toString().equals(Global_data_methods.getStringValue(this, Global_data_methods.USER_NAME)) && password.getText().toString().equals(Global_data_methods.getStringValue(this, Global_data_methods.PASSWORD))) {
                Intent intent = new Intent(this, ShowWeather.class);

                intent.putExtra("City",Global_data_methods.CITY);
                startActivity(intent);
                finish();
            } else {
                Global_data_methods.displayToast(this, "Username or Password is Wrong!!!");
            }
        } else {
            Global_data_methods.displayToast(this, "Please enter your credentials");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_register) {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null){
            Global_data_methods.setStringValue(this,Global_data_methods.LAT,Double.toString(location.getLatitude()));
            Global_data_methods.setStringValue(this,Global_data_methods.LON,Double.toString(location.getLongitude()));
            Log.w("Location",location.getLatitude()+","+location.getLongitude());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(this);
    }
}
