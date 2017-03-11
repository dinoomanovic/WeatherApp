package com.odin.weatherapp;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.odin.weatherapp.Models.CityPreferences;
import com.odin.weatherapp.Models.Weather;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import android.support.v4.app.Fragment;

public class MainActivity extends Activity{


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
           //showInputDialog();
            findPlace(getWindow().getDecorView().getRootView().findViewById(R.id.activity_main));
        }
        return false;
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change city");
        LinearLayout tempLayout = new LinearLayout(this);
        tempLayout.setOrientation(LinearLayout.VERTICAL);
        final EditText input = new EditText(this);
        input.setHint("Enter city code");

        input.setInputType(InputType.TYPE_CLASS_TEXT);


        tempLayout.addView(input);
        builder.setView(tempLayout);
        builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText().toString().matches("") || input.getText().toString().matches(" ")) {
                    Toast toast = Toast.makeText(MainActivity.this, "City is empty", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else
                    changeCity(input.getText().toString());
            }
        });
        builder.show();
    }

    public void changeCity(String city) {

        CityPreferences cityPreferences = new CityPreferences(this);
        cityPreferences.setCity(city);

        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);//Start the same Activity
        finish();
    }

    /*
        public void getPlaceAutoCompleteUrl(String input) {
            StringBuilder urlString = new StringBuilder();
            urlString.append("https://maps.googleapis.com/maps/api/place/autocomplete/json");
            urlString.append("?input=");
            try {
                urlString.append(URLEncoder.encode(input, "utf8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            urlString.append("&location=");
            urlString.append("44.5107355" + "," + "18.622587"); // append lat long of current location to show nearby results.
            urlString.append("&radius=500&language=en");
            urlString.append("&key=" + "AIzaSyBTthRBq6k8TP_P0ikUDv1_OKiUH6lnPQM");
            changeCity(urlString.toString());
        }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherFragmentRetro weatherFragmentRetro = new WeatherFragmentRetro();
        DetailFragmentRetro detailFragmentRetro = new DetailFragmentRetro();

/*
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
*/


        if (savedInstanceState == null) {

            FragmentTransaction ft = getFragmentManager().beginTransaction();

            ft.add(R.id.fragment1, weatherFragmentRetro);
            //ft.add(R.id.fragment2,detailFragment);
            //LayoutInflater.from(this).inflate(R.layout.weather_layout2,null);
            ft.add(R.id.fragment2, detailFragmentRetro);
            ft.commit();


        }
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Intent refresh = new Intent(MainActivity.this, MainActivity.class);
             //   refresh.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                refresh.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                refresh.putExtra("flag","add");
                startActivity(refresh);
                finish();
                overridePendingTransition(0,0);
            }
        });

        /*
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent refresh = getIntent();
                startActivity(refresh);
                finish();
            }
        });

        */

/*
        findViewById(R.id.fragment1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               showAlert();

            }
        });

     */
    }


    /*
private void showAlert(){
    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
    alert.setTitle("City");
    alert.setMessage("Enter city");
    final EditText input = new EditText(MainActivity.this);
    alert.setView(input);
    alert.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {

            cityChange(input.getText().toString());
        }
    });
    alert.show();

}
    public void cityChange(String city){
       //WeatherFragment weatherFragment = (WeatherFragment) getFragmentManager().findFragmentById(R.id.fragment1);
            CityPreferences cityPreferences = new CityPreferences(this);
            cityPreferences.setCity(city);
    }


    */


    public void findPlace(View view) {

        try {


                    Intent intent =
                            new PlaceAutocomplete
                                    .IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(MainActivity.this);
                    startActivityForResult(intent, 1);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("flag","add");
            //Thread.sleep(1000);

        } catch (GooglePlayServicesRepairableException e) {
            Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG);
        } catch (GooglePlayServicesNotAvailableException e) {
            Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG);

            // TODO: Handle the error.
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
/*
                ((TextView) findViewById(R.id.searched_address))
                        .setText(place.getName() + ",\n" +
                                place.getAddress() + "\n" + place.getPhoneNumber());*/
            changeCity(place.getName().toString());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.

                Toast.makeText(MainActivity.this,"User canceled operation",Toast.LENGTH_LONG);

            }
        }

    }
}

