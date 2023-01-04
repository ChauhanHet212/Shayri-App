package com.tuneonn.shayariapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tuneonn.shayariapp.Adapter.ColorsAdapter;

import java.util.Random;

public class EditActivity extends AppCompatActivity {

    ImageView edit_ran_col, edit_sel_col;
    TextView edit_shayritxtv, edit_background, edit_textcolor, edit_textsize, edit_emojis, edit_font;
    BottomSheetDialog dialog, bgcolor_dialog, txtcolor_dialog, textsize_dialog, emojis_dialog, font_dialog;
    int current_size = 26, po1, po2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edit_shayritxtv = findViewById(R.id.edit_shayritxtv);
        edit_ran_col = findViewById(R.id.edit_ran_col);
        edit_sel_col = findViewById(R.id.edit_sel_col);
        edit_background = findViewById(R.id.edit_background);
        edit_textcolor = findViewById(R.id.edit_textcolor);
        edit_textsize = findViewById(R.id.edit_textsize);
        edit_emojis = findViewById(R.id.edit_emojis);
        edit_font = findViewById(R.id.edit_font);

        String title = getIntent().getStringExtra("action_title");
        getSupportActionBar().setTitle(title);
        po1 = getIntent().getIntExtra("po1",0);
        po2 = getIntent().getIntExtra("po2",0);

        edit_shayritxtv.setText(AllShayris.ST_EMOJIS[po2] + AllShayris.ALL_SHAYRIS[po1][po2] + AllShayris.EN_EMOJIS[po2]);

        edit_ran_col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ran = new Random().nextInt(AllShayris.gradarr.length);
                edit_shayritxtv.setBackground(getDrawable(AllShayris.gradarr[ran]));
            }
        });

        edit_sel_col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new BottomSheetDialog(EditActivity.this);
                dialog.setContentView(R.layout.choosebg_dialog);

                GridView grad_gridView = dialog.findViewById(R.id.grad_gridView);
                ColorsAdapter adapter = new ColorsAdapter(EditActivity.this, false);
                grad_gridView.setAdapter(adapter);

                grad_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        edit_shayritxtv.setBackground(getResources().getDrawable(AllShayris.gradarr[i]));
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
        edit_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgcolor_dialog = new BottomSheetDialog(EditActivity.this, R.style.MyBottomSheetDialogTheme);
                bgcolor_dialog.setContentView(R.layout.colors_dialog);
                bgcolor_dialog.setCancelable(false);

                GridView gridView = bgcolor_dialog.findViewById(R.id.gridView);
                ImageView closebtn = bgcolor_dialog.findViewById(R.id.closebtn);
                ColorsAdapter colorsAdapter = new ColorsAdapter(EditActivity.this, true);
                gridView.setAdapter(colorsAdapter);

                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bgcolor_dialog.dismiss();
                    }
                });

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        edit_shayritxtv.setBackgroundColor(getResources().getColor(AllShayris.colorsarr[i]));
                    }
                });

                bgcolor_dialog.show();
            }
        });
        edit_textcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtcolor_dialog = new BottomSheetDialog(EditActivity.this, R.style.MyBottomSheetDialogTheme);
                txtcolor_dialog.setContentView(R.layout.colors_dialog);
                txtcolor_dialog.setCancelable(false);

                ImageView closebtn = txtcolor_dialog.findViewById(R.id.closebtn);
                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtcolor_dialog.dismiss();
                    }
                });

                GridView gridView = txtcolor_dialog.findViewById(R.id.gridView);
                ColorsAdapter adapter = new ColorsAdapter(EditActivity.this, true);
                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        edit_shayritxtv.setTextColor(getResources().getColor(AllShayris.colorsarr[i]));
                    }
                });

                txtcolor_dialog.show();
            }
        });
        edit_textsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textsize_dialog = new BottomSheetDialog(EditActivity.this, R.style.MyBottomSheetDialogTheme);
                textsize_dialog.setCancelable(false);
                textsize_dialog.setContentView(R.layout.text_size_dialog);

                ImageView closebtn1 = textsize_dialog.findViewById(R.id.closebtn1);

                closebtn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        textsize_dialog.dismiss();
                    }
                });

                SeekBar seekBar = textsize_dialog.findViewById(R.id.seekbar);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    seekBar.setMin(10);
                    seekBar.setMax(55);
                }
                seekBar.setProgress(current_size);

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        edit_shayritxtv.setTextSize(i);
                        current_size = i;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}
                });
                textsize_dialog.show();
            }
        });
        edit_emojis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emojis_dialog = new BottomSheetDialog(EditActivity.this, R.style.MyBottomSheetDialogTheme);
                emojis_dialog.setContentView(R.layout.emojis_dialog);
                emojis_dialog.setCancelable(false);

                ImageView closebtn2 = emojis_dialog.findViewById(R.id.closebtn2);

                closebtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        emojis_dialog.dismiss();
                    }
                });
                TextView noemoji_txtv = emojis_dialog.findViewById(R.id.noemoji_txtv);
                noemoji_txtv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edit_shayritxtv.setText(AllShayris.ALL_SHAYRIS[po1][po2]);
                    }
                });

                TextView[] emoji_txtv = new TextView[10];

                for (int i = 0; i < 10; i++) {
                    int id = getResources().getIdentifier("emoji_txtv" + i, "id", getPackageName());
                    emoji_txtv[i] = emojis_dialog.findViewById(id);
                    int finalI = i;
                    emoji_txtv[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String emoji = emoji_txtv[finalI].getText().toString();
                            edit_shayritxtv.setText(emoji + "\n" + AllShayris.ALL_SHAYRIS[po1][po2] + "\n" + emoji);
                        }
                    });
                }

                emojis_dialog.show();
            }
        });
        edit_font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                font_dialog = new BottomSheetDialog(EditActivity.this, R.style.MyBottomSheetDialogTheme);
                font_dialog.setCancelable(false);
                font_dialog.setContentView(R.layout.fonts_dialog);

                ImageView closebtn3 = font_dialog.findViewById(R.id.closebtn3);
                TextView font1 = font_dialog.findViewById(R.id.font1);
                TextView font2 = font_dialog.findViewById(R.id.font2);
                TextView font3 = font_dialog.findViewById(R.id.font3);
                TextView font4 = font_dialog.findViewById(R.id.font4);
                TextView font5 = font_dialog.findViewById(R.id.font5);
                TextView font6 = font_dialog.findViewById(R.id.font6);

                closebtn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        font_dialog.dismiss();
                    }
                });
                font1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edit_shayritxtv.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    }
                });
                font2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edit_shayritxtv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    }
                });
                font3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edit_shayritxtv.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                    }
                });
                font4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Typeface typeface = ResourcesCompat.getFont(EditActivity.this, R.font.agra);
                        edit_shayritxtv.setTypeface(typeface);
                    }
                });
                font5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Typeface typeface = ResourcesCompat.getFont(EditActivity.this, R.font.dev);
                        edit_shayritxtv.setTypeface(typeface);
                    }
                });
                font6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Typeface typeface = ResourcesCompat.getFont(EditActivity.this, R.font.himalaya);
                        edit_shayritxtv.setTypeface(typeface);
                    }
                });

                font_dialog.show();
            }
        });
    }
}