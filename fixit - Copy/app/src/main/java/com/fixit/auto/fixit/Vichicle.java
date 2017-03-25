package com.fixit.auto.fixit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.androidfung.geoip.api.ApiManager;
import com.androidfung.geoip.model.GeoIpResponseModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class Vichicle extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
ViewGroup car,bike,bycycle;
    GoogleApiClient mGoogleApiClient=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vichicle);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar1);
        ViewGroup appbar= (ViewGroup) findViewById(R.id.app_bar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
         car= (ViewGroup) findViewById(R.id.mycar);
        bike= (ViewGroup) findViewById(R.id.mybike);
        getLocation();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Vichicle.this,product.class));
            }
        });
    }
public void getLocation(){
    ApiManager apiManager = new ApiManager(Volley.newRequestQueue(Vichicle.this));
    apiManager.getGeoIpInfo(new Response.Listener<GeoIpResponseModel>() {
        @Override
        public void onResponse(GeoIpResponseModel response) {
            //This is how you get the information.
            //not all attribute are listed
            String country = response.getCountry();
            String city = response.getCity();
            String countryCode =response.getCountryCode();
            double latitude = response.getLatitude();
            double longtidue = response.getLongitude();
            String region = response.getRegion();
            String timezone = response.getTimezone();
            String isp = response.getIsp();
            Toast.makeText(Vichicle.this,"country is "+country,Toast.LENGTH_SHORT).show();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            String errorMessage = error.toString();
        }
    });
}
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (mGoogleApiClient != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if(mLastLocation!=null){
                Toast.makeText(Vichicle.this,mLastLocation.getLatitude()+"       "+mLastLocation.getLongitude(),Toast.LENGTH_SHORT).show();

            }
        }
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
    mGoogleApiClient.disconnect();
    }
}
