package com.example.administrator.weatherapp;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpClient {

    public static final String URL = "http://api.openweathermap.org/data/2.5/weather?";

    public static final String BASE_URL = URL+"q=";

    public static final String IMG_URL = "http://openweathermap.org/img/w/";
    public static final String API_KEY="2de143494c0b295cca9337e1e96b00e0";
    private Context context;
    public WeatherHttpClient(Context context){
       this.context = context;
    }

    public String getWeatherData(String location) {
        HttpURLConnection con = null ;
        InputStream is = null;
        StringBuilder stringBuilder = new StringBuilder();
        //Log.w("URL", BASE_URL + location + "APPID=" + API_KEY);
        try {

            if (!location.equals(Global_data_methods.CITY)){
                con = (HttpURLConnection) ( new URL(BASE_URL + location+"&appid="+API_KEY)).openConnection();
            }else{
                con = (HttpURLConnection) ( new URL(URL + "lat="+Global_data_methods.getStringValueLat(context)+"&lon="+Global_data_methods.getStringValueLon(context)+"&appid="+API_KEY)).openConnection();
                Log.w("URL",URL + "lat="+Global_data_methods.getStringValueLat(context)+"&lon="+Global_data_methods.getStringValueLon(context)+"&appid="+API_KEY);
            }

            con.setRequestMethod("GET");
            con.setDoInput(true);
            //con.setDoOutput(true);
            con.connect();

            // Let's read the response
            //StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String line = "";
            while (  (line = br.readLine()) != null ) {
                stringBuilder.append(line).append("\r\n");
            }

            is.close();
            con.disconnect();
           // return stringBuilder.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
        Log.w("DATA",stringBuilder.toString());
        return stringBuilder.toString();

    }

    public byte[] getImage(String code) {
        HttpURLConnection con = null ;
        InputStream is = null;
        try {
            con = (HttpURLConnection) ( new URL(IMG_URL + code)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while ( is.read(buffer) != -1)
                baos.write(buffer);

            return baos.toByteArray();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }


}
