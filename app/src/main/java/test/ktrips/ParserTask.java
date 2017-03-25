package test.ktrips;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;



// This is used to parse the JsonDaTa
// Please see PlaceTask, onPostExecute() for implementation.
// This is parent to Place_JSON

public class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {


    JSONObject jObject;

    @Override
    protected List<HashMap<String, String>> doInBackground(String... jsonData) {


        List<HashMap<String, String>> places = null;
        Place_JSON placeJson = new Place_JSON(); // From PLace+JSON

        try {
            jObject = new JSONObject(jsonData[0]);
            places = placeJson.parse(jObject); // From Place_JSON parse function

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
        return places; // sends to PlacesTask


    }


    // Takes the Hasmap Created in Place_JSON and

    @Override
    protected void onPostExecute(List<HashMap<String, String>> list) {


        // Loops/ Searches through list
        for (int i = 0; i < list.size(); i++) {


            // Getting a place from the places list
            HashMap<String, String> hmPlace = list.get(i);


            // Getting latitude of the place
            double lat = Double.parseDouble(hmPlace.get("lat"));

            // Getting longitude of the place
            double lng = Double.parseDouble(hmPlace.get("lng"));

            // Getting name
            String name = hmPlace.get("place_name");

            Log.d("Map", "place: " + name);

            // Getting vicinity
            String vicinity = hmPlace.get("vicinity");


        }

    }
}