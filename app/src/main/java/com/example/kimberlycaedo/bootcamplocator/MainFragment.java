package com.example.kimberlycaedo.bootcamplocator;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Kimberly Cañedo on 15/08/2017.
 */

public class MainFragment extends SupportMapFragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    private MarkerOptions userMarker;
    private String zip;
    private LocationListFragments locationsListFragments;


    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        //loading the map...
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        locationsListFragments = (LocationListFragments)getActivity().getSupportFragmentManager().findFragmentById(R.id.container_location_list);

        if (locationsListFragments == null) {
            locationsListFragments = LocationListFragments.newInstance();
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_location_list, locationsListFragments)
                    .commit();
        }

        final EditText zipText = (EditText) v.findViewById(R.id.zip_text);
        zipText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Looking for input on the searchbar and checking when enter is pressed...
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    //perform action on key pressed...
                    zip = zipText.getText().toString();
                    Toast.makeText(getContext(), zip, Toast.LENGTH_SHORT).show();
                    //Dismiss the keyboard
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(zipText.getWindowToken(), 0);
                    updateMapForZip(zip);
                    showList();
                    return true;
                }
                return false;
            }
        });
        //Show the locations recycler view/list to the user when the user enters a zip...
        hideList();
        return v;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

    }

    public void setUserMarkers(LatLng latLng) {
        if (userMarker == null) {
            userMarker = new MarkerOptions().position(latLng).title("Current Location");
            mGoogleMap.addMarker(userMarker);
            Log.v("Hey", "Current Location");
        }
        //geocoding to find zip of current users location retrieved from the phone...
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            String postal_code = addresses.get(0).getPostalCode();
            updateMapForZip(postal_code);
        }
        catch (IOException exception){
            //catching IO.
        }

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

        mGoogleMap.setTrafficEnabled( true );

    }


    private void updateMapForZip(String zip_code){

        Toast.makeText(getContext(), zip_code, Toast.LENGTH_SHORT).show();
        ArrayList<Devslopes> locations = DataService.getInstance().getNearBootCampLocations(Integer.parseInt(zip_code));

        for (int x = 0; x < locations.size(); x++){
            Devslopes loc = locations.get(x);
            MarkerOptions marker = new MarkerOptions().position(new LatLng(loc.getLatitude(), loc.getLongitude()));
            marker.title(loc.getLocationTittle());
            marker.snippet(loc.getLocationAddress());
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin));
            mGoogleMap.addMarker(marker);
        }

    }

    //Hide location list fragment function ...
    private void hideList(){
        getActivity().getSupportFragmentManager().beginTransaction().hide(locationsListFragments).commit();
    }

    //Show location list fragment function ...
    private void showList(){
        getActivity().getSupportFragmentManager().beginTransaction().show(locationsListFragments).commit();
    }

}
