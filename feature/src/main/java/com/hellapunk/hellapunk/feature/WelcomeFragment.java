package com.hellapunk.hellapunk.feature;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WelcomeFragment extends Fragment {
    RecyclerView recyclerView;
    WelcomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_welcome, container, false);

        String url = "http://dev.jtimko.com/apps/news.php";
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                final List<WelcomeListings> welcomeListings;
                // Recyclerview for listing shows
                welcomeListings = new ArrayList<>();
                recyclerView = getActivity().findViewById(R.id.recyclerView2);
                recyclerView.setHasFixedSize(true);

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                adapter = new WelcomeAdapter(getActivity(), welcomeListings);
                recyclerView.setAdapter(adapter);

                ImageView img = getActivity().findViewById(R.id.filler);
                Log.i("TAG", response);

                try {
                    JSONArray newsResp = new JSONArray(response);

                    for(int i = 0; i < newsResp.length(); ++i) {
                        JSONObject newsObj = newsResp.getJSONObject(i);
                        welcomeListings.add(
                                new WelcomeListings(
                                        newsObj.getString("news_title"),
                                        newsObj.getString("news_summary"),
                                        newsObj.getString("news_article"),
                                        newsObj.getString("news_img")
                                ));
                    }

                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    Log.i("TAG", "Some error: " + e.toString());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getContext()).add(sr);

        return v;
    }
}