package test.ktrips;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;


public class TravelActivity extends FragmentActivity implements OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

   // protected Button button;
    private GoogleApiClient mGoogleApiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
     // button = (Button) findViewById(R.id.PlacePicker);


        // Creates
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API) // or .addAPI(LocationServices.API)
                .enableAutoManage(this, this)
                .build();


        //Setting Bounds around the area ( Camera angle)
        LatLng southwest = new LatLng(42.80749, -73.14697);
        LatLng northeast = new LatLng(44.98423, -71.58691);
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);

        // Main Function, Commented out due to error
       // ChangeLocation( GetCurrentLocation ());

        // Launches Place Picker
        int PLACE_PICKER_REQUEST = 1;
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder()
                .setLatLngBounds(bounds);

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }


        // Gets Current Position
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(mGoogleApiClient, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            public static final String TAG = "Place";


            @Override
            public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                            placeLikelihood.getPlace().getName(),
                            placeLikelihood.getLikelihood()));
                }
                likelyPlaces.release();
            }
        });
    }


    public StringBuilder sbMethod(Location currentLocation) {

        //current location
        double mLatitude = currentLocation.getLatitude();
        double mLongitude = currentLocation.getLongitude();


        // Add what you want to query the API
        // See Google API PLACES Documentation for different types
        // Creates something like this : https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+Sydney&key=AIzaSyC6pSo_aG3RA4nMvGzJ1iQ0PlMj4p1Y7BI

        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location=" + mLatitude + "," + mLongitude);
        sb.append("&radius=5000"); // creates the radius of 5KM
        sb.append("&types=" + "restaurant");
        sb.append("&sensor=true");

        sb.append("&key=AIzaSyC6pSo_aG3RA4nMvGzJ1iQ0PlMj4p1Y7BI");

        Log.d("Map", "<><>api: " + sb.toString());

        return sb; // Returns the Queriest
    }


    // For future Sprints, Not necessary, but can implement Connection methods
    // IE: Forced

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    // Gets Current Location
    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    
    // This is the Main Function
    //
    public void ChangeLocation(Location location) {

        //query places with current location
        StringBuilder sbValue = new StringBuilder(sbMethod(location));
        PlacesTask placesTask = new PlacesTask();
        placesTask.execute(sbValue.toString()); // Queries Location via HTML Request

        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (LocationListener) this);


        }


    }



    // Gets Current Location of the Phone
    // Adds a permission Beforehand, Just a formality
    // See ONCreate
    public Location GetCurrentLocation () {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }


        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); // Gets the Location of the system
        Criteria criteria = new Criteria(); // Sets up an empty Criteria,
        String bestProvider = locationManager.getBestProvider(criteria, true); // By Default, If GPS is enabled it will always return GPS as the best provider since you specified an empty criteria.
        Location location = locationManager.getLastKnownLocation(bestProvider); // Returns Last known location

        return location;

    }
    
    
}









