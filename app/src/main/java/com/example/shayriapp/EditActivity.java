package com.example.shayriapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.shayriapp.Adapters.ColorsAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EditActivity extends AppCompatActivity {

    TextView edit_textv,bg1, bg2, bg3, bg4, bg5, bg6, bg7, bg8, bg9, bg10, backgroundcolorbtn, textcolorbtn, textsizebtn, fontbtn, edit_emojis;
    ImageView edit_randombgbtn, edit_choosebgbtn;
    BottomSheetDialog dialog, dialogbg_color, dialogtxt_color, dialog_textsize, dialog_fonts, dialog_emojis;
    TextView[] col = new TextView[40];
    TextView[] txtcol = new TextView[40];
    int current_size = 26;
    String shayri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edit_textv = findViewById(R.id.edit_textv);
        edit_randombgbtn = findViewById(R.id.edit_randombgbtn);
        edit_choosebgbtn = findViewById(R.id.edit_choosebgbtn);
        backgroundcolorbtn = findViewById(R.id.backgroundcolorbtn);
        textcolorbtn = findViewById(R.id.textcolorbtn);
        textsizebtn = findViewById(R.id.textsizebtn);
        fontbtn = findViewById(R.id.fontbtn);
        edit_emojis = findViewById(R.id.edit_emojis);

        shayri = getIntent().getStringExtra("shayri");
        String emoji1 = getIntent().getStringExtra("emoji1");
        String emoji2 = getIntent().getStringExtra("emoji2");
        edit_textv.setText(emoji1 + shayri + emoji2);

        edit_randombgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ran = new Random().nextInt(AllShayris.GRADIENT.length);
                edit_textv.setBackground(getDrawable(AllShayris.GRADIENT[ran]));
            }
        });
        edit_choosebgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new BottomSheetDialog(EditActivity.this);
                dialog.setContentView(R.layout.choose_bg);

                GridView grad_gridView = dialog.findViewById(R.id.grad_gridView);
                ColorsAdapter adapter = new ColorsAdapter(EditActivity.this, false);
                grad_gridView.setAdapter(adapter);

                grad_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        edit_textv.setBackground(getResources().getDrawable(AllShayris.GRADIENT[i]));
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        backgroundcolorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogbg_color = new BottomSheetDialog(EditActivity.this, R.style.MyBottomSheetDialogTheme);
                dialogbg_color.setContentView(R.layout.select_color_dialog);
                dialogbg_color.setCancelable(false);

                ImageView closebtn = dialogbg_color.findViewById(R.id.closebtn);
                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogbg_color.dismiss();
                    }
                });

                GridView gridView = dialogbg_color.findViewById(R.id.gridView);
                ColorsAdapter adapter = new ColorsAdapter(EditActivity.this, true);
                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        edit_textv.setBackgroundColor(getResources().getColor(AllShayris.COLORS[i]));
                    }
                });

                dialogbg_color.show();
            }
        });
        textcolorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogtxt_color = new BottomSheetDialog(EditActivity.this, R.style.MyBottomSheetDialogTheme);
                dialogtxt_color.setContentView(R.layout.select_color_dialog);
                dialogtxt_color.setCancelable(false);

                ImageView closebtn = dialogtxt_color.findViewById(R.id.closebtn);
                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogtxt_color.dismiss();
                    }
                });

                GridView gridView = dialogtxt_color.findViewById(R.id.gridView);
                ColorsAdapter adapter = new ColorsAdapter(EditActivity.this, true);
                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        edit_textv.setTextColor(getResources().getColor(AllShayris.COLORS[i]));
                    }
                });

                dialogtxt_color.show();
            }
        });
        textsizebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_textsize = new BottomSheetDialog(EditActivity.this, R.style.MyBottomSheetDialogTheme);
                dialog_textsize.setContentView(R.layout.fontsize_dialog);
                dialog_textsize.setCancelable(false);

                ImageView closebtn2 = dialog_textsize.findViewById(R.id.closebtn2);
                closebtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog_textsize.dismiss();
                    }
                });

                SeekBar fontsize_bar = dialog_textsize.findViewById(R.id.fontsize_bar);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    fontsize_bar.setMin(10);
                    fontsize_bar.setMax(45);
                    fontsize_bar.setProgress(current_size);
                    fontsize_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                            current_size = i;
                            edit_textv.setTextSize(i);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });
                }

                dialog_textsize.show();
            }
        });
        fontbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_fonts = new BottomSheetDialog(EditActivity.this, R.style.MyBottomSheetDialogTheme);
                dialog_fonts.setContentView(R.layout.fonts_dialog);
                dialog_fonts.setCancelable(false);

                ImageView closebtn3 = dialog_fonts.findViewById(R.id.closebtn3);
                closebtn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog_fonts.dismiss();
                    }
                });

                ((TextView)dialog_fonts.findViewById(R.id.font1)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edit_textv.setTypeface(Typeface.DEFAULT);
                    }
                });

                ((TextView)dialog_fonts.findViewById(R.id.font2)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edit_textv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    }
                });

                ((TextView)dialog_fonts.findViewById(R.id.font3)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edit_textv.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                    }
                });

                ((TextView)dialog_fonts.findViewById(R.id.font4)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Typeface typeface = ResourcesCompat.getFont(EditActivity.this, R.font.advent_pro_medium);
                        edit_textv.setTypeface(typeface);
                    }
                });

                ((TextView)dialog_fonts.findViewById(R.id.font5)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Typeface typeface = ResourcesCompat.getFont(EditActivity.this, R.font.acme);
                        edit_textv.setTypeface(typeface);
                    }
                });

                ((TextView)dialog_fonts.findViewById(R.id.font6)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edit_textv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
                    }
                });

                ((TextView)dialog_fonts.findViewById(R.id.font7)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Typeface typeface = ResourcesCompat.getFont(EditActivity.this, R.font.agra);
                        edit_textv.setTypeface(typeface);
                    }
                });

                ((TextView)dialog_fonts.findViewById(R.id.font8)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Typeface typeface = ResourcesCompat.getFont(EditActivity.this, R.font.dev);
                        edit_textv.setTypeface(typeface);
                    }
                });

                ((TextView)dialog_fonts.findViewById(R.id.font9)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Typeface typeface = ResourcesCompat.getFont(EditActivity.this, R.font.himalaya);
                        edit_textv.setTypeface(typeface);
                    }
                });

                dialog_fonts.show();
            }
        });
        edit_emojis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_emojis = new BottomSheetDialog(EditActivity.this, R.style.MyBottomSheetDialogTheme);
                dialog_emojis.setContentView(R.layout.select_emoji_dialog);
                dialog_emojis.setCancelable(false);

                ImageView closebtn4 = dialog_emojis.findViewById(R.id.closebtn4);
                closebtn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog_emojis.dismiss();
                    }
                });

                TextView noemoji_txtv = dialog_emojis.findViewById(R.id.noemoji_txtv);
                noemoji_txtv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edit_textv.setText(shayri);
                    }
                });

                TextView[] emoji_txtv = new TextView[10];
                for (int i = 0; i < 10; i++) {
                    int id = getResources().getIdentifier("emoji_txtv"+i, "id", getPackageName());
                    emoji_txtv[i] = dialog_emojis.findViewById(id);
                    int finalI = i;
                    emoji_txtv[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            edit_textv.setText(emoji_txtv[finalI].getText().toString() + "\n" + shayri + "\n" + emoji_txtv[finalI].getText().toString());
                        }
                    });
                }

                dialog_emojis.show();
            }
        });
    }
}