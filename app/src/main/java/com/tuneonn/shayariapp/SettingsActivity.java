package com.tuneonn.shayariapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    TextView settings_emoji, settings_noemoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings_emoji = findViewById(R.id.settings_emoji);
        settings_noemoji = findViewById(R.id.settings_noemoji);

        settings_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings_emoji.setBackgroundColor(getResources().getColor(R.color.pink2));
                settings_noemoji.setBackgroundColor(getResources().getColor(R.color.green2));
            }
        });

        settings_noemoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings_noemoji.setBackgroundColor(getResources().getColor(R.color.pink2));
                settings_emoji.setBackgroundColor(getResources().getColor(R.color.green2));
            }
        });
    }
}