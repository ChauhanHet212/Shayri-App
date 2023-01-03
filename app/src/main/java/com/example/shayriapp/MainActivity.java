package com.example.shayriapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shayriapp.Adapters.CategorysAdapter;

public class MainActivity extends AppCompatActivity {

    int[] categorys_img = {R.drawable.bestwishesh, R.drawable.friendship, R.drawable.funny, R.drawable.god,
            R.drawable.motivational, R.drawable.life, R.drawable.love, R.drawable.memories,
            R.drawable.others, R.drawable.politics, R.drawable.love2, R.drawable.sad,
            R.drawable.alcohol, R.drawable.unfaithful, R.drawable.birthday, R.drawable.holi};
    String[] categorys_name = {"शुभकामनाएँ शायरी", "दोस्ती शायरी", "मजेदार शायरी", "भगवान शायरी",
            "प्रेरणा स्त्रोत शायरी", "जीवन शायरी", "मोहब्बत शायरी", "यादें शायरी",
            "अन्य शायरी", "राजनीति शायरी", "प्रेम शायरी", "दर्द शायरी",
            "शराब शायरी", "बेवफा शायरी", "जन्मदिन शायरी", "होली शायरी"};

    ListView category_listView;
    CategorysAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        category_listView = findViewById(R.id.category_listView);
        adapter = new CategorysAdapter(this, categorys_img, categorys_name);
        category_listView.setAdapter(adapter);

        category_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ShayrisActivity.class);
                intent.putExtra("position", i);
                intent.putExtra("category_img",categorys_img[i]);
                intent.putExtra("title", categorys_name[i]);
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
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

        return false;
    }
}