package fi.ahonen.emilia.hanna.mobilerestaurants;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private JSONArray otto;
    private List<StudentRestaurant> restaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        FetchDataTask task = new FetchDataTask();
        task.execute("http://student.labranet.jamk.fi/~H8404/JSON/studentrestaurants.json");
        mMap = googleMap;
        LatLng jkl = new LatLng(62.242459, 25.747781);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jkl,13));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker arg0) {
               /* Context context = getApplicationContext();
                CharSequence text = "Otto automaatti";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;*/
                Intent menuIntent = new Intent(MapsActivity.this, MenuActivity.class);
                startActivity(menuIntent);
                return true;
            }
        });
    }

    class FetchDataTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            JSONObject json = null;
            try {
                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                json = new JSONObject(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) urlConnection.disconnect();
            }
            return json;
        }

        protected void onPostExecute(JSONObject json) {
            StringBuffer text = new StringBuffer("");
            try {
                otto = json.getJSONArray("studentRestaurants");
                //JsonElement ele = new JsonParser().parse(json);
                for (int i=0;i < otto.length();i++) {
                    JSONObject hs = otto.getJSONObject(i);
                    //text.append(hs.getString("adress")+":"+hs.getString("lat")+"\n");
                    StudentRestaurant restaurant = new StudentRestaurant(hs.getString("adress"),hs.getDouble("lat"),hs.getDouble("lon"));
                    restaurants.add(restaurant);
                }
            } catch (JSONException e) {
                Log.e("JSON", "Error getting data.");
            }finally {
                if (!restaurants.isEmpty()) {
                    for (StudentRestaurant r : restaurants) {
                        LatLng position = new LatLng(r.getLat(), r.getLon());
                        mMap.addMarker(new MarkerOptions().position(position).title(r.getAddress()));
                    }
                }
            }
        }
    }
}
