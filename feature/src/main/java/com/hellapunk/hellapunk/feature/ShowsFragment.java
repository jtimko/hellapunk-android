package com.hellapunk.hellapunk.feature;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowsFragment extends Fragment {
    RecyclerView recyclerView;
    ShowAdapter adapter;

    List<ShowListings> showListings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shows, container, false);

        String[] cities = new String[]{"Sacramento", "Berkeley", "San Francisco"};


        showListings = new ArrayList<>();
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ShowAdapter(getActivity(), showListings);
        recyclerView.setAdapter(adapter);

        String url = "http://dev.jtimko.com/apps/punkshows.php";
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Log.i("TAG", response);
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

                        showListings.add(
                                new ShowListings(
                                        showObj.getString("show_summary") + ", " +
                                        time[0] + "pm",
                                        showObj.getString("venue_name"),
                                        date[1] + "/" + date[2] + "/" + date[0],
                                        R.drawable.ic_menu_gallery
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

        return v;

    }
}
