package com.tuneonn.shayariapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tuneonn.shayariapp.Adapter.CategorysAdapter;

public class MainActivity extends AppCompatActivity {

    int[] images = {R.drawable.bestwishesh, R.drawable.frienship, R.drawable.fun, R.drawable.god,
            R.drawable.motivational, R.drawable.life, R.drawable.love2, R.drawable.memories,
            R.drawable.others, R.drawable.politics, R.drawable.love, R.drawable.sad,
            R.drawable.alcohol, R.drawable.unfaithful, R.drawable.birthday, R.drawable.holi};
    String[] names = {"शुभकामनाए शायरी", "दोस्ती शायरी", "मजेदार शायरी", "भगवान शायरी",
            "प्रेरणा स्त्रोत शायरी", "जीवन शायरी","मोहब्बत शायरी","यादें शायरी",
            "अन्य शायरी", "राजनीती शायरी", "प्रेम शायरी", "दर्द शायरी",
            "शराब शायरी", "बेवफा शायरी", "जन्मदिन शायरी", "होली शायरी"};

    ListView category_ListView;
    CategorysAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        category_ListView = findViewById(R.id.category_ListView);
        adapter = new CategorysAdapter(this, images, names);
        category_ListView.setAdapter(adapter);

        category_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ShayrisActivity.class);
                intent.putExtra("position", i);
                intent.putExtra("images", images[i]);
                intent.putExtra("title",names[i]);
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

            return true;
        }

        return false;
    }
}