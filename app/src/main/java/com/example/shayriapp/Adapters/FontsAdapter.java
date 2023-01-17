package com.example.shayriapp.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shayriapp.R;

public class FontsAdapter extends BaseAdapter {

    Context context;
    String[] fonts;

    public FontsAdapter(Context context, String[] fonts) {
        this.context = context;
        this.fonts = fonts;
    }

    @Override
    public int getCount() {
        return fonts.length;
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
        view = LayoutInflater.from(context).inflate(R.layout.fonts_dialog_item, viewGroup, false);
        TextView TextView = view.findViewById(R.id.TextView);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fonts[i]);
        TextView.setTypeface(typeface);
        return view;
    }
}
