package com.tuneonn.shayariapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuneonn.shayariapp.AllShayris;
import com.tuneonn.shayariapp.R;

public class ShayrisAdapter extends BaseAdapter {

    Context context;
    int image;
    String[][] shayris;
    int po;

    ImageView shayris_item_img;
    TextView shayris_item_shayri;

    public ShayrisAdapter(Context context, int image, String[][] shayris, int po) {
        this.context = context;
        this.image = image;
        this.shayris = shayris;
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

        shayris_item_img = view.findViewById(R.id.shayris_item_img);
        shayris_item_shayri = view.findViewById(R.id.shayris_item_shayri);

        shayris_item_img.setImageResource(image);
        try {
            shayris_item_shayri.setText(AllShayris.ST_EMOJIS[i] + AllShayris.ALL_SHAYRIS[po][i] + AllShayris.EN_EMOJIS[i]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}
