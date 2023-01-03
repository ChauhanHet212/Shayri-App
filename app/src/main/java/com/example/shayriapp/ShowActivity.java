package com.example.shayriapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShowActivity extends AppCompatActivity {

    TextView shayri_count, show_shayritxtv, bg1, bg2, bg3, bg4, bg5, bg6, bg7, bg8, bg9, bg10;
    ImageView previous_shayri, next_shayri, share_shayri, random_bgbtn, show_copy, show_choosebgbtn, show_editbtn;
    BottomSheetDialog dialog;

    int po1, po2;
    String actionbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        shayri_count = findViewById(R.id.shayri_count);
        show_shayritxtv = findViewById(R.id.show_shayritxtv);
        previous_shayri = findViewById(R.id.previous_shayri);
        next_shayri = findViewById(R.id.next_shayri);
        share_shayri = findViewById(R.id.share_shayri);
        random_bgbtn = findViewById(R.id.random_bgbtn);
        show_copy = findViewById(R.id.show_copy);
        show_choosebgbtn = findViewById(R.id.show_choosebgbtn);
        show_editbtn = findViewById(R.id.show_editbtn);

        po1 = getIntent().getIntExtra("first_po", 100);
        po2 = getIntent().getIntExtra("second_po", 100);
        actionbar_title = getIntent().getStringExtra("title");

        getSupportActionBar().setTitle(actionbar_title);

        shayri_count.setText((po2+1) + "/10");
        try {
            show_shayritxtv.setText(AllShayris.ST_EMOJIS[po2] + AllShayris.ALL_SHAYRIS[po1][po2] + AllShayris.EN_EMOJIS[po2]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        previous_shayri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (po2 != 0){
                    po2--;
                    shayri_count.setText((po2+1) + "/10");
                    try {
                        show_shayritxtv.setText(AllShayris.ST_EMOJIS[po2] + AllShayris.ALL_SHAYRIS[po1][po2] + AllShayris.EN_EMOJIS[po2]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        next_shayri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (po2 != 9){
                    po2++;
                    shayri_count.setText((po2+1) + "/10");
                    try {
                        show_shayritxtv.setText(AllShayris.ST_EMOJIS[po2] + AllShayris.ALL_SHAYRIS[po1][po2] + AllShayris.EN_EMOJIS[po2]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        share_shayri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ishare = new Intent(Intent.ACTION_SEND);
                ishare.setType("text/plain");
                ishare.putExtra(Intent.EXTRA_TEXT, show_shayritxtv.getText().toString());
                startActivity(Intent.createChooser(ishare, "Share Via"));
            }
        });
        random_bgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ran = new Random().nextInt(AllShayris.GRADIENT.length);
                show_shayritxtv.setBackground(getDrawable(AllShayris.GRADIENT[ran]));
            }
        });
        show_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData data = ClipData.newPlainText("text", show_shayritxtv.getText().toString());
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
                        show_shayritxtv.setBackground(getResources().getDrawable(AllShayris.GRADIENT[i]));
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