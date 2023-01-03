package com.example.shayriapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shayriapp.Adapters.ShayrisAdapter;

public class ShayrisActivity extends AppCompatActivity {

    ListView shayris_listView;
    ShayrisAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shayris);

        int po = getIntent().getIntExtra("position", 100);
        int image = getIntent().getIntExtra("category_img", 100);
        String actionbar_title = getIntent().getStringExtra("title");

        getSupportActionBar().setTitle(actionbar_title);

        shayris_listView = findViewById(R.id.Shayris_listView);
        adapter = new ShayrisAdapter(this, image, AllShayris.ALL_SHAYRIS, po);
        shayris_listView.setAdapter(adapter);

        shayris_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ShayrisActivity.this, ShowActivity.class);
                intent.putExtra("first_po", po);
                intent.putExtra("second_po", i);
                intent.putExtra("title", actionbar_title);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_settings){
            startActivity(new Intent(ShayrisActivity.this, SettingsActivity.class));
        }

        return false;
    }
}