package com.tuneonn.shayariapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tuneonn.shayariapp.Adapter.ShayrisAdapter;

public class ShayrisActivity extends AppCompatActivity {

    ListView shayris_listView;
    ShayrisAdapter adapter;
    int po;
    int image;
    ActionBar actionBar;
    String actionbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shayris);

        po = getIntent().getIntExtra("position", 100);
        image = getIntent().getIntExtra("images", 100);
        actionbar_title = getIntent().getStringExtra("title");
        actionBar = getSupportActionBar();
        actionBar.setTitle(actionbar_title);

        shayris_listView = findViewById(R.id.shayris_listView);
        adapter = new ShayrisAdapter(this, image, AllShayris.ALL_SHAYRIS, po);
        shayris_listView.setAdapter(adapter);

        shayris_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ShayrisActivity.this, ShowActivity.class);
                intent.putExtra("position1", po);
                intent.putExtra("position2", i);
                intent.putExtra("title",actionbar_title);
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

            return true;
        }

        return false;
    }
}