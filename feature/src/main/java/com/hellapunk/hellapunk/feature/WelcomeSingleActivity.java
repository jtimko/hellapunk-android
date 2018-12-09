package com.hellapunk.hellapunk.feature;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class WelcomeSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_single);

        Bundle bundle = getIntent().getExtras();

        ImageView pic = findViewById(R.id.welcomeImg);
        TextView title = findViewById(R.id.welcomeTitle);
        TextView msg = findViewById(R.id.welcomeMsg);

        title.setText(bundle.getString("welcomeTitle"));
        msg.setText(bundle.getString("welcomeMsg"));
        String url = bundle.getString("welcomePic");

        Glide.with(this)
                .load(url)
                .into(pic);
    }
}
