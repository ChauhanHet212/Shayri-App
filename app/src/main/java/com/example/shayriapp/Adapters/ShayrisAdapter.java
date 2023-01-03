package com.example.shayriapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shayriapp.AllShayris;
import com.example.shayriapp.R;

public class ShayrisAdapter extends BaseAdapter {

    Context context;
    int shayris_img;
    String[][] shayris_shayri;
    int po;

    ImageView shayri_item_img;
    TextView shayri_item_shayri;

    public ShayrisAdapter(Context context, int shayris_img, String[][] shayris_shayri, int po) {
        this.context = context;
        this.shayris_img = shayris_img;
        this.shayris_shayri = shayris_shayri;
        this.po = po;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.shayris_list_items, viewGroup, false);

        shayri_item_img = view.findViewById(R.id.shayri_item_img);
        shayri_item_shayri = view.findViewById(R.id.shayri_item_shayri);

        shayri_item_img.setImageResource(shayris_img);
        try {
            shayri_item_shayri.setText(AllShayris.ST_EMOJIS[i] + shayris_shayri[po][i] + AllShayris.EN_EMOJIS[i]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}
