package com.hellapunk.hellapunk.feature;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowsFragment extends Fragment {
    RecyclerView recyclerView;
    ShowAdapter adapter;

    Spinner spinnerCities;
    ArrayAdapter<CharSequence> cAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_shows, container, false);

        spinnerCities = v.findViewById(R.id.spinnerDrop);
        cAdapter = ArrayAdapter.createFromResource(getContext(), R.array.Cities, R.layout.spinner_layout);
        cAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCities.setAdapter(cAdapter);

        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int adjustValue = (int)parent.getItemIdAtPosition(position) + 1;
                grabData(adjustValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        grabData(1);

        return v;

    }

    public void grabData(int $i) {

        String url = "http://dev.jtimko.com/apps/punkshows.php?cId=" + $i;
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<ShowListings> showListings;
                // Recyclerview for listing shows
                showListings = new ArrayList<>();
                recyclerView = getActivity().findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                adapter = new ShowAdapter(getActivity(), showListings);
                recyclerView.setAdapter(adapter);

                try {
                    JSONArray showResp = new JSONArray(response);
                    for (int i = 0; i < showResp.length(); i++) {
                        JSONObject showObj = showResp.getJSONObject(i);

                        // Parsing RFC 3999 date format
                        String[] split = showObj.getString("show_date").split("T");
                        String[] date = split[0].split("-"); // For Date

                        String[] time = split[1].split(":"); // For Time
                        if (Integer.parseInt(time[0]) >= 13) {
                            int temp = Integer.parseInt(time[0]) - 12;
                            time[0] = String.valueOf(temp);
                        }

                        ImageView pnk = getActivity().findViewById(R.id.punklogo);
                        showListings.add(
                                new ShowListings(
                                        showObj.getString("show_summary") + ", " +
                                                time[0] + "pm",
                                        showObj.getString("venue_name"),
                                        date[1] + "/" + date[2] + "/" + date[0],
                                         showObj.getString("shows_img")
                                ));
                    }

                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    //Log.i(TAG, "Error: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //null
            }
        });
        Volley.newRequestQueue(getContext()).add(sr);

    }
}
