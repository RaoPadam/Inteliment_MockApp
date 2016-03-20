package com.padam.mockapp.navdrawerfragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.padam.mockapp.R;
import com.padam.mockapp.activity.MainActivity;
import com.padam.mockapp.navigationApp.AppController;
import com.padam.mockapp.navigationApp.Beans;
import com.padam.mockapp.navigationApp.ConnectionDetector;
import com.padam.mockapp.navigationApp.DatabaseHandler;


/**
 * Created by Ravi on 29/07/15.
 */

public class TestTwoFragment extends Fragment implements Spinner.OnItemSelectedListener {

    private Spinner spinner;
    private List<Beans> arraylist_location_names = new ArrayList<Beans>();
    private JSONArray result;
    int loc;

    private TextView txt_time_car, txt_time_train;
    private Button btn_navigate;

    private ProgressDialog pDialog;

    private String jsonResponse;


    private static String TAG = MainActivity.class.getSimpleName();
    private String urlJsonArry = "http://express-it.optusnet.com.au/sample.json";


    DatabaseHandler db;

    Boolean isInternetPresent = false;

    // Connection detector class
    ConnectionDetector cd;

    public TestTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        {

            View rootView = inflater.inflate(R.layout.fragment_navigationapp, container, false);


            spinner = (Spinner) rootView.findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(this);

            txt_time_car = (TextView) rootView.findViewById(R.id.txt_time_car);
            txt_time_train = (TextView) rootView.findViewById(R.id.txt_time_train);

            btn_navigate = (Button) rootView.findViewById(R.id.btn_navigate);

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");


            pDialog.setCancelable(false);


            cd = new ConnectionDetector(getActivity().getApplicationContext());

            CheckNetwork();


            db = new DatabaseHandler(getActivity());


            DBcheck();

            btn_navigateClickListener();

            ColorDrawable cd = new ColorDrawable(getActivity().getResources().getColor(
                    R.color.windowBackground));


            getActivity().getWindow().setBackgroundDrawable(cd);

            return rootView;
        }
    }


    private void btn_navigateClickListener() {

        btn_navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (db.getAllData().size() != 0) {

                    Beans b = db.getData(loc);


                    String lat = b.getLocation_latitude();
                    String longit = b.getLocation_longitude();


                    Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + longit);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        getActivity().startActivity(mapIntent);
                    }

                } else {


                    Toast.makeText(getActivity(), "Can't connect right now.", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    private void getData() {


        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());


                        try {

                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";

                            String name = "";
                            String mode_car = "";
                            String mode_train = "";
                            String location_longitude = "";
                            String location_latitude = "";

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject abc = (JSONObject) response.get(i);

                                if (abc.has("name")) {


                                    name = abc.getString("name");

                                } else {

                                    name = "No name available";
                                }

                                JSONObject mode = abc
                                        .getJSONObject("fromcentral");

                                if (mode.has("car")) {


                                    mode_car = mode.getString("car");

                                } else {

                                    mode_car = "No car available";

                                }

                                if (mode.has("train")) {

                                    mode_train = mode.getString("train");

                                } else {

                                    mode_train = ("Train not available");

                                }


                                JSONObject location = abc

                                        .getJSONObject("location");

                                if (location.has("longitude")) {

                                    location_longitude = location.getString("longitude");

                                } else {

                                    location_longitude = "151.209900";

                                }

                                if (location.has("latitude")) {

                                    location_latitude = location.getString("latitude");

                                } else {

                                    location_latitude = "-33.865143";

                                }

                                db.addData(new Beans(name, mode_car, mode_train, location_longitude, location_latitude));


                            }


                        } catch (JSONException e) {

                            e.printStackTrace();

                        }

                        hidepDialog();


                        List<Beans> data = db
                                .getAllData();

                        ArrayList<String> name = new ArrayList<String>();

                        for (Beans bean_name : data) {

                            name.add(bean_name.getName());

                        }


                        spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, name));


                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    private void DBcheck() {

        db.getReadableDatabase();
        db.getWritableDatabase();


        if (db.getAllData().size() == 0) {

            CheckNetwork();
            getData();


        } else {


            db.getReadableDatabase();
            db.getWritableDatabase();


            List<Beans> data = db
                    .getAllData();

            ArrayList<String> name = new ArrayList<String>();

            for (Beans bean_name : data) {

                name.add(bean_name.getName());

            }


            spinner.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, name));


        }


    }


    private void CheckNetwork() {

        isInternetPresent = cd.isConnectingToInternet();

        // check for Internet status
        if (isInternetPresent) {



        } else {


            Toast.makeText(getActivity(), "Internet not available !", Toast.LENGTH_SHORT).show();
        }


    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Beans b = db.getData(position + 1);


        loc = position + 1;

        txt_time_car.setText(b.getMode_car());
        txt_time_train.setText(b.getMode_train());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    setRetainInstance(true);

    }
}
