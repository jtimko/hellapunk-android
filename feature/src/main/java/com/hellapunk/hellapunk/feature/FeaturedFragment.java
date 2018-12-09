package com.hellapunk.hellapunk.feature;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONObject;


public class FeaturedFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_featured, container, false);

        String url = "http://dev.jtimko.com/apps/featured.php";

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    TextView bandName = getActivity().findViewById(R.id.bandTitle);
                    TextView bandLoc = getActivity().findViewById(R.id.bandLocation);
                    ImageView bandImg = getActivity().findViewById(R.id.bandImg);
                    TextView bandSum = getActivity().findViewById(R.id.bandDesc);

                    JSONArray featArray = new JSONArray(response);
                    JSONObject featObj = featArray.getJSONObject(0);



                    bandName.setText(featObj.getString("feat_artist"));
                    bandLoc.setText(featObj.getString("feat_location"));
                    Glide.with(getContext())
                            .load(featObj.getString("feat_img"))
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .into(bandImg);
                    bandSum.setText(featObj.getString("feat_summary"));

                } catch (Exception e) {

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
