package com.odin.weatherapp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.odin.weatherapp.Models.CityPreferences;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;
import static com.google.android.gms.location.places.ui.PlaceAutocomplete.RESULT_ERROR;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
    @ViewById(R.id.swipe_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Click(R.id.searchText)
    public void searchTextClicked() {
        findPlace();
    }

    public void changeCity(String city) {

        CityPreferences cityPreferences = new CityPreferences(this);
        cityPreferences.setCity(city);

        Intent refresh = new Intent(this, MainActivity_.class);
        startActivity(refresh);
        finish();
    }

    @AfterViews
    protected void initialize() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Intent refresh = new Intent(MainActivity.this, MainActivity_.class);
                refresh.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                refresh.putExtra("flag", "add");
                startActivity(refresh);
                finish();
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showMainScreen();
    }

    @UiThread
    protected void showMainScreen() {
        if (isFinishing()) {
            return;
        }
        WeatherFragment weatherFragment = new WeatherFragment();
        DetailFragment detailFragment = new DetailFragment();

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.add(R.id.fragment1, weatherFragment);
        ft.add(R.id.fragment2, detailFragment);
        ft.commitAllowingStateLoss();
    }

    public void findPlace() {
        try {
            Intent intent =
                    new PlaceAutocomplete
                            .IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(MainActivity.this);
            startActivityForResult(intent, 1);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("flag", "add");

        } catch (GooglePlayServicesRepairableException e) {
            Toast.makeText(MainActivity.this, e.toString(), LENGTH_LONG).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            Toast.makeText(MainActivity.this, e.toString(), LENGTH_LONG).show();

            // TODO: Handle the error.
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(this, data);
                Geocoder myLocation = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> myList = myLocation.getFromLocation(Double.parseDouble(String.valueOf(place.getLatLng().latitude)),
                            Double.parseDouble(String.valueOf(place.getLatLng().longitude)), 1);
                    if ((myList == null) || myList.isEmpty()) {
                        return;
                    }
                    changeCity(String.format(getString(R.string.connect_two_strings_comma), place.getName(), myList.get(0).getCountryCode()));

                    Log.e("myList", myList.toString());
                } catch (IOException e) {
                    Log.e("myList IOException", e.getLocalizedMessage());
                }
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
            } else if (resultCode == RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.

                Toast.makeText(MainActivity.this, "User canceled operation", LENGTH_LONG).show();

            }
        }

    }
}

