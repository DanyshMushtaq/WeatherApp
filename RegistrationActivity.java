package com.example.administrator.weatherapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
public class RegistrationActivity extends AppCompatActivity {


    private EditText userName;
    private EditText password;
   private EditText city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_registration);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("OpenWeatherMap");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        userName = (EditText)findViewById(R.id.uname);
        password = (EditText)findViewById(R.id.pwd);

    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    public void onSave(View view){
        if (!userName.getText().toString().equals(Global_data_methods.DEF_VALUE) || !password.getText().toString().equals(Global_data_methods.DEF_VALUE) ||
        !city.getText().toString().equals(Global_data_methods.DEF_VALUE)){

            Global_data_methods.setStringValue(this, Global_data_methods.USER_NAME, userName.getText().toString());
            Global_data_methods.setStringValue(this, Global_data_methods.PASSWORD, password.getText().toString());
             Intent intent = new Intent();
            Global_data_methods.displayToast(this, "Registration Successful");
            finish();
        }else{
            Global_data_methods.displayToast(this,"Empty Fields not allowed");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
