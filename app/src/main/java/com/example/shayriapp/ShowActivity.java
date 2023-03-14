package com.example.shayriapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shayriapp.Adapters.ColorsAdapter;
import com.example.shayriapp.Adapters.ViewPagerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShowActivity extends AppCompatActivity {

    TextView shayri_count;
    ImageView previous_shayri, next_shayri, share_shayri, random_bgbtn, show_copy, show_choosebgbtn, show_editbtn;
    BottomSheetDialog dialog;
    ViewPager2 pager;
    ViewPagerAdapter adapter;

    int po1, po2;
    String actionbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        shayri_count = findViewById(R.id.shayri_count);
        previous_shayri = findViewById(R.id.previous_shayri);
        next_shayri = findViewById(R.id.next_shayri);
        share_shayri = findViewById(R.id.share_shayri);
        random_bgbtn = findViewById(R.id.random_bgbtn);
        show_copy = findViewById(R.id.show_copy);
        show_choosebgbtn = findViewById(R.id.show_choosebgbtn);
        show_editbtn = findViewById(R.id.show_editbtn);
        pager = findViewById(R.id.pager);

        po1 = getIntent().getIntExtra("first_po", 100);
        po2 = getIntent().getIntExtra("second_po", 100);
        actionbar_title = getIntent().getStringExtra("title");

        getSupportActionBar().setTitle(actionbar_title);

        adapter = new ViewPagerAdapter(ShowActivity.this, po1);
        pager.setAdapter(adapter);
        pager.setCurrentItem(po2);
        shayri_count.setText((po2+1) + "/10");

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                po2 = position;
                shayri_count.setText((po2+1) + "/10");
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        previous_shayri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (po2 != 0){
                    po2--;
                    shayri_count.setText((po2+1) + "/10");
                    pager.setCurrentItem(po2);
                }
            }
        });
        next_shayri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (po2 != 9){
                    po2++;
                    shayri_count.setText((po2+1) + "/10");
                    pager.setCurrentItem(po2);
                }
            }
        });
        share_shayri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ishare = new Intent(Intent.ACTION_SEND);
                ishare.setType("text/plain");
                ishare.putExtra(Intent.EXTRA_TEXT, AllShayris.ST_EMOJIS[po2] + AllShayris.ALL_SHAYRIS[po1][po2] + AllShayris.EN_EMOJIS[po2]);
                startActivity(Intent.createChooser(ishare, "Share Via"));
            }
        });
        random_bgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ran = new Random().nextInt(AllShayris.GRADIENT.length);
                adapter.changeBg(ran);
            }
        });
        show_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData data = ClipData.newPlainText("text", AllShayris.ST_EMOJIS[po2] + AllShayris.ALL_SHAYRIS[po1][po2] + AllShayris.EN_EMOJIS[po2]);
                manager.setPrimaryClip(data);

                Toast.makeText(ShowActivity.this, "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });
        show_choosebgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new BottomSheetDialog(ShowActivity.this);
                dialog.setContentView(R.layout.choose_bg);

                GridView grad_gridView = dialog.findViewById(R.id.grad_gridView);
                ColorsAdapter adapter = new ColorsAdapter(ShowActivity.this, false);
                grad_gridView.setAdapter(adapter);

                grad_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ShowActivity.this.adapter.changeBg(i);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        show_editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowActivity.this, EditActivity.class);
                intent.putExtra("shayri", AllShayris.ALL_SHAYRIS[po1][po2]);
                intent.putExtra("emoji1", AllShayris.ST_EMOJIS[po2]);
                intent.putExtra("emoji2", AllShayris.EN_EMOJIS[po2]);
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
            startActivity(new Intent(ShowActivity.this, SettingsActivity.class));
        }

        return false;
    }
}
