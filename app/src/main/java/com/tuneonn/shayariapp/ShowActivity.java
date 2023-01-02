package com.tuneonn.shayariapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {

    TextView shayri_count, show_shayri, bg1, bg2, bg3, bg4, bg5, bg6, bg7, bg8, bg9, bg10;
    ImageView show_previous, show_next, show_share, random_bg, show_copy, show_choosebg, edit_modebtn;
    BottomSheetDialog dialog;

    int po1, po2;
    String actionbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        shayri_count = findViewById(R.id.shayri_count);
        show_shayri = findViewById(R.id.show_shayri);
        show_previous = findViewById(R.id.show_previous);
        show_next = findViewById(R.id.show_next);
        show_share = findViewById(R.id.show_share);
        random_bg = findViewById(R.id.random_bg);
        show_copy = findViewById(R.id.show_copy);
        show_choosebg = findViewById(R.id.show_choosebg);
        edit_modebtn = findViewById(R.id.edit_modebtn);

        po1 = getIntent().getIntExtra("position1", 100);
        po2 = getIntent().getIntExtra("position2", 100);
        actionbar_title = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(actionbar_title);

        try {
            show_shayri.setText(AllShayris.ST_EMOJIS[po2] + AllShayris.ALL_SHAYRIS[po1][po2] + AllShayris.EN_EMOJIS[po2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        shayri_count.setText((po2+1) + "/10");

        show_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (po2 != 0){
                    po2--;
                    try {
                        show_shayri.setText(AllShayris.ST_EMOJIS[po2] + AllShayris.ALL_SHAYRIS[po1][po2] + AllShayris.EN_EMOJIS[po2]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    shayri_count.setText((po2+1) + "/10");
                }
            }
        });
        show_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (po2 != 9){
                    po2++;
                    try {
                        show_shayri.setText(AllShayris.ST_EMOJIS[po2] + AllShayris.ALL_SHAYRIS[po1][po2] + AllShayris.EN_EMOJIS[po2]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    shayri_count.setText((po2+1) + "/10");
                }
            }
        });
        show_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ishare = new Intent(Intent.ACTION_SEND);
                ishare.setType("text/plain");
                ishare.putExtra(Intent.EXTRA_TEXT, show_shayri.getText().toString());
                startActivity(Intent.createChooser(ishare, "Share Via"));
            }
        });
        random_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> randomlist = new ArrayList<>();

                randomlist.add(R.drawable.grad_bg1);
                randomlist.add(R.drawable.grad_bg2);
                randomlist.add(R.drawable.grad_bg3);
                randomlist.add(R.drawable.grad_bg4);
                randomlist.add(R.drawable.grad_bg5);
                randomlist.add(R.drawable.grad_bg6);
                randomlist.add(R.drawable.grad_bg7);
                randomlist.add(R.drawable.grad_bg8);
                randomlist.add(R.drawable.grad_bg9);
                randomlist.add(R.drawable.grad_bg10);

                int ran = new Random().nextInt(randomlist.size());
                show_shayri.setBackground(getDrawable(randomlist.get(ran)));
            }
        });
        show_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData data = ClipData.newPlainText("text", AllShayris.ALL_SHAYRIS[po1][po2]);
                clipboardManager.setPrimaryClip(data);
                Toast.makeText(ShowActivity.this, "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });
        show_choosebg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new BottomSheetDialog(ShowActivity.this);
                dialog.setContentView(R.layout.choosebg_dialog);

//                bg1 = dialog.findViewById(R.id.bg1);
//                bg2 = dialog.findViewById(R.id.bg2);
//                bg3 = dialog.findViewById(R.id.bg3);
//                bg4 = dialog.findViewById(R.id.bg4);
//                bg5 = dialog.findViewById(R.id.bg5);
//                bg6 = dialog.findViewById(R.id.bg6);
//                bg7 = dialog.findViewById(R.id.bg7);
//                bg8 = dialog.findViewById(R.id.bg8);
//                bg9 = dialog.findViewById(R.id.bg9);
//                bg10 = dialog.findViewById(R.id.bg10);
//
//                bg1.setOnClickListener(ShowActivity.this);
//                bg2.setOnClickListener(ShowActivity.this);
//                bg3.setOnClickListener(ShowActivity.this);
//                bg4.setOnClickListener(ShowActivity.this);
//                bg5.setOnClickListener(ShowActivity.this);
//                bg6.setOnClickListener(ShowActivity.this);
//                bg7.setOnClickListener(ShowActivity.this);
//                bg8.setOnClickListener(ShowActivity.this);
//                bg9.setOnClickListener(ShowActivity.this);
//                bg10.setOnClickListener(ShowActivity.this);

                dialog.show();

            }
        });
        edit_modebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowActivity.this, EditActivity.class);
                intent.putExtra("action_title", actionbar_title);
                intent.putExtra("po1", po1);
                intent.putExtra("po2", po2);
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

            return true;
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == bg1.getId()){
            show_shayri.setBackground(getDrawable(R.drawable.grad_bg1));
            dialog.dismiss();
        } else if (view.getId() == bg2.getId()){
            show_shayri.setBackground(getDrawable(R.drawable.grad_bg2));
            dialog.dismiss();
        } else if (view.getId() == bg3.getId()){
            show_shayri.setBackground(getDrawable(R.drawable.grad_bg3));
            dialog.dismiss();
        } else if (view.getId() == bg4.getId()){
            show_shayri.setBackground(getDrawable(R.drawable.grad_bg4));
            dialog.dismiss();
        } else if (view.getId() == bg5.getId()){
            show_shayri.setBackground(getDrawable(R.drawable.grad_bg5));
            dialog.dismiss();
        } else if (view.getId() == bg6.getId()){
            show_shayri.setBackground(getDrawable(R.drawable.grad_bg6));
            dialog.dismiss();
        } else if (view.getId() == bg7.getId()){
            show_shayri.setBackground(getDrawable(R.drawable.grad_bg7));
            dialog.dismiss();
        } else if (view.getId() == bg8.getId()){
            show_shayri.setBackground(getDrawable(R.drawable.grad_bg8));
            dialog.dismiss();
        } else if (view.getId() == bg9.getId()){
            show_shayri.setBackground(getDrawable(R.drawable.grad_bg9));
            dialog.dismiss();
        } else {
            show_shayri.setBackground(getDrawable(R.drawable.grad_bg10));
            dialog.dismiss();
        }
    }
}