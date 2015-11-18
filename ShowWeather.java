package com.example.administrator.weatherapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;

public class ShowWeather extends AppCompatActivity {


    private TextView cityText;
    private TextView condDescr;
    private TextView temp;
    private TextView press;
    private TextView windSpeed;
    private TextView windDeg;
    private TextView hum;
    private ImageView imgView;
    private Bundle extras ;
    private String city;
    private double latitude;
    private double longitude;
  private  RelativeLayout base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Current Weather");
        extras = getIntent().getExtras();
        if(extras !=null) {
            city = extras.getString("City");
            /*latitude=extras.getDouble("Lat");
            longitude=extras.getDouble("Lon");*/

            JSONWeatherTask task = new JSONWeatherTask(this);

            task.execute(city);
        }

    base=(RelativeLayout)findViewById(R.id.base);
        cityText = (TextView) findViewById(R.id.cityText);
        condDescr = (TextView) findViewById(R.id.condDescr);
        temp = (TextView) findViewById(R.id.temp);
        hum = (TextView) findViewById(R.id.hum);
        press = (TextView) findViewById(R.id.press);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        windDeg = (TextView) findViewById(R.id.windDeg);
        imgView = (ImageView) findViewById(R.id.condIcon);

        Log.w("Extraaaaa", city);

        Log.w("GLobal",Global_data_methods.CITY);
        Log.w("Extraaaaa",city);
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.action_logout){
            Global_data_methods.displayToast(this,"Successfully Logged Out");
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {
        private ProgressDialog pdia;
        public JSONWeatherTask(Context context){
            pdia = ProgressDialog.show(context,"Wait","Pre Connect");
        }

        @Override
        protected Weather doInBackground(String... params) {
            pdia.setMessage("Connecting....");
            Weather weather = new Weather();
            String data = ( (new WeatherHttpClient(ShowWeather.this)).getWeatherData(params[0]));
            //Log.w("Data",data);
            try {
                weather = JSONWeatherParser.getWeather(data);

                // Let's retrieve the icon



            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

        }


        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            Log.w("RESULT", weather.toString());

/*

            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                imgView.setImageBitmap(img);
            }
            */

            cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
            condDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
            temp.setText("" + Math.round((weather.temperature.getTemp() - 257.15)) + (char) 0x00B0+"C");
            hum.setText("" + weather.currentCondition.getHumidity() + "%");
            press.setText("" + weather.currentCondition.getPressure() + " hPa");
            windSpeed.setText("" + weather.wind.getSpeed() + " mps");

            if(!city.equals(Global_data_methods.CITY)){
                windDeg.setText("" + weather.wind.getDeg() + (char) 0x00B0);

            }
            else{windDeg.setText("not available");}

            base.setVisibility(RelativeLayout.VISIBLE);

            pdia.dismiss();
        }







    }


}
